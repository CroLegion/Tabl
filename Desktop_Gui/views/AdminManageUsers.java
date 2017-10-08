package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.FlowLayout;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.TextArea;

import javax.swing.JTextPane;

import java.awt.Font;
import java.awt.Button;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.MatteBorder;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

import common.jdbc;
import common.User;
import common.Job;
import common.Qualification;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JSeparator;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SpringLayout;
import javax.swing.JTextArea;

public class AdminManageUsers extends JFrame {

	private JPanel contentPane;
	private JButton btn_create_new_user;
	private JButton btn_preferences;
	private JButton btn_settings;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textUsername;
	private JTextField textEmail;
	private JTextField textPhone;
	private JButton btnSaveChanges;
	int lastClickedIndex;
	int lastClickeduserID;
	String lastClickedUser;
	private JList listUsers;
	DefaultListModel userList = new DefaultListModel();

	//lists and models to display the qualifications on the gui to the user
	private JList listAvailableQuals;
	private JList listAssignedQuals;
	DefaultListModel availableQualList = new DefaultListModel();
	DefaultListModel assignedQualList = new DefaultListModel();
	private JButton assignQual;
	private JButton unassignQual;

	//The following ArrayLists are used to save the qualifications once a user is clicked
	ArrayList<Qualification> assignedQuals = new ArrayList<Qualification>();
	ArrayList<Qualification> availQuals = new ArrayList<Qualification>();
	private JPanel pnlCreateUser;
	private JLabel lblEnterUserInfo;
	private JLabel lblFirstName_1;
	private JButton btnCreateUser;
	private JLabel lblUserType;

	//lists and models to display the qualifications on the gui to the user
	private JButton btnAssign;
	private JButton btnRemove;
	private JList listUsersAdded;	
	private JList listUsersAvailable;	
	private JList listQualifications;
	DefaultListModel listedQualList = new DefaultListModel();
	ArrayList<Qualification> listedQuals = new ArrayList<Qualification>();
	
	
	//group for listing all users	
	private JButton buttonAssignUsers; 								//move users
	private JButton buttonRemoveUsers;								//move users
	private JList listAssignedUsers;								//list of assidned users
	private JList listAvailableUsers;								//list of avail. users
	
	//Group for allowing only one radio button to be clicked at one time
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnManager;
	private JRadioButton rdbtnWorker;
	//List of entry fields that are displayed after a user clicks the "Create User button"
	private JTextField txtCreateFirstName;
	private JTextField txtCreateLastName;
	private JTextField txtCreateUsername;
	private JTextField txtCreateEmailAddress;
	private JTextField txtCreatePhoneNumber;
	private JPasswordField txtCreatePassword;
	private JButton btnChangePassword;
	private JPanel pnlDeleteUser;
	private JButton btnDeleteUser;
	private JPanel pnlUserEditInfo;
	private JLayeredPane UserManagerPages;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtJobName;
	private JTextField textTaskName;
	private JTextField txtProjectName;
	private JButton btnCreateNewProject;
	private JButton btnCreateNewTask;	
	private JButton btn_create_project;
	private JButton btn_create_task;
	private JButton btn_create_job;
	private JButton btnCancelProject;
	private JButton btnCancelTask;
	private JButton btnCreateJob;
	private JButton btnCancelJob;	
	private JPanel pnlCreateProject;
	private JPanel pnlCreateTask;
	private JPanel pnlCreateJob;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminManageUsers frame = new AdminManageUsers();
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
	public AdminManageUsers() 
	{
		//creations a connection to the SQL server that is persistent until the GUI window is closed
		jdbc.openSQLConnection();
		initComponents();
		createEvents();
		
	}

