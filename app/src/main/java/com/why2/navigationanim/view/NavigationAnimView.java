package com.why2.navigationanim.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.why2.navigationanim.R;
import com.why2.navigationanim.utils.SizeUtils;

/**
 * 底部导航动画view
 */
public class NavigationAnimView extends LinearLayout {
    /**
     * 是否选中
     */
    private boolean selected;
    /**
     * 未选中文字颜色
     */
    private int unselectedTxtColor;
    /**
     * 选中文字颜色
     */
    private int selectedTxtColor;
    /**
     * 未选中图片
     */
    private int unSelectedImgRes;
    /**
     * 开始动画
     */
    private boolean startAnim;
    /**
     * 文字内容
     */
    private String content;
    /**
     * 选中图片集合
     */
    private int[] selectedRes;
    /**
     * 选中之后是否可以再次响应点击
     */
    private boolean selected_reselected;
    /**
     * 文字大小
     */
    private int txtSize;
    /**
     * 图片宽度
     */
    private int nav_icon_width;
    /**
     * 图片高度
     */
    private int nav_icon_height;
    /**
     * 文字图片间距
     */
    private int nav_txt_icon_margin;
    /**
     * 点击监听
     */
    private ClickListener clickListener;
    private ImageView img_icon;
    private TextView tv_content;

    public NavigationAnimView(Context context) {
        this(context, null);
    }

    public NavigationAnimView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationAnimView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
        inflate(context, R.layout.layout_navigation_anim_view, this);
    }

    public NavigationAnimView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NavigationAnimView);
        content = typedArray.getString(R.styleable.NavigationAnimView_nav_content);
        txtSize = typedArray.getDimensionPixelSize(R.styleable.NavigationAnimView_nav_txt_size, 10);
        nav_icon_width = typedArray.getDimensionPixelSize(R.styleable.NavigationAnimView_nav_icon_width, SizeUtils.dp2px(24));
        nav_icon_height = typedArray.getDimensionPixelSize(R.styleable.NavigationAnimView_nav_icon_height, SizeUtils.dp2px(24));
        nav_txt_icon_margin = typedArray.getDimensionPixelSize(R.styleable.NavigationAnimView_nav_txt_icon_margin, SizeUtils.dp2px(5));
        selected = typedArray.getBoolean(R.styleable.NavigationAnimView_nav_selected, false);
        selected_reselected = typedArray.getBoolean(R.styleable.NavigationAnimView_nav_selected_reselected, false);
        selectedTxtColor = typedArray.getColor(R.styleable.NavigationAnimView_nav_selectedTxtColor, 0);
        unselectedTxtColor = typedArray.getColor(R.styleable.NavigationAnimView_nav_unselectedTxtColor, 0);
        unSelectedImgRes = typedArray.getResourceId(R.styleable.NavigationAnimView_nav_unSelectedImgRes, 0);
        selectedRes = getResources().getIntArray(typedArray.getResourceId(R.styleable.NavigationAnimView_nav_selectedImgRes, 0));
        typedArray = getResources().obtainTypedArray(typedArray.getResourceId(R.styleable.NavigationAnimView_nav_selectedImgRes, 0));
        int length = selectedRes.length;
        for (int i = 0; i < length; i++) {
            selectedRes[i] = typedArray.getResourceId(i, 0);
        }
        typedArray.recycle();
        setOnClickListener(v -> {
            if (selected_reselected) {
                if (clickListener != null) {
                    clickListener.click();
                }
            } else {
                if (!selected) {
                    if (clickListener != null) {
                        clickListener.click();
                    }
                }
            }
        });
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        img_icon = findViewById(R.id.img_icon);
        tv_content = findViewById(R.id.tv_content);
        img_icon.setImageResource(selected ? selectedRes[selectedRes.length - 1] : unSelectedImgRes);
        tv_content.setText(content);
        tv_content.setTextSize(TypedValue.COMPLEX_UNIT_PX, txtSize);
        tv_content.setTextColor(selected ? selectedTxtColor : unselectedTxtColor);
        ViewGroup.LayoutParams lpImgIcon = img_icon.getLayoutParams();
        lpImgIcon.width = nav_icon_width;
        lpImgIcon.height = nav_icon_height;
        img_icon.setLayoutParams(lpImgIcon);
        ViewGroup.MarginLayoutParams lpTvContent = (MarginLayoutParams) tv_content.getLayoutParams();
        lpTvContent.topMargin = nav_txt_icon_margin;
        tv_content.setLayoutParams(lpTvContent);
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        tv_content.setTextColor(selected ? selectedTxtColor : unselectedTxtColor);
        img_icon.setImageResource(selected ? selectedRes[selectedRes.length - 1] : unSelectedImgRes);
    }

    public boolean isStartAnim() {
        return startAnim;
    }

    /**
     * 设置动画效果是否开启
     *
     * @param startAnim
     */
    public void setStartAnim(boolean startAnim) {
        this.startAnim = startAnim;
        if (startAnim) {
            ValueAnimator valueAnimator = ValueAnimator.ofInt(0, selectedRes.length - 1);
            valueAnimator.setDuration(1000);
            valueAnimator.setInterpolator(new LinearInterpolator());
            valueAnimator.setRepeatCount(0);
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            valueAnimator.addUpdateListener(animation -> img_icon.setImageResource(selected ? selectedRes[((int) animation.getAnimatedValue())] : unSelectedImgRes));
            valueAnimator.start();
        } else {
            img_icon.clearAnimation();
            img_icon.setImageResource(selected ? selectedRes[selectedRes.length - 1] : unSelectedImgRes);
        }
    }

    /**
     * 设置点击回调监听
     *
     * @param clickListener
     */
    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    /**
     * 点击回调
     */
    public interface ClickListener {
        void click();
    }
}
