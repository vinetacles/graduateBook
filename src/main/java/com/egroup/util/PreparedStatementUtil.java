package com.egroup.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

public class PreparedStatementUtil {
	public static PreparedStatement convertInt(int target, Integer content, PreparedStatement smt) {
		if (content != null) {
			try {
				smt.setInt(target, content);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				smt.setNull(target, java.sql.Types.INTEGER);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return smt;
	}

	public static PreparedStatement convert(int target, Date content, PreparedStatement smt) {
		try {
			if (content != null) {
				final Timestamp contentDate = new Timestamp(content.getTime());
				smt.setTimestamp(target, contentDate);
			} else {
				smt.setNull(target, java.sql.Types.DATE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return smt;
	}
}
