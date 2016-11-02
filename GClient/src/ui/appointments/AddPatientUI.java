package ui.appointments;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;

import Client.Resources;
import Controllers.MedicalRecordController;
import Controllers.PatientsController;
import models.Patient;
import ui.utils.Messages;
import ui.utils.UITests;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
/**
 * Add new patient GUI 
 * @author Ahmad Mnasra , Bolous Abo Jaber
 *
 */
public class AddPatientUI {

	private JFrame addPatient;
	
	/**
	 * Patient Controller instance
	 */
	private PatientsController idctrl = new PatientsController();

	private JTextField FieldID;
	private JTextField FnameField;
	private JTextField LnameField;
	private JTextField PhoneField;
	private JTextField EmailField;
	private JTextField AddressField;
	private JComboBox comboBox_gender = new JComboBox();

	/**
	 * BirthDate chooses 
	 */
	private JDateChooser chooser= null;

	private JLabel msqlbl;
	private JLabel msqlbl_1;
	private JLabel msqlbl_2;
	private JLabel msqlbl_4;
	private JLabel msqlbl_5;
	private JLabel msqlbl_6;
	private JLabel msqlbl_7;

	public AddPatientUI() {
		initialize();
		addPatient.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		addPatient = new JFrame();
		addPatient.setTitle("Add New Patient- GHealth");
		addPatient.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		addPatient.setIconImage(icon);
		addPatient.setForeground(Color.BLACK);
		addPatient.setFont(new Font("Dialog", Font.PLAIN, 16));
		addPatient.setBackground(Color.WHITE);
		addPatient.getContentPane().setBackground(Color.WHITE);
		addPatient.getContentPane().setLayout(null);

		JTextField textField = new JTextField(15);

		/**
		*JCalendar is a Java date chooser bean for graphically picking a date. 
		*JCalendar is composed of several other Java beans, a JDayChooser,
 		*a JMonthChooser and a JYearChooser. All these beans have a locale property,
 		*Also part of the package is a JDateChooser	
 		*/
		chooser = new JDateChooser();

		
		chooser.enableInputMethods(false);
		chooser.setBackground(Color.GRAY);
		chooser.setLocale(Locale.US);
		chooser.setBounds(83, 166, 252, 26);
		chooser.setVisible(true);
		chooser.addPropertyChangeListener("date", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				JDateChooser chooser = (JDateChooser) evt.getSource();
				SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
				textField.setText(formatter.format(chooser.getDate()));
			}
		});
		JTextFieldDateEditor editor = (JTextFieldDateEditor) chooser.getDateEditor();
		editor.setEditable(false);
		chooser.setDate(new Date());
		
		
	
		addPatient.getContentPane().add(chooser);

		JLabel logo = new JLabel("Add New Patient");
		logo.setBounds(0, 0, 346, 60);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Tahoma", Font.BOLD, 16));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		addPatient.getContentPane().add(logo);

		JLabel lblNewLabel = new JLabel("ID:");
		lblNewLabel.setBounds(22, 71, 64, 35);
		addPatient.getContentPane().add(lblNewLabel);

		JLabel label_1 = new JLabel("First Name:");
		label_1.setBounds(22, 101, 64, 35);
		addPatient.getContentPane().add(label_1);

		JLabel label_2 = new JLabel("Last Name:");
		label_2.setBounds(22, 131, 64, 35);
		addPatient.getContentPane().add(label_2);

		JLabel label_3 = new JLabel("BirthDate:");
		label_3.setBounds(22, 161, 64, 35);
		addPatient.getContentPane().add(label_3);

		JLabel lblPhone = new JLabel("Phone:");
		lblPhone.setBounds(22, 251, 64, 35);
		addPatient.getContentPane().add(lblPhone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(22, 221, 64, 35);
		addPatient.getContentPane().add(lblEmail);

		FieldID = new JTextField();
		FieldID.setBounds(83, 75, 252, 26);
		addPatient.getContentPane().add(FieldID);
		FieldID.setColumns(10);

		FnameField = new JTextField();
		FnameField.setColumns(10);
		FnameField.setBounds(83, 105, 252, 26);

		addPatient.getContentPane().add(FnameField);

		LnameField = new JTextField();
		LnameField.setColumns(10);
		LnameField.setBounds(83, 135, 252, 26);
		addPatient.getContentPane().add(LnameField);

		EmailField = new JTextField();
		EmailField.setColumns(10);
		EmailField.setBounds(83, 225, 252, 26);
		addPatient.getContentPane().add(EmailField);
		/**
		 * check if all requirement field is filled . call to
		 * AddNewPatient method in PatientController to add the
		 * patient show success message to user or warning message if patient is exist 
		 */
		JButton btnNewButton = new JButton("Save");
		btnNewButton.setForeground(Color.BLUE);
		btnNewButton.setBounds(83, 348, 110, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				msqlbl.setText("");
				msqlbl_1.setText("");
				msqlbl_2.setText("");
				msqlbl_4.setText("");
				msqlbl_5.setText("");
				msqlbl_6.setText("");
				msqlbl_7.setText("");

				if (!isvalid()){
					addPatient.setBounds(addPatient.getX(), addPatient.getY(), 500, addPatient.getHeight());
					return;
				}
				addPatient.setBounds(addPatient.getX(), addPatient.getY(), 350, addPatient.getHeight());

				Patient patient = new Patient();
				patient.setSid(FieldID.getText());
				patient.setFirstName(FnameField.getText());
				patient.setLastName(LnameField.getText());
				patient.setEmail(EmailField.getText());
				patient.setPhone(PhoneField.getText());
				patient.setBirthDate(chooser.getDate());
				patient.setGender((String) comboBox_gender.getSelectedItem());
				patient.setAddress(AddressField.getText());
				/**
				 * Patient Controller 
				 */		
				PatientsController.AddNewPatient(patient);
			
				Messages.successMessage("Patient was added successfully", "Success", null);
				addPatient.dispose();
				
				return;
			}

		});
		addPatient.getContentPane().add(btnNewButton);
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int result = Messages.confirmMessage("Are you sure you want to cancel?","GHealth" , null);
				if(result == JOptionPane.YES_OPTION)
						addPatient.dispose();
			}
		});

		btnNewButton_1.setBounds(217, 348, 118, 23);
		addPatient.getContentPane().add(btnNewButton_1);

		JLabel lblAdressess = new JLabel("Address:");
		lblAdressess.setBounds(22, 281, 64, 35);
		addPatient.getContentPane().add(lblAdressess);

		PhoneField = new JTextField();
		PhoneField.setColumns(10);
		PhoneField.setBounds(83, 255, 252, 26);
		addPatient.getContentPane().add(PhoneField);

		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(22, 191, 64, 35);
		addPatient.getContentPane().add(lblGender);
		/**
		 * Combobox  to choose gender : male or female 
		 */	
		comboBox_gender.setModel(new DefaultComboBoxModel(new String[] { "Male", "Female" }));

		comboBox_gender.setBounds(83, 195, 252, 26);
		addPatient.getContentPane().add(comboBox_gender);

		AddressField = new JTextField();
		AddressField.setColumns(10);
		AddressField.setBounds(83, 285, 252, 26);
		addPatient.getContentPane().add(AddressField);

		msqlbl = new JLabel("");
		msqlbl.setForeground(Color.RED);
		msqlbl.setBounds(345, 71, 145, 35);
		addPatient.getContentPane().add(msqlbl);

		msqlbl_1 = new JLabel("");
		msqlbl_1.setForeground(Color.RED);
		msqlbl_1.setBounds(345, 101, 145, 35);
		addPatient.getContentPane().add(msqlbl_1);

		msqlbl_2 = new JLabel("");
		msqlbl_2.setForeground(Color.RED);
		msqlbl_2.setBounds(345, 131, 145, 35);
		addPatient.getContentPane().add(msqlbl_2);

		msqlbl_4 = new JLabel("");
		msqlbl_4.setForeground(Color.RED);
		msqlbl_4.setBounds(345, 191, 145, 35);
		addPatient.getContentPane().add(msqlbl_4);

		msqlbl_5 = new JLabel("");
		msqlbl_5.setForeground(Color.RED);
		msqlbl_5.setBounds(345, 221, 155, 35);
		addPatient.getContentPane().add(msqlbl_5);

		msqlbl_6 = new JLabel("");
		msqlbl_6.setForeground(Color.RED);
		msqlbl_6.setBounds(345, 251, 145, 35);
		addPatient.getContentPane().add(msqlbl_6);

		msqlbl_7 = new JLabel("");
		msqlbl_7.setForeground(Color.RED);
		msqlbl_7.setBounds(345, 281, 145, 35);
		addPatient.getContentPane().add(msqlbl_7);

		addPatient.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));
		addPatient.setBounds(100, 100, 352, 402);
		addPatient.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
	/**

	 */
	/**
	 * 
	 * @return
	 */
	private Boolean isvalid() {
		boolean flag = true;
		if (UITests.notEmpty(FieldID.getText()) == false) {
			msqlbl.setText("*Please enter patient ID");
			flag = false;
		} else if (UITests.correctId(FieldID.getText()) == false) {
			msqlbl.setText("*ID should be 9 digit");
			flag = false;

		} else if (PatientsController.exists(FieldID.getText())) {
			msqlbl.setText("*is exist");
			flag = false;

		}
		if (UITests.notEmpty(FnameField.getText()) == false) {
			msqlbl_1.setText("*is Empty");
			flag = false;
		} else if (UITests.checkIsCh(FnameField.getText()) == false) {
			msqlbl_1.setText("*should be only Letters");
			flag = false;
		}
		if (UITests.notEmpty(LnameField.getText()) == false) {
			msqlbl_2.setText("*is Empty");
			flag = false;
		} else if (UITests.checkIsCh(LnameField.getText()) == false) {
			msqlbl_2.setText("*should only be Letters ");
			flag = false;
		}
		if (UITests.notEmpty(PhoneField.getText()) == false) {
			msqlbl_6.setText("*is Empty");
			flag = false;

		} else if (UITests.checkIsDigit(PhoneField.getText()) == false) {
			msqlbl_6.setText("*should only be digits");
			flag = false;
		}
		if (UITests.notEmpty(EmailField.getText()) == false) {
			msqlbl_5.setText("*is Empty");
			flag = false;
		}
		
		if (UITests.notEmpty(AddressField.getText()) == false) {
			msqlbl_7.setText("*is Empty");
			flag = false;
		}
		return flag;
	}
	
}
