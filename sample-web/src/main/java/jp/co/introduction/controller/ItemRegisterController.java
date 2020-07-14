package jp.co.introduction.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.biz.util.RestTemplateLogic;
import jp.co.introduction.common.model.req.ItemRegisterReqModel;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/item/register")
public class ItemRegisterController {

	@Autowired
	private RestTemplateLogic restTemplatelogic;

	/**
	 * 商品登録画面の初期表示
	 * 
	 * @param model 情報を引き継ぐためのモデル
	 * @return 遷移先画面のURL
	 */
	@GetMapping
	public String index(Model model) {
		/** テンプレートで利用するmodelに値を設定 */
		// 入力フォームの初期化
		model.addAttribute("itemRegistForm", new ItemRegisterReqModel());

		// 登録画面に遷移
		return "item/item_register";
	}

	/**
	 * 商品登録画面の登録処理
	 * 
	 * @param reqModel 画面で入力した情報
	 * @param model    情報を引き継ぐためのモデル
	 * @param result   画面で入力した情報の検証結果
	 * @return 遷移先画面のURL
	 */
	@PostMapping
	public String register(@Valid @ModelAttribute("itemRegistForm") ItemRegisterReqModel reqModel, BindingResult result,
			Model model) {

		// リクエスト情報に不正な値が存在していることをチェック
		if (result.hasErrors()) {
			// 登録画面に遷移
			return "item/item_register";
		}

		String endpoint = "http://localhost:1192/sample-api/v1/items";
		// 商品登録APIの呼び出し
		restTemplatelogic.post(endpoint, reqModel, BaseResModel.class);

		// 入力フォームの初期化
		model.addAttribute("itemRegistForm", new ItemRegisterReqModel());

		// 登録画面に遷移
		return "item/item_register";
	}
}
