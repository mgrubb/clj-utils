# co.grubb.clj-utils

A Clojure library that contains various utility functions that I use.

## Releases and Dependency Information

* Latest stable release is 0.4.1

[Leiningen](http://leiningen.org/) dependency information:

```clojure
[co.grubb/clj-utils "0.4.1"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>co.grubb</groupId>
  <artifactId>clj-utils</artifactId>
  <version>0.4.1</version>
</dependency>
```

## Usage

### clj-utils.seq

__seq*__
Return a sequence of the given argument, even if it is a scalar value.
```clojure
(seq* :a)
; => (:a)

(seq* [1 2 3])
; => (1 2 3)
```
### clj-utils.string

__to-str__
Convert an arugment to its name or string reprensentation.
```clojure
(to-str 'a)
; => "a"

(to-str :a)
; => "a"

(to-str {:a 1})
; => "{:a 1}"
```
__munge-string__
Replace space and `_` characters with a single `-`
```clojure
(munge-string "abc def")
; => "abc-def"

(munge-string "abc_def")
; => "abc-def"

(munge-string "abc _def")
; => "abc-def"

(munge-string "abc-def")
; => "abc-def"
```

__keywordize__
Transform the argument into a keyword by lower-casing and munging it.
```clojure
(keywordize "abc")
; => :abc

(keywordize "abc_def")
; => :abc-def

(keywordize "Abc Def")
; => :abc-def
```

### clj-utils.map

__map-entry?__ Returns true if argument is a IMapEntry
```clojure
(map-entry? (first {:a 1}))
; => true
```

__map-entry__ Create a MapEntry object
```clojure
(map-entry :k "value")
; => [:k "value"]
```

__apply-to-keys__ Apply a function to all the keys in a nested map
```clojure
(apply-to-keys #(-> % name (str "-x") keyword) {:a {:b 2}})
; => {:a-x {:b-x 2}}
```

__apply-to-leaves__ Apply a function to all the leaves in a nested map
```clojure
(apply-to-leaves (partial * 2) {:a {:b 2, :c 3}, :d 4})
; => {:a {:b 4, :c 6}, :d 8}
```

__stringify-keys__ Just like `clojure.walk/stringify-keys` except it will stringify any key type
```clojure
(stringify-keys {:a {'b {[1 2] :c}}})
; => {"a" {"b" {"[1 2]" :c}}}
```

__keywordize-keys__ Just like `clojure.walk/keywordize-keys` except it uses `clj-utils.string/keywordize` instead of `keyword`.
```clojure
(keywordize-keys {"a_x" {"b x" :c}, "d _x" :e})
; => {:a-x {:b-x :c}, :d-x :e}
```

__keys-in__ Return a vector of vectors containing the key paths in a map
```clojure
(def m {:a {:b {:c 1, :d 2}, :e 3}, :f 4, :g {:h 5, :i {:j 6}}})

(keys-in m)
; => [[:g :h] [:g :i :j] [:f] [:a :e] [:a :b :c] [:a :b :d]]

(map (partial get-in m) (keys-in m))
; => (5 6 4 3 1 2)
```

### clj-utils.java

__apply-java-new__ Calls the contstructor for class `c` with arguments `args`.
```clojure
(def m {:scheme "http" :host "example.com" :port -1 :path "/index.html"})
(apply-java-new java.net.URI
                ((juxt :scheme :user-info :host :port :path :query :fragment} m)))
;=> #<URI http://example.com/index.html>
```

## License

Copyright © 2015 Michael Grubb. All Rights Reserved.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
