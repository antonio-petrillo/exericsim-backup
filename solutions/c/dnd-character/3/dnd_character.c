#include "dnd_character.h"

#include <limits.h>
#include <stdlib.h>
#include <math.h>

int ability(void) {
    return rand() % 15 + 3;
}

int modifier(int score){
    return floor((score - 10) / 2.);
}

dnd_character_t make_dnd_character(void) {
    dnd_character_t character;
    character.strength     = ability(),
    character.strength     = ability(),
    character.dexterity    = ability(),
    character.constitution = ability(),
    character.intelligence = ability(),
    character.wisdom       = ability(),
    character.charisma     = ability(),
    character.hitpoints    = 10 + modifier(character.constitution);
    return character;
}