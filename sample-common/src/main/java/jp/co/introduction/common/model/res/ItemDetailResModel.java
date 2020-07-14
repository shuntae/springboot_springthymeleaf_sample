package jp.co.introduction.common.model.res;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.entity.ItemDetailEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ItemDetailResModel extends BaseResModel {

	private ItemDetailEntity itemDetail;
}
