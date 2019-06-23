package jp.co.introduction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.model.res.FruitResModel;
import jp.co.introduction.entity.FruitEntity;
import jp.co.introduction.logic.FruitLogic;

/**
 * サービスクラス
 *
 * <p>業務ロジックを行うロジッククラスを呼び出し、レスポンスを組み立てる層<br>
 * 「@Transactional」アノテーションを利用してトランザクションの開始を行うことが多い。<br>
 * 引数の相関チェックもここで行う。<br>
 * ロジッククラスは複数呼び出してもOK<br>
 * ただし、トランザクションの関係でサービスクラス呼び出しはNG
 */
@Service // サービスクラスに付与するアノテーション 付与することでDIコンテナに登録される。
public class FruitService {

  @Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
  public FruitLogic fruitLogic;

  public FruitResModel getFruit() {

    List<FruitEntity> fruitList = fruitLogic.getFruit();
    String message = "データ取得に成功しました。データの取得件数は" + fruitList.size() + "件です。";

    FruitResModel resModel = new FruitResModel();
    resModel.setFruitList(fruitList);

    resModel.setSuccess(true);
    resModel.setMessage(message);

    return resModel;
  }

  public BaseResModel deleteFruit(int id) {

    boolean deleteResult = fruitLogic.deleteFruit(id);
    String message = "id:" + id + "の削除に";
    message += deleteResult ? "成功しました。" : "失敗しました。レコードが存在しません。";

    BaseResModel resModel = new BaseResModel();

    resModel.setSuccess(true);
    resModel.setMessage(message);

    return resModel;
  }
}