	//This method contains all of the code for creating and intializing components.
	private void initComponents() {
		setBackground(Color.RED);
		setTitle("TABL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 951, 815);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setVisible(true);
		
		btn_settings = new JButton("Settings");
		btn_settings.setBounds(756, 5, 89, 28);
		btn_preferences = new JButton("Preferences");
		btn_preferences.setBounds(631, 5, 115, 28);
		
		btn_create_new_user = new JButton("Create New User");
		btn_create_new_user.setBounds(172, 5, 130, 28);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 43, 157, 720);
		
//creates the list of all the users on the left side of the window
		createUserList();
		listUsers = new JList(userList);
		
		scrollPane.setViewportView(listUsers);
		listUsers.setBackground(UIManager.getColor("Button.background"));
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listUsers.setLayoutOrientation(JList.VERTICAL);
		listUsers.setVisibleRowCount(1);
		contentPane.setLayout(null);
		
		Panel pnlUsers = new Panel();
		pnlUsers.setBounds(5, 5, 157, 28);
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		pnlUsers.add(lblUsers);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		contentPane.add(pnlUsers);
		contentPane.add(scrollPane);
		contentPane.add(btn_create_new_user);
		contentPane.add(btn_preferences);
		contentPane.add(btn_settings);
		
		UserManagerPages = new JLayeredPane();
		UserManagerPages.setBounds(170, 43, 746, 720);
		contentPane.add(UserManagerPages);
		UserManagerPages.setLayout(null);
		
		pnlCreateUser = new JPanel();
		pnlCreateUser.setVisible(false);
		UserManagerPages.setLayer(pnlCreateUser, 2);
		pnlCreateUser.setBounds(0, 0, 746, 720);
		UserManagerPages.add(pnlCreateUser);
		pnlCreateUser.setBackground(UIManager.getColor("Button.background"));
		pnlCreateUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(220, 20, 60), null, null, null));
		
		lblEnterUserInfo = new JLabel("Enter User Info");
		lblEnterUserInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblFirstName_1 = new JLabel("First Name:");
		lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnCreateUser = new JButton("Create User");
		
		btnCreateUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblUserType = new JLabel("User Type:");
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		rdbtnAdmin = new JRadioButton("Admin");
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rdbtnManager = new JRadioButton("Manager");
		buttonGroup.add(rdbtnManager);
		rdbtnManager.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rdbtnWorker = new JRadioButton("Worker");
		buttonGroup.add(rdbtnWorker);
		rdbtnWorker.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblLastName_1 = new JLabel("Last Name:");
		lblLastName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblEmailAddress_1 = new JLabel("Email Address:");
		lblEmailAddress_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number");
		lblPhoneNumber_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		txtCreateFirstName = new JTextField();
		txtCreateFirstName.setColumns(10);
		
		txtCreateLastName = new JTextField();
		txtCreateLastName.setColumns(10);
		
		txtCreateUsername = new JTextField();
		txtCreateUsername.setColumns(10);
		
		txtCreateEmailAddress = new JTextField();
		txtCreateEmailAddress.setColumns(10);
		
		txtCreatePhoneNumber = new JTextField();
		txtCreatePhoneNumber.setColumns(10);
		
		txtCreatePassword = new JPasswordField();
		
		btnCancel = new JButton("Cancel");

		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_pnlCreateUser = new GroupLayout(pnlCreateUser);
		gl_pnlCreateUser.setHorizontalGroup(
			gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateUser.createSequentialGroup()
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(311)
							.addComponent(lblEnterUserInfo))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(113)
							.addComponent(lblFirstName_1)
							.addGap(62)
							.addComponent(txtCreateFirstName, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(114)
							.addComponent(lblLastName_1)
							.addGap(62)
							.addComponent(txtCreateLastName, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(118)
							.addComponent(lblUsername_1)
							.addGap(62)
							.addComponent(txtCreateUsername, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(91)
							.addComponent(lblEmailAddress_1)
							.addGap(62)
							.addComponent(txtCreateEmailAddress, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(88)
							.addComponent(lblPhoneNumber_1)
							.addGap(62)
							.addComponent(txtCreatePhoneNumber, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(120)
							.addComponent(lblPassword)
							.addGap(62)
							.addComponent(txtCreatePassword, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(118)
							.addComponent(lblUserType)
							.addGap(33)
							.addComponent(rdbtnAdmin)
							.addGap(25)
							.addComponent(rdbtnManager)
							.addGap(31)
							.addComponent(rdbtnWorker))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(141)
							.addComponent(btnCreateUser, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
							.addGap(57)
							.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(191, Short.MAX_VALUE))
		);
		gl_pnlCreateUser.setVerticalGroup(
			gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateUser.createSequentialGroup()
					.addGap(18)
					.addComponent(lblEnterUserInfo)
					.addGap(18)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFirstName_1)
						.addComponent(txtCreateFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(3)
							.addComponent(lblLastName_1))
						.addComponent(txtCreateLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(3)
							.addComponent(lblUsername_1))
						.addComponent(txtCreateUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(3)
							.addComponent(lblEmailAddress_1))
						.addComponent(txtCreateEmailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPhoneNumber_1))
						.addComponent(txtCreatePhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(1)
							.addComponent(lblPassword))
						.addComponent(txtCreatePassword, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(67)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(1)
							.addComponent(lblUserType))
						.addComponent(rdbtnAdmin)
						.addComponent(rdbtnManager)
						.addComponent(rdbtnWorker))
					.addGap(33)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(3)
							.addComponent(btnCreateUser))
						.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
		);
		pnlCreateUser.setLayout(gl_pnlCreateUser);
