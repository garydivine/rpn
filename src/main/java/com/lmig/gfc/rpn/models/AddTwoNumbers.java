package com.lmig.gfc.rpn.models;

import java.util.Stack;

public class AddTwoNumbers extends TwoNumberCalculation {
	
	public AddTwoNumbers (Stack<Double> stack) {
		super(stack);
	}
	
	@Override
	protected double doMath(double first, double second) {
		return first + second;
	}

}
