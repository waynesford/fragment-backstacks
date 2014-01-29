package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TableLayout.LayoutParams;

/**
 * This is the view hierarchy fragment manager, which will manage each of our fragment stacks for each of the navItems (tabs). 
 * A fragment with nested child fragments in it. 
 * @author Wayne
 *
 */
public class ParentFragment extends Fragment{

	private static final String TAG = ParentFragment.class.getSimpleName(); 
	
	private static final int LAYOUT_ID = 1000; 
	/**
	 * Used to name the view hierarchy, which we'll eventually pass on to the child fragments to name them. 
	 */
	private static final String HIERARCHY_NAME = "view_hierarchy_name";

	/**
	 * Wrapper for {@link #setArguments(Bundle)} so we can pass in a name for each instance of {@link ParentFragment}
	 * @param name
	 */
	public void setArguments(String name) 
	{
		Bundle bundle = new Bundle();
		bundle.putString(HIERARCHY_NAME, name);
		setArguments(bundle);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		//making the frame layout to hold the views of this view hierarchy
		FrameLayout layout = new FrameLayout(getActivity());
		layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout.setId(LAYOUT_ID);
		return layout; 
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) 
	{
		super.onActivityCreated(savedInstanceState);

		//this check is essential for individual backstacks to work
		//if this is the very first time this parentFragment is displaying, we attach the first fragment of the hierarchy to it
		if(getChildFragmentManager().getBackStackEntryCount()==0) {
			//grab the letter of this parentFragment (which was got from the tab) and make a new fragment and replace it on the view
			String tab_letter = getArguments().getString(HIERARCHY_NAME);
			MyFragment fragment = new MyFragment();
			fragment.setArguments(tab_letter, 1); //setting it to one because it will be the first fragment
			replace(fragment);
		}
	}

	public void replace(MyFragment fragment)
	{
		FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
		fragmentTransaction.replace(LAYOUT_ID, fragment, fragment.getName());
		fragmentTransaction.addToBackStack(fragment.getName());
		fragmentTransaction.commit();
		getChildFragmentManager().executePendingTransactions();
	}


}
