package tomasvolker.numeriko.sandbox.tpsti

import org.ejml.dense.row.factory.DecompositionFactory_DDRM
import tomasvolker.numeriko.core.interfaces.array1d.double.DoubleArray1D
import tomasvolker.numeriko.core.interfaces.array1d.double.times
import tomasvolker.numeriko.core.interfaces.array2d.double.DoubleArray2D
import tomasvolker.numeriko.core.interfaces.array2d.generic.indices0
import tomasvolker.numeriko.core.interfaces.factory.doubleArray1D
import tomasvolker.numeriko.core.interfaces.factory.doubleArray2D
import tomasvolker.numeriko.core.interfaces.factory.doubleDiagonal
import tomasvolker.numeriko.core.primitives.squared
import kotlin.math.abs
import kotlin.math.tan
import kotlin.math.tanh


data class EigenVector(
        val value: Double,
        val vector: DoubleArray1D
)

class EigenDecomposition(
        val eigenVectorList: List<EigenVector>
) {

    val size get() = eigenVectorList.size

    fun diagonal(): DoubleArray2D = doubleDiagonal(size) { i -> eigenVectorList[i].value }

    val eigenValues: DoubleArray1D get() =
        doubleArray1D(size) { i -> eigenVectorList[i].value }

    fun vectorMatrix(): DoubleArray2D = doubleArray2D(size, size) { i0, i1 ->
        eigenVectorList[i1].vector[i0]
    }

    operator fun component1(): DoubleArray2D = vectorMatrix()
    operator fun component2(): DoubleArray2D = diagonal()

    override fun toString(): String =
            "EigenDecomposition(eigenVectorList = $eigenVectorList)"

}


fun DoubleArray2D.eigenDecomposition(symetric: Boolean = false): EigenDecomposition {
    val decomposition = DecompositionFactory_DDRM.eig(shape0, true, symetric)

    if (!decomposition.decompose(toEjmlMatrix()))
        throw RuntimeException("Could not decompose")

    return EigenDecomposition(
            indices0.map { i ->
                EigenVector(
                        value = decomposition.getEigenvalue(i).real,
                        vector = decomposition.getEigenVector(i).toDoubleArray1D()
                )
            }
    )
}


fun SignalEnsemble.retrieveIndependentComponent(
        seed: DoubleArray1D = doubleArray1D(channelCount) { javaRandom.nextGaussian() },
        iterations: Int = 1000
): DoubleArray1D {

    var w = seed

    fun g(x: Double): Double = tanh(x)
    fun gPrime(x: Double): Double = sech(x).squared()

    repeat(iterations) {
        w = meanVector { x -> x * g(w inner x) } - meanValue { x -> gPrime(w inner x) } * w
        w /= w.norm2()
    }

    return w
}

fun SignalEnsemble.independentComponentAnalysis(): List<DoubleArray1D> {

    val components = mutableListOf<DoubleArray1D>()

    while (components.size < channelCount) {
        val newVal = retrieveIndependentComponent(iterations = 100)
        println("New independent component: $newVal")
        if (components.all { abs(it colinearityFactor newVal) < 0.5 })
            components.add(newVal)
    }

    return components
}


fun List<DoubleArray1D>.orthogonalize(): List<DoubleArray1D> {

    val result = mutableListOf(this.first().normalized())

    this.drop(1).forEach { vector ->
        result.add(
                (vector - sumVector(result.indices) { i -> (vector colinearityFactor result[i]) * result[i] }).normalized()
        )

    }

    return result
}
