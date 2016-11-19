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

public class SignUp {

	private JFrame signUp;
	private final JLabel logoLbl = new JLabel(Logo.getLogoText()); 
	private final JLabel lblNewLabel_1 = new JLabel("");
	private final JLabel lblNewLabel_3 = new JLabel("Connected to server : ");
	private final JLabel lblFirstName = new JLabel("First Name :");
	private final JTextField textField = new JTextField();
	private final JLabel lblLastName = new JLabel("Last Name :");
	private final JTextField textField_1 = new JTextField();
	private final JLabel lblId = new JLabel("ID :");
	private final JTextField textField_2 = new JTextField();
	private final JLabel lblPassword = new JLabel("Password :");
	private final JTextField textField_3 = new JTextField();
	private final JLabel lblPhoneNumber = new JLabel("Phone Number :");
	private final JTextField textField_4 = new JTextField();
	private final JLabel lblBirthDate = new JLabel("Birth Date :");
	private JDateChooser chooserDate= null;


	public SignUp() {
		initialize();
		signUp.setSize(656, 575);
		signUp.setVisible(true);
		signUp.setLocationRelativeTo(null);
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

		signUp.getContentPane().add(lblNewLabel_3);

		Panel panel = new Panel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(20, 80, 570, 4);
		signUp.getContentPane().add(panel);
		lblFirstName.setBounds(471, 127, 98, 40);
		
		signUp.getContentPane().add(lblFirstName);
		textField.setBounds(232, 140, 200, 27);
		
		signUp.getContentPane().add(textField);
		lblLastName.setBounds(471, 173, 98, 32);
		
		signUp.getContentPane().add(lblLastName);
		textField_1.setBounds(232, 178, 200, 27);
		
		signUp.getContentPane().add(textField_1);
		lblId.setBounds(471, 208, 65, 35);
		
		signUp.getContentPane().add(lblId);
		textField_2.setBounds(232, 216, 200, 27);
		
		signUp.getContentPane().add(textField_2);
		lblPassword.setBounds(471, 248, 53, 33);
		
		signUp.getContentPane().add(lblPassword);
		textField_3.setBounds(232, 254, 200, 27);
		
		signUp.getContentPane().add(textField_3);
		lblPhoneNumber.setBounds(471, 292, 87, 27);
		
		signUp.getContentPane().add(lblPhoneNumber);
		textField_4.setBounds(232, 292, 200, 27);
		
		signUp.getContentPane().add(textField_4);
		lblBirthDate.setBounds(471, 330, 65, 21);
		
		signUp.getContentPane().add(lblBirthDate);
		JTextField textFieldStart = new JTextField(15);

		chooserDate = new JDateChooser();
				chooserDate.enableInputMethods(false);
				chooserDate.setBackground(Color.GRAY);
				chooserDate.setLocale(Locale.US);
				chooserDate.setBounds(232, 330, 200, 27);
				chooserDate.setVisible(true);
				chooserDate.addPropertyChangeListener("date", new PropertyChangeListener() {
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						JDateChooser chooser = (JDateChooser) evt.getSource();
						SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
						textFieldStart.setText(formatter.format(chooser.getDate()));
					}
				});
				JTextFieldDateEditor editor = (JTextFieldDateEditor) chooserDate.getDateEditor();
				editor.setEditable(false);
				chooserDate.setDate(new Date());
				signUp.getContentPane().add(chooserDate);
	}
	
	private void initialize() {
		textField_4.setColumns(10);
		textField_3.setColumns(10);
		textField_2.setColumns(10);
		textField_1.setColumns(10);
		textField.setColumns(10);

		Resources res = new Resources();
		signUp = new JFrame();
		signUp.setTitle("GHealth");
		signUp.setResizable(false);
		signUp.setIconImage(Resources.getImage("icon.png"));
		signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		signUp.setBackground(Color.WHITE);
		signUp.getContentPane().setBackground(Color.WHITE);
		signUp.getContentPane().setLayout(null);
		logoLbl.setBackground(Color.WHITE);
		logoLbl.setBounds(10, 11, 284, 60);
		logoLbl.setFont(new Font(Logo.getLogofont(), Font.BOLD, Logo.getLogosize()));
		logoLbl.setIcon(Resources.getIcon(Logo.getLogoUrl()));
		signUp.getContentPane().add(logoLbl);
		lblNewLabel_1.setIcon(Resources.getIcon("user.png"));
		signUp.getContentPane().add(lblNewLabel_1);

	}
}
