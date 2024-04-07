import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class PuttingIntoPractice {

    public static void main(String... args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = List.of(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // еайти все транзакции за 2011 год и отсортировать их по сумме
        List<Transaction> transactions2011 = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .collect(Collectors.toList());

        //  вывести список неповторяющихся городов, в которых работают трейдеры
        Set<String> uniqueCities = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());

        //  найти всех трейдеров из Кембриджа и отсортировать их по именам
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::getTrader)
                .filter(trader -> trader.getCity().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        //  нернуть строку со всеми именами трейдеров, отсортированными в алфавитном порядке
        String sortedTraderNames = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining(", "));

        // выяснить, существует ли хоть один трейдер из Милана
        boolean anyTraderFromMilan = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));

        // вывести суммы всех транзакций трейдеров из Кембриджа
        int totalValueFromCambridge = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .mapToInt(Transaction::getValue)
                .sum();

        // какова максимальная сумма среди всех транзакций?
        int maxTransactionValue = transactions.stream()
                .mapToInt(Transaction::getValue)
                .max()
                .orElse(0);

        // найти транзакцию с минимальной суммой.
        Transaction minTransaction = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue))
                .orElse(null);

        System.out.println("1. Транзакции за 2011 год, отсортированные по сумме: " + transactions2011);
        System.out.println("2. Список неповторяющихся городов: " + uniqueCities);
        System.out.println("3. Все трейдеры из Кембриджа, отсортированные по именам: " + tradersFromCambridge);
        System.out.println("4. Все имена трейдеров, отсортированные в алфавитном порядке: " + sortedTraderNames);
        System.out.println("5. Существует ли трейдер из Милана? " + anyTraderFromMilan);
        System.out.println("6. Суммы всех транзакций трейдеров из Кембриджа: " + totalValueFromCambridge);
        System.out.println("7. Максимальная сумма среди всех транзакций: " + maxTransactionValue);
        System.out.println("8. Транзакция с минимальной суммой: " + minTransaction);
    }
}
