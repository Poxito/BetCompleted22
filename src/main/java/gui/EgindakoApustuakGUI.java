package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import domain.User;
import domain.UserAdapter;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EgindakoApustuakGUI extends JFrame{

	private JPanel contentPane;
	private MainGUI main;
	private MainUserGUI mainUser;
	private EgindakoApustuakGUI apustuakFrame;
	private JTable table;
	private JScrollPane scrollPane;
	
	public EgindakoApustuakGUI() {
		
	}
}
