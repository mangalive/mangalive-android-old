// Generated by Dagger (https://dagger.dev).
package com.acg.mangalive.data.network;

import com.acg.mangalive.domain.model.SortingParametersNotifications;
import dagger.internal.DaggerGenerated;
import javax.inject.Provider;
import kotlinx.coroutines.CoroutineDispatcher;

@DaggerGenerated
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NotificationsPagingSource_Factory {
  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private final Provider<NotificationsService> notificationsServiceProvider;

  public NotificationsPagingSource_Factory(Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<NotificationsService> notificationsServiceProvider) {
    this.ioDispatcherProvider = ioDispatcherProvider;
    this.notificationsServiceProvider = notificationsServiceProvider;
  }

  public NotificationsPagingSource get(
      SortingParametersNotifications sortingParametersNotifications) {
    return newInstance(sortingParametersNotifications, ioDispatcherProvider.get(), notificationsServiceProvider.get());
  }

  public static NotificationsPagingSource_Factory create(
      Provider<CoroutineDispatcher> ioDispatcherProvider,
      Provider<NotificationsService> notificationsServiceProvider) {
    return new NotificationsPagingSource_Factory(ioDispatcherProvider, notificationsServiceProvider);
  }

  public static NotificationsPagingSource newInstance(
      SortingParametersNotifications sortingParametersNotifications,
      CoroutineDispatcher ioDispatcher, NotificationsService notificationsService) {
    return new NotificationsPagingSource(sortingParametersNotifications, ioDispatcher, notificationsService);
  }
}
