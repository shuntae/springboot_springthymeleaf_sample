package jp.co.introduction.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jp.co.introduction.common.model.req.AddItemReqModel;
import jp.co.introduction.entity.ItemDetailEntity;
import jp.co.introduction.entity.ItemDetailRowMapper;
import jp.co.introduction.entity.ItemEntity;
import jp.co.introduction.entity.ItemRowMapper;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
		try {
			return jdbcTemplate.queryForObject(sql.toString(), new ItemDetailRowMapper(), itemCode);
		} catch (Exception e) {
			return null;
		}
	}

	public boolean addItem(AddItemReqModel reqModel) {
		log.info("### パラメータ", reqModel.toString());
		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList();
		sql.append("INSERT INTO ITEM ");
		sql.append("( ");
		sql.append("  ITEM_CODE ");
		sql.append("  , ITEM_NAME ");
		sql.append("  , PRICE ");
		sql.append("  , TAX_RATE ");
		sql.append("  , TAX_TYPE ");
		sql.append("  , MAKER ");
		sql.append("  , DESCRIPTION ");
		sql.append("  , CREATED_USER ");
		sql.append("  , CREATED_DATE ");
		sql.append("  , UPDATED_USER ");
		sql.append("  , UPDATED_DATE ");
		sql.append("  , DELETE_FLG ");
		sql.append(") VALUE ( ");
		sql.append("  ? ");
		paramList.add(reqModel.getItemCode());
		sql.append("  , ? ");
		paramList.add(reqModel.getItemName());
		sql.append("  , ? ");
		paramList.add(reqModel.getPrice());
		sql.append("  , ? ");
		paramList.add(reqModel.getTaxRate());
		sql.append("  , ? ");
		paramList.add(reqModel.getTaxType());
		sql.append("  , ? ");
		paramList.add(reqModel.getMaker());
		sql.append("  , ? ");
		paramList.add(reqModel.getDescription());
		sql.append("  , ? ");
		paramList.add("ItemDao#addItem");
		sql.append("  , ? ");
		paramList.add(new Date());
		sql.append("  , ? ");
		paramList.add("ItemDao#addItem");
		sql.append("  , ? ");
		paramList.add(new Date());
		sql.append("  , ? ");
		paramList.add("0"); // 削除フラグの定数は後で作る
		sql.append(") ");
		int resultCount = 0;
		try {
			resultCount = jdbcTemplate.update(sql.toString(), paramList);
		} catch (Exception e) {
			log.error("### 例外発生", e);
			return false;
		}
		return resultCount == 1;
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
