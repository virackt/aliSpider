package me.veryvic.webMagic.core;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by vic on 2015/2/2.
 */
public class AliJsonSearcher implements PageProcessor {

    private Site site = Site.me().setUserAgent("aliTest").setRetryTimes(3).setSleepTime(100);
    @Override
    public void process(Page page) {
        List<String> firstAddresses = page.getHtml().regex("(http:\\/\\/\\w+\\.1688\\.com)").all();
        for (String str : firstAddresses) {
            page.addTargetRequest(str + "/page/contactinfo.htm");
        }
        page.addTargetRequests(page.getJson().regex("(http://\\w+\\.1688\\.com)").all());
        page.putField("公司姓名", page.getHtml().xpath("//title/text()"));
        page.putField("性别职位", page.getHtml().xpath("//div[@class='contact-info']/dl/dd/text()"));
        page.putField("电话", page.getHtml().xpath("//div[@class='contcat-desc']/dl/dd/text()"));
        page.putField("移动电话", page.getHtml().xpath("//dl[@class='m-mobilephone']/dd/text()"));
        page.putField("地址", page.getHtml().xpath("//dd[@class='address']/text()"));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args){
        Spider.create(new AliJsonSearcher()).addUrl("http://s.1688.com/selloffer/rpc_offer_search.jsonp?keywords=%B5%E7%D7%D3%D1%CC&from=marketSearch&async=true&asyncCount=20&startIndex=40&qrwRedirectEnabled=false&offset=0&isWideScreen=false&controls=_template_%5Eofferresult%2Cshopwindow%2CshopwindowOfferResult.vm%7C_moduleConfig_%5EshopwindowResultConfig%7CpageSize%5E60%7C_name_%5EofferResult%7Coffset%5E0&token=1451661909&callback=jQuery17207938945458019054_1422694555688").addPipeline(new FilePipeline("d:/a.json")).thread(10).run();
    }

}
