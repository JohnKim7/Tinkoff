package com.johnkim.tinkoff.views;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.johnkim.tinkoff.R;

public class SendLayout extends RelativeLayout {

    private AppCompatEditText editText;
    private AppCompatButton sendButton;

    public SendLayout(Context context) {
        super(context);
    }

    public SendLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public SendLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_message_send, this);
        editText = (AppCompatEditText) findViewById(R.id.edit_text_message);
        sendButton = (AppCompatButton) findViewById(R.id.btn_send_message);
        sendButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        sendButton.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
        editText.setBackgroundColor(Color.WHITE);
        editText.setTextColor(ContextCompat.getColor(getContext(), R.color.textColor));
        requestLayout();

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.length() == 0){
                    sendButton.setEnabled(false);
                }else{
                    sendButton.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });



    }





}
