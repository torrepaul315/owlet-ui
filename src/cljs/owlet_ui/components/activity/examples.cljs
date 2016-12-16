(ns owlet-ui.components.activity.examples
  (:require [cljsjs.dropzone]
            [reagent.core :as reagent]
            [reagent.debug :refer-macros [dbg]]))

(defn examples-component []
  (reagent/create-class
    {:component-did-mount
     (fn []
       ;; disable auto discover of form.dropzone
       (set! (.-autoDiscover js/Dropzone) false)
       (js/Dropzone. ".dropzone" #js {:url "http://localhost:3000/api/content/foobar"}))
     :reagent-render
     (fn []
       [:div.activity-examples-wrap.box-shadow
        "example component"
        [:form {:id "my-awesome-dropzone"
                :class "dropzone"}]])}))
