package com.tangqiang.dbtest.orm;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.tangqiang.db.base.entity.BaseORMEntity;

/**
 * 用户
 *
 * @author 汤强
 * @since 2013-7-30 下午3:58:32
 * @project app-db
 * @package com.tangqiang.dbtest.orm
 */
@MappedSuperclass
public abstract class User extends BaseORMEntity
{
	
	protected String name;
	protected String phone;
	
	
	@Basic
	@Column(name="NAME",length=20)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Basic
	@Column(name="PHONE",length=20)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
}
