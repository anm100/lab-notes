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

public class AddLaboratorian {

	private JFrame addLaboratorian;
	private final JLabel logoLbl = new JLabel(Logo.getLogoText()); 
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JLabel lblNewLabel_3 = new JLabel("Connected to server : ");
	private final JLabel lblFirstName = new JLabel("First Name :");
	private final JTextField textField = new JTextField();
	private final JLabel lblLastName = new JLabel("Last Name :");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblType = new JLabel("Type :");
	private final JTextField textField_2 = new JTextField();
	private final JLabel lblAddress = new JLabel("Address :");
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblPhoneNumber = new JLabel("Phone Number :");
	private final JTextField textField_4 = new JTextField();


	public AddLaboratorian() {
		initialize();
		addLaboratorian.setSize(656, 575);
		addLaboratorian.setVisible(true);
		addLaboratorian.setLocationRelativeTo(null);
		lblNewLabel_1.setBounds(331, 21, 279, 44);
		lblNewLabel_1.setText(Application.user.getFirstName() + " " + Application.user.getLastName() + " ("
				+ Application.user.getClass().getSimpleName() + ")");

		if (Application.client.isConnected())
			lblNewLabel_3.setText(
					"Connected to server: " + Config.getConfig().getHost() + ":" + Config.getConfig().getPort());
		else
			lblNewLabel_3.setText("Offline");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 10));

		lblNewLabel_3.setBounds(10, 518, 308, 14);

		addLaboratorian.getContentPane().add(lblNewLabel_3);

		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(20, 80, 570, 4);
		addLaboratorian.getContentPane().add(panel);
		lblFirstName.setBounds(471, 127, 98, 40);
		
		addLaboratorian.getContentPane().add(lblFirstName);
		textField.setBounds(232, 140, 200, 27);
		
		addLaboratorian.getContentPane().add(textField);
		lblLastName.setBounds(471, 173, 98, 32);
		
		addLaboratorian.getContentPane().add(lblLastName);
		textField_1.setBounds(232, 178, 200, 27);
		
		addLaboratorian.getContentPane().add(textField_1);
		lblType.setBounds(471, 208, 65, 35);
		
		addLaboratorian.getContentPane().add(lblType);
		textField_2.setBounds(232, 216, 200, 27);
		
		addLaboratorian.getContentPane().add(textField_2);
		lblAddress.setBounds(471, 248, 53, 33);
		
		addLaboratorian.getContentPane().add(lblAddress);
		textField_3.setBounds(232, 254, 200, 27);
		
		addLaboratorian.getContentPane().add(textField_3);
		lblPhoneNumber.setBounds(471, 292, 87, 27);
		
		addLaboratorian.getContentPane().add(lblPhoneNumber);
		textField_4.setBounds(232, 292, 200, 27);
		
		addLaboratorian.getContentPane().add(textField_4);
		JTextField textFieldStart = new JTextField(15);
	}
	
	private void initialize() {
		textField_4.setColumns(10);
		textField_3.setColumns(10);
		textField_2.setColumns(10);
		textField_1.setColumns(10);
		textField.setColumns(10);

		Resources res = new Resources();
		addLaboratorian = new JFrame();
		addLaboratorian.setTitle("GHealth");
		addLaboratorian.setResizable(false);
		addLaboratorian.setIconImage(Resources.getImage("icon.png"));
		addLaboratorian.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addLaboratorian.setBackground(Color.WHITE);
		addLaboratorian.getContentPane().setBackground(Color.WHITE);
		addLaboratorian.getContentPane().setLayout(null);
		logoLbl.setBackground(Color.WHITE);
		logoLbl.setBounds(10, 11, 284, 60);
		logoLbl.setFont(new Font(Logo.getLogofont(), Font.BOLD, Logo.getLogosize()));
		logoLbl.setIcon(Resources.getIcon(Logo.getLogoUrl()));
		addLaboratorian.getContentPane().add(logoLbl);
		lblNewLabel_1.setIcon(Resources.getIcon("user.png"));
		addLaboratorian.getContentPane().add(lblNewLabel_1);

	}
}
