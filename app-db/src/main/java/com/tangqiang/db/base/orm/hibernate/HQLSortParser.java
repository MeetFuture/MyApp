package com.tangqiang.db.base.orm.hibernate;

import java.util.Set;

import com.tangqiang.db.base.entity.enumaration.SortType;
import com.tangqiang.db.base.entity.util.ISortParser;
import com.tangqiang.db.base.entity.util.Sort;

public class HQLSortParser implements ISortParser {

	public static ISortParser newInstance() {
		return new HQLSortParser();
	}

	public String parse(Sort sort) {
		StringBuffer sortBuffer = new StringBuffer();
		if (sort != null && sort.getSorters() != null && sort.getSorters().size() > 0) {
			sortBuffer.append(" order by");
			Set<String> nameSet = sort.getSorters().keySet();
			for (String key : nameSet) {
				sortBuffer.append(" ").append(key).append(" ");
				if (sort.getSorters().get(key) == SortType.DESC.getValue()) {
					sortBuffer.append(ISortParser.DESC);
				} else {
					sortBuffer.append(ISortParser.ASC);
				}
			}
		}

		return sortBuffer.toString();
	}

}
