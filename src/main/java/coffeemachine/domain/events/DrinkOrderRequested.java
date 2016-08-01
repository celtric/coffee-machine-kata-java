package coffeemachine.domain.events;

import coffeemachine.domain.Drink;
import coffeemachine.domain.Money;
import lombok.Value;

@Value
public final class DrinkOrderRequested {

    private final Drink drink;
    private final int quantity;
    private final Money credit;

    public DrinkOrderRequested(Drink drink, int quantity, Money credit) {
        this.drink = drink;
        this.quantity = quantity;
        this.credit = credit;
    }
}
