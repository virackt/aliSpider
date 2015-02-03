package me.veryvic.webMagic.core;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

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
//        page.addTargetRequests(page.getHtml().links().regex(""));
    }
}
