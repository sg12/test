package internet.test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

public class ImageManager {
	private final static String TAG = "ImageManager";
	private HashMap<String, SoftReference<Drawable>> imageCache;

	/** Private constructor prevents instantiation from other classes */
	public ImageManager() {
		imageCache = new HashMap<String, SoftReference<Drawable>>();
	}

	/*public static void fetchImage(final String iUrl, final ImageView iView) {
		if (iUrl == null || iView == null)
			return;

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message message) {
				final Bitmap image = (Bitmap) message.obj;
				iView.setImageBitmap(image);
			}
		};

		final Thread thread = new Thread() {
			@Override
			public void run() {
				final Bitmap image = downloadImage(iUrl);
				if (image != null) {
					Log.v(TAG, "Got image by URL: " + iUrl);
					final Message message = handler.obtainMessage(1, image);
					handler.sendMessage(message);
				}
			}
		};
		iView.setImageResource(R.drawable.smile_1);
		thread.setPriority(3);
		thread.start();
	}

	public static Bitmap downloadImage(String iUrl) {
		Bitmap bitmap = null;
		HttpURLConnection conn = null;
		BufferedInputStream buf_stream = null;
		try {
			Log.v(TAG, "Starting loading image by URL: " + iUrl);
			conn = (HttpURLConnection) new URL(iUrl).openConnection();
			conn.setDoInput(true);
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.connect();
			buf_stream = new BufferedInputStream(conn.getInputStream(), 8192);
			bitmap = BitmapFactory.decodeStream(buf_stream);
			buf_stream.close();
			conn.disconnect();
			buf_stream = null;
			conn = null;
		} catch (MalformedURLException ex) {
			Log.e(TAG, "Url parsing was failed: " + iUrl);
		} catch (IOException ex) {
			Log.d(TAG, iUrl + " does not exists");
		} catch (OutOfMemoryError e) {
			Log.w(TAG, "Out of memory!!!");
			return null;
		} finally {
			if (buf_stream != null)
				try {
					buf_stream.close();
				} catch (IOException ex) {
				}
			if (conn != null)
				conn.disconnect();
		}
		return bitmap;
	}*/

	public Drawable loadDrawable(final String imageUrl, final ImageCallback imageCallback) {
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            Drawable drawable = softReference.get();
            if (drawable != null) {
                return drawable;
            }
        }
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message message) {
                imageCallback.imageLoaded((Drawable) message.obj, imageUrl);
            }
        };
        new Thread() {
            @Override
            public void run() {
                Drawable drawable = loadImageFromUrl(imageUrl);
                imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                Message message = handler.obtainMessage(0, drawable);
                handler.sendMessage(message);
            }
        }.start();
        return null;
    }
	
	 public static Drawable loadImageFromUrl(String url) {
	        InputStream inputStream;
	        try {
	            inputStream = new URL(url).openStream();
	        } catch (IOException e) {
	        	return null;
	        }
	        return Drawable.createFromStream(inputStream, "src");
	    }

	 public interface ImageCallback {
	        public void imageLoaded(Drawable imageDrawable, String imageUrl);
	    }
}
