package jp.co.introduction.batch.processor;

import org.springframework.batch.item.ItemProcessor;

import jp.co.introduction.batch.entity.CustomerEntity;
import jp.co.introduction.batch.model.CustomerUpdateModel;

/**
 * Processor<br>
 * Readerで読み込んだデータの加工を行い、データベース更新用モデルを返却する
 */
public class UpdateCustomerStatusServiceProcessor implements ItemProcessor<CustomerEntity, CustomerUpdateModel> {

	@Override
	public CustomerUpdateModel process(CustomerEntity customer) throws Exception {
		// 会員コードと姓を取得
		String customerCode = customer.getCustomerCode();
		String firstName = customer.getFirstName();

		// デフォルトの会員ステータス=0(通常ユーザ)
		int customerStatus = 0;
		// ユーザ名が山田の場合
		if (firstName.equals("山田")) {
			customerStatus = 9; // 会員ステータスを9(退会済みユーザ)に変更
		}
		// 更新用データ返却
		return new CustomerUpdateModel(customerCode, customerStatus);
	}
}
