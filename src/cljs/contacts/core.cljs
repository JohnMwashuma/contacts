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
(defn contacts []
  (om/ref-cursor (:contacts (om/root-cursor app-state))))

(defn contact-view [contact owner]
  (reify
    om/IRender
      (render [_]
        (dom/div nil 
                    (dom/div nil (:name contact))
                    (dom/div nil (:email contact))
                    (dom/button #js {:onClick (fn [e]
                                                (let [cs (contacts)]
                                                (om/update! cs
                                                (vec (remove #(=
                                                (:id contact) (:id %))
                                                cs)))))} 
                    "Delete")))))

(defn contacts-list-view [contacts owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil 
                  (to-array (om/build-all contact-view contacts))))))

(defn app-view [state owner]
  (reify
    om/IRender
    (render [_]
      (dom/div nil 
                  (om/build contacts-list-view (:contacts state))))))

(defn render []
(om/root
 app-view
 app-state
 {:target (.getElementById js/document "app")}))
