package com.softwarelikeyou.viewcontroller.ercot;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.zkoss.zkex.zul.impl.JFreeChartEngine;
import org.zkoss.zul.Chart;

public class CandlestickChartEngine extends JFreeChartEngine {
	private static final long serialVersionUID = 1L;

    protected boolean prepareJFreeChart(JFreeChart jfchart, Chart chart) {
        XYPlot xyplot = (XYPlot) jfchart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setUpperMargin(0.0D);
        numberaxis.setLowerMargin(0.0D);
        return false;
    }
}
