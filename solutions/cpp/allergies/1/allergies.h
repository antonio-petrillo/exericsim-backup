#pragma once

#include <string>
#include <unordered_set>

namespace allergies {

class allergy_test {
    public:
      allergy_test(unsigned int score);
    
      bool is_allergic_to(const std::string allergen);
      
      std::unordered_set<std::string> get_allergies();

    private:
        unsigned int _score;
};

}  // namespace allergies
