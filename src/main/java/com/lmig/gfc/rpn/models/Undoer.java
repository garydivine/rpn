package com.lmig.gfc.rpn.models;

import java.util.Stack;

public interface Undoer {  // created this to enforce that anything that implements Undoer must have an undo method
	
	// should not ever have fields in an interface...not best practice
	
	public void undo(Stack<Double> stack);
	
}
