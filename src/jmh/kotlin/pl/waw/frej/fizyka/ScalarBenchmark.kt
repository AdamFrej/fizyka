package pl.waw.frej.fizyka

import affinemaths.Scalar
import org.openjdk.jmh.annotations.*

@BenchmarkMode(Mode.All)
@Warmup(iterations = 2)
@Measurement(iterations = 2, batchSize = 10)
open class ScalarBenchmark {

    @Benchmark
    fun testScalarAddition() {
        Scalar("5.23423") plus Scalar("4.324324")
    }
}