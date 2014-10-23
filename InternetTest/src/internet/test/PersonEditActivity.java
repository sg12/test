package internet.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PersonEditActivity extends Activity {

	private PersonInfo person = new PersonInfo();

	EditText txtFirstName;
	EditText txtLastName;
	EditText txtDate;
	EditText txtAddress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.person_edit);

		txtFirstName = (EditText) findViewById(R.id.editText0);
		txtLastName = (EditText) findViewById(R.id.editText1);
		txtDate = (EditText) findViewById(R.id.editText2);
		txtAddress = (EditText) findViewById(R.id.editText3);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			int id = extras.getInt("PersonID", 0);
			if (id > 0) {
				person = PersonList.GetPersonID(this, id);
				txtFirstName.setText(person.GetPersonFirstName());
				txtLastName.setText(person.GetPersonLastName());
				txtDate.setText(person.GetPersonDateBirth());
				txtAddress.setText(person.GetPersonAddress());
			}
		}

		Button okB = (Button) findViewById(R.id.save_button);
		okB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				person.SetPersonFirstName(txtFirstName.getText().toString());
				person.SetPersonLastName(txtLastName.getText().toString());
				person.SetPersonDateBirth(txtDate.getText().toString());
				person.SetPersonAddress(txtAddress.getText().toString());
				if (person.GetPersonID() > 0) {
					PersonList.UpdatePerson(v.getContext(), person);
				} else {
					PersonList.InsertPerson(v.getContext(), person);
				}
				Intent resultIntent = new Intent();
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});

		Button cancelB = (Button) findViewById(R.id.cancel_button);
		cancelB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				finish();
			}
		});
		
		Button removeB = (Button) findViewById(R.id.remove_button);
		removeB.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				PersonList.DeletePerson(v.getContext(), person.GetPersonID());
				Intent resultIntent = new Intent();
				setResult(RESULT_OK, resultIntent);
				finish();
			}
		});

	}
}
