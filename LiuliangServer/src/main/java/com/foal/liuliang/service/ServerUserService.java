package com.foal.liuliang.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foal.liuliang.bean.PageBean;
import com.foal.liuliang.bean.ServerUserBean;
import com.foal.liuliang.config.Constant;
import com.foal.liuliang.dao.DaoSupport;
import com.foal.liuliang.pojo.LLScoreOrder;
import com.foal.liuliang.pojo.LLScoreRecord;
import com.foal.liuliang.pojo.LLShop;
import com.foal.liuliang.pojo.LLTask;
import com.foal.liuliang.pojo.LLVIPOrder;
import com.foal.liuliang.pojo.Menu;
import com.foal.liuliang.pojo.Role;
import com.foal.liuliang.pojo.RoleMenu;
import com.foal.liuliang.pojo.ServerUser;
import com.foal.liuliang.pojo.SystemParam;
import com.foal.liuliang.pojo.UserRole;
import com.foal.liuliang.pojo.UserRolePK;
import com.foal.liuliang.util.StringTools;
import com.foal.liuliang.util.StringUtil;

@SuppressWarnings("unchecked")
@Service(value = "serverUserService")
public class ServerUserService extends DaoSupport {

	@Autowired
	LLScoreRecordService llScoreRecordService;
	
	public void updateServerUser(ServerUser user) {
		this.hibernateDao.update(user);
	}
	
	public ServerUser queryServerUser(ServerUserBean userBean, StringBuffer sb) {
		String queryHql = "from ServerUser as t where t.username = ?";
		List list = this.hibernateDao.queryList(queryHql, userBean.getUsername());
		if (list.isEmpty()) {
			sb.append("用户名不存在.");
			return null;
		}
		ServerUser user = (ServerUser)list.get(0);
		if (!StringUtil.checkPassword(userBean.getPassword(), user.getEncryptedPassword(), user.getAssistantPassword())) {
			sb.append("密码错误.");
			return null;
		}
		if (user.getStatus() == ServerUser.Status.Freeze) {
			sb.append("您的账户已被冻结,无权登录.");
			return null;
		}
		return user;
	}
	
	public void updateServerUserLastLoginTime(ServerUser user) {
		user.setLastLoginTime(new Date());
		this.updateServerUser(user);
	}
	
	public List<Menu> queryAllMenu() {
		String queryHql = "from Menu as t order by t.sort";
		return this.hibernateDao.queryList(queryHql);
	}
	
	public List<Menu> queryLoginMenu(String userId) {
		String queryHql = "select distinct(t2.pk.menu) from RoleMenu as t2 where t2.pk.role.roleId in " +
		"(select t3.pk.role.roleId from UserRole as t3 where t3.pk.serverUser.userId = ?) order by t2.pk.menu.sort";
		return this.hibernateDao.queryList(queryHql, userId);
	}
	
	public PageBean queryServerUser(ServerUserBean serverUserBean) {
        String queryHql = "from ServerUser as s where 1 = 1";
        if (!serverUserBean.getOperator().getUserId().equals(Constant.ADMIN_ID)) {
			queryHql += " and s.parent.userId is not null";
		}
        Map paramMap = new HashMap();
        queryHql += " and s.userType = :userType";
        paramMap.put("userType", ServerUser.UserType.AdminUser );
        if (!StringUtil.isEmpty(serverUserBean.getName())) {
            queryHql += " and s.name like :name";
            paramMap.put("name", "%" +serverUserBean.getName()+"%" );
        }
        if (!StringUtil.isEmpty(serverUserBean.getUsername())) {
            queryHql += " and s.username like :username";
            paramMap.put("username", "%" +serverUserBean.getUsername()+"%" );
        }
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
        queryHql += " order by s.createTime desc";
        List list = this.hibernateDao.queryList(queryHql, serverUserBean.getPage(), serverUserBean.getPageSize(), paramMap);
        return new PageBean(list, allRow, serverUserBean.getPage(), serverUserBean.getPageSize());
    }
	
