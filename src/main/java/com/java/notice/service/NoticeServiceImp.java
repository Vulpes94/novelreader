package com.java.notice.service;

import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import com.java.aop.LogAspect;
import com.java.notice.dao.NoticeDao;
import com.java.notice.dto.NoticeDto;
import com.java.notice.dto.QuestionDto;
import com.java.notice.dto.QuestionReplyDto;

@Component
public class NoticeServiceImp implements NoticeService {
  @Autowired
  private NoticeDao noticeDao;

  @Override
  public void notice(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String pageNumber = request.getParameter("pageNumber");

    if (pageNumber == null)
      pageNumber = "1";

    int currentPage = Integer.parseInt(pageNumber);
    LogAspect.logger.info(LogAspect.LogMsg + "현재 페이지 : " + currentPage);

    // 한페이지당 게시물 10개/ start 1, end 10
    int boardSize = 10;
    int startRow = (currentPage - 1) * boardSize + 1;
    int endRow = currentPage * boardSize;

    List<NoticeDto> noticeList = noticeDao.selectNotice(startRow, endRow);

    int count = noticeDao.selectNoticeCount();

    System.out.println(" 개수 " + count);

    mav.addObject("noticeList", noticeList);
    mav.addObject("count", count);
    mav.addObject("boardSize", boardSize);
    mav.addObject("currentPage", currentPage);

    mav.setViewName("notice/notice.tiles");
  }

  @Override
  public void noticeWriteOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    NoticeDto noticeDto = (NoticeDto) map.get("noticeDto");

    LogAspect.logger.info(LogAspect.LogMsg + noticeDto);

    int check = noticeDao.noticeInsert(noticeDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);

    mav.setViewName("notice/noticeWriteOk.tiles");
  }

  @Override
  public void noticeView(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    int not_num = Integer.parseInt(request.getParameter("not_num"));
    String pageNumber = request.getParameter("pageNumber");

    if (pageNumber == null)
      pageNumber = "1";

    Integer.parseInt(pageNumber);

    LogAspect.logger.info(LogAspect.LogMsg + not_num + "," + pageNumber);

    NoticeDto noticeDto = noticeDao.noticeRead(not_num);
    LogAspect.logger.info(LogAspect.LogMsg + noticeDto.toString());

    request.setAttribute("noticeDto", noticeDto);
    request.setAttribute("pageNumber", pageNumber);

    mav.setViewName("notice/noticeView.tiles");
  }

  @Override
  public void noticeDelete(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    int not_num = Integer.parseInt(request.getParameter("not_num"));
    LogAspect.logger.info(LogAspect.LogMsg + "not_num : " + not_num);

    int check = noticeDao.noticeDelete(not_num);
    LogAspect.logger.info(LogAspect.LogMsg + check);
    
    mav.addObject("check", check);
    mav.setViewName("notice/noticeDeleteOk");
  }

  @Override
  public void question(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String pageNumber = request.getParameter("pageNumber");

    if (pageNumber == null)
      pageNumber = "1";

    int currentPage = Integer.parseInt(pageNumber);
    LogAspect.logger.info(LogAspect.LogMsg + "현재 페이지 : " + currentPage);

    // 한페이지당 게시물 10개/ start 1, end 10
    int listSize = 10;
    int startRow = (currentPage - 1) * listSize + 1;
    int endRow = currentPage * listSize;

    List<QuestionDto> questionList = noticeDao.selectQuestion(startRow, endRow);

    int questionCount = noticeDao.selectQuestionCount();

    System.out.println(" 개수 " + questionCount);

    mav.addObject("listSize", listSize);
    mav.addObject("questionList", questionList);
    mav.addObject("questionCount", questionCount);
    mav.addObject("currentPage", currentPage);

    mav.setViewName("notice/question.tiles");
  }

  @Override
  public void questionWrite(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    String n_title = request.getParameter("n_title");

    mav.addObject("n_title", n_title);
    mav.setViewName("notice/questionWrite.tiles");
  }

  @Override
  public void questionWriteOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    QuestionDto questionDto = (QuestionDto) map.get("questionDto");

    LogAspect.logger.info(LogAspect.LogMsg + questionDto.toString());

    int check = noticeDao.questionInsert(questionDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);

    mav.setViewName("notice/questionWriteOk");
  }

  @Override
  public void questionReplyWriteOk(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    QuestionReplyDto questionReplyDto = (QuestionReplyDto) map.get("questionReplyDto");

    LogAspect.logger.info(LogAspect.LogMsg + questionReplyDto.toString());

    int check = noticeDao.questioReplyInsert(questionReplyDto);
    LogAspect.logger.info(LogAspect.LogMsg + check);

    mav.addObject("check", check);
    mav.addObject("q_num", questionReplyDto.getQr_num());

    mav.setViewName("notice/questionReplyWriteOk");
  }


  @Override
  public void questionView(ModelAndView mav) {
    Map<String, Object> map = mav.getModelMap();
    HttpServletRequest request = (HttpServletRequest) map.get("request");

    int q_num = Integer.parseInt(request.getParameter("q_num"));
    String pageNumber = request.getParameter("pageNumber");

    if (pageNumber == null)
      pageNumber = "1";

    Integer.parseInt(pageNumber);

    LogAspect.logger.info(LogAspect.LogMsg + q_num + "," + pageNumber);

    QuestionDto questionDto = noticeDao.questionRead(q_num);
    LogAspect.logger.info(LogAspect.LogMsg + questionDto.toString());

    // questionReply
    QuestionReplyDto questionReplyDto = noticeDao.questionReplySelect(q_num);
    if (questionReplyDto != null) {
      LogAspect.logger.info(LogAspect.LogMsg + "questionReplyDto : " + questionReplyDto.toString());
    }


    mav.addObject("questionDto", questionDto);
    mav.addObject("pageNumber", pageNumber);
    mav.addObject("questionReplyDto", questionReplyDto);

    mav.setViewName("notice/questionView.tiles");
  }

}
