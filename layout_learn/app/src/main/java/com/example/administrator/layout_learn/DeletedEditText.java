package com.example.administrator.layout_learn;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


/**
 * Created by Administrator on 2019/3/19.
 */
public class DeletedEditText extends EditText {
    private Drawable mRightDrawable;
    boolean mIsHasFocus;

   //
    private void init() {
        //Drawable left=getResources().getDrawable(R.drawable.user_account);
       // left.setBounds(0,0,50,50);
        Drawable drawables[]=this.getCompoundDrawables();
        mRightDrawable=drawables[2];
        this.addTextChangedListener(new TextWatcherImple());
        this.setOnFocusChangeListener(new OnFocusChangeImpl());
        setClearDrawableVisible(false);
    }
    public DeletedEditText(Context context) {
        super(context);
        init();
    }

    public DeletedEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public DeletedEditText(Context context, AttributeSet attrs,int defStyle) {
        super(context, attrs,defStyle);
        init();
    }
    private class OnFocusChangeImpl implements OnFocusChangeListener
    {
        @Override
        public void onFocusChange(View v,boolean hasFocus)
        {
            mIsHasFocus=hasFocus;
            if(mIsHasFocus)
            {
                boolean isNoNull=getText().toString().length()>=1;
                setClearDrawableVisible(isNoNull);
            }
            else
                setClearDrawableVisible(false);
        }
    }
    void  setClearDrawableVisible(boolean isNoNull)
    {
        Drawable rightDrawable;
        if(isNoNull)
        {
            rightDrawable=mRightDrawable;
        }
        else
        {
            rightDrawable=null;
        }
        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],rightDrawable,getCompoundDrawables()[3]);
    }
    private class TextWatcherImple implements TextWatcher
    {
        @Override
        public void afterTextChanged(Editable s)
        {
            boolean isNoNull=getText().toString().length()>=1;
            setClearDrawableVisible(isNoNull);
        }
        public void beforeTextChanged(CharSequence s, int start,
                                      int count, int after)
        {

        }
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

    }
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_UP:
                int rightPos=getWidth()-getPaddingRight();
                int leftPos=getWidth()-getTotalPaddingRight();
                boolean isClean=(event.getX()<rightPos&&event.getX()>leftPos);
                if(isClean)
                    setText("");
                break;
            default:
                break;
        }
        return super.onTouchEvent(event);
    }

}
