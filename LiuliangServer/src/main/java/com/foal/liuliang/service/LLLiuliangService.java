package com.foal.liuliang.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLLiuliangBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLLiuliang;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.util.CrazyClickTools;
import com.foal.liuliang.util.GsonTools;
import com.foal.liuliang.util.StringTools;
import com.foal.liuliang.util.StringUtil;
import com.google.gson.JsonObject;

@SuppressWarnings("unchecked")
@Service(value = "llLiuliangService")
public class LLLiuliangService extends DaoSupport {
	
	private static final Logger logger = Logger.getLogger(LLLiuliangService.class);
	
	public void addKewordTask(LLTask llTask) {
		String goodId = "";
		String method = "";
		String shopType = "";
		int path1 = 0;
		int path2 = 0;
		int path3 = 0;
		if (LLShop.BindPlat.TaoBao.equals(llTask.getLlShop().getBindPlat())) {
			String idTemp = llTask.getGoodsUrl().substring(llTask.getGoodsUrl().indexOf("id="));
			if (idTemp.indexOf("&") > 0) {
				goodId = idTemp.substring(0, idTemp.indexOf("&"));
			} else {
				goodId = idTemp;
			}
			if (LLTask.ClientType.Phone.equals(llTask.getClientType())) {
				method = CrazyClickTools.Method.tbmobi_add;
			} else {
				method = CrazyClickTools.Method.tbpc_add;
			}
			shopType = "c";
			path1 = llTask.getSearchSource();
			path2 = 0;
			path3 = 0;
		} else if (LLShop.BindPlat.Tmall.equals(llTask.getLlShop().getBindPlat())) {
			String idTemp = llTask.getGoodsUrl().substring(llTask.getGoodsUrl().indexOf("id=") + 3);
			if (idTemp.indexOf("&") > 0) {
				goodId = idTemp.substring(0, idTemp.indexOf("&"));
			} else {
				goodId = idTemp;
			}
			if (LLTask.ClientType.Phone.equals(llTask.getClientType())) {
				method = CrazyClickTools.Method.tbmobi_add;
				path1 = 0;
				path2 = 100;
				path3 = 0;
			} else {
				method = CrazyClickTools.Method.tbpc_add;
				path1 = 0;
				path2 = llTask.getSearchSource();
				path3 = 100 - llTask.getSearchSource();
			}
			shopType = "b";
		} else if (LLShop.BindPlat.JD.equals(llTask.getLlShop().getBindPlat())) {
			String idTemp = llTask.getGoodsUrl().substring(0, llTask.getGoodsUrl().indexOf(".html"));
			goodId = idTemp.substring(idTemp.lastIndexOf("/") + 1);
			method = CrazyClickTools.Method.jdpc_add;
			path1 = 100;
			path2 = 0;
			path3 = 0;
			shopType = "";
		}
		LLLiuliang keyword1 = parseKeyword(goodId, shopType, llTask.getKeyword1(), llTask.getOrderNumberOneDay1(), path1, path2, path3, llTask);
		addKeyword(keyword1, method);
		LLLiuliang keyword2 = parseKeyword(goodId, shopType, llTask.getKeyword2(), llTask.getOrderNumberOneDay2(), path1, path2, path3, llTask);
		addKeyword(keyword2, method);
		LLLiuliang keyword3 = parseKeyword(goodId, shopType, llTask.getKeyword3(), llTask.getOrderNumberOneDay3(), path1, path2, path3, llTask);
		addKeyword(keyword3, method);
		if (StringTools.isNotBlank(llTask.getKeyword4()) && llTask.getOrderNumberOneDay4() > 0) {
			LLLiuliang keyword4 = parseKeyword(goodId, shopType, llTask.getKeyword4(), llTask.getOrderNumberOneDay4(), path1, path2, path3, llTask);
			addKeyword(keyword4, method);
		}
		if (StringTools.isNotBlank(llTask.getKeyword5()) && llTask.getOrderNumberOneDay5() > 0) {
			LLLiuliang keyword5 = parseKeyword(goodId, shopType, llTask.getKeyword5(), llTask.getOrderNumberOneDay5(), path1, path2, path3, llTask);
			addKeyword(keyword5, method);
		}
	}
	
	private LLLiuliang parseKeyword(String goodId, String shopType, String keyword, int times, int path1, int path2, int path3, LLTask llTask) {
		LLLiuliang liuliang = new LLLiuliang();
		liuliang.setGoodId(goodId);
		liuliang.setKeyword(keyword);
		liuliang.setLlTask(llTask);
		liuliang.setPath1(path1);
		liuliang.setPath2(path2);
		liuliang.setPath3(path3);
		liuliang.setShopType(shopType);
		liuliang.setTimes(times);
		liuliang.setSleepTime(Constant.PageStayTypeTimeMap.get(llTask.getPageStayType()));
		liuliang.setClickStart(Constant.VisitTimeTypeClickStartMap.get(llTask.getVisitTimeType()));
		liuliang.setClickEnd(Constant.VisitTimeTypeClickEndMap.get(llTask.getVisitTimeType()));
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		liuliang.setBeginTime(cal.getTime());
		cal.add(Calendar.DAY_OF_MONTH, llTask.getDurationDay());
		liuliang.setEndTime(cal.getTime());
		liuliang.setNum(llTask.getDurationDay() * times);
		liuliang.setCreateTime(new Date());
		return liuliang;
	}
	
