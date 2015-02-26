# co.grubb.clj-utils

A Clojure library that contains various utility functions that I use.

## Releases and Dependency Information

* Latest stable release is 0.1.0

[Leiningen](http://leiningen.org/) dependency information:

```clojure
[co.grubb/clj-utils "0.1.0"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>co.grubb</groupId>
  <artifactId>clj-utils</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Usage

### co.grubb.clj-utils.seq

__seq*__
Return a sequence of the given argument, even if it is a scalar value.
```clojure
(seq* :a)
; => (:a)

(seq* [1 2 3])
; => (1 2 3)
```
### co.grubb.clj-utils.string

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

## License

Copyright © 2015 Michael Grubb. All Rights Reserved.

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
