package com.geniuseoe2012.popwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;

import com.hss01248.dialog.R;

import java.util.List;

public abstract class AbstractChoicePopWindow {

	protected Context mContext;
	protected View mParentView;

	protected ScrollView mScrollView;
	protected TextView mTVTitle;
	protected Button mButtonOK;
	protected Button mButtonCancel;
	protected ListView mListView;
	
	
	protected PopupWindow mPopupWindow;
	protected List<String> mList;
	
	private OnClickListener mOkListener;

	public AbstractChoicePopWindow(Context context,View parentView, List<String> list)
	{
		mContext = context;
		mParentView = parentView;
		mList = list;
	
		initView(mContext);
	}
	
	protected void initView(Context context)
	{
		View view = LayoutInflater.from(context).inflate(R.layout.popwindow_listview_layout, null);
		mScrollView = (ScrollView) view.findViewById(R.id.scrollView);
		mTVTitle = (TextView) view.findViewById(R.id.tvTitle);
		mButtonOK = (Button) view.findViewById(R.id.btnOK);
		mButtonOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onButtonOK(v);
			}
		});
		mButtonCancel = (Button) view.findViewById(R.id.btnCancel);
		mButtonCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onButtonCancel(v);
			}
		});
		
		mPopupWindow = new PopupWindow(view, LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
    	mPopupWindow.setFocusable(true);
    	ColorDrawable dw = new ColorDrawable(0x00);
		mPopupWindow.setBackgroundDrawable(dw);
		
		mListView = (ListView) view.findViewById(R.id.listView);   	
	
	}
	

	
	public void setOnOKButtonListener(OnClickListener onClickListener) {
		mOkListener = onClickListener;
	}
	
	public void setTitle(String title)
	{
		mTVTitle.setText(title);
	}
	

	public void show(boolean bShow)
	{

		if (bShow)
		{		
			mScrollView.scrollTo(0, 0);
			mPopupWindow.showAtLocation(mParentView, Gravity.TOP, 0, 0);
		}else{
			mPopupWindow.dismiss();
		}
	}

	protected void onButtonOK(View v)
	{
		show(false);
		
		if (mOkListener != null)
		{
			mOkListener.onClick(v);
		}
	}
	
	protected void onButtonCancel(View v)
	{
		show(false);

	}

}
