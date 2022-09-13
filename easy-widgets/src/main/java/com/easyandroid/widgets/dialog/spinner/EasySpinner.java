package com.easyandroid.widgets.dialog.spinner;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.easyandroid.widgets.R;

import java.util.Arrays;
import java.util.List;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/**
 * package: com.easyandroid.widgets.dialog.spinner.EasySpinner
 * author: gyc
 * description:
 * time: create at 2022/7/6 22:27
 */
public class EasySpinner extends AppCompatTextView {

    private static final int MAX_LEVEL = 10000;
    private static final int VERTICAL_OFFSET = 1;
    private static final String INSTANCE_STATE = "instance_state";
    private static final String SELECTED_INDEX = "selected_index";
    private static final String IS_POPUP_SHOWING = "is_popup_showing";
    private static final String IS_ARROW_HIDDEN = "is_arrow_hidden";
    private static final String ARROW_DRAWABLE_RES_ID = "arrow_drawable_res_id";

    private int selectedIndex;
    private Drawable arrowDrawable;
    private ListPopupWindow popupWindow;
    private EasySpinnerBaseAdapter adapter;

    private AdapterView.OnItemClickListener onItemClickListener;
    private AdapterView.OnItemSelectedListener onItemSelectedListener;
    private OnSpinnerItemSelectedListener onSpinnerItemSelectedListener;

    private boolean isArrowHidden;
    private int textColor;
    private int spinner_backgroundSelector;
    private int item_backgroundSelector;
    private int arrowDrawableTint;
    private int displayHeight;
    private int parentVerticalOffset;
    private int spinner_title_margin;
    private int maxNumber;//listPopwindow默认最大条目数，超过该数，高度不再增大，默认6个
    private @DrawableRes
    int arrowDrawableResId;
    private SpinnerTextFormatter spinnerTextFormatter = new SimpleSpinnerTextFormatter();
    private SpinnerTextFormatter selectedTextFormatter = new SimpleSpinnerTextFormatter();
    private TextAlignment horizontalAlignment;
    private String defaultText;

    @Nullable
    private ObjectAnimator arrowAnimator = null;

    public EasySpinner(Context context) {
        super(context);
        init(context, null);
    }

    public EasySpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public EasySpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(INSTANCE_STATE, super.onSaveInstanceState());
        bundle.putInt(SELECTED_INDEX, selectedIndex);
        bundle.putBoolean(IS_ARROW_HIDDEN, isArrowHidden);
        bundle.putInt(ARROW_DRAWABLE_RES_ID, arrowDrawableResId);
        if (popupWindow != null) {
            bundle.putBoolean(IS_POPUP_SHOWING, popupWindow.isShowing());
        }
        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable savedState) {
        if (savedState instanceof Bundle) {
            Bundle bundle = (Bundle) savedState;
            selectedIndex = bundle.getInt(SELECTED_INDEX);
            if (adapter != null) {
                setTextInternal(selectedTextFormatter.format(adapter.getItemInDataset(selectedIndex)).toString());
                adapter.setSelectedIndex(selectedIndex);
            }

            if (bundle.getBoolean(IS_POPUP_SHOWING)) {
                if (popupWindow != null) {
                    // Post the show request into the looper to avoid bad token exception
                    post(this::showDropDown);
                }
            }
            isArrowHidden = bundle.getBoolean(IS_ARROW_HIDDEN, false);
            arrowDrawableResId = bundle.getInt(ARROW_DRAWABLE_RES_ID);
            savedState = bundle.getParcelable(INSTANCE_STATE);
        }
        super.onRestoreInstanceState(savedState);
    }

    private void init(Context context, AttributeSet attrs) {
        Resources resources = getResources();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.EasySpinner);
//        int defaultPadding = resources.getDimensionPixelSize(R.dimen.one_and_a_half_grid_unit);
        int defaultPadding = 0;

        setGravity(Gravity.CENTER_VERTICAL | Gravity.START);
