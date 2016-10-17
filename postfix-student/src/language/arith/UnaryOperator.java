package language.arith;

import language.Operand;
import language.Operator;

public abstract class UnaryOperator<T> implements Operator<T> {
	protected Operand<T> op0;
	
	public final int getNumberOfArguments(){
		return 1;
	}
	
	public void setOperand(int position, Operand<T> operand){
		if (position > 0)
			throw new IllegalArgumentException("Only one required!");
		if (operand == null)
			throw new NullPointerException("Operand is null!");
		else {
			if (op0 != null)
				throw new IllegalStateException("Operand has already been set!");
			op0 = operand;
		}
	}
	
}
