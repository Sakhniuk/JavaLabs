public class AccumulativeCard extends TransportCard {
    private double balance;
    private final double tripCost;

    public AccumulativeCard(double initialBalance, double tripCost) {
        super(CardType.ACCUMULATIVE, Integer.MAX_VALUE);
        if (initialBalance < 0) throw new IllegalArgumentException("Баланс < 0");
        if (tripCost <= 0) throw new IllegalArgumentException("Вартість <= 0");
        this.balance = initialBalance;
        this.tripCost = tripCost;
    }

    public void topUp(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Сума поповнення <= 0");
        balance += amount;
    }

    @Override
    public boolean withdrawTrip() {
        if (!isValid() || balance < tripCost) return false;
        balance -= tripCost;
        return true;
    }

    @Override
    public boolean hasTripsLeft() {
        return balance >= tripCost;
    }

    public double getBalance() { return balance; }
    public double getTripCost() { return tripCost; }
}
