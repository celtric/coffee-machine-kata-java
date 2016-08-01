package coffeemachine.domain.events;

import coffeemachine.domain.Drink;
import lombok.Value;

@Value
public final class DrinkOrdered implements DrinkRequester {

    private final String id;
    private final Drink drink;
    private final int quantity;

    public DrinkOrdered(String id, Drink drink, int quantity) {
        this.id = id;
        this.drink = drink;
        this.quantity = quantity;
    }

    @Override
    public String toCommand() {
        return String.format("%s:%d:0", drink.toShortHand(), quantity);
    }
}