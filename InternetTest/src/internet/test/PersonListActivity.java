package internet.test;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonListActivity extends Activity {

	PersonList _list;
	LinearLayout container;
	boolean _click = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_list);
		container = (LinearLayout) findViewById(R.id.select_person_list);
		Button btnNew = (Button) findViewById(R.id.new_button);
		btnNew.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(PersonListActivity.this,
						PersonEditActivity.class);
				i.putExtra("PersonID", 0);
				startActivityForResult(i, 1);
			}
		});

		fillPersonList();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (1): {
			if (resultCode == Activity.RESULT_OK) {
				fillPersonList();
			}
			break;
		}
		}
	}

	void fillPersonList() {
		container.removeAllViews();
		_list = PersonList.GetPersonList(this);

		for (PersonInfo per : _list) {
			LayoutInflater inflater = (LayoutInflater) this
					.getApplicationContext().getSystemService(
							Context.LAYOUT_INFLATER_SERVICE);
			if (inflater != null) {
				View v = inflater.inflate(R.layout.person_view, container);

				LinearLayout lnMain = (LinearLayout) container
						.getChildAt(container.getChildCount() - 1);
				lnMain.setTag(per.GetPersonID());

				TextView txtPersonName = (TextView) lnMain.getChildAt(0);
				TextView txtPersonDateBirth = (TextView) lnMain.getChildAt(1);
				TextView txtPersonAddress = (TextView) lnMain.getChildAt(2);

				txtPersonName.setText(per.GetPersonFirstName() + " "
						+ per.GetPersonLastName());
				txtPersonDateBirth.setText(per.GetPersonDateBirth());
				txtPersonAddress.setText(per.GetPersonAddress());

				lnMain.setOnTouchListener(new View.OnTouchListener() {
					public boolean onTouch(View v, MotionEvent event) {
						LinearLayout ln;
						TextView txt;
						switch (event.getAction()) {
						case MotionEvent.ACTION_DOWN:
							v.setBackgroundColor(Color.YELLOW);
							_click = true;
							break;
						case MotionEvent.ACTION_CANCEL:
							v.setBackgroundColor(Color.BLACK);
							_click = false;
							break;
						case MotionEvent.ACTION_UP:
							v.setBackgroundColor(Color.BLACK);
							if (!_click) {
								break;
							}
							if (v.getTag() != null) {
								int perID = (Integer) v.getTag();
								Intent i = new Intent(PersonListActivity.this,
										PersonEditActivity.class);
								i.putExtra("PersonID", perID);
								startActivityForResult(i, 1);
							}
							break;
						default:
							break;
						}
						return true;
					}
				});
			}
		}

	}

}
