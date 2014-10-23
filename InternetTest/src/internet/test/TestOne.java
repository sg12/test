package internet.test;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TestOne extends Activity {
	
	LinearLayout _layout;
	TestList _list;
	LinearLayout.LayoutParams _layParams = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.FILL_PARENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
	LinearLayout.LayoutParams _layParams1 = new LinearLayout.LayoutParams(
			LinearLayout.LayoutParams.WRAP_CONTENT,
			LinearLayout.LayoutParams.WRAP_CONTENT);
	LinearLayout.LayoutParams _layParams2 = new LinearLayout.LayoutParams(32,
			32);

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		_layout = (LinearLayout) findViewById(R.id.mainlayout);

		fill();
	}

	void fill() {
		_layout.removeAllViews();
		// _list = TestList.GetListByJSON(2012, 5, 12);
		_list = TestList.GetListByXML();
		for (Test _t : _list) {
			LinearLayout _lay = new LinearLayout(this);
			_lay.setLayoutParams(_layParams);
			_lay.setOrientation(0);
			
			TextView txt = new TextView(this);
			txt.setLayoutParams(_layParams1);
			txt.setTextSize(14);
			txt.setTextColor(Color.WHITE);
			txt.setText(String.valueOf(_t.GetID()) + " - " + _t.GetName());
			_lay.addView(txt);
			ImageView img = new ImageView(this);
			img.setLayoutParams(_layParams1);
			Uri _u = Uri.parse(_t.GetParams());
			try {
				img.setImageDrawable(drawable_from_url(_t.GetParams(),"aaa"));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			_lay.addView(img);
			_layout.addView(_lay);
		}
	}
	
	Drawable drawable_from_url(String url, String src_name) throws 
	   java.net.MalformedURLException, java.io.IOException 
	{
	   return Drawable.createFromStream(((java.io.InputStream)
	      new java.net.URL(url).getContent()), src_name);
	}
}
