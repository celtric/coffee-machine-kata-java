package coffeemachine.domain;

import lombok.Value;

@Value
public final class Money {

    private final int amount;
    private final String currency;

    private Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money euro(int amount) {
        return new Money(amount, "EUR");
    }

    boolean lessThanOrEqual(Money money) {
        assertSameCurrency(money);
        return amount <= money.amount;
    }

    private void assertSameCurrency(Money money) {
        if (!money.currency.equals(currency)) {
            throw new RuntimeException("Forbidden operation on different currencies");
        }
    }

    public Money add(Money money) {
        assertSameCurrency(money);
        return new Money(amount + money.amount, currency);
    }
}
