package jp.co.introduction.common.model.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddItemReqModel {

	private String itemCode;
	private String itemName;
	private int price;
	private int taxRate;
	private int taxType;
	private String maker;
	private String description;

}