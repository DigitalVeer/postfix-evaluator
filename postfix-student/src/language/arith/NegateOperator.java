package language.arith;

import language.Operand;

public class NegateOperator extends UnaryOperator<Integer> {

	@Override
	public Operand<Integer> performOperation() {
		if (op0 == null)
			throw new IllegalStateException("Could not perform operation prior to operand set.");
		Integer result = op0.getValue() * -1;
		return new Operand<Integer>(result);
	}
	public String toString(){
		return ("~");
	}
}