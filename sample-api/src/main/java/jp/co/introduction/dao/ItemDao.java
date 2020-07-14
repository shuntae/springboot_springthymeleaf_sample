package jp.co.introduction.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jp.co.introduction.base.constants.SqlConst;
import jp.co.introduction.common.entity.ItemDetailEntity;
import jp.co.introduction.common.entity.ItemDetailRowMapper;
import jp.co.introduction.common.entity.ItemEntity;
import jp.co.introduction.common.entity.ItemRowMapper;
import jp.co.introduction.common.model.req.ItemRegisterReqModel;
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
		List<ItemEntity> item = jdbcTemplate.query(sql.toString(), new ItemRowMapper());
		return item;
	}

	/**
	 * <p>
	 * 商品詳細情報取得
	 * <p/>
	 * 商品コードを元に商品テーブルから商品詳細情報の取得を行う。
	 * 
	 * @param itemCode 商品コード
	 * @return 商品詳細情報
	 */
	public ItemDetailEntity getItemDetail(String itemCode) {
		// 実行するSQL
		StringBuilder sql = new StringBuilder();
		// INSERTするデータのパラメーターリスト
		List<Object> paramList = new ArrayList<Object>();
		/** 商品テーブルへ商品詳細情報を取得 */
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
		paramList.add(itemCode);
		sql.append("  AND DELETE_FLG = ? "); // 削除フラグ
		paramList.add(SqlConst.DELETE_FLG.NON_DELETE);

		try {
			return jdbcTemplate.queryForObject(sql.toString(), new ItemDetailRowMapper(), paramList.toArray());
		} catch (EmptyResultDataAccessException e) {
			log.info("### 商品コードに紐づくデータが見つかりませんでした。(商品コード={})", itemCode);
			return null;
		}
	}

	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 商品テーブルへ商品データの登録を行う。
	 * 
	 * @param reqModel リクエスト情報
	 * @return 商品登録結果
	 */
	public boolean addItem(ItemRegisterReqModel reqModel) {
		// 実行するSQL
		StringBuilder sql = new StringBuilder();
		// INSERTするデータのパラメーターリスト
		List<Object> paramList = new ArrayList<Object>();
		/** 商品テーブルへ商品データを登録 */
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
		sql.append("  ? "); // 商品コード
		paramList.add(reqModel.getItemCode());
		sql.append("  , ? "); // 商品名
		paramList.add(reqModel.getItemName());
		sql.append("  , ? "); // 価格
		paramList.add(reqModel.getPrice());
		sql.append("  , ? "); // 税率
		paramList.add(reqModel.getTaxRate());
		sql.append("  , ? "); // 税区分
		paramList.add(reqModel.getTaxType());
		sql.append("  , ? "); // メーカー
		paramList.add(reqModel.getMaker());
		sql.append("  , ? "); // 商品説明
		paramList.add(reqModel.getDescription());
		sql.append("  , ? "); // 作成ユーザ
		paramList.add("ItemDao#addItem");
		sql.append("  , ? "); // 作成日時
		paramList.add(new Date());
		sql.append("  , ? "); // 更新ユーザ
		paramList.add("ItemDao#addItem");
		sql.append("  , ? "); // 更新日時
		paramList.add(new Date());
		sql.append("  , ? "); // 削除フラグ
		paramList.add(SqlConst.DELETE_FLG.NON_DELETE);
		sql.append(") ");

		// 処理レコード数
		int resultCount = 0;
		try {
			resultCount = jdbcTemplate.update(sql.toString(), paramList.toArray());
		} catch (Exception e) {
			log.error("### 例外発生", e);
			return false;
		}

		// 処理件数が1である場合trueを返却
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
