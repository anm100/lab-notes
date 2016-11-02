package ui.medical;

import javax.swing.JPanel;
import javax.swing.JTextField;

import models.Visit;

import java.awt.Rectangle;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import Utils.DateTime;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;

/**
 * public class VisitPanel presents the visit details
 * 
 * @author maisam marjieh
 *
 */
public class VisitPanel extends JPanel {
	public JTextField textField_1;
	public JTextArea textArea = new JTextArea();
	public JTextField textField;

	/**
	 * construct the panel add to panel the visit details
	 * 
	 * @param visit
	 *            - visit instance should be presents
	 */
	public VisitPanel(Visit visit) {
		super();
		setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Visit Details", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		setBackground(UIManager.getColor("Panel.background"));
		setBounds(new Rectangle(283, 143, 122, 144));
		setLayout(null);

		textField_1 = new JTextField(
				DateTime.getDateString(visit.getVisitDate()) );
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 12));

		textField_1.setBounds(112, 28, 158, 20);
		textField_1.setBackground(new Color(255, 255, 255));
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		add(textField_1);

		JLabel lblDate = new JLabel("Visit Date :");
		lblDate.setBounds(10, 31, 70, 14);
		lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblDate);

		JLabel lblComments = new JLabel("Visit description : ");
		lblComments.setBounds(10, 138, 104, 20);
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
		add(lblComments);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(112, 138, 328, 120);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane);
		textArea.setEditable(false);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textArea.setText(visit.getComments());

		scrollPane.setViewportView(textArea);

		JLabel lblDoctorName = new JLabel("Doctor Name : ");
		lblDoctorName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblDoctorName.setBounds(10, 84, 89, 14);
		add(lblDoctorName);

		textField = new JTextField(
				visit.getTreatment().getDoctor().getFirstName() + "" + visit.getTreatment().getDoctor().getLastName());
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(112, 82, 158, 20);
		add(textField);
		textField.setColumns(10);
	}
}
