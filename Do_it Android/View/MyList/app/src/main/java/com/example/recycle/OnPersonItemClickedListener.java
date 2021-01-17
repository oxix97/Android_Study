package com.example.recycle;

import android.view.View;

public interface OnPersonItemClickedListener {

    public void onItemClicked(PersonAdapter.ViewHolder holder, View view, int position);

}
