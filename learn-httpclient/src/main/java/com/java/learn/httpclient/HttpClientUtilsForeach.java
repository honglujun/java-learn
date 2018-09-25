package com.java.learn.httpclient;

import com.ycky.app.core.constant.Constants;
import com.ycky.app.util.SecurityUtil;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title:
 * @Package
 * @Description
 * @Author 111665
 * @CreateDate 2018/08/23/10:53
 * @Version 1.0
 */
public class HttpClientUtilsForeach {
    private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();


    private static final int TIMEOUT = 5 * 1000000;//5 * 1000

    //            StringUtils.strToInteger(propFactory.getConfig("max_http_connection"), 50);// 50
    private static final int MAX_HTTP_CONNECTION = 50;


    private static final int MAX_CONNECTION_PER_HOST = 10;// 10

    private static final String CHARSET_UTF8 = "UTF-8";

    private static HttpClientUtilsForeach instance = null;

    private static final String APP_KEY = "BOS";
    //	    private static final String APP_KEY = "PP";
    private static final String APP_SECRET = "WREW223421FDR134R";
//	    private static final String APP_SECRET = "PP1234WEAS4444";
    //  private static Logger logger = Logger.getLogger(HttpClientUtil.class);

    static {

        //HttpClient 连接池属性设置，HOST并发数默认为50, 客户端总并发数为200, TimeOut时间为5s.
        HttpConnectionManagerParams httpConnectionManagerParams = new HttpConnectionManagerParams();

        // 文档参数说明：Sets the maximum number of connections allowed.
        httpConnectionManagerParams.setMaxTotalConnections(MAX_HTTP_CONNECTION);

        // 文档参数说明： Sets the default maximum number of connections allowed for a given host config.
        httpConnectionManagerParams.setDefaultMaxConnectionsPerHost(MAX_CONNECTION_PER_HOST);

        // 读取数据超时时间
        httpConnectionManagerParams.setSoTimeout(TIMEOUT);

        // 连接超时时间
        httpConnectionManagerParams.setConnectionTimeout(TIMEOUT);

        connectionManager.setParams(httpConnectionManagerParams);

    }

    public HttpClientUtilsForeach() {

    }

    public static HttpClientUtilsForeach getInstance() {

        if (null == instance) {

            synchronized (HttpClientUtilsForeach.class) {

                if (instance == null) {

                    instance = new HttpClientUtilsForeach();

                }

            }

        }

        return instance;

    }

    public HttpClient createHttpClient() {

        HttpClient httpClient = new HttpClient(connectionManager);

        return httpClient;

    }

    /**
     * 进行 http请求返回String结果
     *
     * @param url
     * @return
     */

    public String getHttpClientJson(String url) {

        HttpClient httpClient = createHttpClient();

        GetMethod getMethod = new GetMethod(url);

        try {

            httpClient.executeMethod(getMethod);

            InputStreamReader in = new InputStreamReader(getMethod.getResponseBodyAsStream(),
                    HttpClientUtilsForeach.CHARSET_UTF8);
            //            String jsonString = getMethod.getResponseBodyAsString();
            String jsonString = getStringByParams(in);

            return jsonString;

        } catch (Exception e) {
            /*   logger.error("Http Client GetMethod Execute is Exception , Message = " + e.getMessage(), e);*/
            return "{\"msg\":\"获取账户余额信息失败！\",\"success\":\"false\"}";
        } finally {
            if (getMethod != null){
                getMethod.releaseConnection();
            }

        }

    }

    /**
     * 进行 http post请求返回String结果
     *
     * @param url
     * @return
     */

    public String postHttpClientJson(String url, String params) {

        HttpClient httpClient = createHttpClient();

        PostMethod postMethod = getPostMethod(url, params);

        try {
            //logger.info(" Request Navigate Url is : " + url);

            httpClient.executeMethod(postMethod);
            //            String jsonString = postMethod.getResponseBodyAsString();
            InputStreamReader in = new InputStreamReader(postMethod.getResponseBodyAsStream(),
                    HttpClientUtilsForeach.CHARSET_UTF8);
            String jsonString = getStringByParams(in);

            // 当地址端口错误时，返回为空，为防止Json解析异常
            if (jsonString == null || "".equals(jsonString)) {
                jsonString = "[{\"msg\":\"获取折扣信息失败！\",\"success\":\"false\"}]";
            }

            return jsonString;

        } catch (Exception e) {
            //  logger.error("Http Client PostMethod Execute is Exception , Message = " + e.getMessage(), e);
            return e.getMessage();
        } finally {
            if (postMethod != null) {
                postMethod.releaseConnection();
            }


        }

    }

