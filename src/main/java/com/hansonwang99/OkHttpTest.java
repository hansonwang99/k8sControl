package com.hansonwang99;

import com.alibaba.fastjson.JSON;
import com.hansonwang99.bean.ArticleBean;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/19.
 */
public class OkHttpTest {

    OkHttpClient client = new OkHttpClient();

    private String run(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    private Map<String,String> jsonParser(String originData ) {

        ArticleBean articleBean = JSON.parseObject( originData, ArticleBean.class );

        Map<String,String> map = new HashMap<>();
        map.put( "id", articleBean.getId() );
        map.put( "title", articleBean.getTitle() );
        map.put( "tag", articleBean.getTag() );
        map.put( "categoryName", articleBean.getCategoryName() );
        map.put( "content", articleBean.getContent() );
        return map;
    }

    public static void main(String[] args) throws IOException {
        OkHttpTest example = new OkHttpTest();
        String response = example.run("https://easy-mock.com/mock/598eb2fea1d30433d85f00f1/codesheep/article/backgetdetail/id/2");
        Map<String,String> map = example.jsonParser( response );
        System.out.println(response);
    }
}
