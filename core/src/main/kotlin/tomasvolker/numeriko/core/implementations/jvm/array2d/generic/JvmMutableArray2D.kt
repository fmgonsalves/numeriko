package tomasvolker.numeriko.core.implementations.jvm.array2d.generic

import tomasvolker.numeriko.core.interfaces.array2d.generic.*

class JvmMutableArray2D<T>(
        override val shape0: Int,
        override val shape1: Int,
        val data: Array<T>
): MutableArray2D<T> {

    init {
        require(data.size == shape0 * shape1)
    }

    override val size: Int get() = data.size

    override fun setValue(value: T, i0: Int, i1: Int) {
        checkIndices(i0, i1)
        data[i0 * shape0 + i1] = value
    }

    override fun getValue(i0: Int, i1: Int): T {
        checkIndices(i0, i1)
        return data[i0 * shape0 + i1]
    }

    override fun equals(other: Any?): Boolean {
        if (other === this) return true
        if (other !is Array2D<*>) return false
        return defaultEquals(this, other)
    }

    override fun hashCode(): Int = defaultHashCode(this)

    override fun toString(): String = defaultToString(this)

}