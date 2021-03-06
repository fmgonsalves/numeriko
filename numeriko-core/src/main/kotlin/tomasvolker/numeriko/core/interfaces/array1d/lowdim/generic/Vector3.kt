package tomasvolker.numeriko.core.interfaces.array1d.lowdim.generic

import tomasvolker.numeriko.core.interfaces.array1d.generic.Array1D

interface Vector3<out T>: Array1D<T> {

    override val size: Int get() = 3

}