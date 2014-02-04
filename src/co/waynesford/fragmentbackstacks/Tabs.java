package co.waynesford.fragmentbackstacks;

import android.support.v4.app.Fragment;
import co.waynesford.fragmentbackstacks.NavItem.Commandable;

public class Tabs {
	
	//unsightly anonymous classes, but easier to maintain when all things tab are objectified here
	private static Commandable tabAFirstFragment = new Commandable() {
		@Override
		public Fragment getFragment() {
			MyFragment fragment = new MyFragment();
			fragment.setArguments(A.getLabel(), 1); //setting the name of the first fragment
			return fragment;
		}
	};
	
	private static Commandable tabBFirstFragment = new Commandable() {
		@Override
		public Fragment getFragment() {
			MyFragment fragment = new MyFragment();
			fragment.setArguments(B.getLabel(), 1); 
			return fragment;
		}
	};
	
	private static Commandable tabCFirstFragment = new Commandable() {
		@Override
		public Fragment getFragment() {
			MyFragment fragment = new MyFragment();
			fragment.setArguments(C.getLabel(), 1); 
			return fragment;
		}
	};
	
	public static NavItem A = new NavItem(R.id.tabA, "A", tabAFirstFragment);
	public static NavItem B = new NavItem(R.id.tabB, "B", tabBFirstFragment);
	public static NavItem C = new NavItem(R.id.tabC, "C", tabCFirstFragment);


}
