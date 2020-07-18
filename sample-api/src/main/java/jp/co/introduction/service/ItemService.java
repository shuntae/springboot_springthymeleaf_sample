package jp.co.introduction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.entity.ItemDetailEntity;
import jp.co.introduction.common.entity.ItemEntity;
import jp.co.introduction.common.model.req.GetItemReqModel;
import jp.co.introduction.common.model.req.ItemRegisterReqModel;
import jp.co.introduction.common.model.res.ItemDetailResModel;
import jp.co.introduction.common.model.res.ItemsResModel;
import jp.co.introduction.logic.ItemLogic;

/**
 * サービスクラス
 *
 * <p>
 * 業務ロジックを行うロジッククラスを呼び出し、レスポンスを組み立てる層<br>
 * 「@Transactional」アノテーションを利用してトランザクションの開始を行うことが多い。<br>
 * 引数の相関チェックもここで行う。<br>
 * ロジッククラスは複数呼び出してもOK<br>
 * ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Service // サービスクラスに付与するアノテーション 付与することでDIコンテナに登録される。
public class ItemService {

	@Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
	public ItemLogic ItemLogic;

	public ItemsResModel getItems(GetItemReqModel reqModel) {

		List<ItemEntity> itemList = ItemLogic.getItems();
		String message = "データ取得に成功しました。データの取得件数は" + itemList.size() + "件です。";

		ItemsResModel resModel = new ItemsResModel();
		resModel.setItemList(itemList);

		resModel.setSuccess(true);
		resModel.setMessage(message);

		return resModel;
	}

	/**
	 * <p>
	 * 商品詳細取得
	 * <p/>
	 * 商品コードを基に、商品テーブル（ITEM）へ商品詳細情報の取得を行う。
	 * 
	 * @param itemCode 商品コード
	 * @return 商品詳細情報
	 */
	public ItemDetailResModel getDetail(String itemCode) {

		ItemDetailEntity itemDetail = ItemLogic.getItemDetail(itemCode);
		String message;
		if (itemDetail != null) {
			message = "該当データ有り";
		} else {
			message = "該当データ無し";
		}

		ItemDetailResModel resModel = new ItemDetailResModel();
		resModel.setItemDetail(itemDetail);

		resModel.setSuccess(true);
		resModel.setMessage(message);

		return resModel;
	}

	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 
	 * @param reqModel リクエスト情報
	 * @return 登録結果
	 */
	public BaseResModel addItem(ItemRegisterReqModel reqModel) {

		boolean insertResult = ItemLogic.addItem(reqModel);
		String message = "itemCode:" + reqModel.getItemCode() + "の登録に";
		message += insertResult ? "成功しました。" : "失敗しました。";

		BaseResModel resModel = new BaseResModel();
		resModel.setSuccess(true);
		resModel.setMessage(message);

		return resModel;
	}

	public BaseResModel deleteFruit(int id) {

		boolean deleteResult = ItemLogic.deleteFruit(id);
		String message = "id:" + id + "の削除に";
		message += deleteResult ? "成功しました。" : "失敗しました。レコードが存在しません。";

		BaseResModel resModel = new BaseResModel();

		resModel.setSuccess(true);
		resModel.setMessage(message);

		return resModel;
	}
}
