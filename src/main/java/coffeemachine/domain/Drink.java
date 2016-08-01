package coffeemachine.domain;

public final class Drink {

    private final String shortHand;
    private final Money price;

    private Drink(String shortHand, Money price) {
        this.shortHand = shortHand;
        this.price = price;
    }

    public static Drink tea() {
        return new Drink("T", Money.euro(40));
    }

    public static Drink chocolate() {
        return new Drink("H", Money.euro(50));
    }

    public static Drink coffee() {
        return new Drink("C", Money.euro(60));
    }

    String toShortHand() {
        return shortHand;
    }

    public boolean costsLessOrEqualThan(Money money) {
        return price.lessThanOrEqual(money);
    }
}
