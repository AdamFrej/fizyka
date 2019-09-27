package affinemaths

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class VectorTest {

    @Test
    fun plus() {
        assertEquals(Vector("123","321"), Vector("123","321") plus Vector.ZERO)
        assertEquals(Vector("123","321"), Vector.ZERO plus Vector("123","321"))
        assertEquals(Vector("123","321"), Vector("100","300") plus Vector("23","21"))
        assertEquals(Vector("-77","-321"), Vector("-100","-300") plus Vector("23","-21"))
    }

    @Test
    fun minus() {
        assertEquals(Vector("123","321"), Vector("123","321") minus Vector.ZERO)
        assertEquals(Vector("123","321"), Vector("123.45","321") minus Vector("0.45", "0"))
        assertEquals(Vector("-123","-321"), Vector.ZERO minus Vector("123","321"))
    }

    @Test
    fun times() {
        assertEquals(Vector("0","0"), Vector("123","321") times Scalar.ZERO)
        assertEquals(Vector("1.353","3.531"), Vector("123","321") times Scalar("11", "-3"))
        assertEquals(Vector("-1353000","-3531000"), Vector("123","321") times Scalar("-11", "3"))
    }

    @Test
    fun over() {
        assertEquals(Vector("6150","16050"), Vector("123","321") over Scalar("20", "-3"))
        assertEquals(Vector("-0.0041","-0.0107"), Vector("123","321") over Scalar("-30", "3"))
    }
}