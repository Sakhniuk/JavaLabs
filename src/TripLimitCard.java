public class TripLimitCard extends TransportCard {
    private int tripsLeft;

    public TripLimitCard(CardType type) {
        super(type, type.getValidDays());
        this.tripsLeft = type.getTrips();
    }

    @Override
    public boolean withdrawTrip() {
        if (!isValid() || tripsLeft <= 0) return false;
        tripsLeft--;
        return true;
    }

    @Override
    public boolean hasTripsLeft() {
        return tripsLeft > 0;
    }

    public int getTripsLeft() { return tripsLeft; }
}
