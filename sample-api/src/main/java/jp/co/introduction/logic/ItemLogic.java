package jp.co.introduction.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jp.co.introduction.dao.ItemDao;
import jp.co.introduction.entity.ItemDetailEntity;
import jp.co.introduction.entity.ItemEntity;

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

	public boolean deleteFruit(int id) {
		return itemDao.deleteFruit(id);
	}
}
