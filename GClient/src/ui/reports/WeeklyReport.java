package ui.reports;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import Client.Application;
import Client.Client;
import Client.Config;
import Client.Resources;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import models.Appointment;
import models.Report;
import models.Statistic;
import ui.utils.MyTableModel;
import Utils.Request;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class WeeklyReport {
	private JFrame weeklyReport;
	private JTable weekly_table;
	private JTable table;
	private Statistic s;
	private Appointment a;
	private int t;
	private JTextField txtPavg;
	private JTextField txtPmax;
	private JTextField txtPmin;
	private JTextField txtPstd;
	private JTextField txtWavg;
	private JTextField txtWmax;
	private JTextField txtWmin;
	private JTextField txtWstd;

	

	public WeeklyReport() {
		initialize();
		weeklyReport.setVisible(true);
	}

	private void initialize() {
		Report r = new Report();
		weeklyReport = new JFrame();
		weeklyReport.getContentPane().setBackground(Color.WHITE);
		weeklyReport.setResizable(false);
		weeklyReport.getContentPane().setLayout(null);
		weeklyReport.setTitle("Weekly Report");
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		weeklyReport.setIconImage(icon);
		JLabel lblChooseDate = new JLabel("Choose Date :");
		lblChooseDate.setForeground(new Color(0, 0, 0));
		lblChooseDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblChooseDate.setBounds(10, 59, 84, 20);
		weeklyReport.getContentPane().add(lblChooseDate);

		JLabel logo = new JLabel("Weekly Report");
		logo.setBounds(0, 0, 439, 50);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		weeklyReport.getContentPane().add(logo);

		JComboBox<String> dates = new JComboBox<String>();
		Date date;

		date = Utils.DateTime.getDate(2016, 1, 3);

		for (int i = 0; i < 54; i++) {
			Date temp = new Date();
			temp.setTime(date.getTime() + 7 * 24 * 60 * 60 * 1000);
			date = temp;
			dates.addItem(Utils.DateTime.getDateString(date));

		}
		dates.addActionListener(new ActionListener() {
			/**
			 * this function set the selected date from the comboBox into the
			 * variable cDate
			 */
			public void actionPerformed(ActionEvent arg0) {
				Date cDate = new Date();
				try {
					cDate = Utils.DateTime.getReportDate((String) dates.getSelectedItem());
				} catch (ParseException e) {
					Config.getConfig().getLogger().exception(e);
				}

				fillWeeklyReport(cDate);

			}
		});

		dates.setForeground(new Color(0, 0, 0));
		dates.setBackground(new Color(192, 192, 192));
		dates.setBounds(104, 59, 335, 22);

		weeklyReport.getContentPane().add(dates);
		weeklyReport.setBounds(100, 100, 464, 511);

		JScrollPane weekly_scrll_table = new JScrollPane();
		weekly_scrll_table.setBounds(10, 90, 430, 240);
		weeklyReport.getContentPane().add(weekly_scrll_table);

		weekly_table = new JTable();
		String[] report_rawNames = { "Day", "Number Of Patients", "Waiting Period" };
		weekly_table.setModel(new MyTableModel(report_rawNames, new Object[][] {}));
		weekly_table.setFillsViewportHeight(true);
		weekly_table.setSurrendersFocusOnKeystroke(true);
		weekly_table.setShowVerticalLines(false);
		weekly_table.setRowHeight(30);
		weekly_table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		weekly_scrll_table.setViewportView(weekly_table);
		weekly_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		weekly_table.setBackground(Color.WHITE);

		txtPavg = new JTextField();
		txtPavg.setEditable(false);
		txtPavg.setBounds(30, 388, 95, 20);
		weeklyReport.getContentPane().add(txtPavg);
		txtPavg.setColumns(10);

		txtPmax = new JTextField();
		txtPmax.setEditable(false);
		txtPmax.setColumns(10);
		txtPmax.setBounds(135, 388, 95, 20);
		weeklyReport.getContentPane().add(txtPmax);

		txtPmin = new JTextField();
		txtPmin.setEditable(false);
		txtPmin.setColumns(10);
		txtPmin.setBounds(240, 388, 95, 20);
		weeklyReport.getContentPane().add(txtPmin);

		txtPstd = new JTextField();
		txtPstd.setEditable(false);
		txtPstd.setColumns(10);
		txtPstd.setBounds(345, 388, 95, 20);
		weeklyReport.getContentPane().add(txtPstd);

		JLabel lblNewLabel_1 = new JLabel("Statistics");
		lblNewLabel_1.setForeground(new Color(30, 144, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 341, 84, 14);
		weeklyReport.getContentPane().add(lblNewLabel_1);

		JLabel lblPatientsStatistics = new JLabel("Number of patients");
		lblPatientsStatistics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPatientsStatistics.setBounds(20, 363, 130, 14);
		weeklyReport.getContentPane().add(lblPatientsStatistics);

		JLabel lblWaitingPeriodStatistics = new JLabel("Waiting period");
		lblWaitingPeriodStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblWaitingPeriodStatistics.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblWaitingPeriodStatistics.setBounds(10, 419, 107, 14);
		weeklyReport.getContentPane().add(lblWaitingPeriodStatistics);

		txtWavg = new JTextField();
		txtWavg.setEditable(false);
		txtWavg.setColumns(10);
		txtWavg.setBounds(30, 444, 93, 20);
		weeklyReport.getContentPane().add(txtWavg);

		txtWmax = new JTextField();
		txtWmax.setEditable(false);
		txtWmax.setColumns(10);
		txtWmax.setBounds(135, 444, 93, 20);
		weeklyReport.getContentPane().add(txtWmax);

		txtWmin = new JTextField();
		txtWmin.setEditable(false);
		txtWmin.setColumns(10);
		txtWmin.setBounds(240, 444, 93, 20);
		weeklyReport.getContentPane().add(txtWmin);

		txtWstd = new JTextField();
		txtWstd.setEditable(false);
		txtWstd.setColumns(10);
		txtWstd.setBounds(345, 444, 93, 20);
		weeklyReport.getContentPane().add(txtWstd);
		weekly_table.setVisible(true);
		weeklyReport.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));

		weeklyReport.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		weeklyReport.setLocationRelativeTo(null);
	}

	/**
	 * this function gets date and fills the Statistics of weekly report
	 * 
	 * @param d
	 */
	public void fillWeeklyReport(Date d) {
		Report report;
		Request r = new Request("reports/getWeeklyReport");
		r.addParam("date", d);
		report = (Report) Application.client.sendRequest(r);

		DefaultTableModel dm = (DefaultTableModel) weekly_table.getModel();
		dm.setNumRows(0);
		for (Statistic s : report.getStatistic())
			dm.addRow(new Object[] { Utils.DateTime.getDateString(s.getDate()), s.getNumOfPatients(),
					s.getWaitingPeriod() });
		fillTexts(report);
	}

	private void fillTexts(Report report) {
		txtPavg.setText(String.format("Average: %.2f", report.getpAvg()));
		txtWavg.setText(String.format("Average: %.2f", report.getwAvg()));

		txtPmax.setText(String.format("Max: %d", report.getpMax()));
		txtWmax.setText(String.format("Max: %d", report.getwMax()));

		txtPmin.setText(String.format("Min: %d", report.getpMin()));
		txtWmin.setText(String.format("Min: %d", report.getwMin()));

		txtPstd.setText(String.format("StdDev: %.2f", report.getpStd()));
		txtWstd.setText(String.format("StdDev: %.2f", report.getwStd()));

	}

	public JFrame getFrame() {
		return weeklyReport;
	}
}
