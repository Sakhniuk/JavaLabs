import java.util.EnumMap;
import java.util.Map;

public class Statistics {
    private int allowed = 0;
    private int denied = 0;
    private final Map<CardKind, Integer> allowedByKind = new EnumMap<>(CardKind.class);
    private final Map<CardKind, Integer> deniedByKind = new EnumMap<>(CardKind.class);

    {
        for (CardKind k : CardKind.values()) {
            allowedByKind.put(k, 0);
            deniedByKind.put(k, 0);
        }
    }

    void recordAllow(CardKind kind) {
        allowed++;
        allowedByKind.merge(kind, 1, Integer::sum);
    }

    void recordDeny(CardKind kind) {
        denied++;
        if (kind != null) deniedByKind.merge(kind, 1, Integer::sum);
    }

    public int getTotalAllowed() { return allowed; }
    public int getTotalDenied() { return denied; }
    public Map<CardKind, Integer> getAllowedByKind() { return allowedByKind; }
    public Map<CardKind, Integer> getDeniedByKind() { return deniedByKind; }

    public String summary() {
        StringBuilder sb = new StringBuilder();
        sb.append("Загальна статистика турнікету\n");
        sb.append("Дозволено проходів: ").append(allowed).append("\n");
        sb.append("Відмовлено:         ").append(denied).append("\n");
        return sb.toString();
    }

    public String detailed() {
        StringBuilder sb = new StringBuilder(summary());
        sb.append("\nРозбивка по типах карток \n");
        for (CardKind k : CardKind.values()) {
            sb.append(String.format("%-12s дозволено: %d, відмовлено: %d%n",
                    k.getTitle(), allowedByKind.get(k), deniedByKind.get(k)));
        }
        return sb.toString();
    }
}
