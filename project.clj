(defproject org.clojars.nankk/clj-path "0.1.0"
  :description "Node-like path functions for clojure"
  :url "https://github.com/Nankk/clj-path"
  :license {:name "EPL-1.0"
            :comment "Eclipse Public License 1.0"
            :url "https://choosealicense.com/licenses/epl-1.0"
            :year 2021
            :key "epl-1.0"}
  :repositories {"clojars" {:url "https://clojars.org/repo"
                            :sign-releases false}}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :repl-options {:init-ns clj-path.core})
