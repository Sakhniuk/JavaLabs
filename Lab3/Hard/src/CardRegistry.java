import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CardRegistry {
    private final Map<String, TransportCard> cards = new HashMap<>();
    private final double tripCost;

    public CardRegistry(double tripCost) {
        this.tripCost = tripCost;
    }

    public TransportCard issueTripLimitCard(CardType type) {
        if (type.isAccumulative())
            throw new IllegalArgumentException("Для накопичувальної використовуйте issueAccumulativeCard");
        TransportCard c = new TripLimitCard(type);
        cards.put(c.getId(), c);
        return c;
    }

    public AccumulativeCard issueAccumulativeCard(double initialBalance) {
        AccumulativeCard c = new AccumulativeCard(initialBalance, tripCost);
        cards.put(c.getId(), c);
        return c;
    }

    public TransportCard find(String id) { return cards.get(id); }

    public Collection<TransportCard> all() { return cards.values(); }
}
