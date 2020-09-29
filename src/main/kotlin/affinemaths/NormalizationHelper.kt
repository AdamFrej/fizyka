package affinemaths

import kotlin.math.absoluteValue

class NormalizationHelper {
    companion object {
        private const val precision = 5

        fun normalize(value: Double, exponent: Int): Scalar {
            val numberOfDigits = numberOfDigits(value)
            val correction = precision - numberOfDigits
            return normalize((value * tenToPow(precision + correction)).toLong(), exponent - correction - 1)
        }

        fun normalize(string: String, exponent: String): Scalar {
            if (string.toLongOrNull() != null) {
                return normalize(string.toLong(), exponent.toInt() + precision - 1)
            }
            return normalize(string.toDouble(), exponent.toInt())
        }

        fun normalize(value: Long, exponent: Int): Scalar {
            val numberOfDigits = numberOfDigits(value)
            val correction = numberOfDigits - precision
            if (numberOfDigits > precision) {
                return Scalar(value / tenToPow(correction), exponent + correction)
            }
            if (numberOfDigits < precision) {
                return Scalar(value * tenToPow(correction), exponent + correction)
            }
            return Scalar(value, exponent)
        }

        fun tenToPow(pow: Int): Long {
            if (pow.absoluteValue <= 0) return 1
            if (pow.absoluteValue == 1) return 10
            if (pow.absoluteValue == 2) return 100
            if (pow.absoluteValue == 3) return 1_000
            if (pow.absoluteValue == 4) return 10_000
            if (pow.absoluteValue == 5) return 100_000
            if (pow.absoluteValue == 6) return 1_000_000
            if (pow.absoluteValue == 7) return 10_000_000
            if (pow.absoluteValue == 8) return 100_000_000
            if (pow.absoluteValue == 9) return 1_000_000_000
            if (pow.absoluteValue == 10) return 10_000_000_000
            if (pow.absoluteValue == 11) return 100_000_000_000
            if (pow.absoluteValue == 12) return 1_000_000_000_000
            if (pow.absoluteValue == 13) return 10_000_000_000_000
            if (pow.absoluteValue == 14) return 100_000_000_000_000
            return 1_000_000_000_000_000
        }

        private fun numberOfDigits(value: Long): Int {
            val absoluteValue = value.absoluteValue
            if (absoluteValue < 10) return 1;
            if (absoluteValue < 100) return 2;
            if (absoluteValue < 1_000) return 3;
            if (absoluteValue < 10_000) return 4;
            if (absoluteValue < 100_000) return 5;
            if (absoluteValue < 1_000_000) return 6;
            if (absoluteValue < 10_000_000) return 7;
            if (absoluteValue < 100_000_000) return 8;
            if (absoluteValue < 1_000_000_000) return 9;
            if (absoluteValue < 10_000_000_000) return 10;
            if (absoluteValue < 100_000_000_000) return 11;
            if (absoluteValue < 1_000_000_000_000) return 12;
            if (absoluteValue < 10_000_000_000_000) return 13;
            if (absoluteValue < 100_000_000_000_000) return 14;
            return 15;
        }

        private fun numberOfDigits(value: Double): Int {
            val absoluteValue = value.absoluteValue
            if (absoluteValue < 1) return 0;
            if (absoluteValue < 10) return 1;
            if (absoluteValue < 100) return 2;
            if (absoluteValue < 1_000) return 3;
            if (absoluteValue < 10_000) return 4;
            if (absoluteValue < 100_000) return 5;
            if (absoluteValue < 1_000_000) return 6;
            if (absoluteValue < 10_000_000) return 7;
            if (absoluteValue < 100_000_000) return 8;
            if (absoluteValue < 1_000_000_000) return 9;
            if (absoluteValue < 10_000_000_000) return 10;
            if (absoluteValue < 100_000_000_000) return 11;
            if (absoluteValue < 1_000_000_000_000) return 12;
            if (absoluteValue < 10_000_000_000_000) return 13;
            if (absoluteValue < 100_000_000_000_000) return 14;
            return 15;
        }
    }
}