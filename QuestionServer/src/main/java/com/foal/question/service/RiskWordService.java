package com.foal.question.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppRiskWord;

@SuppressWarnings("unchecked")
@Service(value = "riskWordService")
public class RiskWordService  extends DaoSupport {
	
	public static final List<String> RiskWordList = new ArrayList<String>();
	
	public void initRiskWord(){
		List<AppRiskWord> appRiskWordList = this.hibernateDao.queryList("from AppRiskWord");
		for (AppRiskWord appRiskWord : appRiskWordList) {
			RiskWordList.add(appRiskWord.getRiskWord());
		}
	}
}
