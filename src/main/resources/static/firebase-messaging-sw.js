importScripts("https://www.gstatic.com/firebasejs/8.0.0/firebase-app.js");
importScripts("https://www.gstatic.com/firebasejs/8.0.0/firebase-messaging.js");

const config = {
    apiKey: "AIzaSyCrxeDZgBU1P35FZKQTDE2XuMngMS4NGWY",
    authDomain: "web8-e13cd.firebaseapp.com",
    projectId: "web8-e13cd",
    storageBucket: "web8-e13cd.appspot.com",
    messagingSenderId: "127047419469",
    appId: "1:127047419469:web:a52ef7a8d388f157cf7e04",
    measurementId: "G-0C4KPY0LQG"
};
firebase.initializeApp(config)
const messaging = firebase.messaging();