import java.time.LocalDate;
import java.util.UUID;

public abstract class TransportCard {
    protected final String id;
    protected final CardType type;
    protected final LocalDate issueDate;
    protected final LocalDate expiryDate;

    protected TransportCard(CardType type, int validDays) {
        this.id = UUID.randomUUID().toString();
        this.type = type;
        this.issueDate = LocalDate.now();
        this.expiryDate = validDays == Integer.MAX_VALUE
                ? LocalDate.MAX
                : issueDate.plusDays(validDays);
    }

    public String getId() { return id; }
    public CardType getType() { return type; }
    public CardKind getKind() { return type.getKind(); }
    public LocalDate getExpiryDate() { return expiryDate; }

    public boolean isValid() {
        return !LocalDate.now().isAfter(expiryDate);
    }

    public abstract boolean withdrawTrip();

    public abstract boolean hasTripsLeft();
}
