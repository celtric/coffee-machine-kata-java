package coffeemachine.domain;

public final class Drink {

    private final String shortHand;

    private Drink(String shortHand) {
        this.shortHand = shortHand;
    }

    public static Drink tea() {
        return new Drink("T");
    }

    public static Drink chocolate() {
        return new Drink("H");
    }

    public static Drink coffee() {
        return new Drink("C");
    }

    String toShortHand() {
        return shortHand;
    }
}
