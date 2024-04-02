import { getApp, getApps, initializeApp } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-app.js";
import { getMessaging, getToken, deleteToken } from "https://www.gstatic.com/firebasejs/10.8.1/firebase-messaging.js";
const config =  {
    apiKey: "AIzaSyCrxeDZgBU1P35FZKQTDE2XuMngMS4NGWY",
    authDomain: "web8-e13cd.firebaseapp.com",
    projectId: "web8-e13cd",
    storageBucket: "web8-e13cd.appspot.com",
    messagingSenderId: "127047419469",
    appId: "1:127047419469:web:a52ef7a8d388f157cf7e04",
    measurementId: "G-0C4KPY0LQG"
    };
console.log(getApps().length);
const app = getApps().length === 0 ? initializeApp(config) : getApp();
const messaging = getMessaging(app);
window.addEventListener('load', () => {
    const subButton = document.getElementById("subscribeButton");
    const unsubButton = document.getElementById("unsubscribeButton");
    unsubButton.addEventListener("click", unsubscribeToFirebase);
    subButton.addEventListener("click", subscribeToFirebase);
});
function subscribeToFirebase() {
    if ('Notification' in window) {
        if (Notification.permission === 'default') {
            Notification.requestPermission().then((permission) => {
                if (permission === 'granted') {
                    console.log('Разрешение предоставлено');
                    getSubscribe();
                } else {
                    console.log('В разрешении отказано');
                }
            });
        } else if (Notification.permission === 'granted') {
            console.log('Разрешение уже предоставлено');
            getSubscribe();
        } else {
            console.log('В разрешении отказано');
        }
    } else {
        console.log('Уведомления не доступны');
    }
}

function getSubscribe() {
    if ('serviceWorker' in navigator) {
        navigator.serviceWorker.register('/js/service-worker.js')
            .then(function(registration) {
                console.log('Service Worker зарегистрирован:', registration);
                getToken(messaging, {serviceWorkerRegistration: registration, vapidKey: "BE8IniBNmvj9XbLPxuCxvdiqhduej3uyBPPZNcDU-tlWGFBAhGOAMr1s8ZY8Psmj87w-w4WNQA5BZMp72pzgs9w"})
                    .then(function (currentToken) {
                        console.log(currentToken);
                        if (currentToken) {
                            sendTokenToServer(currentToken);
                        } else {
                            console.warn('Не удалось получить токен.');
                            setTokenSentToServer(false);
                        }
                    })
                    .catch(function (err) {
                        console.warn('При получении токена произошла ошибка.', err);
                        setTokenSentToServer(false);
                    });
            })
        .catch(function(error) {
                console.error('Ошибка при регистрации Service Worker:', error);
        });
    }
}


function sendTokenToServer(currentToken) {
    if (!isTokenSentToServer(currentToken)) {
        console.log('Отправка токена на сервер...');
        fetch('/tokens/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(currentToken)
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ошибка отправки токена');
                }
                console.log("Токен сохранён на сервере");
                setTokenSentToServer(currentToken);
                alert("Вы подписались к уведомлениям");
            })
            .catch(error => {
                console.error('Ошибка: ', error);
            });
    } else {
        console.log('Токен уже отправлен на сервер.');
    }
}

function setTokenSentToServer(currentToken) {
    window.localStorage.setItem(
        'sentFirebaseMessagingToken',
        currentToken ? currentToken : ''
    );
}

function isTokenSentToServer(currentToken) {
    return window.localStorage.getItem('sentFirebaseMessagingToken') === currentToken;
}

function unsubscribeToFirebase(){
    const token = window.localStorage.getItem('sentFirebaseMessagingToken');
    if (token !== null) {
        fetch('/tokens/delete', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(token)
        }).then(response => {
            if (!response.ok) {
                throw new Error('Ошибка удаления токена');
            }
            if ('serviceWorker' in navigator) {
                navigator.serviceWorker.register('/js/service-worker.js').then(function(registration) {
                        console.log('Service Worker зарегистрирован:', registration);
                        deleteToken(messaging).then(() => {
                            window.localStorage.removeItem('sentFirebaseMessagingToken');
                            console.log("Токен удалён");
                            alert("Вы отписались от уведомлений");
                        }).catch(error => {
                            console.error('Ошибка удаления токена: ', error);
                        });
                }).catch(error => {
                        console.error('Ошибка при регистрации Service Worker: ', error);
                    });
            }
        }).catch(error => {
            console.error('Ошибка: ', error);
        });
    }
}