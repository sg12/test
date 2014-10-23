package internet.test;

import internet.test.ImageManager.ImageCallback;

import java.lang.ref.SoftReference;
import java.util.HashMap;

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

public class ImageAdapter extends BaseAdapter {
	private Context mContext;
	private TestList list;
	private boolean needAll = false;
	ImageManager imgManager;
	GridView _grid;

	public ImageAdapter(Context c, TestList l, boolean isNeedAll, GridView grid) {
		mContext = c;
		list = l;
		needAll = isNeedAll;
		imgManager = new ImageManager();
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
		Activity activity = (Activity) mContext;
		View rowView = convertView;
		ViewCache viewCache;
		if (rowView == null) {
			LayoutInflater inflater = activity.getLayoutInflater();
			rowView = inflater.inflate(R.layout.image_and_text_row, null);
			viewCache = new ViewCache(rowView);
			rowView.setTag(viewCache);
		} else {
			viewCache = (ViewCache) rowView.getTag();
		}
		Test _t = list.get(position);
		// Load the image and set it on the ImageView
		ImageView imageView = viewCache.getImageView();
		imageView.setTag(_t.GetParams());
		// Set the text on the TextView
		// TextView textView = (TextView) rowView.findViewById(R.id.text);
		TextView textView = viewCache.getTextView();

		// ImageManager.fetchImage(_t.GetParams(),imageView);
		// imageView.setImageURI(Uri.parse(_t.GetParams()));

		Drawable cachedImage = imgManager.loadDrawable(_t.GetParams(),
				new ImageCallback() {
					public void imageLoaded(Drawable imageDrawable,
							String imageUrl) {
						// imageView.setImageDrawable(imageDrawable);
						ImageView imageViewByTag = (ImageView) _grid
								.findViewWithTag(imageUrl);
						if (imageViewByTag != null) {
							if (imageDrawable != null) {
								imageViewByTag.setImageDrawable(imageDrawable);
							} else {
//								imageViewByTag.setImageResource(mContext.getResources().getIdentifier("smile_1", "drawable", "internet.test"));
							}
						}
					}
				});
		imageView.setImageDrawable(cachedImage);

		textView.setText(_t.GetName());

		textView.setTag(Integer.toString(_t.GetID()));
		return rowView;
	}

	public class ViewCache {

		private View baseView;
		private TextView textView;
		private ImageView imageView;

		public ViewCache(View baseView) {
			this.baseView = baseView;
		}

		public TextView getTextView() {
			if (textView == null) {
				textView = (TextView) baseView.findViewById(R.id.text);
			}
			return textView;
		}

		public ImageView getImageView() {
			if (imageView == null) {
				imageView = (ImageView) baseView.findViewById(R.id.image);
			}
			return imageView;
		}
	}
}
