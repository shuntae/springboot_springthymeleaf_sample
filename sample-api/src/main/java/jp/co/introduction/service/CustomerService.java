package jp.co.introduction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.model.req.CustomerRegisterReqModel;
import jp.co.introduction.logic.CustomerLogic;

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
public class CustomerService {

	@Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
	public CustomerLogic CustomerLogic;


	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 
	 * @param reqModel リクエスト情報
	 * @return 登録結果
	 */
	public BaseResModel addCustomer(CustomerRegisterReqModel reqModel) {

		boolean insertResult = CustomerLogic.addCustomer(reqModel);
		String message = "customerCode:" + reqModel.getCustomerCode() + "の登録に";
		message += insertResult ? "成功しました。" : "失敗しました。";

		BaseResModel resModel = new BaseResModel();
		resModel.setSuccess(true);
		resModel.setMessage(message);

		return resModel;
	}


}
