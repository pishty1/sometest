(ns sometest.layout
  (:require 
   [cljsjs.material-ui]
   [cljs-react-material-ui.core :as ui]
   [cljs-react-material-ui.reagent :as rui]
   [cljs-react-material-ui.icons :as ic]
   [reagent.core :as reagent :refer [atom]]))

(enable-console-print!)

(def app-state (atom {:text "Hello Chestnut from layout"
                      :clicktext "Click me"
                      :left-nav {:open false}}))

(def refresh-left-nav (atom false))

(def menu-items [
                   {:payload "home" :text "Home" :icon "action-home"}
                   {:payload "favorites" :text "Favorites" :icon "action-favorite"}
                   {:payload "about" :text "About" :icon "action-info"}
                   ])

(defn greeting []
  [:h1 (:text @app-state)])

(defn home-page []
  [rui/mui-theme-provider
   {:mui-theme (ui/get-mui-theme
                {:palette {:text-color (ui/color :green600)}})}
   [:div
    [rui/app-bar {:title              "BitNews"
                  :icon-element-right (ui/icon-button
                                        (ic/action-account-balance-wallet))}]

    
    [rui/paper
     [:div {:class "content"} 
      (:text @app-state)]
     [rui/mui-theme-provider
      {:mui-theme (ui/get-mui-theme {:palette {:text-color (ui/color :blue200)}})}
      [rui/raised-button {:label "Blue button"}]]
     (ic/action-home {:color (ui/color :grey600)})
     [rui/raised-button {:label        (:clicktext @app-state)
                         :icon         (ic/social-group)
                         :on-touch-tap #(println "clicked")}]]]])



(defn swapsi []
  (swap! app-state assoc-in [:left-nav :open] (not (get-in @app-state [:left-nav :open])))
  (println "open left nav: " (get-in @app-state [:left-nav :open]))
  (swap! refresh-left-nav not)
  ;(-> (.' left-nav -refs.leftNav) .open)
)

(defn drawsy []
  [rui/drawer {:open (get-in @app-state [:left-nav :open])
               :docked false
               :onRequestChange swapsi
               :disableSwipeToOpen true}])

(defn my-component []
  (reagent/create-class
    {:component-did-mount #(println "component-did-mount yayayayay")
     :display-name        "my-component"
     :reagent-render      (fn []
                            [rui/paper "My component"])}))


(defn default []
  [rui/mui-theme-provider
   {:mui-theme (ui/get-mui-theme (-> js/MaterialUIStyles
                       (aget "lightBaseTheme")))}
   [:div
    [rui/app-bar {:title              "BitNews"
                  :icon-element-right 
                  (ui/icon-button
                   (ic/action-account-balance-wallet))
                  :on-left-icon-button-touch-tap swapsi
                  :zDepth 2}]
    [drawsy]
    [:br]
    [:div.container "This is content"]
    [rui/paper
     [:div  (:text @app-state)]
     [rui/mui-theme-provider
      {:mui-theme (ui/get-mui-theme {:palette {:text-color (ui/color :blue200)}})}
      [rui/raised-button {:label "Blue button"}]]
     (ic/action-home {:color (ui/color :grey600)})
     [rui/raised-button {:label        (:clicktext @app-state)
                         :icon         (ic/social-group)
                         :on-touch-tap #(println "clicked")}]]
    [my-component]]])

(reagent/render [default] (js/document.getElementById "appsi"))
