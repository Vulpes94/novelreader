package com.java.notice.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.aop.LogAspect;
import com.java.notice.dto.NoticeDto;
import com.java.notice.dto.QuestionDto;

@Component
public class NoticeDaoImp implements NoticeDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public int noticeInsert(NoticeDto noticeDto) {
		return sqlSessionTemplate.insert("noticeInsert", noticeDto);
	}

	@Override
	public int questionInsert(QuestionDto questionDto) {
		return sqlSessionTemplate.insert("questionInsert", questionDto);
	}

	@Override
	public List<QuestionDto> selectQuestion(int startRow, int endRow) {
		HashMap<String, Object> hmap = new HashMap<String, Object>();
		hmap.put("startRow", startRow);
		hmap.put("endRow", endRow);

		return sqlSessionTemplate.selectList("selectQuestion", hmap);
	}

	@Override
	public int selectQuestionCount() {
		return sqlSessionTemplate.selectOne("selectQuestionCount");
	}

	@Override
	public QuestionDto questionRead(int q_num) {
		int check = sqlSessionTemplate.update("questionReadCount", q_num);
		LogAspect.logger.info(LogAspect.LogMsg + check);

		return sqlSessionTemplate.selectOne("questionRead", q_num);
	}
}
