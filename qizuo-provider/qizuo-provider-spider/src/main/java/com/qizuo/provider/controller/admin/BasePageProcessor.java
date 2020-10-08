package com.qizuo.provider.controller.admin;

import com.qizuo.provider.config.BasicConfiguration;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.BloomFilterDuplicateRemover;
import us.codecraft.webmagic.scheduler.FileCacheQueueScheduler;

/**
 * Created by brian on 16/11/24.
 *
 * <p>爬取知乎用户的关注者 step 1: 运行该类的 main 方法开始爬取
 */
public class BasePageProcessor implements PageProcessor {

  private Site site = new BasicConfiguration().getSite();

  // process是定制爬虫逻辑的核心接口，在这里编写抽取逻辑
  @Override
  public void process(Page page) {
    page.addTargetRequests(
        page.getHtml().links().regex("(https://github\\.com/[\\w\\-]+/[\\w\\-]+)").all());
    page.addTargetRequests(page.getHtml().links().regex("(https://github\\.com/[\\w\\-])").all());
    page.putField("author", page.getUrl().regex("https://github\\.com/(\\w+)/.*").toString());
    page.putField(
        "name",
        page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
    if (page.getResultItems().get("name") == null) {
      // skip this page
      page.setSkip(true);
    }
    page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
  }

  // site的设定，包括域名/header/编码/抓取间隔/重试次数等
  @Override
  public Site getSite() {
    return site;
  }

  /** 创建一个spider启动 */
  public static void main(String[] args) {
    String pipelinePath = new BasicConfiguration().getBaseDir();
    int crawlSize = 100_0000;
    Spider.create(new BasePageProcessor())
        .setScheduler(
            new FileCacheQueueScheduler(pipelinePath)
                .setDuplicateRemover(
                    new BloomFilterDuplicateRemover(crawlSize))) // new QueueScheduler()
        .addUrl("https://github.com/code4craft") // 抓取地址
        .thread(5) // 开启5个线程
        .run();
  }
}
