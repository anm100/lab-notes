package ui.appointments;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;

import Client.Resources;
import Controllers.AppointmentsController;
import Controllers.ReferralController;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import models.Confirmation;
import models.Examination;
import models.Patient;
import models.Referral;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ui.main.Identification;
import ui.utils.Messages;
import ui.utils.MyTableModel;
import ui.utils.UITests;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JEditorPane;

/**
 * Add new Confirmation GUI for patient to specific specialty 
 * 
 * @author Ahmad Mnasra
 *
 */
public class NewConfirmUI {
	/**
	 * current frame
	 */
	private JFrame NewConfirmUI;
	private AppointmentsController app_ctrl = new AppointmentsController();
	private ReferralController RefCtrl = new ReferralController();
	private JTextField field_Name;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNext;
	private JTable tblToday;
	/**
	 * shows all specialty 
	 */
	private JComboBox speciality_cbox;
	private JEditorPane editorPane;
	private JLabel msqlbl_1;
	private JLabel msqlbl_2;
	private JLabel msqlbl_3;
	private JLabel lblNewLabel; 
	private JLabel note;
	private JLabel msgerror1; 
	private JLabel msgerror2; 
	private JLabel msgerror3; 
	
	private boolean flag_lock;

	public NewConfirmUI(Patient p) {
		initialize(p);
		NewConfirmUI.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Patient p) {
		NewConfirmUI = new JFrame();
		NewConfirmUI.setTitle("New Conformation - GHealth");
		NewConfirmUI.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		NewConfirmUI.setIconImage(icon);
		NewConfirmUI.setForeground(Color.BLACK);
		NewConfirmUI.setFont(new Font("Dialog", Font.PLAIN, 16));
		NewConfirmUI.setBackground(Color.WHITE);
		NewConfirmUI.getContentPane().setBackground(Color.WHITE);
		NewConfirmUI.getContentPane().setLayout(null);

		JLabel logo = new JLabel("New Refferal and conformation");
		logo.setBounds(-33, -11, 412, 80);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		NewConfirmUI.getContentPane().add(logo);

		JButton btnFinish = new JButton("Finish");
		btnFinish.setEnabled(false);
		btnFinish.setBounds(271, 436, 89, 23);
		NewConfirmUI.getContentPane().add(btnFinish);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int result = Messages.confirmMessage("Are you sure you want to cancel?", "GHealth", null);
				if (result == JOptionPane.YES_OPTION)
					NewConfirmUI.dispose();
			}
		});
		btnCancel.setBounds(30, 436, 89, 23);
		NewConfirmUI.getContentPane().add(btnCancel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 100, 350, 325);
		NewConfirmUI.getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Add Referral", null, panel, null);
		tabbedPane.setEnabledAt(0, false);

		panel.setLayout(null);

		JLabel label_1 = new JLabel("Description:");
		label_1.setBounds(7, 111, 71, 14);
		panel.add(label_1);

		field_Name = new JTextField();
		field_Name.setColumns(10);
		field_Name.setBounds(75, 61, 200, 25);
		panel.add(field_Name);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 111, 200, 141);
		panel.add(scrollPane);
		
				editorPane = new JEditorPane();
				scrollPane.setViewportView(editorPane);
			/**
			 * Combobox handler to choose specific specialty use getDoctorsBySpec to
			 * add confirmation to  specialty(Selected) for  patient 
			 * and checks if confirmation is active show message and fields is locked 
			 * else, permit for user to add new confirmation 
			 */
		speciality_cbox = new JComboBox(AppointmentsController.getSpecialties());
		speciality_cbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flag_lock = false;

				field_Name.setText("");
				editorPane.setText("");
				textField_1.setText("");
				textField_2.setText("");
				textField_3.setText("");
				field_Name.setEditable(true);
				editorPane.setEditable(true);
				textField_1.setEditable(true);
				textField_2.setEditable(true);
				textField_3.setEditable(true);
				note.setText("");
				Referral ref;

				if ((ref = referalExit()) != null) {
					field_Name.setText(ref.getDoctor_name());
					editorPane.setText(ref.getDescription());
					textField_1.setText(ref.getConfirmation().getHmo_id());
					textField_2.setText(ref.getConfirmation().getApproval_id());
					textField_3.setText(ref.getConfirmation().getRefferal_id());
					field_Name.setEditable(false);
					textField_1.setEditable(false);
					textField_2.setEditable(false);
					textField_3.setEditable(false);
					flag_lock = true;
					return;
				}

			}
			/**
			 *This private method search for active Referral to
			 * selected specialty 
			 */
			private Referral referalExit() {
				for (Referral ref : p.getReferrals()) {
					if (ref.isActive() == true
							&& ref.getSpeciality().compareTo((String) speciality_cbox.getSelectedItem()) == 0) {
						note.setText("A simillar referral already exist");
						return ref;
					}
				}
				return null;
			}
		});
		speciality_cbox.setName("");
		speciality_cbox.setBounds(75, 11, 200, 25);
		panel.add(speciality_cbox);

		JLabel label_2 = new JLabel("Doctor Name:");
		label_2.setBounds(7, 66, 77, 14);
		panel.add(label_2);

		JLabel lblSpeciality = new JLabel("speciality:");
		lblSpeciality.setBounds(7, 16, 71, 14);
		panel.add(lblSpeciality);

		note = new JLabel("");
		note.setForeground(Color.RED);
		note.setFont(new Font("Tahoma", Font.PLAIN, 11));
		note.setBounds(68, 251, 203, 13);
		panel.add(note);

		msqlbl_2 = new JLabel("");
		msqlbl_2.setForeground(Color.RED);
		msqlbl_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		msqlbl_2.setBounds(75, 89, 127, 14);
		panel.add(msqlbl_2);

		msqlbl_1 = new JLabel("");
		msqlbl_1.setForeground(Color.RED);
		msqlbl_1.setBounds(75, 41, 102, 14);
		panel.add(msqlbl_1);
		
		JLabel lblNewLabel = new JLabel("(Doctor from HMO)");
		lblNewLabel.setForeground(Color.DARK_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel.setBounds(198, 80, 91, 32);
		panel.add(lblNewLabel);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Add confirm", null, panel_1, null);
		tabbedPane.setEnabledAt(1, false);
		panel_1.setLayout(null);

		JLabel label_5 = new JLabel("Refferal Num:");
		label_5.setBounds(4, 70, 82, 14);
		panel_1.add(label_5);

		JLabel label_6 = new JLabel("Approval Num:");
		label_6.setBounds(4, 110, 96, 14);
		panel_1.add(label_6);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(83, 26, 187, 25);
		panel_1.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(83, 106, 187, 25);
		panel_1.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(83, 66, 187, 25);
		panel_1.add(textField_3);

		JLabel label_7 = new JLabel("HMO ID : ");
		label_7.setBounds(4, 30, 82, 14);
		panel_1.add(label_7);
		
				msqlbl_3 = new JLabel("");
				msqlbl_3.setBounds(235, 90, 127, 14);
				panel_1.add(msqlbl_3);
				
				 msgerror1 = new JLabel("");
				msgerror1.setForeground(Color.RED);
				msgerror1.setBounds(83, 50, 187, 14);
				panel_1.add(msgerror1);
				
				msgerror2 = new JLabel("");
				msgerror2.setForeground(Color.RED);
				msgerror2.setBounds(83, 91, 187, 14);
				panel_1.add(msgerror2);
				
				msgerror3 = new JLabel("");
				msgerror3.setForeground(Color.RED);
				msgerror3.setBounds(83, 133, 187, 14);
				panel_1.add(msgerror3);

		JLabel lblPatientId = new JLabel("Patient:");
		lblPatientId.setBounds(10, 69, 66, 14);
		NewConfirmUI.getContentPane().add(lblPatientId);

		JLabel label_id = new JLabel((String) p.getFirstName() + " " + p.getLastName() + " - " + p.getSid());
		label_id.setBounds(85, 64, 275, 25);
		NewConfirmUI.getContentPane().add(label_id);

		JButton btnPrev = new JButton("Previous");
		btnPrev.setVisible(false);
		btnPrev.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.setSelectedIndex(0);
				btnNext.setVisible(true);
				btnPrev.setVisible(false);

			}
		});
		btnPrev.setBounds(129, 436, 89, 23);
		NewConfirmUI.getContentPane().add(btnPrev);

		btnNext = new JButton("Next");
		btnNext.setBounds(129, 436, 89, 23);
		NewConfirmUI.getContentPane().add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				msqlbl_1.setText("");
				msqlbl_2.setText("");
				msqlbl_3.setText("");
				note.setText("");

				if (!isvalidRef())
					return;
				btnFinish.setEnabled(true);
				btnNext.setVisible(false);
				btnPrev.setVisible(true);
				tabbedPane.setEnabledAt(1, true);
				tabbedPane.setSelectedIndex(1);

			}

		});
		/**
		 * check if all requirement field is filled . call to
		 * addReferralHMO method in ReferralController to add the
		 * Referral and approval from HMO show success message to user or warning message if patient is exist 
		 */
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				msgerror1.setText("");
				msgerror2.setText("");
				msgerror3.setText("");

				if (!isvalidconf())
					return;
				if (flag_lock == false) {
					Referral ref = new Referral();
					ref.setDoctor_name(field_Name.getText());
					ref.setSpeciality((String) speciality_cbox.getSelectedItem());
					ref.setDescription(editorPane.getText());
					ref.setPatient(p);
					Confirmation conf = new Confirmation();
					conf.setRefferal_id(textField_3.getText());
					conf.setApproval_id(textField_2.getText());
					conf.setHmo_id(textField_1.getText());
					ref.setConfirmation(conf);
					ref.setActive(true);
					RefCtrl.addReferralHMO(ref);
					Messages.successMessage("Confirmation was added successfully", "Success", null);
					NewConfirmUI.dispose();
					new Identification();
					return;
				} else {
					NewConfirmUI.dispose();
					new Identification();
					return;
				}
			}

		});
		NewConfirmUI.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));
		NewConfirmUI.setBounds(100, 100, 376, 496);
		NewConfirmUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	/**
	 *This private method , checks if all requirement field of confirmation is filled correct . return true 
	 * if all fields is valid , else  if one of fields is incorrect input or empty 
	 */
	private Boolean isvalidRef() {
		boolean flag = true;
		msqlbl_2.setBounds(75, 89, 127, 14);
		msqlbl_1.setBounds(75, 41, 102, 14);
		if (UITests.notEmpty((String) speciality_cbox.getSelectedItem()) == false) {
			msqlbl_1.setText("Missing Speciality");
			flag = false;
		}
		if (UITests.notEmpty(field_Name.getText()) == false) {
			msqlbl_2.setText("Missing doctor name");
			flag = false;
		}else if(UITests.checkIsCh(field_Name.getText())== false)
		{
			msqlbl_2.setText("Should only be letters");
			flag = false;
		}
		if (UITests.notEmpty(editorPane.getText()) == false) {
			note.setText("Missing description");
			flag = false;
		}

		return flag;
	}
	/**
	 *This private method , checks if all requirement field of confirmation is filled correct . return true 
	 * if all fields is valid , else  if one of fields is incorrect input or empty 
	 */
	private Boolean isvalidconf() {
		boolean flag = true;

		if (UITests.notEmpty(textField_1.getText()) == false) {
			msgerror1.setText("isempty");
			flag = false;
		}else if (UITests.checkIsDigit(textField_1.getText()) == false){
			msgerror1.setText("should be only digits");
			flag = false;
		}
		if (UITests.notEmpty(textField_2.getText()) == false) {
			msgerror3.setText("isempty");
			flag = false;
		}else if (UITests.checkIsDigit(textField_2.getText()) == false){
			msgerror3.setText("should be only digits");
			flag = false;
		}
		if (UITests.notEmpty(textField_3.getText()) == false) {
			msgerror2.setText("isempty");
			flag = false;
		}else if (UITests.checkIsDigit(textField_3.getText()) == false){
			msgerror2.setText("should be only digits");
			flag = false;
		}
		return flag;
	}
}
