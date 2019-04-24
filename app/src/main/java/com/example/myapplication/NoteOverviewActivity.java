package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;



import java.util.Calendar;

public class NoteOverviewActivity extends Activity implements View.OnClickListener{
    private PopupWindow mPopWindow;
    private PopupWindow DatePopWindow;
    private LinearLayout llDate;
    private TextView StvDate, EtvDate;
    private int Syear, Smonth, Sday, Eyear, Emonth, Eday;
    private StringBuffer Sdate, Edate;
    private int flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_overview);
        ImageButton NtOverviewBtn = (ImageButton) findViewById(R.id.button_edit);
        ImageButton MapBtn = (ImageButton) findViewById(R.id.button_map);
        ImageButton SearchBtn = (ImageButton) findViewById(R.id.button_search);
        ImageButton CollectBtn = (ImageButton) findViewById(R.id.button_collect);
        FloatingActionButton AddNoteBtn = (FloatingActionButton) findViewById(R.id.add_note);
        NtOverviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt添加点击响应事件
                Intent intent =new Intent(NoteOverviewActivity.this,NoteOverviewActivity.class);
                //启动
                startActivity(intent);
            }
        });
        MapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt添加点击响应事件
                Intent intent =new Intent(NoteOverviewActivity.this,MainActivity.class);
                //启动
                startActivity(intent);
            }
        });
        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt添加点击响应事件
                Intent intent =new Intent(NoteOverviewActivity.this,SearchActivity.class);
                //启动
                startActivity(intent);
            }
        });
        CollectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 给bnt添加点击响应事件
                Intent intent =new Intent(NoteOverviewActivity.this,CollectActivity.class);
                //启动
                startActivity(intent);
            }
        });
        //新建行程事件
        AddNoteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow();
            }
        });
    }
    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(NoteOverviewActivity.this).inflate(R.layout.popwindow_select_time, null);
        mPopWindow = new PopupWindow(contentView,
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //在PopupWindow里面就加上下面代码，让键盘弹出时，不会挡住pop窗口。
        mPopWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //点击空白处时，隐藏掉pop窗口
        mPopWindow.setFocusable(true);
        mPopWindow.setBackgroundDrawable(new BitmapDrawable());
        backgroundAlpha(1f);
        //添加pop窗口关闭事件
        mPopWindow.setOnDismissListener(new poponDismissListener());
        backgroundAlpha(0.6f);
        //设置各个控件的点击响应
        TextView ok = (TextView)contentView.findViewById(R.id.time_pick_ok);
        TextView cancel = (TextView)contentView.findViewById(R.id.time_pick_cancel);
        TextView start = (TextView)contentView.findViewById(R.id.start_date_picker);
        StvDate = (TextView)contentView.findViewById(R.id.start_date_picker);
        EtvDate = (TextView)contentView.findViewById(R.id.end_date_picker);
        TextView end = (TextView)contentView.findViewById(R.id.end_date_picker);
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
        start.setOnClickListener(this);
        end.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(NoteOverviewActivity.this).inflate(R.layout.note_overview, null);
        mPopWindow.showAtLocation(rootview, Gravity.CENTER, 0, 0);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.time_pick_ok: {
                Toast.makeText(this, "clicked computer", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(NoteOverviewActivity.this,NoteEditActivity.class);
                //启动
                startActivity(intent);
                mPopWindow.dismiss();
            }
            break;
            case R.id.time_pick_cancel: {
                Toast.makeText(this, "clicked financial", Toast.LENGTH_SHORT).show();
                mPopWindow.dismiss();
            }
            break;
            case R.id.start_date_picker: {
                Intent intent = new Intent(this,DateSelectActivity.class);
                startActivityForResult(intent,100);
            }
            break;
            case R.id.end_date_picker: {

            }
            break;
        }
    }

    /**
     * 设置背景透明度
     * @param f
     */
    private void backgroundAlpha(float f) {
        WindowManager.LayoutParams lp =getWindow().getAttributes();
        lp.alpha = f;
        getWindow().setAttributes(lp);
    }

    class poponDismissListener implements PopupWindow.OnDismissListener{
        @Override
        public void onDismiss() {
            // TODO Auto-generated method stub
            //Log.v("List_noteTypeActivity:", "我是关闭事件");
            backgroundAlpha(1f);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == DateSelectActivity.RERSULT_CODE) {
            Bundle extras = data.getExtras();
            if (extras!=null) {
                String start = extras.getString(DateSelectActivity.START);
                String end = extras.getString(DateSelectActivity.END);
                int days = extras.getInt(DateSelectActivity.DAY_NUMBER);

                StvDate.setText("开始时间："+start + "   结束时间："+end + "   天数："+days);
            }
        }
    }
}
