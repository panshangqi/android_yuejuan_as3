package com.app.utils;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import com.app.yuejuan.Public;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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
                    //����ȡ����Ϣͨ��handler�������߳�
                    mHandler.sendMessage(mHandler.obtainMessage(0,resultSoapObject));
                }
            }
        });
    }

    public interface WebServiceCallBack{
        public void callBack(String result);
    }
    
}
