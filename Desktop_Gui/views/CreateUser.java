package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import common.jdbc;

import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class CreateUser extends JFrame {

	private JPanel contentPane;
	private JTextField first_name;
	private JTextField username;
	private JTextField last_name;
	private JTextField email;
	private JTextField phone;
	private JPasswordField pass;
	private JButton create_user_button;
	private JLabel lblUserType;
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnManager;
	private JRadioButton rdbtnWorker;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JLabel lblUsername;
	private JLabel lblEmail;
	private JLabel lblPhoneNumber;
	private JLabel lblPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateUser frame = new CreateUser();
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
	public CreateUser() {
		setResizable(false);
		initComponents();
		createEvents();		
	}

	private void initComponents() {
		setTitle("TABL");
		setIconImage(Toolkit.getDefaultToolkit().getImage(CreateUser.class.getResource("/resources/Logo.PNG")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 659, 418);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		first_name = new JTextField();
		first_name.setColumns(10);
		
		username = new JTextField();
		username.setColumns(10);
		
		last_name = new JTextField();
		last_name.setColumns(10);
		
		email = new JTextField();
		email.setColumns(10);
		
		phone = new JTextField();
		phone.setColumns(10);
		
		pass = new JPasswordField();
		
		create_user_button = new JButton("Create User");
		
		JLabel lblNewLabel = new JLabel("Enter User Info");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lblUserType = new JLabel("User Type:");
		
		rdbtnAdmin = new JRadioButton("Admin");
		buttonGroup.add(rdbtnAdmin);
		
		rdbtnManager = new JRadioButton("Manager");
		buttonGroup.add(rdbtnManager);
		
		rdbtnWorker = new JRadioButton("Worker");
		buttonGroup.add(rdbtnWorker);
		
		lblFirstName = new JLabel("First Name");
		
		lblLastName = new JLabel("Last Name");
		
		lblUsername = new JLabel("Username:");
		
		lblEmail = new JLabel("Email Address");
		
		lblPhoneNumber = new JLabel("Phone Number");
		
		lblPassword = new JLabel("Password");
		
		GroupLayout groupLayout = new GroupLayout(contentPane);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(121)
									.addComponent(lblUserType))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(60)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(lblLastName)
										.addComponent(lblFirstName)
										.addComponent(lblUsername)
										.addComponent(lblEmail)
										.addComponent(lblPhoneNumber)
										.addComponent(lblPassword))))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addComponent(email)
									.addComponent(username)
									.addComponent(last_name, GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
									.addComponent(pass)
									.addComponent(phone, GroupLayout.DEFAULT_SIZE, 423, Short.MAX_VALUE)
									.addComponent(first_name))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(16)
									.addComponent(rdbtnAdmin)
									.addGap(18)
									.addComponent(rdbtnManager)
									.addGap(18)
									.addComponent(rdbtnWorker))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(220)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 168, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(38, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(297, Short.MAX_VALUE)
					.addComponent(create_user_button)
					.addGap(255))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFirstName)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(first_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(last_name, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLastName))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(username, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblUsername))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(phone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhoneNumber))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPassword))
							.addPreferredGap(ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUserType)
								.addComponent(rdbtnAdmin)
								.addComponent(rdbtnManager)
								.addComponent(rdbtnWorker))
							.addGap(18)
							.addComponent(create_user_button)
							.addGap(21))))
		);
		contentPane.setLayout(groupLayout);
		
	}
	private void createEvents() {
		create_user_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int usertype = 0;
				if (rdbtnAdmin.isSelected()) {
					usertype = 1;
				} else if (rdbtnManager.isSelected()) {
					usertype = 2;
				} else if (rdbtnWorker.isSelected()) {
					usertype = 3;
				}
				try {
					jdbc.add_user(usertype,username.getText(),first_name.getText(),last_name.getText(),email.getText(),phone.getText(),pass.getPassword().toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}
