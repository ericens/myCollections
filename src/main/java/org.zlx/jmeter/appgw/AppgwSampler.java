/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.zlx.jmeter.appgw;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;
import org.junit.Test;
import org.zlx.http.HttpClient;

import java.nio.charset.Charset;

/**
 * A sampler which understands all the parts necessary to read statistics about
 * HTTP requests, including cookies and authentication.
 * This sampler uses the default Java HTTP implementation
 */
public class AppgwSampler extends AbstractJavaSamplerClient {

    private static String KEY_URL="url";
    private static String KEY_PARAM="param";
    private static String KEY_SYSTEM="system";
    private static String KEY_DATA="data";


    private static String DEFAULT_URL="http://172.28.3.24:9086/shop/dealRecords";
    private static String DEFAULT_SYSTEM="{version:'2.2.2', sessionId:'0fcf8613b427a89c16813db79e10cdff', userId:'1505890335732', controller:'idcardInfo', action:'updateIdCardInfo', source:'android', machineCode:'20:5D:47:14:94:18', timestamp:'1506328987486', channel:'', longitude:'113.958215', latitude:'22.546729', address:'中国广东省深圳市南山区科技路2-3', sign:'912f0eb62e86683dbf5b3dfbb46a3ab9', needLogin:null, redirect:null}";
    private static String DEFAULT_DATA="{\"bankCardNo\":\"6228480088141848874\",\"rebuy\":1,\"realName\":\"沈思利\",\"occupation\":2,\"nation\":\"汉\",\"chooseDaiKou\":\"0\",\"bankMobile\":\"13200008888\",\"supportDaiKou\":\"0\",\"idCardNo\":\"510125198506206022\",\"idCardAddr\":\"四川省新津县花源镇官林村5组\",\"bankName\":\"中国农业银行\"}";


    private String param=null;
    private String url=null;


    /**
     * 在 继承到jmeter之前。先进行单元测试，看看测试类能否通过
     */
    @Test
    public void test(){
        JavaSamplerContext context=new JavaSamplerContext(getDefaultParameters());
        setupTest(context);
        runTest(context);

    }


    /**
     * 对应一次  jmeter请求
     * @param javaSamplerContext
     * @return
     */
    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        String systemJson = javaSamplerContext.getParameter(KEY_SYSTEM);
        String dataJson = javaSamplerContext.getParameter(KEY_DATA);

        param= getPostParam(systemJson,dataJson);
        url=javaSamplerContext.getParameter(KEY_URL);


        SampleResult result = new SampleResult();
        result.setSampleLabel("Http sample" + Thread.currentThread().getId());
        result.sampleStart();

        //发送到服务器


        String reponse=HttpClient.sendPostForm(url,param);
        String str=(Thread.currentThread().getId() + ":"+reponse);
        byte []bs=str.getBytes(Charset.forName("UTF-8"));

        System.out.println((Thread.currentThread().getId()+"setupTest:" + reponse));
        result.setResponseData(bs);
        result.sampleEnd();
        result.setSuccessful(true);
        return result;


    }

    /**
     *  初始化方法，实际运行时每个线程仅执行一次，在测试方法运行前执行
     * @param context
     */
    @Override
    public void setupTest(JavaSamplerContext context) {



        System.out.println("setupTest:" + Thread.currentThread().getId());
    }




    public String getPostParam(String systemJson, String dataJson) {

        /*  引用系统不存在了，屏蔽掉



        AppSystem system = JSON.parseObject(systemJson, AppSystem.class);
        //通过随机时间来,构造签名 sgin
        system.setTimestamp(UUID.randomUUID().toString());

        AppRequest request = new AppRequest();
        request.setData(dataJson);
        request.setSystem(system);

        //设置ParamUtil环境变量
        ParamUtil x=new ParamUtil();
        x.setAppSecretKey("8qsyz#EFC");
        x.setAppShopAndroidSign("299eb5953630a4ce70584d349547d3b0");
        //一个方法的的参数相同，但是输出确是不同，那么可能该方法使用了 实例变量。环境变量等等
        String sgin = ParamUtil.getSign(request, ParamUtil.sortDataStr(request.getData()));
        system.setSign(sgin);

        systemJson = JSON.toJSONString(system);
        System.out.println((Thread.currentThread().getId() + ":setupTest:" + systemJson + "  " + dataJson));

        try {
            // 怎样把多个参数拼凑成一个 string 提交给form 表单
            StringBuilder sb = new StringBuilder("system=").append(systemJson).append("&data=")
                    .append(request.getData());


//  我们的程序可以  应对 URLEncoder.encode ，编码或者未变得情况
//            StringBuilder sb = new StringBuilder("system=").append(systemJson).append("&data=")
//                    .append(URLEncoder.encode(dataJson, "utf-8"));

            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
        }

         */

        return  "";
    }


    /**
     * 设置默认参数，一定要要有，不然保存测试计划的时候，出现不能保存的情况
     *
     */
    @Override
    public Arguments getDefaultParameters() {
        Arguments params = new Arguments();
        params.addArgument("system", String.valueOf(DEFAULT_SYSTEM));
        params.addArgument("data", String.valueOf(DEFAULT_DATA));
        params.addArgument("url", String.valueOf(DEFAULT_URL));
        return params;
    }



}
