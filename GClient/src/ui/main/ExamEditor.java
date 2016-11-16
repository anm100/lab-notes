package ui.main;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JFileChooser;

import Client.Application;
import Client.Config;
import Client.Resources;
import Controllers.ExaminationController;
import models.Examination;
import models.Labratorian;
import ui.utils.Messages;

import javax.swing.SwingConstants;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.SystemColor;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextPane;
import java.io.File;
import java.util.Date;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * public class ExamEditor , shows examination details and insert test result In
 * addition , labratory can insert photo if test need
 * 
 * @author Bolous Abo Jaber
 *
 */
public class ExamEditor {

	private JFrame labratoryUI;
	private Date creationDate = null;
	private Examination exam;
	private boolean isEditable;
	private Labratory labratory;
	private int row;

	public ExamEditor(Examination ex, int row, boolean isEditable, Labratory lab) {
		exam = ex;
		this.isEditable = isEditable;
		this.labratory = lab;
		this.row = row;
		initialize();
		labratoryUI.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Labratorian labratorian = (Labratorian) Application.user;
		labratoryUI = new JFrame();
		labratoryUI.setTitle("<Frame name> - GHealth");
		labratoryUI.setResizable(false);
		Image icon = new ImageIcon(this.getClass().getResource("/img/" + "icon.png")).getImage();
		labratoryUI.setIconImage(icon);
		labratoryUI.setForeground(Color.BLACK);
		labratoryUI.setFont(new Font("Tahoma", Font.PLAIN, 12));
		labratoryUI.setBackground(Color.WHITE);
		labratoryUI.getContentPane().setBackground(Color.WHITE);
		labratoryUI.getContentPane().setLayout(null);

		JLabel label_2 = new JLabel("<dynamic> <dynamic>");
		label_2.setBounds(232, 39, 214, 21);
		labratoryUI.getContentPane().add(label_2);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		try {
			creationDate = Utils.DateTime.currentDate();
		} catch (ParseException e) {
			Config.getConfig().getLogger().exception(e);
		}
		label_2.setText(creationDate.toString());

		JLabel logo = new JLabel("Examination Viewer");
		logo.setBounds(0, 0, 378, 60);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Tahoma", Font.BOLD, 16));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		labratoryUI.getContentPane().add(logo);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 65, 447, 94);
		labratoryUI.getContentPane().add(panel);

		JLabel lblPatient = new JLabel("Patient:");
		lblPatient.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPatient.setBounds(10, 11, 80, 21);
		panel.add(lblPatient);

		JLabel label_1 = new JLabel("<dynamic> <dynamic>");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(68, 11, 207, 21);
		panel.add(label_1);

		label_1.setText(new String(labratorian.getFirstName() + " " + labratorian.getLastName()));

		JLabel label_3 = new JLabel((String) null);
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(223, 11, 90, 21);
		panel.add(label_3);

		JLabel lblExaminationType = new JLabel("Examination Type:" + exam.geteType());
		lblExaminationType.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblExaminationType.setBounds(223, 11, 215, 21);
		panel.add(lblExaminationType);

		JLabel lblComments = new JLabel("Doctor comments: ");
		lblComments.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblComments.setBounds(10, 43, 132, 21);
		panel.add(lblComments);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setBounds(148, 43, 290, 47);
		panel.add(scrollPane_1);

		JTextPane txtDoctor = new JTextPane();
		txtDoctor.setEditable(false);
		scrollPane_1.setViewportView(txtDoctor);
		txtDoctor.setText(exam.getComments());

		JLabel lblResults = new JLabel("Result comments:");
		lblResults.setBounds(10, 163, 128, 14);
		labratoryUI.getContentPane().add(lblResults);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(29, 182, 418, 75);
		labratoryUI.getContentPane().add(scrollPane);

		JLabel lblAttachedImage = new JLabel("Attached Image");
		lblAttachedImage.setBounds(10, 268, 89, 14);
		labratoryUI.getContentPane().add(lblAttachedImage);

		JLabel btnAddImage = new JLabel("Attached Image");
		if (isEditable)
			btnAddImage.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
					int result = fileChooser.showOpenDialog(labratoryUI);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectedFile = fileChooser.getSelectedFile();
						exam.setFile(selectedFile.getName());
						ImageIcon ic = Resources.getImageFromPath(selectedFile.getPath());
						btnAddImage.setIcon(ic);
						lblAttachedImage.setText("");
					}
				}
			});
		btnAddImage.setHorizontalAlignment(SwingConstants.CENTER);
		btnAddImage.setForeground(Color.LIGHT_GRAY);
		btnAddImage.setBackground(Color.LIGHT_GRAY);

		ImageIcon image = (ImageIcon) ExaminationController.getImage(exam);
		if (image != null) {
			btnAddImage.setIcon(image);
			btnAddImage.setText("");
		}
		btnAddImage.setBounds(20, 293, 418, 271);
		labratoryUI.getContentPane().add(btnAddImage);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				exam.setResults(txtResult.getText());
				exam.setExaminationDate(creationDate);
				exam.setLabratorian(labratorian);
				ExaminationController.saveExamination(exam, (ImageIcon) btnAddImage.getIcon());
				Messages.successMessage("Examination results added", "Examinations", null);
				if (labratory != null)
					labratory.removeRow(row);
				labratoryUI.dispose();
			}
		});
		btnSave.setBounds(43, 575, 169, 23);
		labratoryUI.getContentPane().add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				labratoryUI.dispose();
			}
		});
		btnCancel.setBounds(222, 575, 200, 23);
		labratoryUI.getContentPane().add(btnCancel);

		labratoryUI.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));
		labratoryUI.setBounds(100, 100, 453, 638);
		labratoryUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		btnSave.setEnabled(isEditable);
		lblAttachedImage.setEnabled(isEditable);
		
				JTextPane txtResult = new JTextPane();
				txtResult.setBounds(46, 285, 416, 73);
				labratoryUI.getContentPane().add(txtResult);
				txtResult.setText(exam.getResults());
				txtResult.setEditable(isEditable);

	}
}
