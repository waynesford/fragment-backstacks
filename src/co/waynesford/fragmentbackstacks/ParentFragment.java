package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.TableLayout.LayoutParams;

/**
 * This will manage each of our stacks for each of the tabs.
 * @author Wayne
 *
 */
public class ParentFragment extends Fragment{

	private static final String TAG = ParentFragment.class.getSimpleName(); 

	private static final String LABEL = "label_key";
	
	private static final int LAYOUT_ID = 1000; 
	private MyFragment mCurrentFragment; 
	
	public void setArguments(String label) 
	{
		Bundle bundle = new Bundle();
		bundle.putString(LABEL, label);
		setArguments(bundle);
	}
	
	public String getName()
	{
		return getArguments().getString(LABEL);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		//making the frame layout to hold the view
		FrameLayout layout = new FrameLayout(getActivity());
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.setId(LAYOUT_ID);
		
		return layout; 
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		//this check is essential for individual backstacks to work
		//if this is the very first time this parentFragment is displaying, only then do we attach view 1
		if(getChildFragmentManager().getBackStackEntryCount()==0) {
			//grab the letter of this parentFragment (which was got from the tab) and make a new fragment and replace it on the view
			String tab_letter = getArguments().getString(LABEL);
			MyFragment fragment = new MyFragment();
			fragment.setArguments(tab_letter, 1); //setting it to one because it will be the first fragment
			replace(fragment);
			//set tab just clicked as the new current tab
			mCurrentFragment = fragment; 
		}
	}
	
	public void replace(MyFragment fragment)
	{
		FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
		
		//if the fragment is already in the backstack, then retrieve it and show it instead
//		Fragment fragmentInStack = getChildFragmentManager().findFragmentByTag(fragment.getName());
//		if(fragmentInStack!=null) {
//			fragmentTransaction.replace(LAYOUT_ID, fragmentInStack, fragment.getName());
//			//don't add to backstack because it's already on the backstack and we want to maintain the state of it
//		} else  {
			fragmentTransaction.replace(LAYOUT_ID, fragment, fragment.getName());
			fragmentTransaction.addToBackStack(fragment.getName());
//		}
		fragmentTransaction.commit(); 
		getChildFragmentManager().executePendingTransactions();
		
		//set that fragment to the current fragment
		mCurrentFragment = fragment; 
		
		int count = getChildFragmentManager().getBackStackEntryCount();
		
		Log.d(TAG, "fragment just added:" + fragment.getName());
		Log.d(TAG, "parentfragment's child backstack count: " + count);
	}
	
	
}
