package jp.co.introduction.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jp.co.introduction.common.model.req.AddItemReqModel;
import jp.co.introduction.dao.ItemDao;
import jp.co.introduction.entity.ItemDetailEntity;
import jp.co.introduction.entity.ItemEntity;
import lombok.extern.slf4j.Slf4j;

/**
 * ロジッククラス
 *
 * <p>
 * DBとのやりとりを行うDaoクラスを使用したり、業務ロジックを実装する役割の層<br>
 * Daoクラスやロジッククラスは複数呼び出してもOK<br>
 * ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Component // DIコンテナにBeanとして登録するためのアノテーション
@Repository // Daoクラスを使ってDBとのやりとりを行うクラスに付与する
@Slf4j
public class ItemLogic {

	// 定数はconstantsパッケージを作ってそこにまとめるのがベスト
	// 今回はフィールドに定義しちゃってます。
	@Autowired
	private ItemDao itemDao;

	public List<ItemEntity> getItems() {
		return itemDao.getItems();
	}

	public ItemDetailEntity getItemDetail(String itemCode) {
		return itemDao.getItemDetail(itemCode);
	}

	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 
	 * 登録済みの商品は登録処理をSKIPする。 未登録の商品は登録処理を行う。
	 * 
	 * @param reqModel リクエスト情報
	 * @return 登録結果
	 */
	public boolean addItem(AddItemReqModel reqModel) {
		// 既に登録済みのデータでないことをチェック
		if (itemDao.getItemDetail(reqModel.getItemCode()) != null) {
			log.info("# 既に登録済みのデータのため、登録処理をSKIPします。");
			return false;
		}
		// 商品登録処理呼び出し
		return itemDao.addItem(reqModel);
	}

	public boolean deleteFruit(int id) {
		return itemDao.deleteFruit(id);
	}
}
