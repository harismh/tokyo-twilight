(ns clojurescript
  (:require
   [dumdom.core :as dd]
   [todomvc.ui :as ui]
   [todomvc.model :as model]))

(defonce store (atom model/initial-state))

(defn render! []
  (dd/render
   [ui/shell (model/prepare-ui-data @store)]
   (js/document.getElementById "app")))

(dd/set-event-handler!
 (fn [event actions]
   (doseq [[action data] actions]
     (if (nil? (action model/available-actions))
       (throw (js/Error. (str "Unknown action " action)))
       (let [event-val (.. event -target -value)
             event-key (.. event -keyCode)
             payload   (cond-> (or data nil)
                         event-val (assoc-in [:event :value] event-val)
                         event-key (assoc-in [:event :key-code] event-key))]
         (prn "Triggered action" action payload)
         (reset! store (model/handle-action action payload @store)))))))

(defn ^:export init []
  (add-watch store
             ::re-render
             (fn [_ _ _ _] (render!)))
  (render!))