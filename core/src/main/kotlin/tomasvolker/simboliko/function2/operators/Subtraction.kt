package tomasvolker.simboliko.function2.operators

import tomasvolker.simboliko.constant.MinusOne
import tomasvolker.simboliko.constant.One
import tomasvolker.simboliko.constant.Zero
import tomasvolker.simboliko.expression.RealExpression
import tomasvolker.simboliko.function2.DifferentiableFunction2
import tomasvolker.simboliko.function2.defaultToString

object Subtraction: DifferentiableFunction2 {

    override fun compute(input1: Double, input2: Double) = input1 - input2

    override fun derivative1() = One

    override fun derivative2() = MinusOne

    override fun toString(input1: String, input2: String) = "$input1 - ($input2)"

    override fun toString() = defaultToString()

    override fun simplifyInvoke(input1: RealExpression, input2: RealExpression) =
            super.simplifyInvoke(input1, input2) ?:
            when {
                input1 is Zero -> -input2
                input2 is Zero -> input1
                else -> null
            }

}
