package jp.co.introduction.common.model.req;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import jp.co.introduction.base.model.BaseReqModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRegisterReqModel extends BaseReqModel {

	@NotBlank
	private String customerCode;// 会員ID
	@NotBlank
	private String firstName;// 性
	@NotBlank
	private String lastName;// 名
	@NotBlank
	@Size(max = 7)
	private String postalCode;// 郵便番号
	@NotNull
	@Max(99)
	private Integer prefCode;// 都道府県コード
	@NotBlank
	private String address1;// zyuusyo1
	@NotBlank
	private String address2;// zyuusyo2
	@NotBlank
	private String address3;// zyuusyo3
	@NotBlank
	private String address4;// zyuusyo4

}