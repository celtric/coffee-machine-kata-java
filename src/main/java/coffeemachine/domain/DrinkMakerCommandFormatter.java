package coffeemachine.domain;

public final class DrinkMakerCommandFormatter {

    public String format(Drink drink, int quantity) {
        return String.format("%s:%s:%s", drink.toShortHand(), quantity > 0 ? quantity : "", quantity > 0 ? 0 : "");
    }
}
