package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

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
	
	
	public static class MyFragment extends Fragment
	{
		
		private static final String LABEL = "label_key";
		private static final String COUNTER = "counter_key"; 

		
		private void setArguments(String label, int counter) 
		{
			Bundle bundle = new Bundle();
			bundle.putInt(COUNTER, counter);
			bundle.putString(LABEL, label);
			setArguments(bundle);
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
		{
			String label 	= getArguments().getString(LABEL);
			int counter 	= getArguments().getInt(COUNTER);

			
			View v = inflater.inflate(R.layout.screen, container, false);
			
			TextView tv = (TextView)v.findViewById(R.id.textView1);
			tv.setText(label + counter);
			Button button = (Button)v.findViewById(R.id.button1);
			counter++;
			button.setText("Go to " + label + counter);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					Toast.makeText(getActivity(), "button hit", Toast.LENGTH_SHORT).show();
				}
			});

			
			
			return v;
		}
	}

}
