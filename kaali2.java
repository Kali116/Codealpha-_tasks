import java.util.*;

class Stock {
    String symbol;
    double price;
    Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }
}

class Transaction {
    String type, symbol;
    int quantity;
    double price;
    Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
    }
    public String toString() {
        return type + " " + quantity + " of " + symbol + " at $" + price;
    }
}

class User {
    double balance;
    Map<String, Integer> portfolio = new HashMap<>();
    List<Transaction> history = new ArrayList<>();

    User(double balance) {
        this.balance = balance;
    }

    void buy(String symbol, int qty, double price) {
        double cost = qty * price;
        if (balance >= cost) {
            balance -= cost;
            portfolio.put(symbol, portfolio.getOrDefault(symbol, 0) + qty);
            history.add(new Transaction("BUY", symbol, qty, price));
            System.out.println("Bought " + qty + " shares of " + symbol);
        } else System.out.println("Not enough balance.");
    }

    void sell(String symbol, int qty, double price) {
        if (portfolio.getOrDefault(symbol, 0) >= qty) {
            balance += qty * price;
            portfolio.put(symbol, portfolio.get(symbol) - qty);
            if (portfolio.get(symbol) == 0) portfolio.remove(symbol);
            history.add(new Transaction("SELL", symbol, qty, price));
            System.out.println("Sold " + qty + " shares of " + symbol);
        } else System.out.println("Not enough shares.");
    }

    void showPortfolio(Map<String, Stock> market) {
        System.out.println("\n=== Portfolio ===");
        if (portfolio.isEmpty()) {
            System.out.println("No stocks.");
            return;
        }
        double total = 0;
        for (String sym : portfolio.keySet()) {
            int qty = portfolio.get(sym);
            double price = market.get(sym).price;
            double value = qty * price;
            total += value;
            System.out.println(sym + ": " + qty + " shares | $" + value);
        }
        System.out.println("Cash: $" + balance);
        System.out.println("Total Value: $" + (total + balance));
    }

    void showHistory() {
        System.out.println("\n=== Transactions ===");
        if (history.isEmpty()) System.out.println("No history.");
        else history.forEach(System.out::println);
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Map<String, Stock> market = new HashMap<>();
        market.put("AAPL", new Stock("AAPL", 180));
        market.put("TSLA", new Stock("TSLA", 250));
        market.put("GOOG", new Stock("GOOG", 2800));
        market.put("AMZN", new Stock("AMZN", 3400));
        market.put("MSFT", new Stock("MSFT", 320));

        User user = new User(10000);

        while (true) {
            System.out.println("\n=== Stock Trading ===");
            System.out.println("1. View Market  2. Buy  3. Sell  4. Portfolio  5. History  6. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt(); sc.nextLine();

            switch (ch) {
                case 1 -> market.forEach((k, v) -> System.out.println(k + ": $" + v.price));
                case 2 -> {
                    System.out.print("Symbol to buy: ");
                    String sym = sc.nextLine().toUpperCase();
                    if (!market.containsKey(sym)) { System.out.println("Invalid."); break; }
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt(); sc.nextLine();
                    user.buy(sym, qty, market.get(sym).price);
                }
                case 3 -> {
                    System.out.print("Symbol to sell: ");
                    String sym = sc.nextLine().toUpperCase();
                    if (!market.containsKey(sym)) { System.out.println("Invalid."); break; }
                    System.out.print("Quantity: ");
                    int qty = sc.nextInt(); sc.nextLine();
                    user.sell(sym, qty, market.get(sym).price);
                }
                case 4 -> user.showPortfolio(market);
                case 5 -> user.showHistory();
                case 6 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }
}