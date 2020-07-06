package com.sevenpay.agentmanager.common.utils.wx;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 工具类
 * @AUTHOR JIANGZONGLIN
 * @DATE 2019/10/15 16:21
 * @VERSION 1.0
 */
public class AuthUtil {

    /**
     * 执行url请求数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String getUrlData(String urlStr) throws Exception {
        URL url = new URL(urlStr);
        URLConnection connection = url.openConnection();
        //一旦发送成功，用以下方法就可以得到服务器的回应
        String currentLine = "";
        StringBuffer totalString = new StringBuffer();
        InputStream urlStream = connection.getInputStream();
        //
        BufferedReader rb = new BufferedReader(new InputStreamReader(
                urlStream, "UTF-8"));
        while ((currentLine = rb.readLine()) != null) {
            totalString.append(currentLine);
        }
        return totalString.toString();
    }

    /**
     * 执行url请求数据
     *
     * @param urlStr
     * @return
     * @throws Exception
     * @throws IOException
     */
    public static String postUrlData(String urlStr, String data)
            throws Exception {
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("POST");
        http.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");
        http.setDoOutput(true);
        http.setDoInput(true);
        System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒28
        System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒29
        //30
        OutputStream os = http.getOutputStream();
        os.write(data.getBytes("UTF-8"));// 传入参数
        os.flush();
        os.close();
        //一旦发送成功，用以下方法就可以得到服务器的回应：
        String currentLine = "";
        String totalString = "";
        InputStream urlStream = http.getInputStream();
        //
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                urlStream, "UTF-8"));
        while ((currentLine = reader.readLine()) != null) {
            totalString += currentLine;
        }
        return totalString;
    }


}
