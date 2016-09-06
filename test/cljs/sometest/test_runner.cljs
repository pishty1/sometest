(ns sometest.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [sometest.core-test]
   [sometest.common-test]))

(enable-console-print!)

(doo-tests 'sometest.core-test
           'sometest.common-test)
