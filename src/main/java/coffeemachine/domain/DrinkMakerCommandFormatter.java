package coffeemachine.domain;

public final class DrinkMakerCommandFormatter {

    public String format(Drink drink, int quantity) {
        return String.format("%s:%d:0", drink.toShortHand(), quantity);
    }
}
