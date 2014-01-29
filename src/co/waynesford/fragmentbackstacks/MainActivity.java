package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements OnClickListener{

	private static final String TAG = MainActivity.class.getSimpleName(); 

	/**
	 * The current textview tab, so we know what to dehighlight when the next tab is pressed. 
	 */
	private View mCurrentTab;
	/**
	 * The current view hierarchy.
	 */
	private ParentFragment mCurrentParentFragment; 

	
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
	public void onClick(View v) {
		switch(v.getId()) {
		case R.id.tabA: case R.id.tabB: case R.id.tabC:

			//deselect the previously selected tab
			mCurrentTab.setSelected(false); 
			
			v.setSelected(true); //toggles on the selected color that we set in our selector drawable
			//grab the letter of the tab and make a new fragment and replace it on the view
			String tab_letter = ((TextView)v).getText().toString();
			ParentFragment fragment = new ParentFragment();
			fragment.setArguments(tab_letter);
			replace(fragment);

			//set tab just clicked as the new current tab
			mCurrentTab = v; 
			break; 
		}
	}


	private void replace (ParentFragment fragment)
	{
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		
		//if the fragment is already in the backstack, then retrieve it and show it instead
		Fragment fragmentInStack = getSupportFragmentManager().findFragmentByTag(fragment.getName());
		if(fragmentInStack!=null) {
			
			fragmentTransaction.replace(R.id.mainWindow, fragmentInStack, fragment.getName());
			mCurrentParentFragment = (ParentFragment)fragmentInStack;
			//don't add to backstack because it's already on the backstack and we want to maintain the state of it
		} else  {
			
			fragmentTransaction.replace(R.id.mainWindow, fragment, fragment.getName());
			fragmentTransaction.addToBackStack(null);
			mCurrentParentFragment = fragment;

		}
		fragmentTransaction.commit(); 
		getSupportFragmentManager().executePendingTransactions();
	}
	
	
	@Override
	public void onBackPressed() 
	{
		//if we are at the first screen, then we can't back out any further on this view stack/tab
		if(mCurrentParentFragment.getChildFragmentManager().getBackStackEntryCount()==1) {
			return; 
		}
		
		//pop off the topmost fragment in the current view hiearchy
		mCurrentParentFragment.getChildFragmentManager().popBackStackImmediate();
	}






}