	public PageBean queryShopUser(ServerUserBean serverUserBean) {
        String queryHql = "from ServerUser as s where 1 = 1";
        if (!serverUserBean.getOperator().getUserId().equals(Constant.ADMIN_ID)) {
			queryHql += " and s.parent.userId is not null";
		}
        Map paramMap = new HashMap();
        queryHql += " and s.userType = :userType";
        paramMap.put("userType", ServerUser.UserType.ShopUser );
        if (!StringUtil.isEmpty(serverUserBean.getUsername())) {
            queryHql += " and s.username like :username";
            paramMap.put("username", "%" +serverUserBean.getUsername()+"%" );
        }
        if (serverUserBean.getBeginTime() != null) {
            queryHql += " and s.vipEndTime >= :beginTime";
            paramMap.put("beginTime", serverUserBean.getBeginTime() );
        }
        if (serverUserBean.getEndTime() != null) {
            queryHql += " and s.vipEndTime <= :endTime";
            paramMap.put("endTime", serverUserBean.getEndTime() );
        }
        int allRow = this.hibernateDao.getAllRow("select count(*) " + queryHql, paramMap);
        queryHql += " order by s.modifyTime desc";
        List list = this.hibernateDao.queryList(queryHql, serverUserBean.getPage(), serverUserBean.getPageSize(), paramMap);
        return new PageBean(list, allRow, serverUserBean.getPage(), serverUserBean.getPageSize());
    }

	public ServerUser getServerUser(String userId) {
		return this.hibernateDao.get(ServerUser.class, userId);
	}

	public ServerUser getServerUserByEmail(String email) {
        String queryHql = "from ServerUser as s where s.email = :email";
        Map paramMap = new HashMap();
        paramMap.put("email", email );
        List list = this.hibernateDao.queryList(queryHql, paramMap);
        if (list.size() == 0) {
			return null;
		}
		return (ServerUser)list.get(0);
	}
	
	public ServerUser updateServerUserInfo(ServerUserBean userBean) {
		ServerUser user = this.getServerUser(userBean.getUserId());
		if (user == null) {
			return null;
		}
		user.setStatus(userBean.getStatus());
		user.setModifyTime(new Date());
		this.updateServerUser(user);
		if (!userBean.getUserId().equals(Constant.ADMIN_ID)) {
			List list = this.hibernateDao.queryList("from UserRole as u where u.pk.serverUser.userId = ?", userBean.getUserId());
			this.hibernateDao.deleteAll(list);
			if (StringTools.isNotEmpty(userBean.getRoleIds())) {
				String[] roleId = userBean.getRoleIds().split(",");
				for (int i = 0; i < roleId.length; i++) {
					UserRole ur = new UserRole();
					UserRolePK pk = new UserRolePK();
					pk.setRole(this.hibernateDao.get(Role.class, roleId[i]));
					pk.setServerUser(user);
					ur.setPk(pk);
					this.hibernateDao.save(ur);
				}
			}
		}
		return user;
	}
	
	public ServerUser updateShopUserInfo(ServerUserBean userBean) {
		ServerUser user = this.getServerUser(userBean.getUserId());
		if (user == null) {
			return null;
		}
		Date now = new Date();
		int oldScore = user.getScore();
		user.setVipEndTime(userBean.getVipEndTime());
		user.setScore(userBean.getScore());
		user.setEmail(userBean.getEmail());
		user.setUserqq(userBean.getUserqq());
		user.setPhone(userBean.getPhone());
		user.setStatus(userBean.getStatus());
		user.setModifyTime(now);
		this.updateServerUser(user);
		int scoreChange = user.getScore() - oldScore;
		if (scoreChange != 0) {
			LLScoreRecord record = new LLScoreRecord();
			record.setServerUser(user);
			record.setNum(scoreChange);
			record.setType(LLScoreRecord.ScoreRecordType.AdminEdit);
			record.setRemain(user.getScore());
			record.setCreateTime(now);
			record.setRemark("");
			llScoreRecordService.add(record);
		}
		return user;
	}
	
	public ServerUser updateServerUserPass(ServerUserBean userBean) {
		ServerUser user = this.getServerUser(userBean.getUserId());
		if (!StringUtil.checkPassword(userBean.getOldPassword(), user.getEncryptedPassword(), user.getAssistantPassword())) {
			return null;
		}
		String[] passwords = StringUtil.generatePassword(userBean.getNewPassword());
		user.setAssistantPassword(passwords[1]);
		user.setEncryptedPassword(passwords[0]);
		user.setModifyTime(new Date());
		this.updateServerUser(user);
		return user;
	}

	public ServerUser updateServerUserPass(String userId, String newPassword) {
		ServerUser user = this.getServerUser(userId);
		if (user == null) {
			return null;
		}
		String[] passwords = StringUtil.generatePassword(newPassword);
		user.setAssistantPassword(passwords[1]);
		user.setEncryptedPassword(passwords[0]);
		user.setModifyTime(new Date());
		this.updateServerUser(user);
		return user;
	}
	
