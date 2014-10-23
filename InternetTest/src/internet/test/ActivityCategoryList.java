package internet.test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

public class ActivityCategoryList extends ArrayList<ActivityCategory> {

	public static final String UrlAddress = "http://simo-mobile.com/activity_script.php";
	public static final String UrlAddressIcon = "http://simo-mobile.com/icon/";

	public int GetCount() {
		int count = 0;
		for (ActivityCategory _cat : this) {
			count++;
		}
		return count;
	}

	public static ActivityCategoryList GetActivityCategoryList(Context ctx) {
		ActivityCategoryList _list = new ActivityCategoryList();
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		Cursor c = db.GetActivityCategoryList();
		if (c.moveToFirst()) {
			do {
				_list.add(FillCategory(c, ctx));
			} while (c.moveToNext());
		}
		db.Close();
		return _list;
	}

	public static void ClearActivityCategoryList(Context ctx) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		Cursor c = db.GetActivityCategoryList();
		if (c.moveToFirst()) {
			do {
				int id = c.getInt(c.getColumnIndex("ActivityCategoryID"));
				db.DeleteActivityCategory(id);
			} while (c.moveToNext());
		}
		db.Close();
	}

	public static void InsertCategory(Context ctx, ActivityCategory cat) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		long id;
		id = db.InsertActivityCategory(cat.GetActivityCategoryName(),
				cat.GetActivityImageName(), cat.GetVercion(),
				cat.GetIsNeedUpdate());
		db.Close();
	}

	public static void UpdateCategory(Context ctx, ActivityCategory cat) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		boolean reply;
		reply = db.UpdateActivityCategory(cat.GetActivityCategoryID(),
				cat.GetActivityCategoryName(), cat.GetActivityImageName(),
				cat.GetVercion(), cat.GetIsNeedUpdate());
		db.Close();
	}

	public static void DeleteAllActivity(Context ctx) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		boolean reply;
		reply = db.DeleteAllActivityCategory();
		db.Close();
	}
	
	public static void DeleteActivity(Context ctx, int catID) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		boolean reply;
		reply = db.DeleteActivityCategory(catID);
		db.Close();
	}

	private static ActivityCategory FillCategory(Cursor c, Context ctx) {
		ActivityCategory cat = new ActivityCategory();

		cat.SetActivityCategoryID(c.getInt(c
				.getColumnIndex("ActivityCategoryID")));
		cat.SetActivityCategoryName(c.getString(c
				.getColumnIndex("ActivityCategoryName")));
		cat.SetActivityImageName(c.getString(c
				.getColumnIndex("ActivityImageName")));
		cat.SetVercion(c.getInt(c.getColumnIndex("ActivityVersion")));
		cat.SetIsNeedUpdate(c.getInt(c.getColumnIndex("ActivityNeedUpdate")));

		return cat;
	}

	public static ActivityCategoryList GetXMLFromServer() {
		ActivityCategoryList _list = new ActivityCategoryList();

		InputStream is = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(UrlAddress);
			// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			String ff = "Error";
		}

		if (is == null) {
			String ff = "Error";
		}
		// convert response to string

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("category");

			for (int temp = 0; temp < nodes.getLength(); temp++) {
				Node nNode = nodes.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					ActivityCategory newCat = new ActivityCategory();
					if (getTagValue("activitycategoryid", eElement).compareTo(
							"null") != 0) {
						newCat.SetActivityCategoryID(Integer
								.parseInt(getTagValue("activitycategoryid",
										eElement)));
					}
					if (getTagValue("activitycategoryname", eElement)
							.compareTo("null") != 0) {
						newCat.SetActivityCategoryName(getTagValue(
								"activitycategoryname", eElement));
					}
					if (getTagValue("activityimagebigname", eElement)
							.compareTo("null") != 0) {
						newCat.SetActivityImageName(UrlAddressIcon
								+ getTagValue("activityimagebigname", eElement));
					}
					if (newCat.GetActivityCategoryID() > 0) {
						_list.add(newCat);
					}
				}
			}
		} catch (Exception e) {
			String ff = "Error";
		}

		return _list;
	}

	

	public static void SaveListToSD(Context ctx, ActivityCategoryList _list) {
//		Bitmap bmImg;
		for (ActivityCategory cat : _list) {
			File filename;
			try {
				String storagePath = Environment.getExternalStorageDirectory()
						.toString();
				new File(storagePath + "/mvc/mvc").mkdir();
				filename = new File(storagePath + "/mvc/mvc/+ "
						+ cat.GetActivityImageName());
				FileOutputStream out = new FileOutputStream(filename);
//				bmImg.compress(Bitmap.CompressFormat.PNG, 20, out);
				out.flush();
				out.close();
				MediaStore.Images.Media.insertImage(ctx.getContentResolver(),
						filename.getAbsolutePath(), filename.getName(),
						filename.getName());
				cat.SetActivityImageName(filename.getPath());
				ActivityCategoryList.InsertCategory(ctx, cat);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void RefreshDB(Context ctx) {

		ActivityCategoryList _list = new ActivityCategoryList();

		InputStream is = null;
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(UrlAddress);
			// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			String ff = "Error";
		}

		if (is == null) {
			String ff = "Error";
		}
		// convert response to string

		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

			NodeList nodes = doc.getElementsByTagName("category");

			for (int temp = 0; temp < nodes.getLength(); temp++) {
				Node nNode = nodes.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					ActivityCategory newCat = new ActivityCategory();
					if (getTagValue("activitycategoryid", eElement).compareTo(
							"null") != 0) {
						newCat.SetActivityCategoryID(Integer
								.parseInt(getTagValue("activitycategoryid",
										eElement)));
					}
					if (getTagValue("activitycategoryname", eElement)
							.compareTo("null") != 0) {
						newCat.SetActivityCategoryName(getTagValue(
								"activitycategoryname", eElement));
					}
					if (getTagValue("activityimagebigname", eElement)
							.compareTo("null") != 0) {
						newCat.SetActivityImageName(getTagValue("activityimagebigname", eElement).replace(".png", ""));
					}
					if (newCat.GetActivityCategoryID() > 0) {
						_list.add(newCat);
					}
				}
			}
		} catch (Exception e) {
			String ff = "Error";
		}
		
		ActivityCategoryList.DeleteAllActivity(ctx);
		
		for (ActivityCategory cat : _list) {
//			try {
				ActivityCategoryList.InsertCategory(ctx, cat);
//				
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
		}
	}

	private static String getTagValue(String sTag, Element eElement) {
		try {
			NodeList nlList = eElement.getElementsByTagName(sTag).item(0)
					.getChildNodes();

			Node nValue = (Node) nlList.item(0);
			if (nValue == null) {
				return "null";
			}
			return nValue.getNodeValue();
		} catch (Exception e) {
			return "null";
		}

	}
}
