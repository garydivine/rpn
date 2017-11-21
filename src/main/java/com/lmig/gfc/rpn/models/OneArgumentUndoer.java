package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer implements Undoer {
	
	private double oldValue;
	
	public OneArgumentUndoer (double oldValue) {
		this.oldValue = oldValue;
	}
	
	@Override
	public void undo (Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
		
	}
	
	protected void parentUndo (Stack<Double> stack) {
		stack.push(oldValue);
	}

}
