package coffeemachine.tests.utils;

import coffeemachine.domain.Drink;
import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.DrinkMakerCommandFormatter;
import java.util.ArrayList;
import java.util.Collection;

public final class FakeDrinkMaker implements DrinkMaker {

    private final Collection<String> commandHistory = new ArrayList<>();

    @Override
    public void command(String command) {
        commandHistory.add(command);
    }

    public boolean hasDrinkBeenRequested(Drink drink, int quantity) {
        return commandHistory
                .stream()
                .filter(c -> c.equals(new DrinkMakerCommandFormatter().format(drink, quantity)))
                .count() != 0;
    }
}
