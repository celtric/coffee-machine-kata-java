package coffeemachine.adapters;

import coffeemachine.domain.Drink;
import coffeemachine.domain.Money;
import coffeemachine.domain.ReportingRepository;
import java.util.ArrayList;
import java.util.Collection;

public final class InMemoryReportingRepository implements ReportingRepository {

    private final Collection<Drink> orders = new ArrayList<>();

    @Override
    public void addDrink(Drink drink) {
        orders.add(drink);
    }

    @Override
    public Money totalEarned() {
        return orders.stream().map(o -> o.price()).reduce((m1, m2) -> m1.add(m2)).orElse(Money.euro(0));
    }
}
