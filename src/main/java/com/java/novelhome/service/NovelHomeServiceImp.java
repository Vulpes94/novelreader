package com.java.novelhome.service;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.LogAspect;
import com.java.novelhome.dao.NovelHomeDao;
import com.java.novelhome.dto.NovelHomeDto;

@Component
public class NovelHomeServiceImp implements NovelHomeService {
	
	@Autowired
	private NovelHomeDao novelHomeDao;

	@Override
	public void novelhomeUploadOk(ModelAndView mav) {
		Map<String, Object> map = mav.getModelMap();
		NovelHomeDto novelHomeDto = (NovelHomeDto) map.get("novelHomeDto");		
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) map.get("request");
//		LogAspect.logger.info(LogAspect.LogMsg + novelHomeDto);
	
		
		
		MultipartFile upFile = request.getFile("file");
		LogAspect.logger.info(LogAspect.LogMsg + upFile);
		
		if (upFile.getSize() != 0) {
			String fileName = Long.toString(System.currentTimeMillis()) + "_" + upFile.getOriginalFilename();
			long fileSize = upFile.getSize();
			LogAspect.logger.info(LogAspect.LogMsg + fileName + fileSize);

			File path = new File("C:\\pds\\");
			path.mkdir();

			if (path.exists() && path.isDirectory()) {
				File file = new File(path, fileName);

				try {
					upFile.transferTo(file);

					novelHomeDto.setN_IMAGE_PATH(file.getAbsolutePath());
					novelHomeDto.setN_IMAGE_NAME(fileName);
					novelHomeDto.setN_IMAGE_SIZE(fileSize);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

		}
		
		
		LogAspect.logger.info(LogAspect.LogMsg + novelHomeDto.toString());
		int check = novelHomeDao.novelHomeUpload(novelHomeDto);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		mav.addObject("check", check);
		mav.setViewName("novelhome/uploadOk.do");
	
	}
}
