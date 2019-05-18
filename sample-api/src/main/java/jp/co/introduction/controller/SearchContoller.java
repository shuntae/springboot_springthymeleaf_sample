package jp.co.introduction.controller;

import java.security.InvalidParameterException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.introduction.common.model.req.SearchReqModel;
import jp.co.introduction.common.model.res.SearchResModel;
import jp.co.introduction.service.SearchService;

/**
 * コントローラクラス
 * 
 * エンドポイント実装クラス アノテーションの引数チェックを行う。 業務ロジックは書かず、サービスクラスの呼び出しが主
 * １メソッド＝１エンドポイントというイメージ
 */
@RestController // コントローラクラスに付与 付与することでDIコンテナに登録される。
public class SearchContoller {

    @Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
    public SearchService searchService;

    @RequestMapping(method = RequestMethod.GET, path = "/v1/items") // HttpMethodとエンドポイントの指定を行う
    public SearchResModel getItems(@Valid SearchReqModel reqModel, BindingResult result) {

        // 引数チェック結果判定(アノテーションチェック結果)
        if (result.hasErrors()) {
            // エラー情報を例外クラスの引数に設定＆例外スロー
            throw new InvalidParameterException(result.getAllErrors().toString());
        }
        // サービスクラス呼び出し
        return searchService.searchItem(reqModel);
    }
}