package tomasvolker.numeriko.core.interfaces.array1d.integer

import tomasvolker.numeriko.core.index.Index
import tomasvolker.numeriko.core.index.IndexProgression
import tomasvolker.numeriko.core.interfaces.array1d.generic.MutableArray1D
import tomasvolker.numeriko.core.interfaces.array1d.generic.indices
import tomasvolker.numeriko.core.interfaces.array1d.integer.view.DefaultMutableIntArray1DView
import tomasvolker.numeriko.core.interfaces.factory.copy
import tomasvolker.numeriko.core.preconditions.requireSameSize

interface MutableIntArray1D: IntArray1D, MutableArray1D<Int> {

    fun setInt(value: Int, i0: Int)

    fun setInt(value: Int, i0: Index) =
            setInt(value, i0.computeValue(size))

    override fun setValue(value: Int, i0: Int) =
            setInt(value, i0)

    fun setValue(other: IntArray1D) {
        requireSameSize(other, this)
        // Anti alias copy
        val copy = other.copy()
        for (i in indices) {
            setInt(copy.getInt(i), i)
        }

    }

    override fun setValue(value: Int) = setInt(value)

    fun setInt(value: Int) {

        for (i in indices) {
            setInt(value, i)
        }

    }

    override fun getView(i0: IntProgression): MutableIntArray1D =
            DefaultMutableIntArray1DView(
                    array = this,
                    offset = i0.first,
                    size = i0.count(),
                    stride = i0.step
            )

    override fun getView(i0: IndexProgression): MutableIntArray1D =
            getView(i0.computeProgression(size))

    fun setView(value: IntArray1D, indexRange: IndexProgression) =
            setView(value, indexRange.computeProgression(size))

    fun setView(value: IntArray1D, indexRange: IntProgression) =
            getView(indexRange).setValue(value)

    override fun setView(value: Int, i0: IndexProgression) =
            setView(value, i0.computeProgression(size))

    override fun setView(value: Int, i0: IntProgression) =
            getView(i0).setInt(value)

    override fun copy(): MutableIntArray1D = copy(this).asMutable()

    override operator fun get(index: IntProgression): MutableIntArray1D = getView(index)
    override operator fun get(index: IndexProgression): MutableIntArray1D = getView(index)

    operator fun set(index: Int, value: Int) = setValue(value, index)
    operator fun set(index: Index, value: Int) = setValue(value, index)

    operator fun set(index: IntProgression, value: Int) = setView(value, index)
    operator fun set(index: IndexProgression, value: Int) = setView(value, index)

    operator fun set(index: IntProgression, value: IntArray1D) = setView(value, index)
    operator fun set(index: IndexProgression, value: IntArray1D) = setView(value, index)

}
