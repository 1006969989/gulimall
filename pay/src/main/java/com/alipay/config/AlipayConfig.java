package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "9021000131685169";

	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC28d6eysv02qEgwpleogmCEMd6/0+mRmNM+U8JJjiUIbxtaVp46F5oyFyn6nuUFygqaUZXxWaxNdDdL9XUtwfja77LpStzgYtGShYeVPIafkWR3niFHz40wF4HjZ/N7ue+QEl0dAP5p04q3Eh+8oA4jcaHhjZJ4rqvTyyVh+5ovP+5F1K+8EHKYRhk1vEkGyVuoe4a4HUpG10JfJHSi4KQ1w9H3ZvOHlz7740fOQ2RDIinRCKBaB/4Pnbu4ljkov05gVhS+y9ds25Yq85CFVHhYSdvjN7uz3kkGht8oaJ+y9oqp/xYqZzdcRbihJ5SEZ7O0jog3BcC5KKztmLrWCl9AgMBAAECggEAb3XzlbmnA/qEOwp1GQQOWPFSKC0PYRX3gDlRHxjrVc0sGVkb5xhkFOEmGqQxQKvPK+/SDL3XcFaCd5CibiRJqRTHPRnX3Frr+RpAWaCVlPCkAFiUAz9izg75+w4ddAF/rhlK56mblmqJpHFrtfSXjF1MvNtXjjKWXSMW3c/gwGyDzqmvz5uU67YmJHlZj3EEG1KJ23WyB9r1ABxy2gUAQU5T6QA1pJdA1vO5veSBb/ramAsO/h75oUYjBJWhoZZD0qI9JYBLT43n5wad/R1W5dsoeUFqzmanh+nAcq4bZFGLbl81wrueqvGfQn0KnocgmldhhuoUSA2OwVAneLiUPQKBgQD52j1hpSxDSRIuQ8PenaT134TEuUXbAywyWX2TV+MVNWEweE6Isd1uhVG1ZBuM8ARmwPULh12dwPrnfA7ecIcU/K5QdxFxEQm6GhjAzDYb3YhQPcsQW3XaW1P4CGu4Yf1s1VquC/rRnnaQz6qsbmFXUXydTT3/inzvZUJ44Ow6mwKBgQC7cjHLPVO29WlX3MVad/snf21zQNGo8S51+C9x8fFNtxNH+x+aLiEVWnx5MpOwDqvA6lTDeuw9+/+kiZAmqxFqYF5e4eK5Xr3aJH1WTuoNdJP2BidqAgHup1LxYnxp4DMyRATqA1p4CRbPNsW6ptb9hl/kEWgTFYehXKEBwDQBxwKBgD5nrScjaz/PE3RsxpJmy5oB3xaHtBP04egdhAj2TaixcXn6wprP2h/Nus/UljEIjgDgNxRPFind1o1U5h3W+1ZZmmxMoRWdjNFnzUDygJ1LIs3v8TU/str8Lqfn6ofeKb8yNvj70wTiUHjw9Y8l7rgxiS986HxYKcZdg1HP1BD7AoGAf1TQXHZ5crR87fGLoqwUUT1vtTYX9kJ3xC1m1Wl7+MXfdCszyYGCecXw/keMKbGdnLvR/sR2zJhtjXDhGlNkzjVOhfe+Exw3rYLSHZoXxjIl/J8rkQhyFZNWdaDoUOe6/29TQ0Ap6ZaLBxH2+ZcyPiBU7VGhuC0M6GFLAix1Z6sCgYEAz/yi2RFvOQ7p/MCGI6IUmH/J+P/mRxxw9NdMz96f49u407y8ESsxIKa3jLrM+j6sZUst4YQIMopBMfZtR+xUdFGe7t51wbM0Uh1OrAck+QQe6c4OJKciaxdfoi1I5QFLKWdH2sCCquhcKEfNlvspkJDBiWDjCeFnNbOt4xCPWxY=";

	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoXKhcF/YPePXZb1xUlb+vmcikc9vSJMfpMKvmob2MZg5p+13cVOQoZt9cKwccn0WbRUoP9bTNidlg93SUFx1Oqxs4XughKSH71eufk5UwjI1k4cpmAipo1MD/IZ68Ad9G7xmTMUshwWfhOm3eI/AueM3HWcWj9f2xQBGYI4cCTwu6xR+3h1UjUhAIzuwOr0dHpHW1RmlTu8/F7J81XjvTJRneQ595Y67o/qG6OmM76L4DCAnpQ9qxxQ8yDLUNdWhiflRmvqHf0BkhMk7ed7bhkD9DbXCF6jS/uYCRanTyJruZJ9+h6SOFzTH1mMK0WGsx2rtUl1FstfIVwRmM8XTIQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";

	// 字符编码格式
	public static String charset = "utf-8";

	// 支付宝网关                        https://openapi-sandbox.dl.alipaydev.com/gateway.do
	public static String gatewayUrl = "https://openapi-sandbox.dl.alipaydev.com/gateway.do";

	// 支付宝网关
	public static String log_path = "C:\\";
    /**
     * 买家账号 imrota7183@sandbox.com
     * 登录密码 111111
     * 支付密码 111111
     * 用户UID 2088722021361736
     * 用户名称 imrota7183
     * 证件类型 IDENTITY_CARD
     * 证件账号 623389190111038938
     */

//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

