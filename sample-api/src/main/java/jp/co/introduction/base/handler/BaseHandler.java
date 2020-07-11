package jp.co.introduction.base.handler;

import java.security.InvalidParameterException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jp.co.introduction.base.model.BaseResModel;

/**
 * 例外発生時のハンドリングクラス
 */
@ControllerAdvice
public class BaseHandler {

    /**
     * 引数例外のハンドリングメソッド
     * 
     * @param exception
     *            InvalidParameterException
     * @return エラーレスポンス
     */
    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<?> invalidParameterException(InvalidParameterException exception) {
        // レスポンス設定
        BaseResModel resModel = new BaseResModel();
        // エラーメッセージ
        resModel.setMessage(exception.getMessage());
        // success = false
        resModel.setSuccess(false);

        // HTTP_STATUS=400で返却
        return new ResponseEntity<>(resModel, HttpStatus.BAD_REQUEST);
    }

    /**
     * 想定外エラーのハンドリングメソッド
     * 
     * @param exception
     *            Exception
     * @return エラーレスポンス
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> invalidParameterException(Exception exception) {
        // レスポンス設定
        BaseResModel resModel = new BaseResModel();
        // エラーメッセージ
        resModel.setMessage(exception.getMessage());
        // success = false
        resModel.setSuccess(false);

        // HTTP_STATUS=500で返却
        return new ResponseEntity<>(resModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}