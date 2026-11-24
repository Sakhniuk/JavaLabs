public enum CardType {
    MONTH_LIMIT_5(CardKind.REGULAR, 30, 5, false),
    MONTH_LIMIT_10(CardKind.REGULAR, 30, 10, false),
    TEN_DAYS_LIMIT_5(CardKind.REGULAR, 10, 5, false),
    TEN_DAYS_LIMIT_10(CardKind.REGULAR, 10, 10, false),

    PUPIL_MONTH_5(CardKind.PUPIL, 30, 5, false),
    PUPIL_MONTH_10(CardKind.PUPIL, 30, 10, false),
    PUPIL_TEN_DAYS_5(CardKind.PUPIL, 10, 5, false),
    PUPIL_TEN_DAYS_10(CardKind.PUPIL, 10, 10, false),

    STUDENT_MONTH_5(CardKind.STUDENT, 30, 5, false),
    STUDENT_MONTH_10(CardKind.STUDENT, 30, 10, false),
    STUDENT_TEN_DAYS_5(CardKind.STUDENT, 10, 5, false),
    STUDENT_TEN_DAYS_10(CardKind.STUDENT, 10, 10, false),

    ACCUMULATIVE(CardKind.REGULAR, Integer.MAX_VALUE, Integer.MAX_VALUE, true);

    private final CardKind kind;
    private final int validDays;
    private final int trips;
    private final boolean accumulative;

    CardType(CardKind kind, int validDays, int trips, boolean accumulative) {
        this.kind = kind;
        this.validDays = validDays;
        this.trips = trips;
        this.accumulative = accumulative;
    }

    public CardKind getKind() { return kind; }
    public int getValidDays() { return validDays; }
    public int getTrips() { return trips; }
    public boolean isAccumulative() { return accumulative; }
}
