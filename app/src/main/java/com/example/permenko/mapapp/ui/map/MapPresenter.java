package com.example.permenko.mapapp.ui.map;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.api.ApiFactory;
import com.example.permenko.mapapp.api.FuraApi;
import com.example.permenko.mapapp.api.data.PlaceResponse;
import com.example.permenko.mapapp.db.data.PlaceRealmModel;
import com.example.permenko.mapapp.ui.data.Place;
import com.example.permenko.mapapp.util.RetryWithDelayExponential;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import java.util.ArrayList;
import java.util.List;

public class MapPresenter {
  private final FuraApi furaApi;
  private final CompositeDisposable compositeDisposable = new CompositeDisposable();

  public MapPresenter() {
    furaApi = ApiFactory.furaApi();
  }

  public void attachView(@NonNull MapView mapView) {
    loadPlaces(1, mapView);
  }

  public void detachView() {
    compositeDisposable.dispose();
  }

  private void loadPlaces(int startId, @NonNull MapView mapView) {
    compositeDisposable.add(
        cached()
            .flatMap(cachedList -> {
              if (cachedList.isEmpty()) return places(startId);
              if (cachedList.get(cachedList.size() - 1).id() <= startId) return places(startId);
              return Observable.just(cachedList);
            })
            .doOnSubscribe(disposable -> mapView.showLoading())
            .subscribeWith(new DisposableObserver<List<Place>>() {
              @Override public void onNext(List<Place> places) {
                for (Place place : places) {
                  mapView.showPlaces(place);
                }
                if (places.size() > 0) loadPlaces(places.get(places.size() - 1).id(), mapView);
              }

              @Override public void onError(Throwable e) {
                e.printStackTrace();
              }

              @Override public void onComplete() {
                mapView.hideLoading();
              }
            }));
  }

  @NonNull Observable<List<Place>> places(int startId) {
    return furaApi.places(startId)
        .subscribeOn(Schedulers.io())
        .observeOn(Schedulers.computation())
        .retryWhen(new RetryWithDelayExponential(Integer.MAX_VALUE, 5000))
        .flatMap(response -> Observable.create((ObservableOnSubscribe<List<PlaceResponse>>) emitter -> {
          emitter.onNext(response.places());
          if (!response.hasMorePages()) emitter.onComplete();
        }))
        .map(placeResponseList -> {
          List<Place> places = new ArrayList<>();
          for (PlaceResponse placeResponse : placeResponseList) {
            places.add(Place.create(placeResponse));
          }
          return places;
        })
        .flatMap(this::cache)
        .observeOn(AndroidSchedulers.mainThread());
  }

  @NonNull private Observable<List<Place>> cache(@NonNull List<Place> places) {
    return Observable.fromIterable(places)
        .map(PlaceRealmModel::new)
        .toList()
        .map(placeRealmModels -> {
          Realm.getDefaultInstance().executeTransaction(realm -> realm.insertOrUpdate(placeRealmModels));
          return placeRealmModels;
        })
        .toObservable()
        .flatMap(Observable::fromIterable)
        .map(Place::create)
        .toList()
        .toObservable();
  }

  @NonNull private Observable<List<Place>> cached() {
    return Observable.just(Realm.getInstance(Realm.getDefaultConfiguration()).where(PlaceRealmModel.class).findAll())
        .flatMap(Observable::fromIterable)
        .map(Place::create)
        .toList()
        .toObservable();
  }
}
