package coffeemachine.tests.acceptance;

import coffeemachine.app.App;
import coffeemachine.domain.Drink;
import coffeemachine.domain.events.DrinkOrdered;
import coffeemachine.app.MessageBus;
import coffeemachine.tests.utils.FakeDrinkMaker;
import org.junit.Test;

import static org.junit.Assert.*;

public final class OrderingTest {

    private final FakeDrinkMaker drinkMaker = new FakeDrinkMaker();
    private final App app = App.start(drinkMaker);

    @Test
    public void a_customer_can_order_tea_with_sugar() {
        publish(new DrinkOrdered("AN-ID", Drink.tea(), 1));

        assertDrinkRequested(Drink.tea(), 1);
    }

    @Test
    public void a_customer_can_order_chocolate() {
        publish(new DrinkOrdered("AN-ID", Drink.chocolate(), 0));

        assertDrinkRequested(Drink.chocolate(), 0);
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private void publish(Object message) {
        MessageBus.publish(message);
    }

    private void assertDrinkRequested(Drink drink, int quantity) {
        assertTrue(drinkMaker.hasDrinkBeenRequested(drink, quantity));
    }
}
