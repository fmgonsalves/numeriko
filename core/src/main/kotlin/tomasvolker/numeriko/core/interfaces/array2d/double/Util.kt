package tomasvolker.numeriko.core.interfaces.array2d.double


import tomasvolker.numeriko.core.interfaces.array2d.generic.forEachIndex
import tomasvolker.numeriko.core.interfaces.factory.mutableDoubleZeros
import tomasvolker.numeriko.core.preconditions.requireSameShape

inline fun elementWise(source: DoubleArray2D, destination: MutableDoubleArray2D, operation: (Double) -> Double) {
    requireSameShape(source, destination)
    source.forEachIndex { i0, i1 ->
            destination[i0, i1] = operation(source[i0, i1])
    }
}

inline fun elementWise(
        source1: DoubleArray2D,
        source2: DoubleArray2D,
        destination: MutableDoubleArray2D,
        operation: (Double, Double) -> Double
) {
    requireSameShape(source1, source2)
    requireSameShape(source1, destination)
    source1.forEachIndex { i0, i1 ->
        destination[i0, i1] = operation(source1[i0, i1], source2[i0, i1])
    }
}

inline fun DoubleArray2D.elementWise(operation: (Double) -> Double): DoubleArray2D {
    val result = mutableDoubleZeros(shape0, shape1)
    elementWise(
            source = this,
            destination = result,
            operation = operation
    )
    return result
}

inline fun MutableDoubleArray2D.applyElementWise(operation: (Double) -> Double): MutableDoubleArray2D {
    elementWise(
            source = this,
            destination = this,
            operation = operation
    )
    return this
}

inline fun elementWise(array1: DoubleArray2D, array2: DoubleArray2D, operation: (Double, Double) -> Double): DoubleArray2D {
    requireSameShape(array1, array2)

    val result = mutableDoubleZeros(array1.shape0, array2.shape1)
    elementWise(
            source1 = array1,
            source2 = array2,
            destination = result,
            operation = operation
    )
    return result

}

inline fun MutableDoubleArray2D.applyElementWise(
        other: DoubleArray2D,
        operation: (Double, Double) -> Double
): MutableDoubleArray2D {
    requireSameShape(this, other)
    elementWise(
            source1 = this,
            source2 = other,
            destination = this,
            operation = operation
    )
    return this
}

inline fun DoubleArray2D.sumBy(operation: (Double) -> Double): Double {
    var result = 0.0
    forEachIndex { i0, i1 ->
        result += operation(this[i0, i1])
    }
    return result
}