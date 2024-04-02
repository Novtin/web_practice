self.addEventListener('install', function(event) {
    console.log('Service Worker установлен');
});

self.addEventListener('activate', function(event) {
    console.log('Service Worker активирован');
});

self.addEventListener('push', function(event) {
    const payload = event.data.json();
    const title = payload.notification.title;
    const body = payload.notification.body;
    const click_action= payload.notification.click_action;
    event.waitUntil(
        self.registration.showNotification(title, {
            body: body,
            data: {
                clickUrl: click_action
            }
        })
    );
});

self.addEventListener('notificationclick', function(event) {
    event.notification.close();
    const clickUrl = event.notification.data.clickUrl;
    if (clickUrl) {
        event.waitUntil(
            clients.openWindow(clickUrl)
        );
    }
});