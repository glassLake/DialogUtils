package com.hss01248.progressview;

import android.content.DialogInterface;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public interface MyDialogListener {
    void onFirst(DialogInterface dialog);
    void onSecond(DialogInterface dialog);
    void onThird(DialogInterface dialog);

    void onCancle();
}
