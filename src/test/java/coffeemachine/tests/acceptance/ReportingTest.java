package coffeemachine.tests.acceptance;

import coffeemachine.adapters.InMemoryReportingRepository;
import coffeemachine.app.App;
import coffeemachine.app.MessageBus;
import coffeemachine.domain.Drink;
import coffeemachine.domain.DrinkMaker;
import coffeemachine.domain.Money;
import coffeemachine.domain.ReportingRepository;
import coffeemachine.domain.events.DrinkOrderRequested;
import coffeemachine.tests.utils.FakeDrinkMaker;
import org.junit.Test;

import static org.junit.Assert.*;

public final class ReportingTest {

    private final DrinkMaker drinkMaker = new FakeDrinkMaker();
    private final ReportingRepository reportingRepository = new InMemoryReportingRepository();
    private final MessageBus messageBus = new MessageBus();
    private final App app = App.start(drinkMaker, reportingRepository, messageBus);

    @Test
    public void reporting_case_01() {
        buyDrink(Drink.tea(), 1, Money.euro(100));

        assertTotalEarned(Money.euro(40));
    }

    @Test
    public void reporting_case_02() {
        buyDrink(Drink.tea(), 1, Money.euro(100));
        buyDrink(Drink.tea(), 1, Money.euro(10));

        assertTotalEarned(Money.euro(40));
    }

    @Test
    public void reporting_case_03() {
        buyDrink(Drink.tea(), 1, Money.euro(100));
        buyDrink(Drink.tea(), 1, Money.euro(10));
        buyDrink(Drink.tea(), 1, Money.euro(40));
        buyDrink(Drink.coffee(), 1, Money.euro(80));
        buyDrink(Drink.extraHotCoffee(), 1, Money.euro(200));

        assertTotalEarned(Money.euro(200));
    }

    //---[ Helpers ]--------------------------------------------------------------------//

    private void buyDrink(Drink drink, int sugarQuantity, Money credit) {
        messageBus.publish(new DrinkOrderRequested(null, drink, sugarQuantity, credit));
    }

    private void assertTotalEarned(Money money) {
        assertEquals(money, reportingRepository.totalEarned());
    }
}
