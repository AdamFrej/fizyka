package affinemaths

import affinemaths.NormalizationHelper.Companion.normalize
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NormalizationHelperTest {

    @ParameterizedTest
    @ValueSource(ints = [-5, -2, 0, 1, 12])
    fun normalizeLong(initialExponent: Int) {
        assertEquals(Scalar(12345, 4 + initialExponent), normalize(123456789, initialExponent))
        assertEquals(Scalar(12345, 3 + initialExponent), normalize(12345678, initialExponent))
        assertEquals(Scalar(12345, 2 + initialExponent), normalize(1234567, initialExponent))
        assertEquals(Scalar(12345, 1 + initialExponent), normalize(123456, initialExponent))

        assertEquals(Scalar(12345, 0 + initialExponent), normalize(12345, initialExponent))

        assertEquals(Scalar(12340, -1 + initialExponent), normalize(1234, initialExponent))
        assertEquals(Scalar(12300, -2 + initialExponent), normalize(123, initialExponent))
        assertEquals(Scalar(12000, -3 + initialExponent), normalize(12, initialExponent))
        assertEquals(Scalar(10000, -4 + initialExponent), normalize(1, initialExponent))
    }

    @ParameterizedTest
    @ValueSource(ints = [-13, -3, 0, 1, 6])
    fun normalizeDouble(initialExponent: Int) {
        assertEquals(Scalar(12345, -5 + initialExponent), normalize(0.0000123456789, initialExponent))
        assertEquals(Scalar(12345, -4 + initialExponent), normalize(0.000123456789, initialExponent))
        assertEquals(Scalar(12345, -3 + initialExponent), normalize(0.00123456789, initialExponent))
        assertEquals(Scalar(12345, -2 + initialExponent), normalize(0.0123456789, initialExponent))
        assertEquals(Scalar(12345, -1 + initialExponent), normalize(0.123456789, initialExponent))

        assertEquals(Scalar(12345, 0 + initialExponent), normalize(1.23456789, initialExponent))

        assertEquals(Scalar(12345, 1 + initialExponent), normalize(12.3456789, initialExponent))
        assertEquals(Scalar(12345, 2 + initialExponent), normalize(123.456789, initialExponent))
        assertEquals(Scalar(12345, 3 + initialExponent), normalize(1234.56789, initialExponent))
        assertEquals(Scalar(12345, 4 + initialExponent), normalize(12345.6789, initialExponent))
        assertEquals(Scalar(12345, 5 + initialExponent), normalize(123456.789, initialExponent))
        assertEquals(Scalar(12345, 6 + initialExponent), normalize(1234567.89, initialExponent))
        assertEquals(Scalar(12345, 7 + initialExponent), normalize(12345678.9, initialExponent))
        assertEquals(Scalar(12345, 8 + initialExponent), normalize(123456789.0, initialExponent))
    }
    @ParameterizedTest
    @ValueSource(strings = ["-5","-2","0","1","8"])
    fun normalizeString(initialExponent: String) {

        assertEquals(Scalar(10000,initialExponent.toInt()), normalize("1", initialExponent))

        assertEquals(Scalar(12000, 1 + initialExponent.toInt()), normalize("12", initialExponent))
        assertEquals(Scalar(12300, 2 + initialExponent.toInt()), normalize("123", initialExponent))
        assertEquals(Scalar(12340, 3 + initialExponent.toInt()), normalize("1234", initialExponent))
        assertEquals(Scalar(12345, 4 + initialExponent.toInt()), normalize("12345", initialExponent))
        assertEquals(Scalar(12345, 5 + initialExponent.toInt()), normalize("123456", initialExponent))

        assertEquals(Scalar(12000, 1 + initialExponent.toInt()), normalize("12.0", initialExponent))
        assertEquals(Scalar(12300, 2 + initialExponent.toInt()), normalize("123.0", initialExponent))
        assertEquals(Scalar(12340, 3 + initialExponent.toInt()), normalize("1234.0", initialExponent))
        assertEquals(Scalar(12345, 4 + initialExponent.toInt()), normalize("12345.0", initialExponent))
        assertEquals(Scalar(12345, 5 + initialExponent.toInt()), normalize("123456.0", initialExponent))

        assertEquals(Scalar(12345, -5 + initialExponent.toInt()), normalize("0.000012345", initialExponent))
        assertEquals(Scalar(12345, -4 + initialExponent.toInt()), normalize("0.00012345", initialExponent))
        assertEquals(Scalar(12345, -3 + initialExponent.toInt()), normalize("0.0012345", initialExponent))
        assertEquals(Scalar(12345, -2 + initialExponent.toInt()), normalize("0.012345", initialExponent))
        assertEquals(Scalar(12345, -1 + initialExponent.toInt()), normalize("0.12345", initialExponent))

        assertEquals(Scalar(12345, 0 + initialExponent.toInt()), normalize("1.2345", initialExponent))

        assertEquals(Scalar(12345, 1 + initialExponent.toInt()), normalize("12.345", initialExponent))
        assertEquals(Scalar(12345, 2 + initialExponent.toInt()), normalize("123.45", initialExponent))
        assertEquals(Scalar(12345, 3 + initialExponent.toInt()), normalize("1234.5", initialExponent))
        assertEquals(Scalar(12345, 4 + initialExponent.toInt()), normalize("12345.0", initialExponent))
        assertEquals(Scalar(12345, 5 + initialExponent.toInt()), normalize("123456.0", initialExponent))
    }

    @Test
    fun normalizeStringSpecialCases() {
        assertEquals(Scalar(77000, 0), normalize("77", "-1"))

    }
}