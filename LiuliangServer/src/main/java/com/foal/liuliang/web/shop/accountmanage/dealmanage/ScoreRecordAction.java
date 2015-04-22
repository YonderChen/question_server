package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.LLScoreRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.service.LLScoreRecordService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreRecordAction extends UserBaseAction implements ModelDriven<LLScoreRecordBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175963361351969047L;

	private LLScoreRecordBean llscoreRecordBean = new LLScoreRecordBean();

	@Autowired
	LLScoreRecordService llScoreRecordService;
	
	public LLScoreRecordBean getModel() {
		return llscoreRecordBean;
	}

	@Action("score_record")
    public String list() {
		llscoreRecordBean.setUserId(getSessionServerUser().getUserId());
		if (llscoreRecordBean.getBeginTime() == null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.MONTH, -1);
			llscoreRecordBean.setBeginTime(cal.getTime());
		}
		if (llscoreRecordBean.getEndTime() == null) {
			llscoreRecordBean.setEndTime(new Date());
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(llscoreRecordBean.getBeginTime());
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		llscoreRecordBean.setBeginTime(cal.getTime());
		cal.setTime(llscoreRecordBean.getEndTime());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		llscoreRecordBean.setEndTime(cal.getTime());
		PageBean pageBean = this.llScoreRecordService.queryLLScoreRecord(llscoreRecordBean);
		this.setAttrToRequest("pageBean", pageBean);
		this.setAttrToRequest("type", llscoreRecordBean.getType());
		this.setAttrToRequest("beginTime", llscoreRecordBean.getBeginTime());
		this.setAttrToRequest("endTime", llscoreRecordBean.getEndTime());
        return SUCCESS;
    }
	
}
