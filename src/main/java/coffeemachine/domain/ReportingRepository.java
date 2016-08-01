package coffeemachine.domain;

public interface ReportingRepository {

    void addDrink(Drink drink);

    Money totalEarned();

    long totalByDrink(Drink drink);
}
