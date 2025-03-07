package sliderule.core.number;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Stream;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;
import sliderule.core.util.Longs;

/** Immutable implementation of a rational number */
public final class LongRational extends AbstractRational<LongRational> {
    /** Comparator */
    public static final @NonNull Comparator<LongRational> COMPARATOR = Comparable::compareTo;

    /** 0 */
    public static final @NonNull LongRational ZERO = new LongRational(0, 1);

    /** 1 */
    public static final @NonNull LongRational ONE = new LongRational(1, 1);

    private final long numerator;
    private final long denominator;

    /**
     * All arguments constructor
     *
     * @param numerator numerator
     * @param denominator denominator
     * @throws IllegalArgumentException when denominator is 0
     */
    public LongRational(final long numerator, final long denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Expected denominator != 0 but denominator=" + 0);
        }
        final var absNumerator = Math.absExact(numerator);
        this.numerator =
                Long.signum(numerator) * Long.signum(denominator) < 0 ? Math.negateExact(absNumerator) : absNumerator;
        this.denominator = Math.absExact(denominator);
    }

    @Override
    public boolean isInvertible() {
        return numerator != 0;
    }

    @Override
    public boolean isUnit() {
        return numerator == 1;
    }

    @Override
    public boolean isDyadic() {
        final var quotient = Math.log((double) denominator) / Math.log(2);
        return (long) Math.ceil(quotient) == (long) Math.floor(quotient);
    }

    @Override
    public boolean hasInvisibleDenominator() {
        return denominator == 1;
    }

    @Override
    public boolean isDecimal() {
        return Stream.iterate(
                        BigDecimal.valueOf(denominator),
                        decimal -> decimal.divide(BigDecimal.TEN, MathContext.UNLIMITED))
                .limit(String.valueOf(denominator).length())
                .anyMatch(decimal -> decimal.compareTo(BigDecimal.ONE) == 0);
    }

    @Override
    public LongRational negate() {
        return new LongRational(Math.negateExact(numerator), denominator);
    }

    @Override
    public @NonNull LongRational add(final @NonNull LongRational summand) {
        Objects.requireNonNull(summand, "summand");
        return new LongRational(
                Math.addExact(
                        Math.multiplyExact(summand.getDenominator(), numerator),
                        Math.multiplyExact(denominator, summand.getNumerator())),
                Math.multiplyExact(denominator, summand.getDenominator()));
    }

    @Override
    public @NonNull LongRational subtract(final @NonNull LongRational subtrahend) {
        Objects.requireNonNull(subtrahend, "subtrahend");
        return new LongRational(
                Math.subtractExact(
                        Math.multiplyExact(subtrahend.getDenominator(), numerator),
                        Math.multiplyExact(denominator, subtrahend.getNumerator())),
                Math.multiplyExact(denominator, subtrahend.getDenominator()));
    }

    @Override
    public @NonNull LongRational multiply(final @NonNull LongRational multiplier) {
        Objects.requireNonNull(multiplier, "multiplier");
        return new LongRational(
                Math.multiplyExact(numerator, multiplier.getNumerator()),
                Math.multiplyExact(denominator, multiplier.getDenominator()));
    }

    @Override
    public @NonNull LongRational divide(final @NonNull LongRational divisor) {
        Objects.requireNonNull(divisor, "divisor");
        if (divisor.isNotInvertible()) {
            throw new IllegalArgumentException("divisor must be invertible but is " + divisor);
        }
        return new LongRational(numerator * divisor.getNumerator(), denominator * divisor.getDenominator());
    }

    @Override
    public @NonNull LongRational invert() {
        if (isNotInvertible()) {
            throw new IllegalStateException("must be invertible but is " + this);
        }
        return new LongRational(denominator, numerator);
    }

    @Override
    public @NonNull LongRational power(final int exponent) {
        return new LongRational(
                (long) Longs.powerExact(numerator, exponent), (long) Longs.powerExact(denominator, exponent));
    }

    @Override
    public int signum() {
        return Long.signum(numerator);
    }

    @Override
    public @NonNull LongRational min(final @NonNull LongRational other) {
        Objects.requireNonNull(other, "other");
        return isLessThanOrEqualTo(other) ? this : other;
    }

    @Override
    public @NonNull LongRational max(final @NonNull LongRational other) {
        Objects.requireNonNull(other, "other");
        return isGreaterThanOrEqualTo(other) ? this : other;
    }

    @Override
    public @NonNull LongRational canonical() {
        final var gcd = Longs.gcd(numerator, denominator);
        return new LongRational(numerator / gcd, denominator / gcd);
    }

    @Override
    public @NonNull LongRational positive() {
        return this;
    }

    @Override
    public @NonNull LongRational unaryPlus() {
        return this;
    }

    @Override
    public @NonNull BigDecimal toBigDecimal() {
        return BigDecimal.valueOf(numerator).divide(BigDecimal.valueOf(denominator), MathContext.UNLIMITED);
    }

    @Override
    public int compareTo(final @NonNull LongRational other) {
        Objects.requireNonNull(other, "other");
        return Long.compare(
                Math.multiplyExact(other.getDenominator(), numerator),
                Math.multiplyExact(denominator, other.getNumerator()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    @Override
    public boolean equals(final @Nullable Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof final LongRational that)) {
            return false;
        }
        return numerator == that.getNumerator() && denominator == that.getDenominator();
    }

    @Override
    public @NonNull String toString() {
        return "LongRational{numerator=" + numerator + ", denominator=" + denominator + '}';
    }

    /**
     * Numerator
     *
     * @return numerator
     */
    public long getNumerator() {
        return numerator;
    }

    /**
     * Denominator
     *
     * @return denominator
     */
    public long getDenominator() {
        return denominator;
    }
}
