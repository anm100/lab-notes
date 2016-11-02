package ui.appointments;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;

import models.Appointment;
import models.Doctor;
import models.Patient;
import ui.main.Identification;
import ui.medical.DoctorMedicalRecordUI;
import ui.utils.Messages;
import ui.utils.MyTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Utils.DateTime;
import Utils.Request;

import com.j256.ormlite.dao.ForeignCollection;

import Client.Application;
import Client.Config;
import Client.Resources;
import Controllers.AppointmentsController;
import Controllers.PatientsController;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Insets;
import java.awt.Point;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.awt.Frame;

/**
 * Appointments window , shows all needed information about specific patient :
 * shows all patient's future appointments sorted in table by appointment time
 * Ascending. option for add new appointment for this patient option to cancel
 * specific appointment.
 * 
 * @author Muhamad Igbaria
 *
 */
public class DoctorAppointments {

	/**
	 * appointments frame
	 */
	private JFrame app;
	/**
	 * all future patient's appointments table
	 */
	private JTable apps_table;

	
	private Doctor doctor;
	private ArrayList<Appointment> apps_list = new ArrayList<Appointment>();
	private AppointmentsController apctrl = new AppointmentsController();
	private DoctorAppointments thisRef = this;


	/**
	 * 
	 * @param doctor : : Models Doctor instance
	 */
	public DoctorAppointments(Doctor doctor) {
		this.doctor = doctor;
		initialize();
		app.setVisible(true);
		app.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		app = new JFrame();
		app.setTitle("Client Appointments - GHealth");
		app.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource(
				"/img/" + "icon.png")).getImage();
		app.setIconImage(icon);
		app.setForeground(Color.BLACK);
		app.setFont(new Font("Tahoma", Font.PLAIN, 16));
		app.setBackground(Color.WHITE);
		app.getContentPane().setBackground(Color.WHITE);
		app.getContentPane().setLayout(null);

		JLabel logo = new JLabel("GHealth - Appointments");
		logo.setBounds(0, 0, 495, 80);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		app.getContentPane().add(logo);

		JPanel panel = new JPanel();
		panel.setBounds(0, 91, 609, 45);
		app.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(10, 11, 46, 21);
		panel.add(lblNewLabel);

		JLabel name_lbl = new JLabel(doctor.getFirstName() + " "
				+ doctor.getLastName());
		name_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name_lbl.setBounds(61, 11, 148, 21);
		panel.add(name_lbl);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPhone.setBounds(205, 11, 67, 21);
		panel.add(lblPhone);

		JLabel phone_lbl = new JLabel(doctor.getPhone());
		phone_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		phone_lbl.setBounds(257, 11, 107, 21);
		panel.add(phone_lbl);

		JLabel lblEmail = new JLabel("E-Mail:");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(374, 11, 46, 21);
		panel.add(lblEmail);

		JLabel mail_lbl = new JLabel(doctor.getEmail());
		mail_lbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		mail_lbl.setBounds(430, 8, 169, 27);
		panel.add(mail_lbl);

		JScrollPane apps_scrollPane = new JScrollPane();
		apps_scrollPane.setBounds(10, 147, 599, 419);
		app.getContentPane().add(apps_scrollPane);

		String[] doc_columnNames = { "ID", "Name", "Time" };
		Object[][] doc_data = {};
		apps_table = new JTable();
		apps_table.setModel(new MyTableModel(doc_columnNames, doc_data));
		getAppointments();
		apps_table.setFillsViewportHeight(true);
		apps_table.setSurrendersFocusOnKeystroke(true);
		apps_table.setShowVerticalLines(false);
		apps_table.setRowHeight(30);
		apps_table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		apps_scrollPane.setViewportView(apps_table);
		apps_table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		apps_table.setBackground(Color.WHITE);

		
		apps_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {

				if (event.getValueIsAdjusting() == true)
					return;
				int index = apps_table.getSelectedRow();

				String sid = (String) apps_table.getModel()
						.getValueAt(index, 0);
				Patient p = PatientsController.getById(sid);
				AppointmentsController.setAppointmentDone(Application.user.getSid(), p.getSid());
				new DoctorMedicalRecordUI(p);
			}});
				

	

		app.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { logo }));
		app.setBounds(100, 100, 625, 694);
		app.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		app.setLocationRelativeTo(null);
	}
		
	/**
	 * 
	 * @return current JFrame
	 */
	public JFrame getFrame() {
		return app;
	}

	/**
	 * add all patient future appointments to appointments table this method get
	 * the appointments from getPatientAppointments in Appointment controller
	 */
	public void getAppointments() {
		DefaultTableModel dm = (DefaultTableModel) apps_table.getModel();
		dm.setRowCount(0);
		String currentDate = null;
		try {
			currentDate = DateTime.getDateString(DateTime.currentDay());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for (Appointment a : doctor.getAppointments()) {
			if (DateTime.getDateString(a.getAppointmentTime()).equals(
					currentDate))
				dm.addRow(new Object[] {
						a.getPatient().getSid(),
						a.getPatient().getFirstName() + " "
								+ a.getPatient().getLastName(),
						DateTime.getTimeString(a.getAppointmentTime()) });
		}
	}
}
	
