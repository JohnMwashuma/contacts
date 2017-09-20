(ns contacts.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(defonce app-state (atom {:text "Hello Chestnut!"
                          :contacts [{:id 1
                                      :name "John"
                                      :email "jmwashuma@live.com"}
                                     {:id 2
                                      :name "Roy"
                                      :email "roy@live.com"}
                                     {:id 3
                                      :name "Kip"
                                      :email "kip@live.com"}]}))

(defn root-component [app owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil (dom/h1 nil (:text app))))))

(defn render []
  (om/root
   root-component
   app-state
   {:target (js/document.getElementById "app")}))
