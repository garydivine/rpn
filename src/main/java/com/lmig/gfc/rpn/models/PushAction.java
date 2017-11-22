package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class PushAction implements Godoer, Undoer{
	
	private Stack<Double> stack = new Stack<Double>();
	private double valueToPush;
	

	public PushAction(Stack<Double> stack, double valueToPush) {
		this.stack = stack;
		this.valueToPush = valueToPush;
	}
	
	@Override  
	public void undo(Stack<Double> stack) {
		stack.pop();	
	}

	@Override
	public void goDoIt() {
		stack.push(valueToPush);
	}

}
