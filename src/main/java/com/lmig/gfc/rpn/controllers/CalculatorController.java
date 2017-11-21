package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.AddTwoNumbers;
import com.lmig.gfc.rpn.models.DivideTwoNumbers;
import com.lmig.gfc.rpn.models.ExponentTwoNumbers;
import com.lmig.gfc.rpn.models.MultiplyTwoNumbers;
import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.PushUndoer;
import com.lmig.gfc.rpn.models.SubtractTwoNumbers;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoNumberCalculation;
import com.lmig.gfc.rpn.models.Undoer;

@Controller
public class CalculatorController {

	private Stack<Double> stack;
	private Stack<Undoer> undoers;

	public CalculatorController() {
		stack = new Stack<Double>();
		undoers = new Stack<Undoer>();
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
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {

		stack.push(value);
		
		undoers.push(new PushUndoer());

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
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

		double firstNumber = stack.pop();
		
		undoers.push(new OneArgumentUndoer(firstNumber));
		
		stack.push(Math.abs(firstNumber));

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
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
		
		undoers.pop().undo(stack); // get most recent Undoer and call undo
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	private ModelAndView doOperation(TwoNumberCalculation calc) {
		calc.goDoIt();
		undoers.push(calc);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
		
		
	}


} 
