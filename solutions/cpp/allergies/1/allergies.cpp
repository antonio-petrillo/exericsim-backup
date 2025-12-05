#include "allergies.h"


namespace allergies {

    namespace {
        std::string from_allergen_num(unsigned int allergen) {
            std::string s{};
            switch (allergen) {
                case 1: s = "eggs"; break; 
                case 2: s = "peanuts"; break; 
                case 4: s = "shellfish"; break; 
                case 8: s = "strawberries"; break; 
                case 16: s = "tomatoes"; break; 
                case 32: s = "chocolate"; break; 
                case 64: s = "pollen"; break; 
                case 128: s = "cats"; break; 
            }
            return s;
        }
        
        unsigned int from_allergen_string(std::string allergen) {
            unsigned int i{};
            
            if (allergen == "eggs") i = 1;
            else if (allergen == "peanuts") i = 2;
            else if (allergen == "shellfish") i = 4;
            else if (allergen == "strawberries") i = 8;
            else if (allergen == "tomatoes") i = 16;
            else if (allergen == "chocolate") i = 32;
            else if (allergen == "pollen") i = 64;
            else if (allergen == "cats") i = 128;
            
            return i;
        }
    }
    
    allergy_test::allergy_test(unsigned int score) {
        _score = score;
    }
    
    bool allergy_test::is_allergic_to(const std::string allergen) {
        unsigned int index{ from_allergen_string(allergen) };
        return (_score & index) != 0;
    }
    
    std::unordered_set<std::string> allergy_test::get_allergies() {
        std::unordered_set<std::string> set;

        for (unsigned int i = 1; i <= 128; i <<= 1) {
          if ((_score & i) != 0) {
              set.insert(from_allergen_num(i));
          }
        }
        
        return set;
    }

}  // namespace allergies
