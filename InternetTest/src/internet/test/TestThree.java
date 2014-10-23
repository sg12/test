package internet.test;

import java.io.IOException;
import java.net.MalformedURLException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

public class TestThree extends Activity {
	
	GridView gridview;
	ActivityCategoryList _list = new ActivityCategoryList();
	
	boolean _needAll = false;
	boolean _isFromSettings = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_async);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.containsKey("NeedAll")) {
				_needAll = extras.getBoolean("NeedAll");
			}
			if (extras.containsKey("IsFromSettings")) {
				_isFromSettings = extras.getBoolean("IsFromSettings");
			}
		}

		gridview = (GridView) findViewById(R.id.gridview_async);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				int idTag = Integer.parseInt(v.getTag().toString());
				if (idTag > 0) {
					if (!_isFromSettings) {
						Intent resultIntent = new Intent();
						resultIntent.putExtra("ActivityCategoryID", v.getTag()
								.toString());
						setResult(RESULT_OK, resultIntent);
						finish();
					} else {
//						Intent i = new Intent(TestGrid.this,
//								SelectCategoryImageDialog.class);
//						i.putExtra("CategoryID", idTag);
//						startActivityForResult(i, 16);
					}
				} else {
					if (_needAll) {
//						Intent resultIntent = new Intent();
//						resultIntent.putExtra("ActivityCategoryID", "0");
//						setResult(RESULT_OK, resultIntent);
//						finish();
					} else {
						if (_isFromSettings) {
//							Intent i = new Intent(TestGrid.this,
//									SelectCategoryImageDialog.class);
//							i.putExtra("CategoryID", idTag);
//							startActivityForResult(i, 16);
						} else {
//							Intent i = new Intent(TestGrid.this,
//									SelectCategoryImageDialog.class);
//							i.putExtra("CategoryID", idTag);
//							startActivityForResult(i, 16);
						}
					}
				}

			}
		});

		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				Utility.SetUpdate(TestThree.this, 0);
//				int id = Integer.parseInt(arg1.getTag().toString());
//				EditDialog editDesc = new EditDialog(SelectListActivity.this,
//						id, new OnReadyEditListener());
//				editDesc.show();
				return false;
			}

		});

		if(Utility.NeedUpdate(this)==0){
			new AsyncTaskDemo().execute();
			Utility.SetUpdate(this, 1);
		}else{
			fillList();
			ProgressBar progress = (ProgressBar) findViewById(R.id.progress);
			progress.setVisibility(View.INVISIBLE);
		}
		
		
		
		

	}

	private void fillList() {
		_list = ActivityCategoryList.GetActivityCategoryList(this);
		gridview = (GridView) findViewById(R.id.gridview_async);
//		this.gridview.setAdapter(null);
		this.gridview.setAdapter(new ImageSimpleAdapter(this, _list, _needAll, gridview));

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (16): {
			if (resultCode == Activity.RESULT_OK) {
				fillList();
			}
			break;
		}
		}
	}

	private class AsyncTaskDemo extends AsyncTask<Void, Integer, Void> {
		 
        ProgressBar progress;
 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.progress = (ProgressBar) findViewById(R.id.progress);
        }
 
        @Override
        protected Void doInBackground(Void... params) {
 
        	ActivityCategoryList.RefreshDB(TestThree.this);
//            for (int i = 1; i <= 100; i++) {
//                  SystemClock.sleep(100);
//                              publishProgress(i);
// 
//            }
//        	fillList();
 
            return null;
        }
 
        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            this.progress.setProgress(progress[0]);
        }
 
                @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            fillList();
//            Toast.makeText(TestThree.this, "Finished!", Toast.LENGTH_LONG).show();
            this.progress.setVisibility(View.INVISIBLE);
            
        }
 
    }
}
