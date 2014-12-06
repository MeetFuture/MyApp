package com.tangqiang.db.base.entity.enumaration;

/**
 * 排序方式定义 <li>ASC(1) 升序  <li>DESC(-1) 降序
 * 
 * @author 汤强
 * @since 2013-7-30 下午5:20:31
 * @project app-db
 * @package com.tangqiang.db.base.entity.enumaration
 */
public enum SortType {

	ASC(1), DESC(-1);

	private int value;

	public int getValue() {
		return value;
	}

	private SortType(int value) {
		this.value = value;
	}
}
