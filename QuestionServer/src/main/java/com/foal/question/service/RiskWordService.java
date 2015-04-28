package com.foal.question.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.AppRiskWord;
import com.foal.question.util.SensitiveWordTools;

@SuppressWarnings("unchecked")
@Service(value = "riskWordService")
public class RiskWordService  extends DaoSupport {
	
	private static SensitiveWordTools sensitiveWordTools;
	
	private static boolean isInit = false;
	
	public void initRiskWord(){
		List<AppRiskWord> appRiskWordList = this.hibernateDao.queryList("from AppRiskWord");
		Set<String> riskWordSet = new HashSet<String>();
		for (AppRiskWord appRiskWord : appRiskWordList) {
			riskWordSet.add(appRiskWord.getRiskWord());
		}
		sensitiveWordTools = new SensitiveWordTools(riskWordSet);
		isInit = true;
	}
	/**
	 * 检查指定的字符串中是否含有敏感词
	 * @param str
	 * @return
	 */
	public boolean containRiskWord(String str){	
		if (!isInit || sensitiveWordTools == null) {
			initRiskWord();
		}
		return sensitiveWordTools.isContaintSensitiveWord(str);
	}
	/**
	 * 将敏感词汇用指定字符替换
	 * @param str
	 * @param replaceChar
	 * @return
	 */
	public String replaceRiskWord(String str, char replaceChar){
		if (str == null) {
			return "";
		}
		if (!isInit || sensitiveWordTools == null) {
			initRiskWord();
		}
		return sensitiveWordTools.replaceSensitiveWord(str, SensitiveWordTools.MaxMatchType, replaceChar);
	}
}
