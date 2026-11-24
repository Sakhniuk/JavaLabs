public enum CardKind {
    PUPIL("Учнівська"),
    STUDENT("Студентська"),
    REGULAR("Звичайна");

    private final String title;
    CardKind(String title) { this.title = title; }
    public String getTitle() { return title; }
}
