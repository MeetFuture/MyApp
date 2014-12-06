package com.tangqiang.db.base.orm;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tangqiang.db.base.entity.IFilter;
import com.tangqiang.db.base.entity.IFilter.FilterCase;
import com.tangqiang.db.base.entity.util.IFilterParser;

public abstract class FilterParser implements IFilterParser {
	private static Logger log = LoggerFactory.getLogger(FilterParser.class);
	private static final String space = " ";

	private String parseUnitCase(FilterCase filterCase) {
		return null;
	}

	private String parseGroupCase(FilterCase filterCase) {
		return null;
	}

	public String parse(IFilter filter) {
		StringBuffer filterBuffer = new StringBuffer();
		List<FilterCase> filterCases = filter.getFilterCases();
		for (FilterCase tmp : filterCases) {
			// filterBuffer.append(b)
		}
		return filterBuffer.toString();
	}

	private String parseCase(FilterCase filterCase) {
		StringBuffer unitBuffer = new StringBuffer();
		switch (filterCase.getCaseType()) {
		case UNIT:
			unitBuffer.append(this.parseUnitCase(filterCase));
			break;
		case GROUP:
			unitBuffer.append(this.parseGroupCase(filterCase));
			break;
		}
		return unitBuffer.toString();
	}

}
