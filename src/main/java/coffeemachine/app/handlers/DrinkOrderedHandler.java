package coffeemachine.app.handlers;

import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.events.DrinkOrdered;

public final class DrinkOrderedHandler implements MessageHandler<DrinkOrdered> {

    private final DrinkMaker drinkMaker;

    public DrinkOrderedHandler(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    @Override
    public void handle(DrinkOrdered message) {
        drinkMaker.command(message.toCommand());
    }
}
