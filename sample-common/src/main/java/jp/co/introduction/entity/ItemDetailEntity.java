package jp.co.introduction.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDetailEntity {

	/**
	 * SQL実行結果の詰め替えを行う。
	 *
	 * @param rs SQL実行結果
	 * @throws SQLException SQL例外
	 */
	public ItemDetailEntity(ResultSet rs) throws SQLException {
		this.setItemCode(rs.getString("itemCode"));
		this.setItemName(rs.getString("itemName"));
		this.setPrice(rs.getInt("price"));
		this.setTaxRate(rs.getInt("taxRate"));
		this.setTaxType(rs.getInt("taxType"));
		this.setMaker(rs.getString("maker"));
		this.setDescription(rs.getString("description"));
	}

	private String itemCode;
	private String itemName;
	private int price;
	private int taxRate;
	private int taxType;
	private String maker;
	private String description;
}
