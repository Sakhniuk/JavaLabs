import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PerfectNumbers {

    static boolean isPrime(long n) {
        if (n < 2) return false;
        if (n < 4) return true;
        if (n % 2 == 0) return false;
        for (long i = 3; i * i <= n; i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    static List<Long> perfectNumbers(long limit) {
        List<Long> result = new ArrayList<>();
        int p = 2;
        while (true) {
            long mersenne = (1L << p) - 1;
            if (mersenne > limit) break;

            if (isPrime(p) && isPrime(mersenne)) {
                long n = (1L << (p - 1)) * mersenne;
                if (n <= limit) result.add(n);
            }
            p++;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введіть верхню межу: ");
        long limit = sc.nextLong();

        List<Long> perfect = perfectNumbers(limit);
        System.out.println("Досконалі числа: " + perfect);
    }
}