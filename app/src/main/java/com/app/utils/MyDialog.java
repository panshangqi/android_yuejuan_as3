package com.app.utils;

import com.app.yuejuan.R;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 *
 * Created by admin on 2017/8/30.
 */

public class MyDialog extends Dialog {
    private Button yes;//
    private Button no;//
    private TextView titleTV;//
    private TextView message;//
    private String titleStr;//
    private String messageStr;//
    private View splitView;
    //
    private String yesStr, noStr;
    private onNoOnclickListener noOnclickListener;//
    private onYesOnclickListener yesOnclickListener;//
    private String DialogType;
    public MyDialog( Context context, int themeResId, String DialogType) {
        super(context, themeResId);
        this.DialogType = DialogType; // "YES_NO" "YES"
    }

    /**
     *
     *
     * @param str
     * @param onNoOnclickListener
     */
    public void setNoOnclickListener(String str, onNoOnclickListener onNoOnclickListener) {
        if (str != null) {
            noStr = str;
        }
        this.noOnclickListener = onNoOnclickListener;
    }

    /**
     *
     *
     * @param str
     * @param yesOnclickListener
     */
    public void setYesOnclickListener(String str, onYesOnclickListener yesOnclickListener) {
        if (str != null) {
            yesStr = str;
        }
        this.yesOnclickListener = yesOnclickListener;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_yes_no);

        setCanceledOnTouchOutside(false);


        initView();


        initData();

        initEvent();
    }

    /**
     *
     */
    private void initView() {
        yes = (Button)findViewById(R.id.yes);
        no = (Button)findViewById(R.id.no);
        splitView = this.findViewById(R.id.split_line);
        titleTV = (TextView) findViewById(R.id.title);
        message = (TextView) findViewById(R.id.message);
        
        if("YES".equals(DialogType)){
        	splitView.setVisibility(View.GONE);
        	no.setVisibility(View.GONE);
        }
    }

    /**
     *
     */
    private void initData() {
        //����û��Զ���title��message
        if (titleStr != null) {
            titleTV.setText(titleStr);
        }
        if (messageStr != null) {
            message.setText(messageStr);
        }
        //������ð�ť����
        if (yesStr != null) {
            yes.setText(yesStr);
        }
        if (noStr != null) {
            no.setText(noStr);
        }
    }

    /**
     *
     */
    private void initEvent() {
        //
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (yesOnclickListener != null) {
                    yesOnclickListener.onYesOnclick();
                }
            }
        });
        //
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (noOnclickListener != null) {
                    noOnclickListener.onNoClick();
                }
            }
        });
    }

    /**
     *
     *
     * @param title
     */
    public void setTitle(String title) {
        titleStr = title;
    }

    /**
     *
     *
     * @param message
     */
    public void setMessage(String message) {
        messageStr = message;
    }

    public interface onNoOnclickListener {
        public void onNoClick();
    }

    public interface onYesOnclickListener {
        public void onYesOnclick();
    }
}