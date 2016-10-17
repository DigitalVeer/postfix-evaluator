package evaluator.arith;

import language.Operand;
import language.Operator;
import parser.IllegalPostfixExpressionException;
import parser.PostfixParser.Type;
import parser.Token;
import parser.arith.ArithPostfixParser;
import stack.LinkedStack;
import stack.StackInterface;
import evaluator.PostfixEvaluator;

/**
 * An {@link ArithPostfixEvaluator} is a postfix evaluator over simple arithmetic expressions.
 *
 */
public class ArithPostfixEvaluator implements PostfixEvaluator<Integer> {

	private final StackInterface<Operand<Integer>> stack;
	
	/**
	 * Constructs an {@link ArithPostfixEvaluator}
	 */
	public ArithPostfixEvaluator(){
		stack = new LinkedStack<Operand<Integer>>();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Integer evaluate(String expr) throws IllegalPostfixExpressionException {
		ArithPostfixParser parser = new ArithPostfixParser(expr);
		for (Token<Integer> token : parser) {
			Type type = token.getType();
			switch(type){ 
			case OPERAND:
				if (token.getOperand() == null)
					throw new IllegalPostfixExpressionException("Null operand");
				stack.push(token.getOperand());
				break;
			case OPERATOR:
				Operator<Integer> oper = token.getOperator();
				if (oper== null)
					throw new IllegalPostfixExpressionException("Null operator");
				if (oper.toString() == "~")
					oper.setOperand(0, stack.pop());
				else {
					oper.setOperand(1, stack.pop());
					oper.setOperand(0, stack.pop());
				}
				stack.push(oper.performOperation());
				break;
			default:
				throw new IllegalStateException("Parser returned an invalid Type: " + type);
			}						
		}		
		Integer latestValue = new Integer(stack.pop().getValue());
		if (stack.size() != 0)
			throw new IllegalPostfixExpressionException();
		while (!stack.isEmpty())
			stack.pop();
		return latestValue;
	}

}
