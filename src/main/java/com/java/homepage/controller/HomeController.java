package com.java.homepage.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.java.homepage.service.HomeService;


@Controller
public class HomeController {
	@Autowired
	private HomeService homeService;
	
	@RequestMapping(value = "/index.do", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		homeService.index(mav);
		return mav;
	 }
	
	@RequestMapping(value = "/novel/top10.do", method = RequestMethod.GET)
	  public ModelAndView top10(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		homeService.top10(mav);
		return mav;
	}
	
	@RequestMapping(value = "/novel/ranking.do", method = RequestMethod.GET)
	  public ModelAndView ranking(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("request", request);
		
		homeService.ranking(mav);
	    return mav;
	}
	
}
