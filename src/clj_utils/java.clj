(ns clj-utils.java
  "Java-interop utilities"
  )

(defmacro apply-java-new
  "Calls the Java object contructor for class c, with argument args"
  ([c] `(new ~c))
  ([c args] `(new ~c ~@(eval args))))
