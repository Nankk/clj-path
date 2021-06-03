(ns clj-path.core-test
  (:require [clojure.test :refer :all]
            [clj-path.core :refer :all]))

(def windows? (re-find #"(?i)windows" (System/getProperty "os.name")))

(when-not windows?

  (deftest absolute-test
    (is (absolute? "/usr/bin"))
    (is (not (absolute? "./usr/bin")))
    (is (not (absolute? "usr/bin"))))

  (deftest relative-test
    (is (= (relative "/usr/local/bin" "/usr/bin") "../../bin")))

  (deftest dirname-test
    (is (= (dirname "/home/user/rogue.save") "/home/user"))
    (is (= (dirname "/home/user/rogue") "/home/user")))

  (deftest extname-test
    (is (= (extname "/home/user/rogue.save") ".save"))
    (is (= (extname "/home/user/rogue.") "."))
    (is (= (extname "/home/user/rogue") "")))

  (deftest basename-test
    (is (= (basename "/home/user/doc.txt") "doc.txt"))
    (is (= (basename "/home/user/doc.txt" ".txt") "doc")))

  (deftest join-test
    (is (= (join "/etc" "X11" "xinit") "/etc/X11/xinit"))
    (is (= (join "/etc" "X11" "xinit" ".." "xkb") "/etc/X11/xinit/../xkb"))
    (is (= (join "/etc" "X11" "xinit" "../..") "/etc/X11/xinit/../..")))

  (deftest normalize-test
    (is (= (normalize "/etc/X11/xinit/../xkb") "/etc/X11/xkb"))
    (is (= (normalize "/etc/X11/xinit/../..") "/etc"))))

