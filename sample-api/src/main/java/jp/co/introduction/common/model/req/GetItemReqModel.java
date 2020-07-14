package jp.co.introduction.common.model.req;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetItemReqModel {

	@NotNull
	private String itemCode;
	@NotNull
	private String itemName;
	@NotNull
	private Integer price;
	@NotNull
	private String maker;

}