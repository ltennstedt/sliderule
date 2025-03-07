package sliderule.core.util;

import java.util.stream.IntStream;

/** Utilities for long */
public final class Longs {
    private Longs() {}

    /**
     * Calculates base to the power of exponent
     *
     * @param base base
     * @param exponent exponent
     * @return power
     * @throws ArithmeticException when an operation overflows
     */
    public static double powerExact(final long base, final int exponent) {
        if (base == 0) {
            return 0;
        }
        if (base == 1 || exponent == 0) {
            return 1;
        }
        if (exponent == 1) {
            return base;
        }
        final var power = IntStream.rangeClosed(1, Math.abs(exponent))
                .mapToLong(i -> base)
                .reduce(Math::multiplyExact)
                .orElseThrow();
        return exponent < 0 ? 1.0D / power : power;
    }

    /**
     * Returns the positive greatest common divisor
     *
     * @param a a
     * @param b b
     * @return greatest common divisor
     */
    public static long gcd(final long a, final long b) {
        return b == 0 ? Math.absExact(a) : gcd(b, a % b);
    }
}
