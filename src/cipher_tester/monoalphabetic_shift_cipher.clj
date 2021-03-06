; Copyright (c) 2014, 2015 Russell Andrew Edson
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

(ns ^{:doc "Functions for dealing with monoalphabetic shift ciphers."
      :author "Russell Andrew Edson"}
  cipher-tester.monoalphabetic-shift-cipher)

(def ^{:doc "The name of this cipher."}
  cipher-name "Monoalphabetic Shift Cipher")

(defn shift-by-n
  "Shifts the given lower-case character ch forward by n, 
  looping back around to 'a' if we reach the end."
  [n ch]
  (char (+ (int \a)
           (mod (+ n (- (int ch) (int \a))) 
                26))))

(defn make-shift-function
  "Takes in an integer n, and returns a function that will
  take an input string and shift each alphabetic character
  forward by n steps. Non-alphabetic characters are not
  modified, and the string is always returned in lower-case."
  [n]
  (fn [message]
    (apply str (map (fn [ch]
                      (if (Character/isLowerCase ch)
                        (shift-by-n n ch)
                        ch))                     
                    (clojure.string/lower-case message)))))

(defn make-encipher-function
  "Takes an integer n, and returns a function that will encipher
  a given message according to the n-step monoalphabetic 
  shift cipher."
  [n]
  (make-shift-function n))

(defn make-decipher-function
  "Takes in an integer n, and returns a function that will decipher
  a given message according to the n-step monoalphabetic
  shift cipher."
  [n]
  (make-shift-function (- n)))

(defn cipher
  "Takes in a cipher key (in this case, an integer n to shift by)
  and returns a map containing :encipher and :decipher functions  
  to work with that key, as well as a :key map containing the
  keys used to construct the cipher."
  [n]
  {:encipher (make-encipher-function n), 
   :decipher (make-decipher-function n),
   :key {:n n}})

(def ^{:doc "The default example of this cipher (the 3-shift Caesar cipher."}
  default-cipher (cipher 3))
