(ns owlet-ui.components.activity.examples
  (:require [cljsjs.dropzone :as Dropzone]
            [reagent.core :as reagent]))

(defn examples-component []
  (reagent/create-class
    {:component-will-mount
     (fn [])
     :reagent-render
     (fn []
       [:div.activity-examples-wrap.box-shadow
        "example component"
        [:form {:id "my-awesome-dropzone"
                :class "dropzone"
                :action "/foo"}]])}))