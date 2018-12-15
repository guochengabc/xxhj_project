
package com.kongtiaoapp.xxhj.ui.view;

import android.content.Context;
import android.widget.TextView;

import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.Utils;
import com.kongtiaoapp.xxhj.R;


public class ChartMarkerView extends MarkerView {
    private  Context context;
    private TextView tvContent;

    public ChartMarkerView(Context context, int layoutResource) {
        super(context, layoutResource);


        tvContent = (TextView) findViewById(R.id.chart_marker_tv);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {

        if (e instanceof CandleEntry) {

            CandleEntry ce = (CandleEntry) e;

            tvContent.setText("" + Utils.formatNumber(ce.getHigh(), 0, true));
        } else {

//            tvContent.setText("" + Utils.formatNumber(e.getVal(), 0, true));
            tvContent.setText("" +e.getVal());
        }
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 0;
    }


}
