package jp.co.introduction.common.model.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchReqModel {

    /** 検索キーワード */
    // UTF-8でURLエンコードした文字列
    // 検索キーワード全体は半角で128文字以内で指定する必要があります。
    // 検索キーワードは半角スペースで区切ることができ、デフォルトではAND条件 (すべてのキーワードが含まれるものを検索 ) になります。
    // もし、OR条件 (いずれかのキーワードが含まれるものを検索) を利用したい場合は、 orFlg を 1 に設定してください。
    // 各検索キーワードは半角2文字 もしくは 全角1文字 以上で指定する必要があります。
    // また例外として、各検索キーワードがひらがな・カタカナ・記号の場合は2文字以上で指定する必要があります。
    // (*1)検索キーワード、ジャンルID、商品コード、ショップコードのいずれかが指定されていることが必須です。
    private String keyword;

    /** ジャンルID */
    // 楽天市場におけるジャンルを特定するためのID
    // ジャンル名、ジャンルの親子関係を調べたい場合は、「楽天ジャンル検索API」をご利用ください
    // (*1)検索キーワード、ジャンルID、商品コード、ショップコードのいずれかが指定されていることが必須です。
    private String genreId;

    /** 取得件数 */
    // 1から30までの整数
    @Max(value = 30)
    @Min(value = 1)
    private Integer hits;

    /** 取得ページ */
    // 1から100までの整数
    @Max(value = 100)
    @Min(value = 1)
    private Integer page;

}