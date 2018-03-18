package com.example.permenko.mapapp.util;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import java.util.concurrent.TimeUnit;

public class RetryWithDelayExponential implements Function<Observable<? extends Throwable>, Observable<?>> {
  private final int maxRetries;
  private final int retryDelayMillis;
  private int retryCount;

  public RetryWithDelayExponential(final int maxRetries, final int retryDelayMillis) {
    this.maxRetries = maxRetries;
    this.retryDelayMillis = retryDelayMillis;
    this.retryCount = 0;
  }

  @Override
  public Observable<?> apply(final Observable<? extends Throwable> attempts) {
    return attempts
        .flatMap((Function<Throwable, Observable<?>>) throwable -> {
          if (++retryCount < maxRetries) {
            // When this Observable calls onNext, the original
            // Observable will be retried (i.e. re-subscribed).
            return Observable.timer((long) (retryDelayMillis * Math.pow(2, retryCount - 1)),
                TimeUnit.MILLISECONDS);
          }

          // Max retries hit. Just pass the error along.
          return Observable.error(throwable);
        });
  }
}
