package com.geniuseoe2012.dialog;

import android.content.Context;

import com.geniuseoe2012.demo.SingleChoicAdapter;
import com.geniuseoe2012.demo.Utils;
import com.hss01248.dialog.R;

import java.util.List;

public class SingleChoiceDialog extends AbstractChoickDialog{

	private SingleChoicAdapter<String> mSingleChoicAdapter;

	public SingleChoiceDialog(Context context, List<String> list) {
		super(context, list);
		
		initData();
	}

	protected void initData() {
		// TODO Auto-generated method stub
		mSingleChoicAdapter = new SingleChoicAdapter<String>(mContext, mList, R.drawable.selector_checkbox2);
		
		mListView.setAdapter(mSingleChoicAdapter);
		mListView.setOnItemClickListener(mSingleChoicAdapter);   
		
		Utils.setListViewHeightBasedOnChildren(mListView);
	
	}


	public int getSelectItem()
	{
		return mSingleChoicAdapter.getSelectItem();
	}


}
