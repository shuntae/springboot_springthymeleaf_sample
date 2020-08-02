package jp.co.introduction.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/** データベース書き込み用クラス */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerUpdateModel {
	/** 会員コード */
	private String customerCode;
	/** 会員ステータス */
	private int customerStatus;
}
