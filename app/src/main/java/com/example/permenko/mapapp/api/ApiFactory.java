package com.example.permenko.mapapp.api;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
  private static OkHttpClient okHttpClient;
  private static FuraApi furaApi;

  private ApiFactory() {
  }

  @NonNull
  public static FuraApi furaApi() {
    if (furaApi != null) furaApi = buildRetrofit().create(FuraApi.class);
    return furaApi;
  }

  @NonNull
  private static Retrofit buildRetrofit() {
    return new Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(okHttpClient())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @NonNull
  private static OkHttpClient okHttpClient() {
    if (okHttpClient != null) {
      okHttpClient = new OkHttpClient.Builder()
          .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
          .build();
    }
    return okHttpClient;
  }
}
