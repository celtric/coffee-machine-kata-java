package coffeemachine.app;

import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.events.DrinkRequester;

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
        MessageBus.listen(message -> {
            if (message instanceof DrinkRequester) {
                drinkMaker.command(((DrinkRequester) message).toCommand());
            }
        });
    }
}
