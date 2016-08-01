package coffeemachine.tests.utils;

import coffeemachine.Drink;
import coffeemachine.DrinkMaker;
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
                .filter(c -> c.equals(String.format("%s:%d:0", drink.toShortHand(), quantity)))
                .count() != 0;
    }
}
