(ns owlet.views.settings
  (:require
    [owlet.utils :refer [get-user-cms-profile]]
    [reagent.validation :as validation]
    [reagent.session :as session]
    [reagent.core :as reagent :refer [atom]]
    [ajax.core :refer [PUT]]))

(defonce server-url "http://localhost:3000")

(def district-id
  (atom nil))

(defn settings-page []
      (reagent/create-class
        {:component-did-mount
         (fn []
             (get-user-cms-profile
               (session/get :user-id)
               #(reset! district-id (:district_id %))))
         :reagent-render
         (fn []
             (let []
                  [:div.container
                   [:div.row
                    [:div.col-lg-12
                     [:div.jumbotron
                      [:h1 "My Settings"]
                      [:div.search
                       [:label "District ID:"
                        [:input.test {:type        "text"
                                      :value       @district-id
                                      :placeholder 123456
                                      :max-length 6
                                      :on-change   #(reset! district-id (-> % .-target .-value))}]
                        [:input {:type     "submit"
                                 :value    "Enter"
                                 :on-click #(when (and (validation/has-value? (session/get :user-id))
                                                       (validation/has-value? @district-id))
                                                  (PUT (str server-url "/api/users-district-id")
                                                       {:params  {:district-id @district-id
                                                                  :user-id     (session/get :user-id)}
                                                        :handler (fn [res]
                                                                     (js/alert res))}))}]]]]]]]))}))