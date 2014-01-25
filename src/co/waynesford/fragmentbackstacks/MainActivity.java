package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
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

	}
	
	@Override
	protected void onStart() 
	{
		super.onStart();
		
		MyFragment fragment = new MyFragment();
		fragment.setArguments("Stack A:", 1);
		add(fragment);
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
		case R.id.tabA: case R.id.tabB: case  R.id.tabC:
			String tab_letter = ((TextView)v).getText().toString();
			MyFragment fragment = new MyFragment();
			fragment.setArguments(tab_letter, 1);
			replace(fragment);
			break; 
		}
		
	}
	
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
