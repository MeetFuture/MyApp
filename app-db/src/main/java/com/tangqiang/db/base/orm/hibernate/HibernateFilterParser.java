package com.tangqiang.db.base.orm.hibernate;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.tangqiang.db.base.entity.IFilter;
import com.tangqiang.db.base.entity.IFilter.FilterCase;
import com.tangqiang.db.base.entity.util.IFilterParser;
import com.tangqiang.db.base.orm.InjectFilter;

/**
 * hibernate 过滤器解析实现
 *
 * @author 汤强
 * @since 2013-8-1 下午7:44:44
 * @project app-db
 * @package com.tangqiang.db.base.orm.hibernate
 */
public class HibernateFilterParser implements IFilterParser {
	private static Logger log = Logger.getLogger(HibernateFilterParser.class);
	private static final String space = " ";

	public static IFilterParser newInstance() {
		return new HibernateFilterParser();
	}

	public String parse(IFilter filter) throws Exception {
		StringBuffer filterBuffer = new StringBuffer();
		List<FilterCase> filterCases = filter.getFilterCases();
		for (FilterCase tmp : filterCases) {
			filterBuffer.append(this.parseCase(tmp));
		}
		String filterStr = filterBuffer.toString().trim();
		if (filterStr.startsWith("and ")) {
			filterStr = filterStr.substring(4);
		}
		if (filterStr.startsWith("or ")) {
			filterStr = filterStr.substring(3);
		}
		return filterStr;
	}

	/**
	 * 解析过滤器的类型
	 * 
	 * @param filterCase
	 * @return
	 * @throws Exception
	 */
	private String parseCase(FilterCase filterCase) throws Exception {
		StringBuffer caseBuffer = new StringBuffer();
		caseBuffer.append(HibernateFilterParser.space);
		caseBuffer.append(filterCase.getGroupType().getValue());
		caseBuffer.append(HibernateFilterParser.space);
		switch (filterCase.getCaseType()) {
		case UNIT:
			caseBuffer.append(this.parseUnitCase(filterCase));
			break;
		case GROUP:
			caseBuffer.append(this.parseGroupCase(filterCase));
		}
		return caseBuffer.toString();
	}

	@SuppressWarnings("unchecked")
	private String parseGroupCase(FilterCase filterCase) throws Exception {
		List<FilterCase> cases = (List<FilterCase>) filterCase.getValue();
		StringBuffer unitBuffer = new StringBuffer();
		unitBuffer.append("(");
		boolean first = true;
		for (FilterCase tmp : cases) {
			if (first) {
				first = false;
				String firstCase = this.parseCase(tmp).trim();
				if (firstCase.startsWith("and ")) {
					firstCase = firstCase.substring(4);
				}
				if (firstCase.startsWith("or ")) {
					firstCase = firstCase.substring(3);
				}
				unitBuffer.append(firstCase);
			} else {
				unitBuffer.append(this.parseCase(tmp));
			}

		}
		unitBuffer.append(")");
		return unitBuffer.toString();
	}

	private String parseUnitCase(FilterCase filterCase) throws Exception {
		StringBuffer unitBuffer = new StringBuffer();

		switch (filterCase.getFilterType()) {
		case EQ:
			unitBuffer.append(filterCase.getFieldName());
			unitBuffer.append("=");
			unitBuffer.append(this.ecap(filterCase.getValue()));
			break;
		case NE:
			unitBuffer.append(filterCase.getFieldName());
			unitBuffer.append("!=");
			unitBuffer.append(this.ecap(filterCase.getValue()));
			break;
		case GT:
			unitBuffer.append(filterCase.getFieldName());
			unitBuffer.append(">");
			unitBuffer.append(this.ecap(filterCase.getValue()));
			break;
		case GE:
			unitBuffer.append(filterCase.getFieldName());
			unitBuffer.append(">=");
			unitBuffer.append(this.ecap(filterCase.getValue()));
			break;
		case LT:
			unitBuffer.append(filterCase.getFieldName());
			unitBuffer.append("<");
			unitBuffer.append(this.ecap(filterCase.getValue()));
			break;
		case LE:
			unitBuffer.append(filterCase.getFieldName());
			unitBuffer.append("<=");
			unitBuffer.append(this.ecap(filterCase.getValue()));
			break;
		case BETWEEN:
			unitBuffer = this.parseBetween(unitBuffer, filterCase);
			break;
		case NOTBETWEEN:
			unitBuffer = this.parseNotBetween(unitBuffer, filterCase);
			break;
		case IN:
			unitBuffer = this.parseIn(unitBuffer, filterCase, true);
			break;
		case NOTIN:
			unitBuffer = this.parseIn(unitBuffer, filterCase, false);
			break;
		case LIKE:
			unitBuffer = this.parseLike(unitBuffer, filterCase);
			break;
		case NOTLIKE:
			unitBuffer = this.parseNotLike(unitBuffer, filterCase);
			break;
		case EQIGNORECASE:
			unitBuffer = this.parseEqIgnoreCase(unitBuffer, filterCase);
			break;
		case NEIGNORECASE:
			unitBuffer = this.parseNeIgnoreCase(unitBuffer, filterCase);
			break;
		case EMPTY:
			unitBuffer = this.parseEmpty(unitBuffer, filterCase);
			break;
		case NOTEMPTY:
			unitBuffer = this.parseNotEmpty(unitBuffer, filterCase);
			break;
		case NULL:
			unitBuffer = this.parseNull(unitBuffer, filterCase);
			break;
		case NOTNULL:
			unitBuffer = this.parseNotNull(unitBuffer, filterCase);
			break;
		}
		return unitBuffer.toString();

	}

