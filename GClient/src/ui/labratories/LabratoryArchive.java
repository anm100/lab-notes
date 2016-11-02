package ui.labratories;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.Image;

import Client.Application;
import Client.Config;
import Client.Resources;
import Controllers.ExaminationController;
import models.Examination;
import models.Labratorian;
import ui.utils.MyTableModel;

import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import java.awt.Component;
import java.awt.SystemColor;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;

/**
 * public class Laboratory, shows examinations to laboratory in order to check
 * and document test result
 * 
 * @author Bolous Abo Jaber , Ahmad Mnasra
 *
 */
public class LabratoryArchive {

	private JFrame labratoryUI;
	private JTable tblToday;
	private int ex_id;
	private ExaminationController ex_ctrl = new ExaminationController();

	public LabratoryArchive() {
		initialize();
		labratoryUI.setVisible(true);
		labratoryUI.setLocationRelativeTo(null);
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
		labratoryUI.setFont(new Font("Dialog", Font.PLAIN, 16));
		labratoryUI.setBackground(Color.WHITE);
		labratoryUI.getContentPane().setBackground(Color.WHITE);
		labratoryUI.getContentPane().setLayout(null);

		JLabel logo = new JLabel("Examinations Archive");
		logo.setBounds(0, 0, 645, 60);
		logo.setForeground(SystemColor.textHighlight);
		logo.setFont(new Font("Tahoma", Font.BOLD, 16));
		logo.setBackground(Color.WHITE);
		logo.setIcon(Resources.getIcon("logo.png"));
		labratoryUI.getContentPane().add(logo);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBounds(0, 66, 751, 36);
		labratoryUI.getContentPane().add(panel);

		JLabel label = new JLabel("Name:");
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(10, 11, 46, 21);
		panel.add(label);

		JLabel label_1 = new JLabel("<dynamic> <dynamic>");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_1.setBounds(61, 11, 207, 21);
		panel.add(label_1);

		label_1.setText(new String(labratorian.getFirstName() + " " + labratorian.getLastName()));

		JLabel label_3 = new JLabel((String) null);
		label_3.setHorizontalAlignment(SwingConstants.TRAILING);
		label_3.setVerticalAlignment(SwingConstants.TOP);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(223, 11, 90, 21);
		panel.add(label_3);

		JLabel label_2 = new JLabel("<dynamic> <dynamic>");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_2.setBounds(502, 11, 239, 21);
		panel.add(label_2);
		try {
			label_2.setText(new String("Tody:" + Utils.DateTime.currentDate().toString()));
		} catch (ParseException e) {
			Config.getConfig().getLogger().exception(e);
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 127, 741, 409);
		labratoryUI.getContentPane().add(scrollPane);

		tblToday = new JTable();
		scrollPane.setViewportView(tblToday);
		tblToday.setModel(
				new MyTableModel(new String[] { "id", "Doctor", "Patient", "Type", "Status" }, new Object[][] {}));

		tblToday.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent event) {
				if (event.getValueIsAdjusting())
					return;
				int row = tblToday.getSelectedRow();
				ex_id = (int) tblToday.getModel().getValueAt(row, 0);

				Examination ex = ExaminationController.getById(ex_id);
				ExamEditor edit = new ExamEditor(ex,row, false,null);

			}
		});

		JLabel lblTodaysExaminations = new JLabel("Examinations");
		lblTodaysExaminations.setBounds(0, 108, 170, 14);
		labratoryUI.getContentPane().add(lblTodaysExaminations);

		fillExaminations(tblToday);

		labratoryUI.getContentPane().setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[] { logo }));
		labratoryUI.setBounds(100, 100, 763, 576);
		labratoryUI.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * fill examination of laboratory to this table
	 * 
	 * @param tbl
	 *            : table of examination today
	 */
	public void fillExaminations(JTable tbl) {
		DefaultTableModel model = (DefaultTableModel) tbl.getModel();
		Labratorian lab = (Labratorian) Application.user;
		for (Examination e : ExaminationController.getExaminations(lab)) {
			if (e.getResults() != null) {
				model.addRow(new Object[] { e.getEid(), e.getTreatment().getDoctor().getFirstName(),
						e.getTreatment().getMedicalRecord().getPatient().getSid(), e.geteType(), "Closed" });
			}
		}
	}
}
