package tomasvolker.numeriko.core.interfaces.array1d.integer.view

import tomasvolker.numeriko.core.interfaces.array1d.generic.lastIndex
import tomasvolker.numeriko.core.interfaces.array1d.integer.MutableIntArray1D

class DefaultMutableIntArray1DView (
        val array: MutableIntArray1D,
        val offset: Int,
        override val size: Int,
        val stride: Int
) : DefaultMutableIntArray1D() {

    init {
        array.requireValidIndices(convertIndex(0))
        array.requireValidIndices(convertIndex(lastIndex))
    }

    override fun setInt(value: Int, index: Int) {
        requireValidIndices(index)
        array.setInt(value, offset + stride * index)
    }

    override fun getInt(index: Int): Int {
        requireValidIndices(index)
        return array.getInt(offset + stride * index)
    }

    fun convertIndex(i0: Int): Int = offset + stride * i0

}

fun defaultIntArray1DView(array: MutableIntArray1D, i0: IntProgression) =
        DefaultMutableIntArray1DView(
                array = array,
                offset = i0.first,
                size = i0.count(),
                stride = i0.step
        )
