package jp.co.introduction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.model.req.AddItemReqModel;
import jp.co.introduction.common.model.res.ItemDetailResModel;
import jp.co.introduction.common.model.res.ItemsResModel;
import jp.co.introduction.service.ItemService;
import lombok.extern.slf4j.Slf4j;

/**
 * コントローラクラス
 *
 * <p>
 * エンドポイント実装クラス<br>
 * アノテーションの引数チェックを行う。<br>
 * 業務ロジックは書かず、サービスクラスの呼び出しが主<br>
 * １メソッド＝１エンドポイントというイメージ
 */
@RestController // コントローラクラスに付与 付与することでDIコンテナに登録される。
@Slf4j
public class ItemController {

	@Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
	public ItemService itemService;

	@RequestMapping(method = RequestMethod.GET, path = "/v1/items") // HttpMethodとエンドポイントの指定を行う
	public ItemsResModel getItems() {
		return itemService.getItems();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/v1/items/{item_code}") // HttpMethodとエンドポイントの指定を行う
	public ItemDetailResModel getItemDetail(@PathVariable("item_code") String itemCode) {
		return itemService.getDetail(itemCode);
	}

	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 画面より入力された情報で商品テーブル（ITEM）への商品情報登録を行う。
	 * 
	 * @param reqModel 画面の入力情報
	 * @param result   入力情報の検証結果
	 * @return 登録結果
	 */
	@PostMapping(path = "/v1/items", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE) // HttpMethodとエンドポイントの指定を行う
	public BaseResModel addItem(@RequestBody @Valid AddItemReqModel reqModel, BindingResult result) {
		// リクエスト情報に不正な値が存在していることをチェック
		if (result.hasErrors()) {
			// 不正と検知された入力項目とエラー情報をログに出力
			result.getFieldErrors().stream()
					.forEach(e -> log.error("# 入力不正フィールド:{}, 理由:{}", e.getField(), e.getDefaultMessage()));
			// エラーとして返却
			BaseResModel resModel = new BaseResModel();
			resModel.setError(true);
			resModel.setMessage("入力値に不正が見られます。");
			return resModel;
		}

		// 商品登録処理呼び出し
		return itemService.addItem(reqModel);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/v1/items/{id}")
	public BaseResModel deleteFruit(@PathVariable(name = "id") int id) {

		return itemService.deleteFruit(id);
	}
}
