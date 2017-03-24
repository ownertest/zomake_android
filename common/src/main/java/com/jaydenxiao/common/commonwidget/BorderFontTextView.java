package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;

import com.jaydenxiao.common.R;


/**
 * 自定义字体图标
 */
public class BorderFontTextView extends FontTextView {

    private Paint paint;
    private int strokeWidth = 1;

    private static final int BORDER_NONE_DEFAULT = 0;
    private static final int BORDER_TOP = 1;
    private static final int BORDER_RIGHT = 2;
    private static final int BORDER_BOTTOM = 4;
    private static final int BORDER_LEFT = 8;
    private int color;
    private boolean borderLeft;
    private boolean borderRight;
    private boolean borderBottom;
    private boolean borderTop;
    private boolean hasBorder;

    public BorderFontTextView(Context context) {
        super(context);
        init(context, null);
    }

    public BorderFontTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BorderFontTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    void init(Context context, AttributeSet attrs) {
        if (paint == null)
            paint = new Paint();

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BorderFontTextView);

        borderLeft = array.getBoolean(R.styleable.BorderFontTextView_borderLeft, false);
        borderRight = array.getBoolean(R.styleable.BorderFontTextView_borderRight, false);
        borderBottom = array.getBoolean(R.styleable.BorderFontTextView_borderBottom, false);
        borderTop = array.getBoolean(R.styleable.BorderFontTextView_borderTop, false);
        hasBorder = borderLeft || borderBottom || borderRight || borderTop;

        strokeWidth = array.getDimensionPixelSize(R.styleable.BorderFontTextView_border_width, 0);

        color = array.getColor(R.styleable.BorderFontTextView_border_color, getResources().getColor(R.color.transparent));

        array.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (hasBorder) {
            //  将边框设为黑色
            paint.setColor(color);
            paint.setStrokeWidth(strokeWidth);
            //  画TextView的4个边
            if (borderTop)
                canvas.drawLine(0, 0, this.getWidth(), 0, paint);
            if (borderLeft)
                canvas.drawLine(0, 0, 0, this.getHeight(), paint);
            if (borderRight)
                canvas.drawLine(this.getWidth(), 0, this.getWidth(), this.getHeight(), paint);
            if (borderBottom)
                canvas.drawLine(0, this.getHeight(), this.getWidth(), this.getHeight(), paint);
        }
        super.onDraw(canvas);
    }


}