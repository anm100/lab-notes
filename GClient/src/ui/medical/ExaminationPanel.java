package ui.medical;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Clinic;
import models.Examination;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Utils.DateTime;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;

import Controllers.ExaminationController;

import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

/**
 * public class ExaminationPanel Displays the Examinations details and the
 * Examination Referral details
 * 
 * @author maisam marjieh
 *
 */
public class ExaminationPanel {

	private JTextArea textArea_1 = new JTextArea();

	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblNewLabel_2;
	private JTextField textField;
	private JLabel lblExaminationType;
	private JScrollPane scrollPane_1;
	private JLabel lblPhotolink;
	private JLabel lblClincsName;
	private JLabel lblLabortianName;
	private JLabel lblDate_1;
	private JButton btnViewReferral;
	private JButton btnBack;
	private JLabel btnNewButton;

	private JTextField textField_1;
	private JTextField textField_3;
	private JTextField textField_2;
	private JLabel lblDate;
	private JLabel lblComments;
	private JLabel lblDoctorname;
	private JTextArea textArea;
	private JLabel lblNewLabel;
	public JPanel mainPanel;
	private JScrollPane scrollPane;

	/**
	 * construct the panel
	 * 
	 * @param ex
	 *            - the Examination instance should be present
	 */

	public ExaminationPanel(Examination ex) {

		mainPanel = new JPanel();
		mainPanel.setBorder(
				new TitledBorder(null, "Examination Result", TitledBorder.CENTER, TitledBorder.TOP, null, Color.BLACK));

		mainPanel.setBackground(UIManager.getColor("Panel.background"));
		mainPanel.setBounds(283, 143, 477, 474);
		mainPanel.setLayout(null);

		textField_5 = new JTextField();
		textField_5.setBackground(Color.WHITE);
		textField_5.setBounds(131, 119, 197, 20);
		mainPanel.add(textField_5);

		textField_5.setEditable(false);
		textField_5.setColumns(10);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(131, 211, 328, 92);
		mainPanel.add(scrollPane_1);

		textArea_1.setEditable(false);
		scrollPane_1.setViewportView(textArea_1);

		lblPhotolink = new JLabel("Photo :");
		lblPhotolink.setBounds(11, 336, 63, 16);
		mainPanel.add(lblPhotolink);
		lblPhotolink.setFont(new Font("Tahoma", Font.PLAIN, 12));

		lblNewLabel_2 = new JLabel("Examination Result :");
		lblNewLabel_2.setBounds(11, 216, 110, 15);
		mainPanel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setForeground(new Color(0, 0, 0));

		lblClincsName = new JLabel("Clincs Name : ");
		lblClincsName.setBounds(10, 119, 133, 14);
		mainPanel.add(lblClincsName);
		lblClincsName.setFont(new Font("Tahoma", Font.PLAIN, 12));

		lblLabortianName = new JLabel("Labortian Name : ");
		lblLabortianName.setBounds(10, 69, 143, 20);
		mainPanel.add(lblLabortianName);
		lblLabortianName.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textField_4 = new JTextField();
		textField_4.setBackground(Color.WHITE);
		textField_4.setBounds(130, 70, 197, 20);
		mainPanel.add(textField_4);

		textField_4.setEditable(false);
		textField_4.setColumns(10);

		lblDate_1 = new JLabel("Date : ");
		lblDate_1.setBounds(10, 28, 80, 14);
		mainPanel.add(lblDate_1);
		lblDate_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textField_6 = new JTextField();
		textField_6.setBackground(Color.WHITE);
		textField_6.setBounds(129, 27, 197, 20);
		mainPanel.add(textField_6);

		textField_6.setEditable(false);
		textField_6.setColumns(10);

		Clinic clinic = ExaminationController.getClinic(ex.getLabratorian().getClinic().getCid());
		textArea_1.setText("" + ex.getResults());
		textField_6.setText(DateTime.getDateString(ex.getExaminationDate()));

		textField_5.setText("" + clinic.toString());
		textField_4.setText(ex.getLabratorian().getFirstName() + " " + ex.getLabratorian().getLastName());

		lblExaminationType = new JLabel("Examination Type : ");
		lblExaminationType.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblExaminationType.setBounds(11, 168, 110, 14);
		mainPanel.add(lblExaminationType);

		textField = new JTextField(ex.geteType());
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(131, 165, 197, 20);
		mainPanel.add(textField);
		textField.setColumns(10);

		lblDate = new JLabel("Date :");
		lblDate.setVisible(false);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDate.setBounds(9, 40, 46, 14);
		mainPanel.add(lblDate);

		lblComments = new JLabel("Comments : ");
		lblComments.setVisible(false);
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComments.setBounds(9, 169, 110, 14);
		mainPanel.add(lblComments);

		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(130, 164, 328, 108);
		mainPanel.add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		textArea.setVisible(false);
		scrollPane.setVisible(false);

		lblDoctorname = new JLabel("DoctorName : ");
		lblDoctorname.setVisible(false);

		lblDoctorname.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoctorname.setBounds(9, 80, 110, 21);
		mainPanel.add(lblDoctorname);

		textField_3 = new JTextField();
		textField_3.setVisible(false);
		textField_3.setBackground(Color.WHITE);
		textField_3.setEditable(false);
		textField_3.setBounds(130, 81, 197, 20);
		mainPanel.add(textField_3);
		textField_3.setColumns(10);

		lblNewLabel = new JLabel("Examination Type :");
		lblNewLabel.setVisible(false);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(9, 126, 110, 20);
		mainPanel.add(lblNewLabel);

		textField_2 = new JTextField();
		textField_2.setVisible(false);
		textField_2.setBackground(Color.WHITE);

		textField_2.setEditable(false);
		textField_2.setBounds(130, 127, 197, 20);
		mainPanel.add(textField_2);
		textField_2.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setVisible(false);
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setBounds(130, 38, 197, 20);
		textField_1.setColumns(10);
		mainPanel.add(textField_1);

		textField_1.setText(DateTime.getDateString(ex.getReferralDate()));

		textField_2.setText(ex.geteType());
		textField_3.setText("" + ex.getTreatment().getDoctor().getFirstName() + " "
				+ (ex.getTreatment().getDoctor().getLastName()));
		textArea.setText(ex.getComments());

		/**
		 * display the referral of the Examination
		 */
		btnViewReferral = new JButton("View Referral");
		btnViewReferral.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnViewReferral.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setBorder(new TitledBorder(null, "Referral Information", TitledBorder.CENTER,
						TitledBorder.TOP, null, Color.BLACK));

				textArea_1.setVisible(false);
				textField_5.setVisible(false);
				textField_4.setVisible(false);
				textField_6.setVisible(false);

				lblNewLabel_2.setVisible(false);
				lblExaminationType.setVisible(false);
				scrollPane_1.setVisible(false);
				lblPhotolink.setVisible(false);
				lblClincsName.setVisible(false);
				lblLabortianName.setVisible(false);
				lblDate_1.setVisible(false);
				btnViewReferral.setVisible(false);
				textField.setVisible(false);

				btnNewButton.setVisible(false);
				btnBack.setVisible(true);

				textField_1.setVisible(true);
				textField_3.setVisible(true);
				textField_2.setVisible(true);
				lblDate.setVisible(true);
				lblComments.setVisible(true);
				lblDoctorname.setVisible(true);
				textArea.setVisible(true);
				lblNewLabel.setVisible(true);
				mainPanel.setVisible(true);
				scrollPane.setVisible(true);

			}
		});
		btnViewReferral.setBounds(195, 440, 133, 23);
		mainPanel.add(btnViewReferral);

		/**
		 * back to shows the Examination details
		 */
		btnBack = new JButton("Back");
		btnBack.setVisible(false);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setBorder(new TitledBorder(null, "Examination Result", TitledBorder.CENTER, TitledBorder.TOP,
						null, Color.BLACK));
				textArea_1.setVisible(true);
				textField_5.setVisible(true);
				textField_4.setVisible(true);
				textField_6.setVisible(true);
				lblNewLabel_2.setVisible(true);
				lblExaminationType.setVisible(true);
				scrollPane_1.setVisible(true);
				lblPhotolink.setVisible(true);
				lblClincsName.setVisible(true);
				lblLabortianName.setVisible(true);
				lblDate_1.setVisible(true);
				btnViewReferral.setVisible(true);
				textField.setVisible(true);
				btnNewButton.setVisible(true);

				btnBack.setVisible(false);

				btnBack.setVisible(false);

				textField_1.setVisible(false);
				textField_3.setVisible(false);
				textField_2.setVisible(false);
				lblDate.setVisible(false);
				lblComments.setVisible(false);
				lblDoctorname.setVisible(false);
				textArea.setVisible(false);
				lblNewLabel.setVisible(false);
				scrollPane.setVisible(false);
				mainPanel.setVisible(true);
			}
		});
		btnBack.setToolTipText("Return to Examination Result ");
		btnBack.setBounds(216, 314, 89, 23);
		mainPanel.add(btnBack);

		btnNewButton = new JLabel("");

		ImageIcon image = ExaminationController.getImage(ex);
		if (image != null)
			btnNewButton.setIcon(image);
		btnNewButton.setBounds(131, 317, 237, 112);
		mainPanel.add(btnNewButton);
		btnBack.setVisible(false);

	}
}
