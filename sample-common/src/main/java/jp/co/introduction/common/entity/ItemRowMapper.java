package jp.co.introduction.common.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ItemRowMapper implements RowMapper<ItemEntity> {
	@Override
	public ItemEntity mapRow(ResultSet row, int rowNum) throws SQLException {
		return new ItemEntity(row);
	}
}
