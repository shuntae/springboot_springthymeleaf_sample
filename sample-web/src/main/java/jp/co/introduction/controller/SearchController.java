package jp.co.introduction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import jp.co.introduction.common.biz.util.RestTemplateLogic;
import jp.co.introduction.common.model.res.SearchResModel;
import jp.co.introduction.form.SearchForm;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping(value = "/search")
public class SearchController {

	@Autowired
	private RestTemplateLogic restTemplatelogic;

	/**
	 * 検索ボタンが押下されたときに呼び出されるメソッド 検索ワードを元にsample-apiの呼び出しを行う
	 * 
	 * @param form
	 * @param model
	 * @return
	 */
	@GetMapping
	public String index(SearchForm form, Model model) {

		// 入力チェック
		if (form.getKeyword() == null || form.getKeyword().isEmpty()) {
			// 未入力または空の場合は検索画面に遷移
			return "search";
		}

		/** APIの呼び出し */
		// ホスト名、API名、エンドポイントを指定してビルダーを生成
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:1192/sample-api/v1/search");
		// API呼び出しに必要なパラメータを設定
		builder.queryParam("keyword", form.getKeyword());
		// APIの呼び出し実行
		SearchResModel resModel = restTemplatelogic.get(builder, SearchResModel.class);

		/** APIのレスポンスチェック */
		// nullチェック
		if (resModel == null) {
			// HttpRequestで200以外が返却されたケース
			System.err.println("APIの呼び出しに失敗しました。");
		}
		// 呼び出し結果のチェック
		if (!resModel.isSuccess()) {
			// APIの処理中に予期せぬ例外が発生したケース
			System.err.println("APIの処理中に予期せぬ例外が発生しました。");
		} else {
			System.out.println("APIの呼び出しに成功しました。");
			System.out.println("### response = " + resModel.toString());
		}

		/** テンプレートで利用するmodelに値を設定 */
		// 商品リストの設定
		model.addAttribute("items", resModel.getItemList());
		// メッセージの設定
		model.addAttribute("message", String.valueOf(resModel.getHits()) + "件ヒットしました。(※検索上限は30件)");

		// 検索画面に遷移
		return "search";
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
}
