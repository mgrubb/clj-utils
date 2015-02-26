(ns co.grubb.clj-utils.seq)

(defn seq*
  "If x is a coll return a sequence of it, otherwise return [x]"
  [x]
  (if (sequential? x)
    (seq x)
    (list x)))
