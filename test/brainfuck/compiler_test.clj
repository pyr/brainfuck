(ns brainfuck.compiler-test
  (:require [clojure.test       :refer :all]
            [brainfuck.compiler :refer :all]))

(deftest bf-test
  (testing "hello world"
    (is (= "Hello World!\n"
           (with-out-str
             (run (str  "++++++++"
                        "[>++++[>++>+++>+++>+<<<<-]"
                        ">+>+>->>+[<]<-]>>.>---.+++++++.."
                        "+++.>>.<-.<.+++.------.--------.>>+.>++."))))))


  (testing "cat"
    (is (= "foobar"
           (with-in-str "foobar"
             (with-out-str
               (run "-,+[-.,+]"))))))

  (testing "rot13"
    (is (= "sbbone"
           (with-in-str "foobar"
             (with-out-str
               (run (str "-,+["
                         "-["
                         ">>++++[>++++++++<-]"
                         "<+<-[>+>+>-[>>>]<[[>+<-]>>+>]<<<<<-]"
                         "]>>>[-]+>--[-[<->+++[-]]]<[++++++++++++<"
                         "[>-[>+>>]>[+[<+>-]>+>>]<<<<<-]>>[<+>-]>"
                         "[-[-<<[-]>>]<<[<<->>-]>>]<<[<<+>>-]]<"
                         "[-]<.[-]<-,+]"))))))))