	private StringBuffer parseNotNull(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(filterCase.getFieldName()).append(" ");
		unitBuffer.append(" is not null");
		return unitBuffer;
	}

	private StringBuffer parseNull(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(filterCase.getFieldName()).append(" ");
		unitBuffer.append(" is null ");
		return unitBuffer;
	}

	private StringBuffer parseEmpty(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(filterCase.getFieldName()).append(" ");
		unitBuffer.append(" is empty ");
		return unitBuffer;
	}

	private StringBuffer parseNotEmpty(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(filterCase.getFieldName()).append(" ");
		unitBuffer.append(" is not empty ");
		return unitBuffer;
	}

	private StringBuffer parseNeIgnoreCase(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append("lower(").append(filterCase.getFieldName()).append(")");
		unitBuffer.append("!=");
		unitBuffer.append("lower(").append(this.ecap(filterCase.getValue())).append(")");
		return unitBuffer;
	}

	private StringBuffer parseEqIgnoreCase(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append("lower(").append(filterCase.getFieldName()).append(")");
		unitBuffer.append("=");
		unitBuffer.append("lower(").append(this.ecap(filterCase.getValue())).append(")");
		return unitBuffer;
	}

	private StringBuffer parseLike(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(filterCase.getFieldName());
		unitBuffer.append(HibernateFilterParser.space);
		unitBuffer.append(" like ");
		unitBuffer.append(this.ecap(filterCase.getValue()));
		return unitBuffer;
	}

	private StringBuffer parseNotLike(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(filterCase.getFieldName());
		unitBuffer.append(HibernateFilterParser.space);
		unitBuffer.append(" not like ");
		unitBuffer.append(this.ecap(filterCase.getValue()));
		return unitBuffer;
	}

	@SuppressWarnings("unchecked")
	private StringBuffer parseBetween(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append(" ( ");
		unitBuffer.append(filterCase.getFieldName());
		unitBuffer.append(HibernateFilterParser.space);
		unitBuffer.append(" between ");
		Map<String, Object> value0 = (Map<String, Object>) filterCase.getValue();
		unitBuffer.append(this.ecap(value0.get(IFilter.LEFT_VALUE)));
		unitBuffer.append(" and ");
		unitBuffer.append(this.ecap(value0.get(IFilter.RIGHT_VALUE)));
		unitBuffer.append(" ) ");
		return unitBuffer;
	}

	@SuppressWarnings("unchecked")
	private StringBuffer parseNotBetween(StringBuffer unitBuffer, FilterCase filterCase) {
		unitBuffer.append("(");
		unitBuffer.append(filterCase.getFieldName());
		unitBuffer.append("<");
		Map<String, Object> value1 = (Map<String, Object>) filterCase.getValue();
		unitBuffer.append(this.ecap(value1.get(IFilter.LEFT_VALUE)));
		unitBuffer.append(" and ");
		unitBuffer.append(filterCase.getFieldName());
		unitBuffer.append(">");
		unitBuffer.append(this.ecap(value1.get(IFilter.RIGHT_VALUE)));
		unitBuffer.append(")");
		return unitBuffer;
	}

	private StringBuffer parseIn(StringBuffer unitBuffer, FilterCase filterCase, boolean in) throws Exception {
		unitBuffer.append(filterCase.getFieldName());
		unitBuffer.append(HibernateFilterParser.space);
		if (in) {
			unitBuffer.append(" in( ");
		} else {
			unitBuffer.append(" not in( ");
		}

		if (filterCase.getValue() instanceof Object[]) {
			Object[] values = (Object[]) filterCase.getValue();
			for (Object tmp : values) {
				unitBuffer.append(this.ecap(tmp));
				unitBuffer.append(",");
			}
		} else if (filterCase.getValue() instanceof Collection) {
			Collection values = (Collection) filterCase.getValue();
			for (Object tmp : values) {
				unitBuffer.append(this.ecap(tmp));
				unitBuffer.append(",");
			}
		} else {
			log.error("invalid params container type");
			throw new Exception("invalid params container type");
		}
		return unitBuffer;
	}

	private Object ecap(Object value) {
		Object rt = null;
		if (value instanceof String) {
			rt = "'" + InjectFilter.filterSqlInject(value.toString()) + "'";
		} else if (value instanceof Date) {
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			rt = "'" + sf.format((Date) value) + "'";
		} else {
			rt = value;
		}
		return rt;
	}

}
