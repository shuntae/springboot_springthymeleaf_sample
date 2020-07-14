package jp.co.introduction.common.model.req;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jp.co.introduction.base.model.BaseReqModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemRegisterReqModel extends BaseReqModel {

	@NotBlank
	private String itemCode;
	@NotBlank
	private String itemName;
	@NotNull
	private Integer price;
	@NotNull
	private Integer taxRate;
	@NotNull
	private Integer taxType;
	@NotBlank
	private String maker;
	@NotBlank
	private String description;

}