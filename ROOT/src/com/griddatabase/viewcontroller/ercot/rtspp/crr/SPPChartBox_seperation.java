package com.griddatabase.viewcontroller.ercot.rtspp.crr;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


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

import com.softwarelikeyou.exception.RTSPPException;
import com.softwarelikeyou.facade.RTSPPFacade;
import com.softwarelikeyou.model.entity.rtspp.RTSPP;
import com.softwarelikeyou.util.Util;

public class SPPChartBox_seperation extends Div implements AfterCompose  {
	private static final long serialVersionUID = 1L;

	protected AnnotateDataBinder binder;
	
	private Date date = new Date();
	private String settlementPointA;
	private String settlementPointB;
	private String canonical;
	private static String PACKAGE = "com.softwarelikeyou.model.entity.rtspp";
	
	protected Image image;
	
	@SuppressWarnings("unchecked")
	private Class<? extends RTSPP> getClass(String value) throws ClassNotFoundException {
		return (Class<? extends RTSPP>) Class.forName(value);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void afterCompose() {
		Components.wireVariables(this, this);
		Components.addForwards(this, this);
		
		binder = new AnnotateDataBinder(this);
		binder.loadAll();
	
		if (Executions.getCurrent().getArg() != null) {
			settlementPointA = String.valueOf(Executions.getCurrent().getArg().get("settlementPointA"));
			settlementPointB = String.valueOf(Executions.getCurrent().getArg().get("settlementPointB"));
			try {
			    date = Util.stringToDate(String.valueOf(Executions.getCurrent().getArg().get("date")), "yyyy-MM-dd");
		    }
			catch (ParseException e) {
				e.printStackTrace();
				Messagebox.show("Problem reading date " + date, "Error", Messagebox.OK, Messagebox.NONE);
			}
		}
		
		Calendar start = Calendar.getInstance();
		start.setTime(date);
		start.set(Calendar.HOUR_OF_DAY, 0);
		start.set(Calendar.MINUTE, 0);
		start.set(Calendar.SECOND, 0);
		
		Calendar end = Calendar.getInstance();
		end.setTime(date);
		end.set(Calendar.HOUR_OF_DAY, 23);
		end.set(Calendar.MINUTE, 59);
		end.set(Calendar.SECOND, 59);
		
		try {
	    	canonical = PACKAGE + ".hu.RTSPPHU15M";
			JFreeChart chart = getChart(getDataSet(RTSPPFacade.findPriceChangeBetweenSettlementPoints(getClass(canonical), start.getTime(), end.getTime(), settlementPointA, settlementPointB)));
	
			image.setContent(getImage(chart));
		}
		catch (RTSPPException e) {
			e.printStackTrace();
			Messagebox.show("Could not retrieve data", "Error", Messagebox.OK, Messagebox.NONE);
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
		BufferedImage bi = chart.createBufferedImage(775, 440, BufferedImage.TRANSLUCENT , null);
		byte[] bytes = EncoderUtil.encode(bi, ImageFormat.PNG, true);
		return new AImage(settlementPointA + " - " + settlementPointB, bytes);
	}
	
	private JFreeChart getChart(XYDataset dataSet) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart("Price Separation", "Date", "Difference", dataSet, false, false, false);
        DateAxis axis = (DateAxis) chart.getXYPlot().getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("kk"));
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
	
	private XYDataset getDataSet(Map<Date, Double> map) {
		TimeSeriesCollection dataSet = new TimeSeriesCollection();
		TimeSeries series = new TimeSeries("Difference");
		for (Date key : map.keySet())
			series.addOrUpdate(new Hour(key), map.get(key));
		dataSet.addSeries(series);
		return dataSet;
	}
}
