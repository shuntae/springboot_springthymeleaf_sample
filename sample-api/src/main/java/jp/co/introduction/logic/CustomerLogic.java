package jp.co.introduction.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jp.co.introduction.common.model.req.CustomerRegisterReqModel;
import jp.co.introduction.dao.CustomerDao;
import lombok.extern.slf4j.Slf4j;

/**
 * ロジッククラス
 *
 * <p>
 * DBとのやりとりを行うDaoクラスを使用したり、業務ロジックを実装する役割の層<br>
 * Daoクラスやロジッククラスは複数呼び出してもOK<br>
 * ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Component // DIコンテナにBeanとして登録するためのアノテーション
@Repository // Daoクラスを使ってDBとのやりとりを行うクラスに付与する
@Slf4j
public class CustomerLogic {

	// 定数はconstantsパッケージを作ってそこにまとめるのがベスト
	// 今回はフィールドに定義しちゃってます。
	@Autowired
	private CustomerDao customerDao;

	/**
	 * <p>
	 * 商品登録
	 * <p/>
	 * 	
	 * 登録済みの商品は登録処理をSKIPする。 未登録の商品は登録処理を行う。
	 * 
	 * @param reqModel リクエスト情報
	 * @return 登録結果
	 */
	public boolean addCustomer(CustomerRegisterReqModel reqModel) {

		// 商品登録処理呼び出し
		return customerDao.addCustomer(reqModel);
	}

	
}
