package BqgWebAnt;

//使用webmagic框架进行爬取笔趣阁小说

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.processor.example.GithubRepoPageProcessor;

import java.sql.SQLException;
import java.util.List;

public class BqgProcessor implements PageProcessor {
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    public static final String web = "http://www\\.xbiquge\\.la/\\w+";
    public static final String web1 = "http://www\\.xbiquge\\.la/\\d+/\\d+";
    //public static final String web2 = "http://www\\.xbiquge\\.la/\\d+/\\d+/\\d+\\.html";

//    @Override
//    public void process(Page page) {

//  1      page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());

//   2     page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());

//    3    page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
//     4   page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());

//      5  if (page.getResultItems().get("name")==null){
//            //skip this page
//            page.setSkip(true);
//        }
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
//    }

    public void process(Page page){
        if(page.getUrl().regex(web).match()){

            List<String> nextWeb = page.getHtml().links().regex(web1).all();
            page.addTargetRequests(nextWeb);
            //使用List方法避免只爬了一次

            //========================================================================================================
//            page.putField("book",page.getHtml().xpath("//div[@id='info']/h1/text()").get());
//
//            if(page.getResultItems().get("book") == null)
//                page.setSkip(true);
//
//            page.putField("author",page.getHtml().xpath("//div[@id='info']/p/text()").get());
//
//            page.putField("info",page.getHtml().xpath("//div[@id='intro']/p[2]/text()").get());
//
//            page.putField("image",page.getHtml().xpath("//div[@id='fmimg']/img").get());
//
//            page.putField("bookname", page.getHtml().xpath("//div[@id='list']/dl/dd/a/text()").get());
//
//            page.putField("content", page.getHtml().xpath("//div[@id='list']/dl/dd/a").get());
            //=======================================================================================================================
            //以上可用作在控制框中查看爬了什么

            try {
                if (!new BqgJdbc().checkExist(page.getUrl().toString()))
                {
                    BqgBlog bqgBlog = new BqgBlog();
//            System.out.println("111111: " + page.getUrl());
                    bqgBlog.setUrl(page.getUrl().toString());

                    bqgBlog.setBook(page.getHtml().xpath("//div[@id='info']/h1/text()").get());

                    bqgBlog.setAuthor(page.getHtml().xpath("//div[@id='info']/p/text()").get());

                    bqgBlog.setInfo(page.getHtml().xpath("//div[@id='intro']/p[2]/text()").get());

                    bqgBlog.setImage(page.getHtml().xpath("//div[@id='fmimg']/img").get());

                    bqgBlog.setBookname(page.getHtml().xpath("//div[@id ='list']/dl/dd/a/text()").get());

                    bqgBlog.setContent(page.getHtml().xpath("//div[@id='list']/dl/dd/a").get());

                    //=======================================================================================================
                    //爬取标题、作者、简介、头图

//            //下一深度的网页爬取章节和内容
//            if(page.getUrl().regex(web1).match()){
//
//                List<String> lastWeb = page.getHtml().links().regex(web2).all();
//                page.addTargetRequests(lastWeb);
//                //=========================================================================================================
//                page.putField("bookname", page.getHtml().xpath("//div[@class='bookname']/h1").get());
//
//                page.putField("content", page.getHtml().xpath("//div[@id='content']/text()").get());
//
//                bqgBlog.setBookname(page.getHtml().xpath("//div[@class='bookname']/h1/text()").get());
//
//                bqgBlog.setContent(page.getHtml().xpath("//div[@id='content']/text()").get());
//                //========================================================================================================
//                //爬取第一章的标题和内容
//            }

                    try {
                        new BqgJdbc().insertInfo(bqgBlog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    public Site getSite() {
        return site;
    }
//
//    public static void main(String[] args) {
//        Spider.create(new GithubRepoPageProcessor()).addUrl("https://github.com/code4craft").thread(5).run();
//    }


    public static void main(String[] args){
        Spider.create(new BqgProcessor()).addUrl("http://www.xbiquge.la/xiaoshuodaquan/")
                .addPipeline(new ConsolePipeline())
                .thread(10)
                .run();
    }
}
