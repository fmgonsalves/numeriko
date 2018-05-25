package tomasvolker.simboliko.function2.operators

import tomasvolker.simboliko.constant.One
import tomasvolker.simboliko.constant.Zero
import tomasvolker.simboliko.expression.Expression
import tomasvolker.simboliko.function2.DifferentiableFunction2
import tomasvolker.simboliko.function2.defaultToString
import tomasvolker.simboliko.function2.differentiableFunction2

object Multiplication: DifferentiableFunction2 {

    override fun compute(input1: Double, input2: Double) = input1 * input2

    override fun derivative1() = differentiableFunction2 { x1, x2 -> x2 }

    override fun derivative2() = differentiableFunction2 { x1, x2 -> x1 }

    override fun toString(input1: String, input2: String) = "$input1 * $input2"

    override fun toString() = defaultToString()

    override fun simplifyInvoke(input1: Expression, input2: Expression) =
            super.simplifyInvoke(input1, input2) ?:
            when {
                input1 is Zero || input2 is Zero -> Zero
                input1 is One -> input2
                input2 is One -> input1
                else -> null
            }

}