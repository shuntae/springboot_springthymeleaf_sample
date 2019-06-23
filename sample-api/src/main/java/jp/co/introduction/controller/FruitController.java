package jp.co.introduction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jp.co.introduction.base.model.BaseResModel;
import jp.co.introduction.common.model.res.FruitResModel;
import jp.co.introduction.service.FruitService;

/**
 * コントローラクラス
 *
 * <p>エンドポイント実装クラス<br>
 * アノテーションの引数チェックを行う。<br>
 * 業務ロジックは書かず、サービスクラスの呼び出しが主<br>
 * １メソッド＝１エンドポイントというイメージ
 */
@RestController // コントローラクラスに付与 付与することでDIコンテナに登録される。
public class FruitController {

  @Autowired // DIコンテナに登録したBean(クラス)を利用するときに使用するアノテーション
  public FruitService fruitService;

  @RequestMapping(method = RequestMethod.GET, path = "/v1/fruits") // HttpMethodとエンドポイントの指定を行う
  public FruitResModel getFruit() {

    return fruitService.getFruit();
  }

  @RequestMapping(method = RequestMethod.DELETE, path = "/v1/fruits/{id}")
  public BaseResModel deleteFruit(@PathVariable(name = "id") int id) {

    return fruitService.deleteFruit(id);
  }
}
