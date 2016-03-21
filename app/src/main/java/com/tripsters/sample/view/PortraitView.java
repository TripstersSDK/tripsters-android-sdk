package com.tripsters.sample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.tripsters.android.model.Identity;
import com.tripsters.sample.R;
import com.tripsters.sample.util.ImageUtils;

public class PortraitView extends FrameLayout {

    public enum Type {
        ITEM(0), PROFILE(1), MESSAGE(2), ORDER(3), EDIT(4);

        Type(int ni) {
            nativeInt = ni;
        }

        final int nativeInt;
    }

    private ImageView mPortraitIv;
    private ImageView mPortraitLocalIv;

    private Type mType;
    private boolean mCircle;

    public PortraitView(Context context) {
        super(context);
        init();
        setType(Type.ITEM);
    }

    public PortraitView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PortraitView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.PortraitView, defStyle, 0);

        int index = a.getInt(R.styleable.PortraitView_type, -1);
        if (index >= 0 && index < Type.values().length) {
            setType(Type.values()[index]);
        } else {
            setType(Type.ITEM);
        }

        a.recycle();
    }

    private void setType(Type type) {
        mType = type;

        int portraitSize;
        int portraitMargin;

        switch (mType) {
            case EDIT:
                portraitSize = getResources().getDimensionPixelSize(R.dimen.portrait_edit_size);
                portraitMargin = 0;
                break;
            case ORDER:
                portraitSize = getResources().getDimensionPixelSize(R.dimen.portrait_order_size);
                portraitMargin = 0;
                break;
            case PROFILE:
                portraitSize = getResources().getDimensionPixelSize(R.dimen.portrait_profile_size);
                portraitMargin = 0;
                break;
            case MESSAGE:
                portraitSize = getResources().getDimensionPixelSize(R.dimen.portrait_item_size);
                portraitMargin = getResources().getDimensionPixelSize(R.dimen.portrait_item_margin);
                break;
            default: // ITEM
                portraitSize = getResources().getDimensionPixelSize(R.dimen.portrait_item_size);
                portraitMargin = getResources().getDimensionPixelSize(R.dimen.portrait_item_margin);
                break;
        }

        LayoutParams portraitParams = (LayoutParams) mPortraitIv.getLayoutParams();
        portraitParams.width = portraitSize;
        portraitParams.height = portraitSize;
        portraitParams.rightMargin = portraitMargin;
        portraitParams.bottomMargin = portraitMargin;
        mPortraitIv.setLayoutParams(portraitParams);
        if (type == Type.PROFILE || type == Type.ORDER) {
            mCircle = true;
        }
    }

    private void init() {
        View view = View.inflate(getContext(), R.layout.view_portrait, this);
        mPortraitIv = (ImageView) view.findViewById(R.id.iv_portrait);
        mPortraitLocalIv = (ImageView) view.findViewById(R.id.iv_portrait_local);
    }

    public void setDeault(boolean female) {
        setPortrait("", female, Identity.NONE);
    }

    public void setDeault(int defaultResId) {
        setPortrait("", defaultResId, Identity.NONE);
    }

    public void setPortraitLocal(Identity identity) {
        switch (mType) {
            case EDIT:
                mPortraitLocalIv.setVisibility(View.GONE);
                break;
            case ORDER:
            case PROFILE:
                if (identity == Identity.COUNTRY_DAREN) {
                    mPortraitLocalIv.setVisibility(View.VISIBLE);

                    mPortraitLocalIv.setImageResource(R.drawable.icon_country_daren_large);
                } else if (identity == Identity.NORMAL_DAREN) {
                    mPortraitLocalIv.setVisibility(View.VISIBLE);

                    mPortraitLocalIv.setImageResource(R.drawable.icon_normal_daren_large);
                } else {
                    mPortraitLocalIv.setVisibility(View.GONE);
                }
                break;
            default: // MESSAGE ITEM
                if (identity == Identity.COUNTRY_DAREN) {
                    mPortraitLocalIv.setVisibility(View.VISIBLE);

                    mPortraitLocalIv.setImageResource(R.drawable.icon_country_daren);
                } else if (identity == Identity.NORMAL_DAREN) {
                    mPortraitLocalIv.setVisibility(View.VISIBLE);

                    mPortraitLocalIv.setImageResource(R.drawable.icon_normal_daren);
                } else {
                    mPortraitLocalIv.setVisibility(View.GONE);
                }
                break;
        }
    }

    public void setPortrait(String url, boolean female, Identity identity) {
        ImageUtils.setAvata(getContext(), mPortraitIv, url, female, mCircle);

        setPortraitLocal(identity);
    }

    public void setPortrait(String url, int defaultResId, Identity identity) {
        ImageUtils.setAvata(getContext(), mPortraitIv, url, defaultResId, mCircle);

        setPortraitLocal(identity);
    }

    public void setPortrait(Bitmap bitmap) {
        mPortraitIv.setImageBitmap(bitmap);
    }

    public void setPortraitSize(int size, int margin) {
        LayoutParams portraitParams = (LayoutParams) mPortraitIv.getLayoutParams();
        portraitParams.width = size;
        portraitParams.height = size;
        portraitParams.rightMargin = margin;
        portraitParams.bottomMargin = margin;
        mPortraitIv.setLayoutParams(portraitParams);
    }
}
