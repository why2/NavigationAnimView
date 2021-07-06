package com.why2.navigationanim.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 导航动画布局
 */
public class NavigationAnimLayout extends LinearLayout {
    List<NavigationAnimView> animViews = new ArrayList<>();
    List<Integer> positions = new ArrayList<>();
    private int position = 0;
    private ClickListener clickListener;

    public NavigationAnimLayout(Context context) {
        this(context, null);
    }

    public NavigationAnimLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationAnimLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public NavigationAnimLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = getChildAt(i);
            if (view instanceof NavigationAnimView) {
                animViews.add((NavigationAnimView) view);
                positions.add(position);
                view.setTag(position);
                ((NavigationAnimView) view).setClickListener(() -> {
                    int curPos = (int) view.getTag();
                    for (int j = 0; j < animViews.size(); j++) {
                        NavigationAnimView navigationAnimView = animViews.get(j);
                        boolean isCurrent = ((int) navigationAnimView.getTag()) == curPos;
                        navigationAnimView.setSelected(isCurrent);
                        navigationAnimView.setStartAnim(isCurrent);
                    }
                    if (clickListener != null) {
                        clickListener.click(curPos);
                    }
                });
                position++;
            }
        }
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        /**
         * @param position 点击菜单位置
         */
        void click(int position);
    }
}
