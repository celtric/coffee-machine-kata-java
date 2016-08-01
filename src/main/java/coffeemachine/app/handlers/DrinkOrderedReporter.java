package coffeemachine.app.handlers;

import coffeemachine.domain.ReportingRepository;
import coffeemachine.domain.events.DrinkOrdered;

public final class DrinkOrderedReporter implements MessageHandler<DrinkOrdered> {

    private final ReportingRepository repository;

    public DrinkOrderedReporter(ReportingRepository repository) {
        this.repository = repository;
    }

    @Override
    public void handle(DrinkOrdered message) {
        repository.addDrink(message.getDrink());
    }
}
