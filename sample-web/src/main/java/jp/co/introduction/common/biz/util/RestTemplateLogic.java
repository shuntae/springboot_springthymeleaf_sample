package jp.co.introduction.common.biz.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RestTemplateLogic {
    
    @Autowired
    private RestTemplate restTemplate;

    /**
     * HttpRequest Get 引数のビルダーとレスポンスクラスを元にHttpRequest(GET)を行う。
     * 
     * @param builder
     *            URLビルダー
     * @param genericyType
     *            レスポンスクラス
     * 
     * @return HttpResponse
     */
    public <T> T get(UriComponentsBuilder builder, Class<T> genericyType) {

        // build実行(urlEncode = false)
        UriComponents url = builder.build(false);
        // コンソール出力
        System.out.println("実行するリクエスト:" + url);

        // ヘッダー設定
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Encoding", "UTF-8");
        headers.add("Content-Type", "application/json; charset=UTF-8");
        HttpEntity<?> requestEntity = new HttpEntity<>(headers);

        // HttpRequest実行
        ResponseEntity<T> response;
        try {
            response = restTemplate.exchange(url.toUriString(), HttpMethod.GET, requestEntity, genericyType);
        } catch (Exception e) {
            // コンソール出力
            System.err.println("えらー");
            // stackTraceをコンソールに出力
            e.printStackTrace();
            // nullを返却
            return null;
        }

        // 呼び出し成功(200)
        if (response.getStatusCode() == HttpStatus.OK) {
            // bodyを返却
            return response.getBody();
        }
        // 上記以外
        else {
            return null;
        }
    }

    /**
     * URLエンコード 文字コード(UTF-8)を指定してエンコードを行う。
     * 
     * @param builder
     *            URLビルダー
     * @param genericyType
     *            レスポンスクラス
     * 
     * @return HttpResponse
     */
    public static String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // stackTraceをコンソールに出力
            e.printStackTrace();
            // nullを返却
            return null;
        }
    }
}