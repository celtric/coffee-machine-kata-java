package coffeemachine.app;

import coffeemachine.app.handlers.DrinkOrderRequestedHandler;
import coffeemachine.app.handlers.DrinkOrderedHandler;
import coffeemachine.app.handlers.DrinkOrderedReporter;
import coffeemachine.app.handlers.UserMessageRequestedHandler;
import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.ReportingRepository;

public final class App {

    private final DrinkMaker drinkMaker;
    private final ReportingRepository reportingRepository;
    private final MessageBus messageBus;

    private App(DrinkMaker drinkMaker, ReportingRepository reportingRepository, MessageBus messageBus) {
        this.drinkMaker = drinkMaker;
        this.reportingRepository = reportingRepository;
        this.messageBus = messageBus;
    }

    public static App start(DrinkMaker drinkMaker, ReportingRepository reportingRepository, MessageBus messageBus) {
        App app = new App(drinkMaker, reportingRepository, messageBus);
        app.registerListeners();

        return app;
    }

    private void registerListeners() {
        messageBus.listen(new BusMessageHandler<>(new DrinkOrderRequestedHandler(messageBus)));
        messageBus.listen(new BusMessageHandler<>(new DrinkOrderedHandler(drinkMaker)));
        messageBus.listen(new BusMessageHandler<>(new UserMessageRequestedHandler(drinkMaker)));
        messageBus.listen(new BusMessageHandler<>(new DrinkOrderedReporter(reportingRepository)));
    }
}
