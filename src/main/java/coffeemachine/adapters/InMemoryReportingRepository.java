package coffeemachine.adapters;

import coffeemachine.domain.Drink;
import coffeemachine.domain.Money;
import coffeemachine.domain.ReportingRepository;
import java.util.ArrayList;
import java.util.Collection;

public final class InMemoryReportingRepository implements ReportingRepository {

    private final Collection<Drink> drinks = new ArrayList<>();

    @Override
    public void addDrink(Drink drink) {
        drinks.add(drink);
    }

    @Override
    public Money totalEarned() {
        return drinks.stream().map(o -> o.price()).reduce((m1, m2) -> m1.add(m2)).orElse(Money.euro(0));
    }

    @Override
    public long totalByDrink(Drink drink) {
        return drinks.stream().filter(d -> d.equals(drink)).count();
    }
}
