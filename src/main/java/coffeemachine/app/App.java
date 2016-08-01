package coffeemachine.app;

import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.Money;
import coffeemachine.domain.events.DrinkOrderRequested;
import coffeemachine.domain.events.DrinkOrdered;
import coffeemachine.domain.events.DrinkRequester;
import coffeemachine.domain.events.UserMessageRequested;

public final class App {

    private final DrinkMaker drinkMaker;

    private App(DrinkMaker drinkMaker) {
        this.drinkMaker = drinkMaker;
    }

    public static App start(DrinkMaker drinkMaker) {
        App app = new App(drinkMaker);
        app.registerListeners();

        return app;
    }

    private void registerListeners() {
        MessageBus.listen(message -> handleDrinkOrderRequests(message));
        MessageBus.listen(message -> handleDrinkOrders(message));
    }

    private void handleDrinkOrderRequests(Object message) {
        if (message instanceof DrinkOrderRequested) {
            DrinkOrderRequested request = (DrinkOrderRequested) message;
            if (request.getCredit().lessThan(Money.euro(40))) {
                MessageBus.publish(new UserMessageRequested(null, "Not enough money"));
            } else {
                MessageBus.publish(new DrinkOrdered(null, request.getDrink(), request.getQuantity()));
            }
        }
    }

    private void handleDrinkOrders(Object message) {
        if (message instanceof DrinkRequester) {
            drinkMaker.command(((DrinkRequester) message).toCommand());
        }
    }
}
