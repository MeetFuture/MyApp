package com.tangqiang.dbtest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.tangqiang.db.base.orm.IDao;
import com.tangqiang.db.base.test.HibernateTestCase;
import com.tangqiang.dbtest.entity.TUser;

/**
 * 用户数据表测试
 * 
 * @author 汤强
 * @since 2013-7-31 下午8:27:11
 * @project app-db
 * @package com.tangqiang.dbtest
 */
public class UserDaoTest extends HibernateTestCase{
	private Logger log = Logger.getLogger(UserDaoTest.class);

	private IDao<TUser, Long> userDao;
	private PropertyPlaceholderConfigurer propertyConfigurer; 

	public void testAdd() {
		//log.info("系统属性:"+System.getProperties());
		//log.info("环境变量:"+System.getenv());
		log.info("order:" +propertyConfigurer.getOrder());
		//propertyConfigurer.
		
		TUser user = new TUser();
		user.setName("汤asdasd");
		user.setPhone("1501as7575121");

		userDao.save(user);
		setComplete();
	}

	public void testSearch() {
		log.info("开始查询!");
		List<TUser> listT = userDao.all(null);
		for (int i = 0; i < listT.size(); i++) {
			TUser t = listT.get(i);
			log.info("查询到得用户: id:" + t.getId() + "  " + t.getName() + "  " + t.getPhone());
		}
	}

	public IDao<TUser, Long> getUserDao() {
		return userDao;
	}

	public void setUserDao(IDao<TUser, Long> userDao) {
		this.userDao = userDao;
	}

	public PropertyPlaceholderConfigurer getPropertyConfigurer() {
		return propertyConfigurer;
	}

	public void setPropertyConfigurer(PropertyPlaceholderConfigurer propertyConfigurer) {
		this.propertyConfigurer = propertyConfigurer;
	}
	
	
}
