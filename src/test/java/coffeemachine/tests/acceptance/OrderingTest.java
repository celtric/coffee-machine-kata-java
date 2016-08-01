package coffeemachine.tests.acceptance;

import coffeemachine.app.App;
import coffeemachine.domain.Drink;
import coffeemachine.domain.Money;
import coffeemachine.domain.events.DrinkOrderRequested;
import coffeemachine.app.MessageBus;
import coffeemachine.tests.utils.FakeDrinkMaker;
import org.junit.Test;

import static org.junit.Assert.*;

public final class OrderingTest {

    private final FakeDrinkMaker drinkMaker = new FakeDrinkMaker();
    private final App app = App.start(drinkMaker);

    @Test
    public void a_customer_can_order_tea_with_sugar() {
        buyDrink(Drink.tea(), 1, Money.euro(100));

        assertDrinkRequested(Drink.tea(), 1);
    }

    @Test
    public void a_customer_can_order_extra_hot_tea() {
        buyDrink(Drink.extraHotTea(), 1, Money.euro(100));

        assertDrinkRequested(Drink.extraHotTea(), 1);
    }

    @Test
    public void a_customer_can_order_chocolate() {
        buyDrink(Drink.chocolate(), 0, Money.euro(100));

        assertDrinkRequested(Drink.chocolate(), 0);
    }

    @Test
    public void a_customer_can_order_extra_hot_chocolate() {
        buyDrink(Drink.extraHotChocolate(), 0, Money.euro(100));

        assertDrinkRequested(Drink.extraHotChocolate(), 0);
    }

    @Test
    public void a_customer_can_order_coffee() {
        buyDrink(Drink.coffee(), 0, Money.euro(100));

        assertDrinkRequested(Drink.coffee(), 0);
    }

    @Test
    public void a_customer_can_order_extra_hot_coffee() {
        buyDrink(Drink.extraHotCoffee(), 0, Money.euro(100));

        assertDrinkRequested(Drink.extraHotCoffee(), 0);
    }

    @Test
    public void a_customer_can_order_orange_juice() {
        buyDrink(Drink.orangeJuice(), 0, Money.euro(100));

        assertDrinkRequested(Drink.orangeJuice(), 0);
    }

    @Test
    public void a_drink_order_is_rejected_if_not_enough_money_is_provided() {
        buyDrink(Drink.tea(), 1, Money.euro(0));

        assertMessagePrinted("Not enough money");
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private void buyDrink(Drink drink, int quantity, Money credit) {
        MessageBus.publish(new DrinkOrderRequested(drink, quantity, credit));
    }

    private void assertDrinkRequested(Drink drink, int quantity) {
        assertTrue(drinkMaker.hasDrinkBeenRequested(drink, quantity));
    }

    private void assertMessagePrinted(String message) {
        assertTrue(drinkMaker.hasMessageBeenPrinted(message));
    }
}