//create user end				
//create project start		
		pnlCreateProject = new JPanel();
		pnlCreateProject.setVisible(false);
		UserManagerPages.setLayer(pnlCreateProject, 2);
		pnlCreateProject.setBounds(0, 0, 746, 720);
		UserManagerPages.add(pnlCreateProject);
		
		lblNewLabel = new JLabel("Project name:");
		
		lblNewLabel_1 = new JLabel("Description:");
		
		JScrollPane scrlPaneProjectDescription = new JScrollPane();
		
		JScrollPane scrlPaneQualifications = new JScrollPane();
		
		JScrollPane scrlPaneUsersAvailable = new JScrollPane();
		
		JScrollPane scrlPaneUsersAdded = new JScrollPane();
		
		JLabel lblNewLabel_2 = new JLabel("Qualifications:");
		
		JLabel lblNewLabel_3 = new JLabel("Users to add:");
		
		JLabel lblNewLabel_4 = new JLabel("Users added:");
		
		btnAssign = new JButton("->");
		btnRemove = new JButton("<-");
		
		JLabel lblCreateANew = new JLabel("Create a new Project");
		lblCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		btnCreateNewProject = new JButton("Create new Project");
		
		txtProjectName = new JTextField();
		txtProjectName.setColumns(10);
		
		btnCancelProject = new JButton("Cancel");
		GroupLayout gl_pnlCreateProject = new GroupLayout(pnlCreateProject);
		gl_pnlCreateProject.setHorizontalGroup(
			gl_pnlCreateProject.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCreateProject.createSequentialGroup()
					.addGap(147)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlPaneProjectDescription, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(144, Short.MAX_VALUE))
				.addGroup(gl_pnlCreateProject.createSequentialGroup()
					.addGap(89)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlPaneQualifications, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateProject.createSequentialGroup()
							.addComponent(scrlPaneUsersAvailable, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnRemove)
								.addComponent(btnAssign)))
						.addComponent(lblNewLabel_3))
					.addGap(32)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_4)
						.addComponent(scrlPaneUsersAdded, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
					.addGap(60))
				.addGroup(gl_pnlCreateProject.createSequentialGroup()
					.addContainerGap(299, Short.MAX_VALUE)
					.addComponent(lblCreateANew)
					.addGap(284))
				.addGroup(gl_pnlCreateProject.createSequentialGroup()
					.addContainerGap(356, Short.MAX_VALUE)
					.addComponent(btnCancelProject)
					.addGap(63)
					.addComponent(btnCreateNewProject)
					.addGap(113))
		);
		gl_pnlCreateProject.setVerticalGroup(
			gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateProject.createSequentialGroup()
					.addGap(29)
					.addComponent(lblCreateANew)
					.addGap(68)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel)
						.addComponent(txtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(47)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrlPaneProjectDescription, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1))
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateProject.createSequentialGroup()
							.addGap(55)
							.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(lblNewLabel_4)
								.addComponent(lblNewLabel_3))
							.addGap(18)
							.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.BASELINE)
									.addComponent(scrlPaneQualifications, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
									.addComponent(scrlPaneUsersAvailable, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrlPaneUsersAdded, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlCreateProject.createSequentialGroup()
							.addGap(122)
							.addComponent(btnAssign)
							.addGap(32)
							.addComponent(btnRemove)))
					.addGap(53)
					.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateNewProject)
						.addComponent(btnCancelProject))
					.addContainerGap(126, Short.MAX_VALUE))
		);
		
		JTextArea textAreaProjectDescription = new JTextArea();
		scrlPaneProjectDescription.setViewportView(textAreaProjectDescription);
		
		listUsersAdded = new JList();
		scrlPaneUsersAdded.setViewportView(listUsersAdded);
		
		listUsersAvailable = new JList();
		scrlPaneUsersAvailable.setViewportView(listUsersAvailable);
		
		listQualifications = new JList(listedQualList);
		listQualifications.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneQualifications.setViewportView(listQualifications);
		pnlCreateProject.setLayout(gl_pnlCreateProject);
