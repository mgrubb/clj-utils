(ns clj-utils.map
  (:require [clj-utils.string :refer (to-str keywordize)]
            [clojure.zip :as z]
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

(defn apply-to-leaves
  "Applies a function to all leaf values in a nested map"
  [f m]
  (prewalk #(if (and (map-entry? %) (not (map? (val %))))
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

;; Credit goes to A Webb @ stackoverflow (http://stackoverflow.com/users/1756702/a-webb)
;; A node is a vector of a path and a map
;; The path is the list of keys to the current map (i.e. (get-in m path))
;; The map (or value) is the value at that path.
;; A node is a branch if it is a map otherwise it is a leaf node
;; The node's children are the all the top level keys in the node
;; At a leaf node, record the current path in the accumulator `path`
(defn keys-in
  "Return all the keys in the nested hash m"
  [m]
  (letfn [(branch? [[path m]] (map? m))
          (children [[path m]] (for [[k v] m] [(conj path k) v]))]
    (if (empty? m)
      []
      (loop [zm (z/zipper branch? children nil [[] m]) paths []]
        (cond (z/end? zm) paths
              (z/branch? zm) (recur (z/next zm) paths)
              :leaf (recur (z/next zm) (conj paths (first (z/node zm)))))))))
