(ns owlet-ui.components.settings.activity-classifier
  (:require cljsjs.handsontable
            [reagent.core :as reagent]
            [javelin.core :refer [cell] :refer-macros [cell=]]))

(def row-data
  [{:name "foo" :color "#000"}
   {:name "bar" :color "#fff"}
   {:name "waz" :color "#ccc"}])

(def columns-defs
  ["name" "color" "preview"])

(def row-count (cell (count row-data)))

(def row-count-inc (cell= row-count))

(defn create-row!
  ([t]
   (.setDataAtCell t @row-count 0 (str "foo-" @row-count))
   (.setDataAtCell t @row-count 1 "#fff")
   (.setDataAtCell t @row-count 2 "#fff")
   (swap! row-count inc))
  ([t row col name color]
   (.setDataAtCell t row col name)
   (.setDataAtCell t row (+ col 1) color)
   (.setDataAtCell t row (+ col 2) color)
   (swap! row-count inc)))

(defn remove-row!
  [t]
  (when (> @row-count 0)
    (swap! row-count dec)
    (.alter t "remove_row" @row-count)))

(defn color-renderer [instance td row col prop value cellProperties])

(defn activity-classsifer-component
  "Activity classifier component"
  []
  (let [table (js-obj)
        row-data-w-preview (mapv #(assoc % :preview (:color %)) row-data)]
    ;; anonymous formula cell for observing side effects
    (cell= (.log js/console row-count-inc))
    (reagent/create-class
      {:component-did-mount
       (fn []
         (aset table "ref"
               (new js/Handsontable
                    (js/document.getElementById "tag-classifier-container")
                    (clj->js {:data       (clj->js row-data-w-preview)
                              :colHeaders columns-defs
                              :rowHeaders true}))))
                              ;:columns [
                              ;          {:data "preview"
                              ;           :renderer color-renderer}))))
       :reagent-render
       (fn []
         [:div.jumbotron
          [:h2.display-6 "Tag Name Classifier"]
          [:div#tag-classifier-container]
          [:button.btn.btn-success.btn-sm
           {:on-click #(create-row! (aget table "ref"))} "+"]
          [:button.btn.btn-success.btn-sm
           {:on-click #(remove-row! (aget table "ref"))} "-"]])})))