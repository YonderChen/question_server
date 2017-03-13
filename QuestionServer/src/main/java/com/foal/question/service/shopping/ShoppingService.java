package com.foal.question.service.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.foal.question.bean.PageBean;
import com.foal.question.bean.ShoppingGoodsBean;
import com.foal.question.dao.DaoSupport;
import com.foal.question.pojo.ShoppingGoods;
import com.foal.question.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "shoppingService")
public class ShoppingService extends DaoSupport {

	public boolean addRecord(ShoppingGoods record) {
		this.hibernateDao.save(record);
		return true;
	}

	public ShoppingGoods getRecord(int id) {
		return this.hibernateDao.get(ShoppingGoods.class, id);
	}
	
	public boolean deleteRecord(ShoppingGoods record) {
		this.hibernateDao.delete(record);
		return true;
	}

	/**
	 * 获取公共列表
	 * @param orderBy	0（默认）：创建时间，1：点赞数
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<ShoppingGoods> getPublicRecord(int page, int pageSize, int goodType, String title, String content, String sourceStore) {
		String queryHql = "from ShoppingGoods as r where 1 = 1";
		Map paramMap = new HashMap();
		if (!StringUtil.isEmpty(goodType)) {
			queryHql += " and r.goodType like :goodType";
			paramMap.put("goodType", "%" + goodType + "%");
		}
		if (!StringUtil.isEmpty(title)) {
			queryHql += " and r.title like :title";
			paramMap.put("title", "%" + title + "%");
		}
		if (!StringUtil.isEmpty(content)) {
			queryHql += " and r.content like :content";
			paramMap.put("content", "%" + content + "%");
		}
		if (!StringUtil.isEmpty(sourceStore)) {
			queryHql += " and r.sourceStore like :sourceStore";
			paramMap.put("sourceStore", "%" + sourceStore + "%");
		}
		queryHql += " order by r.createTime desc";
		return this.hibernateDao.queryListByPage(queryHql, page, pageSize, paramMap);
	}

	public PageBean queryTextImage(ShoppingGoodsBean bean) {
		String queryHql = "from ShoppingGoods as r where 1 = 1";
		Map paramMap = new HashMap();
		if (!StringUtil.isEmpty(bean.getId())) {
			queryHql += " and r.id = :id";
			paramMap.put("id", bean.getId());
		}
		if (!StringUtil.isEmpty(bean.getGoodType())) {
			queryHql += " and r.goodType like :goodType";
			paramMap.put("goodType", "%" + bean.getGoodType() + "%");
		}
		if (!StringUtil.isEmpty(bean.getTitle())) {
			queryHql += " and r.title like :title";
			paramMap.put("title", "%" + bean.getTitle() + "%");
		}
		if (!StringUtil.isEmpty(bean.getContent())) {
			queryHql += " and r.content like :content";
			paramMap.put("content", "%" + bean.getContent() + "%");
		}
		if (!StringUtil.isEmpty(bean.getSourceStore())) {
			queryHql += " and r.sourceStore like :sourceStore";
			paramMap.put("sourceStore", "%" + bean.getSourceStore() + "%");
		}
		int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
		queryHql += " order by r.createTime desc";
		List list = this.hibernateDao.queryListByPage(queryHql, bean.getPage(), bean.getPageSize(), paramMap);
		return new PageBean(list, allRow, bean.getPage(), bean.getPageSize());
	}

}
