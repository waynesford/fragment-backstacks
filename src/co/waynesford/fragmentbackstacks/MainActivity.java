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
			String tabLetter = ((TextView)v).getText().toString();
			replace(tabLetter);

			//set tab just clicked as the new current tab
			mCurrentTab = v; 
			break; 
		}
	}

	/**
	 * This will take the name of the navitem, and use that as the tag for our parent fragment.
	 * @param navItemName
	 */
	private void replace (String navItemName)
	{
		FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
		//if the fragment is already in the backstack, then retrieve it and show it instead
		Fragment fragmentInStack = getSupportFragmentManager().findFragmentByTag(navItemName);
		if(fragmentInStack!=null) {
			
			fragmentTransaction.replace(R.id.mainWindow, fragmentInStack, navItemName);
			mCurrentParentFragment = (ParentFragment)fragmentInStack;
			//don't add to backstack because it's already on the backstack and we want to maintain the state of it
		} else  {
			
			//the fragment hasn't been added to the backstack, so now we can init it
			ParentFragment fragment = new ParentFragment();
			fragment.setArguments(navItemName);
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
