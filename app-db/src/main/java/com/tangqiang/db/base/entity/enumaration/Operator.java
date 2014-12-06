package com.tangqiang.db.base.entity.enumaration;

/**
 * 关联操作
 *
 * @author 汤强
 * @since 2013-7-30 下午5:45:16
 * @project app-db
 * @package com.tangqiang.db.base.entity.enumaration
 */
public enum Operator {
	EQ("eq"), EQIGNORECASE("eqIgnoreCase"), NE("ne"), NEIGNORECASE("neIgnoreCase"), 
	GT("gt"), GE("gr"), LT("lt"), LE("le"), BETWEEN("between"), NOTBETWEEN("notBeteen"), 
	IN("in"), NOTIN("notIn"), EMPTY("empty"), NOTEMPTY("notEmpty"), NULL("isNull"), 
	NOTNULL("notNull"), LIKE("like"), NOTLIKE("notLike");
	private String value;

	public String getValue() {
		return value;
	}

	private Operator(String value) {
		this.value = value;
	}
}
