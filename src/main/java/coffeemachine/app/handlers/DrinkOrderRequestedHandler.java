package coffeemachine.app.handlers;

import coffeemachine.app.MessageBus;
import coffeemachine.domain.events.DrinkOrderRequested;
import coffeemachine.domain.events.DrinkOrdered;
import coffeemachine.domain.events.UserMessageRequested;

public final class DrinkOrderRequestedHandler implements MessageHandler<DrinkOrderRequested> {

    private final MessageBus bus;

    public DrinkOrderRequestedHandler(MessageBus bus) {
        this.bus = bus;
    }

    @Override
    public void handle(DrinkOrderRequested message) {
        if (message.getDrink().costsLessOrEqualThan(message.getCredit())) {
            bus.publish(new DrinkOrdered(null, message.getDrink(), message.getSugarQuantity()));
        } else {
            bus.publish(new UserMessageRequested(null, "Not enough money"));
        }
    }
}
