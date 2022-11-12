package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import domain.Registered;
import domain.UserAdapter;

public class EgindakoApustuakGUI extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable taula;
	EgindakoApustuakGUI frame;

	/**
	 * Create the frame.
	 */
	public EgindakoApustuakGUI(Registered r) {
		this.frame = this;
		//frame.setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		UserAdapter use = new UserAdapter(r.getApustuAnitzak());
		
		taula = new JTable(use);
		contentPane.add(taula, BorderLayout.CENTER);
		taula.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(taula);
		scrollPane.setBounds(31, 5, 380, 280);
		scrollPane.setPreferredSize(new Dimension(380, 280));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.add(scrollPane);
		getContentPane().add(panel, BorderLayout.CENTER);
	}

}
