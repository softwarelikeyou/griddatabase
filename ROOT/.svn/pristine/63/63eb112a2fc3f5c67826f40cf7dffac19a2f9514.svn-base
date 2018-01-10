package com.griddatabase.viewcontroller.ercot.forcast;

import java.awt.BasicStroke;
import java.awt.Color;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.HighLowRenderer;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.zkoss.zkex.zul.impl.JFreeChartEngine;
import org.zkoss.zul.Chart;

public class CandlestickChartEngine extends JFreeChartEngine {
	private static final long serialVersionUID = 1L;

    protected boolean prepareJFreeChart(JFreeChart jfchart, Chart chart) {
        XYPlot xyplot = (XYPlot) jfchart.getPlot();
        NumberAxis numberaxis = (NumberAxis) xyplot.getRangeAxis();
        numberaxis.setUpperMargin(0.0D);
        numberaxis.setLowerMargin(0.0D);
        xyplot.setRangeAxisLocation(0, AxisLocation.BOTTOM_OR_RIGHT); 
        
        HighLowRenderer renderer = (HighLowRenderer) xyplot.getRenderer();
        renderer.setSeriesStroke(0, new BasicStroke(1.5f));
        renderer.setSeriesPaint(0, new Color(0,0,255));
        renderer.setCloseTickPaint(new Color(0,0,255));
        renderer.setOpenTickPaint(new Color(0,0,255));
        renderer.setTickLength(3);
        xyplot.setRenderer(0, renderer);

        final Marker currentTime = new ValueMarker(System.currentTimeMillis());
        currentTime.setPaint(Color.black);
        currentTime.setStroke(new BasicStroke(2.0f));
        currentTime.setLabel("--> Forecast Prices");
        currentTime.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        currentTime.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        xyplot.addDomainMarker(currentTime);
        
        return false;
    }
}
