; Copyright (c) 2014 Russell Andrew Edson
;
; Permission is hereby granted, free of charge, to any person obtaining a copy
; of this software and associated documentation files (the "Software"), to deal
; in the Software without restriction, including without limitation the rights
; to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
; copies of the Software, and to permit persons to whom the Software is
; furnished to do so, subject to the following conditions:
;
; The above copyright notice and this permission notice shall be included in all
; copies or substantial portions of the Software.
;
; THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
; IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
; FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
; AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
; LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
; OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
; SOFTWARE.

(ns ^{:doc "The main GUI for the cipher tester."
      :author "Russell Andrew Edson"}
  cipher-tester.core
    ;; After test, remove JOptionPane from imports!
    (:import (javax.swing JFrame JLabel JTextField JButton JComboBox BoxLayout JOptionPane)
             (java.awt.event ActionListener)))

;; TODO: Put main GUI code in here.
;; GUI test code to make sure this works...
;; From Stuart Halloway's example found at:
;;   thinkrelevance.com/blog/2008/08/12/java-next-2-java-interop
(defn -main []
  (let [frame (JFrame. "Hello Swing")
        button (JButton. "Click Me")]
    (.addActionListener button
      (proxy [ActionListener] []
        (actionPerformed [event]
          (JOptionPane/showMessageDialog nil (str "Hello world!")))))
    (doto frame
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.add button)
      (.pack)
      ;; Need to determine a decent starting size for the app...
      (.setSize 400 300)
      (.setVisible true))))
