package coffeemachine.tests.unit;

import coffeemachine.domain.Drink;
import coffeemachine.domain.DrinkMakerCommandFormatter;
import org.junit.Test;

import static org.junit.Assert.*;

public final class DrinkMakerCommandFormatterTest {

    @Test
    public void tea_with_one_sugar() {
        assertEquals("T:1:0", format(Drink.tea(), 1));
    }

    @Test
    public void chocolate_with_no_sugar() {
        assertEquals("H::", format(Drink.chocolate(), 0));
    }

    @Test
    public void coffee_with_two_sugars() {
        assertEquals("C:2:0", format(Drink.coffee(), 2));
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private String format(Drink drink, int quantity) {
        return new DrinkMakerCommandFormatter().format(drink, quantity);
    }
}
