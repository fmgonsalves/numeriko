package tomasvolker.simboliko.function1.functions

import tomasvolker.simboliko.constant.E
import tomasvolker.simboliko.constant.Zero
import tomasvolker.simboliko.expression.Expression
import tomasvolker.simboliko.function1.DifferentiableFunction1
import tomasvolker.simboliko.function1.RealFunction1
import tomasvolker.simboliko.number.RealNumber
import kotlin.math.exp

object Exponential: DifferentiableFunction1 {

    override fun compute(input: Double) = exp(input)

    override fun derivative() = Exponential

    override fun derivativeAt(input: Double) = exp(input)

    override fun toString(input: String) = "exp(${input})"

    override fun toString() = defaultToString()

}

fun exp(input: RealNumber) = when(input) {
    is Zero -> E
    else -> Exponential(input)
}
fun exp(input: Expression<RealNumber>) = Exponential(input)
fun exp(input: RealFunction1) = Exponential(input)
fun exp(input: DifferentiableFunction1) = Exponential(input)
