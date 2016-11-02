package ui.medical;

import java.awt.EventQueue;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JButton;

import Client.Application;
import Client.Config;
import Client.Resources;
import Controllers.AppointmentsController;
import Controllers.MedicalRecordController;

import javax.swing.BoxLayout;

import java.awt.GridLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

import models.Doctor;
import models.MedicalRecord;
import models.Treatment;
import models.Visit;
import ui.utils.Messages;
import ui.utils.UITests;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Utils.DateTime;

import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.DropMode;
import javax.swing.border.TitledBorder;

/**
 * public class NewTreatmentUI Presentation of the treatment form to be filled
 * by a doctor
 * 
 * @author maisam marjieh
 *
 */
public class NewTreatmentUI extends JPanel {

	private JTextField textField_1;
	private JLabel lblDate;
	private JLabel lblType;
	private JButton btnCancel;
	private JButton btnOk_1;
	private JLabel error_lbl;
	private JTextField textField_4;
	/**
	 * The treatment will be added
	 */
	private Treatment treatment;

	/**
	 * construct the panel
	 * 
	 * @param mr
	 *            - medical record
	 * @param doctorMedicalRecordUI
	 */
	public NewTreatmentUI(MedicalRecord mr, DoctorMedicalRecordUI doctorMedicalRecordUI) {

		super();
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New Treatment", TitledBorder.CENTER,
				TitledBorder.TOP, null, Color.BLACK));
		setBackground(UIManager.getColor("Panel.background"));
		setBounds(new Rectangle(283, 143, 571, 300));
		setLayout(null);

		treatment = new Treatment();
		treatment.setDoctor((Doctor) Application.user);

		try {
			treatment.setStart(DateTime.currentDate());
		} catch (Exception e) {
			Config.getConfig().getLogger().exception(e);
		}
		treatment.setMedicalRecord(mr);

		lblDate = new JLabel("Start Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(10, 42, 101, 20);
		add(lblDate);

		textField_1 = new JTextField(DateTime.getDateString(treatment.getStart()));
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);

		textField_1.setBounds(121, 43, 215, 20);
		textField_1.setColumns(10);
		add(textField_1);

		lblType = new JLabel("Treatment Name :");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(10, 103, 144, 20);
		add(lblType);

		textField_4 = new JTextField();
		textField_4.setBackground(Color.WHITE);

		textField_4.setBounds(121, 104, 215, 20);
		add(textField_4);
		textField_4.setColumns(10);

		btnCancel = new JButton("Close");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);

			}

		});
		btnCancel.setBounds(236, 211, 89, 23);
		add(btnCancel);

		/**
		 * check if all requirement field is filled . call to saveTreatment
		 * method in MedicalRecordController to save the referral call to
		 * updateTree method in doctorMedicalRecordUI to add the new treatment
		 * in tree
		 */
		btnOk_1 = new JButton("Save");
		btnOk_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnOk_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tType = textField_4.getText();
				error_lbl.setText("");
				
				if (UITests.notEmpty(tType) == false)
					error_lbl.setText("*Please enter treatment name");
				else if(!UITests.checkIsCh(tType))
					error_lbl.setText("*Treatment name should be only letters");
					
				else {
					treatment.settType(tType);

					Messages.successMessage("Treatment was added successfully ", "Success",
							doctorMedicalRecordUI.DoctorMedicalRecord);

					Treatment treatmentDB = (Treatment) MedicalRecordController.saveTreatment(treatment);
					

					doctorMedicalRecordUI.updateTree(treatmentDB, true);
					setVisible(false);

				}
			}
		});
		btnOk_1.setBounds(150, 211, 76, 23);
		add(btnOk_1);

		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(121, 74, 269, 27);
		add(error_lbl);

	}
}