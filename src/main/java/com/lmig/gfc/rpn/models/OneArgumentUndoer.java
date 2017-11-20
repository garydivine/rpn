package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class OneArgumentUndoer {
	
	private double oldValue;
	
	public OneArgumentUndoer (double oldValue) {
		this.oldValue = oldValue;
	}
	
	public void undo (Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
		
	}
	
	protected void parentUndo (Stack<Double> stack) {
		stack.push(oldValue);
	}

}
