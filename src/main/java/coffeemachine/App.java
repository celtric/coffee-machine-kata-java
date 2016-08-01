package coffeemachine;

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
            if (message instanceof DrinkMakerCommand) {
                drinkMaker.command(((DrinkMakerCommand) message).toCommand());
            }
        });
    }
}
