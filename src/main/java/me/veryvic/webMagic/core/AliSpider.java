package me.veryvic.webMagic.core;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by vic on 2015/2/3.
 */
public class AliSpider extends Thread {
    private PageProcessor processor;
    // 目标路径
    private String targetUrl;
    // 线程数量
    private int threadNum;
    // json文本存放路径
    private String jsonPiplinePath;

    public AliSpider(PageProcessor processor, String targetUrl, int threadNum, String jsonPiplinePath){
        this.processor = processor;
        this.targetUrl = targetUrl;
        this.threadNum = threadNum;
        this.jsonPiplinePath = jsonPiplinePath;
    }

    public AliSpider(PageProcessor processor,String targetUrl){
        this(processor, targetUrl, 1, null);
    }

    public void run(){
        if(jsonPiplinePath != null){
            Spider.create(processor).addUrl(targetUrl).addPipeline(new FilePipeline(jsonPiplinePath)).thread(threadNum).run();
        } else {
            Spider.create(processor).addUrl(targetUrl).thread(threadNum).run();
        }
    }

}
