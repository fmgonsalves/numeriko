package tomasvolker.numeriko.core.interfaces.int.array1d

import tomasvolker.numeriko.core.index.Index
import tomasvolker.numeriko.core.index.IndexProgression
import tomasvolker.numeriko.core.interfaces.generic.arraynd.ReadOnlyArrayND
import tomasvolker.numeriko.core.interfaces.int.arraynd.IntArrayND
import tomasvolker.numeriko.core.interfaces.numeric.array1d.NumericArray1D

interface IntArray1D: ReadOnlyIntArray1D, NumericArray1D<Int>, IntArrayND {

    override fun getView(vararg indices: Any): IntArray1D {
        require(indices.size <= 1) { "${indices.size} indices provided, expected 1 or less" }
        TODO("implement")
    }

    override fun setValue(value: ReadOnlyArrayND<Int>, vararg indices: Any) {
        getView(*indices).setAll { value.getValue(it) }
    }

    override fun copy(): IntArray1D

    override fun getView(i0: IntProgression): IntArray1D

    override fun getView(i0: IndexProgression): IntArray1D

    override fun setValue(value: Int, i0: Int) = setInt(value, i0)

    override fun setValue(value: Int, i0: Index) = setInt(value, i0)

    //TODO see if inherit this
    override fun setDouble(value: Double, i0: Int) =
            setInt(value.toInt(), i0)

    //TODO see if inherit this
    override fun setDouble(value: Double, i0: Index) =
            setInt(value.toInt(), i0)

}