//create project end
//Create task start			
		pnlCreateTask = new JPanel();
		pnlCreateTask.setVisible(false);
		UserManagerPages.setLayer(pnlCreateTask, 2);
		pnlCreateTask.setBounds(0, 0, 746, 720);
		UserManagerPages.add(pnlCreateTask);
		
		JLabel lblCreateTask = new JLabel("Create a new Task");
		lblCreateTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblTaskName = new JLabel("Name of the Task:");
		
		JLabel lblTaskDescription = new JLabel("Description of the Task:");
		
		JLabel lblTaskReason = new JLabel("Reason why it should be added:");
		
		JScrollPane scrlPaneDescription = new JScrollPane();
		
		JScrollPane scrlPaneReason = new JScrollPane();
		
		btnCreateNewTask = new JButton("Create new Task");
		
		textTaskName = new JTextField();
		textTaskName.setColumns(10);
		
		btnCancelTask = new JButton("Cancel");
		GroupLayout gl_pnlCreateTask = new GroupLayout(pnlCreateTask);
		gl_pnlCreateTask.setHorizontalGroup(
			gl_pnlCreateTask.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pnlCreateTask.createSequentialGroup()
					.addGap(97)
					.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblTaskName)
						.addComponent(lblTaskDescription)
						.addComponent(lblTaskReason))
					.addPreferredGap(ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
					.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlPaneReason, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrlPaneDescription, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
						.addComponent(textTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(164, Short.MAX_VALUE))
				.addGroup(gl_pnlCreateTask.createSequentialGroup()
					.addContainerGap(374, Short.MAX_VALUE)
					.addComponent(btnCancelTask)
					.addGap(45)
					.addComponent(btnCreateNewTask)
					.addGap(125))
				.addGroup(gl_pnlCreateTask.createSequentialGroup()
					.addContainerGap(301, Short.MAX_VALUE)
					.addComponent(lblCreateTask)
					.addGap(299))
		);
		gl_pnlCreateTask.setVerticalGroup(
			gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateTask.createSequentialGroup()
					.addGap(47)
					.addComponent(lblCreateTask)
					.addGap(52)
					.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTaskName)
						.addComponent(textTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(130)
					.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTaskDescription)
						.addComponent(scrlPaneDescription, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
					.addGap(73)
					.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlPaneReason, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTaskReason))
					.addGap(77)
					.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateNewTask)
						.addComponent(btnCancelTask))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		
		JTextArea txtAreaReason = new JTextArea();
		scrlPaneReason.setViewportView(txtAreaReason);
		
		JTextArea txtAreaDescription = new JTextArea();
		scrlPaneDescription.setViewportView(txtAreaDescription);
		pnlCreateTask.setLayout(gl_pnlCreateTask);
