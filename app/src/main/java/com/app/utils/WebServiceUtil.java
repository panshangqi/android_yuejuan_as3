package com.app.utils;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

//import org.ksoap2.SoapEnvelope;
//import org.ksoap2.serialization.SoapObject;
//import org.ksoap2.serialization.SoapSerializationEnvelope;
//import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.app.yuejuan.Public;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/*
public class WebServiceUtil {
	//public static final String WEB_SERVER_URL = "http://121.18.49.118:88/exam/AppdatacenterImpPort?wsdl";//"http://49.4.48.115/exam/AppdatacenterImpPort?wsdl";
	//public static final String WEB_SERVER_URL = "http://49.4.48.115/exam/AppdatacenterImpPort?wsdl";
	//public static final String WEB_SERVER_URL = "http://"+Public.imageHost+"/exam/AppdatacenterImpPort?wsdl";
	
    // ����3���̵߳��̳߳�
    private static final ExecutorService executorService = Executors.newFixedThreadPool(8);//�����̳߳ش�СΪ8���̳߳�
    // �����ռ�
    private static final String NAMESPACE = "http://webservice.app.com/";//"http://121.18.49.118:88/";
    public static String getURL(){
    	return "http://"+Public.imageHost+"/exam/AppdatacenterImpPort?wsdl";
    }
    public static void callWebService(String url, final String methodName, HashMap<String,String> properties, final WebServiceCallBack webServiceCallBack){
        //����HttpTransportSE���󣬴���WebService��������ַ
    	Log.v("YJip", url);
        final HttpTransportSE httpTransportSE = new HttpTransportSE(url);
        //����SoapObject����
        final SoapObject soapObject = new SoapObject(NAMESPACE,methodName);
        //SoapObject��Ӳ���
        if (properties != null){
            for (Iterator<Map.Entry<String,String>> it = properties.entrySet().iterator(); it.hasNext();){
                Map.Entry<String,String> entry = it.next();
                soapObject.addProperty(entry.getKey(),entry.getValue());
            }
        }
        //ʵ����SoapSerializationEnvelope,����WebService��SOAPЭ��İ汾��
        final SoapSerializationEnvelope soapSerializationEnvelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

        //�����Ƿ���õ���.NET������WebService
        soapSerializationEnvelope.setOutputSoapObject(soapObject);
        soapSerializationEnvelope.dotNet = false;   //������ر�
        httpTransportSE.debug = true;

        //�������߳������߳�ͨ�ŵ�Handler
        final Handler mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //������ֵ�ص���callBack�Ĳ�����
                SoapObject result = (SoapObject) msg.obj;
                if(result == null)
                	webServiceCallBack.callBack(null);
                else{
                	String resStr = result.getProperty(0).toString();
                	int _len = resStr.length() - 1;
                	if(_len>1){
                		Log.v("Rsult", resStr);
                		if(resStr.charAt(0)=='[' && resStr.charAt(_len) == ']'){
                			resStr = resStr.substring(1, _len);
                			webServiceCallBack.callBack(resStr);
                			return;
                    	}
                	}
                	webServiceCallBack.callBack(resStr);
                    
                }
            }
        };
        //�����߳�ȥ����WebService
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                SoapObject resultSoapObject = null;
                try {
                    httpTransportSE.call(NAMESPACE + methodName,soapSerializationEnvelope);
                    if (soapSerializationEnvelope.getResponse() != null){
                        //��ȡ��������Ӧ���ص�SoapObject
                        resultSoapObject = (SoapObject) soapSerializationEnvelope.bodyIn;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }finally {
//                    Message msg = handler.obtainMessage();
//                    msg.arg1 =
                    mHandler.sendMessage(mHandler.obtainMessage(0,resultSoapObject));
                }
            }
        });
    }

    public interface WebServiceCallBack{
        public void callBack(String result);
    }
    
}
*/


