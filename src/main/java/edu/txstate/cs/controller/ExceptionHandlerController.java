package edu.txstate.cs.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ExceptionHandlerController implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		ex.printStackTrace();
		ModelAndView mv = new ModelAndView("error");
		mv.addObject("msg", "Hmmmmm What did you do?!n");
		return mv;
	}

}
