package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AbsoluterOfOneNumber;
import com.lmig.gfc.rpn.models.AddTwoNumbers;
import com.lmig.gfc.rpn.models.DivideTwoNumbers;
import com.lmig.gfc.rpn.models.ExponentTwoNumbers;
import com.lmig.gfc.rpn.models.Godoer;
import com.lmig.gfc.rpn.models.MultiplyTwoNumbers;
import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.PushAction;
import com.lmig.gfc.rpn.models.SubtractTwoNumbers;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoNumberCalculation;
import com.lmig.gfc.rpn.models.Undoer;

@Controller
public class CalculatorController {

	private Stack<Double> stack;
	private Stack<Godoer> undoers;
	private Stack<Godoer> redoers;

	public CalculatorController() {
		stack = new Stack<Double>();
		undoers = new Stack<Godoer>();
		redoers = new Stack<Godoer>();
	}

	@GetMapping("/") // this is more specific than @RequestMapping, this method will just get the
						// default page
	public ModelAndView showApp() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack); // putting this here because we are redirect back to this @GetMapping
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasOneOrMoreNumbers", stack.size() >= 1);
		mv.addObject("hasUndoer", !undoers.empty());
		mv.addObject("hasRedoer", !redoers.empty());
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {
		PushAction pusher = new PushAction(stack,value);
		return doOperation(pusher);
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {
		
		AddTwoNumbers adder = new AddTwoNumbers(stack);
		return doOperation(adder);
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {
		
		SubtractTwoNumbers subtracter = new SubtractTwoNumbers(stack);
		return doOperation(subtracter);
	}
	
	@PostMapping("/abs")
	public ModelAndView absoluteValue() {
		AbsoluterOfOneNumber absoluter = new AbsoluterOfOneNumber(stack);
		return doOperation(absoluter);
	}
	
	@PostMapping("/divide")
	public ModelAndView divideTwoNumbers() {
		
		DivideTwoNumbers divider = new DivideTwoNumbers(stack);
		return doOperation(divider);
	}
	
	@PostMapping("/multiply")
	public ModelAndView multiplyTwoNumbers() {
		
		MultiplyTwoNumbers multiplier = new MultiplyTwoNumbers(stack);
		return doOperation(multiplier);
	}
	
	@PostMapping("/exponent")
	public ModelAndView exponentTwoNumbers() {
		
		ExponentTwoNumbers expo = new ExponentTwoNumbers(stack);
		return doOperation(expo);
	}
	
	@PostMapping("/undo")
	public ModelAndView undoMath() {
		Godoer undoer = undoers.pop(); // get most recent Undoer..needs to be a Godoer for the redoers.push()
		undoer.undo(stack); // call undo method
		
		redoers.push(undoer);
		
		return redirectToHome();
	}
	
	@PostMapping("/redo")
	public ModelAndView redoMath() {
		Godoer redoer = redoers.pop();
		redoer.goDoIt();
		
		undoers.push(redoer);
		
		return redirectToHome();
	}
	
	private ModelAndView doOperation(Godoer calc) {
		calc.goDoIt();
		undoers.push(calc);
		redoers.clear();
		
		return redirectToHome();
	}
	
	private ModelAndView redirectToHome() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}


} 
