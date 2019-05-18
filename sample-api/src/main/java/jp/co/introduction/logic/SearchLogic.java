package jp.co.introduction.logic;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.util.UriComponentsBuilder;

import jp.co.introduction.biz.util.RestTemplateUtil;
import jp.co.introduction.common.model.IchibaItemModel;
import jp.co.introduction.common.model.req.SearchReqModel;

/**
 * ロジッククラス
 * 
 * DBとのやりとりを行うDaoクラスを使用したり、業務ロジックを実装する役割の層 今回はDBの接続先がないため、Daoクラスは割愛します。
 * Daoクラスやロジッククラスは複数呼び出してもOK ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Component // DIコンテナにBeanとして登録するためのアノテーション
@Repository // Daoクラスを使ってDBとのやりとりを行うクラスに付与する
public class SearchLogic {

    // 定数はconstantsパッケージを作ってそこにまとめるのがベスト
    // 今回はフィールドに定義しちゃってます。
    private final String DEFAULT_GENRE = "0";
    private final int DEFAULT_HITS = 30;
    private final int DEFAULT_PAGE = 1;

    /**
     * 楽天市場の商品検索APIの呼び出しを行い、APIレスポンスを返却する。
     * ドキュメント：https://webservice.rakuten.co.jp/api/ichibaitemsearch/
     * 
     * @param reqModel
     *            リクエストモデル
     * @return 市場APIレスポンス
     */
    public IchibaItemModel callIchibaSearchAPI(SearchReqModel reqModel) {

        // ホスト名、API名、エンドポイントを指定してビルダーを生成
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://app.rakuten.co.jp/services/api/IchibaItem/Search/20170706");
        // API呼び出しに必要なパラメータを設定
        builder.queryParam("applicationId", "1073168092098951167");
        builder.queryParam("format", "json");
        builder.queryParam("formatVersion", "2");

        // 検索キーワードの指定
        if (reqModel.getKeyword() != null) {
            builder.queryParam("keyword", reqModel.getKeyword());
        }

        // ジャンルIDの指定(default:0（未指定))
        String genreId = Optional.ofNullable(reqModel.getGenreId()).orElse(DEFAULT_GENRE);
        builder.queryParam("genreId", genreId);

        // 取得件数の指定(default:30)
        int hits = Optional.ofNullable(reqModel.getHits()).orElse(DEFAULT_HITS);
        builder.queryParam("hits", hits);

        // 現在ページ番号の指定(default:1)
        int page = Optional.ofNullable(reqModel.getPage()).orElse(DEFAULT_PAGE);
        builder.queryParam("page", page);

        // ここにRedisやMemcachedなどでキャッシュを作る処理を入れると良い

        // API呼び出し＆レスポンス取得
        return RestTemplateUtil.get(builder, IchibaItemModel.class);
    }
}