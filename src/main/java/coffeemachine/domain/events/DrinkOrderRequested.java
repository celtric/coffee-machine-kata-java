package coffeemachine.domain.events;

import coffeemachine.domain.Drink;
import coffeemachine.domain.Money;
import lombok.Value;

@Value
public final class DrinkOrderRequested {

    private final String id;
    private final Drink drink;
    private final int sugarQuantity;
    private final Money credit;

    public DrinkOrderRequested(String id, Drink drink, int sugarQuantity, Money credit) {
        this.id = id;
        this.drink = drink;
        this.sugarQuantity = sugarQuantity;
        this.credit = credit;
    }
}
