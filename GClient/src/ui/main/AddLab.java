package ui.main;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JButton;

import Client.Application;
import Client.Config;
import Client.Resources;
import models.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Panel;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;

import ui.utils.Logo;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
/**
 * public class ClientUI ,the menu window of Ghealth 
 * @author Bolous Abo Jaber 
 *
 */
public class AddLab {

	private JFrame addLab;
	private final JLabel logoLbl = new JLabel(Logo.getLogoText()); 
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JLabel lblNewLabel_3 = new JLabel("Connected to server : ");
	private final JLabel lblPatientName = new JLabel("Patient Name :");
	private final JTextField textField = new JTextField();
	private final JLabel lblPatientPhoneNumber = new JLabel("Patient Phone Number :");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblLabratorianName = new JLabel("labratorian Name:");
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblStartDate = new JLabel("Start Date :");
	private final JLabel lblEndDate = new JLabel("End Date :");
	private final JTextPane textPane = new JTextPane();
	private JDateChooser chooserStart= null;
	private JDateChooser chooserEnd= null;


	public AddLab() {
		initialize();
		addLab.setSize(656, 624);
		addLab.setVisible(true);
		addLab.setLocationRelativeTo(null);
		lblNewLabel_1.setBounds(331, 21, 279, 44);
		lblNewLabel_1.setText(Application.user.getFirstName() + " " + Application.user.getLastName() + " ("
				+ Application.user.getClass().getSimpleName() + ")");

		if (Application.client.isConnected())
			lblNewLabel_3.setText(
					"Connected to server: " + Config.getConfig().getHost() + ":" + Config.getConfig().getPort());
		else
			lblNewLabel_3.setText("Offline");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));

		lblNewLabel_3.setBounds(10, 574, 308, 14);

		addLab.getContentPane().add(lblNewLabel_3);

		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(20, 80, 570, 4);
		addLab.getContentPane().add(panel);
		lblPatientName.setBounds(471, 127, 98, 53);
		
		addLab.getContentPane().add(lblPatientName);
		textField.setBounds(232, 140, 200, 27);
		
		addLab.getContentPane().add(textField);
		lblPatientPhoneNumber.setBounds(455, 173, 114, 36);
		
		addLab.getContentPane().add(lblPatientPhoneNumber);
		textField_1.setBounds(232, 178, 200, 27);
		
		addLab.getContentPane().add(textField_1);
		lblLabratorianName.setBounds(465, 220, 125, 23);
		
		addLab.getContentPane().add(lblLabratorianName);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(232, 215, 200, 27);
		addLab.getContentPane().add(comboBox);
		JTextField textFieldStart = new JTextField(15);

		chooserStart = new JDateChooser();

				
				chooserStart.enableInputMethods(false);
				chooserStart.setBackground(Color.GRAY);
				chooserStart.setLocale(Locale.US);
				chooserStart.setBounds(232, 253, 200, 26);
				chooserStart.setVisible(true);
				chooserStart.addPropertyChangeListener("date", new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						JDateChooser chooser = (JDateChooser) evt.getSource();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						textFieldStart.setText(formatter.format(chooser.getDate()));
					}
				});
				JTextFieldDateEditor editor = (JTextFieldDateEditor) chooserStart.getDateEditor();
				editor.setEditable(false);
				chooserStart.setDate(new Date());
				
				
			
				addLab.getContentPane().add(chooserStart);	
	////===================================================
				JTextField textFieldend = new JTextField(15);

				chooserEnd = new JDateChooser();

				
				chooserEnd.enableInputMethods(false);
				chooserEnd.setBackground(Color.GRAY);
				chooserEnd.setLocale(Locale.US);
				chooserEnd.setBounds(232, 290, 202, 26);
				chooserEnd.setVisible(true);
				chooserEnd.addPropertyChangeListener("date", new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						JDateChooser chooser = (JDateChooser) evt.getSource();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						textFieldend.setText(formatter.format(chooser.getDate()));
					}
				});
				JTextFieldDateEditor editor2 = (JTextFieldDateEditor) chooserEnd.getDateEditor();
				editor2.setEditable(false);
				chooserEnd.setDate(new Date());
				
				
			
				addLab.getContentPane().add(chooserEnd);
		
		JLabel lblDescription = new JLabel("Description :");
		lblDescription.setBounds(471, 320, 115, 36);
		addLab.getContentPane().add(lblDescription);
		scrollPane.setBounds(182, 337, 251, 197);
		
		addLab.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(textPane);
		lblStartDate.setBounds(475, 242, 115, 36);
		
		addLab.getContentPane().add(lblStartDate);
		lblEndDate.setBounds(475, 280, 115, 36);
		
		addLab.getContentPane().add(lblEndDate);
	}

	
	private void initialize() {
		textField_1.setColumns(10);
		textField.setColumns(10);

		Resources res = new Resources();
		addLab = new JFrame();
		addLab.setTitle("GHealth");
		addLab.setResizable(false);
		addLab.setIconImage(Resources.getImage("icon.png"));
		addLab.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addLab.setBackground(Color.WHITE);
		addLab.getContentPane().setBackground(Color.WHITE);
		addLab.getContentPane().setLayout(null);
		logoLbl.setBackground(Color.WHITE);
		logoLbl.setBounds(10, 11, 284, 60);
		logoLbl.setFont(new Font(Logo.getLogofont(), Font.BOLD, Logo.getLogosize()));
		logoLbl.setIcon(Resources.getIcon(Logo.getLogoUrl()));
		addLab.getContentPane().add(logoLbl);
		lblNewLabel_1.setIcon(Resources.getIcon("user.png"));
		addLab.getContentPane().add(lblNewLabel_1);

	}
}
