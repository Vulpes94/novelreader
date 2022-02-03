package com.java.homepage.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request, HttpServletResponse response,Locale locale) {

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);

		request.setAttribute("serverTime", formattedDate);
	    return new ModelAndView("index.tiles");
	 }
	
	@RequestMapping(value = "/novel/top10.do", method = RequestMethod.GET)
	  public ModelAndView top10(HttpServletRequest request, HttpServletResponse response) {
	    return new ModelAndView("home/top10.tiles");
	}
	
	@RequestMapping(value = "/novel/ranking.do", method = RequestMethod.GET)
	  public ModelAndView ranking(HttpServletRequest request, HttpServletResponse response) {
	    return new ModelAndView("home/ranking.tiles");
	}
	
}