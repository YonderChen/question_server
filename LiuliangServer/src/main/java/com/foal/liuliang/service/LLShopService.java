package com.foal.liuliang.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.LLShopBean;
import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.util.HttpTools;
import com.foal.liuliang.util.StringTools;
import com.foal.liuliang.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "llShopService")
public class LLShopService extends DaoSupport {
	
	public static class Regex {
		public static String TaoBaoShopUrl_regex = "http://\\w+\\.taobao\\.com.*";
		public static String TaoBaoGoodsUrl_regex = "http://item\\.taobao\\.com/item\\.htm\\?.*id=.*";
		public static String TmallShopUrl_regex = "http://\\w+\\.tmall\\.com.*";
		public static String TmallGoodsUrl_regex = "http://detail\\.tmall\\.com/item\\.htm\\?.*id=.*";
		public static String JDShopUrl_regex = "http://\\w+\\.jd\\.com.*";
		public static String JDGoodsUrl_regex = "http://item\\.jd\\.com/.*\\.html.*";
	}
	
	public boolean checkShopUrl(LLShopBean llShopBean) {
		//检测店铺URL是否正确
		if (LLShop.BindPlat.TaoBao.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getShopUrl().matches(Regex.TaoBaoShopUrl_regex)) {
				return false;
			}
		} else if (LLShop.BindPlat.Tmall.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getShopUrl().matches(Regex.TmallShopUrl_regex)) {
				return false;
			}
		} else if (LLShop.BindPlat.JD.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getShopUrl().matches(Regex.JDShopUrl_regex)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}

	public boolean checkBindName(LLShopBean llShopBean) {
		//检测店铺旺旺是否正确
		String bindName = "";
		if (LLShop.BindPlat.TaoBao.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getShopUrl().matches(Regex.TaoBaoShopUrl_regex)) {
				return false;
			}
			try {
				String content = HttpTools.sendGet(llShopBean.getShopUrl(), null, "GBK");
				String html = content.substring(content.indexOf("<span class=\"seller\">") + 21, content.indexOf("</a><br>", content.indexOf("<span class=\"seller\">") + 21));
				bindName = html.substring(html.indexOf("掌柜：") + 3);
		    	System.out.println(bindName);
			} catch (Exception e) {
				return false;
			}
		} else if (LLShop.BindPlat.Tmall.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getShopUrl().matches(Regex.TmallShopUrl_regex)) {
				return false;
			}
			try {
				String content = HttpTools.sendGet(llShopBean.getShopUrl(), null, "GBK");
				String html = content.substring(content.indexOf("<a class=\"shop-name\"") + 20, content.indexOf("</span></a>", content.indexOf("<a class=\"shop-name\"") + 20));
				bindName = html.substring(html.indexOf("<span>") + 6);
		    	System.out.println(bindName);
			} catch (Exception e) {
				return false;
			}
		} else if (LLShop.BindPlat.JD.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getShopUrl().matches(Regex.JDShopUrl_regex)) {
				return false;
			}
			try {
				String content = HttpTools.sendGet(llShopBean.getShopUrl(), null);
				String html = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>", content.indexOf("<title>") + 7));
				html = html.trim();
				bindName = html.substring(0, html.indexOf(" - 京东"));
		    	System.out.println(bindName);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
    	return StringTools.equalsStr(bindName, llShopBean.getBindName());
    }
	
	public boolean checkGoodsUrl(LLShopBean llShopBean) {
		//检测店铺URL是否正确
		return checkGoodsUrl(llShopBean.getBindPlat(), llShopBean.getVerifyGoodsUrl());
	}

	public boolean checkGoodsUrl(String bindPlat, String goodsUrl) {
		//检测店铺URL是否正确
		if (LLShop.BindPlat.TaoBao.equals(bindPlat)) {
			if (!goodsUrl.matches(Regex.TaoBaoGoodsUrl_regex)) {
				return false;
			}
		} else if (LLShop.BindPlat.Tmall.equals(bindPlat)) {
			if (!goodsUrl.matches(Regex.TmallGoodsUrl_regex)) {
				return false;
			}
		} else if (LLShop.BindPlat.JD.equals(bindPlat)) {
			if (!goodsUrl.matches(Regex.JDGoodsUrl_regex)) {
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	
	public boolean checkShopNum(LLShopBean llShopBean) {
        String queryHql = "select count(*) from LLShop as s where s.serverUser.userId = :userId and s.bindPlat = :bindPlat and s.status in (" + LLShop.Status.Success + "," + LLShop.Status.Create + ") ";
        Map paramMap = new HashMap();
        paramMap.put("userId", llShopBean.getUserId());
        paramMap.put("bindPlat", llShopBean.getBindPlat());
        int count = this.hibernateDao.getAllRow(queryHql, paramMap);
		return count < Constant.PlatBindShopMaxNum;
	}
	
	public boolean checkShopBindName(LLShopBean llShopBean) {
        String queryHql = "select count(*) from LLShop as s where s.serverUser.userId = :userId and s.bindPlat = :bindPlat and s.bindName = :bindName and s.status in (" + LLShop.Status.Success + "," + LLShop.Status.Create + ") ";
        Map paramMap = new HashMap();
        paramMap.put("userId", llShopBean.getUserId());
        paramMap.put("bindPlat", llShopBean.getBindPlat());
        paramMap.put("bindName", llShopBean.getBindName());
        int count = this.hibernateDao.getAllRow(queryHql, paramMap);
		return count == 0;
	}
	
	public boolean checkVerifyCode(LLShopBean llShopBean) {
		//检测验证码是否正确
		String goodsName = "";
		if (LLShop.BindPlat.TaoBao.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getVerifyGoodsUrl().matches(Regex.TaoBaoGoodsUrl_regex)) {
				return false;
			}
			try {
				String content = HttpTools.sendGet(llShopBean.getVerifyGoodsUrl(), null, "GBK");
				String html = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>", content.indexOf("<title>") + 7));
				goodsName = html.substring(0, html.indexOf("-淘宝网"));
		    	System.out.println(goodsName);
			} catch (Exception e) {
				return false;
			}
		} else if (LLShop.BindPlat.Tmall.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getVerifyGoodsUrl().matches(Regex.TmallGoodsUrl_regex)) {
				return false;
			}
			try {
				String content = HttpTools.sendGet(llShopBean.getVerifyGoodsUrl(), null, "GBK");
				String html = content.substring(content.indexOf("<title>") + 7, content.indexOf("</title>", content.indexOf("<title>") + 7));
				goodsName = html.substring(0, html.indexOf("-tmall.com天猫"));
		    	System.out.println(goodsName);
			} catch (Exception e) {
				return false;
			}
		} else if (LLShop.BindPlat.JD.equals(llShopBean.getBindPlat())) {
			if (!llShopBean.getVerifyGoodsUrl().matches(Regex.JDGoodsUrl_regex)) {
				return false;
			}
			try {
				String content = HttpTools.sendGet(llShopBean.getVerifyGoodsUrl(), null, "GBK");
				String html = content.substring(content.indexOf("<div id=\"itemInfo\">") + 19, content.indexOf("</h1>", content.indexOf("<div id=\"itemInfo\">") + 19));
				goodsName = html.substring(html.indexOf("<h1>"));
		    	System.out.println(goodsName);
			} catch (Exception e) {
				return false;
			}
		} else {
			return false;
		}
		return goodsName.contains(llShopBean.getVerifyCode());
    }
	
	public void add(LLShopBean shopBean, int status) {
		LLShop llShop = new LLShop();
		llShop.setServerUser(shopBean.getOperator());
		llShop.setBindPlat(shopBean.getBindPlat());
		llShop.setBindName(shopBean.getBindName());
		llShop.setShopUrl(shopBean.getShopUrl());
		llShop.setVerifyCode(shopBean.getVerifyCode());
		llShop.setVerifyGoodsUrl(shopBean.getVerifyGoodsUrl());
		llShop.setCreateTime(new Date());
		llShop.setStatus(status);
        this.hibernateDao.save(llShop);
    }
	
	public PageBean queryLLShop(LLShopBean llShopBean) {
        String queryHql = "from LLShop as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llShopBean.getUsername())) {
            queryHql += " and s.serverUser.username = :username";
            paramMap.put("username", llShopBean.getUsername() );
        }
        if (!StringUtil.isEmpty(llShopBean.getBindName())) {
            queryHql += " and s.bindName like :bindName";
            paramMap.put("bindName", "%" +llShopBean.getBindName() + "%" );
        }
        if (!StringUtil.isEmpty(llShopBean.getBindPlat())) {
            queryHql += " and s.bindPlat = :bindPlat";
            paramMap.put("bindPlat", llShopBean.getBindPlat() );
        }
        if (llShopBean.getStatus() >= 0) {
            queryHql += " and s.status = :status";
            paramMap.put("status", llShopBean.getStatus() );
        }
        if (llShopBean.getBeginTime() != null) {
            queryHql += " and s.createTime >= :beginTime";
            paramMap.put("beginTime", llShopBean.getBeginTime() );
        }
        if (llShopBean.getEndTime() != null) {
            queryHql += " and s.createTime <= :endTime";
            paramMap.put("endTime", llShopBean.getEndTime() );
        }
        List list = this.hibernateDao.queryList(queryHql, llShopBean.getPage(), llShopBean.getPageSize(), paramMap);
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		return new PageBean(list, allRow, llShopBean.getPage(), llShopBean.getPageSize());
    }
	
	public List queryLLShopList(LLShopBean llShopBean) {
        String queryHql = "from LLShop as s where 1=1";
        Map paramMap = new HashMap();
        if (!StringUtil.isEmpty(llShopBean.getUserId())) {
            queryHql += " and s.serverUser.userId = :userId";
            paramMap.put("userId", llShopBean.getUserId() );
        }
        if (!StringUtil.isEmpty(llShopBean.getBindPlat())) {
            queryHql += " and s.bindPlat = :bindPlat";
            paramMap.put("bindPlat", llShopBean.getBindPlat() );
        }
        return this.hibernateDao.queryList(queryHql, paramMap);
    }
	
	public int checkShop(LLShopBean shopBean) {
		LLShop llShop = hibernateDao.get(LLShop.class, shopBean.getShopId());
		llShop.setCheckAdmin(shopBean.getOperator());
		llShop.setCheckTime(new Date());
		if(shopBean.getStatus() == LLShop.Status.Success){
			llShop.setStatus(LLShop.Status.Success);
		} else if (shopBean.getStatus() == LLShop.Status.Create) {
			llShop.setStatus(LLShop.Status.Create);
		} else {
			llShop.setStatus(LLShop.Status.CheckFail);
		}
        this.hibernateDao.update(llShop);
        return llShop.getStatus();
    }
	
	public LLShop getShop(String shopId) {
		return this.hibernateDao.get(LLShop.class, shopId);
	}
	
	public LLShop updateShop(LLShopBean llShopBean) {
		LLShop llShop = this.getShop(llShopBean.getShopId());
		if (llShop == null) {
			return null;
		}
		llShop.setBindName(llShopBean.getBindName());
		llShop.setBindPlat(llShopBean.getBindPlat());
		llShop.setShopUrl(llShopBean.getShopUrl());
		llShop.setVerifyGoodsUrl(llShopBean.getVerifyGoodsUrl());
		llShop.setVerifyCode(llShopBean.getVerifyCode());
		if (llShop.getStatus() != llShopBean.getStatus()) {
			if (llShopBean.getStatus() == LLShop.Status.Success) {
				llShop.setCheckAdmin(llShopBean.getOperator());
				llShop.setCheckTime(new Date());
			}
			llShop.setStatus(llShopBean.getStatus());
		}
		this.hibernateDao.update(llShop);
		return llShop;
	}
	
	public int getShopNum(String userId, String bindPlat) {
		String hql = "select count(*) from LLShop as s where s.serverUser.userId = :userId and s.bindPlat = :bindPlat";
        Map paramMap = new HashMap();
        paramMap.put("userId", userId);
        paramMap.put("bindPlat", bindPlat);
        return this.hibernateDao.getAllRow(hql, paramMap);
	}
}

