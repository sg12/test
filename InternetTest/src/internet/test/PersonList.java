package internet.test;

import java.util.ArrayList;
import android.content.Context;
import android.database.Cursor;

public class PersonList extends ArrayList<PersonInfo> {

	public static PersonList GetPersonList(Context ctx) {
		PersonList _list = new PersonList();
		DBAdapter db = new DBAdapter(ctx);
		db.OpenForRead();
		Cursor c = db.GetPersonList();
		if (c.moveToFirst()) {
			do {
				_list.add(FillPerson(c, ctx));
			} while (c.moveToNext());
		}
		db.Close();
		return _list;
	}

	public static PersonInfo GetPersonID(Context ctx, int id) {
		PersonInfo person = new PersonInfo();
		DBAdapter db = new DBAdapter(ctx);
		db.OpenForRead();
		Cursor c = db.GetPerson(id);
		if (c.moveToFirst()) {
			do {
				person = FillPerson(c, ctx);
			} while (c.moveToNext());
		}
		db.Close();
		return person;
	}

	public static void InsertPerson(Context ctx, PersonInfo person) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		db.InsertPerson(person.GetPersonFirstName(),
				person.GetPersonLastName(), person.GetPersonDateBirth(),
				person.GetPersonAddress());
		db.Close();
	}

	public static void DeletePerson(Context ctx, int docID) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		db.DeletePerson(docID);
		db.Close();
	}

	public static void UpdatePerson(Context ctx, PersonInfo person) {
		DBAdapter db = new DBAdapter(ctx);
		db.Open();
		db.UpdatePerson(person.GetPersonID(),
				person.GetPersonFirstName(), person.GetPersonLastName(),
				person.GetPersonDateBirth(), person.GetPersonAddress());
		db.Close();
	}

	private static PersonInfo FillPerson(Cursor c, Context ctx) {
		PersonInfo person = new PersonInfo();
		person.SetPersonID(c.getInt(c.getColumnIndex("PersonID")));
		person.SetPersonFirstName(c.getString(c.getColumnIndex("PersonFirstName")));
		person.SetPersonLastName(c.getString(c.getColumnIndex("PersonLastName")));
		person.SetPersonDateBirth(c.getString(c.getColumnIndex("PersonDateBirth")));
		person.SetPersonAddress(c.getString(c.getColumnIndex("PersonAddress")));
		return person;
	}

}
