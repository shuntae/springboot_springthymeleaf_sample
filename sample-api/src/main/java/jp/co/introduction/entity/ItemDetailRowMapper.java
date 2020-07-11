package jp.co.introduction.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ItemDetailRowMapper implements RowMapper<ItemDetailEntity> {
	@Override
	public ItemDetailEntity mapRow(ResultSet row, int rowNum) throws SQLException {
		return new ItemDetailEntity(row);
	}
}
