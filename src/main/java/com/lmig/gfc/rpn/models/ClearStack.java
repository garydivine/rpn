package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class ClearStack implements Godoer, Undoer{
	
	Stack<Double> stack = new Stack<Double>();
	Stack<Double> initialEntryStack = new Stack<Double>();
	
	
	public ClearStack(Stack<Double> stack) {
		this.stack = stack;
	}
	
	
	@Override
	public void goDoIt() {
		initialEntryStack.addAll(stack);
		stack.clear(); 
	}

	@Override
	public void undo(Stack<Double> stack) {
		stack.addAll(initialEntryStack);
		initialEntryStack.clear();
	}
	

}
