cipher-tester
=============

A GUI written in Clojure for having fun with ciphers.

![Development screenshot.](https://raw.githubusercontent.com/RussellAndrewEdson/cipher-tester/master/development_screenshot.png "Development screenshot for the GUI.")

I started this project as a way for me to gain some experience in development using Clojure
(+ Swing) and the implementation/use of various cipher schemes. 

At the moment (12/02/2015)
the only cipher supported is the simple Monoalphabetic Shift cipher, but the plan is to 
eventually include basic implementations for many other encryption algorithms as I get around to
learning them (Vigenere squares, Hill's cipher, RSA, DES, AES, Blowfish, etc.)

In terms of the program from the user's perspective, it will look very similar to how it looks
now (we'll see if we can't change the proportions for the buttons and make things a little 
prettier though.) The GUI is designed to be as fun and uncomplicated as possible -- selecting a 
cipher from the menu ought to provide a sensible default, with configuration optional 
(through a popup dialog.) Given a cipher, you can enter text on the left and encipher/decipher
it according to the cipher's key, with the output appearing on the right.
