package com.example.permenko.mapapp.api;

import android.support.annotation.NonNull;
import com.example.permenko.mapapp.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {
  private static OkHttpClient okHttpClient;
  private static FuraApi furaApi;

  private ApiFactory() {
  }

  @NonNull
  public static FuraApi furaApi() {
    if (furaApi != null) return furaApi;
    return buildRetrofit().create(FuraApi.class);
  }

  @NonNull
  private static Retrofit buildRetrofit() {
    return new Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .client(okHttpClient())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build();
  }

  @NonNull
  private static OkHttpClient okHttpClient() {
    if (okHttpClient != null) return okHttpClient;
    return new OkHttpClient.Builder()
        .addInterceptor(new HttpLoggingInterceptor())
        .build();
  }
}
