package jp.co.introduction.batch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CSV取り込みデータのマッピングクラス
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FruitReadCsvModel {

  private String name;
  private int price;
}
