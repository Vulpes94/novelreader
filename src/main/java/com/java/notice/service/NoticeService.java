package com.java.notice.service;

import org.springframework.web.servlet.ModelAndView;

public interface NoticeService {
	public void notice(ModelAndView mav);
	
	public void noticeWriteOk(ModelAndView mav);
	
	public void noticeView(ModelAndView mav);
	
	public void noticeDelete(ModelAndView mav);

	public void question(ModelAndView mav);
	
	public void questionWrite(ModelAndView mav);
	
	public void questionWriteOk(ModelAndView mav);
	
	public void questionReplyWriteOk(ModelAndView mav);
	
	public void questionView(ModelAndView mav);	
}
