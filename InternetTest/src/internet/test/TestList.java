package internet.test;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestList extends ArrayList<Test> {

	public int GetCount(){
		int i = 0;
		for(Test t : this){
			i++;
		}
		return i;
	}
	
	public static TestList GetListByJSON(int par1, int par2, int par3) {
		TestList _list = new TestList();

		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs
				.add(new BasicNameValuePair("year", String.valueOf(par1)));
		nameValuePairs
				.add(new BasicNameValuePair("month", String.valueOf(par2)));
		nameValuePairs.add(new BasicNameValuePair("day", String.valueOf(par3)));

		String results = Utility.GetHttpFromServer(nameValuePairs);

		if (results != null) {
			if (results.compareTo("Error") != 0) {
				// parse json data
				try {
					JSONArray jArray = new JSONArray(results);
					for (int i = 0; i < jArray.length(); i++) {
						JSONObject json_data = jArray.getJSONObject(i);
						Test _t = new Test();
						if (!json_data.isNull("ClientID")) {
							_t.SetID(json_data.getInt("ClientID"));
						}
						if (!json_data.isNull("ClientFullName")) {
							_t.SetName(json_data.getString("ClientFullName"));
						}
						if (!json_data.isNull("ImagePath")) {
							_t.SetParams(json_data.getString("ImagePath"));
						}
						_list.add(_t);
					}
				} catch (JSONException e) {
					// Log.e("log_tag", "Error parsing data "+e.toString());
				}
			}
		}

		return _list;
	}
	
	public static TestList GetListByXML() {

		TestList _list = new TestList();
		
		_list = Utility.GetXMLFromServer();

//		if (results != null) {
//			if (results.compareTo("Error") != 0) {
//				// parse json data
//				try {
//					JSONArray jArray = new JSONArray(results);
//					for (int i = 0; i < jArray.length(); i++) {
//						JSONObject json_data = jArray.getJSONObject(i);
//						Test _t = new Test();
//						if (!json_data.isNull("ClientID")) {
//							_t.SetID(json_data.getInt("ClientID"));
//						}
//						if (!json_data.isNull("ClientFullName")) {
//							_t.SetName(json_data.getString("ClientFullName"));
//						}
//						if (!json_data.isNull("ImagePath")) {
//							_t.SetParams(json_data.getString("ImagePath"));
//						}
//						_list.add(_t);
//					}
//				} catch (JSONException e) {
//					// Log.e("log_tag", "Error parsing data "+e.toString());
//				}
//			}
//		}

		return _list;
	}
	
	public static TestList GetListByXML2() {

		TestList _list = new TestList();
		
		_list = Utility.GetXMLFromServer2();

		return _list;
	}
	
	public static TestList GetListByXML3() {

		TestList _list = new TestList();
		
		_list = Utility.GetXMLFromServer3();

		return _list;
	}
	
	public static TestList GetListByXML4() {

		TestList _list = new TestList();
		
		_list = Utility.GetXMLFromServer();

		return _list;
	}

}
