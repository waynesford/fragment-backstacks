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
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		String label 	= getArguments().getString(LABEL);
		int counter 	= getArguments().getInt(COUNTER);

		
		View v = inflater.inflate(R.layout.screen, container, false);
		
		TextView tv = (TextView)v.findViewById(R.id.textView1);
		tv.setText(label + counter);
		
		//increment counter for next screen
		counter++;
		Button button = (Button)v.findViewById(R.id.button1);
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
