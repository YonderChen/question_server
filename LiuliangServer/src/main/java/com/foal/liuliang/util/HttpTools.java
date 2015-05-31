/**
 * 
 */
package com.foal.liuliang.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author jackyli515
 *
 */
public class HttpTools {
    public static String sendGet(String url, Map<String,String> params) throws ConnectException {
    	return sendGet(url, params, null);
    }
	/**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数
     * @return URL 所代表远程资源的响应结果
	 * @throws ConnectException 
     */
    public static String sendGet(String url, Map<String,String> params, String charset) throws ConnectException {
        String result = "";
        BufferedReader in = null;
        try {
            /**组装参数**/
            String param = parseParams(params);
            String urlNameString = url;
            if (StringTools.isNotBlank(param)) {
            	urlNameString = urlNameString + "?" + param;
			}
            URL realUrl = new URL(urlNameString);
            /**打开和URL之间的连接**/
            URLConnection connection = realUrl.openConnection();
            /**设置通用的请求属性**/
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            /**建立实际的连接**/
            connection.connect();
            /**定义 BufferedReader输入流来读取URL的响应**/
            if (StringTools.isBlank(charset)) {
                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			}
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            if (e instanceof ConnectException) {
				throw (ConnectException)e;
			}
        } finally {/**使用finally块来关闭输入流**/
            try {
                if(in != null) { in.close(); }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
    public static String sendPost(String url, Map<String,String> params) throws ConnectException {
    	return sendPost(url, params, null);
    }
    /**
     * 向指定 URL 发送POST方法的请求
     * @param url 发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     * @throws ConnectException 
     */
    public static String sendPost(String url, Map<String,String> params, String charset) throws ConnectException {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            /**打开和URL之间的连接**/
            URLConnection conn = realUrl.openConnection();
            /**设置通用的请求属性**/
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            /**发送POST请求必须设置如下两行**/
            conn.setDoOutput(true);
            conn.setDoInput(true);
            /**获取URLConnection对象对应的输出流**/
            out = new PrintWriter(conn.getOutputStream());
            /**发送请求参数**/
            String param = parseParams(params);
            out.print(param);
            /**flush输出流的缓冲**/
            out.flush();
            /**定义BufferedReader输入流来读取URL的响应**/
            if (StringTools.isBlank(charset)) {
                in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset));
			}
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            if (e instanceof ConnectException) {
				throw (ConnectException)e;
			}
        } finally{ /**使用finally块来关闭输出流、输入流**/
            try{
                if(out!=null){   out.close();}
                if(in!=null){ in.close(); }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }   
    
    /**
     * 将HashMap参数组装成字符串
     * @param map
     * @return
     */
    private static String parseParams(Map<String,String> map){
    	
        StringBuffer sb = new StringBuffer();
        if(map != null && map.size()>0){
            for (Entry<String, String> e : map.entrySet()) {
                sb.append(e.getKey());
                sb.append("=");
                sb.append(e.getValue());
                sb.append("&");
            }
            sb.deleteCharAt(sb.length()-1);
        }
        return sb.toString();
    }
    
    public static void main(String[] args){
    	String url = "http://api.sandbox.aymoo.com/api/tbmobi/add?kwd=中年女装&begin_time=2015-05-31&nid=37746241150&sleep_time=150&click_end=23&appkey=test&shop_type=b&sign=b15d587e54ea45f0e290e9e5398332d3&timestamp=1433080473&path2=100&times=20&end_time=2015-06-15&path1=0&click_start=0&path3=0";
    	
    	String content = "";
		try {
			content = HttpTools.sendGet(url, null);
	    	System.out.println(content);
		} catch (ConnectException e) {
			e.printStackTrace();
			//链接超时
		}
    }
}
