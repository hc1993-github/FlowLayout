package com.hc.flowlayout.base;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hc.flowlayout.R;

public class TwoHolder extends BaseHolder<Two>{
    public TwoHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(Two two, int position, BaseAdapter adapter) {
        TextView textView = (TextView) getView(R.id.item_test2_tv);
        textView.setText(two.getTv());
    }
}
