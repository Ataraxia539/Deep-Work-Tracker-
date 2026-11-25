package com.deepworktracker.core.rewards

import org.junit.Assert.assertEquals
import org.junit.Test
import java.math.BigInteger

class CoinEarningMathTest {
    @Test
    fun zeroSecondsEarnZeroCoins() {
        val result = CoinEarningMath.calculate(0L)
        assertEquals(BigInteger.ZERO, result.numerator)
        assertEquals(BigInteger.valueOf(3600L), result.denominator)
    }

    @Test
    fun oneHourEqualsExactly640Coins() {
        val result = CoinEarningMath.calculate(3600L)
        assertEquals(BigInteger.valueOf(640L), result.wholeCoinsFloor())
        assertEquals(BigInteger.ZERO, result.remainderNumerator())
    }

    @Test
    fun oneMinutePreservesFractionWithoutRounding() {
        val result = CoinEarningMath.calculate(60L)
        assertEquals(BigInteger.valueOf(38400L), result.numerator)
        assertEquals(BigInteger.valueOf(3600L), result.denominator)
        assertEquals(BigInteger.valueOf(10L), result.wholeCoinsFloor())
        assertEquals(BigInteger.valueOf(2400L), result.remainderNumerator())
    }

    @Test
    fun splitSessionsCanBeAddedExactly() {
        val first = CoinEarningMath.calculate(1800L)
        val second = CoinEarningMath.calculate(1800L)
        val totalNumerator = first.numerator.add(second.numerator)
        assertEquals(BigInteger.valueOf(640L), totalNumerator.divide(BigInteger.valueOf(3600L)))
        assertEquals(BigInteger.ZERO, totalNumerator.remainder(BigInteger.valueOf(3600L)))
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeSecondsAreRejected() {
        CoinEarningMath.calculate(-1L)
    }
}
