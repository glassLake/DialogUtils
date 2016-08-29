package com.hss01248.progressview;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4 0004.
 */
public class MyDialogUtils {



    public static Dialog showMdAlert(Activity activity, String title, String msg,
                                          String firstTxt, String secondTxt, String thirdTxt,
                                          boolean outsideCancleable, boolean cancleable,
                                          final MyDialogListener listener){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title)
                .setMessage(msg)
                .setCancelable(cancleable)
                .setPositiveButton(firstTxt, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       listener.onFirst(dialog);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton(secondTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onSecond(dialog);
                    dialog.dismiss();
                }
            }).setNeutralButton(thirdTxt, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    listener.onThird(dialog);
                    dialog.dismiss();
                }
            });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(outsideCancleable);
        dialog.show();
       return dialog;
    }

    public static Dialog showIosAlert(Context activity,String title, String msg,
                                           String firstTxt, String secondTxt,String thirdTxt,
                                           boolean outsideCancleable, boolean cancleable,
                                           final MyDialogListener listener){
        return showIosAlert(activity,false,title,msg,firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
    }

    public static Dialog showIosAlertVertical(Context activity,String title, String msg,
                                                   String firstTxt, String secondTxt,String thirdTxt,
                                                   boolean outsideCancleable, boolean cancleable,
                                                   final MyDialogListener listener){
        return showIosAlert(activity,false,title,msg,firstTxt,secondTxt,thirdTxt,outsideCancleable,cancleable,listener);
    }

    private static Dialog showIosAlert(Context context, boolean isButtonVerticle, String title, String msg,
                                           String firstTxt, String secondTxt, String thirdTxt,
                                           boolean outsideCancleable, boolean cancleable,
                                           final MyDialogListener listener){

        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);

         assigIosAlertView(context,dialog,isButtonVerticle,title,msg,firstTxt,secondTxt,thirdTxt,listener);

        setDialogStyle(context,dialog);

        dialog.show();
        return dialog;
    }

    public static Dialog showBottomItemDialog(Context context,
                                              List<String> words,String bottomTxt,
                                              boolean outsideCancleable, boolean cancleable,
                                              final MyItemDialogListener listener){
        Dialog dialog = buildDialog(context,cancleable,outsideCancleable);

        assignBottomListDialogView(context,dialog,words,bottomTxt,listener);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);
        setDialogStyle(context,dialog);

        dialog.show();
        return dialog;
    }

    private static void assignBottomListDialogView(final Context context,  final Dialog dialog,
                                                   final List<String> words, String bottomTxt, final MyItemDialogListener listener) {
        View root = View.inflate(context,R.layout.dialog_ios_alert_bottom,null);
        Button btnBottom = (Button) root.findViewById(R.id.btn_bottom);
        if (TextUtils.isEmpty(bottomTxt)){
            btnBottom.setVisibility(View.GONE);
        }else {
            btnBottom.setVisibility(View.VISIBLE);
            btnBottom.setText(bottomTxt);
            btnBottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onBottomBtnClick();
                    if (dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });
        }

        ListView listView = (ListView) root.findViewById(R.id.lv);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return words.size();
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(final int position, View convertView, ViewGroup parent) {
                Button view = (Button) View.inflate(context,R.layout.item_btn_bottomalert,null);
                view.setText(words.get(position));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onItemClick(position);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                return view;
            }
        };

        listView.setAdapter(adapter);
        dialog.setContentView(root);
    }

    public static Dialog showProgressDialog(Context context,String msg,boolean cancleable,boolean outsideTouchable) {

        Dialog dialog = buildDialog(context,cancleable,outsideTouchable);

        View root = View.inflate(context,R.layout.progressview_wrapconent,null);
        TextView tvMsg = (TextView) root.findViewById(R.id.message);
        tvMsg.setText(msg);
        dialog.setContentView(root);

        setDialogStyle(context,dialog);

        dialog.show();
        return dialog;
    }

    private static Dialog buildDialog(Context context, boolean cancleable, boolean outsideTouchable) {
        Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(cancleable);
        dialog.setCanceledOnTouchOutside(outsideTouchable);
        return dialog;
    }


    private static void setDialogStyle(Context activity, Dialog dialog) {
        Window window = dialog.getWindow();

        //window.setWindowAnimations(R.style.dialog_center);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//todo keycode to show round corner


        WindowManager.LayoutParams wl = window.getAttributes();
       /* wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();*/
// 以下这两句是为了保证按钮可以水平满屏
        int width = ((WindowManager) activity.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();

        // wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = (int) (width * 0.85);  // todo keycode to keep gap
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //wl.horizontalMargin= 0.2f;
// 设置显示位置
        // wl.gravity = Gravity.CENTER_HORIZONTAL;

        if (!(activity instanceof Activity)){
            wl.type = WindowManager.LayoutParams.TYPE_TOAST;//todo keycode to improve window level
        }

        dialog.onWindowAttributesChanged(wl);
    }

    private static void assigIosAlertView(Context activity, final Dialog dialog, boolean isButtonVerticle,
                                                 String title, String msg, String firstTxt, String secondTxt, String thirdTxt,
                                                 final MyDialogListener listener) {
        View root = View.inflate(activity,isButtonVerticle ? R.layout.dialog_ios_alert_vertical : R.layout.dialog_ios_alert,null);
        TextView tvTitle = (TextView) root.findViewById(R.id.tv_title);
        if (TextUtils.isEmpty(title)){
            tvTitle.setVisibility(View.GONE);
        }else {
            tvTitle.setVisibility(View.VISIBLE);
            tvTitle.setText(title);
        }

        TextView tvMsg = (TextView) root.findViewById(R.id.tv_msg);
        tvMsg.setText(msg);

        Button button1 = (Button) root.findViewById(R.id.btn_1);
        Button button2 = (Button) root.findViewById(R.id.btn_2);
        Button button3 = (Button) root.findViewById(R.id.btn_3);

        if (TextUtils.isEmpty(firstTxt)){
            root.findViewById(R.id.ll_container).setVisibility(View.GONE);
            root.findViewById(R.id.line).setVisibility(View.GONE);
        }else {

            button1.setVisibility(View.VISIBLE);
            button1.setText(firstTxt);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onFirst(dialog);
                    if (dialog != null && dialog.isShowing()){
                        dialog.dismiss();
                    }
                }
            });

            //btn2
            if (TextUtils.isEmpty(secondTxt)){
                root.findViewById(R.id.line_btn2).setVisibility(View.GONE);
                button2.setVisibility(View.GONE);
            }else {
                root.findViewById(R.id.line_btn2).setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                button2.setText(secondTxt);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listener.onSecond(dialog);
                        if (dialog != null && dialog.isShowing()){
                            dialog.dismiss();
                        }
                    }
                });

                //btn 3
                if (TextUtils.isEmpty(thirdTxt)){
                    root.findViewById(R.id.line_btn3).setVisibility(View.GONE);
                    button3.setVisibility(View.GONE);
                }else {
                    root.findViewById(R.id.line_btn3).setVisibility(View.VISIBLE);
                    button3.setVisibility(View.VISIBLE);

                    button3.setText(thirdTxt);
                    button3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            listener.onThird(dialog);
                            if (dialog != null && dialog.isShowing()){
                                dialog.dismiss();
                            }
                        }
                    });


                }

            }

        }

        dialog.setContentView(root);






    }


}