	private void addKeyword(LLLiuliang liuliang, String method) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("kwd", liuliang.getKeyword());
		params.put("nid", liuliang.getGoodId());
		params.put("times", String.valueOf(liuliang.getTimes()));
		params.put("sleep_time", String.valueOf(liuliang.getSleepTime()));
		params.put("click_start", String.valueOf(liuliang.getClickStart()));
		params.put("click_end", String.valueOf(liuliang.getClickEnd()));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		params.put("begin_time", sdf.format(liuliang.getBeginTime()));
		params.put("end_time", sdf.format(liuliang.getEndTime()));
		if (StringTools.isNotBlank(liuliang.getShopType())) {
			params.put("shop_type", liuliang.getShopType());
		}
		if (CrazyClickTools.Method.tbpc_add.equals(method) || CrazyClickTools.Method.tbmobi_add.equals(method)) {
			params.put("path1", String.valueOf(liuliang.getPath1()));
			params.put("path2", String.valueOf(liuliang.getPath2()));
			params.put("path3", String.valueOf(liuliang.getPath3()));
		}
		JsonObject res = CrazyClickTools.request(method, params);
		logger.error(res.toString());
		String status = GsonTools.getStringValue(res, "status", "");
		if ("success".equals(status)) {
			JsonObject data = GsonTools.getJsonObject(res, "data");
			int thirdId = GsonTools.getIntValue(data, "id", -1);
			if (thirdId > 0) {
				liuliang.setThirdId(thirdId);
				long beginTime = GsonTools.getLongValue(data, "begin_time", 0);
				long endTime = GsonTools.getLongValue(data, "end_time", 0);
				liuliang.setBeginTime(new Date(beginTime * 1000));
				liuliang.setEndTime(new Date(endTime * 1000));
				liuliang.setStatus(LLLiuliang.Status.Success);
				this.hibernateDao.save(liuliang);
			} else {
				liuliang.setThirdId(0);
				liuliang.setStatus(LLLiuliang.Status.Fail);
			}
		} else {
			liuliang.setThirdId(0);
			liuliang.setStatus(LLLiuliang.Status.Fail);
		}
		this.hibernateDao.save(liuliang);
	}
	
	public PageBean queryLLLiuliang(LLLiuliangBean llLiuliangBean) {
        String queryHql = "from LLLiuliang as l where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llLiuliangBean.getKeyword())) {
            queryHql += " and l.keyword like :keyword";
            paramMap.put("keyword", "%" + llLiuliangBean.getKeyword() + "%" );
		}
        if (!StringUtil.isEmpty(llLiuliangBean.getBindName())) {
            queryHql += " and l.llTask.llShop.bindName like :bindName";
            paramMap.put("bindName", "%" + llLiuliangBean.getBindName() + "%" );
        }
        if (!StringUtil.isEmpty(llLiuliangBean.getTaskId())) {
            queryHql += " and l.llTask.taskId = :taskId";
            paramMap.put("taskId", llLiuliangBean.getTaskId() );
        }
        if (!StringUtil.isEmpty(llLiuliangBean.getUsername())) {
            queryHql += " and l.llTask.serverUser.username = :username";
            paramMap.put("username", llLiuliangBean.getUsername() );
        }
        if (!StringUtil.isEmpty(llLiuliangBean.getDoStatus())) {
        	if (Integer.valueOf(llLiuliangBean.getDoStatus()) == LLLiuliang.Status.Fail) {
                queryHql += " and l.status = :status";
                paramMap.put("status", LLLiuliang.Status.Fail );
			} else if (Integer.valueOf(llLiuliangBean.getDoStatus()) == LLLiuliang.DoStatus.Wait){
	            queryHql += " and l.status = :status and l.beginTime > :now";
	            paramMap.put("status", LLLiuliang.Status.Success );
	            paramMap.put("now", new Date() );
			} else if (Integer.valueOf(llLiuliangBean.getDoStatus()) == LLLiuliang.DoStatus.Doing){
	            queryHql += " and l.status = :status and l.beginTime <= :now and l.endTime >= :now";
	            paramMap.put("status", LLLiuliang.Status.Success );
	            paramMap.put("now", new Date() );
			} else if (Integer.valueOf(llLiuliangBean.getDoStatus()) == LLLiuliang.DoStatus.Done){
	            queryHql += " and l.status = :status and l.endTime < :now";
	            paramMap.put("status", LLLiuliang.Status.Success );
	            paramMap.put("now", new Date() );
			}
        }
        if (llLiuliangBean.getBeginTime() != null) {
            queryHql += " and l.createTime >= :beginTime";
            paramMap.put("beginTime", llLiuliangBean.getBeginTime() );
        }
        if (llLiuliangBean.getEndTime() != null) {
            queryHql += " and l.createTime <= :endTime";
            paramMap.put("endTime", llLiuliangBean.getEndTime() );
        }
        List list = this.hibernateDao.queryList(queryHql, llLiuliangBean.getPage(), llLiuliangBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llLiuliangBean.getPage(), llLiuliangBean.getPageSize());
	}
	
}
