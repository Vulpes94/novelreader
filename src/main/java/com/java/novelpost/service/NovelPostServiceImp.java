package com.java.novelpost.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.novelpost.dao.NovelPostDao;
import com.java.novelpost.dto.NovelPostDto;

@Component
public class NovelPostServiceImp implements NovelPostService {
	@Autowired
	private NovelPostDao novelPostDao;

	@Override
	public void novelPostRegisterOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		NovelPostDto novelPostDto = (NovelPostDto) map.get("novelPostDto");
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int n_num = Integer.parseInt(request.getParameter("n_num"));

		LogAspect.logger.info(LogAspect.LogMsg + novelPostDto.toString());
		int check = novelPostDao.novelPostInsert(novelPostDto);
		LogAspect.logger.info(LogAspect.LogMsg + check);
//		if (ch > 0) {
//			int check = novelPostDao.novelPostSelect(n_num);
//			LogAspect.logger.info(LogAspect.LogMsg + check);
//			
//			mav.addObject("check", check);
//		}

		mav.addObject("check", check);
		mav.addObject("n_num", n_num);
		mav.setViewName("novelpost/registerOk");
	}

	@Override
	public void novelPostRead(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		int n_post_num = Integer.parseInt(request.getParameter("n_post_num"));
		LogAspect.logger.info(LogAspect.LogMsg + n_post_num);
		
		NovelPostDto novelPostDto = novelPostDao.read(n_post_num);

		mav.addObject("novelPostDto", novelPostDto);
		mav.addObject("n_post_num", n_post_num);
		mav.setViewName("novelpost/read.tiles");
	}

	@Override
	public void novelPostDelete(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int n_num = Integer.parseInt(request.getParameter("n_num"));
		LogAspect.logger.info(LogAspect.LogMsg + "n_num=" + n_num);
		
		int n_post_num = Integer.parseInt(request.getParameter("n_post_num"));
		LogAspect.logger.info(LogAspect.LogMsg + n_post_num);
		
		int check = novelPostDao.delete(n_post_num);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		mav.addObject("check", check);
		mav.addObject("n_num", n_num);
		mav.setViewName("novelpost/delete.tiles");
		
	}

	@Override
	public void novelPostUpdate(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");

		int n_post_num = Integer.parseInt(request.getParameter("n_post_num"));
		NovelPostDto novelPostDto = novelPostDao.novelPostSelect(n_post_num);
		
		LogAspect.logger.info(LogAspect.LogMsg + novelPostDto.toString());

		mav.addObject("novelPostDto", novelPostDto);
		mav.addObject("n_post_num", n_post_num);
		mav.setViewName("novelpost/update.tiles");
	}

	@Override
	public void novelPostUpdateOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		NovelPostDto novelPostDto = (NovelPostDto) map.get("novelPostDto");
		
		int n_post_num = Integer.parseInt(request.getParameter("n_post_num"));
		LogAspect.logger.info(LogAspect.LogMsg + n_post_num);
		
		novelPostDto.setN_POST_NUM(n_post_num);
		
		
		int n_num = Integer.parseInt(request.getParameter("n_num"));
		LogAspect.logger.info(LogAspect.LogMsg + "n_num=" + n_num);
		
		int check = novelPostDao.novelPostUpdate(novelPostDto);
		LogAspect.logger.info(LogAspect.LogMsg + "check="+ check);
		LogAspect.logger.info(LogAspect.LogMsg + novelPostDto.toString());
		
		mav.addObject("check", check);
		mav.addObject("n_num", n_num);
		mav.setViewName("novelpost/updateOk.tiles");
	}

}
