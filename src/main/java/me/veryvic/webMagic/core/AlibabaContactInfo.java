package me.veryvic.webMagic.core;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by vic on 2015/1/31.
 */
public class AlibabaContactInfo implements PageProcessor {
    private Site site = Site.me().setUserAgent("test").setRetryTimes(3).setSleepTime(1000);
    private static String aliJsonUrl1 = "http://s.1688.com/selloffer/rpc_offer_search.jsonp?keywords=%B5%E7%D7%D3%D1%CC&from=marketSearch&async=true&asyncCount=20&startIndex=40&qrwRedirectEnabled=false&offset=0&isWideScreen=false&controls=_template_%5Eofferresult%2Cshopwindow%2CshopwindowOfferResult.vm%7C_moduleConfig_%5EshopwindowResultConfig%7CpageSize%5E60%7C_name_%5EofferResult%7Coffset%5E0&token=1451661909&callback=jQuery17207938945458019054_1422694555688";
    private static String aliJsonUrl2 = "http://s.1688.com/selloffer/rpc_offer_search.jsonp?keywords=%D2%BD%C1%C6%C6%F7%D0%B5&from=marketSearch&async=true&asyncCount=30&startIndex=20&qrwRedirectEnabled=false&offset=0&isWideScreen=false&controls=_template_%5Eofferresult%2Cshopwindow%2CshopwindowOfferResult.vm%7CindustryFlag%5E%7C_moduleConfig_%5EshopwindowResultConfig%7CpageSize%5E60%7C_name_%5EofferResult%7Coffset%5E0&token=1867721500&callback=jQuery17207938945458019054_1422694555683";

    @Override
    public void process(Page page) {
        List<String> firstAddresses = page.getHtml().regex("(http://\\w+\\.1688\\.com)").all();
        for (String str : firstAddresses) {
            page.addTargetRequest(str + "/page/contactinfo.htm");
        }
//        page.addTargetRequests(page.getHtml().regex("(http://\\w+\\.1688\\.com/?spm=*)").all());
        page.putField("公司姓名", page.getHtml().xpath("//title/text()"));
        page.putField("性别职位", page.getHtml().xpath("//div[@class='contact-info']/dl/dd/text()"));
        page.putField("电话", page.getHtml().xpath("//div[@class='contcat-desc']/dl/dd/text()"));
        page.putField("移动电话", page.getHtml().xpath("//dl[@class='m-mobilephone']/dd/text()"));
        page.putField("地址", page.getHtml().xpath("//dd[@class='address']/text()"));
        page.putField("",page.getJson().jsonPath(""));
        if (page.getResultItems() == null) {
            page.setSkip(true);
        }

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        Spider.create(new AlibabaContactInfo()).addUrl("http://s.1688.com/selloffer/-D2BDC1C6C6F7D0B5.html?spm=0.0.0.0.wlgxYq").thread(10).run();
//        Spider.create(new AlibabaContactInfo()).addUrl("http://s.1688.com/selloffer/-B5E7D7D3D1CC.html?spm=0.0.0.0.wlgxYq").thread(10).run();
//        Spider.create(new AlibabaContactInfo()).addUrl("http://shop1406865607567.1688.com/").run();
        Spider.create(new AlibabaContactInfo()).addUrl(aliJsonUrl1).thread(10).run();
    }
}
