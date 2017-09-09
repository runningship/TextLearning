package org.bc.npl.textlearning.service;

import java.net.URL;
import java.util.Date;
import java.util.TimerTask;

import org.bc.npl.textlearning.entity.Article;
import org.bc.npl.textlearning.entity.CrawlTarget;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jumore.dove.boot.comp.DAO;
import com.jumore.dove.boot.comp.Logger;
import com.jumore.dove.plugin.Page;
import com.jumore.dove.util.ParamMap;

@Service
public class ScrawService extends TimerTask{

    @Override
    public void run() {
        while(true){
            Page<CrawlTarget> page = new Page<CrawlTarget>();
            ParamMap pm = new ParamMap();
            // 找没有爬取的任务
            pm.put("crawStatus", 0);
            page.setPageSize(1);
            page = DAO.findPageByParams(CrawlTarget.class, page, "CrawlTarget.listCrawlTarget", pm);
            if(page.result!=null){
                for(CrawlTarget ct : page.result){
                    doCrawl(ct);
                }
            }
            try {
                Thread.sleep(20000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
    public void init(){
        CrawlTarget vo = new CrawlTarget();
        vo.setLink("https://baike.baidu.com/item/%E5%A4%A7%E6%98%AD%E5%AF%BA");
        CrawlTarget po = (CrawlTarget) DAO.getByExample(vo);
        if(po==null){
            vo.setCrawStatus(0);
            vo.setAddtime(new Date());
            DAO.save(vo);
        }
    }
     
    private void doCrawl(CrawlTarget ct){
        try {
            URL url = new URL(ct.getLink());
            Document doc = Jsoup.parse(url, 5000);
            crawlLinks(doc , url);
        } catch (Exception e) {
            e.printStackTrace();
            if(ct.getFailTimes()==null){
                ct.setFailTimes(1);
            }else{
                ct.setFailTimes(ct.getFailTimes()+1);
            }
            ct.setLastError(e.getMessage());
        }
        ct.setCrawStatus(1);
        DAO.update(ct);
    }
    
    private void crawlLinks(Document doc , URL parent){
        Elements links = doc.body().select(".main-content a");
        for (Element link : links) {
            Logger.getLogBuilder().info(link.text() + link.attr("href"));
            String href = link.attr("href");
            if(!href.contains("item")){
                continue;
            }
            
            CrawlTarget target = new CrawlTarget();
            target.setLink("https://baike.baidu.com"+href);
            Object po = DAO.getByExample(target);
            if(po==null){
                target.setCrawStatus(0);
                target.setAddtime(new Date());
                DAO.save(target);
            }
        }
        String body = doc.body().select(".main-content").text();
        if(StringUtils.isEmpty(body)){
            System.out.println();
        }
        Article article = new Article();
        article.setAddtime(new Date());
        article.setLink(parent.toString());
        article.setText(body);
        DAO.save(article);

    }
}
