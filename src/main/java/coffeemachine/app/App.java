package coffeemachine.app;

import coffeemachine.app.handlers.DrinkOrderRequestedHandler;
import coffeemachine.app.handlers.DrinkOrderedHandler;
import coffeemachine.app.handlers.UserMessageRequestedHandler;
import coffeemachine.domain.DrinkMaker;

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
        MessageBus.listen(new BusMessageHandler<>(new DrinkOrderRequestedHandler(drinkMaker)));
        MessageBus.listen(new BusMessageHandler<>(new DrinkOrderedHandler(drinkMaker)));
        MessageBus.listen(new BusMessageHandler<>(new UserMessageRequestedHandler(drinkMaker)));
    }
}