//create task end	
//edit user info start				
		pnlUserEditInfo = new JPanel();
		UserManagerPages.setLayer(pnlUserEditInfo, 3);
		pnlUserEditInfo.setBounds(0, 0, 746, 720);
		UserManagerPages.add(pnlUserEditInfo);
		pnlUserEditInfo.setBorder(new TitledBorder(null, "User Edit/Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFirstName = new JTextField();
		textFirstName.setColumns(10);
		textLastName = new JTextField();
		textLastName.setColumns(10);
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textPhone = new JTextField();
		textPhone.setColumns(10);
		
		btnSaveChanges = new JButton("Save Changes");
		
		btnSaveChanges.setToolTipText("Save Changes to Database");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		
		JScrollPane scrlPaneAvailableQuals = new JScrollPane();
		JScrollPane scrlPaneAssignedQuals = new JScrollPane();
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel lblAssigned = new JLabel("Assigned");
		lblAssigned.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		unassignQual = new JButton("<-");
		
		unassignQual.setToolTipText("Click to remove assigned Qualifications");
		unassignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		assignQual = new JButton("->");
		
		assignQual.setToolTipText("Click to move selected Qualifications to Assigned");
		assignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		listAssignedQuals = new JList(assignedQualList);
		scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
		
		listAvailableQuals = new JList(availableQualList);
		scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
		
		btnChangePassword = new JButton("Change Password");
		
		pnlDeleteUser = new JPanel();
		pnlDeleteUser.setBorder(new TitledBorder(null, "WARNING AREA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDeleteUser.setBackground(new Color(245, 222, 179));
		pnlDeleteUser.setLayout(null);
		
		btnDeleteUser = new JButton("DELETE \r\nUSER");

		btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDeleteUser.setBounds(24, 27, 120, 39);
		pnlDeleteUser.add(btnDeleteUser);
		GroupLayout gl_pnlUserEditInfo = new GroupLayout(pnlUserEditInfo);
		gl_pnlUserEditInfo.setHorizontalGroup(
			gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(258)
					.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(79)
					.addComponent(lblFirstName)
					.addGap(50)
					.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(80)
					.addComponent(lblLastName)
					.addGap(50)
					.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(84)
					.addComponent(lblUsername)
					.addGap(50)
					.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(57)
					.addComponent(lblEmailAddress)
					.addGap(50)
					.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(49)
					.addComponent(lblPhoneNumber)
					.addGap(50)
					.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(158)
					.addComponent(btnSaveChanges, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(37)
					.addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(16)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(61)
					.addComponent(lblAvailable)
					.addGap(191)
					.addComponent(lblAssigned, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(16)
					.addComponent(scrlPaneAvailableQuals, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(assignQual, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(unassignQual, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addGap(5)
					.addComponent(scrlPaneAssignedQuals, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
					.addGap(82)
					.addComponent(pnlDeleteUser, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
		);
		gl_pnlUserEditInfo.setVerticalGroup(
			gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblFirstName)
						.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(23)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblLastName)
						.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(25)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(3)
							.addComponent(lblUsername))
						.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(3)
							.addComponent(lblEmailAddress))
						.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(34)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(3)
							.addComponent(lblPhoneNumber))
						.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(28)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSaveChanges, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnChangePassword, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(32)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAvailable)
						.addComponent(lblAssigned))
					.addGap(11)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addComponent(scrlPaneAvailableQuals, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(85)
							.addComponent(assignQual)
							.addGap(29)
							.addComponent(unassignQual))
						.addComponent(scrlPaneAssignedQuals, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(182)
							.addComponent(pnlDeleteUser, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))))
		);
		pnlUserEditInfo.setLayout(gl_pnlUserEditInfo);
//edit user info end
//create job start				
		pnlCreateJob = new JPanel();
		pnlCreateJob.setVisible(false);
		UserManagerPages.setLayer(pnlCreateJob, 5);
		pnlCreateJob.setBounds(0, 0, 746, 720);
		UserManagerPages.add(pnlCreateJob);
		
		JLabel lblCreateNewJob = new JLabel("Create new Job");
		lblCreateNewJob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JLabel lblNewLabel_9 = new JLabel("Description:");
		
		JLabel lblNewLabel_10 = new JLabel("Assigned Manager:");
		
		JScrollPane scrlPaneJobDescription = new JScrollPane();
		
		JScrollPane scrlPaneAssignableManagers = new JScrollPane();
		
		JScrollPane scrlPaneRequiredQuals = new JScrollPane();
		
		JScrollPane scrlPaneAvailableUsers = new JScrollPane();
		
		JScrollPane scrlPaneAssignedUsers = new JScrollPane();
		
		listAssignedUsers = new JList();
		scrlPaneAssignedUsers.setViewportView(listAssignedUsers);
		
		JTextArea txtAreaJobDescription = new JTextArea();
		scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
		
		JList listAssignableManagers = new JList();
		scrlPaneAssignableManagers.setViewportView(listAssignableManagers);
		
		JLabel lblJobName = new JLabel("Name of the Job:");
		
		JLabel lblRequiredQualifications = new JLabel("Required Qualifications:");
		
		JList listRequiredQuals = new JList();
		scrlPaneRequiredQuals.setViewportView(listRequiredQuals);
		
		JLabel lblUsersList = new JLabel("User List:");
		
		listAvailableUsers = new JList(userList);
		scrlPaneAvailableUsers.setViewportView(listAvailableUsers);
		
		buttonAssignUsers = new JButton("->");
		
		buttonRemoveUsers = new JButton("<-");
		
		txtJobName = new JTextField();
		txtJobName.setColumns(10);
		
		JLabel lblAvailableUsers_1 = new JLabel("Available Users");
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		
		btnCreateJob = new JButton("Create Job");
		
		btnCancelJob  = new JButton("Cancel");
		GroupLayout gl_pnlCreateJob = new GroupLayout(pnlCreateJob);
		gl_pnlCreateJob.setHorizontalGroup(
			gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(302)
					.addComponent(lblCreateNewJob))
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(150)
					.addComponent(lblJobName)
					.addGap(162)
					.addComponent(txtJobName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(176)
					.addComponent(lblNewLabel_9)
					.addGap(162)
					.addComponent(scrlPaneJobDescription, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(141)
					.addComponent(lblNewLabel_10)
					.addGap(162)
					.addComponent(scrlPaneAssignableManagers, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(119)
					.addComponent(lblRequiredQualifications)
					.addGap(162)
					.addComponent(scrlPaneRequiredQuals, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(344)
					.addComponent(lblAvailableUsers_1)
					.addGap(133)
					.addComponent(lblAssignedUsers))
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(188)
					.addComponent(lblUsersList)
					.addGap(111)
					.addComponent(scrlPaneAvailableUsers, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addComponent(buttonAssignUsers)
						.addComponent(buttonRemoveUsers))
					.addGap(6)
					.addComponent(scrlPaneAssignedUsers, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
				.addGroup(Alignment.TRAILING, gl_pnlCreateJob.createSequentialGroup()
					.addContainerGap(491, Short.MAX_VALUE)
					.addComponent(btnCancelJob)
					.addGap(18)
					.addComponent(btnCreateJob)
					.addGap(59))
		);
		gl_pnlCreateJob.setVerticalGroup(
			gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateJob.createSequentialGroup()
					.addGap(22)
					.addComponent(lblCreateNewJob)
					.addGap(63)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addComponent(lblJobName)
						.addComponent(txtJobName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(39)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_9)
						.addComponent(scrlPaneJobDescription, GroupLayout.PREFERRED_SIZE, 69, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_10)
						.addComponent(scrlPaneAssignableManagers, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addComponent(lblRequiredQualifications)
						.addComponent(scrlPaneRequiredQuals, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAvailableUsers_1)
						.addComponent(lblAssignedUsers))
					.addGap(6)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateJob.createSequentialGroup()
							.addGap(2)
							.addComponent(lblUsersList))
						.addComponent(scrlPaneAvailableUsers, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_pnlCreateJob.createSequentialGroup()
							.addGap(27)
							.addComponent(buttonAssignUsers)
							.addGap(26)
							.addComponent(buttonRemoveUsers))
						.addComponent(scrlPaneAssignedUsers, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnCreateJob)
						.addComponent(btnCancelJob))
					.addContainerGap(22, Short.MAX_VALUE))
		);
		pnlCreateJob.setLayout(gl_pnlCreateJob);
		
		btn_create_project = new JButton("Create Project");
		btn_create_project.setBounds(304, 5, 115, 28);
		contentPane.add(btn_create_project);
		
		btn_create_task = new JButton("Create Task");
		btn_create_task.setBounds(417, 5, 115, 28);
		contentPane.add(btn_create_task);
		
		btn_create_job = new JButton("Create Job");
		btn_create_job.setBounds(535, 5, 97, 28);
		contentPane.add(btn_create_job);
//create job end			
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminManageUsers.class.getResource("/resources/Logo.PNG")));
		
	}
	
	//This method contains all of the code for creating events
	private void createEvents() {
		//called when the 'X' button of the GUI in the top right is clicked
		//Closes the SQL connection conn1
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jdbc.closeSQLConnection();
			}
		});
		//assigns users to a project
		btnAssign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
			}
		});
		//removes users from a project
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
			}
		});
		//creates a new project with the given inputs
		btnCreateNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				pnlCreateProject.setVisible(false);
			}
		});
		//creates a new Task with given inputs
		btnCreateNewTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		
				pnlCreateTask.setVisible(false);
			}
		});	
		//assign users to task
		buttonAssignUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
			}
		});
		//removes users from task
		buttonRemoveUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
			}
		});
		//Open create new project tab
		btn_create_project.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateUser.setVisible(false);
				pnlCreateProject.setVisible(true);
				UserManagerPages.setLayer(pnlUserEditInfo, 2);
				UserManagerPages.setLayer(pnlCreateProject, 3);	
				createQualificationsList();
				
			}
		});
		//closes create new project tab
		btnCancelProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateProject.setVisible(false);
			}
		});
		//open create new task tab
		btn_create_task.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateUser.setVisible(false);
				pnlCreateTask.setVisible(true);
				UserManagerPages.setLayer(pnlUserEditInfo, 2);
				UserManagerPages.setLayer(pnlCreateTask, 3);
				
			}
		});
		//closes create new task tab
		btnCancelTask.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateTask.setVisible(false);
			}
		});
		//open create new job tab
		btn_create_job.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateProject.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateUser.setVisible(false);
				pnlCreateJob.setVisible(true);
				UserManagerPages.setLayer(pnlUserEditInfo, 2);
				UserManagerPages.setLayer(pnlCreateJob, 3);	
				
			}
		});
		//closes create job tab
		btnCancelJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
			}
		});
		//creates a new job w/ info
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		//moves the create user panel to the front to allow the user to enter the new user info
		btn_create_new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateUser.setVisible(true);
				UserManagerPages.setLayer(pnlUserEditInfo, 2);
				UserManagerPages.setLayer(pnlCreateUser, 3);
				
			}
		});

		btn_preferences.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		btn_settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			}
		});
		//Display user information that was clicked on the left.
		listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  displayUserInfo(listUsers.getSelectedValue().toString());
                  //saves the index of the array that was clicked on
                  lastClickedIndex = listUsers.getSelectedIndex();
                  int id = jdbc.getIdOfUser(textUsername.getText());
                  //saved the userID of the user that is displayed
                  lastClickeduserID = id;
                }
            }
        });
		//Save changes made the user
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = jdbc.getIdOfUser(textUsername.getText());
				lastClickeduserID = id;
				try {
					jdbc.updateUser(id, textFirstName.getText(), textLastName.getText(), textUsername.getText(), textEmail.getText(), textPhone.getText());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				updateUserList();
			}
		});
		//Called when the -> button is clicked to add some qualifications to a user
		//all edits are done with the jdbc function assignQuals()
		//parameters are the userId, the ArrayList of available qualifications and the selected indices of the qualification list
		assignQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//parameters are the userId, the ArrayList of available qualifications and the selected indices of the qualification list
				jdbc.assignQuals(lastClickeduserID, availQuals, listAvailableQuals.getSelectedIndices());
				createQualLists(lastClickeduserID);
			}
		});
		//Called when the <- button is clicked to remove some qualifications from a user
		//all edits are done with the jdbc function UnassignQuals()
		unassignQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//parameters are the userId, the ArrayList of assigned qualifications and the selected indices of the qualification list
				jdbc.UnassignQuals(lastClickeduserID, assignedQuals, listAssignedQuals.getSelectedIndices());
				createQualLists(lastClickeduserID);
			}
		});
		//Send the information to the SQL server after the information in entered
		btnCreateUser.addActionListener(new ActionListener() {
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
					jdbc.add_user(usertype,txtCreateUsername.getText(),txtCreateFirstName.getText(), txtCreateLastName.getText(), txtCreateEmailAddress.getText(), txtCreatePhoneNumber.getText(), txtCreatePassword.getPassword().toString());
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(null, "User Created!");
				//hides the create user panel
				pnlCreateUser.setVisible(false);
				//Refreshes the list of user on the left side panel
				createUserList();
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateUser.setVisible(false);
			}
		});
		
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.deleteUser(lastClickeduserID);
			}
		});
	}

	//Query's the SQL database to get all users, then constructs a string "Lastname, Firstname [username]"
	//This string is then added to the userList that is displayed on the left panel
	private void createUserList() {
		userList.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			userList.addElement(String.format("%s, %s [%s]", users.get(i).get_lastname(), users.get(i).get_firstname(), users.get(i).get_username()));
		}
	}
	

	//This function takes the string from the userList and uses a regular expression to get the username between the []
	//then it gets the user from the database, and displays that information in the view user panel
	//it also calls the createQualList method that gets both assigned and available qualifications 

	private void displayUserInfo(String name) {
		String username = null;
		Pattern p = Pattern.compile("\\[(.*?)\\]");
		Matcher m = p.matcher(name);
		if (m.find())
		{
		    username = m.group(1);
		    lastClickedUser = username;
		}
		User u = jdbc.get_user(username);
		textFirstName.setText(u.get_firstname());
		textLastName.setText(u.get_lastname());
		textUsername.setText(u.get_username());
		if (u.get_email() != null) {textEmail.setText(u.get_email());} else {textEmail.setText("No Email Address in Database.");}
		if (u.get_phone() != null) {textPhone.setText(u.get_phone());} else {textPhone.setText("No Phone Number in Database.");}				
		createQualLists(u.get_userID());
	}
	

	//This function is used when a user is updated because their firstname, lastname, or username could have changed
	//meaning they need to be displayed correctly on the left side panel
	//it gets the new information from the server and then users the lastClickedIndex to update the string at that spot

	private void updateUserList() {
		User u = jdbc.get_user(lastClickedUser);
		String s = String.format("%s, %s [%s]", u.get_lastname(), u.get_firstname(), u.get_username());
		userList.setElementAt(s, lastClickedIndex);
	}
	
	//pulls all users and fills the list
	private void createUsersList(){
		//TODO
	}
	
	//pulls all qualifications and fills in the list
	private void createQualificationsList(){
		listedQualList.clear();
		listedQuals=jdbc.get_qualifications();
		for(Qualification q: listedQuals){
			listedQualList.addElement(q.getQualName());
		}
	}

	//Populates both the assigned and available qualification lists for a user after clicked on one
	//Is also called each time a qualification is assigned or unassigned to update the lists.

	private void createQualLists(int userID) {
		assignedQualList.clear();
		availableQualList.clear();
				
		assignedQuals = jdbc.getUserAssignedQuals(userID);
		for (Qualification q : assignedQuals) { 
			assignedQualList.addElement(q.getQualName());
		}
				
		availQuals = jdbc.getUserAvailQuals(userID);
		for (Qualification q : availQuals) { 
			availableQualList.addElement(q.getQualName());
		}
		
	}
}
