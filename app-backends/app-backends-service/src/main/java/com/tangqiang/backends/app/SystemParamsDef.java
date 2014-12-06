package com.tangqiang.backends.app;

/**
 * 系统参数定义
 * 
 * @author tom
 * @since 2013-8-3 上午2:13:20
 * @project app-backends-service
 * @package com.tangqiang.backends.app
 */
public enum SystemParamsDef {
	/** mina端口 */
	PORT_MINA(0X0001),
	/** socket端口 */
	PORT_SOCKET(0X0002),
	/** kugou 应用目录 */
	PATH_APP_KUGOU(0X0003);

	private int value;

	public int getValue() {
		return value;
	}

	private SystemParamsDef(int value) {
		this.value = value;
	}
}
