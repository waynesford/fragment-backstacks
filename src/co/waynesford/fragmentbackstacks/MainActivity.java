package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity {

	private static final int LAYOUT_ID = 1000; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		FrameLayout layout = new FrameLayout(this); 
		layout.setId(LAYOUT_ID);
		setContentView(layout);
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
	
	private void add(Fragment fragment)
	{
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		fragmentTransaction.add(LAYOUT_ID, fragment);
		fragmentTransaction.commit();
	}
	
	


}
