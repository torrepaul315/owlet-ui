(ns owlet-ui.firebase
  (:require cljsjs.firebase))

(defonce owlet-users-fb-db
         {:apiKey        "AIzaSyAbs6wXxPGX-8XEWR6nyj7iCETOL6dZjzY"
          :authDomain    "owlet-users.firebaseapp.com"
          :databaseURL   "https://owlet-users.firebaseio.com"
          :storageBucket "owlet-users.appspot.com"})

(defonce firebase-app
         (.initializeApp js/firebase (clj->js owlet-users-fb-db)))

(defonce firebase-db-ref
         (-> firebase-app .database .ref))

(defn ref-for-path
  "Returns a Firebase ref for the node at the given
  path string relative to firebase-db-ref."
  [rel-path]
  (.child firebase-db-ref rel-path))

(defonce am-i-online?
         (-> firebase-app
             .database
             .ref
             (.child ".info/connected")))
