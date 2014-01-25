package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{


	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//setting click listeners for the tab
		findViewById(R.id.tabA).setOnClickListener(this);
		findViewById(R.id.tabB).setOnClickListener(this);
		findViewById(R.id.tabC).setOnClickListener(this);

		//calling click on tab a so that it's the first tab that the user see
		mCurrentTab = findViewById(R.id.tabA);
		onClick(mCurrentTab);
	}

	@Override
	protected void onStart() 
	{
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.tabA: case R.id.tabB: case R.id.tabC:

			//deselect the previously selected tab
			mCurrentTab.setSelected(false); 
			v.setSelected(true); //toggles on the selected color that we set in our selector drawable
			//grab the letter of the tab and make a new fragment and replace it on the view
			String tab_letter = ((TextView)v).getText().toString();
			MyFragment fragment = new MyFragment();
			fragment.setArguments(tab_letter, 1);
			replace(fragment);

			//set tab just clicked as the new current tab
			mCurrentTab = v; 
			break; 
		}
	}

	View mCurrentTab; 


	private void add(Fragment fragment)
	{
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.add(R.id.mainWindow, fragment);
		fragmentTransaction.commit();
	}

	private void replace (Fragment fragment)
	{
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		fragmentTransaction.replace(R.id.mainWindow, fragment);
		fragmentTransaction.commit(); 
	}






}
