(ns owlet-ui.components.error
  (:require [owlet-ui.db :refer [error-bubble]]))

(defn error-component []
  (let []
    [:div.error-display
     [:h1 (:name @error-bubble)]
     [:p  (:message @error-bubble)]]))
