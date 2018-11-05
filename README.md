# Java and C AES Encryption and Decryption

This is sample code developed to demonstrate equality of encryption and decryption using 
Java JCE's AES crypto algorithm and openssl's AES implementation.

# To build

For Java

gradle build


For C

g++ -o encrypt-decrypt-openssl encrypt-decrypt-openssl.c -L/usr/lib64 -lssl -lcrypto

# To run

For Java

Run the CryptoTest -> testCryptoName test which encrypts the given String 'The quick brown fox jumps over the lazy dog' to Hex as
E06F63A711E8B7AA9F9440107D4680A117994380EA31D2A299B95302D439B9702C8E65A99236EC920704915CF1A98A44

decrypts to original string.

For C,

Run 
 
 ./encrypt-decrypt-openssl

Which prints the given string in hex and decrypts the original string.
E06F63A711E8B7AA9F9440107D4680A117994380EA31D2A299B95302D439B9702C8E65A99236EC920704915CF1A98A44
Decrypted text is:
The quick brown fox jumps over the lazy dog
