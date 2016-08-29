package com.geniuseoe2012.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.hss01248.dialog.R;

import java.util.ArrayList;
import java.util.List;

public class MultiChoicAdapter<T> extends BaseAdapter  implements OnItemClickListener{

	 private Context mContext;   
	    private List<T> mObjects = new ArrayList<T>();
	    private int mCheckBoxResourceID = 0;
	    private boolean mBoolean[] = null;
	    
	    private LayoutInflater mInflater;

	  
	    public MultiChoicAdapter(Context context, int checkBoxResourceId) {
	        init(context, checkBoxResourceId);
	    }
	    
	    public MultiChoicAdapter(Context context,  List<T> objects, boolean[] flag, int checkBoxResourceId) {
	        init(context, checkBoxResourceId);
	        if (objects != null)
	    	{
	        	mObjects = objects;
	        	mBoolean = flag;
	    	}

	    }

	    private void init(Context context, int checkBoResourceId) {
	        mContext = context;
	        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        mCheckBoxResourceID = checkBoResourceId;
	    }
	    
	    public void refreshData(List<T> objects,  boolean[] flag)
	    {
	    	if (objects != null)
	    	{
	    		mObjects = objects;
	    		mBoolean = flag;
	    	}
	    }
	    
	  
	    public void setSelectItem( boolean[] flag)
	    {
	    	if (flag != null)
	    	{
	    		mBoolean = flag;
	    		notifyDataSetChanged();
	    	}
	    
	    }
	    

	    public boolean[] getSelectItem()
	    {
	    	return mBoolean;
	    }

	    public void clear() {
	         mObjects.clear();
	         notifyDataSetChanged();
	    }

	    
	    public int getCount() {
	        return mObjects.size();
	    }

	    public T getItem(int position) {
	        return mObjects.get(position);
	    }

	    public int getPosition(T item) {
	        return mObjects.indexOf(item);
	    }


	    public long getItemId(int position) {
	        return position;
	    }


	    public View getView(int position, View convertView, ViewGroup parent) {   
		
	    	 ViewHolder viewHolder;
	    	 
		     if (convertView == null) {
		    	 convertView = mInflater.inflate(R.layout.choice_list_item_layout, null);
		         viewHolder = new ViewHolder();
		         viewHolder.mTextView = (TextView) convertView.findViewById(R.id.textView);
		         viewHolder.mCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox);
		         convertView.setTag(viewHolder);
		         
		         if (mCheckBoxResourceID != 0)
		         {
		        	 viewHolder.mCheckBox.setButtonDrawable(mCheckBoxResourceID);
		         }

		     } else {
		         viewHolder = (ViewHolder) convertView.getTag();
		     }
		   
		     viewHolder.mCheckBox.setChecked(mBoolean[position]);
		   
		     	 
		     T item = getItem(position);
			 if (item instanceof CharSequence) {
			        viewHolder.mTextView.setText((CharSequence)item);
			 } else {
				 viewHolder.mTextView.setText(item.toString());
			 }
		
		     return convertView;
	    }

	    public static class ViewHolder
	    {
	    	public TextView mTextView;
	    	public CheckBox mCheckBox;
	    }

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub

			mBoolean[position] = !mBoolean[position];
			notifyDataSetChanged();
		} 
		
}
