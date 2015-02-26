(ns clj-utils.seq
  "Collection utility functions")

(defn seq*
  "If x is a coll return a sequence of it, otherwise return [x]"
  [x]
  (if (sequential? x)
    (seq x)
    (list x)))
