package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class SwitchTwoNumbers implements Godoer{  // Godoer is also an Undoer
	
	private Stack<Double> stack = new Stack<Double>();
	
	public SwitchTwoNumbers(Stack<Double> stack) {
		this.stack = stack;
	}
	
	@Override
	public void goDoIt() {
		swap(stack);
	}

	@Override
	public void undo(Stack<Double> undoStack) {
		swap(undoStack);
	}

	private void swap(Stack<Double> stack) {
		double firstNumberOffStack = stack.pop();
		double secondNumberOffStack = stack.pop();
		stack.push(firstNumberOffStack);
		stack.push(secondNumberOffStack);
	}

}
