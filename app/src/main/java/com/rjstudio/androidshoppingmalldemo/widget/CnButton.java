package com.rjstudio.androidshoppingmalldemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.R;

/**
 * Created by r0man on 2017/7/31.
 */

public class CnButton extends LinearLayout implements View.OnClickListener
{

    private LayoutInflater mInflater;
    private Button bu_sub;
    private Button bu_add;
    private TextView tv_content;

    private int value;
    private int minValue;
    private int maxValue;

    private OnButtonClickListener onButtonClickListener;

    String TAG = "CnButton";

    public CnButton(Context context) {
        super(context);
    }

    public CnButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CnButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CnButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initView();

        if (attrs != null)
        {
            Log.d(TAG,"Load attrs");
            TintTypedArray a = TintTypedArray.obtainStyledAttributes(context,attrs,R.styleable.CnButton,defStyleAttr,0);

            int val = a.getInt(R.styleable.CnButton_value,0);
            setValue(val);

            int minVal = a.getInt(R.styleable.CnButton_minValue,0);
            setValue(minVal);

            int maxVal = a.getInt(R.styleable.CnButton_maxValue,0);
            setValue(maxVal);

            Drawable drawableBtnAdd = a.getDrawable(R.styleable.CnButton_btnAddBackground);
            Drawable drawableBtnSub = a.getDrawable(R.styleable.CnButton_btnSubBackground);
            Drawable drawableTv = a.getDrawable(R.styleable.CnButton_tvBackground);

            setBtnAddBackground(drawableBtnAdd);
            setBtnSubBackground(drawableBtnSub);
            setTvBackground(drawableTv );
            a.recycle();
        }
    }

    private void initView()
    {
        //TODO : 不懂
        View view = mInflater.inflate(R.layout.custom_button,this,true);
        bu_add = (Button) view.findViewById(R.id.bu_add);
        bu_sub = (Button) view.findViewById(R.id.bu_sub);
        tv_content = (TextView) view.findViewById(R.id.tv_content);

        bu_add.setOnClickListener(this);
        bu_sub.setOnClickListener(this);


    }

    public void setBtnAddBackground(Drawable btnAddBackground) {
        bu_add.setBackgroundDrawable(btnAddBackground);
    }

    public void setBtnSubBackground(Drawable btnSubBackground)
    {
        bu_sub.setBackgroundDrawable(btnSubBackground);
    }
    public void setTvBackground(Drawable tvBackground)
    {
        tv_content.setBackgroundDrawable(tvBackground);
    }

    //设置监听
    public interface OnButtonClickListener{
        abstract void sub(View view,int value);
        abstract void add(View view,int value);
    }

    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener)
    {
        this.onButtonClickListener = onButtonClickListener;
    }

    @Override
    public void onClick(View v) {

            switch (v.getId())
            {
                case R.id.bu_sub:
                    subValue();
                    if (onButtonClickListener != null)
                    {
                        onButtonClickListener.sub(v,value);
                    }

                    break;
                case R.id.bu_add:
                    addValue();
                    if (onButtonClickListener != null)
                    {
                        onButtonClickListener.add(v,value);

                    }
                    break;
            }

    }

    public void addValue()
    {
        if (value < maxValue)
        {
            value ++;
            tv_content.setText(value + "");
        }
    }
    public void subValue()
    {
        if (value > minValue)
        {
            value --;
            tv_content.setText(value +"");
        }
    }
    public int getValue()
    {
        String string_value = tv_content.getText().toString();
        if (string_value != null  &&  !"".equals(value))
        {
            return value;
        }
        this.value = Integer.parseInt(string_value);

        return value;
    }


    public void setValue(int value) {
        tv_content.setText(value);
        this.value = value;
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }
}
