package jp.co.introduction.common.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetItemReqModel {

	private String itemCode;
	private String itemName;
	private Integer price;
	private String maker;

}