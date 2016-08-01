package coffeemachine.app.handlers;

import coffeemachine.app.MessageBus;
import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.Money;
import coffeemachine.domain.events.DrinkOrderRequested;
import coffeemachine.domain.events.DrinkOrdered;
import coffeemachine.domain.events.UserMessageRequested;

public final class DrinkOrderRequestedHandler implements MessageHandler<DrinkOrderRequested> {

    private final DrinkMaker drinkMaker;

    public DrinkOrderRequestedHandler(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    @Override
    public void handle(DrinkOrderRequested message) {
        if (message.getCredit().lessThan(Money.euro(40))) {
            MessageBus.publish(new UserMessageRequested(null, "Not enough money"));
        } else {
            MessageBus.publish(new DrinkOrdered(null, message.getDrink(), message.getQuantity()));
        }
    }
}
