package com.tangqiang.db.base.test;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

/**
 * hibernate 数据库测试类 (protected 方法会在运行 TestCase 时自动调用)
 * 
 * @author 汤强
 * @since 2013-8-1 下午2:33:02
 * @project app-db
 * @package com.tangqiang.db.base.test
 */
@SuppressWarnings("deprecation")
public class HibernateTestCase extends AbstractTransactionalJUnit4SpringContextTests {
	private Logger logger = Logger.getLogger(HibernateTestCase.class);

	/** 测试的 spring 配置文件路径 */
	public static final String TEST_CONTEXT_PATH = "/applicationContext.xml";
	/** session工厂的bean名称 */
	public static final String SESSIONFACTORY_BEAN_NAME = "sessionFactory";
	
	/**
	 * 设置默认的spring配置文件路径
	 */
	protected String getConfigPath() {
		logger.debug("设置测试的spring配置文件:"+TEST_CONTEXT_PATH);
		return TEST_CONTEXT_PATH;
	}
	
	protected void prepareTestInstance() throws Exception {
		logger.debug("设置测试的spring bean注入方式: AUTOWIRE_BY_NAME");
	}

	protected void flush() {
		logger.debug("设置测试的spring sessionFactory bean 名称:"+SESSIONFACTORY_BEAN_NAME);
		flush(SESSIONFACTORY_BEAN_NAME);
	}

	protected void flush(String sessionFactoryName) {
		((SessionFactory) applicationContext.getBean(sessionFactoryName)).getCurrentSession().flush();
	}

	protected void evict(Object entity) {
		logger.debug("调用 evict 方法!");
		evict(entity, SESSIONFACTORY_BEAN_NAME);
	}

	protected void evict(Object entity, String sessionFactoryName) {
		((SessionFactory) applicationContext.getBean(sessionFactoryName)).getCurrentSession().evict(entity);
	}
}
