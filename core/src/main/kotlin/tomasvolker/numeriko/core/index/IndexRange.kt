package tomasvolker.numeriko.core.index

import tomasvolker.numeriko.core.interfaces.integer.ReadOnlyIntNDArray

open class IndexProgression(val first: AbstractIndex, val last: AbstractIndex, val stride: Int = 1) {

    fun computeProgression(shape: ReadOnlyIntNDArray, dimension: Int) =
            IntProgression.fromClosedRange(
                    first.computeValue(shape, dimension),
                    last.computeValue(shape, dimension),
                    stride
            )

}

operator fun Int.rangeTo(index: AbstractIndex) =
        IndexProgression(this.toIndex(), index)

object All: IndexProgression(0.toIndex(), Last)

infix fun IndexProgression.step(step: Int) = IndexProgression(first, last, step)
