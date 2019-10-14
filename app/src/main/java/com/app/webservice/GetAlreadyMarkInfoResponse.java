
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
(9)
GetAlreadmarkinfo(String userid,String authtoken,String subjectid,int secretid)

[{"codeid":"0001","message":[{"examid":"101406212","firstmark":"3","firstsmallmark":"","flag_send":1,"fullmark":8,"imgurl":"exam_d/exam_pic/ceshi/wenzong/001/101406212/6.jpg","queid":6,"quename":"","scorepoints":"0,1,2,3,4,5,6,7,8","secretid":13,"smallqueinfo":[],"smallquenum":0}]}]
[{"codeid":"0002","message":""}]
[{"codeid":"0003","message":""}]

message json
queid
quename
fullmark
smallquenum
scorepoints
smallqueinfo
{
smallqueid
smallquename
smallfullmark
smallscorepoints
}

secretid
examid
imgurl
flag_send
firstmark
firstsmallmark
 */
public class GetAlreadyMarkInfoResponse {

  
	protected String codeid;
	protected String message;
	public static class SmallQueInfo{
		public String smallqueid;
		public String smallquename;
		public String smallfullmark;
		public String smallscorepoints;
	}
	public static class Datas{
		public String queid;
		public String quename;
		public String fullmark;
		public String scorepoints;
		public int smallquenum;
		public String secretid;
		public String examid;
		public String imgurl;
		public String flag_send;
		public String firstmark;
		public String firstsmallmark;
		public String commentimage; //
		public List<SmallQueInfo> smallqueinfoList;
		
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
	public GetAlreadyMarkInfoResponse(String result){
		dataList = new ArrayList();
		try {
			JsonParser parser=new JsonParser();  //
			JsonObject object=(JsonObject) parser.parse(result);  //
			this.codeid = object.get("codeid").getAsString();
			if(Public.responseIDOK.equals(this.codeid)){
				JsonArray jsonArr = object.get("message").getAsJsonArray();
				for(int i=0;i<jsonArr.size();i++){
					Datas data = new Datas();
	                JsonObject subObject=jsonArr.get(i).getAsJsonObject();
	                data.queid = subObject.get("queid").getAsString();
	                data.quename = subObject.get("quename").getAsString();
	                data.firstmark = subObject.get("firstmark").getAsString();
	                data.firstsmallmark = subObject.get("firstsmallmark").getAsString();
	                data.fullmark = subObject.get("fullmark").getAsString();
	                data.scorepoints = subObject.get("scorepoints").getAsString();
	                data.examid = subObject.get("examid").getAsString();
	                data.flag_send = subObject.get("flag_send").getAsString();
	                data.secretid = subObject.get("secretid").getAsString();
	                data.imgurl = subObject.get("imgurl").getAsString();
	                data.smallquenum = subObject.get("smallquenum").getAsInt();
	                data.commentimage = subObject.get("commentimage").getAsString();
	                data.scorepoints =  subObject.get("scorepoints").getAsString();
	                data.smallqueinfoList = new ArrayList();
	                JsonArray smallQueArr = subObject.getAsJsonArray("smallqueinfo");
	                for(int j=0;j<smallQueArr.size();j++){
	                	SmallQueInfo sqInfo = new SmallQueInfo();
	                	JsonObject sqObject=smallQueArr.get(j).getAsJsonObject();
		                sqInfo.smallqueid = sqObject.get("smallqueid").getAsString();
		                sqInfo.smallquename = sqObject.get("smallquename").getAsString();
		                sqInfo.smallfullmark = sqObject.get("samllfullmark").getAsString();
		                sqInfo.smallscorepoints = sqObject.get("smallscorepoints").getAsString();
		                
		                data.smallqueinfoList.add(sqInfo);
	                }
	                
	                dataList.add(data);
	            }
				this.message = "";
			}else{
				this.message = object.get("message").getAsString();
				
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

    
}
