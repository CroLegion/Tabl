package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.omg.CORBA.PRIVATE_MEMBER;

import common.Qualification;
import common.jdbc;
import common.User;
import common.Job;

public class CreateProject extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private JButton addButton;
    private JButton removeButton;
    private JLabel lblQualification;
    private JLabel lblUserToAdd;
    private JLabel lblUsersAdded;
    private JLabel lblProjectName;
    private JLabel lblDescription;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_1;
    private JScrollPane scrollPane_2;
    private JScrollPane scrollPane_3;
    private JTextPane textPane;
    private JList qualifications;
	private JList availableUsers;
	private JList assignedUsers;	
	DefaultListModel selectableQualList = new DefaultListModel();
	DefaultListModel availableWorkersList = new DefaultListModel();
	DefaultListModel assignedWorkersList = new DefaultListModel();
	ArrayList<Qualification> selectableQuals = new ArrayList<Qualification>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProject frame = new CreateProject();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CreateProject() {
		initComponents();
		createEvents();
	}

	private void initComponents(){
		setTitle("TABL");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateUser.class.getResource("/resources/Logo.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 544);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblProjectName = new JLabel("Project Name:");
		lblDescription = new JLabel("Description:");
		lblQualification = new JLabel("Qualification:");		
		lblUserToAdd = new JLabel("Users to add:");		
		lblUsersAdded = new JLabel("Users added:");
		
		qualifications = new JList(selectableQualList);
		availableUsers = new JList(availableWorkersList);
		assignedUsers = new JList(assignedWorkersList);		

		textField = new JTextField();
		textField.setColumns(10);
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(qualifications);				
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setViewportView(availableUsers);
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setViewportView(assignedUsers);		
		
		addButton = new JButton("Add");			
		removeButton = new JButton("Remove");
			
		scrollPane_3 = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		
		textPane = new JTextPane();
		scrollPane_3.setViewportView(textPane);
		contentPane.setLayout(gl_contentPane);
		
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(94)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblQualification))
							.addGap(46)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addGap(1)
											.addComponent(addButton, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE))
										.addComponent(removeButton, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)))
								.addComponent(lblUserToAdd))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(lblUsersAdded)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(139)
									.addComponent(lblProjectName))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(153)
									.addComponent(lblDescription)))
							.addGap(214)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
								.addComponent(textField)
								.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE))))
					.addGap(50))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(75)
							.addComponent(lblProjectName))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(72)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblDescription)
						.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblQualification)
						.addComponent(lblUserToAdd)
						.addComponent(lblUsersAdded))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(addButton)
							.addGap(37)
							.addComponent(removeButton))
						.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)))
		);
		
		
	}
	private void createEvents(){
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
	}
	private void createQualsList(){
		selectableQualList.clear();
		
		selectableQuals = jdbc.get_qualifications();
		for (Qualification q : selectableQuals) { 
			selectableQualList.addElement(q.getQualName());
		}
	}
	
}
