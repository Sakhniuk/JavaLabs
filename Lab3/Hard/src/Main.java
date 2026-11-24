public class Main {
    public static void main(String[] args) {
        CardRegistry registry = new CardRegistry(8.0);
        Turnstile turnstile = new Turnstile();

        TransportCard pupil5   = registry.issueTripLimitCard(CardType.PUPIL_MONTH_5);
        TransportCard student10= registry.issueTripLimitCard(CardType.STUDENT_TEN_DAYS_10);
        TransportCard regular5 = registry.issueTripLimitCard(CardType.MONTH_LIMIT_5);
        AccumulativeCard acc   = registry.issueAccumulativeCard(20.0);

        System.out.println("Учень, 1-й прохід:      " + turnstile.pass(pupil5));
        System.out.println("Студент, 1-й прохід:    " + turnstile.pass(student10));
        System.out.println("Звичайна, 1-й прохід:   " + turnstile.pass(regular5));
        System.out.println("Накопич., 1-й прохід:   " + turnstile.pass(acc));
        System.out.println("Накопич., 2-й прохід:   " + turnstile.pass(acc));
        System.out.println("Накопич., 3-й прохід:   " + turnstile.pass(acc));

        System.out.println("Пошкоджена картка:      " + turnstile.pass(null));

        acc.topUp(16.0);
        System.out.println("Після поповнення:       " + turnstile.pass(acc));

        for (int i = 0; i < 4; i++) turnstile.pass(regular5);
        System.out.println("Звичайна, 5-та (мала б бути): " + turnstile.pass(regular5));
        System.out.println("Звичайна, 6-та (відмова):     " + turnstile.pass(regular5));

        System.out.println();
        System.out.println(turnstile.getStatistics().detailed());
    }
}