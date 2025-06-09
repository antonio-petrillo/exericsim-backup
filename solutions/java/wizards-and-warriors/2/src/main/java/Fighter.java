class Fighter {

    boolean isVulnerable() {
        return false;
    }

    int getDamagePoints(Fighter fighter){
        return 1;
    }

}

class Warrior extends Fighter {

    @Override
    public String toString() {
        return "Fighter is a Warrior";
    }

    @Override
    // int damagePoints(Fighter wizard) <- Why put wizard here?
    int getDamagePoints(Fighter fighter) {
        return fighter.isVulnerable() ? 10 : 6;
    }

    @Override
    boolean isVulnerable() {
        return false;
    }
}

class Wizard extends Fighter {

    private boolean spellPrepared = false;

    @Override
    public String toString() {
        return "Fighter is a Wizard";
    }

    @Override
    boolean isVulnerable() {
        return !spellPrepared;
    }

    @Override
    // int damagePoints(Fighter warrior) <- same thing here
    int getDamagePoints(Fighter fighter) {
        return spellPrepared ? 12 : 3;
    }

    void prepareSpell() {
        spellPrepared = true;
    }

}
