package ui.medical;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import models.Patient;
import ui.utils.Messages;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import Client.Resources;
import Controllers.MedicalRecordController;

import javax.swing.JRadioButton;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

/**
 * public class RequestInformationUI display form to send Request information to
 * HMO and displays the informations files that recieved from HMO
 * the doctor can send request and  receive  Partial information or complete informations
 * @author maisam marjieh
 *
 */
public class RequestInformationUI {
	private JFrame requestInfo;
	private JLabel lblComments;
	private JTextArea textArea;
	private JScrollPane scrollPane;
	private JRadioButton radioComplete;
	private JLabel lblPleaseChooseType;
	MedicalRecordController mrctrl = new MedicalRecordController();
	private JLabel btnImage;
	private JRadioButton radioPartial;

	public RequestInformationUI(Patient patient) {
		initialize(patient);
		requestInfo.setSize(685, 907);
		requestInfo.setLocale(null);
		requestInfo.setLocationRelativeTo(null);
		requestInfo.setVisible(true);

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Patient p) {
		Resources res = new Resources();
		requestInfo = new JFrame();
		requestInfo.setTitle("<Request Information> - GHealth");
		requestInfo.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource(
				"/img/" + "icon.png")).getImage();
		requestInfo.setIconImage(icon);
		requestInfo.setForeground(Color.BLACK);
		requestInfo.setFont(new Font("Dialog", Font.PLAIN, 16));
		requestInfo.setBackground(Color.WHITE);
		requestInfo.getContentPane().setBackground(Color.WHITE);
		requestInfo.getContentPane().setLayout(null);

		JLabel logo = new JLabel(" Request Information");
		logo.setBounds(0, 0, 399, 60);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Microsoft New Tai Lue", Font.BOLD, 17));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		requestInfo.getContentPane().add(logo);
		requestInfo.getContentPane().setFocusTraversalPolicy(
				new FocusTraversalOnArray(new Component[] { logo }));
		requestInfo.setBounds(100, 100, 501, 496);
		requestInfo.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		JButton btnSend = new JButton("Send");
		btnSend.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnSend.setEnabled(false);
		/**
		 * send the  request information and receive it .displays the information file (image)
		 */
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textArea.getText();

				MedicalRecordController.sendRequestToHMO(p);
				Messages.successMessage(
						"Request was sent successfully..wait for result",
						"success", requestInfo);

				ImageIcon image = null;
				if (radioComplete.isSelected())
					image = (ImageIcon) MedicalRecordController
							.getHmoInformation("s1.jpg");
				else if (radioPartial.isSelected())
					image = (ImageIcon) MedicalRecordController
							.getHmoInformation("s3.jpg");

				btnImage.setIcon(image);
			}
		});
		btnSend.setBounds(10, 189, 89, 23);
		requestInfo.getContentPane().add(btnSend);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = Messages.confirmMessage(
						"Are you sure you want to cancel?", "GHealth", null);
				if (result == JOptionPane.YES_OPTION)
					requestInfo.dispose();
			}
		});
		btnCancel.setBounds(109, 189, 104, 23);
		requestInfo.getContentPane().add(btnCancel);

		radioPartial = new JRadioButton("partial information");
		radioPartial.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioPartial
				.setToolTipText("Partial medical information");
		radioPartial.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnSend.setEnabled(true);

			}
		});

		radioPartial.setBounds(16, 78, 169, 23);
		requestInfo.getContentPane().add(radioPartial);

		radioComplete = new JRadioButton("Complete Medical Record ");
		radioComplete.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioComplete.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				btnSend.setEnabled(true);

			}
		});
		radioComplete.setBounds(190, 78, 169, 23);
		requestInfo.getContentPane().add(radioComplete);

		ButtonGroup group = new ButtonGroup();

		group.add(radioComplete);
		group.add(radioPartial);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(76, 131, 283, 47);
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		requestInfo.getContentPane().add(scrollPane);
		scrollPane.setVisible(true);

		lblComments = new JLabel("Notes :");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblComments.setBounds(26, 131, 94, 14);
		requestInfo.getContentPane().add(lblComments);

		lblPleaseChooseType = new JLabel("* Please choose type of request");
		lblPleaseChooseType.setBounds(16, 106, 197, 14);
		requestInfo.getContentPane().add(lblPleaseChooseType);

		btnImage = new JLabel("");
		btnImage.setHorizontalAlignment(SwingConstants.CENTER);
		btnImage.setIcon(new ImageIcon(RequestInformationUI.class
				.getResource("/img/loading.gif")));
		btnImage.setBounds(10, 223, 638, 644);
		requestInfo.getContentPane().add(btnImage);
		lblComments.setVisible(true);

	}

}
