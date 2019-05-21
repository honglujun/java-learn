package com.java.learn.httpclient;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.MultiThreadedHttpConnectionManager;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class HttpClientUtil {

    private static final String DEFAULT_ALP_DIGEST_NAME = "default_alp_digest_name";
    private static final String DEFAULT_ALP_TIMESTAMP_NAME = "default_alp_timestamp_name";
    private static MultiThreadedHttpConnectionManager connectionManager = new MultiThreadedHttpConnectionManager();


    private static final int TIMEOUT = 5 * 1000000;//5 * 1000

    //            StringUtils.strToInteger(propFactory.getConfig("max_http_connection"), 50);// 50
    private static final int MAX_HTTP_CONNECTION = 50;


    private static final int MAX_CONNECTION_PER_HOST = 10;// 10

    private static final String CHARSET_UTF8 = "UTF-8";

    private static HttpClientUtil instance = null;

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

    public HttpClientUtil() {

    }

    public static HttpClientUtil getInstance() {

        if (null == instance) {

            synchronized (HttpClientUtil.class) {

                if (instance == null) {

                    instance = new HttpClientUtil();

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
                    HttpClientUtil.CHARSET_UTF8);
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
                    HttpClientUtil.CHARSET_UTF8);
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
            if (postMethod != null){
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
//        String digest = SecurityUtil.getDigest(params + APP_KEY + APP_SECRET);
        String digest = params + APP_KEY + APP_SECRET;
        String timestamp = "" + System.currentTimeMillis();
//			String timestamp = "" + new Date().getTime();
        final List<NameValuePair> nameValueList = new ArrayList<NameValuePair>();
        // 接口调用的请求格式
        nameValueList.add(new NameValuePair("params", params));

        nameValueList.add(new NameValuePair(DEFAULT_ALP_DIGEST_NAME, digest));
        nameValueList.add(new NameValuePair(DEFAULT_ALP_TIMESTAMP_NAME, timestamp));
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
        HttpClientUtil hcu = HttpClientUtil.getInstance();
        // 标准报价与折扣
//	        String url1 = "http://fo.ycgwl.com/rosefinch-web/ppapi/rest/queryPriceAndDiscount.api";
			String url1 = "http://172.16.36.14:8080/rosefinch-web/ppapi/rest/queryPriceAndDiscount.api";
        // 标准报价与折扣
//        String url1 = "http://localhost:8081/rosefinch-ppTest-web/ppapi/rest/queryPriceAndDiscount.api";
//	         String url ="http://10.133.7.73:8081/rosefinch-webTest/foapi/rest/queryAccountInfo.api";
        // 送货上楼费
        String url2 = "http://172.16.36.14:8080/rosefinch-web/ppapi/rest/queryUpFloorPrice.api";
        // 装卸费
        String url3 = "http://localhost:8081/rosefinch-ppTest-web/ppapi/rest/queryPpStevedoragePrice.api";
        // 包装费
        String url4 = "http://fo.ycgwl.com/rosefinch-web/ppapi/rest/queryPpPackPrice.api";
        //String params = "{\"billNo\":\"\",\"accountTime\":\"2018-10-23 11:19:54\",\"dataId\":\"0\",\"dataType\":\"7\",\"dataSiteCode\":\"517P02\",\"sendDataType\":\"7\",\"sendEmployeeCode\":\"37703\",\"sendSiteCode\":\"517P02\",\"dispatchDataId\":\"0\",\"dispatchDataType\":\"7\",\"dispatchEmployeeCode\":\"\",\"dispatchSiteCode\":\"579J01\",\"dispatchScanTime\":\"\",\"pickGoodsType\":\"SHSM\",\"productCode\":\"\",\"bizType\":\"1\",\"productTimeType\":\"0\",\"payType\":\"2\",\"goodsMode\":null,\"goodsType\":\"\",\"piece\":\"2\",\"codPayMode\":null,\"codReturnTime\":null,\"calcType\":\"2\",\"calcWeight\":\"6.0\",\"callWeight\":\"4\",\"insuredAmount\":\"4\",\"inputFeeList\":{\"codCharge\":\"3\",\"otherFee\":\"3\",\"upStairsFee\":\"2\",\"wareHouseFee\":\"3\",\"makeDocFee\":\"5\",\"sendGoodsFee\":\"0\",\"packagingFee\":\"3\",\"superzoneDispFee\":\"3\",\"rBillFee\":\"3\",\"standardFreight\":\"2\",\"transferFee\":null,\"transferFeeTwo\":null,\"codPoundageIn\":null,\"codPoundageOut\":null,\"arrivePayPoundageIn\":null,\"arrivePayPoundageOut\":null,\"insuredFee\":\"4\",\"dispatchFeeIn\":null,\"dispatchFeeOut\":null,\"siteTransferFee1\":null,\"siteCodPoundageIn1\":null,\"siteCodPoundageOut1\":null,\"siteArrivePayPoundageIn1\":null,\"siteArrivePayPoundageOut1\":null,\"siteDispatchChargeIn1\":null,\"siteDispatchChargeOut1\":null,\"siteTransferFee2\":null,\"siteCodPoundageIn2\":null,\"siteCodPoundageOut2\":null,\"siteArrivePayPoundageIn2\":null,\"siteArrivePayPoundageOut2\":null,\"siteDispatchChargeIn2\":null,\"siteDispatchChargeOut2\":null},\"handFlag\":null,\"sendSiteHandFlag\":null,\"dispatchSiteHandFlag\":null,\"remark\":\"\",\"customerCode\":\"\"}";
        // 标准报价与折扣330701
//	        String params1="{\"bizType\":\"1\",\"blFlag\":\"false\",\"calcWeight\":\"176.00\",\"customerCode\":\"D00107747\",\"customerType\":\"17\",\"desSiteType\":\"\",\"dispatchSiteCode\":\"Z00536\",\"fromTime\":\"2018-04-03 15:02:16\",\"goodsName\":\"米粉\",\"goodsType\":\"7\",\"interfaceType\":\"WBMS\",\"payType\":\"3\",\"pickGoodsType\":\"2\",\"productCode\":\"2\",\"sendSiteCode\":\"791N01\"}";
//			String params1 = "{\"bizType\":\"1\",\"blFlag\":\"false\",\"calcWeight\":\"1300\",\"customerCode\":\"D00137658\",\"customerType\":\"4\",\"desSiteType\":\"\",\"dispatchSiteCode\":\"579J01\",\"dispatchAddressCode\":\"330100\",\"fromTime\":\"2018-06-11 16:16:57\",\"goodsName\":\"原料\",\"goodsType\":\"9\",\"interfaceType\":\"WBMS\",\"payType\":\"1\",\"pickGoodsType\":\"2\",\"productCode\":\"3\",\"sendSiteCode\":\"D00039\"}";
        String params1 = "{\"bizType\":\"1\",\"blFlag\":\"false\",\"calcWeight\":\"1300\",\"customerCode\":\"D00137658\",\"customerType\":\"4\",\"desSiteType\":\"\",\"dispatchSiteCode\":\"579J01\",\"dispatchAddressCode\":\"330100\",\"fromTime\":\"2018-06-11 16:16:57\",\"goodsName\":\"原料\",\"goodsType\":\"9\",\"interfaceType\":\"WBMS\",\"payType\":\"1\",\"pickGoodsType\":\"2\",\"productCode\":\"3\",\"sendSiteCode\":\"D00039\"}";
//	        String params="{\"sendSiteCode\":\"北京153M\",\"dispatchSiteCode\":\"广州92M\",\"fromTime\":\"2018-10-12 09:45:19\",\"calcWeight\":20,\"blFlag\":true,\"bizType\":1}";
        // 送货上楼费
        String params2 = "{\"addressCode\":\"530102\",\"bilType\":1,\"billCode\":\"201805021927\",\"calcWeight\":80.0,\"customerCode\":\"EC0041207\",\"elevatorFlag\":\"1\",\"floor\":0,\"fromTime\":\"2018-06-06 11:40:30\",\"interfaceType\":\"WBMS\",\"sendAddressCode\":\"021I01\",\"upstairsTypeCode\":\"1\"}";

//			String params2="{ \"billCode\": \"201805021901\", \"bilType\": \"1\", \"addressCode\": \"320507\", \"sendAddressCode\": \"021I01\", \"upstairsTypeCode\": \"3\", \"fromTime\": \"2018-05-21 15:56:35\", \"elevatorFlag\": \"0\", \"floor\": 15, \"calcWeight\": 65.0, \"customerCode\": \"D00001212\", \"interfaceType\": \"WBMS\" }";
//			String params2="{\"addressCode\":\"350582\",\"calcWeight\":20,\"customerCode\":\"\",\"elevatorFlag\":\"2\",\"floor\":15,\"fromTime\":\"2018-05-02 15:56:35\",\"interfaceType\":\"WBMS\",\"sendAddressCode\":\"595I01\",\"upstairsTypeCode\":\"2\"}";
        // 装卸费
        String params3 = "{\"addressCode\":\"350505\",\"sendAddressCode\":\"595I01\",\"calcWeight\":\"101\",\"fromTime\":\"2018-05-15 15:07:52\",\"interfaceType\":\"WBMS\"}";
        // 包装费  // 2,105
//			String params4="{\"sendAddressCode\":\"595I01\",\"fromTime\":\"2018-10-29 16:12:50\",\"interfaceType\":\"WBMS\",\"packOtherPriceList\":[{\"calcNumber\":\"2\",\"packType\":\"102\"},{\"calcNumber\":\"2\",\"packType\":\"101\"},{\"calcNumber\":\"2\",\"packType\":\"108\"}]}";
        String params4 = "{\"billCode\": \"300013823125\",\"bilType\": \"1\",\"sendAddressCode\": \"CSWDA\",\"fromTime\": \"2018-05-23 12:30:00\",\"interfaceType\": \"WBMS\",\"packOtherPriceList\": [{\"packType\": \"107\",\"calcNumber\": 1.0}]}";
        String result1 = hcu.postHttpClientJson(url1, params1);
        System.out.println(result1);
//			String result2 = hcu.postHttpClientJson(url2,params2);
//			System.out.println(result2);
//			String result3 = hcu.postHttpClientJson(url3,params3);
//			System.out.println(result3);
//			String result4 = hcu.postHttpClientJson(url4,params4);
//			System.out.println(result4);
/*
			System.out.println(System.currentTimeMillis());
*/


    }
}
