package ui.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;

import Client.Resources;
import Controllers.UsersController;
import models.User;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import ui.utils.Messages;

import java.awt.Component;
import java.awt.SystemColor;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Form for managing the online users and locked ones. only for ceo usage
 * @author aj_pa
 *
 */
public class UsersManagingUI {

	private JFrame ui;
	private JTable tblOnline;
	private JTable tblLocked;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;

	public UsersManagingUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ui = new JFrame();
		ui.setTitle("GHealth - Users manager");
		ui.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		ui.setIconImage(icon);
		ui.setForeground(Color.BLACK);
		ui.setFont(new Font("Dialog", Font.PLAIN, 16));
		ui.setBackground(Color.WHITE);
		ui.getContentPane().setBackground(Color.WHITE);
		ui.getContentPane().setLayout(null);

		JLabel logo = new JLabel("Users managment");
		logo.setBounds(0, 0, 460, 60);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		ui.getContentPane().add(logo);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 440, 164);
		ui.getContentPane().add(scrollPane);

		tblOnline = new JTable();
		tblOnline.setBackground(Color.WHITE);
		scrollPane.setViewportView(tblOnline);
		tblOnline.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Email" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 318, 440, 141);
		ui.getContentPane().add(scrollPane_1);

		tblLocked = new JTable();
		tblLocked.setBackground(Color.WHITE);
		scrollPane_1.setViewportView(tblLocked);
		tblLocked.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Id", "Name", "Email" }) {
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JLabel lblNewLabel = new JLabel("Online users");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 71, 157, 14);
		ui.getContentPane().add(lblNewLabel);

		JLabel lblLockedUserAccounts = new JLabel("Locked user accounts");
		lblLockedUserAccounts.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblLockedUserAccounts.setBounds(10, 292, 157, 14);
		ui.getContentPane().add(lblLockedUserAccounts);

		
		JButton btnUnlock = new JButton("Unlock");
		btnUnlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			int row= tblLocked.getSelectedRow();
					

			if(row<0){
				Messages.errorMessage("Please select a user to unlock", "No user selected", null);
				return;
			}
			String id = (String) tblLocked.getModel().getValueAt(row, 0);
			User u =(User) UsersController.getUser(id);
			UsersController.setLocked(u,false);
		
			DefaultTableModel dm = (DefaultTableModel) tblLocked.getModel();
			dm.removeRow(row);
			}
		});
		btnUnlock.setBounds(10, 470, 157, 20);
		tblLocked.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if(event.getValueIsAdjusting())
					return;
				if(tblLocked.getRowCount()==0)
					btnUnlock.setEnabled(false);	
				btnUnlock.setEnabled(true);

			}
			});
		ui.getContentPane().add(btnUnlock);
		ui.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));
		ui.setBounds(100, 100, 466, 524);
		ui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		ui.setVisible(true);
		fillUsers();

	}

	private void fillUsers() {
		DefaultTableModel odm = (DefaultTableModel) tblOnline.getModel();
		DefaultTableModel ldm = (DefaultTableModel) tblLocked.getModel();

		for (User u : UsersController.getOnlineUsers())
			odm.addRow(
					new Object[] { u.getSid(), u.getFirstName() + " " + u.getLastName(), u.getEmail(), "127.0.0.1" });

		for (User u : UsersController.getLockedUsers())
			ldm.addRow(new Object[] { u.getSid(), u.getFirstName() + " " + u.getLastName(), u.getEmail() });
	}
}
