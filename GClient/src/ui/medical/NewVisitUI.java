package ui.medical;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.JButton;

import Controllers.MedicalRecordController;
import models.Treatment;
import models.Visit;
import ui.utils.Messages;
import ui.utils.UITests;
import Utils.DateTime;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputMethodEvent;
import java.awt.event.InputMethodListener;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Client.Config;

/**
 * public class NewVisitUI Presentation of the visit form to be filled by a
 * doctor
 * 
 * @author maisam marjieh
 *
 */
public class NewVisitUI extends JPanel {

	private JTextField textField_1;
	private JTextArea textArea = new JTextArea();
	private JLabel error_lbl;
	/**
	 * the new visit will be added
	 */
	private Visit v;

	/**
	 * construct the panel
	 * 
	 * @param t
	 *            - treatment instance
	 * @param doctorMedicalRecordUI
	 */
	public NewVisitUI(Treatment t, DoctorMedicalRecordUI doctorMedicalRecordUI) {

		super();
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "New Visit", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBackground(UIManager.getColor("Panel.background"));
		setBounds(new Rectangle(283, 143, 122, 144));
		setLayout(null);

		v = new Visit();
		v.setTreatment(t);

		try {
			v.setVisitDate(DateTime.currentDate());
		} catch (Exception e) {
			Config.getConfig().getLogger().exception(e);
		}

		textField_1 = new JTextField(DateTime.getDateString(v.getVisitDate()));
		textField_1.setBounds(107, 52, 155, 20);
		textField_1.setBackground(Color.WHITE);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		add(textField_1);

		JLabel lblDate = new JLabel("Visit Date :");
		lblDate.setBounds(10, 54, 77, 14);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDate);

		JLabel lblComments = new JLabel("Visit description : ");
		lblComments.setBounds(10, 121, 97, 20);
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblComments);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(107, 122, 329, 120);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}

			public void inputMethodTextChanged(InputMethodEvent arg0) {

			}
		});
		textArea.setFont(UIManager.getFont("Button.font"));
		scrollPane.setViewportView(textArea);

		/**
		 * checks if the all requirement field is filled . call to saveVisit
		 * method in MedicalRecordController to save the new visit call to
		 * updateTree methods in doctorMedicalRecordUI to add the new visit in
		 * the treatment tree show success message to user or warning message if
		 * can not add visits to treatment
		 */
		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String comments = textArea.getText();
				error_lbl.setText("");
				if (UITests.notEmpty(comments) == false)
					error_lbl.setText("*Please enter visit description");

				else {
					v.setComments(textArea.getText());

					if (v.getTreatment().isEndFlag()) {
						Messages.warningMessage(
								"you cannot add Visits to Treatment: " + v.getTreatment().getTid() + "-"
										+ v.getTreatment().gettType() + "\nThis treatment is closed  ",
								"warnning", doctorMedicalRecordUI.DoctorMedicalRecord);

					} else {

						Visit visitDB = (Visit) MedicalRecordController.saveVisit(v);

						Messages.successMessage(
								"Visit was added successfully to Treatment " + v.getTreatment().getTid() + "-"
										+ v.getTreatment().gettType(),
								"Success", doctorMedicalRecordUI.DoctorMedicalRecord);
						System.out.printf(visitDB.getVid() + "");

						doctorMedicalRecordUI.updateTree(visitDB, true);

					}

					setVisible(false);

				}

			}
		});
		btnSave.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSave.setBounds(179, 322, 89, 23);
		add(btnSave);

		JButton btnNewButton = new JButton("Close");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);

			}
		});
		btnNewButton.setBounds(278, 322, 89, 23);
		add(btnNewButton);

		error_lbl = new JLabel("");
		error_lbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		error_lbl.setForeground(Color.RED);
		error_lbl.setBounds(107, 94, 269, 27);

		add(error_lbl);

	}

}
