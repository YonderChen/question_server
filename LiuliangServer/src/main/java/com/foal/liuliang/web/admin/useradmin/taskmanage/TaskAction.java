package com.foal.liuliang.web.admin.useradmin.taskmanage;

import java.util.Calendar;
import java.util.Date;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLTaskBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.pojo.LLScoreRecord;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.service.LLScoreRecordService;
import com.foal.liuliang.service.LLShopService;
import com.foal.liuliang.service.LLTaskService;
import com.foal.liuliang.service.ServerUserService;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class TaskAction extends UserBaseAction implements ModelDriven<LLTaskBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -357112630505176351L;

	private LLTaskBean llTaskBean = new LLTaskBean();
	
	@Autowired
	LLTaskService llTaskService;
	
	@Autowired
	LLShopService llShopService;
	
	@Autowired
	ServerUserService serverUserService;
	
	@Autowired
	LLScoreRecordService llScoreRecordService;
	
	public LLTaskBean getModel() {
		return llTaskBean;
	}

	@Action("index")
	public String index() {
		return SUCCESS;
	}
	
	@Action("list")
    public String list() {
		llTaskBean.setUserId("");
		if (llTaskBean.getBeginTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(llTaskBean.getBeginTime());
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			llTaskBean.setBeginTime(cal.getTime());
		}
		if (llTaskBean.getEndTime() != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(llTaskBean.getEndTime());
			cal.set(Calendar.HOUR_OF_DAY, 23);
			cal.set(Calendar.MINUTE, 59);
			cal.set(Calendar.SECOND, 59);
			llTaskBean.setEndTime(cal.getTime());
		}
        PageBean pageBean = this.llTaskService.queryLLTask(llTaskBean);
		this.setAttrToRequest("pageBean", pageBean);
        return SUCCESS;
    }

	@Action("task_detail")
    public String taskDetail() {
    	LLTask lltask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	this.setAttrToRequest("llTask", lltask);
        return SUCCESS;
    }

	@Action("check_task")
    public String checkTask() {
    	LLTask llTask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	if(llTask.getServerUser().getStatus() != ServerUser.Status.Normal){
        	this.ajaxWrite(new AjaxBean(false, "该用户账户已被冻结，审核失败"));
            return null;
    	}
    	//调用第三方接口
    	//
    	//============
    	Date now = new Date();
    	int returnScore = llTask.getCostScore() - llTaskBean.getCostScore();
    	if (returnScore != 0) {
			llTask.setCostScore(llTaskBean.getCostScore());
			llTask.getServerUser().incScore(returnScore);
			LLScoreRecord record = new LLScoreRecord();
			record.setServerUser(llTask.getServerUser());
			record.setNum(returnScore);
			record.setType(LLScoreRecord.ScoreRecordType.TaskReturn);
			record.setRemain(llTask.getServerUser().getScore());
			record.setCreateTime(now);
			record.setRemark("管理员修改消费积分");
			llScoreRecordService.add(record);
		}
    	llTask.setStatus(LLTask.Status.Executing);
    	llTask.setCheckTime(now);
    	llTask.setCheckAdmin(this.getSessionServerUser());
    	this.llTaskService.updateLLTask(llTask);
    	serverUserService.updateServerUser(llTask.getServerUser());
		this.updateSessionUser(llTask.getServerUser());
    	this.ajaxWrite(new AjaxBean(true, "审核成功"));
        return null;
    }

	@Action("check_fail_task")
    public String checkFailTask() {
    	LLTask llTask = this.llTaskService.getLLTask(llTaskBean.getTaskId());
    	if(llTask.getServerUser().getStatus() != ServerUser.Status.Normal){
        	this.ajaxWrite(new AjaxBean(false, "该用户账户已被冻结，审核失败"));
            return null;
    	}
    	Date now = new Date();
    	int returnScore = llTask.getCostScore();
		llTask.getServerUser().incScore(returnScore);
		LLScoreRecord record = new LLScoreRecord();
		record.setServerUser(llTask.getServerUser());
		record.setNum(returnScore);
		record.setType(LLScoreRecord.ScoreRecordType.TaskReturn);
		record.setRemain(llTask.getServerUser().getScore());
		record.setCreateTime(now);
		record.setRemark("审核不通过");
		llScoreRecordService.add(record);
    	llTask.setStatus(LLTask.Status.VerifyFaild);
    	llTask.setRemark(llTaskBean.getRemark());
    	llTask.setCheckTime(now);
    	llTask.setCheckAdmin(this.getSessionServerUser());
    	this.llTaskService.updateLLTask(llTask);
    	serverUserService.updateServerUser(llTask.getServerUser());
		this.updateSessionUser(llTask.getServerUser());
    	this.ajaxWrite(new AjaxBean(true, "拒绝审核成功"));
        return null;
    }
}
