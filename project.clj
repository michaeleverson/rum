(defproject rum "0.10.8"
  :description  "ClojureScript wrapper for React"
  :license      { :name "Eclipse"
                  :url  "http://www.eclipse.org/legal/epl-v10.html" }
  :url          "https://github.com/tonsky/rum"

  :dependencies
  [[org.clojure/clojure        "1.7.0"   :scope "provided"]
   [org.clojure/clojurescript  "1.7.228" :scope "provided"]
   [cljsjs/react               "15.6.2-0"]
   [cljsjs/react-dom           "15.6.2-0"]
   [sablono                    "0.7.7"]]
  
  :plugins [ [lein-cljsbuild "1.1.7"] ]

  :profiles {
    :dev  { :source-paths ["examples"]
            :dependencies [[cljsjs/react-dom-server "15.6.2-0"]
                           [cljsjs/prop-types "15.5.10-1"]
                           [clj-diffmatchpatch "0.0.9.3" :exclusions [org.clojure/clojure]]] }
    :perf { :source-paths ["perf"]
            :dependencies 
            [[enlive    "1.1.6"]
             [criterium "0.4.4"]
             [hiccup    "1.0.5"]] } }
  
  :aliases {"package" ["do" ["clean"] ["test"] ["clean"] ["cljsbuild" "once" "advanced"] ["run" "-m" "rum.examples-page"]]
            "perf"    ["with-profile" "perf" "run" "-m" "rum.perf"]}
  
  
  :cljsbuild
  { :builds
    [{ :id "advanced"
       :source-paths ["src" "examples" "test"]
       :compiler
       { :main           rum.examples
         :output-to      "target/main.js"
         :optimizations  :advanced
         :source-map     "target/main.js.map"
         :pretty-print   false
         :compiler-stats true
         :parallel-build true }}
     
     { :id "none"
       :source-paths ["src" "examples" "test"]
       :compiler
       { :main           rum.examples
         :output-to      "target/main.js"
         :output-dir     "target/none"
         :asset-path     "target/none"
         :optimizations  :none
         :source-map     true
         :compiler-stats true
         :parallel-build true }}
     
     { :id "test"
       :source-paths ["src" "test"]
       :compiler
       { :main           rum.test.server-render
         :output-to      "target/test.js"
         :output-dir     "target/test"
         :asset-path     "target/test"
         :optimizations  :advanced
         :pretty-print   true
         :pseudo-names   true
         :parallel-build true }}]}
)
