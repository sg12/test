package internet.test;

import internet.test.ImageAdapter.ViewCache;
import internet.test.ImageManager.ImageCallback;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageSimpleAdapter extends BaseAdapter {
	private Context mContext;
	private ActivityCategoryList list;
	GridView _grid;

	public ImageSimpleAdapter(Context c, ActivityCategoryList l,
			boolean isNeedAll, GridView grid) {
		mContext = c;
		list = l;
		_grid = grid;
	}

	public int getCount() {
		return list.GetCount();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ImageView imageView;
		LinearLayout ln = new LinearLayout(mContext);
		// ln.setOrientation(1);
		ln.setBackgroundColor(Color.LTGRAY);
		ln.getBackground().setAlpha(70);
		LinearLayout.LayoutParams ap = new LinearLayout.LayoutParams(
				new ViewGroup.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));
		ln.setLayoutParams(new GridView.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.WRAP_CONTENT));
		ln.setGravity(0x10);

		LinearLayout.LayoutParams apIm = new LinearLayout.LayoutParams(
				new ViewGroup.LayoutParams(32, 32));
		imageView = new ImageView(mContext);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		ap.setMargins(3, 3, 0, 3);
		// imageView.setPadding(0, 8, 0, 0);
		imageView.setLayoutParams(apIm);

		LinearLayout.LayoutParams ap1 = new LinearLayout.LayoutParams(
				new ViewGroup.LayoutParams(
						LinearLayout.LayoutParams.WRAP_CONTENT,
						LinearLayout.LayoutParams.WRAP_CONTENT));

		TextView txt = new TextView(mContext);
		ap1.setMargins(5, 0, 3, 0);
		txt.setLayoutParams(ap1);
		txt.setTextColor(Color.WHITE);
		txt.setTextSize(15);

		ActivityCategory _cat = list.get(position);
		imageView.setImageResource(mContext.getResources().getIdentifier(
				_cat.GetActivityImageName(), "drawable", "internet.test"));

		txt.setText(_cat.GetActivityCategoryName());

		ln.setTag(Integer.toString(_cat.GetActivityCategoryID()));

		ln.addView(imageView);

		ln.addView(txt);
		return ln;
	}

}
