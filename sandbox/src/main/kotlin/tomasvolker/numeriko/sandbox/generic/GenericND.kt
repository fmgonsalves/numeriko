package tomasvolker.numeriko.sandbox.generic

import tomasvolker.numeriko.core.dsl.D
import tomasvolker.numeriko.core.index.*
import tomasvolker.numeriko.core.interfaces.arraynd.double.DoubleArrayND
import tomasvolker.numeriko.core.interfaces.arraynd.double.MutableDoubleArrayND
import tomasvolker.numeriko.core.interfaces.arraynd.generic.ArrayND
import tomasvolker.numeriko.core.interfaces.arraynd.generic.MutableArrayND
import tomasvolker.numeriko.core.interfaces.factory.doubleArray2D
import tomasvolker.numeriko.core.interfaces.factory.doubleArrayND

fun Array<out Any>.convertToIndicesProgression(): Array<IndexProgression> =
        map { index ->
            when (index) {
                is Int -> (index..index).toIndexProgression()
                is Index -> (index..index)
                is IntProgression -> index.toIndexProgression()
                is IndexProgression -> index
                else -> throw IllegalArgumentException("index is not of type Int, Index, IntProgression or IndexProgression")
            }
        }.toTypedArray()

operator fun <T> ArrayND<T>.get(vararg indices: Int): T =
        getValue(*indices)

operator fun <T> ArrayND<T>.get(vararg indices: Any): ArrayND<T> =
        getView(*indices.convertToIndicesProgression())

operator fun <T> MutableArrayND<T>.set(vararg indices: Any, array: ArrayND<T>) =
        setView(array, *indices.convertToIndicesProgression())

operator fun DoubleArrayND.get(vararg indices: Int): Double = getDouble(*indices)

operator fun DoubleArrayND.get(vararg indices: Any): DoubleArrayND =
        getView(*indices.convertToIndicesProgression())

operator fun MutableDoubleArrayND.set(vararg indices: Any, array: DoubleArrayND) =
        setView(array, *indices.convertToIndicesProgression())

fun main() {

    val array = doubleArrayND(4, 3, 2) { (i0, i1, i2) -> (i0 + i1 - i2).toDouble() }

    val matrix = doubleArray2D(4, 4) { i0, i1 -> i0 * i1 + 1.0 }



}