	public ServerUser updateServerUserBaseInfo(ServerUserBean userBean) {
		ServerUser user = this.getServerUser(userBean.getUserId());
		user.setName(userBean.getName());
		user.setPhone(userBean.getPhone());
		user.setModifyTime(new Date());
		this.updateServerUser(user);
		return user;
	}
	
	public ServerUser updateServerUserQQ(ServerUserBean userBean) {
		ServerUser user = this.getServerUser(userBean.getUserId());
		user.setUserqq(userBean.getUserqq());
		user.setModifyTime(new Date());
		this.updateServerUser(user);
		return user;
	}
	
	public ServerUser updateServerUserPhone(ServerUserBean userBean) {
		ServerUser user = this.getServerUser(userBean.getUserId());
		user.setPhone(userBean.getPhone());
		user.setModifyTime(new Date());
		this.updateServerUser(user);
		return user;
	}

	public boolean addServerUser(ServerUserBean userBean, StringBuffer sb) {
		String queryHql = "from ServerUser as t where t.username = ?";
		List list = this.hibernateDao.queryList(queryHql, userBean.getUsername());
		if (!list.isEmpty()) {
			sb.append("用户名已存在");
			return false;
		}
		queryHql = "from ServerUser as t where t.email = ?";
		list = this.hibernateDao.queryList(queryHql, userBean.getEmail());
		if (!list.isEmpty()) {
			sb.append("邮箱已存在.");
			return true;
		}
		ServerUser user = new ServerUser();
		user.setUserType(userBean.getUserType());
		user.setUsername(userBean.getUsername());
		user.setName(userBean.getName());
		user.setPhone(userBean.getPhone());
		user.setEmail(userBean.getEmail());
		user.setModifyTime(new Date());
		user.setParent(userBean.getOperator());
		user.setCreateTime(new Date());
		user.setUserqq(userBean.getUserqq());
		String[] passwords = StringUtil.generatePassword(userBean.getPassword());
		user.setAssistantPassword(passwords[1]);
		user.setEncryptedPassword(passwords[0]);
		user.setStatus(ServerUser.Status.Normal);
		this.hibernateDao.save(user);
		String[] roleId = userBean.getRoleIds().split(",");
		for (int i = 0; i < roleId.length; i++) {
			UserRole ur = new UserRole();
			UserRolePK pk = new UserRolePK();
			pk.setRole(this.hibernateDao.get(Role.class, roleId[i]));
			pk.setServerUser(user);
			ur.setPk(pk);
			this.hibernateDao.save(ur);
		}
		return true;
	}

	public boolean addShopUser(ServerUserBean userBean, StringBuffer sb) {
		String queryHql = "from ServerUser as t where t.username = ?";
		List list = this.hibernateDao.queryList(queryHql, userBean.getUsername());
		if (!list.isEmpty()) {
			sb.append("用户名已存在");
			return false;
		}
		queryHql = "from ServerUser as t where t.email = ?";
		list = this.hibernateDao.queryList(queryHql, userBean.getEmail());
		if (!list.isEmpty()) {
			sb.append("邮箱已存在.");
			return true;
		}
		ServerUser user = new ServerUser();
		user.setUserType(userBean.getUserType());
		user.setUsername(userBean.getUsername());
		user.setName(userBean.getName());
		user.setPhone(userBean.getPhone());
		user.setEmail(userBean.getEmail());
		user.setModifyTime(new Date());
		user.setParent(userBean.getOperator());
		user.setCreateTime(new Date());
		user.setUserqq(userBean.getUserqq());
		String[] passwords = StringUtil.generatePassword(userBean.getPassword());
		user.setAssistantPassword(passwords[1]);
		user.setEncryptedPassword(passwords[0]);
		user.setStatus(ServerUser.Status.Normal);
		this.hibernateDao.save(user);
		return true;
	}
	
	public void clearHibernateCache() {
		this.hibernateDao.getSessionFactory().evict(LLScoreOrder.class);
		this.hibernateDao.getSessionFactory().evict(LLScoreRecord.class);
		this.hibernateDao.getSessionFactory().evict(LLShop.class);
		this.hibernateDao.getSessionFactory().evict(LLTask.class);
		this.hibernateDao.getSessionFactory().evict(LLVIPOrder.class);
		this.hibernateDao.getSessionFactory().evict(Menu.class);
		this.hibernateDao.getSessionFactory().evict(Role.class);
		this.hibernateDao.getSessionFactory().evict(RoleMenu.class);
		this.hibernateDao.getSessionFactory().evict(ServerUser.class);
		this.hibernateDao.getSessionFactory().evict(SystemParam.class);
		this.hibernateDao.getSessionFactory().evict(UserRole.class);
	}
}

