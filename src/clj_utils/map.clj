(ns clj-utils.map
  (:require [clj-utils.string :refer (to-str keywordize)]
            [clojure.walk :refer (prewalk)]))

(defn map-entry?
  "Does x conform to c.l.IMapEntry?"
  [x]
  (isa? (type x) clojure.lang.IMapEntry))

(defn map-entry
  "Create a map entry with key k and value v"
  [k v]
  (clojure.lang.MapEntry. k v))

(defn apply-to-keys
  "Applies a function to all keys in a nested map"
  [f m]
  (prewalk #(if (map-entry? %)
              (map-entry (f (key %)) (val %))
              %)
           m))

(defn apply-to-vals
  "Applies a function to all values in a nested map"
  [f m]
  (prewalk #(if (map-entry? %)
              (map-entry (key %) (f (val %)))
              %)
           m))

(defn stringify-keys
  "Just like clojure.walk/stringify-keys except it will stringify any key"
  [m]
  (apply-to-keys to-str m))

(defn keywordize-keys
  "Just like clojure.walk/keywordize-keys except it uses clj-utils.string/keywordize"
  [m]
  (apply-to-keys keywordize m))
