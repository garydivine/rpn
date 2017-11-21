package com.lmig.gfc.rpn.models;

import java.util.Stack;

public abstract class TwoNumberCalculation implements Undoer { // doesnt have enough info to actually do any math by
																// itself, needs to be abstract..no one can call "new"
																// on the class (can't directly instantiate)

	private Stack<Double> stack = new Stack<Double>();
	private Undoer undoer;

	public TwoNumberCalculation(Stack<Double> stack) {
		this.stack = stack;
	}

	public void goDoIt() {
		double firstNumber = stack.pop();
		double secondNumber = stack.pop();
		double result = doMath(firstNumber, secondNumber);
		stack.push(result);

		undoer = new TwoArgumentUndoer(firstNumber, secondNumber);
	}

	protected abstract double doMath(double first, double second); // this is just a placeholder
																	// doesnt need a return anything or have {}
																	// child class must override the method

	@Override
	public void undo(Stack<Double> stack) {
		undoer.undo(stack);
	}

}