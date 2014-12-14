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
    (:import (javax.swing JFrame JPanel JLabel JTextArea JButton JComboBox)
             (javax.swing BoxLayout JScrollPane)
             (java.awt GridLayout BorderLayout)
             (java.awt.event ActionListener))
    (:require [cipher-tester.monoalphabetic-shift-cipher]))

(def ^{:doc "The application title, along with the version number."}
  application-title "cipher-tester v0.1")

(def ^{:doc "A list of all of the ciphers currently supported by the program."}
  cipher-choices [cipher-tester.monoalphabetic-shift-cipher/cipher-name])

(def ^{:doc "The cipher to be used by the program. Default: Caesar cipher."}
  current-cipher (ref (cipher-tester.monoalphabetic-shift-cipher/cipher 3)))

(def ^{:doc "Welcome text for the user."}
  welcome-text 
"Try me! Click 'Encipher' to see this text
transformed into an encrypted message using
the famous Caesar cipher.")

(defn -main []
  (let [main-frame (JFrame. application-title)
        main-panel (JPanel. (BorderLayout.))
        io-panel (JPanel. (GridLayout. 1 2))
        input-panel (JPanel.)
        input-area (JTextArea. welcome-text)
        output-panel (JPanel.)
        output-area (JTextArea.)
        control-panel (JPanel. (GridLayout. 1 2))
        cipher-config-panel (JPanel. (GridLayout. 2 1))
        cipher-select-panel (JPanel.)
        cipher-config-button (JButton. "Configure cipher...")
        cipher-select-box (JComboBox. (into-array cipher-choices))
        buttons-panel (JPanel. (GridLayout. 1 2))
        encipher-button (JButton. "Encipher")
        decipher-button (JButton. "Decipher")]

    (doto main-panel
      (.add io-panel BorderLayout/CENTER)
      (.add control-panel BorderLayout/SOUTH))

    (doto io-panel
      (.add input-panel)
      (.add output-panel))

    (doto input-panel
      (.setLayout (BoxLayout. input-panel BoxLayout/Y_AXIS))
      (.add (JLabel. "Input: "))
      (.add (JScrollPane. input-area)))

    (doto output-panel
      (.setLayout (BoxLayout. output-panel BoxLayout/Y_AXIS))
      (.add (JLabel. "Output: "))
      (.add (JScrollPane. output-area)))

    (doto control-panel
      (.add cipher-config-panel)
      (.add buttons-panel))

    (doto cipher-config-panel
      (.add cipher-select-panel)
      (.add cipher-config-button))

    (doto cipher-select-panel
      (.add (JLabel. "Select cipher: "))
      (.add cipher-select-box))

    (doto buttons-panel
      (.add encipher-button)
      (.add decipher-button))

    (doto encipher-button
      (.addActionListener 
       (proxy [ActionListener] []
         (actionPerformed [event]
           (let [input (.getText input-area)
                 enciphered-text ((:encipher @current-cipher) input)]
             (.setText output-area enciphered-text))))))

    (doto decipher-button
      (.addActionListener
       (proxy [ActionListener] []
         (actionPerformed [event]
           (let [input (.getText input-area)
                 enciphered-text ((:decipher @current-cipher) input)]
             (.setText output-area enciphered-text))))))
                                                    
    (doto main-frame
      (.setDefaultCloseOperation JFrame/EXIT_ON_CLOSE)
      (.setContentPane main-panel)
      (.setSize 700 400)
      (.setVisible true))))
        
