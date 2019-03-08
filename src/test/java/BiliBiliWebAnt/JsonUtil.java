package BiliBiliWebAnt;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import us.codecraft.webmagic.selector.Json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

public class JsonUtil {
    public static String loadJson(String url) throws Exception {
        //读取url,返回json串
        StringBuilder json = new StringBuilder();
        URL oracle = new URL(url);
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = null;
        while ((inputLine = in.readLine()) != null) {
            json.append(inputLine);
        }
        in.close();

        return json.toString();
    }
    //
    public static String returnView(String url) throws Exception{
//        String url = "https://api.bilibili.com/x/web-interface/archive/stat?aid=44837943";
        String json = loadJson(url);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String r = (String) jsonObject.getString("data");
        JSONObject obj = JSONObject.parseObject(r);
        String view = obj.getString("view");
        return view;
    }

    public static String returnDamark(String url) throws Exception{
        String json = loadJson(url);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String r = (String) jsonObject.getString("data");
        JSONObject obj = JSONObject.parseObject(r);
        String remaku = obj.getString("danmaku");
        return remaku;
    }

//    public static void main(String[] args) throws Exception {
//        String url = "https://api.bilibili.com/x/web-interface/archive/stat?aid=44837943";
//        System.out.println(returnView(url));
//        System.out.println(returnDamark(url));
//        String json = loadJson(url);
//
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        String r = (String) jsonObject.getString("data");
////        List<JsonUtil> list;
////        List<JsonUtil> list = JSONObject.parseArray(r , JsonUtil.class);
////        int aaa = list.get(0).getView();
//        JSONObject obj = JSONObject.parseObject(r);
//        String view = obj.getString("view");
//        String danmaku = obj.getString("danmaku");
//        System.out.println(view + "         " + danmaku);

//
//        JSONObject jsonObject1 = JSONObject.parseObject(aaa);
//        String aaa1 = (String) jsonObject.getString("view");

//        System.out.println(aaa);
}

//        page.putField("p" , page.getHtml().xpath("//*[@id=\"multi_page\"]/div[2]/ul ").get());
//
//        page.putField("p_address" , page.getHtml().xpath("//*[@id=\"multi_page\"]/div[2]/ul/li[1]/a").get());

//        page.putField("play_number" , page.getHtml().xpath("//div[@class='video-data']/span[1]/text()").get());
//
//        page.putField("bullet_number" , page.getHtml().xpath("//div[@class='video-data']/span[2]/text()").get());

//        page.putField("content" , page.getHtml().xpath("/html/body/pre").get());

//        page.putField("title_image" , page.getHtml().xpath("/html/head/meta[10]").get());