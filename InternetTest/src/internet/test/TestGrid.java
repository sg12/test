package internet.test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

public class TestGrid extends Activity {
	/** Called when the activity is first created. */

	GridView gridview;
	TestList _list = new TestList();
	boolean _needAll = false;
	boolean _isFromSettings = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			if (extras.containsKey("NeedAll")) {
				_needAll = extras.getBoolean("NeedAll");
			}
			if (extras.containsKey("IsFromSettings")) {
				_isFromSettings = extras.getBoolean("IsFromSettings");
			}
		}

		gridview = (GridView) findViewById(R.id.gridview);

		gridview.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				int idTag = Integer.parseInt(v.getTag().toString());
				if (idTag > 0) {
					if (!_isFromSettings) {
//						Intent resultIntent = new Intent();
//						resultIntent.putExtra("ActivityCategoryID", v.getTag()
//								.toString());
//						setResult(RESULT_OK, resultIntent);
//						finish();
					} else {
						// Intent i = new Intent(TestGrid.this,
						// SelectCategoryImageDialog.class);
						// i.putExtra("CategoryID", idTag);
						// startActivityForResult(i, 16);
					}
				} else {
					if (_needAll) {
//						Intent resultIntent = new Intent();
//						resultIntent.putExtra("ActivityCategoryID", "0");
//						setResult(RESULT_OK, resultIntent);
//						finish();
					} else {
						if (_isFromSettings) {
							// Intent i = new Intent(TestGrid.this,
							// SelectCategoryImageDialog.class);
							// i.putExtra("CategoryID", idTag);
							// startActivityForResult(i, 16);
						} else {
							// Intent i = new Intent(TestGrid.this,
							// SelectCategoryImageDialog.class);
							// i.putExtra("CategoryID", idTag);
							// startActivityForResult(i, 16);
						}
					}
				}

			}
		});

		gridview.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				generateCsvFile(TestGrid.this,"Example1.csv"); 
				// int id = Integer.parseInt(arg1.getTag().toString());
				// EditDialog editDesc = new EditDialog(SelectListActivity.this,
				// id, new OnReadyEditListener());
				// editDesc.show();
				return false;
			}

		});

		fillList();

	}

	private void fillList() {
		if (_list.size() < 1) {
			_list = TestList.GetListByXML4();
		}
		gridview.setAdapter(null);
		gridview.setAdapter(new ImageAdapter(this, _list, _needAll, gridview));

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

	
	private static void generateCsvFile(Context ctx, String sFileName)
    {
        try
        {
            File root = Environment.getExternalStorageDirectory();
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);

            writer.append("ActivityCategoryID");
            writer.append(';');
            writer.append("ActivityCategoryName");
            writer.append(';');
            writer.append("GetActivityImageName");
            writer.append('\n');
            ActivityCategoryList _list = ActivityCategoryList.GetActivityCategoryList(ctx);
            for(ActivityCategory cat : _list){
            	writer.append(String.valueOf(cat.GetActivityCategoryID()));
                writer.append(';');
                writer.append(cat.GetActivityCategoryName());
                writer.append(';');
                writer.append(cat.GetActivityImageName());
                writer.append('\n');
            }

//            writer.append("Emp_Name");
//            writer.append(',');
//            writer.append("Adress");
//            writer.append('\n');

//            writer.append("hussain");
//            writer.append(',');
//            writer.append("Delhi");
//            writer.append('\n');

            //generate whatever data you want

            writer.flush();
            writer.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        } 
     }
}
