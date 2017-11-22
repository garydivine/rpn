package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AbsoluterOfOneNumber implements Godoer, Undoer{
	
	Stack<Double> stack = new Stack<Double>();
	private OneArgumentUndoer undoer;
	
	public AbsoluterOfOneNumber(Stack<Double> stack) {
		this.stack = stack;
	}
	
	public void goDoIt() {
		double firstNumber = stack.pop();
		stack.push(Math.abs(firstNumber));
		
		undoer = new OneArgumentUndoer(firstNumber);
	}

	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);
	}
}