public class WebServiceUtil {
    //public static final String WEB_SERVER_URL = "http://121.18.49.118:88/exam/AppdatacenterImpPort?wsdl";//"http://49.4.48.115/exam/AppdatacenterImpPort?wsdl";
    //public static final String WEB_SERVER_URL = "http://49.4.48.115/exam/AppdatacenterImpPort?wsdl";
    //public static final String WEB_SERVER_URL = "http://"+Public.imageHost+"/exam/AppdatacenterImpPort?wsdl";
//114.116.116.99:88   1003 888888
    // ����3���̵߳��̳߳�
    private static final ExecutorService executorService = Executors.newFixedThreadPool(8);//�����̳߳ش�СΪ8���̳߳�
    // �����ռ�
    private static final String NAMESPACE = "http://webservice.app.com/";//"http://121.18.49.118:88/";
    public static String getURL(){
        return "http://"+Public.imageHost+"/exam/AppdatacenterImpPort?wsdl";
    }
    public static Handler mHandler = null;
    public void Http_Async(String http_url,  String methodName, HashMap<String,String> properties,  WebServiceCallBack webServiceCallBack, Handler handler){
        HttpURLConnection conn = null;
        String route = methodName;
        Log.v("YJ",route);
        String HttpResult = "";
        try {
            String xml = "<?xml version=\"1.0\"?>";
            xml += "<soap:Envelope ";
            xml += "xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" ";
            xml += "xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" ";
            xml +=  "xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" ";
            xml += "xmlns:AppdatacenterImpService=\"http://webservice.app.com/\" ";
            xml += "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" ";
            xml += "xsl:version=\"1.0\">";
            xml += "<soap:Body>";
            xml += "<AppdatacenterImpService:";
            xml += route;
            xml += ">";
            int count = 0;
            if (properties != null) {
                for (Iterator<Map.Entry<String, String>> it = properties.entrySet().iterator(); it.hasNext(); ) {
                    Map.Entry<String, String> entry = it.next();
                    //soapObject.addProperty(entry.getKey(),entry.getValue());
                    //xml += "<arg" + String.valueOf(count) + ">" + entry.getValue() + "</arg" + String.valueOf(count) + ">";
                    xml += "<" + entry.getKey() + ">" + entry.getValue() + "</" + entry.getKey() + ">";
                    count++;
                }
            }
            xml += "</AppdatacenterImpService:" + route + ">" +
                    "</soap:Body>" +
                    "</soap:Envelope>";
            // 创建url资源
            URL url = new URL(http_url);
            Log.v("YJ", xml);

            // 建立http连接
            conn = (HttpURLConnection) url.openConnection();
            // 设置允许输出
            conn.setDoOutput(true);

            conn.setDoInput(true);

            // 设置不用缓存
            conn.setUseCaches(false);
            // 设置传递方式
            conn.setRequestMethod("POST");

            conn.setConnectTimeout(10000);
            // 设置维持长连接
            conn.setRequestProperty("Connection", "keep-alive");
            // 设置文件字符集:
            //conn.setRequestProperty("Charset", "UTF-8");
            conn.setRequestProperty("Accept", "*/*");
            //转换为字节数组
            byte[] data = xml.getBytes();
            // 设置文件长度
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            // 设置文件类型:
            conn.setRequestProperty("Content-Type", "text/xml");
            //conn.setRequestProperty("contentType", "text/xml");
            // 开始连接请求

            conn.connect();

            OutputStream out = conn.getOutputStream();
            // 写入请求的字符串
            out.write(data);
            out.flush();
            out.close();

            System.out.println(conn.getResponseCode());

            // 请求返回的状态
            if (conn.getResponseCode() == 200) {
                Log.v("YJ", "okok++");
                // 请求返回的数据
                InputStream in = conn.getInputStream();
                // 封装输入流is，并指定字符集
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                String result = sbf.toString();
                int start = result.indexOf("<return>");
                int end = result.indexOf("</return>");
                if(start != -1 && end != -1){

                    String final_result = result.substring(start+9, end-1);
                    final_result = final_result.replaceAll("&quot;","\"");  //114.116.116.99:88
                    Log.v("YJ", final_result);
                    HttpResult = final_result;
                    //webServiceCallBack.callBack(final_result);
                }else{
                    Log.v("YJ", "result substring error");
                }


            } else {

                Log.v("YJ", "no++");
                //webServiceCallBack.callBack(null);

            }

        } catch (Exception e) {
            Log.v("YJ","login error");
            e.printStackTrace();
            //webServiceCallBack.callBack(null);
        } finally {
            Message msg = Message.obtain();
            msg.what = 0;
            msg.obj = HttpResult;
            handler.sendMessage(msg);

            if (conn != null) {
                conn.disconnect();
            }
        }


    }
    public static void callWebService(final String http_url, final String methodName, final HashMap<String,String> properties, final WebServiceCallBack webServiceCallBack) {
        //114.116.116.99.88   1003 888888
//        Thread thread = new MyThread2(http_url, methodName, properties, webServiceCallBack);
//        thread.start();
        final WebServiceUtil webUtil = new WebServiceUtil();
        final Handler handler = webUtil.getHandler(webServiceCallBack);
        new Thread(new Runnable(){
            @Override
            public void run() {
                webUtil.Http_Async(http_url,methodName,properties,webServiceCallBack,handler);
            }
        }).start();
    }
    public Handler getHandler(final WebServiceCallBack webServiceCallBack){
        //if(null == mHandler){
            mHandler = new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                    String result = (String)msg.obj;
                    Log.v("YJ >>>",result);
                    if(result == "")
                        webServiceCallBack.callBack(null);
                    else{
                        webServiceCallBack.callBack(result);
                    }
                }
            };
        //}
        return mHandler;
    }

    public interface WebServiceCallBack{
        public void callBack(String result);
    }

}
