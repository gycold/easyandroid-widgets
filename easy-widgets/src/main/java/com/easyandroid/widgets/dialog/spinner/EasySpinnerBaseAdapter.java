package com.easyandroid.widgets.dialog.spinner;

import android.content.Context;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.easyandroid.widgets.R;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

/**
 * package: com.easyandroid.widgets.dialog.spinner.EasySpinnerBaseAdapter
 * author: gyc
 * description:
 * time: create at 2022/7/6 22:12
 */
public abstract class EasySpinnerBaseAdapter<T> extends BaseAdapter {

  private final TextAlignment horizontalAlignment;
  private final SpinnerTextFormatter spinnerTextFormatter;

  private int textColor;
  private int backgroundSelector;

  int selectedIndex;

  EasySpinnerBaseAdapter(
          Context context,
          int textColor,
          int backgroundSelector,
          SpinnerTextFormatter spinnerTextFormatter,
          TextAlignment horizontalAlignment
  ) {
    this.spinnerTextFormatter = spinnerTextFormatter;
    this.backgroundSelector = backgroundSelector;
    this.textColor = textColor;
    this.horizontalAlignment = horizontalAlignment;
  }

  @Override
  public View getView(int position, @Nullable View convertView, ViewGroup parent) {
    Context context = parent.getContext();
    TextView textView;

    if (convertView == null) {
      convertView = View.inflate(context, R.layout.easyspinner_list_item, null);
      textView = convertView.findViewById(R.id.text_view_spinner);

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        textView.setBackground(ContextCompat.getDrawable(context, backgroundSelector));
      }
      convertView.setTag(new ViewHolder(textView));
    } else {
      textView = ((ViewHolder) convertView.getTag()).textView;
    }

    textView.setText(spinnerTextFormatter.format(getItem(position)));
    textView.setTextColor(textColor);

    setTextHorizontalAlignment(textView);

    return convertView;
  }

  private void setTextHorizontalAlignment(TextView textView) {
    switch (horizontalAlignment) {
      case START:
        textView.setGravity(Gravity.START);
        break;
      case END:
        textView.setGravity(Gravity.END);
        break;
      case CENTER:
        textView.setGravity(Gravity.CENTER_HORIZONTAL);
        break;
    }
  }

  public int getSelectedIndex() {
    return selectedIndex;
  }

  void setSelectedIndex(int index) {
    selectedIndex = index;
  }

  public abstract T getItemInDataset(int position);

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public abstract T getItem(int position);

  @Override
  public abstract int getCount();

  static class ViewHolder {
    TextView textView;

    ViewHolder(TextView textView) {
      this.textView = textView;
    }
  }
}