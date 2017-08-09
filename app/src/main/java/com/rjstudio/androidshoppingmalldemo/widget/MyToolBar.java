package com.rjstudio.androidshoppingmalldemo.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.StringRes;
//import android.support.v7.internal.widget.TintTypedArray;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.rjstudio.androidshoppingmalldemo.R;

/**
 * Created by r0man on 2017/7/26.
 */

public class MyToolBar extends Toolbar {
    private Button leftButton;
    private ImageButton rightButton;
    private TextView titleTextView;
    private EditText editText;
    private Context mContext;
    private View mView;

    public MyToolBar(Context context) {
        this(context,null);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyToolBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initView();
        //TODO : 这个方法有什么用?
        setContentInsetsRelative(10,10);

        //TODO : AttributeSet attrs 这个属性有什么用?

        if (attrs != null)
        {
//            TintTypedArray can only be called from within the same library group



            final TintTypedArray a = TintTypedArray.obtainStyledAttributes(getContext(), attrs,
                    R.styleable.MyToolBar, defStyleAttr, 0);


            final Drawable rightIcon = a.getDrawable(R.styleable.MyToolBar_rightButtonIcon);
            if (rightIcon != null) {
                //setNavigationIcon(navIcon);
                setRightButtonIcon(rightIcon);
            }


            boolean isShowSearchView = a.getBoolean(R.styleable.MyToolBar_isShowSearchView,false);

            if(isShowSearchView){

                showSearchBar();

                hideTitle();
            }


            a.recycle();
        }
    }

    private void initView()
    {
        if (mView == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            mView = layoutInflater.inflate(R.layout.tool_layout,null);
            leftButton = (Button) mView.findViewById(R.id.bu_left);
            rightButton = (ImageButton) mView.findViewById(R.id.bu_right);
            titleTextView = (TextView) mView.findViewById(R.id.tv_tv_toolbar_title);
            Log.d("InitView",""+titleTextView.getText());
            editText = (EditText) mView.findViewById(R.id.et_search);

            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL);
            addView(mView,lp);
        }

    }

//    About "Toolbar" -  title

    @Override
    public void setTitle(@StringRes int resId) {
        setTitle(getContext().getText(resId));
//        Log.d("Change",getContext().getText(resId)+"");
    }

    @Override
    public void setTitle(CharSequence title) {
        initView();
        if (titleTextView != null)
        {

            titleTextView.setText(title);
//            Log.d("Change1",title+ "--getTvContent"+titleTextView.getText());

            showTitle();
        }
    }


//    About "Toolbar" - leftButton -- back
    public void showLeftButton()
    {
//        initView();
        if (leftButton != null)
        {
            leftButton.setVisibility(VISIBLE);
        }
    }

    public void hideLeftButton()
    {
//        initView();
        if (leftButton != null)
        {
            Log.d("hide","Left");
            leftButton.setVisibility(INVISIBLE);
        }
    }

//    About "Toolbar" - RightButton -- Search
    public void showRightButton()
    {
        //initView();
        if (rightButton != null)
        {
            rightButton.setVisibility(VISIBLE);
        }
    }
    public void hideRightButton()
    {
       // initView();
        if (rightButton != null)
        {
            rightButton.setVisibility(INVISIBLE);
        }
    }

//    About "Toolbar" - Search
    public void showSearchBar()
    {
        if (editText != null)
        {
            editText.setVisibility(VISIBLE);
        }
    }

    public void hideSearchBar()
    {
        if (editText != null)
        {
            editText.setVisibility(INVISIBLE);
        }
    }

//    About Toolbar -- RightButton
    public void setRightButtonIcon(Drawable icon)
    {
        if (rightButton != null)
        {
            rightButton.setImageDrawable(icon);
            rightButton.setVisibility(VISIBLE);
        }
    }

    public void hideTitle()
    {
        if (titleTextView != null)
        {
            titleTextView.setVisibility(INVISIBLE);
        }
    }
    public void showTitle()
    {
        if (titleTextView != null)
        {
            titleTextView.setVisibility(VISIBLE);
        }
    }
    public void setRightButtonOnClickListener(OnClickListener li)
    {
        rightButton.setOnClickListener(li);
    }
    public void setLeftButtonOnClickListener(OnClickListener listener)
    {
//        Log.d("ToolBar","LeftButton seted");
        leftButton.setOnClickListener(listener);
    }

}
