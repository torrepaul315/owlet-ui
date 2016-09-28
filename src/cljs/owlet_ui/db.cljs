(ns owlet-ui.db
  (:require [javelin.core :refer [cell] :refer-macros [cell= defc defc=]]
            [owlet-ui.config :as config]))

;; init default *user-state* db
;; ----------------------------

(def user-state-default
  {:user
   {:logged-in?                false
    :social-id                 nil
    :content-entries           []
    :background-image          config/default-header-bg-image
    :background-image-entry-id nil}
   :app
   {:initialized? false}
   :activities                  []
   :activity-models             nil
   :activities-by-track-in-view {}
   :activities-by-track         {}
   :activities-in-view          nil
   :activity-in-view            nil})

;; begin default *app-state* db
;; ----------------------------------------
;; NOTE: these definitions use the defc & defc= macros
;; provided by javelin for ~spreadsheet like FRP awesomeness

(defc error-name "default error name")
(defc error-message "default error message")

(defc= error-bubble
       {:name error-name
        :message error-message})