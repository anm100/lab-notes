package ui.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;

import Client.Application;
import Client.Resources;
import Controllers.UsersController;
import models.User;
import ui.utils.UITests;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dialog.ModalExclusionType;

import javax.swing.SwingConstants;
import javax.swing.text.IconView;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JEditorPane;
import javax.swing.JScrollBar;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

/**
 * Sign in form, allows user to sign in to the system by giving a Social id and
 * password
 * 
 * @author aj_pa
 *
 */
public class AddNote {

	private JFrame AddNote;
	private int attempts = 0;

	JButton btnConnected;

	public AddNote() {
		initialize();
		AddNote.setVisible(true);
		AddNote.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		AddNote self = this;
		AddNote = new JFrame();
		AddNote.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {

			}
		});
		AddNote.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		AddNote.setTitle("Sign In");
		AddNote.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		AddNote.setIconImage(icon);
		AddNote.setForeground(Color.BLACK);
		AddNote.setFont(new Font("Dialog", Font.PLAIN, 16));
		AddNote.setBackground(Color.WHITE);
		AddNote.getContentPane().setBackground(Color.WHITE);
		AddNote.getContentPane().setLayout(null);
		
		JLabel logo = new JLabel ("\u0639\u064A\u0627\u062F\u0629 \u0627\u0644\u062F\u0643\u062A\u0648\u0631 \u0647\u064A\u062B\u0645 \u0627\u0644\u0634\u064A\u062E \u062E\u0644\u064A\u0644\r\n");
		logo.setVerticalAlignment(SwingConstants.BOTTOM);
		logo.setBounds(214, 13, 421, 76);
		logo.setForeground(new Color(0, 0, 0));
		logo.setFont(new Font("Arial", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("pic.png"));
		AddNote.getContentPane().add(logo);

		JLabel lblNewLabel = new JLabel("\u05D4\u05E2\u05E8\u05D5\u05EA :");
		lblNewLabel.setBounds(536, 164, 46, 14);
		AddNote.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(214, 164, 302, 150);
		AddNote.getContentPane().add(scrollPane);
		
		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		textPane.setBackground(Color.WHITE);
		AddNote.setBounds(100, 100, 708, 414);
		AddNote.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public JFrame getFrame() {
		// TODO Auto-generated method stub
		return AddNote;
	}
}
