package jp.co.introduction.biz.util;

import org.springframework.validation.BindingResult;

import jp.co.introduction.base.model.BaseResModel;
import lombok.extern.slf4j.Slf4j;

/**
 * APIUtilクラス APIの処理で使用する共通処理をまとめる
 */
@Slf4j
public class APIUtil {

	/**
	 * 入力チェックでエラーになった情報の出力を行い、エラーレスポンスを返却する。
	 * 
	 * @param result 入力チェックエラー情報
	 * 
	 * @return エラーレスポンス
	 */
	public static BaseResModel printErrorInfo(BindingResult result) {
		// 不正と検知された入力項目とエラー情報をログに出力
		result.getFieldErrors().stream()
				.forEach(e -> log.error("# 入力不正フィールド:{}, 理由:{}", e.getField(), e.getDefaultMessage()));
		// エラーとして返却
		BaseResModel resModel = new BaseResModel();
		resModel.setError(true);
		resModel.setMessage("入力値に不正が見られます。");
		return resModel;
	}
}