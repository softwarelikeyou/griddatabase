package com.griddatabase.viewcontroller.ercot.rtlmp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.LegendItemSource;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYItemRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.data.time.Minute;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleAnchor;
import org.jfree.ui.TextAnchor;
import org.zkoss.zkex.zul.impl.JFreeChartEngine;
import org.zkoss.zul.Chart;

import com.softwarelikeyou.model.entity.rtspp.RTSPP;

public class LMPChartEngine extends JFreeChartEngine {
	private static final long serialVersionUID = 1L;
	
	private List<RTSPP> rtspps;
	
	public void setRTSPP(List<RTSPP> list) {
		rtspps = list;
	}
	
	public JFreeChart jfchart;
	
	@Override
    protected boolean prepareJFreeChart(JFreeChart jfchart, Chart chart) {
		this.jfchart = jfchart;
		
        XYPlot plot = (XYPlot) jfchart.getPlot();
        	
        LegendItemSource source = new LegendItemSource() {
           public LegendItemCollection getLegendItems() {
              LegendItemCollection lic = new LegendItemCollection();
              lic.add(new LegendItem("Forecast Prices", Color.RED));
              lic.add(new LegendItem("Settlement Price", Color.BLUE));
              return lic;
           }
        };
        
        jfchart.removeLegend();
        jfchart.addLegend(new LegendTitle(source));
        
        plot.getRenderer().setSeriesPaint(0, Color.RED);
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(1.2f));

        XYDataset sppDataset = getSPPDataset(rtspps);   
        
        XYItemRenderer sppRenderer = new StandardXYItemRenderer();
        sppRenderer.setSeriesPaint(0, Color.BLUE);
        sppRenderer.setSeriesStroke(0, new BasicStroke(1.5f));
        plot.setRenderer(1, sppRenderer);
        plot.setDataset(1, sppDataset);

        plot.setRangeAxisLocation(0, AxisLocation.BOTTOM_OR_RIGHT); 
      
        NumberAxis leftaxis = (NumberAxis) plot.getRangeAxis();
        plot.setRangeAxis(1, leftaxis);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_LEFT); 
        
        final Marker currentEnd = new ValueMarker(System.currentTimeMillis());
        currentEnd.setPaint(Color.black);
        currentEnd.setLabel(">> Forecast");
        currentEnd.setLabelAnchor(RectangleAnchor.TOP_RIGHT);
        currentEnd.setLabelTextAnchor(TextAnchor.TOP_LEFT);
        plot.addDomainMarker(currentEnd);
        
		return false;
	}
	
	public XYDataset getSPPDataset(List<RTSPP> rtspps) {
		Map<Date, Float> map = new TreeMap<Date, Float>();
		for (RTSPP rtspp : rtspps)
			map.put(rtspp.getIntervalDate(), rtspp.getSettlementPointPrice());

		TimeSeriesCollection dataSet = new TimeSeriesCollection();
		TimeSeries series = new TimeSeries("Settlement Price");
		for (Date key : map.keySet())
			series.addOrUpdate(new Minute(key), map.get(key));
		dataSet.addSeries(series);
		return dataSet;
	}
}
