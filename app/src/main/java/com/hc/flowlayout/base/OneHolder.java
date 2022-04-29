package com.hc.flowlayout.base;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.hc.flowlayout.R;

public class OneHolder extends BaseHolder<One>{
    public OneHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void setUpView(One one, int position, BaseAdapter adapter) {
        TextView view = (TextView) getView(R.id.item_test1_tv);
        view.setText(one.getTv());
    }
}
