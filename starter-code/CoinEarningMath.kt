package com.deepworktracker.core.rewards

import java.math.BigInteger

/**
 * Exact coin earning calculation from the approved product formula:
 * Coins = validSeconds * 640 / 3600.
 *
 * The result remains a rational number. No per-second rounding is performed.
 * The product has not yet approved a final fractional display/carry policy,
 * so callers must not silently round or discard the remainder.
 */
data class ExactCoinAmount(
    val numerator: BigInteger,
    val denominator: BigInteger = BigInteger.valueOf(3600L)
) {
    init {
        require(denominator > BigInteger.ZERO) { "Denominator must be positive." }
    }

    fun wholeCoinsFloor(): BigInteger = numerator.divide(denominator)

    fun remainderNumerator(): BigInteger = numerator.remainder(denominator)
}

object CoinEarningMath {
    private val coinsPerHour = BigInteger.valueOf(640L)
    private val secondsPerHour = BigInteger.valueOf(3600L)

    fun calculate(validSeconds: Long): ExactCoinAmount {
        require(validSeconds >= 0L) { "Valid seconds cannot be negative." }
        val numerator = BigInteger.valueOf(validSeconds).multiply(coinsPerHour)
        return ExactCoinAmount(
            numerator = numerator,
            denominator = secondsPerHour
        )
    }
}
