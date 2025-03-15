package sliderule.core.number;

import java.math.BigDecimal;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

abstract class AbstractNumber<N extends AbstractNumber<N>> extends Number implements Comparable<N> {
    /**
     * Indicates if this number is invertible
     *
     * @return boolean
     */
    public abstract boolean isInvertible();

    /**
     * Indicates if this number is not invertible
     *
     * @return boolean
     */
    public final boolean isNotInvertible() {
        return !isInvertible();
    }

    /**
     * Returns the negated number
     *
     * @return negated number
     * @throws ArithmeticException when an operation overflows
     */
    public abstract N negate();

    /**
     * Returns the sum of this number and the summand
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when summand is null
     * @throws ArithmeticException when an operation overflows
     */
    public abstract @NonNull N add(@NonNull N summand);

    /**
     * Returns the difference of this number and the subtrahend
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when subtrahend is null
     * @throws ArithmeticException when an operation overflows
     */
    public abstract @NonNull N subtract(@NonNull N subtrahend);

    /**
     * Returns the product of this number and the multiplier
     *
     * @param multiplier multiplier
     * @return product
     * @throws NullPointerException when multiplier is null
     * @throws ArithmeticException when an operation overflows
     */
    public abstract @NonNull N multiply(@NonNull N multiplier);

    /**
     * Returns the quotient of this number and the divisor
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when divisor is null
     * @throws IllegalArgumentException when divisor is not invertible
     * @throws ArithmeticException when an operation overflows
     */
    public abstract @NonNull N divide(@NonNull N divisor);

    /**
     * Returns the inverted number
     *
     * @return inverted number
     * @throws IllegalStateException when this is not invertible
     */
    public abstract @NonNull N invert();

    /**
     * Returns this by the power of exponent
     *
     * @param exponent exponent
     * @return power
     * @throws ArithmeticException when an operation overflows
     */
    public abstract @NonNull N power(int exponent);

    /**
     * Returns if this number is less than the other
     *
     * @param other other
     * @return boolean
     * @throws NullPointerException if other is null
     */
    public final boolean isLessThan(final @NonNull N other) {
        Objects.requireNonNull(other, "other");
        return compareTo(other) < 0;
    }

    /**
     * Returns if this number is less than or equal to the other
     *
     * @param other other
     * @return boolean
     * @throws NullPointerException if other is null
     */
    public final boolean isLessThanOrEqualTo(final @NonNull N other) {
        Objects.requireNonNull(other, "other");
        return compareTo(other) <= 0;
    }

    /**
     * Returns if this number is greater than the other
     *
     * @param other other
     * @return boolean
     * @throws NullPointerException if other is null
     */
    public final boolean isGreaterThan(final @NonNull N other) {
        Objects.requireNonNull(other, "other");
        return compareTo(other) > 0;
    }

    /**
     * Returns if this number is greater than or equal to the other
     *
     * @param other other
     * @return boolean
     * @throws NullPointerException if other is null
     */
    public final boolean isGreaterThanOrEqualTo(final @NonNull N other) {
        Objects.requireNonNull(other, "other");
        return compareTo(other) >= 0;
    }

    /**
     * Unary + operator for Groovy
     *
     * @return this
     */
    public abstract N positive();

    /**
     * Unary + operator for Kotlin
     *
     * @return this
     */
    public abstract N unaryPlus();

    /**
     * Unary - operator for Groovy
     *
     * @return negated number
     */
    public final N negative() {
        return negate();
    }

    /**
     * Unary - operator for Kotlin
     *
     * @return negated number
     */
    public final N unaryMinus() {
        return negate();
    }

    /**
     * Binary + operator for Groovy and Kotlin
     *
     * @param summand summand
     * @return sum
     * @throws NullPointerException when summand is null
     */
    public final N plus(final @NonNull N summand) {
        return add(summand);
    }

    /**
     * Binary - operator for Groovy and Kotlin
     *
     * @param subtrahend subtrahend
     * @return difference
     * @throws NullPointerException when subtrahend is null
     */
    public final N minus(final @NonNull N subtrahend) {
        return subtract(subtrahend);
    }

    /**
     * Binary * operator for Kotlin
     *
     * @param multiplier multiplier
     * @return product
     * @throws NullPointerException when multiplier is null
     */
    public final @NonNull N times(final @NonNull N multiplier) {
        return multiply(multiplier);
    }

    /**
     * Binary / operator for Groovy and Kotlin
     *
     * @param divisor divisor
     * @return quotient
     * @throws NullPointerException when divisor is null
     * @throws IllegalArgumentException when divisor is not invertible
     */
    public final @NonNull N div(final @NonNull N divisor) {
        return divide(divisor);
    }

    @Override
    public final int intValue() {
        return toBigDecimal().intValue();
    }

    /**
     * Returns the exact int value
     *
     * @return exact int
     * @throws ArithmeticException when conversion is not exact
     */
    public final int intValueExact() {
        return toBigDecimal().intValueExact();
    }

    @Override
    public final long longValue() {
        return toBigDecimal().longValue();
    }

    /**
     * Returns the exact long value
     *
     * @return exact long
     * @throws ArithmeticException when conversion is not exact
     */
    public final long longValueExact() {
        return toBigDecimal().longValueExact();
    }

    @Override
    public final float floatValue() {
        return toBigDecimal().floatValue();
    }

    @Override
    public final double doubleValue() {
        return toBigDecimal().doubleValue();
    }

    /**
     * Returns this as {@link BigDecimal}
     *
     * @return BigDecimal
     */
    public abstract @NonNull BigDecimal toBigDecimal();
}
