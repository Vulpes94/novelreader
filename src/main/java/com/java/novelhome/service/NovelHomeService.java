package com.java.novelhome.service;

import org.springframework.web.servlet.ModelAndView;

public interface NovelHomeService {

	public void novelhomeUploadOk(ModelAndView mav);

	public void novelHomeList(ModelAndView mav);

	public void search(ModelAndView mav);

	public void novelhomeUpload(ModelAndView mav);

	public void category(ModelAndView mav);

	public void novelHomeDelete(ModelAndView mav);

	public void novelHomeAllDelete(ModelAndView mav);

}
