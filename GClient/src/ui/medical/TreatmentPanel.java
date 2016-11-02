package ui.medical;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;

import Client.Application;
import Controllers.MedicalRecordController;

import models.Treatment;
import ui.utils.Messages;
import ui.utils.UITests;

import Utils.DateTime;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.ParseException;

import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.JTextArea;

/**
 * present the treatment details option add result to treatment and closing the
 * treatment
 * 
 * @author maisam marjieh
 *
 */
public class TreatmentPanel extends JPanel {
	private JTextField textField_startDate;
	private JTextField textField_endDate;
	private JTextField textField_doctorName;
	private JTextField textField_treatmentName;
	private JLabel lblResult;
	private JScrollPane scrollPane;
	private JButton btnSave;
	private JButton btnAddResult;
	private JTextArea textArea_Result;
	private JLabel error_lbl;
	private JLabel lblEndDate;
	private JButton btnClose;

	/**
	 * construct the panel with treatment details
	 * 
	 * @param treatment
	 *            -should be present
	 * @param doctorMedicalRecordUI
	 *            reference to doctorMedicalRecord GUI
	 */
	public TreatmentPanel(Treatment treatment, DoctorMedicalRecordUI doctorMedicalRecordUI) {

		super();
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Treatment", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBackground(UIManager.getColor("Panel.background"));
		setBounds(new Rectangle(283, 143, 122, 144));
		setLayout(null);

		textField_startDate = new JTextField(DateTime.getDateString(treatment.getStart()));
		textField_startDate.setEditable(false);
		textField_startDate.setBackground(new Color(255, 255, 255));
		textField_startDate.setBounds(131, 136, 211, 20);
		textField_startDate.setColumns(10);
		add(textField_startDate);

		JLabel lblDate = new JLabel("Start Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(9, 135, 101, 20);
		add(lblDate);

		lblEndDate = new JLabel("End Date : ");
		lblEndDate.setVisible(false);
		lblEndDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblEndDate.setBounds(9, 182, 101, 20);
		add(lblEndDate);

		textField_endDate = new JTextField();
		textField_endDate.setVisible(false);
		textArea_Result = new JTextArea();
		scrollPane = new JScrollPane();
		lblResult = new JLabel("Result : ");
		btnAddResult = new JButton("Add Result");
		btnAddResult.setToolTipText("add result to treatment and closing it");

		btnSave = new JButton("Save");
		btnSave.setToolTipText("Save result , close the treatment and send report to doctor (in HMO)");
		if (treatment.isEndFlag()) {
			textField_endDate.setText(DateTime.getDateString(treatment.getEnd()));
			textField_endDate.setVisible(true);
			lblEndDate.setVisible(true);
			textArea_Result.setText(treatment.getStatus());

		}

		textField_endDate.setBackground(Color.WHITE);
		textField_endDate.setEditable(false);
		textField_endDate.setBounds(131, 183, 211, 20);
		add(textField_endDate);
		textField_endDate.setColumns(10);

		JLabel lblDoctorname = new JLabel("DoctorName :");
		lblDoctorname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoctorname.setBounds(9, 86, 119, 27);
		add(lblDoctorname);

		textField_doctorName = new JTextField(
				treatment.getDoctor().getFirstName() + " " + treatment.getDoctor().getLastName());
		textField_doctorName.setBackground(Color.WHITE);
		textField_doctorName.setEditable(false);
		textField_doctorName.setBounds(131, 90, 211, 20);
		add(textField_doctorName);
		textField_doctorName.setColumns(10);

		JLabel lblType = new JLabel("Treatment name :");
		lblType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblType.setBounds(9, 48, 144, 27);
		add(lblType);

		textField_treatmentName = new JTextField(treatment.gettType());
		textField_treatmentName.setBackground(Color.WHITE);
		textField_treatmentName.setEditable(false);
		textField_treatmentName.setBounds(131, 52, 211, 20);
		add(textField_treatmentName);
		textField_treatmentName.setColumns(10);

		/**
		 * Allows to doctor to add result about the treatment and close it @
		 * throws ParseException
		 */
		btnAddResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnAddResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				btnSave.setVisible(true);
				scrollPane.setVisible(true);
				lblResult.setVisible(true);
				btnAddResult.setVisible(false);
				btnClose.setVisible(true);
				lblEndDate.setVisible(true);
				textField_endDate.setVisible(true);
				try {
					textField_endDate.setText(DateTime.getDateString(DateTime.currentDay()));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}

			}
		});
		btnAddResult.setBounds(241, 214, 101, 23);
		add(btnAddResult);
		btnAddResult.setVisible(!treatment.isEndFlag());
		btnAddResult.setEnabled(false);
		if (treatment.getDoctor().getSid().equals(Application.user.getSid())) {
			btnAddResult.setEnabled(true);
		}

		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblResult.setBounds(9, 241, 62, 14);
		add(lblResult);
		lblResult.setVisible(treatment.isEndFlag());

		scrollPane = new JScrollPane();
		scrollPane.setBounds(131, 240, 294, 89);
		add(scrollPane);
		scrollPane.setVisible(treatment.isEndFlag());

		scrollPane.setViewportView(textArea_Result);

		/**
		 * checks if result field is filled . call to updateTreatment method in
		 * MedicalRecordController to update the treatment
		 */
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = textArea_Result.getText();
				error_lbl.setText("");
				if (UITests.notEmpty(str) == false)
					error_lbl.setText("* Please enter Results");
				else {
					treatment.setStatus(str);
					treatment.setEndFlag(true);
					try {
						treatment.setEnd((DateTime.currentDay()));
					} catch (ParseException e1) {
						e1.printStackTrace();
					}
					Messages.successMessage("Treatment Report was added successfully ", "Success",
							doctorMedicalRecordUI.DoctorMedicalRecord);

					setVisible(false);
					MedicalRecordController.sendTreatmentReport(treatment);

				}

			}
		});

		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(131, 214, 269, 27);

		add(error_lbl);
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(173, 366, 89, 23);
		add(btnSave);

		btnClose = new JButton("Close");
		btnClose.setVisible(false);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
			}
		});
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnClose.setBounds(272, 367, 89, 23);
		add(btnClose);
		btnSave.setVisible(false);

	}
}