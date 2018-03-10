package org.zlx.http.landingPage;

import cn.jiguang.ad.client.model.LandingPage;
import cn.jiguang.ad.client.model.dto.req.PageRequest;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.zlx.http.HttpClient;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class LandingPageTest {

    String preview="http://localhost:10028/advertisers/222/landing-page/preview";
    String publisUrl="http://localhost:10028/advertisers/222/ad-landingPage/";
    String update="http://localhost:10028/advertisers/222/ad-landingPage/";
    String get="http://localhost:10028/advertisers/222/ad-landingPage/";
    String delete="http://localhost:10028/advertisers/222/ad-landingPage/";
    String getList="http://localhost:10028/advertisers/222/landing_page/";



    private LandingPage getLandingPage(){
        LandingPage landingPage=new LandingPage();
        landingPage.setName("王者荣耀");
        Map data=new HashMap();

        data.put("logoPath","logoPath");
        data.put("bgColor","#ff0012");
        data.put("text","好游戏");
        data.put("appName","王者荣耀");
        data.put("appDesc","好玩的游戏");

        landingPage.setRenderData(JSON.toJSONString(data));
        landingPage.setTemplateId(1);
        landingPage.setTypeId(1);
        landingPage.setAdvertiserId(22L);
        landingPage.setId(UUID.randomUUID().toString());
        return landingPage;
    }

    @Test
    public void publish(){

        LandingPage landingPage=getLandingPage();
        String response= HttpClient.sendPostJson(publisUrl, JSON.toJSONString(landingPage));
        log.info("response:{}",JSON.toJSONString(response));


        response= HttpClient.sendGetJson(get+ landingPage.getId()+"/", "");
        log.info("response:{}",JSON.toJSONString(response));

    }

    @Test
    public void delete(){

        LandingPage landingPage=getLandingPage();
        String response= HttpClient.sendPostJson(publisUrl, JSON.toJSONString(landingPage));
        log.info("response:{}",JSON.toJSONString(response));


        response= HttpClient.sendGetJson(get+ landingPage.getId()+"/", "");
        log.info("response:{}",JSON.toJSONString(response));

        response= HttpClient.dosend(HttpClient.MEHOD_DELETE,delete+landingPage.getId()+"/", JSON.toJSONString(landingPage),true);
        log.info("response:{}",JSON.toJSONString(response));

        response= HttpClient.sendGetJson(get+ landingPage.getId()+"/", "");
        log.info("response:{}",JSON.toJSONString(response));
    }


    @Test
    public void update(){
        LandingPage landingPage=getLandingPage();
        String response= HttpClient.sendPostJson(publisUrl, JSON.toJSONString(landingPage));
        log.info("response:{}",JSON.toJSONString(response));

        response= HttpClient.sendGetJson(get+ landingPage.getId()+"/", "");
        log.info("response:{}",JSON.toJSONString(response));

        landingPage.setName("update");
        response= HttpClient.dosend(HttpClient.MEHOD_PUT,update+landingPage.getId()+"/", JSON.toJSONString(landingPage),true);
        log.info("response:{}",JSON.toJSONString(response));


        response= HttpClient.sendGetJson(get+ landingPage.getId()+"/", "");
        log.info("response:{}",JSON.toJSONString(response));



    }

    @Test
    public void preview(){
        String preview="http://localhost:10028/preview";
        String response= HttpClient.sendPostJson(preview, JSON.toJSONString(getLandingPage()));
        log.info("response:{}",JSON.toJSONString(response));
    }

    @Test
    public void getList(){

        PageRequest baseReq=new PageRequest();
        baseReq.setPageNo(1L);
        baseReq.setPageSize(2L);
        baseReq.setName("update");

        String response= HttpClient.sendGetJson(getList, JSON.toJSONString(getLandingPage()));
        log.info("response:{}",JSON.toJSONString(response));


    }




}
