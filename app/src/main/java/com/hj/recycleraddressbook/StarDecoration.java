package com.hj.recycleraddressbook;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

/**
 * 分割线
 */
public class StarDecoration extends RecyclerView.ItemDecoration {
    private Paint textPaint;
    private Rect textRect;
    private int groupHeaderHeight;
    private Paint headPaint;
    private Context mContext;
    public callBack callBack;


    public StarDecoration(Context context, callBack callBack) {
        this.mContext = context;
        this.callBack = callBack;

        headPaint = new Paint();
        headPaint.setColor(mContext.getResources().getColor(R.color.teal_700));

        groupHeaderHeight = dp2px(context, 100);
        textRect = new Rect();

        textPaint = new Paint();
        textPaint.setTextSize(50);

    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        int position = parent.getChildAdapterPosition(view);
        int position = parent.getChildLayoutPosition(view);
        if (isGroupHeader(position)) {
            //如果是头部，预留更大的difang
            outRect.top = groupHeaderHeight;
        } else {
            //1像素下划线
            outRect.top = 1;
            outRect.left = 50;
            outRect.right = 50;
        }

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        //获取屏幕item数
        int childCount = parent.getChildCount();
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        for (int i = 0; i < childCount; i++) {
            //获取对应的itemView
            View view = parent.getChildAt(i);
            //获取view的布局位置
            int position = parent.getChildLayoutPosition(view);
            int leftDecorationWidth = layoutManager.getLeftDecorationWidth(view);
            int rightDecorationWidth = layoutManager.getRightDecorationWidth(view);
            //绘制头部ui--背景+文字
            //如果是group的第一个
            if (isGroupHeader(position) && (view.getTop() - groupHeaderHeight - parent.getPaddingTop() >= 0)) {
                //头部的绘制
                c.drawRect(left, view.getTop() - groupHeaderHeight, right, view.getTop(), headPaint);
                //绘制文字
                String groupName = callBack.getGroupName(position);
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                c.drawText(groupName, left + 20, view.getTop() - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
            } else {
                //画分割线
                c.drawRect(left + leftDecorationWidth, view.getTop() - 1, right - rightDecorationWidth, view.getTop(), headPaint);
            }

        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //返回可见区域的第一个item的position
        int position = ((LinearLayoutManager) Objects.requireNonNull(parent.getLayoutManager())).findFirstVisibleItemPosition();
        //获取对应position 的view
        View itemView = Objects.requireNonNull(parent.findViewHolderForAdapterPosition(position)).itemView;

        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();
        int top = parent.getPaddingTop();
        //当第二个是组的头部的时候
        if (isGroupHeader(position + 1)) {
            int bottom = Math.min(groupHeaderHeight, itemView.getBottom() - parent.getPaddingTop());
            c.drawRect(left, top, right, top + bottom, headPaint);

            String groupName = callBack.getGroupName(position);
            textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
            c.drawText(groupName, left + 20, top + bottom - groupHeaderHeight / 2 + textRect.height() / 2, textPaint);

        } else {
            //同一组的情况
            c.drawRect(left, top, right, top + groupHeaderHeight, headPaint);
            String groupName = callBack.getGroupName(position);
            textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
            c.drawText(groupName, left + 20, top + groupHeaderHeight / 2 + textRect.height() / 2, textPaint);
        }

    }


    /**
     * 判断是否是组的第一个item
     *
     * @param position
     * @return
     */
    public boolean isGroupHeader(int position) {
        if (position == 0) {
            return true;
        }
        String currentGroupName = callBack.getGroupName(position);
        String preGroupName = callBack.getGroupName(position - 1);
        return !preGroupName.equals(currentGroupName);
    }

    public interface callBack {
        String getGroupName(int position);
    }


    private int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale * 0.5f);
    }

}
