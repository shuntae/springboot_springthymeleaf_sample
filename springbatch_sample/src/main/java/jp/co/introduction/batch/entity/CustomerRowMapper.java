package jp.co.introduction.batch.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CustomerRowMapper implements RowMapper<CustomerEntity> {
	@Override
	public CustomerEntity mapRow(ResultSet row, int rowNum) throws SQLException {
		return new CustomerEntity(row);
	}
}
