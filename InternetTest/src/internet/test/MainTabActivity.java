package internet.test;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class MainTabActivity extends TabActivity {

	public boolean _isNeedUpdateMonth = false;
	public boolean _isNeedUpdateWeek = false;
	public boolean _isNeedUpdateDay = false;
	public boolean _isNeedUpdateReport = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_tab);

//		ActivityCategoryList.RefreshDB(this);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, TestThree.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		// spec =
		// tabHost.newTabSpec("One").setIndicator(this.getApplicationContext().getString(R.string.month),
		// res.getDrawable(R.drawable.month))
		spec = tabHost.newTabSpec("One")
				.setIndicator("One", res.getDrawable(R.drawable.smile_1))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the otheic_tab_artists.xmlr tabs
		// intent = new Intent().setClass(this, TestOne.class);
		// spec = tabHost.newTabSpec("Two").setIndicator("Two",
		// res.getDrawable(R.drawable.smile_1))
		// .setContent(intent);
		// tabHost.addTab(spec);

		intent = new Intent().setClass(this, TestTwo.class);
		spec = tabHost.newTabSpec("Three")
				.setIndicator("Three", res.getDrawable(R.drawable.smile_1))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, TestGrid.class);
		spec = tabHost.newTabSpec("Four")
				.setIndicator("Four", res.getDrawable(R.drawable.smile_1))
				.setContent(intent);
		tabHost.addTab(spec);

		tabHost.setCurrentTab(0);
	}

}
