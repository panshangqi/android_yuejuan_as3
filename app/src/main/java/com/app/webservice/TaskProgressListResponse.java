
package com.app.webservice;
import java.util.ArrayList;
import java.util.List;
import android.util.Log;

import com.app.yuejuan.Public;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
/*
String queid      //���
String quename    //��Ŀ����
String flag_mode  //����ģʽ
int taskcount     //��������
int totalnum_1    //����һ����
int totalnum_2    //���������
int totalnum_3    //����������
int totalnum_wc   //����������ٲ���
int totalnum_yc   //�����쳣���ٲ���
int totalnum_3n   //��������δ����
int totalnum_wcn  //�������δ�ٲ���
int totalnum_ycn  //�����쳣δ�ٲ���
int wanchengtotal //�������������
double reat       //��ɱ���
String avg        //����ƽ����
int teacount      //������������
int teayccount    //�����ύ�쳣������
String teaavg     //����ƽ����
int grouptaskcount //С��������
int groupwanchengcount  //С�������
double groupwanchenglv  //С����ɱ���
*/
public class TaskProgressListResponse {

  
	protected String codeid;
	protected String message;
	protected String authtoken = "";
    
	public static class Datas{
		public int grouptaskcount;
		public int groupwanchengcount;
		public int groupwanchenglv;
		public int taskcount;
		public int teacount;
		public int teayccount;
		public double reat;
		public int wanchengtotal;
		public String queid;
		public String quename;
		public String flag_mode;
		
	}
	
	public List<Datas> dataList;

    /**
     * Gets the value of the return property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
	public TaskProgressListResponse(String result){
		
		try {
			JsonParser parser=new JsonParser();  //����JSON������1
			JsonObject object=(JsonObject) parser.parse(result);  //����JsonObject����
			this.codeid = object.get("codeid").getAsString();
			dataList = new ArrayList();
			if(Public.responseID.equals(this.codeid)){
				JsonArray jsonArr = object.get("message").getAsJsonArray();
				for(int i=0;i<jsonArr.size();i++){
	                Datas data = new Datas();
	                JsonObject subObject=jsonArr.get(i).getAsJsonObject();
	                data.grouptaskcount = subObject.get("grouptaskcount").getAsInt();
	                data.groupwanchengcount = subObject.get("groupwanchengcount").getAsInt();
	                data.groupwanchenglv = subObject.get("groupwanchenglv").getAsInt();
	                data.taskcount = subObject.get("taskcount").getAsInt();
	                data.teacount = subObject.get("teacount").getAsInt();
	                data.teayccount = subObject.get("teayccount").getAsInt();
	                data.reat = subObject.get("reat").getAsDouble();
	                data.wanchengtotal = subObject.get("wanchengtotal").getAsInt();
	                data.queid = subObject.get("queid").getAsString();
	                data.quename = subObject.get("quename").getAsString();
	                data.flag_mode = subObject.get("flag_mode").getAsString();
	                dataList.add(data);
	            }
			}
			
		} catch (JsonIOException e) {
            e.printStackTrace();
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
    public String getCodeID() {
        return this.codeid;
    }
    public String getMessage() {
        return this.message;
    }
    public String getAuthtoken() {
        return this.authtoken;
    }
    @Override
    public String toString(){
    	return "codeid="+this.codeid+",message="+this.message+",authtoken="+this.authtoken;
    }
}
