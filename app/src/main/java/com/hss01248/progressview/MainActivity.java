package com.hss01248.progressview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialog;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.hss01248.progressview.gradientRing.MyRingView;


public class MainActivity extends Activity {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyRingView ringView = (MyRingView) findViewById(R.id.ring);
        //ringView.setDefaultColor(Color.RED);
        ringView.setStrokeWidth(20);

        /*<set xmlns:android="http://schemas.android.com/apk/res/android">
<rotate
android:fromDegrees="0"
android:toDegrees="359"
android:duration="500"
android:repeatCount="-1"
android:pivotX="50%"
android:pivotY="50%" />
</set> */

       /* RotateAnimation animation = new RotateAnimation(0,359, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        //animation.setRepeatMode(RotateAnimation.INFINITE);
        animation.setDuration(4200);
        //animation.setFillAfter(true);
        animation.setRepeatCount(RotateAnimation.INFINITE);
        LinearInterpolator lin = new LinearInterpolator();
        animation.setInterpolator(lin);
        //ringView.setAnimation(animation);
        ringView.startAnimation(animation);*/

        //showProgressDialog();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
       /* Intent intent = new Intent(this, CustomDialogActivity.class);
        startActivity(intent);*/

    }

    @Override
    protected void onResume() {
        super.onResume();

       //MyDialogUtils.showProgressDialog(getApplicationContext(),"jindutiao",true,false);


        // showGlobleDialog(this);

       // showDialog();

       // showcenterDialog();
       /* MyDialogUtils.showIosAlert(getApplication(),"hh", "djdsjlfjsd", "本例子是一个自定义的弹出对话框例子源码本例子是一个自定义的弹出对话框例子源码",
                "弹出的时候有半透明效果", "", false, true, new MyDialogListener() {
            @Override
            public void onFirst(DialogInterface dialog) {

            }

            @Override
            public void onSecond(DialogInterface dialog) {

            }

            @Override
            public void onThird(DialogInterface dialog) {

            }

            @Override
            public void onCancle() {

            }
        });*/
       /* List<String> words = new ArrayList<>();
        words.add("相册");
        words.add("zhaoxiaoji");
        words.add("可以");

        MyDialogUtils.showBottomItemDialog(this,words, "", true, true, new MyItemDialogListener() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onBottomBtnClick() {

            }
        });*/




        // toast();
    }

    private void showcenterDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(R.layout.dialog_ios_alert);
        AlertDialog dialog = builder.create();
        Window window = dialog.getWindow();
       // window.setWindowAnimations(R.style.mystyle);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//round corner
        builder.show();
    }

    private void showDialog() {
        // AlertDialog dialog = new AlertDialog(this);
        AppCompatDialog dialog = new AppCompatDialog(this);
        dialog.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);//key code to remove title
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setWindowAnimations(R.style.mystyle);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//round corner
        // window.setBackgroundDrawableResource(R.drawable.bg_ios_roundcorner);
        // window.requestFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_ios_alert_bottom);
        // AlertDialog.Builder builder = new AlertDialog.Builder(this);


        // 可以在此设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
       /* wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();*/
