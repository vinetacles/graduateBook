package com.egroup.util;

import com.egroup.util.entity.LimitGenerator;
import com.egroup.util.entity.OrderGenerator;
import com.egroup.util.entity.WhereGenerator;

public class SqlUtil {
	private WhereGenerator whereGenerator = new WhereGenerator();
	private OrderGenerator orderGenerator = new OrderGenerator();
	private LimitGenerator limitGenerator = new LimitGenerator();

	public WhereGenerator getWhereGenerator() {
		return whereGenerator;
	}

	public void setWhereGenerator(WhereGenerator whereGenerator) {
		this.whereGenerator = whereGenerator;
	}

	public OrderGenerator getOrderGenerator() {
		return orderGenerator;
	}

	public void setOrderGenerator(OrderGenerator orderGenerator) {
		this.orderGenerator = orderGenerator;
	}

	public LimitGenerator getLimitGenerator() {
		return limitGenerator;
	}

	public void setLimitGenerator(LimitGenerator limitGenerator) {
		this.limitGenerator = limitGenerator;
	}

}
