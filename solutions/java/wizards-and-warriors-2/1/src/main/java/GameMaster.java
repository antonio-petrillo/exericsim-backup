public class GameMaster {

    public String describe(Character c) {
        return String.format("You're a level %d %s with %d hit points.", c.getLevel(), c.getCharacterClass(), c.getHitPoints());
    }

    public String describe(Destination d) {
        return String.format("You've arrived at %s, which has %d inhabitants.", d.getName(), d.getInhabitants());
    }
    
    public String describe(TravelMethod tm) {
        return String.format("You're traveling to your destination %s.", tm == TravelMethod.WALKING ? "by walking" : "on horseback");
    }
    
    public String describe(Character c, Destination d, TravelMethod tm) {
        String[] descr = {describe(c), describe(tm), describe(d)};
        return String.join(" ", descr);
    }

    public String describe(Character c, Destination d) {
        return describe(c, d, TravelMethod.WALKING);
    }
}
