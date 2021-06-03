(ns clj-path.core
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.nio.file Path Paths)))

(defn- ->path [s]
  (.toPath (io/as-file s)))

(defn absolute? [path]
  (.isAbsolute (->path path)))

(defn relative [from to]
  (when-not (and (absolute? from) (absolute? to))
    (throw (Exception. (str "Path is not absolute. [from to] " [from to]))))
  (.toString (.relativize (->path from) (->path to))))

(defn dirname [path]
  (.getParent (io/as-file path)))

(defn extname [path]
  (or (re-find #"\.[^\.]*$" path) ""))

(defn basename [path & [ext]]
  (let [basename (.getName (io/as-file path))]
    (if ext
      (str/replace basename (re-pattern ext) "")
      basename)))

(defn join [& paths]
  (loop [resolved  (->path "")
         remaining paths]
    (if (empty? remaining)
      (.toString resolved)
      (recur (.resolve resolved (->path (first remaining))) (rest remaining)))))

(defn normalize [path]
  (.toString (.normalize (->path path))))

