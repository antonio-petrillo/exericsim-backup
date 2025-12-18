#include "rotational_cipher.h"

namespace rotational_cipher {

std::string rotate(std::string plain, int key) {
    std::string encrypted {};

    for (char ch : plain) {
     char ech {};
     if(ch >= 'a' && ch <= 'z')
         ech = (ch - 'a' + key) % 26 + 'a';
     else if (ch >= 'A' && ch <= 'Z')
         ech = (ch - 'A' + key) % 26 + 'A';
     else 
         ech = ch;

        encrypted.push_back(ech);
    }
    
    return encrypted;
}

}  // namespace rotational_cipher
