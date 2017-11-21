package com.lmig.gfc.rpn.models;

import java.util.Stack;

public interface Undoer {  // created this to enforce
	
	public void undo(Stack<Double> stack);
	
}
