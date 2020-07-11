package jp.co.introduction.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jp.co.introduction.entity.ItemDetailEntity;
import jp.co.introduction.entity.ItemDetailRowMapper;
import jp.co.introduction.entity.ItemEntity;
import jp.co.introduction.entity.ItemRowMapper;

@Component
public class ItemDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<ItemEntity> getItems() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  ITEM_CODE ");
		sql.append("  , ITEM_NAME ");
		sql.append("  , PRICE  ");
		sql.append("  , MAKER  ");
		sql.append("FROM ");
		sql.append("  ITEM ");
		sql.append("WHERE ");
		sql.append("  DELETE_FLG = 0 "); // 削除フラグ(非論理削除)
		List<ItemEntity> fruitList = jdbcTemplate.query(sql.toString(), new ItemRowMapper());
		return fruitList;
	}

	public ItemDetailEntity getItemDetail(String itemCode) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("  ITEM_CODE ");
		sql.append("  , ITEM_NAME ");
		sql.append("  , PRICE  ");
		sql.append("  , TAX_RATE  ");
		sql.append("  , TAX_TYPE  ");
		sql.append("  , MAKER  ");
		sql.append("  , DESCRIPTION  ");
		sql.append("FROM ");
		sql.append("  ITEM ");
		sql.append("WHERE ");
		sql.append("  ITEM_CODE = ? "); // 商品コード
		sql.append("  AND DELETE_FLG = 0 "); // 削除フラグ(非論理削除)
		ItemDetailEntity itemDetail = jdbcTemplate.queryForObject(sql.toString(), new ItemDetailRowMapper(), itemCode);
		return itemDetail;
	}

	public boolean deleteFruit(int id) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE ");
		sql.append("FROM ");
		sql.append("  FRUIT ");
		sql.append("WHERE ");
		sql.append("  ID = ? ");
		int resultCount = jdbcTemplate.update(sql.toString(), id);
		return resultCount == 1;
	}
}
