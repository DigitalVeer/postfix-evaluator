package stack;

/**
 * A {@link LinkedStack} is a stack that is implemented using a Linked List structure
 * to allow for unbounded size.
 *
 * @param <T> the elements stored in the stack
 */
public class LinkedStack<T> implements StackInterface<T> {
	protected int size = 0;
	protected LLNode<T> head;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T pop() throws StackUnderflowException {
		if (!isEmpty()){
			LLNode<T> currN = head;
			head = head.getNext();
			currN.setNext(null);
			size--;
			return currN.getData();
		} else throw new StackUnderflowException("Empty stack!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public T top() throws StackUnderflowException {
		if (!isEmpty())
			return head.getData();
		else 
			throw new StackUnderflowException("Empty!");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int size() {
		return this.size;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void push(T elem) {
		LLNode<T> newNode = new LLNode<T>(elem);
		newNode.setNext(head);
		head = newNode;
		size++;
	}

}
