package jp.co.introduction.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jp.co.introduction.base.constants.SqlConst;
import jp.co.introduction.common.model.req.CustomerRegisterReqModel;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomerDao {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private JdbcTemplate jdbcTemplate;


	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 商品テーブルへ商品データの登録を行う。
	 * 
	 * @param reqModel リクエスト情報
	 * @return 商品登録結果
	 */
	public boolean addCustomer(CustomerRegisterReqModel reqModel) {
		// 実行するSQL
		StringBuilder sql = new StringBuilder();
		// INSERTするデータのパラメーターリスト
		List<Object> paramList = new ArrayList<Object>();
		/** 商品テーブルへ商品データを登録 */
		sql.append("INSERT INTO CUSTOMER");
		sql.append("( ");
		sql.append("  CUSTOMER_CODE ");
		sql.append("  , FIRST_NAME ");
		sql.append("  , LAST_NAME ");
		sql.append("  , POSTAL_CODE ");
		sql.append("  , PREF_CODE ");
		sql.append("  , ADDRESS1 ");
		sql.append("  , ADDRESS2 ");
		sql.append("  , ADDRESS3 ");
		sql.append("  , ADDRESS4 ");
		sql.append("  , CUSTOMER_STATUS ");
		sql.append("  , CREATED_USER ");
		sql.append("  , CREATED_DATE ");
		sql.append("  , UPDATED_USER ");
		sql.append("  , UPDATED_DATE ");
		sql.append("  , DELETE_FLG ");
		sql.append(") VALUE ( ");
		sql.append("  ? "); // 会員ID
		paramList.add(reqModel.getCustomerCode());
		sql.append("  , ? "); // 姓
		paramList.add(reqModel.getFirstName());
		sql.append("  , ? "); // 名
		paramList.add(reqModel.getLastName());
		sql.append("  , ? "); // 郵便番号
		paramList.add(reqModel.getPostalCode());
		sql.append("  , ? "); // 都道府県コード
		paramList.add(reqModel.getPrefCode());
		sql.append("  , ? "); // 住所１
		paramList.add(reqModel.getAddress1());
		sql.append("  , ? "); // 住所2
		paramList.add(reqModel.getAddress2());
		sql.append("  , ? "); // 住所3
		paramList.add(reqModel.getAddress3());
		sql.append("  , ? "); // 住所4
		paramList.add(reqModel.getAddress4());
		sql.append("  , ? "); // 会員ステータス
		paramList.add("0");
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


}