// 以下这两句是为了保证按钮可以水平满屏
        int width = getWindowManager().getDefaultDisplay().getWidth();

        // wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.width = (int) (width * 0.85);  // todo keycode gap
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        //wl.horizontalMargin= 0.2f;
// 设置显示位置
        // wl.gravity = Gravity.CENTER_HORIZONTAL;

        dialog.onWindowAttributesChanged(wl);
        dialog.show();
    }



    public ProgressDialog showActivityDialog(Activity activity, String msg, boolean cancleable, boolean outsideTouchable) {
        ProgressDialog dialog = new ProgressDialog(activity);//,R.style.loading_dialog,
        // android:Theme.Dialog主题能把progressbar的直线头变成圆头，但我又不想要其他特性，其圆头是怎么实现的？
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setIndeterminateDrawable(getResources().getDrawable(R.drawable.progressstyleshape));
        // dialog.setIndeterminate(true);
        dialog.setMessage(msg);
        dialog.setCanceledOnTouchOutside(outsideTouchable);
        dialog.setCancelable(cancleable);
        // dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE)); 会包括下方的阴影效果
        dialog.show();
        return dialog;
    }

    private void showGlobleDialog(Context context) {
        WindowManager wmManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        WindowManager.LayoutParams wmParams = new WindowManager.LayoutParams(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT, 0, 0, PixelFormat.TRANSLUCENT);
/**
 *以下都是WindowManager.LayoutParams的相关属性
 * 具体用途请参考SDK文档
 */
        /**三种类型,
         *  * 每个window都有z-orderd值,也就是type值,值大的会覆盖在值小的上方
         *
         * 系统级:全局可弹出,如toast,状态栏,type值为2000-2999
         * 应用级-如activity,在特定应用中出现,值为1-99
         * 子window级-需要在特定父window中,比如dialog需要在activity中,值为1000-1999
         *
         * 常见系统层级的type值:  弹出这个级别的窗口,如果是TYPE_TOAST以上,需要申请权限,小米手机默认禁止该权限
         *
         *
         * public static final int TYPE_STATUS_BAR         = FIRST_SYSTEM_WINDOW;
         *  public static final int TYPE_SEARCH_BAR         = FIRST_SYSTEM_WINDOW+1;
         *   public static final int TYPE_PHONE              = FIRST_SYSTEM_WINDOW+2;
         *    public static final int TYPE_SYSTEM_ALERT       = FIRST_SYSTEM_WINDOW+3;
         *     public static final int TYPE_KEYGUARD           = FIRST_SYSTEM_WINDOW+4;
         *      public static final int TYPE_TOAST              = FIRST_SYSTEM_WINDOW+5;
         *       public static final int TYPE_SYSTEM_OVERLAY     = FIRST_SYSTEM_WINDOW+6;
         *        public static final int TYPE_SYSTEM_DIALOG      = FIRST_SYSTEM_WINDOW+8;
         *         public static final int TYPE_SYSTEM_ERROR       = FIRST_SYSTEM_WINDOW+10;
         *         public static final int TYPE_NAVIGATION_BAR = FIRST_SYSTEM_WINDOW+19;
         * WindowManager.LayoutParams.TYPE_SYSTEM_ALERT

         * */
        wmParams.type = WindowManager.LayoutParams.TYPE_TOAST;   //
        wmParams.format = 1;
        wmParams.gravity = Gravity.CENTER;
        /**
         *这里的flags也很关键
         *代码实际是wmParams.flags |= FLAG_NOT_FOCUSABLE;
         *40的由来是wmParams的默认属性（32）+ FLAG_NOT_FOCUSABLE（8）
         *
         * // 如果设置了WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE，弹出的View收不到Back键的事件
         *
         * // 不设置这个弹出框的透明遮罩显示为黑色
         params.format = PixelFormat.TRANSLUCENT;
         | WindowManager.LayoutParams.FLAG_DIM_BEHIND
         */
        wmParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_DIM_BEHIND | WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE;
        wmParams.dimAmount = 0.5f;//与WindowManager.LayoutParams.FLAG_DIM_BEHIND以及params.format = PixelFormat.TRANSLUCENT 一起才能设置后面窗口的半透明色

        wmParams.alpha = 1.0f;//整个窗口的半透明值，1.0表示不透明，0.0表示全透明。

        /**
         * 9. public float horizontalMargin;
         水平边距，容器与widget之间的距离，占容器宽度的百分率。

         10. public float verticalMargin;
         纵向边距。*/

        wmParams.horizontalMargin = 0.1f;
       /* wmParams.width=40;
        wmParams.height=40;*/
        ViewGroup viewGroup = (ViewGroup) View.inflate(context, R.layout.progressview_wrapconent, null);


        wmManager.addView(viewGroup, wmParams);  //创建View
    }

    private void toast() {
        Toast toast = Toast.makeText(this, "hahahhahah", Toast.LENGTH_LONG);
        View view = View.inflate(this, R.layout.progressview, null);
        TextView textView = (TextView) view.findViewById(R.id.message);
        textView.setText("我是一条吐司");
        toast.setView(view);
        //toast.setMargin(10,10);
        //toast.setGravity(Gravity.CENTER,0,0);
        toast.show();
    }

    @Override
    public void onStart() {
        super.onStart();




    }

    @Override
    public void onStop() {
        super.onStop();

    }
}
