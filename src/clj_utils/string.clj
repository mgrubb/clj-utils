(ns clj-utils.string
  "String utility functions"
  (:require [clojure.string :as str]))

(defmulti to-str
  "Convert object x to a string, if it is a clojure.lang.Named use name instead of str"
  type)

(defmethod to-str clojure.lang.Named
  [x]
  (name x))

(defmethod to-str :default
  [x]
  (str x))

(defn munge-string
  "Replace one ore more occurences of whitespace or '_' with '-'"
  [s]
  (str/replace s #"[\s_]+" "-"))

(defn keywordize
  "Translate string s to a keyword.
  This does more processing of s than simply keyword.
  It lower-cases, munge-string before keyword."
  [s]
  (-> s
      to-str
      str/lower-case
      munge-string
      keyword))
