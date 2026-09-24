public class FractionBinarySearch {


    public static class Fraction {
        public final long p;
        public final long q;

        public Fraction(long p, long q) {
            long gcd = gcd(p, q);
            this.p = p / gcd;
            this.q = q / gcd;
        }

        private static long gcd(long a, long b) {
            return b == 0 ? a : gcd(b, a % b);
        }

        @Override
        public String toString() {
            return p + "/" + q;
        }
    }

    public static class FractionOracle {
        private final double target;
        private int queryCount = 0;

        public FractionOracle(long p, long q) {
            this.target = (double) p / q;
        }

        public boolean isLessThan(double x) {
            queryCount++;
            return target < x;
        }

        public int getQueryCount() {
            return queryCount;
        }
    }


    public static Fraction findFraction(FractionOracle oracle, int N) {
        double lo = 0.0;
        double hi = 1.0;

        // Giới hạn độ rộng khoảng tìm kiếm: < 1 / (2 * N^2)
        double threshold = 1.0 / (2.0 * (long) N * N);

        while ((hi - lo) > threshold) {
            double mid = lo + (hi - lo) / 2.0;
            if (oracle.isLessThan(mid)) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        double mid = lo + (hi - lo) / 2.0;
        return reconstructFraction(mid, N);
    }


    private static Fraction reconstructFraction(double x, int N) {
        long p0 = 0, q0 = 1;
        long p1 = 1, q1 = 0;
        double val = x;

        while (true) {
            long a = (long) Math.floor(val);
            long p2 = a * p1 + p0;
            long q2 = a * q1 + q0;

            if (q2 >= N) break;

            p0 = p1; q0 = q1;
            p1 = p2; q1 = q2;

            double diff = val - a;
            if (diff < 1e-12) break;
            val = 1.0 / diff;
        }

        return new Fraction(p1, q1);
    }

    public static void main(String[] args) {
        int N = 1000;
        long secretP = 355;
        long secretQ = 813;

        FractionOracle oracle = new FractionOracle(secretP, secretQ);

        Fraction result = findFraction(oracle, N);
        System.out.println("Phân số bí mật : " + secretP + "/" + secretQ);
        System.out.println("Phân số tìm được: " + result);
        System.out.println("Số câu hỏi đã dùng : " + oracle.getQueryCount());
        System.out.printf("Giới hạn 2*log2(N): %.2f câu hỏi%n", 2 * (Math.log(N) / Math.log(2)));
    }
}