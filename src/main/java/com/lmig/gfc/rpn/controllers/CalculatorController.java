package com.lmig.gfc.rpn.controllers;

import java.util.Stack;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.lmig.gfc.rpn.models.OneArgumentUndoer;
import com.lmig.gfc.rpn.models.TwoArgumentUndoer;

@Controller
public class CalculatorController {

	private Stack<Double> stack; // needs to be Double instead of double because
	private OneArgumentUndoer undoer;

	public CalculatorController() {
		stack = new Stack<Double>();
	}

	@GetMapping("/") // this is more specific than @RequestMapping, this method will just get the
						// default page
	public ModelAndView showApp() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("calculator");
		mv.addObject("stack", stack); // putting this here because we are redirect back to this @GetMapping
		mv.addObject("hasTwoOrMoreNumbers", stack.size() >= 2);
		mv.addObject("hasOneOrMoreNumbers", stack.size() >= 1);
		mv.addObject("hasUndoer", undoer != null);
		return mv;
	}

	@PostMapping("/enter")
	public ModelAndView pushNumberOntoStack(double value) {

		stack.push(value);
		
		undoer = null;

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView addTwoNumbers() {

		double firstNumber = stack.pop();
		double secondNumber = stack.pop();
		
		stack.push(firstNumber + secondNumber);
		
		undoer = new TwoArgumentUndoer(firstNumber, secondNumber);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/minus")
	public ModelAndView subtractTwoNumbers() {

		double firstNumber = stack.pop();
		double secondNumber = stack.pop();
		
		stack.push(secondNumber - firstNumber);
		
		undoer = new TwoArgumentUndoer(firstNumber, secondNumber);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/abs")
	public ModelAndView absoluteValue() {

		double firstNumber = stack.pop();
		
		undoer = new OneArgumentUndoer(firstNumber);
		
		stack.push(Math.abs(firstNumber));

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}
	
	@PostMapping("/undo")
	public ModelAndView undoMath() {
		
		undoer.undo(stack);
		undoer = null;
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:/");
		return mv;
	}


} 
