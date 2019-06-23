package jp.co.introduction.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import jp.co.introduction.dao.FruitDao;
import jp.co.introduction.entity.FruitEntity;

/**
 * ロジッククラス
 *
 * <p>DBとのやりとりを行うDaoクラスを使用したり、業務ロジックを実装する役割の層<br>
 * Daoクラスやロジッククラスは複数呼び出してもOK<br>
 * ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Component // DIコンテナにBeanとして登録するためのアノテーション
@Repository // Daoクラスを使ってDBとのやりとりを行うクラスに付与する
public class FruitLogic {

  // 定数はconstantsパッケージを作ってそこにまとめるのがベスト
  // 今回はフィールドに定義しちゃってます。
  @Autowired private FruitDao fruitDao;

  /**
   * 楽天市場の商品検索APIの呼び出しを行い、APIレスポンスを返却する。
   * ドキュメント：https://webservice.rakuten.co.jp/api/ichibaitemsearch/
   *
   * @param reqModel リクエストモデル
   * @return 市場APIレスポンス
   */
  public List<FruitEntity> getFruit() {
    return fruitDao.getFruitList();
  }

  public boolean deleteFruit(int id) {
    return fruitDao.deleteFruit(id);
  }
}
