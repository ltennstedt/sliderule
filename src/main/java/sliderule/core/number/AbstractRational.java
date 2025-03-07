package sliderule.core.number;

import org.jspecify.annotations.NonNull;

/**
 * Base class for rational numbers
 *
 * @param <R> type of the rational number
 */
abstract class AbstractRational<R extends AbstractRational<R>> extends AbstractNumber<R> {
    /**
     * Indicates if this rational is a unit rational
     *
     * @return boolean
     */
    public abstract boolean isUnit();

    /**
     * Indicates if this rational is not a unit rational
     *
     * @return boolean
     */
    public final boolean isNotUnit() {
        return !isUnit();
    }

    /**
     * Indicates if this rational is dyadic
     *
     * @return boolean
     */
    public abstract boolean isDyadic();

    /**
     * Indicates if this rational is not dyadic
     *
     * @return boolean
     */
    public final boolean isNotDyadic() {
        return !isDyadic();
    }

    /**
     * Indicates if this rational has an invisible denominator
     *
     * @return boolean
     */
    public abstract boolean hasInvisibleDenominator();

    /**
     * Indicates if this rational does not have an invisible denominator
     *
     * @return boolean
     */
    public final boolean doesNotHaveInvisibleDenominator() {
        return !hasInvisibleDenominator();
    }

    /**
     * Indicates if this rational is a decimal rational
     *
     * @return boolean
     */
    public abstract boolean isDecimal();

    /**
     * Indicates if this rational is not a decimal rational
     *
     * @return boolean
     */
    public final boolean isNotDecimal() {
        return !isDecimal();
    }

    /**
     * Returns the signum
     *
     * @return signum
     * @throws ArithmeticException when an operation overflows
     */
    public abstract int signum();

    /**
     * Returns the minimum
     *
     * @param other other
     * @return boolean
     */
    public abstract @NonNull R min(@NonNull R other);

    /**
     * Returns the maximum
     *
     * @param other other
     * @return boolean
     */
    public abstract @NonNull R max(@NonNull R other);

    /**
     * Returns the canonical representation
     *
     * @return canonical representation
     */
    public abstract @NonNull R canonical();
}
