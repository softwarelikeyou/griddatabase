package com.griddatabase.viewcontroller.ercot.rtspp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickMarkPosition;
import org.jfree.chart.encoders.EncoderUtil;
import org.jfree.chart.encoders.ImageFormat;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Hour;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.RectangleInsets;
import org.zkoss.image.AImage;
import org.zkoss.zk.ui.Components;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zkplus.databind.AnnotateDataBinder;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Messagebox;

import com.griddatabase.model.cache.RTSPPCache;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;

public class SPPChartBox extends Div implements AfterCompose  {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	private String type;
	
	protected Image image;

	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
	
		if (Executions.getCurrent().getArg() != null) {
			type = String.valueOf(Executions.getCurrent().getArg().get("type"));
		}
		
		try {
			JFreeChart chart = getChart(getDataSet(type));
			image.setContent(getImage(chart));
		}
		catch (IOException e) {
            e.printStackTrace();
			Messagebox.show("Could not create chart", "Error", Messagebox.OK, Messagebox.NONE);
		}
		catch (Exception e) {
			e.printStackTrace();
			Messagebox.show(e.getCause().getMessage(), "Error", Messagebox.OK, Messagebox.NONE);
		}
	}
	
	private AImage getImage(JFreeChart chart) throws IOException {
		BufferedImage bi = chart.createBufferedImage(1000, 440, BufferedImage.TRANSLUCENT , null);
		byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);
		return new AImage(type, bytes);
	}
	
	private JFreeChart getChart(XYDataset dataSet) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(type + " Settlement Point Prices ", "Hour", "Price", dataSet, true, false, false);
        DateAxis axis = (DateAxis) chart.getXYPlot().getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("kk:mm"));
        axis.setTickMarkPosition(DateTickMarkPosition.MIDDLE);
        chart.setBackgroundPaint(Color.WHITE);
        XYPlot plot = (XYPlot) chart.getPlot();
        plot.getRenderer().setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setBackgroundPaint(Color.WHITE);
        plot.setDomainGridlinePaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.WHITE);
        plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
        plot.setDomainCrosshairVisible(true);
        plot.setRangeCrosshairVisible(true);

		return chart;
	}
	
	private XYDataset getDataSet(String type) {
		TimeSeriesCollection dataSet = new TimeSeriesCollection();
		if (type == null)
			return dataSet;
		for (String name : RTSPPCache.getNamesByType(type)) {
			Map<Date, Float> map = new TreeMap<Date, Float>();
			for (RTSPP rtspp : RTSPPCache.getList(name))
				map.put(rtspp.getIntervalDate(), rtspp.getSettlementPointPrice());
			TimeSeries series = new TimeSeries(name);
			for (Date key : map.keySet())
				series.addOrUpdate(new Hour(key), map.get(key));
			dataSet.addSeries(series);
		}
		return dataSet;
	}
}