//        setPadding(resources.getDimensionPixelSize(R.dimen.three_grid_unit), defaultPadding, defaultPadding, defaultPadding);
        setClickable(true);
        spinner_backgroundSelector = typedArray.getResourceId(R.styleable.EasySpinner_spinner_backgroundSelector, R.drawable.easyspinner_selector);
        item_backgroundSelector = typedArray.getResourceId(R.styleable.EasySpinner_spinner_item_backgroundSelector, R.drawable.easyspinner_selector);
        setBackgroundResource(spinner_backgroundSelector);
        textColor = typedArray.getColor(R.styleable.EasySpinner_spinner_textTint, getDefaultTextColor(context));
//        setTextColor(textColor);
        popupWindow = new ListPopupWindow(context);
        popupWindow.setBackgroundDrawable(null);//取消阴影动画
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // `The selected item is not displayed within the list, so when the selected position is equal to
                // the one of the currently selected item it gets shifted to the next item.`
//                if (position >= selectedIndex && position < adapter.getCount()) {
//                    position++;
//                }
                selectedIndex = position;

                if (onSpinnerItemSelectedListener != null) {
                    onSpinnerItemSelectedListener.onItemSelected(EasySpinner.this, view, position, id);
                }

                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(parent, view, position, id);
                }

                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(parent, view, position, id);
                }

                adapter.setSelectedIndex(position);

                setTextInternal(adapter.getItemInDataset(position));

                dismissDropDown();
            }
        });

        popupWindow.setModal(true);

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (!isArrowHidden) {
                    animateArrow(false);
                }
            }
        });

        isArrowHidden = typedArray.getBoolean(R.styleable.EasySpinner_spinner_hideArrow, false);
        arrowDrawableTint = typedArray.getColor(R.styleable.EasySpinner_spinner_arrowTint, getResources().getColor(android.R.color.black));
        arrowDrawableResId = typedArray.getResourceId(R.styleable.EasySpinner_spinner_arrowDrawable, R.drawable.easyspinner_arrow);
        spinner_title_margin = typedArray.getDimensionPixelSize(R.styleable.EasySpinner_spinner_title_margin, 0);
        maxNumber = typedArray.getInt(R.styleable.EasySpinner_spinner_maxNumber, 6);
        popupWindow.setVerticalOffset(spinner_title_margin);

        horizontalAlignment = TextAlignment.fromId(typedArray.getInt(R.styleable.EasySpinner_spinner_popupTextAlignment, TextAlignment.CENTER.ordinal()));

        CharSequence[] entries = typedArray.getTextArray(R.styleable.EasySpinner_spinner_entries);
        if (entries != null) {
            attachDataSource(Arrays.asList(entries), false);
        }

        defaultText = typedArray.getString(R.styleable.EasySpinner_spinner_default_text);
        if (defaultText != null) {
            setText(defaultText);
        }

        typedArray.recycle();

        measureDisplayHeight();

    }

    private void measureDisplayHeight() {
        displayHeight = getContext().getResources().getDisplayMetrics().heightPixels;
    }

    private int getParentVerticalOffset() {
        if (parentVerticalOffset > 0) {
            return parentVerticalOffset;
        }
        int[] locationOnScreen = new int[2];
        getLocationOnScreen(locationOnScreen);
        return parentVerticalOffset = locationOnScreen[VERTICAL_OFFSET];
    }

    @Override
    protected void onDetachedFromWindow() {
        if (arrowAnimator != null) {
            arrowAnimator.cancel();
        }
        super.onDetachedFromWindow();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            onVisibilityChanged(this, getVisibility());
        }
    }

    @Override
    protected void onVisibilityChanged(View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        arrowDrawable = initArrowDrawable(arrowDrawableTint);
        setArrowDrawableOrHide(arrowDrawable);
    }

    private Drawable initArrowDrawable(int drawableTint) {
        if (arrowDrawableResId == 0) return null;
        Drawable drawable = ContextCompat.getDrawable(getContext(), arrowDrawableResId);
        if (drawable != null) {
            // Gets a copy of this drawable as this is going to be mutated by the animator
            drawable = DrawableCompat.wrap(drawable).mutate();
            if (drawableTint != Integer.MAX_VALUE && drawableTint != 0) {
                DrawableCompat.setTint(drawable, drawableTint);
            }
        }
        return drawable;
    }

    private void setArrowDrawableOrHide(Drawable drawable) {
        if (!isArrowHidden && drawable != null) {
            setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    private int getDefaultTextColor(Context context) {
        TypedValue typedValue = new TypedValue();
        context.getTheme()
                .resolveAttribute(android.R.attr.textColorPrimary, typedValue, true);
        TypedArray typedArray = context.obtainStyledAttributes(typedValue.data,
                new int[]{android.R.attr.textColorPrimary});
        int defaultTextColor = typedArray.getColor(0, Color.BLACK);
        typedArray.recycle();
        return defaultTextColor;
    }

    public Object getItemAtPosition(int position) {
        return adapter.getItemInDataset(position);
    }

    public Object getSelectedItem() {
        return adapter.getItemInDataset(selectedIndex);
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

    public void setArrowDrawable(@DrawableRes @ColorRes int drawableId) {
        arrowDrawableResId = drawableId;
        arrowDrawable = initArrowDrawable(R.drawable.easyspinner_arrow);
        setArrowDrawableOrHide(arrowDrawable);
    }

    public void setArrowDrawable(Drawable drawable) {
        arrowDrawable = drawable;
        setArrowDrawableOrHide(arrowDrawable);
    }

    private void setTextInternal(Object item) {
        if (selectedTextFormatter != null) {
            setText(selectedTextFormatter.format(item));
        } else {
            setText(item.toString());
        }
    }

    /**
     * Set the default spinner item using its index
     *
     * @param position the item's position
     */
    public void setSelectedIndex(int position) {
        if (adapter != null) {
            if (position >= 0 && position <= adapter.getCount()) {
                adapter.setSelectedIndex(position);
                selectedIndex = position;
                setTextInternal(selectedTextFormatter.format(adapter.getItemInDataset(position)).toString());
            } else {
                throw new IllegalArgumentException("Position must be lower than adapter count!");
            }
        }
    }


    /**
     * @deprecated use setOnSpinnerItemSelectedListener instead.
     */
    @Deprecated
    public void addOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * @deprecated use setOnSpinnerItemSelectedListener instead.
     */
    @Deprecated
    public void setOnItemSelectedListener(AdapterView.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    /**
     * 关联数据
     *
     * @param list
     * @param isShowFirstLine 是否将数据的第一个显示在spinner上，默认false
     * @param <T>
     */
    public <T> void attachDataSource(@NonNull List<T> list, boolean isShowFirstLine) {
        adapter = new EasySpinnerAdapter<>(getContext(), list, textColor, item_backgroundSelector, spinnerTextFormatter, horizontalAlignment);
        setAdapterInternal(adapter, isShowFirstLine);
    }

    public void setAdapter(ListAdapter adapter) {
        this.adapter = new EasySpinnerAdapterWrapper(getContext(), adapter, textColor, item_backgroundSelector, spinnerTextFormatter, horizontalAlignment);
        setAdapterInternal(this.adapter, false);
    }

    public TextAlignment getPopUpTextAlignment() {
        return horizontalAlignment;
    }

    private <T> void setAdapterInternal(EasySpinnerBaseAdapter<T> adapter, boolean isShowFirstLine) {
        if (adapter.getCount() >= 0) {
            // If the adapter needs to be set again, ensure to reset the selected index as well
            selectedIndex = 0;
            popupWindow.setAdapter(adapter);
            if (isShowFirstLine) {
                setTextInternal(adapter.getItemInDataset(selectedIndex));
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (isEnabled() && event.getAction() == MotionEvent.ACTION_UP) {
            if (adapter != null && !popupWindow.isShowing() && adapter.getCount() > 0) {
                showDropDown();
            } else {
                dismissDropDown();
            }
        }
        return super.onTouchEvent(event);
    }

    private void animateArrow(boolean shouldRotateUp) {
        int start = shouldRotateUp ? 0 : MAX_LEVEL;
        int end = shouldRotateUp ? MAX_LEVEL : 0;
        arrowAnimator = ObjectAnimator.ofInt(arrowDrawable, "level", start, end);
        arrowAnimator.setInterpolator(new LinearOutSlowInInterpolator());
        arrowAnimator.start();
    }

    public void dismissDropDown() {
        if (!isArrowHidden) {
            animateArrow(false);
        }
        popupWindow.dismiss();
    }

    @SuppressLint("RestrictedApi")
    public void showDropDown() {
        if (!isArrowHidden) {
            animateArrow(true);
        }
        popupWindow.setAnchorView(this);
        popupWindow.setOverlapAnchor(false);
        popupWindow.show();
        final ListView listView = popupWindow.getListView();
        if (listView != null) {
//            listView.setVerticalScrollBarEnabled(false);
//            listView.setHorizontalScrollBarEnabled(false);
//            listView.setVerticalFadingEdgeEnabled(false);
//            listView.setHorizontalFadingEdgeEnabled(false);
//            listView.setCacheColorHint(Color.TRANSPARENT);
            setListViewHeight(popupWindow.getListView(), maxNumber);
        }
    }


    private int getPopUpHeight() {
        return Math.max(verticalSpaceBelow(), verticalSpaceAbove());
    }

    private int verticalSpaceAbove() {
        return getParentVerticalOffset();
    }

    private int verticalSpaceBelow() {
        return displayHeight - getParentVerticalOffset() - getMeasuredHeight();
    }

    public void setTintColor(@ColorRes int resId) {
        if (arrowDrawable != null && !isArrowHidden) {
            DrawableCompat.setTint(arrowDrawable, ContextCompat.getColor(getContext(), resId));
        }
    }

    public void setArrowTintColor(int resolvedColor) {
        if (arrowDrawable != null && !isArrowHidden) {
            DrawableCompat.setTint(arrowDrawable, resolvedColor);
        }
    }

    public void hideArrow() {
        isArrowHidden = true;
        setArrowDrawableOrHide(arrowDrawable);
    }

    public void showArrow() {
        isArrowHidden = false;
        setArrowDrawableOrHide(arrowDrawable);
    }

    public boolean isArrowHidden() {
        return isArrowHidden;
    }

    public void setSpinner_title_margin(int paddingBottom) {
        spinner_title_margin = paddingBottom;
    }

    public int getSpinner_title_margin() {
        return spinner_title_margin;
    }

    public void setSpinnerTextFormatter(SpinnerTextFormatter spinnerTextFormatter) {
        this.spinnerTextFormatter = spinnerTextFormatter;
    }

    public void setSelectedTextFormatter(SpinnerTextFormatter textFormatter) {
        this.selectedTextFormatter = textFormatter;
    }


    public void performItemClick(int position, boolean showDropdown) {
        if (showDropdown) showDropDown();
        setSelectedIndex(position);
    }

    /**
     * only applicable when popup is shown .
     *
     * @param view
     * @param position
     * @param id
     */
    public void performItemClick(View view, int position, int id) {
        showDropDown();
        final ListView listView = popupWindow.getListView();
        if (listView != null) {
            listView.performItemClick(view, position, id);
        }
    }

    public OnSpinnerItemSelectedListener getOnSpinnerItemSelectedListener() {
        return onSpinnerItemSelectedListener;
    }

    public void setOnSpinnerItemSelectedListener(OnSpinnerItemSelectedListener onSpinnerItemSelectedListener) {
        this.onSpinnerItemSelectedListener = onSpinnerItemSelectedListener;
    }

    public void setListViewHeight(ListView listView, int maxNumber) {
        ListAdapter listAdapter = listView.getAdapter(); //得到ListView 添加的适配器
        if (listAdapter == null) {
            return;
        }

        View itemView = listAdapter.getView(0, null, listView); //获取其中的一项
        //进行这一项的测量
        itemView.measure(0, 0);
        int itemHeight = itemView.getMeasuredHeight(); //一项的高度
        int itemCount = listAdapter.getCount();//得到总的项数
        ViewGroup.LayoutParams layoutParams = listView.getLayoutParams(); //进行布局参数的设置
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;

        if (itemCount <= maxNumber) {
//            if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
//                layoutParams.set
//            }
            layoutParams.height = itemHeight * itemCount;
//            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * itemCount);
        } else if (itemCount > maxNumber) {
            layoutParams.height = itemHeight * maxNumber;
//            layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, itemHeight * 6);
        }
        listView.setLayoutParams(layoutParams);

    }
}