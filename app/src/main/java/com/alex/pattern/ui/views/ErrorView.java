package com.alex.pattern.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alex.pattern.R;
import com.alex.pattern.utils.CommonUtils;

/**
 * Created by Alex
 */

public class ErrorView extends RelativeLayout {

    private Context mContext;
    private RelativeLayout mErrorLayout;
    private TextView mTextViewErrorText;
    private ImageView mImageViewIcon;

    public ErrorView(Context context) {
        super(context);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ErrorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void showError() {
        mErrorLayout.setVisibility(View.VISIBLE);
    }

    public void showError(String error) {
        showError();
        mTextViewErrorText.setText(error);
    }

    public void showError(String error, int drawable) {
        showError(error);
        mImageViewIcon.setImageDrawable(CommonUtils.getDrawable(mContext, drawable));
    }

    public void hideError() {
        mErrorLayout.setVisibility(View.GONE);
    }

    private void init(Context context) {
        inflate(getContext(), R.layout.view_error, this);
        mContext = context;
        mErrorLayout = (RelativeLayout) findViewById(R.id.error_layout);
        mTextViewErrorText = (TextView) findViewById(R.id.textView_text);
        mImageViewIcon = (ImageView) findViewById(R.id.imageView_icon);
    }
}