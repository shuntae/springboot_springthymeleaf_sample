package jp.co.introduction.batch.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerEntity {

	/**
	 * SQL実行結果の詰め替えを行う。
	 *
	 * @param rs SQL実行結果
	 * @throws SQLException SQL例外
	 */
	public CustomerEntity(ResultSet rs) throws SQLException {
		this.setCustomerCode(rs.getString("CUSTOMER_CODE"));
		this.setFirstName(rs.getString("FIRST_NAME"));
	}

	private String customerCode;
	private String firstName;
}
