package com.foal.liuliang.web.shop.accountmanage.dealmanage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.springframework.beans.factory.annotation.Autowired;

import com.foal.liuliang.bean.AjaxBean;
import com.foal.liuliang.bean.LLScoreRecordBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.pojo.LLScoreRecord;
import com.foal.liuliang.service.LLScoreRecordService;
import com.foal.liuliang.util.ExcelUtil;
import com.foal.liuliang.util.GenerateSequenceUtil;
import com.foal.liuliang.web.UserBaseAction;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("unchecked")
@InterceptorRefs( { @InterceptorRef("interceptor-admin") })
public class ScoreRecordAction extends UserBaseAction implements ModelDriven<LLScoreRecordBean>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1175963361351969047L;

	private LLScoreRecordBean llscoreRecordBean = new LLScoreRecordBean();

	@Autowired
	private LLScoreRecordService llScoreRecordService;
	
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

	@Action("export_score_record")
    public String export() {
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
		List list = this.llScoreRecordService.queryAllLLScoreRecord(llscoreRecordBean);
		try {
			String filePath = exportFile(list);
			this.ajaxWrite(new AjaxBean(true, filePath));
		} catch (Exception e) {
			this.ajaxWrite(new AjaxBean(false, "导出失败"));
		}
        return null;
    }
	
	@Action("export_all_score_record")
    public String exportAll() {
		LLScoreRecordBean bean = new LLScoreRecordBean();
		bean.setUserId(getSessionServerUser().getUserId());
		List list = this.llScoreRecordService.queryAllLLScoreRecord(bean);
		try {
			String filePath = exportFile(list);
			this.ajaxWrite(new AjaxBean(true, filePath));
		} catch (Exception e) {
			this.ajaxWrite(new AjaxBean(false, "导出失败"));
		}
        return null;
    }

	private String exportFile(List list) throws Exception{
		List<String[]> data = new ArrayList<String[]>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (Object obj : list) {
			LLScoreRecord record = (LLScoreRecord)obj;
			String[] rowData = {df.format(record.getCreateTime()), 
					LLScoreRecord.ScoreRecordTypeMap.get(record.getType()),
					String.valueOf(record.getNum()),
					String.valueOf(record.getRemain()),
					record.getRemark()};
			data.add(rowData);
		}
		String[] title = {"时间", "类型", "数量", "剩余积分", "备注"};
		String fileName =  this.getSessionServerUser().getUsername() + "_score_record_" + GenerateSequenceUtil.generateSequenceNo() +".xls";
		String fileDirPath = Constant.TOMCAT_SERVICE_ADDRESS + Constant.EXPORT_PATH;
		ExcelUtil.exportRepositoryModule(fileName, fileDirPath, "积分报表", title, data);
		return Constant.EXPORT_PATH + fileName;
	}
	
}
