package coffeemachine;

public final class Drink {

    private final String shortHand;

    private Drink(String shortHand) {
        this.shortHand = shortHand;
    }

    public static Drink tea() {
        return new Drink("T");
    }

    public String toShortHand() {
        return shortHand;
    }
}
