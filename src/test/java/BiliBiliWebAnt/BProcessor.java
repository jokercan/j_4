package BiliBiliWebAnt;

import BqgWebAnt.BqgProcessor;
//import org.apache.commons.lang.ObjectUtils;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class BProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000).setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");

//    public static final String web = "[a-zA-z]+://[^\\s]*";
//    public static final String web1 = "https://www.\\.bilibili\\.com/video/av\\d{1 , 8}";

    public  static int count = 2;

    public void process(Page page){
        //text是分p和分p的地址
        String textUrl = "https://api.bilibili.com/x/player/pagelist?aid=" + count + "&jsonp=jsonp";
        String utilUrl = "https://api.bilibili.com/x/web-interface/archive/stat?aid=" + count;
//        List<String> web = page.getHtml().links().regex(web1).all();
//        page.addTargetRequests(web);
//        int count = 2;

//        while(count < 99999999){
//            page.addTargetRequests(page.getHtml().links().);
//        }
//        page.addTargetRequests(page.getHtml().links().regex(web).all());
//        page.putField("title" , page.getHtml().xpath("//div[@id='viewbox_report']/h1/span/text()").get());
//
//        page.putField("up" , page.getHtml().xpath("//*[@id=\"v_upinfo\"]/div[2]/div[1]/a[1]/text()").get());
//
//        page.putField("info" , page.getHtml().xpath("//div[@id='v_desc']/div/text()").get());

        //////////////////========================================
//        String aaa = null;
//        try {
//            aaa = new Test().returnPandPadress(textUrl , count);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(aaa);

//        String danmu = null , view = null;
//        try {
//            danmu = new JsonUtil().returnDamark(utilUrl);
//            view = new JsonUtil().returnView(utilUrl);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("danmu: " + danmu + "  view: " + view);
////===========================================================


        //========================================
        page.putField("title" , page.getHtml().xpath("//div[@id='viewbox_report']/h1/span/text()").get());

        if (page.getResultItems().get("title") == null) {
            return ;
        }

        BiliPojo biliPojo = new BiliPojo();

        biliPojo.setTitle(page.getHtml().xpath("//div[@id='viewbox_report']/h1/span/text()").get());

        biliPojo.setTitleImage(page.getHtml().xpath("/html/head/meta[10]").get());
//
        biliPojo.setUp(page.getHtml().xpath("//*[@id=\"v_upinfo\"]/div[2]/div[1]/a[1]/text()").get());

        biliPojo.setInfo(page.getHtml().xpath("//div[@id='v_desc']/div/text()").get());

        String aaa = null;
        try {
            aaa = new Test().returnPandPadress(textUrl , count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        biliPojo.setpAndAddress(aaa);

        String danmu = null , view = null;
        try {
            danmu = new JsonUtil().returnDamark(utilUrl);
            view = new JsonUtil().returnView(utilUrl);
        }catch (Exception e){
            e.printStackTrace();
        }
        biliPojo.setBulletNumber(danmu);
        biliPojo.setPlayNumber(view);

        try {
            new BiliJdbc().insertInfo(biliPojo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Site getSite(){
        return site;
    }

    public static void main(String[] args){

        while(count < 99999999) {
//            System.out.println("a" + count);

            String url = "https://www.bilibili.com/video/av" + count;

//            String ur2 = "https://api.bilibili.com/x/web-interface/archive/stat?aid=" + count; 弹幕
//            String uurrll = "https://api.bilibili.com/x/player/pagelist?aid=" + count + "&jsonp=jsonp";  分p及地址
//            System.out.println(url);

            Spider.create(new BProcessor()).addUrl(url)
                    .addPipeline(new ConsolePipeline())
                    .thread(10)
                    .run();

            count++;
        }
    }
}
