package com.egroup.util.entity;

import com.egroup.util.AttributeCheck;

public class WhereGenerator {
	private final AttributeCheck attributeCheck = new AttributeCheck();
	private String whereSql;
	private LikeGenerator likeGenerator = new LikeGenerator();
	private EqualGenerator equalGenerator = new EqualGenerator();

	public String getWhereSql(boolean b) {
		final String likeSql = likeGenerator.getLikeSql();
		final String equalSql = equalGenerator.getEqualSql();
		if (attributeCheck.stringsNotNull(likeSql) && attributeCheck.stringsNotNull(equalSql)) {
			whereSql = equalSql + " AND " + likeSql;
		} else if (attributeCheck.stringsNotNull(likeSql)) {
			whereSql = likeSql;
		} else {
			whereSql = equalSql;
		}
		return whereSql;
	}

	public LikeGenerator getLikeGenerator() {
		return likeGenerator;
	}

	public void setLikeGenerator(LikeGenerator likeGenerator) {
		this.likeGenerator = likeGenerator;
	}

	public EqualGenerator getEqualGenerator() {
		return equalGenerator;
	}

	public void setEqualGenerator(EqualGenerator equalGenerator) {
		this.equalGenerator = equalGenerator;
	}

}