    /**
     * @param params
     * @return List<NameValuePair>    返回类型
     * @throws
     * @Title: getCheckParam
     * @Description: (设置验证参数)
     */
    private List<NameValuePair> getCheckParam(String params) {
        String digest = SecurityUtil.getDigest(params + APP_KEY + APP_SECRET);
        String timestamp = "" + System.currentTimeMillis();
//			String timestamp = "" + new Date().getTime();
        final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
        // 接口调用的请求格式
        nameValueList.add(new NameValuePair("params", params));

        nameValueList.add(new NameValuePair(Constants.DEFAULT_ALP_DIGEST_NAME, digest));
        nameValueList.add(new NameValuePair(Constants.DEFAULT_ALP_TIMESTAMP_NAME, timestamp));
        nameValueList.add(new NameValuePair("appkey", APP_KEY));

        return nameValueList;

    }

    /**
     * @param url
     * @param params
     * @return PostMethod    返回类型
     * @throws
     * @Title: getPostMethod
     * @Description: (设置params参数)
     */
    private PostMethod getPostMethod(String url, String params) {
        PostMethod postMethod = new PostMethod(url);

        List<NameValuePair> nameValueList = getCheckParam(params);

        postMethod.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        postMethod.setRequestBody(nameValueList.toArray(new NameValuePair[nameValueList.size()]));
        //        postMethod.addRequestHeader("Connection", "close");

        return postMethod;

    }

    /**
     * @param in
     * @return String    返回类型
     * @throws IOException
     * @throws
     * @Title: getStringByParams
     * @Description: (将流文件转换成字符)
     */
    private String getStringByParams(InputStreamReader in) throws IOException {

        BufferedReader reader = new BufferedReader(in);

        StringBuffer stringBuffer = new StringBuffer();

        String str = "";

        while ((str = reader.readLine()) != null) {

            stringBuffer.append(str);

        }

        String ts = stringBuffer.toString();

        return ts;
    }

    public String getParams(String siteCode) {

        StringBuilder str = new StringBuilder();
        str.append("{");
        str.append("\"siteCode\":\"" + siteCode + "\"");
        str.append("}");

        return str.toString();
    }


    public static void main(String[] args) {
        List<String> ls = new ArrayList<String>();
        String path = "D:\\datatext\\data04.txt";
        currentTime("开始读时间：");
        try {
            readFileByLines(path, ls);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        HttpClientUtilsForeach hcu = HttpClientUtilsForeach.getInstance();
        String url1 = "http://fo.ycgwl.com/rosefinch-web/ppapi/rest/queryPriceAndDiscount.api";
//        String url1 = "http://localhost:8081/rosefinch-ppTest-web/ppapi/rest/queryPriceAndDiscount.api";

        // 写到D盘的一个TXT文件
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("D:\\dataresult\\ResultDate04.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentTime("开始计算的时间：");
        for (int i = 0; i < ls.size(); i++) {
            String ssss = ls.get(i);
            String result1 = hcu.postHttpClientJson(url1, ssss);
            try {
                fileWriter.write(result1 + "\n");
                fileWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileWriter.close();
            currentTime("结束计算的时间：");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 输出系统时间
     */
    private static void currentTime(String st) {
        String fromFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(fromFormat);
        Date myDate = new Date();
        System.out.println(st + format.format(myDate));
    }


    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件原料
     */
    public static void readFileByLines(String fileName, List<String> ls) throws FileNotFoundException, UnsupportedEncodingException {
        File firstFile = new File(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(firstFile), "GBK"));
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            Date dt = new Date();
            String tempString = null;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                ls.add(tempString);
            }
            reader.close();
            currentTime("开始读结束时间：");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {

                }
            }
        }
    }

}
