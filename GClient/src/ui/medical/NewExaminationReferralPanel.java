package ui.medical;

import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Clinic;
import models.Examination;
import models.Treatment;
import ui.utils.Messages;
import ui.utils.UITests;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Controllers.ExaminationController;
import Controllers.MedicalRecordController;
import Utils.DateTime;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Client.Config;

/**
 * public class NewExaminationReferralPanel Presentation of the referral form to
 * be filled by a doctor
 * 
 * @author maisam marjieh
 *
 */
public class NewExaminationReferralPanel extends JPanel {

	private JTextField textField_1;
	private JLabel error_lbl;
	/**
	 * The selected clinic from list of clinics with labratories
	 */
	private Clinic clinic;
	/**
	 * Examination instance
	 */
	private Examination examination;

	/**
	 * The selected examination type from list of examination type
	 */
	private String exType;

	/**
	 * the examination referral comment
	 */
	private String comment;

	/**
	 * create the panel
	 * 
	 * @param t
	 *            - treatment instance that will be added to him the new
	 *            Examination
	 * @param doctorMedicalRecordUI
	 *            - reference to doctorMedicalRecordUI
	 */
	public NewExaminationReferralPanel(Treatment t, DoctorMedicalRecordUI doctorMedicalRecordUI) {
		super();
		examination = new Examination();
		examination.setTreatment(t);
		try {
			examination.setReferralDate(DateTime.currentDate());
			examination.setExaminationDate(DateTime.getDate(1, 1, 1));

		} catch (Exception ex) {
			Config.getConfig().getLogger().exception(ex);

		}

		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New Referral", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBackground(UIManager.getColor("Panel.background"));
		setBounds(new Rectangle(283, 143, 122, 144));
		setLayout(null);

		textField_1 = new JTextField(DateTime.getDateString(examination.getReferralDate()));
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setBounds(140, 59, 197, 20);
		textField_1.setColumns(10);
		add(textField_1);

		JLabel lblDate = new JLabel("Date :");
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(9, 61, 46, 14);
		add(lblDate);

		JLabel lblComments = new JLabel("Comments : ");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComments.setBounds(9, 252, 121, 14);
		add(lblComments);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(140, 247, 308, 68);
		add(scrollPane);

		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("Examination Type :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(9, 115, 121, 20);
		add(lblNewLabel);

		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Blood", "Urine", "CT", "ECG", "X-Ray", "Eye", "CAT" }));
		// Default ExType
		exType = "Blood";
		comboBox.setBackground(Color.WHITE);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exType = (String) comboBox.getSelectedItem();

			}
		});

		comboBox.setBounds(140, 113, 197, 27);
		add(comboBox);

		/**
		 * check if all requirement field is filled . call to
		 * saveExaminationReferral method in ExaminationController to save the
		 * referral show success message to user or warning message if can not
		 * add Examination to treatment
		 */

		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comment = textArea.getText();

				error_lbl.setText("");
				if (UITests.notEmpty(comment) == false)
					error_lbl.setText("*Please enter comment");

				else {
					examination.setComments(comment);
					examination.seteType(exType);
					examination.setClinic(clinic);

					if (examination.getTreatment().isEndFlag()) {
						Messages.warningMessage(
								"you cannot add an Examinations to Treatment :" + examination.getTreatment().getTid()
										+ "-" + examination.getTreatment().gettType(),
								"warning", doctorMedicalRecordUI.DoctorMedicalRecord);
					} else {
						ExaminationController.saveExaminationReferral(examination);

						Messages.successMessage("Referral was added successfully  ", "Success",
								doctorMedicalRecordUI.DoctorMedicalRecord);
					}

					setVisible(false);
				}
			}
		});
		btnSave.setBounds(164, 374, 89, 23);
		add(btnSave);

		JButton btnCancel = new JButton("Close");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(295, 374, 89, 23);
		add(btnCancel);

		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(140, 219, 269, 27);

		add(error_lbl);

		JLabel lblLabortary = new JLabel("Labratory :");
		lblLabortary.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLabortary.setBounds(9, 180, 121, 20);
		add(lblLabortary);

		JComboBox<Clinic> comboBox_1 = new JComboBox();

		ArrayList<Clinic> labList = (ArrayList<Clinic>) MedicalRecordController.getAllLabratories();
		for (Clinic c : labList)
			comboBox_1.addItem(c);
		if (labList.size() != 0)
			clinic = labList.get(0);

		comboBox_1.setBackground(Color.WHITE);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clinic = (Clinic) comboBox_1.getSelectedItem();

			}
		});
		comboBox_1.setBounds(140, 180, 197, 31);
		add(comboBox_1);

	}
}
