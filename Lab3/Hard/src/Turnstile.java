public class Turnstile {
    private final Statistics stats = new Statistics();

    public boolean pass(TransportCard cardData) {
        if (cardData == null) {
            stats.recordDeny(null);
            return false;
        }

        CardKind kind = cardData.getKind();

        if (!cardData.isValid()) {
            stats.recordDeny(kind);
            return false;
        }

        if (!cardData.hasTripsLeft()) {
            stats.recordDeny(kind);
            return false;
        }

        if (!cardData.withdrawTrip()) {
            stats.recordDeny(kind);
            return false;
        }

        stats.recordAllow(kind);
        return true;
    }

    public Statistics getStatistics() { return stats; }
}
