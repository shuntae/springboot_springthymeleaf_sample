package jp.co.introduction.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.introduction.common.model.IchibaItemModel;
import jp.co.introduction.common.model.ItemModel;
import jp.co.introduction.common.model.req.SearchReqModel;
import jp.co.introduction.common.model.res.SearchResModel;
import jp.co.introduction.logic.SearchLogic;

/**
 * サービスクラス
 * 
 * 業務ロジックを行うロジッククラスを呼び出し、レスポンスを組み立てる層
 * 「@Transactional」アノテーションを利用してトランザクションの開始を行うことが多い。 引数の相関チェックもここで行う。
 * ロジッククラスは複数呼び出してもOK ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Service // サービスクラスに付与するアノテーション 付与することでDIコンテナに登録される。
public class SearchService {

	@Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
	public SearchLogic searchLogic;

	/**
	 * 楽天市場の商品検索APIの呼び出しを行い、総取得件数と商品リストを返却する。
	 * 
	 * @param reqModel リクエストモデル
	 * @return 取得結果
	 */
	public SearchResModel searchItem(SearchReqModel reqModel) {

		// 楽天市場の商品検索API呼び出し
		IchibaItemModel ichibaItemModel = searchLogic.callIchibaSearchAPI(reqModel);

		SearchResModel resModel = new SearchResModel();
		// レスポンスが取得できていることをチェック
		if (ichibaItemModel != null) {
			// 総取得件数と商品リストを返却
			resModel.setHits(ichibaItemModel.getHits());
			ichibaItemModel.getItemList().forEach(e -> {
				// 商品コードの余計な文字列を削除(ex/"AAA:111" -> "111")
				e.setItemCode(e.getItemCode().split(":")[1]);
				// 商品名に【】が含まれている場合remove
				e.setItemName(e.getItemName().replaceAll("【.*】", " "));
			});
			// 不要データの削除
			List<ItemModel> itemList = ichibaItemModel.getItemList().stream().filter(e -> !e.getItemName().equals(" "))
					.collect(Collectors.toList());
			// リストの再設定
			ichibaItemModel.setItemList(itemList);
			resModel.setItemList(ichibaItemModel.getItemList());

			// ITEMテーブルへ商品の登録
			searchLogic.addItemData(itemList);

		} else {
			// 初期値を設定して返却
			resModel.setHits(0);
			resModel.setItemList(new ArrayList<ItemModel>());
		}

		// 成功を表すsuccessを設定
		resModel.setSuccess(true);

		return resModel;
	}
}