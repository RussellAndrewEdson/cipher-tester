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

(ns ^{:doc "Tests for the monoalphabetic shift ciphers."
      :author "Russell Andrew Edson"}
  cipher-tester.monoalphabetic-shift-cipher-test
  (:require [clojure.test :refer :all]
            [cipher-tester.monoalphabetic-shift-cipher :refer :all]))

(def ^{:doc "The lowercase alphabet characters are modified by the cipher."}
  lowercase-alphabet "abcdefghijklmnopqrstuvwxyz")

(def ^{:doc "The uppercase alphabet characters are modified by the cipher,
       but are converted to lowercase characters first."}
  uppercase-alphabet "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

(def ^{:doc "All other characters are ignored by the cipher."} 
  other-characters "1234567890`~!@#$%^&*()-_+=[{]}\\|;:'\"<,.>/? \n\t")

(defn correct-encipher-with-n?
  "Returns true if the cipher, with the given value of n, correctly
  enciphers the message (which is checked by comparing it with the
  expected-message.)"
  [message expected-message n]
  (= expected-message ((:encipher (cipher n)) message)))

(defn correct-decipher-with-n?
  "Returns true if the cipher, with the given value of n, correctly
  deciphers the message (which is checked by comparing it with the
  expected-message.)"
  [message expected-message n]
  (= expected-message ((:decipher (cipher n)) message)))


;; Tests for all characters in the monoalphabetic-shift ciphers:
(deftest lowercase-message
  (def correct-encipher? (partial correct-encipher-with-n? lowercase-alphabet))  
  (testing "Correct enciphering of lowercase alphabet characters in [-26,26]"
    (is (correct-encipher? "abcdefghijklmnopqrstuvwxyz" -26))
    (is (correct-encipher? "bcdefghijklmnopqrstuvwxyza" -25))
    (is (correct-encipher? "cdefghijklmnopqrstuvwxyzab" -24))
    (is (correct-encipher? "defghijklmnopqrstuvwxyzabc" -23))
    (is (correct-encipher? "efghijklmnopqrstuvwxyzabcd" -22))
    (is (correct-encipher? "fghijklmnopqrstuvwxyzabcde" -21))
    (is (correct-encipher? "ghijklmnopqrstuvwxyzabcdef" -20))
    (is (correct-encipher? "hijklmnopqrstuvwxyzabcdefg" -19))
    (is (correct-encipher? "ijklmnopqrstuvwxyzabcdefgh" -18))
    (is (correct-encipher? "jklmnopqrstuvwxyzabcdefghi" -17))
    (is (correct-encipher? "klmnopqrstuvwxyzabcdefghij" -16))
    (is (correct-encipher? "lmnopqrstuvwxyzabcdefghijk" -15))
    (is (correct-encipher? "mnopqrstuvwxyzabcdefghijkl" -14))
    (is (correct-encipher? "nopqrstuvwxyzabcdefghijklm" -13))
    (is (correct-encipher? "opqrstuvwxyzabcdefghijklmn" -12))
    (is (correct-encipher? "pqrstuvwxyzabcdefghijklmno" -11))
    (is (correct-encipher? "qrstuvwxyzabcdefghijklmnop" -10))
    (is (correct-encipher? "rstuvwxyzabcdefghijklmnopq"  -9))
    (is (correct-encipher? "stuvwxyzabcdefghijklmnopqr"  -8))
    (is (correct-encipher? "tuvwxyzabcdefghijklmnopqrs"  -7))
    (is (correct-encipher? "uvwxyzabcdefghijklmnopqrst"  -6))
    (is (correct-encipher? "vwxyzabcdefghijklmnopqrstu"  -5))
    (is (correct-encipher? "wxyzabcdefghijklmnopqrstuv"  -4))
    (is (correct-encipher? "xyzabcdefghijklmnopqrstuvw"  -3))
    (is (correct-encipher? "yzabcdefghijklmnopqrstuvwx"  -2))
    (is (correct-encipher? "zabcdefghijklmnopqrstuvwxy"  -1))
    (is (correct-encipher? "abcdefghijklmnopqrstuvwxyz"   0))
    (is (correct-encipher? "bcdefghijklmnopqrstuvwxyza"   1))
    (is (correct-encipher? "cdefghijklmnopqrstuvwxyzab"   2))
    (is (correct-encipher? "defghijklmnopqrstuvwxyzabc"   3))
    (is (correct-encipher? "efghijklmnopqrstuvwxyzabcd"   4))
    (is (correct-encipher? "fghijklmnopqrstuvwxyzabcde"   5))
    (is (correct-encipher? "ghijklmnopqrstuvwxyzabcdef"   6))
    (is (correct-encipher? "hijklmnopqrstuvwxyzabcdefg"   7))
    (is (correct-encipher? "ijklmnopqrstuvwxyzabcdefgh"   8))
    (is (correct-encipher? "jklmnopqrstuvwxyzabcdefghi"   9))
    (is (correct-encipher? "klmnopqrstuvwxyzabcdefghij"  10))
    (is (correct-encipher? "lmnopqrstuvwxyzabcdefghijk"  11))
    (is (correct-encipher? "mnopqrstuvwxyzabcdefghijkl"  12))
    (is (correct-encipher? "nopqrstuvwxyzabcdefghijklm"  13))
    (is (correct-encipher? "opqrstuvwxyzabcdefghijklmn"  14))
    (is (correct-encipher? "pqrstuvwxyzabcdefghijklmno"  15))
    (is (correct-encipher? "qrstuvwxyzabcdefghijklmnop"  16))
    (is (correct-encipher? "rstuvwxyzabcdefghijklmnopq"  17))
    (is (correct-encipher? "stuvwxyzabcdefghijklmnopqr"  18))
    (is (correct-encipher? "tuvwxyzabcdefghijklmnopqrs"  19))
    (is (correct-encipher? "uvwxyzabcdefghijklmnopqrst"  20))
    (is (correct-encipher? "vwxyzabcdefghijklmnopqrstu"  21))
    (is (correct-encipher? "wxyzabcdefghijklmnopqrstuv"  22))
    (is (correct-encipher? "xyzabcdefghijklmnopqrstuvw"  23))
    (is (correct-encipher? "yzabcdefghijklmnopqrstuvwx"  24))
    (is (correct-encipher? "zabcdefghijklmnopqrstuvwxy"  25))
    (is (correct-encipher? "abcdefghijklmnopqrstuvwxyz"  26)))

  (def correct-decipher? (partial correct-decipher-with-n? lowercase-alphabet))  
  (testing "Correct deciphering of lowercase alphabet characters in [-26,26]"
    (is (correct-decipher? "abcdefghijklmnopqrstuvwxyz" -26))
    (is (correct-decipher? "zabcdefghijklmnopqrstuvwxy" -25))
    (is (correct-decipher? "yzabcdefghijklmnopqrstuvwx" -24))
    (is (correct-decipher? "xyzabcdefghijklmnopqrstuvw" -23))
    (is (correct-decipher? "wxyzabcdefghijklmnopqrstuv" -22))
    (is (correct-decipher? "vwxyzabcdefghijklmnopqrstu" -21))
    (is (correct-decipher? "uvwxyzabcdefghijklmnopqrst" -20))
    (is (correct-decipher? "tuvwxyzabcdefghijklmnopqrs" -19))
    (is (correct-decipher? "stuvwxyzabcdefghijklmnopqr" -18))
    (is (correct-decipher? "rstuvwxyzabcdefghijklmnopq" -17))
    (is (correct-decipher? "qrstuvwxyzabcdefghijklmnop" -16))
    (is (correct-decipher? "pqrstuvwxyzabcdefghijklmno" -15))
    (is (correct-decipher? "opqrstuvwxyzabcdefghijklmn" -14))
    (is (correct-decipher? "nopqrstuvwxyzabcdefghijklm" -13))
    (is (correct-decipher? "mnopqrstuvwxyzabcdefghijkl" -12))
    (is (correct-decipher? "lmnopqrstuvwxyzabcdefghijk" -11))
    (is (correct-decipher? "klmnopqrstuvwxyzabcdefghij" -10))
    (is (correct-decipher? "jklmnopqrstuvwxyzabcdefghi"  -9))
    (is (correct-decipher? "ijklmnopqrstuvwxyzabcdefgh"  -8))
    (is (correct-decipher? "hijklmnopqrstuvwxyzabcdefg"  -7))
    (is (correct-decipher? "ghijklmnopqrstuvwxyzabcdef"  -6))
    (is (correct-decipher? "fghijklmnopqrstuvwxyzabcde"  -5))
    (is (correct-decipher? "efghijklmnopqrstuvwxyzabcd"  -4))
    (is (correct-decipher? "defghijklmnopqrstuvwxyzabc"  -3))
    (is (correct-decipher? "cdefghijklmnopqrstuvwxyzab"  -2))
    (is (correct-decipher? "bcdefghijklmnopqrstuvwxyza"  -1))
    (is (correct-decipher? "abcdefghijklmnopqrstuvwxyz"   0))
    (is (correct-decipher? "zabcdefghijklmnopqrstuvwxy"   1))
    (is (correct-decipher? "yzabcdefghijklmnopqrstuvwx"   2))
    (is (correct-decipher? "xyzabcdefghijklmnopqrstuvw"   3))
    (is (correct-decipher? "wxyzabcdefghijklmnopqrstuv"   4))
    (is (correct-decipher? "vwxyzabcdefghijklmnopqrstu"   5))
    (is (correct-decipher? "uvwxyzabcdefghijklmnopqrst"   6))
    (is (correct-decipher? "tuvwxyzabcdefghijklmnopqrs"   7))
    (is (correct-decipher? "stuvwxyzabcdefghijklmnopqr"   8))
    (is (correct-decipher? "rstuvwxyzabcdefghijklmnopq"   9))
    (is (correct-decipher? "qrstuvwxyzabcdefghijklmnop"  10))
    (is (correct-decipher? "pqrstuvwxyzabcdefghijklmno"  11))
    (is (correct-decipher? "opqrstuvwxyzabcdefghijklmn"  12))
    (is (correct-decipher? "nopqrstuvwxyzabcdefghijklm"  13))
    (is (correct-decipher? "mnopqrstuvwxyzabcdefghijkl"  14))
    (is (correct-decipher? "lmnopqrstuvwxyzabcdefghijk"  15))
    (is (correct-decipher? "klmnopqrstuvwxyzabcdefghij"  16))
    (is (correct-decipher? "jklmnopqrstuvwxyzabcdefghi"  17))
    (is (correct-decipher? "ijklmnopqrstuvwxyzabcdefgh"  18))
    (is (correct-decipher? "hijklmnopqrstuvwxyzabcdefg"  19))
    (is (correct-decipher? "ghijklmnopqrstuvwxyzabcdef"  20))
    (is (correct-decipher? "fghijklmnopqrstuvwxyzabcde"  21))
    (is (correct-decipher? "efghijklmnopqrstuvwxyzabcd"  22))
    (is (correct-decipher? "defghijklmnopqrstuvwxyzabc"  23))
    (is (correct-decipher? "cdefghijklmnopqrstuvwxyzab"  24))
    (is (correct-decipher? "bcdefghijklmnopqrstuvwxyza"  25))
    (is (correct-decipher? "abcdefghijklmnopqrstuvwxyz"  26))))

(deftest uppercase-message
  (def correct-encipher? (partial correct-encipher-with-n? uppercase-alphabet))
  (testing "Correct enciphering of uppercase alphabet characters in [-26,26]"
    (is (correct-encipher? "abcdefghijklmnopqrstuvwxyz" -26))
    (is (correct-encipher? "bcdefghijklmnopqrstuvwxyza" -25))
    (is (correct-encipher? "cdefghijklmnopqrstuvwxyzab" -24))
    (is (correct-encipher? "defghijklmnopqrstuvwxyzabc" -23))
    (is (correct-encipher? "efghijklmnopqrstuvwxyzabcd" -22))
    (is (correct-encipher? "fghijklmnopqrstuvwxyzabcde" -21))
    (is (correct-encipher? "ghijklmnopqrstuvwxyzabcdef" -20))
    (is (correct-encipher? "hijklmnopqrstuvwxyzabcdefg" -19))
    (is (correct-encipher? "ijklmnopqrstuvwxyzabcdefgh" -18))
    (is (correct-encipher? "jklmnopqrstuvwxyzabcdefghi" -17))
    (is (correct-encipher? "klmnopqrstuvwxyzabcdefghij" -16))
    (is (correct-encipher? "lmnopqrstuvwxyzabcdefghijk" -15))
    (is (correct-encipher? "mnopqrstuvwxyzabcdefghijkl" -14))
    (is (correct-encipher? "nopqrstuvwxyzabcdefghijklm" -13))
    (is (correct-encipher? "opqrstuvwxyzabcdefghijklmn" -12))
    (is (correct-encipher? "pqrstuvwxyzabcdefghijklmno" -11))
    (is (correct-encipher? "qrstuvwxyzabcdefghijklmnop" -10))
    (is (correct-encipher? "rstuvwxyzabcdefghijklmnopq"  -9))
    (is (correct-encipher? "stuvwxyzabcdefghijklmnopqr"  -8))
    (is (correct-encipher? "tuvwxyzabcdefghijklmnopqrs"  -7))
    (is (correct-encipher? "uvwxyzabcdefghijklmnopqrst"  -6))
    (is (correct-encipher? "vwxyzabcdefghijklmnopqrstu"  -5))
    (is (correct-encipher? "wxyzabcdefghijklmnopqrstuv"  -4))
    (is (correct-encipher? "xyzabcdefghijklmnopqrstuvw"  -3))
    (is (correct-encipher? "yzabcdefghijklmnopqrstuvwx"  -2))
    (is (correct-encipher? "zabcdefghijklmnopqrstuvwxy"  -1))
    (is (correct-encipher? "abcdefghijklmnopqrstuvwxyz"   0))
    (is (correct-encipher? "bcdefghijklmnopqrstuvwxyza"   1))
    (is (correct-encipher? "cdefghijklmnopqrstuvwxyzab"   2))
    (is (correct-encipher? "defghijklmnopqrstuvwxyzabc"   3))
    (is (correct-encipher? "efghijklmnopqrstuvwxyzabcd"   4))
    (is (correct-encipher? "fghijklmnopqrstuvwxyzabcde"   5))
    (is (correct-encipher? "ghijklmnopqrstuvwxyzabcdef"   6))
    (is (correct-encipher? "hijklmnopqrstuvwxyzabcdefg"   7))
    (is (correct-encipher? "ijklmnopqrstuvwxyzabcdefgh"   8))
    (is (correct-encipher? "jklmnopqrstuvwxyzabcdefghi"   9))
    (is (correct-encipher? "klmnopqrstuvwxyzabcdefghij"  10))
    (is (correct-encipher? "lmnopqrstuvwxyzabcdefghijk"  11))
    (is (correct-encipher? "mnopqrstuvwxyzabcdefghijkl"  12))
    (is (correct-encipher? "nopqrstuvwxyzabcdefghijklm"  13))
    (is (correct-encipher? "opqrstuvwxyzabcdefghijklmn"  14))
    (is (correct-encipher? "pqrstuvwxyzabcdefghijklmno"  15))
    (is (correct-encipher? "qrstuvwxyzabcdefghijklmnop"  16))
    (is (correct-encipher? "rstuvwxyzabcdefghijklmnopq"  17))
    (is (correct-encipher? "stuvwxyzabcdefghijklmnopqr"  18))
    (is (correct-encipher? "tuvwxyzabcdefghijklmnopqrs"  19))
    (is (correct-encipher? "uvwxyzabcdefghijklmnopqrst"  20))
    (is (correct-encipher? "vwxyzabcdefghijklmnopqrstu"  21))
    (is (correct-encipher? "wxyzabcdefghijklmnopqrstuv"  22))
    (is (correct-encipher? "xyzabcdefghijklmnopqrstuvw"  23))
    (is (correct-encipher? "yzabcdefghijklmnopqrstuvwx"  24))
    (is (correct-encipher? "zabcdefghijklmnopqrstuvwxy"  25))
    (is (correct-encipher? "abcdefghijklmnopqrstuvwxyz"  26)))

  (def correct-decipher? (partial correct-decipher-with-n? uppercase-alphabet))
  (testing "Correct deciphering of uppercase alphabet characters in [-26,26]"
    (is (correct-decipher? "abcdefghijklmnopqrstuvwxyz" -26))
    (is (correct-decipher? "zabcdefghijklmnopqrstuvwxy" -25))
    (is (correct-decipher? "yzabcdefghijklmnopqrstuvwx" -24))
    (is (correct-decipher? "xyzabcdefghijklmnopqrstuvw" -23))
    (is (correct-decipher? "wxyzabcdefghijklmnopqrstuv" -22))
    (is (correct-decipher? "vwxyzabcdefghijklmnopqrstu" -21))
    (is (correct-decipher? "uvwxyzabcdefghijklmnopqrst" -20))
    (is (correct-decipher? "tuvwxyzabcdefghijklmnopqrs" -19))
    (is (correct-decipher? "stuvwxyzabcdefghijklmnopqr" -18))
    (is (correct-decipher? "rstuvwxyzabcdefghijklmnopq" -17))
    (is (correct-decipher? "qrstuvwxyzabcdefghijklmnop" -16))
    (is (correct-decipher? "pqrstuvwxyzabcdefghijklmno" -15))
    (is (correct-decipher? "opqrstuvwxyzabcdefghijklmn" -14))
    (is (correct-decipher? "nopqrstuvwxyzabcdefghijklm" -13))
    (is (correct-decipher? "mnopqrstuvwxyzabcdefghijkl" -12))
    (is (correct-decipher? "lmnopqrstuvwxyzabcdefghijk" -11))
    (is (correct-decipher? "klmnopqrstuvwxyzabcdefghij" -10))
    (is (correct-decipher? "jklmnopqrstuvwxyzabcdefghi"  -9))
    (is (correct-decipher? "ijklmnopqrstuvwxyzabcdefgh"  -8))
    (is (correct-decipher? "hijklmnopqrstuvwxyzabcdefg"  -7))
    (is (correct-decipher? "ghijklmnopqrstuvwxyzabcdef"  -6))
    (is (correct-decipher? "fghijklmnopqrstuvwxyzabcde"  -5))
    (is (correct-decipher? "efghijklmnopqrstuvwxyzabcd"  -4))
    (is (correct-decipher? "defghijklmnopqrstuvwxyzabc"  -3))
    (is (correct-decipher? "cdefghijklmnopqrstuvwxyzab"  -2))
    (is (correct-decipher? "bcdefghijklmnopqrstuvwxyza"  -1))
    (is (correct-decipher? "abcdefghijklmnopqrstuvwxyz"   0))
    (is (correct-decipher? "zabcdefghijklmnopqrstuvwxy"   1))
    (is (correct-decipher? "yzabcdefghijklmnopqrstuvwx"   2))
    (is (correct-decipher? "xyzabcdefghijklmnopqrstuvw"   3))
    (is (correct-decipher? "wxyzabcdefghijklmnopqrstuv"   4))
    (is (correct-decipher? "vwxyzabcdefghijklmnopqrstu"   5))
    (is (correct-decipher? "uvwxyzabcdefghijklmnopqrst"   6))
    (is (correct-decipher? "tuvwxyzabcdefghijklmnopqrs"   7))
    (is (correct-decipher? "stuvwxyzabcdefghijklmnopqr"   8))
    (is (correct-decipher? "rstuvwxyzabcdefghijklmnopq"   9))
    (is (correct-decipher? "qrstuvwxyzabcdefghijklmnop"  10))
    (is (correct-decipher? "pqrstuvwxyzabcdefghijklmno"  11))
    (is (correct-decipher? "opqrstuvwxyzabcdefghijklmn"  12))
    (is (correct-decipher? "nopqrstuvwxyzabcdefghijklm"  13))
    (is (correct-decipher? "mnopqrstuvwxyzabcdefghijkl"  14))
    (is (correct-decipher? "lmnopqrstuvwxyzabcdefghijk"  15))
    (is (correct-decipher? "klmnopqrstuvwxyzabcdefghij"  16))
    (is (correct-decipher? "jklmnopqrstuvwxyzabcdefghi"  17))
    (is (correct-decipher? "ijklmnopqrstuvwxyzabcdefgh"  18))
    (is (correct-decipher? "hijklmnopqrstuvwxyzabcdefg"  19))
    (is (correct-decipher? "ghijklmnopqrstuvwxyzabcdef"  20))
    (is (correct-decipher? "fghijklmnopqrstuvwxyzabcde"  21))
    (is (correct-decipher? "efghijklmnopqrstuvwxyzabcd"  22))
    (is (correct-decipher? "defghijklmnopqrstuvwxyzabc"  23))
    (is (correct-decipher? "cdefghijklmnopqrstuvwxyzab"  24))
    (is (correct-decipher? "bcdefghijklmnopqrstuvwxyza"  25))
    (is (correct-decipher? "abcdefghijklmnopqrstuvwxyz"  26))))

(deftest other-character-message
  (def correct-encipher? (partial correct-encipher-with-n? other-characters))
  (testing "Enciphering ignores all other characters in [-26,26]"  
    (is (correct-encipher? other-characters -26))
    (is (correct-encipher? other-characters -25))
    (is (correct-encipher? other-characters -24))
    (is (correct-encipher? other-characters -23))
    (is (correct-encipher? other-characters -22))
    (is (correct-encipher? other-characters -21))
    (is (correct-encipher? other-characters -20))
    (is (correct-encipher? other-characters -19))
    (is (correct-encipher? other-characters -18))
    (is (correct-encipher? other-characters -17))
    (is (correct-encipher? other-characters -16))
    (is (correct-encipher? other-characters -15))
    (is (correct-encipher? other-characters -14))
    (is (correct-encipher? other-characters -13))
    (is (correct-encipher? other-characters -12))
    (is (correct-encipher? other-characters -11))
    (is (correct-encipher? other-characters -10))
    (is (correct-encipher? other-characters  -9))
    (is (correct-encipher? other-characters  -8))
    (is (correct-encipher? other-characters  -7))
    (is (correct-encipher? other-characters  -6))
    (is (correct-encipher? other-characters  -5))
    (is (correct-encipher? other-characters  -4))
    (is (correct-encipher? other-characters  -3))
    (is (correct-encipher? other-characters  -2)) 
    (is (correct-encipher? other-characters  -1)) 
    (is (correct-encipher? other-characters   0)) 
    (is (correct-encipher? other-characters   1)) 
    (is (correct-encipher? other-characters   2)) 
    (is (correct-encipher? other-characters   3)) 
    (is (correct-encipher? other-characters   4)) 
    (is (correct-encipher? other-characters   5)) 
    (is (correct-encipher? other-characters   6)) 
    (is (correct-encipher? other-characters   7)) 
    (is (correct-encipher? other-characters   8)) 
    (is (correct-encipher? other-characters   9)) 
    (is (correct-encipher? other-characters  10)) 
    (is (correct-encipher? other-characters  11)) 
    (is (correct-encipher? other-characters  12)) 
    (is (correct-encipher? other-characters  13)) 
    (is (correct-encipher? other-characters  14)) 
    (is (correct-encipher? other-characters  15)) 
    (is (correct-encipher? other-characters  16)) 
    (is (correct-encipher? other-characters  17)) 
    (is (correct-encipher? other-characters  18)) 
    (is (correct-encipher? other-characters  19)) 
    (is (correct-encipher? other-characters  20)) 
    (is (correct-encipher? other-characters  21)) 
    (is (correct-encipher? other-characters  22)) 
    (is (correct-encipher? other-characters  23)) 
    (is (correct-encipher? other-characters  24)) 
    (is (correct-encipher? other-characters  25)) 
    (is (correct-encipher? other-characters  26))) 

  (def correct-decipher? (partial correct-decipher-with-n? other-characters))
  (testing "Deciphering ignores all other characters in [-26,26]"  
    (is (correct-decipher? other-characters -26))
    (is (correct-decipher? other-characters -25))
    (is (correct-decipher? other-characters -24))
    (is (correct-decipher? other-characters -23))
    (is (correct-decipher? other-characters -22))
    (is (correct-decipher? other-characters -21))
    (is (correct-decipher? other-characters -20))
    (is (correct-decipher? other-characters -19))
    (is (correct-decipher? other-characters -18))
    (is (correct-decipher? other-characters -17))
    (is (correct-decipher? other-characters -16))
    (is (correct-decipher? other-characters -15))
    (is (correct-decipher? other-characters -14))
    (is (correct-decipher? other-characters -13))
    (is (correct-decipher? other-characters -12))
    (is (correct-decipher? other-characters -11))
    (is (correct-decipher? other-characters -10))
    (is (correct-decipher? other-characters  -9))
    (is (correct-decipher? other-characters  -8))
    (is (correct-decipher? other-characters  -7))
    (is (correct-decipher? other-characters  -6))
    (is (correct-decipher? other-characters  -5))
    (is (correct-decipher? other-characters  -4))
    (is (correct-decipher? other-characters  -3))
    (is (correct-decipher? other-characters  -2)) 
    (is (correct-decipher? other-characters  -1)) 
    (is (correct-decipher? other-characters   0)) 
    (is (correct-decipher? other-characters   1)) 
    (is (correct-decipher? other-characters   2)) 
    (is (correct-decipher? other-characters   3)) 
    (is (correct-decipher? other-characters   4)) 
    (is (correct-decipher? other-characters   5)) 
    (is (correct-decipher? other-characters   6)) 
    (is (correct-decipher? other-characters   7)) 
    (is (correct-decipher? other-characters   8)) 
    (is (correct-decipher? other-characters   9)) 
    (is (correct-decipher? other-characters  10)) 
    (is (correct-decipher? other-characters  11)) 
    (is (correct-decipher? other-characters  12)) 
    (is (correct-decipher? other-characters  13)) 
    (is (correct-decipher? other-characters  14)) 
    (is (correct-decipher? other-characters  15)) 
    (is (correct-decipher? other-characters  16)) 
    (is (correct-decipher? other-characters  17)) 
    (is (correct-decipher? other-characters  18)) 
    (is (correct-decipher? other-characters  19)) 
    (is (correct-decipher? other-characters  20)) 
    (is (correct-decipher? other-characters  21)) 
    (is (correct-decipher? other-characters  22)) 
    (is (correct-decipher? other-characters  23)) 
    (is (correct-decipher? other-characters  24)) 
    (is (correct-decipher? other-characters  25))
    (is (correct-decipher? other-characters  26))))


;; Test of invariants: 

(defn make-message-length
  "Generates a random message length between 1 and 50 characters for 
  testing purposes."
  []
  (inc (rand-int 50)))

(defn make-random-message
  "Generates a random message of the given length."
  [n]
  (let [all-possible-characters (concat lowercase-alphabet
                                        uppercase-alphabet
                                        other-characters)]
    (apply str (for [i (range n)] 
                 (nth all-possible-characters
                      (rand-int (count all-possible-characters)))))))
    

;; Test of invariant -- the message length doesn't ever change:

(defn correct-length-with-encipher?
  "Take in a message length and a cipher shift key, and returns true if
  the length of the message is unchanged by the enciphering."
  [message-length shift]
  (= message-length
     (count ((:encipher (cipher shift))
               (make-random-message message-length)))))

(defn correct-length-with-decipher?
  "Take in a message length and a cipher shift key, and returns true if
  the length of the message is unchanged by the deciphering."
  [message-length shift]
  (= message-length
     (count ((:decipher (cipher shift))
               (make-random-message message-length)))))

(deftest invariant-message-length
  (testing "Message length doesn't change when encipher applied"
    ;TODO: There absolutely has to be a way to do this with a macro.
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-encipher? (make-message-length) (rand-int 26))))

  (testing "Message length doesn't change when decipher applied"
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))
    (is (correct-length-with-decipher? (make-message-length) (rand-int 26)))))


;; Test of invariant -- encipher, decipher are inverses of each other:
;;TODO

;; Test of invariant -- encipher/decipher are commutative with permutation:
;;TODO

