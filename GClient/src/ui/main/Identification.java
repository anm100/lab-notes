package ui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import models.Dispatcher;
import models.Doctor;
import models.Patient;
import models.Secretary;
import ui.appointments.AddPatientUI;
import ui.appointments.Appointments;
import ui.appointments.NewConfirmUI;
import ui.medical.DoctorMedicalRecordUI;
import ui.utils.FrameInterface;
import ui.utils.UITests;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Client.Application;
import Client.Resources;
import Controllers.AppointmentsController;
import Controllers.PatientsController;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

/**
 * Identification GUI frame , the user enter patient ID and enter to his
 * appropriate frame ,for example: enters to Appointments window of the patient
 * or the Medical record of the patient
 * 
 * @author Muhamad Igbaria
 *
 */
public class Identification implements FrameInterface {

	/**
	 * current frame
	 */
	private JFrame disID;
	private JTextField IdTxt;
	private JLabel error_lbl;
	private JButton btnNewButton;
	private JTextPane txtpnEnterPatientId;
	private JLabel lblNewLabel_1;
	JButton btnAddNewPatient;

	/**
	 * Patient Controller instance
	 */

	public Identification() {
		initialize();
		disID.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		disID = new JFrame();
		disID.setTitle("Identification- GHealth");
		disID.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		ImageIcon loginImg = Resources.getIcon("loginRow.png");
		disID.setIconImage(icon);
		disID.setForeground(Color.BLACK);
		disID.setFont(new Font("Dialog", Font.PLAIN, 16));
		disID.setBackground(Color.WHITE);
		disID.getContentPane().setBackground(Color.WHITE);
		disID.getContentPane().setLayout(null);

		IdTxt = new JTextField();
		IdTxt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				identificationHandler();
			}
		});
		IdTxt.setFont(new Font("Tahoma", Font.PLAIN, 18));
		IdTxt.setBounds(80, 127, 165, 35);
		disID.getContentPane().add(IdTxt);
		IdTxt.setColumns(10);

		JButton login_btn = new JButton("Enter");
		login_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				identificationHandler();
			}
		});
		login_btn.setBorder(null);
		login_btn.setBackground(Color.WHITE);
		login_btn.setIcon(loginImg);
		login_btn.setBounds(245, 127, 89, 35);
		disID.getContentPane().add(login_btn);

		JLabel logo = new JLabel("Patient Identification");
		logo.setBounds(0, 0, 359, 80);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		disID.getContentPane().add(logo);

		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(80, 102, 269, 25);
		disID.getContentPane().add(error_lbl);

		btnNewButton = new JButton("Exit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				disID.dispose();
			}
		});
		btnNewButton.setBounds(245, 228, 89, 23);
		disID.getContentPane().add(btnNewButton);

		txtpnEnterPatientId = new JTextPane();
		txtpnEnterPatientId.setEditable(false);
		txtpnEnterPatientId.setText("Enter Patient ID ");
		txtpnEnterPatientId.setBounds(80, 167, 200, 35);
		disID.getContentPane().add(txtpnEnterPatientId);

		lblNewLabel_1 = new JLabel("");
		ImageIcon info = Resources.getIcon("info.png");
		lblNewLabel_1.setIcon(info);
		lblNewLabel_1.setBounds(26, 113, 44, 70);
		disID.getContentPane().add(lblNewLabel_1);

		btnAddNewPatient = new JButton("Add Patient");
		btnAddNewPatient.setVisible(false);
		if (Application.user.getClass() == Secretary.class) {
			btnAddNewPatient.setVisible(true);
		}
		btnAddNewPatient.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddPatientUI();
			}
		});
		btnAddNewPatient.setBounds(80, 228, 165, 23);
		disID.getContentPane().add(btnAddNewPatient);

		disID.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));
		disID.setBounds(100, 100, 349, 288);
		disID.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		disID.setLocationRelativeTo(null);

	}

	/**
	 * This private method implement the identification use case ,and create
	 * patient instance if the identification success else it will show error
	 * messages in the GUI Frame used in textField and enter Button handler
	 */
	private void identificationHandler() {
		String id = IdTxt.getText();
		Patient patient;

		error_lbl.setText("");

		if (UITests.notEmpty(id) == false)
			error_lbl.setText("*Please enter patient ID");
		else if (UITests.correctId(id) == false)
			error_lbl.setText("*Please enter 9 digits ID");
		else if ((patient = PatientsController.getById(id)) == null) {
			error_lbl.setText("*Patient does not exist in the system");
		} else {
				
			disID.setVisible(false);
			if (Application.user.getClass() == Doctor.class) {
				String result = (String) AppointmentsController.setAppointmentDone(Application.user.getSid(),
						patient.getSid());
				new DoctorMedicalRecordUI(patient);
					}

			 else if (Application.user.getClass() == Secretary.class)
				 new NewConfirmUI(patient);
			else if (Application.user.getClass() == Dispatcher.class)
				new Appointments(patient).getFrame().setVisible(true);
		}

	}

	/**
	 * @return current JFrame
	 */
	@Override
	public JFrame getFrame() {
		return disID;
	}
}
