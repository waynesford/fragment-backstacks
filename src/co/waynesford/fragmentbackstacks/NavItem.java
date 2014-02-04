package co.waynesford.fragmentbackstacks;

import android.support.v4.app.Fragment;

/**
 * Here, we couple the title of the navitem, the navitem's view (a tab, or a list item), and the first fragment of our navitem. 
 * @author wayne
 *
 */
public class NavItem {
	
	private int mViewResourceId;
	private String mLabel;
	private Commandable mFirstFragment;  
	
	public int getViewId() 				{ return mViewResourceId; }
	public String getLabel() 			{ return mLabel; }
	public Fragment getFirstFragment() 	{ return mFirstFragment.getFragment(); }

	
	//command pattern in order to encapsulate the fragment's constructor, so a new instance can be retrieved at runtime. 
	public interface Commandable
	{
		public Fragment getFragment(); 
	}
	
	/**
	 * 
	 * @param viewResourceId The view id for the tab/navitem. 
	 * @param label The label name, use to tag the parentfragments as we attach them onto the backstack
	 * @param firstFragment The first fragment for the heirarchy, so we know what to attach first.
	 */
	public NavItem(int viewResourceId, String label, Commandable firstFragment) 
	{
		mViewResourceId = viewResourceId;
		mLabel = label;
		mFirstFragment = firstFragment; 
	}
	
	
	
	
}
