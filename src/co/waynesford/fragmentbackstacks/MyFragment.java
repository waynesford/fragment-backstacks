package co.waynesford.fragmentbackstacks;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MyFragment extends Fragment
{
	
	private static final String LABEL = "label_key";
	private static final String COUNTER = "counter_key"; 

	
	public void setArguments(String label, int counter) 
	{
		Bundle bundle = new Bundle();
		bundle.putInt(COUNTER, counter);
		bundle.putString(LABEL, label);
		setArguments(bundle);
	}
	
	public String getName()
	{
		String label 	= getArguments().getString(LABEL);
		int counter 	= getArguments().getInt(COUNTER);
		return label + counter; 
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		//grabbing label and screen number passed in so we can display on view
		final String label 	= getArguments().getString(LABEL);
		final int counter 	= getArguments().getInt(COUNTER);
		//increment counter for next screen
		final int next = counter+1; 
		
		View layout = inflater.inflate(R.layout.screen, container, false);
		
		TextView tv = (TextView)layout.findViewById(R.id.textView1);
		tv.setText(label + counter);
		Button button = (Button)layout.findViewById(R.id.button1);
		button.setText("Go to " + label + next);
		button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MyFragment fragment = new MyFragment();
				fragment.setArguments(label, next);
				goToFragment(fragment); 
			}	
		});
		
		return layout;
	}
	
	private void goToFragment(MyFragment fragment)
	{
		( (ParentFragment) getParentFragment() ).replace(fragment);
	}
	
	
}
