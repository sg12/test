package internet.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class Utility {

	public static final String UrlAddress = "http://simo-mobile.com/activity_script.php";
	public static final String UrlAddressIcon = "http://simo-mobile.com/icon/";

	// public static final String UrlAddressIconEnd = "";

	public static String GetHttpFromServer(
			ArrayList<NameValuePair> nameValuePairs) {
		String results = null;

		// ArrayList<NameValuePair> nameValuePairs = new
		// ArrayList<NameValuePair>();
		// nameValuePairs.add(new BasicNameValuePair("year","1980"));

		InputStream is = null;

		// http post
		try {
			HttpClient httpclient = new DefaultHttpClient();
			HttpPost httppost = new HttpPost(UrlAddress);
			// httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			is = entity.getContent();
		} catch (Exception e) {
			return "Error";
		}

		if (is == null) {
			return results;
		}
		// convert response to string
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();

			results = sb.toString();
		} catch (Exception e) {
			return "Error";
		}

		return results;
	}

	public static TestList GetXMLFromServer() {
		TestList _list = new TestList();

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
					Test newTest = new Test();
					if (getTagValue("activitycategoryid", eElement).compareTo(
							"null") != 0) {
						newTest.SetID(Integer.parseInt(getTagValue(
								"activitycategoryid", eElement)));
					}
					if (getTagValue("activitycategoryname", eElement)
							.compareTo("null") != 0) {
						newTest.SetName(getTagValue("activitycategoryname",
								eElement));
					}
					if (getTagValue("activityimagebigname", eElement)
							.compareTo("null") != 0) {
						newTest.SetParams(UrlAddressIcon
								+ getTagValue("activityimagebigname", eElement));
					}
					if (newTest.GetID() > 0) {
						_list.add(newTest);
					}
				}
			}
		} catch (Exception e) {
			String ff = "Error";
		}

		return _list;
	}

	public static TestList GetXMLFromServer2() {
		TestList _list = new TestList();

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
					Test newTest = new Test();
					if (getTagValue("activitycategoryid", eElement).compareTo(
							"null") != 0) {
						newTest.SetID(Integer.parseInt(getTagValue(
								"activitycategoryid", eElement)));
					}
					if (getTagValue("activitycategoryname", eElement)
							.compareTo("null") != 0) {
						newTest.SetName(getTagValue("activitycategoryname",
								eElement));
					}
					// if (getTagValue("activityimagebigname",
					// eElement).compareTo("null") != 0) {
					// newTest.SetParams(UrlAddressIcon +
					// getTagValue("activityimagebigname", eElement));
					// }
					if (newTest.GetID() > 0) {
						_list.add(newTest);
					}
				}
			}
		} catch (Exception e) {
			String ff = "Error";
		}

		return _list;
	}

	public static TestList GetXMLFromServer3() {
		TestList _list = new TestList();

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
					Test newTest = new Test();
					if (getTagValue("activitycategoryid", eElement).compareTo(
							"null") != 0) {
						newTest.SetID(Integer.parseInt(getTagValue(
								"activitycategoryid", eElement)));
					}
					if (getTagValue("activitycategoryname", eElement)
							.compareTo("null") != 0) {
						newTest.SetName(getTagValue("activitycategoryname",
								eElement));
					}
					if (getTagValue("activityimagename", eElement).compareTo(
							"null") != 0) {
						newTest.SetParams(UrlAddressIcon
								+ getTagValue("activityimagename", eElement));
					}
					if (newTest.GetID() > 0) {
						_list.add(newTest);
					}
				}
			}
		} catch (Exception e) {
			String ff = "Error";
		}

		return _list;
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

	public static int NeedUpdate(Context ctx) {
		int ret = 0;

		DBAdapter db = new DBAdapter(ctx);
		db.OpenForRead();
		Cursor c = db.GetUpdateDB();
		if (c.moveToFirst()) {
			do {
				ret = c.getInt(c.getColumnIndex("UtilityValue"));
			} while (c.moveToNext());
		}
		db.Close();

		return ret;
	}
	
	public static void SetUpdate(Context ctx, int versionID) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		boolean reply;
		reply = db.UpdateDBVersion(String.valueOf(versionID));
		db.Close();
	}
}
