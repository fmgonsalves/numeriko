package tomasvolker.numeriko.legacy.core.interfaces.numeric.arraynd

import tomasvolker.numeriko.legacy.core.interfaces.generic.arraynd.ReadOnlyArrayND
import tomasvolker.numeriko.legacy.core.interfaces.generic.arraynd.ReadOnlyArrayNDViewer
import tomasvolker.numeriko.legacy.core.interfaces.integer.array1d.ReadOnlyIntArray1D

interface ReadOnlyNumericArrayNDViewer<out T: Number>: ReadOnlyArrayNDViewer<T> {

    override val array: ReadOnlyNumericArrayND<T>

    override operator fun get(vararg indices: Any): ReadOnlyNumericArrayND<T> = array.getView(*indices)

}

class DefaultReadOnlyNumericArrayNDViewer<out T: Number>(
        override val array: ReadOnlyNumericArrayND<T>
        ): ReadOnlyNumericArrayNDViewer<T>

interface ReadOnlyNumericArrayND<out T: Number>: ReadOnlyArrayND<T> {

    override val view: ReadOnlyNumericArrayNDViewer<T> get() = DefaultReadOnlyNumericArrayNDViewer(this)

    override fun copy(): ReadOnlyNumericArrayND<T>

    override fun getView(vararg indices: Any): ReadOnlyNumericArrayND<T>

    fun getInt(vararg indices:Int): Int

    fun getInt(indexArray: ReadOnlyIntArray1D): Int

    fun getDouble(vararg indices:Int): Double

    fun getDouble(indexArray: ReadOnlyIntArray1D): Double

}

