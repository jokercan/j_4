package BiliBiliWebAnt;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public String part;

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

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

    public static String returnPandPadress(String url , int count) throws Exception{
        String json = loadJson(url);
        JSONObject jsonObject = JSONObject.parseObject(json);
        String r = jsonObject.getString("data");
        List<Test> list = JSONObject.parseArray(r , Test.class);

        List<String> aaa = new ArrayList<String>();

        for(int i = 0 ; i < list.size() ; i++) {
            String a = list.get(i).getPart();
            aaa.add(a);
//            List<String> aaa = null;
//            System.out.println(a);
        }

        String pText = null;

        for (int i = 0 ; i < aaa.size() ; i++){
            String uurrll = "https://www.bilibili.com/video/av" + count +"/?p" + (i+1);
            pText += aaa.get(i) + ":" + uurrll + " ";
        }

        return pText;
    }

//    public static void main(String[] args) throws Exception {
//        String url = "https://api.bilibili.com/x/player/pagelist?aid=40817563&jsonp=jsonp";
//        String json = loadJson(url);
//        JSONObject jsonObject = JSONObject.parseObject(json);
//        String r = jsonObject.getString("data");
//        List<Test> list = JSONObject.parseArray(r , Test.class);
//        for(int i = 0 ; i < list.size() ; i++) {
//            String a = list.get(i).getPart();
//            System.out.println(a);
//        }

//        List<String> list = null;
//        list = returnPandPadress(url);
//        List<String> list = new ArrayList<String>();
//        String list = returnPandPadress(url , 40817563);

//        System.out.println(list);

//        returnPandPadress(url);
//        for (int i = 0 ; i < list.size() ; i++){
//            System.out.println(list.get(i));
//        }

//    }
}
