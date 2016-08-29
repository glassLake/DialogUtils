package com.geniuseoe2012.demo;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Utils {


        public static void setListViewHeightBasedOnChildren(ListView listView) { 
            ListAdapter listAdapter = listView.getAdapter(); 
            if (listAdapter == null ) { 
                    // pre-condition 
                    return; 
            } 
            
            Log.e("", "listAdapter.getCount() = " + listAdapter.getCount());
      
            int totalHeight = 0; 
            int tmp = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) { 
                    View listItem = listAdapter.getView(i, null, listView); 
                    listItem.measure(0, 0); 
                    totalHeight += listItem.getMeasuredHeight();   
                    tmp = listItem.getMeasuredHeight();
            } 
            totalHeight += 10;
            ViewGroup.LayoutParams params = listView.getLayoutParams(); 
            params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)); 
            listView.setLayoutParams(params); 
        } 
} 