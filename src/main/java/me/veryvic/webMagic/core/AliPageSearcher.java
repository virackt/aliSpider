package me.veryvic.webMagic.core;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by vic on 2015/2/1.
 */
public class AliPageSearcher implements PageProcessor{
    private Site site = Site.me().setRetryTimes(3).setUserAgent("aliTest").setSleepTime(100);
    @Override
    public Site getSite() {
        return site;
    }

    @Override
    public void process(Page page) {
        List<String> firstAddresses = page.getHtml().regex("(http://\\w+\\.1688\\.com)").all();
        for (String str : firstAddresses) {
            page.addTargetRequest(str + "/page/contactinfo.htm");
        }
        page.putField("公司姓名", page.getHtml().xpath("//title/text()"));
        page.putField("性别职位", page.getHtml().xpath("//div[@class='contact-info']/dl/dd/text()"));
        page.putField("电话", page.getHtml().xpath("//div[@class='contcat-desc']/dl/dd/text()"));
        page.putField("移动电话", page.getHtml().xpath("//dl[@class='m-mobilephone']/dd/text()"));
        page.putField("地址", page.getHtml().xpath("//dd[@class='address']/text()"));
        if (page.getResultItems() == null) {
            page.setSkip(true);
        }
    }


}
