package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class DivideTwoNumbers extends TwoNumberCalculation { // also inherits Undoer

	public DivideTwoNumbers(Stack<Double> stack) {
		super(stack);
		
	}

	@Override
	protected double doMath(double first, double second) {
		return second / first;
	}

}
