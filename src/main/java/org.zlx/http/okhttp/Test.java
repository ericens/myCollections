package org.zlx.http.okhttp;

import com.squareup.okhttp.*;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static jodd.util.ThreadUtil.sleep;

/**
 * Created by @author linxin on 06/11/2017.  <br>
 */
@Slf4j
public class Test {

    @org.junit.Test
    public  void SyncGet() throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }

        log.info("body begin.......");

        System.out.println(response.body().string());
    }
    @org.junit.Test
    public void Headers() throws IOException {

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .header("User-Agent", "My super agent")
                .addHeader("Accept", "text/html")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.header("Server"));
        System.out.println(response.headers("Set-Cookie"));
    }

    @org.junit.Test
    public void CacheResponse () throws IOException {

        int cacheSize = 100 * 1024 * 1024;
        File cacheDirectory = Files.createTempDirectory("cache").toFile();
        Cache cache = new Cache(cacheDirectory, cacheSize);
        OkHttpClient client = new OkHttpClient();
        client.setCache(cache);

        Request request = new Request.Builder()
                .url("http://www.baidu.com")
                .build();

        Response response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.cacheResponse());
        System.out.println(response.networkResponse());

        sleep(1000*5);

        response = client.newCall(request).execute();
        if (!response.isSuccessful()) {
            throw new IOException("服务器端错误: " + response);
        }

        System.out.println(response.cacheResponse());
        System.out.println(response.networkResponse());
    }





}
