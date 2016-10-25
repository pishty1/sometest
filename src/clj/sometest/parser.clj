(ns sometest.parser
  (:require [reddit.clj.core :as reddit])) 
   
;; create a reddit client with reddit/login.
;; you can also pass nil as username and password to use it
;; anonymously
(def rc (reddit/login "papabear666" "Monday201210!!"))

;; load reddits from subreddit "clojure", and print titles
(doseq 
  [title (map :title (reddit/reddits rc "clojure"))] 
  (println title))

;; vote-up a thing on reddit
(reddit/vote-up rc "t3_iz61z")

;; you may also submit links to reddit.
;; permalink will be returned when success.
;; be careful to use this API because reddit may ask you for a 
;; captcha. But as a library, reddit.clj will return nil on this case.
(reddit/submit-link rc "title" "url" "subreddit-name")


(defn parsley []
  (reddit/reddits rc "clojure"))
