package jp.co.introduction.service;

import java.util.ArrayList;

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
     * @param reqModel
     *            リクエストモデル
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
            resModel.setItemList(ichibaItemModel.getItemList());
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