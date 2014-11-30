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

(ns ^{:doc "Functions for creating monoalphabetic shift ciphers."
      :author "Russell Andrew Edson"}
  cipher-tester.monoalphabetic-shift-cipher)

(defn mod26
  "Returns n modulo 26."
  [n]
  (mod n 26))

(defn alphabet-character?
  "Returns true if the given character is a lower-case
  alphabet character."
  [ch]
  (and (>= (int ch) (int \a))
       (<= (int ch) (int \z))))

(defn shift-by-n
  "Shifts the given lower-case character ch forward by n, 
  looping back around to 'a' if we reach the end."
  [n ch]
  (char (+ (int \a)
           (mod26 (+ n (- (int ch) (int \a)))))))

(defn shift-function-generator
  "Takes in an integer n, and returns a function that will
  take an input string and shift each alphabetic character
  forward by n steps. Non-alphabetic characters are not
  modified, and the string is always returned in lower-case."
  [n]
  (fn [message]
    (apply str (map (fn [ch]
                      (if (alphabet-character? ch)
                        (shift-by-n n ch)
                        ch))                     
                    (clojure.string/lower-case message)))))

(defn encipher-generator
  "Takes an integer n, and returns a function that will encipher
  a given message according to the n-step monoalphabetic 
  shift cipher."
  [n]
  (shift-function-generator n))

(defn decipher-generator
  "Takes in an integer n, and returns a function that will decipher
  a given message according to the n-step monoalphabetic
  shift cipher."
  [n]
  (shift-function-generator (- n)))

;; TODO: Test this change. 28/11
; (since all cipher functions will look the same regardless of the 
; actual cipher in use, perhaps look into using a macro here?)
(defn cipher
  "Takes in a cipher key (in this case, an integer n to shift by)
  and returns a map containing :encipher and :decipher functions  
  to work with that key."
  [n]
  {:encipher (encipher-generator n), :decipher (decipher-generator n)})
