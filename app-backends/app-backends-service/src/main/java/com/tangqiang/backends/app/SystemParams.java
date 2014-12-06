package com.tangqiang.backends.app;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tangqiang.backends.service.IAppConfigService;
import com.tangqiang.db.app.entity.TAppSystemParams;

/**
 * 系统参数配置工具
 * 
 * @author tom
 * @since 2013-8-3 上午2:14:27
 * @project app-backends-service
 * @package com.tangqiang.backends.app
 */
public class SystemParams {
	private static Logger logger = Logger.getLogger(SystemParams.class);

	/** 系统参数数据服务层 */
	private static final String APP_SYSTEM_PARAMS_SERVICE = "appConfigService";
	private static long lastFreshTime = 0;

	/** 参数数据 */
	private static Map<Integer, String> mSystemParams = new HashMap<Integer, String>();

	/**
	 * 获取系统参数
	 * 
	 * @param systemParamsDef
	 * @return
	 */
	public static String getSystemParams(SystemParamsDef systemParamsDef) {
		synchronized (mSystemParams) {
			long lNowTime = System.currentTimeMillis();
			if (lNowTime - lastFreshTime > 2 * 60 * 60 * 1000 || mSystemParams == null) {

				initSystemParams();
				lastFreshTime = lNowTime;
			}
		}
		return mSystemParams.get(systemParamsDef.getValue());
	}

	/**
	 * 初始化
	 */
	private static void initSystemParams() {
		logger.info("开始初始化系统配置参数!");
		try {
			synchronized (mSystemParams) {
				IAppConfigService appConfig = (IAppConfigService) AppContext.getInstance().getBean(APP_SYSTEM_PARAMS_SERVICE);
				;
				List<TAppSystemParams> listParams = appConfig.allAppSystemParams();
				for (int i = 0; i < listParams.size(); i++) {
					TAppSystemParams param = listParams.get(i);
					mSystemParams.put(param.getParamId(), param.getParamValue());
				}
			}
		} catch (Exception ex) {
			logger.error("读取平台参数配置表发生异常", ex);
		}
		logger.info("初始化系统配置参数结束！参数信息:" + mSystemParams);
	}

}
