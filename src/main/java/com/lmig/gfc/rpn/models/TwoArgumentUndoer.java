package com.lmig.gfc.rpn.models;

import java.util.Stack;

// has the same stuff as OneArgumentUndoer plus some extra stuff

public class TwoArgumentUndoer extends OneArgumentUndoer {
	
	private double second;
	
	public TwoArgumentUndoer (double first, double second) {
		super(first);
		this.second = second;
	}
	
	public void undo (Stack<Double> stack) {
		stack.pop();
		parentUndo(stack);
	}
	
	protected void parentUndo(Stack<Double> stack) {  // need to do this in case we need like a 3 argument undoer
		stack.push(second);
		super.parentUndo(stack);
	}
}
