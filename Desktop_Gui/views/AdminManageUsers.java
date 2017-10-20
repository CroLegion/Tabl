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
import java.awt.SystemColor;
import java.awt.Component;

public class AdminManageUsers extends JFrame {
	private JLabel lblUsername_2;
	private JLabel lblPassword_1;
	private JLabel lblTitle;
	private JLabel lblTablLogin;	
	private JLabel lblPortal;
	private JLabel lblOpenTickets;
	private JLabel lblCompletedTickets;
	
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
	
	//project creation tab
	private JPanel pnlCreateProject;	
	private JButton btnAssign;
	private JButton btnRemove;
	private JList listUsersAdded;	
	private JList listUsersAvailable;	
	private JList listQualifications;
	DefaultListModel listedQualList = new DefaultListModel();
	ArrayList<Qualification> listedQuals = new ArrayList<Qualification>();
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton btnCreateNewProject;	
	private JButton btnCancelProject;
	private JTextArea textAreaProjectDescription;
	private JButton btn_create_project;	
			
	//task creation tab
	private JPanel pnlCreateTask;
	private JTextField textTaskName;
	private JButton btnCreateNewTask;	
	private JButton btnCancelTask;	
	private JTextArea txtAreaReason;	
	private JTextArea txtAreaDescription;
	private JButton btn_create_task;
	
	//job creation tab
	private JPanel pnlCreateJob;
	private JTextField txtJobName;
	private JButton btnCreateJob;
	private JButton btnCancelJob;	
	private JButton buttonAssignUsers; 								
	private JButton buttonRemoveUsers;
	private JButton btn_create_job;	
	private JTextArea txtAreaJobDescription;
	private JList listAssignableManagers;
	private JList listRequiredQuals;
	private JList listAssignedUsers;								
	private JList listAvailableUsers;

	//Layered Pane login
	private JButton btnLogin;
	private JPasswordField passwordLogin;
	private JTextField txtLoginUser;
	private JPanel pnl_login_components;
	private JPanel pnlLogin;
	private JLayeredPane layeredPaneLogin;
	private JLayeredPane layeredPaneLoginComponents;	
	
	//Layered Pane Admin
	private JLayeredPane layeredPaneAdmin;
	private JLayeredPane layeredPaneAdminComponents;
	private JPanel pnlAdmin;
	private JButton btn_create_new_user;
	private JButton btn_add_qualifications;
	private JButton btnViewTickets;
	private JPanel pnlOpenTicketsLbl;
	private JPanel pnlCompleteTicketsLbl;
	private JScrollPane scrlOpenTickets;
	private JScrollPane scrlCompletedTickets;									
	private JList listUsers;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textUsername;
	private JTextField textEmail;
	private JTextField textPhone;	
	private JTextField txtProjectName;	
	private JButton btnSaveChanges;	
	private JButton btnChangePassword;
	private JButton btnDeleteUser;
	private JButton btnCancel;	
	private JButton btnAssignUserQual;
	private JButton btnUnassignUserQual;
	private JButton btnCreateQual;
	private JButton btnCancelAddQualifcation;
	private JList listCreateQualAvailUsers;
	private JList listCreateQualAssignedUsers;
	private JTextArea txtNewQualificationDesc;
	private JTextField txtNewQualificationName;	
	
	//other or unassigned vars
	String lastClickedUser;
	DefaultListModel managerList = new DefaultListModel();
	DefaultListModel userList = new DefaultListModel();
	DefaultListModel userListAssignQual = new DefaultListModel();
	DefaultListModel userListAvailQual = new DefaultListModel();	
	int lastClickedIndex;
	int lastClickeduserID;
	private JButton btn_settings;
	private JButton btnLogout;
	private JPanel pnlCreateQualification;
	private JPanel contentPane;	
	private JPanel pnlViewTickets;	
	private JPanel pnlManagerWorker;
	private JPanel pnlDeleteUser;
	private JPanel pnlUserEditInfo;
	private JLayeredPane layeredPane_1;	
	private JLayeredPane layeredPaneManagerWorkerComponents;
	private JLayeredPane layeredPane;
	private JLayeredPane layeredPaneManagerWorker;
	
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
		setBounds(100, 100, 974, 842);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setVisible(true);
		
//creates the list of all the users on the left side of the window
		createUserList();
		contentPane.setLayout(null);
		createUserListQual();
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 40, 941, 757);
		contentPane.add(layeredPane);
		
		layeredPaneAdmin = new JLayeredPane();
		layeredPaneAdmin.setBackground(new Color(100, 149, 237));
		layeredPane.setLayer(layeredPaneAdmin, 20);
		layeredPaneAdmin.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneAdmin);
		
		pnlAdmin = new JPanel();
		layeredPaneAdmin.setLayer(pnlAdmin, 0);
		pnlAdmin.setBounds(0, 0, 947, 760);
		layeredPaneAdmin.add(pnlAdmin);
		
		layeredPaneAdminComponents = new JLayeredPane();
		
		Panel pnlUsers = new Panel();
		layeredPaneAdminComponents.setLayer(pnlUsers, 1);
		pnlUsers.setEnabled(false);
		pnlUsers.setBounds(0, 0, 157, 28);
		layeredPaneAdminComponents.add(pnlUsers);
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		GroupLayout gl_pnlUsers = new GroupLayout(pnlUsers);
		gl_pnlUsers.setHorizontalGroup(
			gl_pnlUsers.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUsers.createSequentialGroup()
					.addGap(58)
					.addComponent(lblUsers))
		);
		gl_pnlUsers.setVerticalGroup(
			gl_pnlUsers.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUsers.createSequentialGroup()
					.addGap(5)
					.addComponent(lblUsers))
		);
		pnlUsers.setLayout(gl_pnlUsers);
		
		pnlCreateQualification = new JPanel();
		pnlCreateQualification.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlCreateQualification);
		pnlCreateQualification.setVisible(false);
		
		JLabel lblCreateQualification = new JLabel("Create New Qualification");
		lblCreateQualification.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAssignToUsers = new JLabel("Assign to Users");
		lblAssignToUsers.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAvailable_1 = new JLabel("Available");
		lblAvailable_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblAssigned_1 = new JLabel("Assigned");
		lblAssigned_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtNewQualificationName = new JTextField();
		txtNewQualificationName.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		txtNewQualificationDesc = new JTextArea();
		scrollPane_1.setViewportView(txtNewQualificationDesc);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		listCreateQualAvailUsers = new JList(userListAvailQual);
		scrollPane_2.setViewportView(listCreateQualAvailUsers);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		
		listCreateQualAssignedUsers = new JList(userListAssignQual);
		scrollPane_3.setViewportView(listCreateQualAssignedUsers);
		
		btnAssignUserQual = new JButton("->");
		
				btnAssignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				btnUnassignUserQual = new JButton("<-");
				
						btnUnassignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
						
						btnCreateQual = new JButton("CREATE");
						
								btnCreateQual.setFont(new Font("Tahoma", Font.BOLD, 16));
								
								btnCancelAddQualifcation = new JButton("Cancel");

								btnCancelAddQualifcation.setFont(new Font("Tahoma", Font.BOLD, 16));
								GroupLayout gl_pnlCreateQualification = new GroupLayout(pnlCreateQualification);
								gl_pnlCreateQualification.setHorizontalGroup(
									gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(246)
											.addComponent(lblCreateQualification, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(111)
											.addComponent(lblTitle, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
											.addGap(73)
											.addComponent(txtNewQualificationName, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(61)
											.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
											.addGap(59)
											.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 387, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(247)
											.addComponent(lblAssignToUsers, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(121)
											.addComponent(lblAvailable_1, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
											.addGap(281)
											.addComponent(lblAssigned_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(80)
											.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
											.addGap(37)
											.addGroup(gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
												.addComponent(btnAssignUserQual, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnUnassignUserQual, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
											.addGap(46)
											.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(271)
											.addComponent(btnCreateQual, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(btnCancelAddQualifcation, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
								);
								gl_pnlCreateQualification.setVerticalGroup(
									gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_pnlCreateQualification.createSequentialGroup()
											.addGap(11)
											.addComponent(lblCreateQualification, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
											.addGap(69)
											.addGroup(gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
												.addComponent(lblTitle)
												.addGroup(gl_pnlCreateQualification.createSequentialGroup()
													.addGap(2)
													.addComponent(txtNewQualificationName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
											.addGap(40)
											.addGroup(gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
												.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_pnlCreateQualification.createSequentialGroup()
													.addGap(1)
													.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)))
											.addGap(25)
											.addComponent(lblAssignToUsers, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
											.addGap(2)
											.addGroup(gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
												.addComponent(lblAvailable_1)
												.addComponent(lblAssigned_1))
											.addGap(11)
											.addGroup(gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE)
												.addGroup(gl_pnlCreateQualification.createSequentialGroup()
													.addGap(106)
													.addComponent(btnAssignUserQual, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
													.addGap(51)
													.addComponent(btnUnassignUserQual, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
												.addComponent(scrollPane_3, GroupLayout.PREFERRED_SIZE, 287, GroupLayout.PREFERRED_SIZE))
											.addGap(42)
											.addGroup(gl_pnlCreateQualification.createParallelGroup(Alignment.LEADING)
												.addComponent(btnCreateQual, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnCancelAddQualifcation, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
								);
								pnlCreateQualification.setLayout(gl_pnlCreateQualification);
								
								pnlCreateUser = new JPanel();
								layeredPaneAdminComponents.setLayer(pnlCreateUser, 0);
								pnlCreateUser.setBounds(180, 38, 746, 720);
								layeredPaneAdminComponents.add(pnlCreateUser);
								pnlCreateUser.setVisible(false);
								pnlCreateUser.setBackground(UIManager.getColor("Button.background"));
								pnlCreateUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(220, 20, 60), null, null, null));
								
								lblEnterUserInfo = new JLabel("Enter User Info");
								lblEnterUserInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
								
								lblFirstName_1 = new JLabel("First Name:");
								lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
								
								btnCreateUser = new JButton("Create User");
								
								btnCreateUser.setFont(new Font("Tahoma", Font.BOLD, 16));
								
								rdbtnAdmin = new JRadioButton("Admin");
								buttonGroup.add(rdbtnAdmin);
								rdbtnAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
								
								rdbtnManager = new JRadioButton("Manager");
								buttonGroup.add(rdbtnManager);
								rdbtnManager.setFont(new Font("Tahoma", Font.BOLD, 11));
								
								rdbtnWorker = new JRadioButton("Worker");
								buttonGroup.add(rdbtnWorker);
								rdbtnWorker.setFont(new Font("Tahoma", Font.BOLD, 11));
								
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
										
										lblUserType = new JLabel("User Type:");
										lblUserType.setFont(new Font("Tahoma", Font.BOLD, 14));
										GroupLayout gl_pnlCreateUser = new GroupLayout(pnlCreateUser);
										gl_pnlCreateUser.setHorizontalGroup(
											gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
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
													.addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE))
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
										//create task end	
										//edit user info start				
												pnlUserEditInfo = new JPanel();
												layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 0);
												pnlUserEditInfo.setBounds(180, 38, 746, 720);
												layeredPaneAdminComponents.add(pnlUserEditInfo);
												pnlUserEditInfo.setBorder(new TitledBorder(null, "User Edit/Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
												JLabel lblFullName = new JLabel("Full Name");
												lblFullName.setFont(new Font("Tahoma", Font.BOLD, 20));
												
												JLabel lblFirstName = new JLabel("First Name:");
												lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
												
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
												
												JSeparator separator = new JSeparator();
												separator.setBackground(Color.BLACK);
												separator.setForeground(Color.BLACK);
												JScrollPane scrlPaneAssignedQuals = new JScrollPane();
												
												unassignQual = new JButton("<-");
												
												unassignQual.setToolTipText("Click to remove assigned Qualifications");
												unassignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
												
												assignQual = new JButton("->");
												
												assignQual.setToolTipText("Click to move selected Qualifications to Assigned");
												assignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
												
												
												
												listAssignedQuals = new JList(assignedQualList);
												scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
														
														JLabel lblLastName = new JLabel("Last Name:");
														lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
														
														JLabel lblUsername = new JLabel("Username:");
														lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
														
														JLabel lblEmailAddress = new JLabel("Email Address:");
														lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
														
														JLabel lblPhoneNumber = new JLabel("Phone Number:");
														lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
														
														JLabel lblAvailable = new JLabel("Available");
														lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 14));
														JLabel lblAssigned = new JLabel("Assigned");
														lblAssigned.setFont(new Font("Tahoma", Font.BOLD, 14));
														
														btnChangePassword = new JButton("Change Password");
														
														btnSaveChanges = new JButton("Save Changes");
														
														btnSaveChanges.setToolTipText("Save Changes to Database");
														
														JScrollPane scrlPaneAvailableQuals = new JScrollPane();
														
														listAvailableQuals = new JList(availableQualList);
														scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
														
														pnlDeleteUser = new JPanel();
														pnlDeleteUser.setBorder(new TitledBorder(null, "WARNING AREA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
														pnlDeleteUser.setBackground(new Color(245, 222, 179));
														pnlDeleteUser.setLayout(null);
														
														btnDeleteUser = new JButton("DELETE USER");
														
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
														
														pnlViewTickets = new JPanel();
														layeredPaneAdminComponents.setLayer(pnlViewTickets, 0);
														pnlViewTickets.setBounds(180, 38, 746, 720);
														layeredPaneAdminComponents.add(pnlViewTickets);
														pnlViewTickets.setVisible(false);
														
														pnlOpenTicketsLbl = new JPanel();
														pnlOpenTicketsLbl.setBackground(UIManager.getColor("Button.shadow"));
														pnlOpenTicketsLbl.setLayout(null);
														
														lblOpenTickets = new JLabel("Open Tickets");
														lblOpenTickets.setFont(new Font("Tahoma", Font.BOLD, 14));
														lblOpenTickets.setBounds(312, 0, 189, 15);
														pnlOpenTicketsLbl.add(lblOpenTickets);
														
														pnlCompleteTicketsLbl = new JPanel();
														pnlCompleteTicketsLbl.setBackground(SystemColor.controlShadow);
														pnlCompleteTicketsLbl.setLayout(null);
														
														lblCompletedTickets = new JLabel("Completed Tickets");
														lblCompletedTickets.setFont(new Font("Tahoma", Font.BOLD, 14));
														lblCompletedTickets.setBounds(302, 0, 189, 26);
														pnlCompleteTicketsLbl.add(lblCompletedTickets);
														
														scrlOpenTickets = new JScrollPane();
														
														scrlCompletedTickets = new JScrollPane();
														GroupLayout gl_pnlViewTickets = new GroupLayout(pnlViewTickets);
														gl_pnlViewTickets.setHorizontalGroup(
															gl_pnlViewTickets.createParallelGroup(Alignment.LEADING)
																.addComponent(scrlOpenTickets, GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE)
																.addComponent(pnlOpenTicketsLbl, GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE)
																.addComponent(pnlCompleteTicketsLbl, GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE)
																.addComponent(scrlCompletedTickets, GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE)
														);
														gl_pnlViewTickets.setVerticalGroup(
															gl_pnlViewTickets.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlViewTickets.createSequentialGroup()
																	.addGroup(gl_pnlViewTickets.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_pnlViewTickets.createSequentialGroup()
																			.addGap(22)
																			.addComponent(scrlOpenTickets, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE))
																		.addComponent(pnlOpenTicketsLbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
																	.addGroup(gl_pnlViewTickets.createParallelGroup(Alignment.LEADING)
																		.addComponent(pnlCompleteTicketsLbl, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)
																		.addGroup(gl_pnlViewTickets.createSequentialGroup()
																			.addGap(25)
																			.addComponent(scrlCompletedTickets, GroupLayout.PREFERRED_SIZE, 366, GroupLayout.PREFERRED_SIZE))))
														);
														pnlViewTickets.setLayout(gl_pnlViewTickets);
														
														JScrollPane scrollPane = new JScrollPane();
														scrollPane.setBounds(0, 38, 157, 720);
														layeredPaneAdminComponents.add(scrollPane);
														listUsers = new JList(userList);
														
														scrollPane.setViewportView(listUsers);
														listUsers.setBackground(UIManager.getColor("Button.background"));
														listUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
														listUsers.setLayoutOrientation(JList.VERTICAL);
														listUsers.setVisibleRowCount(1);
														
														btn_create_new_user = new JButton("Create New User");
														btn_create_new_user.setBounds(177, 0, 130, 28);
														layeredPaneAdminComponents.add(btn_create_new_user);
														btn_add_qualifications = new JButton("Add Qualifications");
														btn_add_qualifications.setBounds(678, 0, 150, 28);
														layeredPaneAdminComponents.add(btn_add_qualifications);
														
														btnViewTickets = new JButton("Ticket Viewer");
														
																												btnViewTickets.setBounds(337, 5, 130, 23);
																												layeredPaneAdminComponents.add(btnViewTickets);
																												GroupLayout gl_pnlAdmin = new GroupLayout(pnlAdmin);
																												gl_pnlAdmin.setHorizontalGroup(
																													gl_pnlAdmin.createParallelGroup(Alignment.LEADING)
																														.addComponent(layeredPaneAdminComponents, GroupLayout.PREFERRED_SIZE, 937, GroupLayout.PREFERRED_SIZE)
																												);
																												gl_pnlAdmin.setVerticalGroup(
																													gl_pnlAdmin.createParallelGroup(Alignment.LEADING)
																														.addComponent(layeredPaneAdminComponents, GroupLayout.PREFERRED_SIZE, 760, GroupLayout.PREFERRED_SIZE)
																												);
																												pnlAdmin.setLayout(gl_pnlAdmin);
		
		layeredPaneLogin = new JLayeredPane();
		layeredPane.setLayer(layeredPaneLogin, 10);
		layeredPaneLogin.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneLogin);
		
		pnlLogin = new JPanel();
		layeredPaneLogin.setLayer(pnlLogin, 10);
		pnlLogin.setBounds(0, 0, 941, 760);
		layeredPaneLogin.add(pnlLogin);
		
		layeredPaneLoginComponents = new JLayeredPane();
		
		pnl_login_components = new JPanel();
		pnl_login_components.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_login_components.setBounds(134, 109, 639, 354);
		layeredPaneLoginComponents.add(pnl_login_components);
		
		btnLogin = new JButton("Login");
		
				btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				passwordLogin = new JPasswordField();
				
				lblPassword_1 = new JLabel("Password:");
				lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblUsername_2 = new JLabel("Username:");
				lblUsername_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblTablLogin = new JLabel("TABL LOGIN");
				lblTablLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
				
				txtLoginUser = new JTextField();
				txtLoginUser.setColumns(10);
				GroupLayout gl_pnl_login_components = new GroupLayout(pnl_login_components);
				gl_pnl_login_components.setHorizontalGroup(
					gl_pnl_login_components.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_login_components.createSequentialGroup()
							.addGap(190)
							.addComponent(lblTablLogin, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnl_login_components.createSequentialGroup()
							.addGap(130)
							.addComponent(lblUsername_2, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(txtLoginUser, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnl_login_components.createSequentialGroup()
							.addGap(130)
							.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
							.addGap(65)
							.addComponent(passwordLogin, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnl_login_components.createSequentialGroup()
							.addGap(302)
							.addComponent(btnLogin, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
				);
				gl_pnl_login_components.setVerticalGroup(
					gl_pnl_login_components.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnl_login_components.createSequentialGroup()
							.addGap(16)
							.addComponent(lblTablLogin, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
							.addGap(60)
							.addGroup(gl_pnl_login_components.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnl_login_components.createSequentialGroup()
									.addGap(1)
									.addComponent(lblUsername_2, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtLoginUser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(65)
							.addGroup(gl_pnl_login_components.createParallelGroup(Alignment.LEADING)
								.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordLogin, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
							.addGap(64)
							.addComponent(btnLogin))
				);
				pnl_login_components.setLayout(gl_pnl_login_components);
				GroupLayout gl_pnlLogin = new GroupLayout(pnlLogin);
				gl_pnlLogin.setHorizontalGroup(
					gl_pnlLogin.createParallelGroup(Alignment.LEADING)
						.addComponent(layeredPaneLoginComponents, GroupLayout.PREFERRED_SIZE, 941, GroupLayout.PREFERRED_SIZE)
				);
				gl_pnlLogin.setVerticalGroup(
					gl_pnlLogin.createParallelGroup(Alignment.LEADING)
						.addComponent(layeredPaneLoginComponents, GroupLayout.PREFERRED_SIZE, 760, GroupLayout.PREFERRED_SIZE)
				);
				pnlLogin.setLayout(gl_pnlLogin);
		
		layeredPaneManagerWorker = new JLayeredPane();
		layeredPane.setLayer(layeredPaneManagerWorker, 0);
		layeredPaneManagerWorker.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneManagerWorker);
		
		pnlManagerWorker = new JPanel();
		layeredPaneManagerWorker.setLayer(pnlManagerWorker, 20);
		pnlManagerWorker.setBounds(0, 0, 941, 760);
		layeredPaneManagerWorker.add(pnlManagerWorker);
		
		btn_create_project = new JButton("Create Project");
		
		btn_create_task = new JButton("Create Task");
		
		btn_create_job = new JButton("Create Job");
		
		layeredPaneManagerWorkerComponents = new JLayeredPane();
												//create project end
												//Create task start			
														pnlCreateTask = new JPanel();
														layeredPaneManagerWorkerComponents.setLayer(pnlCreateTask, 0);
														pnlCreateTask.setBounds(0, 0, 746, 720);
														layeredPaneManagerWorkerComponents.add(pnlCreateTask);
														pnlCreateTask.setVisible(false);
														
														JLabel lblTaskName = new JLabel("Name of the Task:");
														
														JLabel lblTaskDescription = new JLabel("Description of the Task:");
														
														JLabel lblTaskReason = new JLabel("Reason why it should be added:");
														
														JScrollPane scrlPaneDescription = new JScrollPane();
														
														JScrollPane scrlPaneReason = new JScrollPane();
														
														btnCreateNewTask = new JButton("Create new Task");
														
														textTaskName = new JTextField();
														textTaskName.setColumns(10);
														
														btnCancelTask = new JButton("Cancel");
														
														txtAreaReason = new JTextArea();
														scrlPaneReason.setViewportView(txtAreaReason);
														
														txtAreaDescription = new JTextArea();
														scrlPaneDescription.setViewportView(txtAreaDescription);
														
														JLabel lblCreateTask = new JLabel("Create a new Task");
														lblCreateTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
														GroupLayout gl_pnlCreateTask = new GroupLayout(pnlCreateTask);
														gl_pnlCreateTask.setHorizontalGroup(
															gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlCreateTask.createSequentialGroup()
																	.addGap(301)
																	.addComponent(lblCreateTask))
																.addGroup(gl_pnlCreateTask.createSequentialGroup()
																	.addGap(163)
																	.addComponent(lblTaskName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
																	.addGap(89)
																	.addComponent(textTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateTask.createSequentialGroup()
																	.addGap(137)
																	.addComponent(lblTaskDescription, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
																	.addGap(89)
																	.addComponent(scrlPaneDescription, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateTask.createSequentialGroup()
																	.addGap(97)
																	.addComponent(lblTaskReason, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
																	.addGap(89)
																	.addComponent(scrlPaneReason, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateTask.createSequentialGroup()
																	.addGap(398)
																	.addComponent(btnCancelTask, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
																	.addGap(24)
																	.addComponent(btnCreateNewTask, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
														);
														gl_pnlCreateTask.setVerticalGroup(
															gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlCreateTask.createSequentialGroup()
																	.addGap(47)
																	.addComponent(lblCreateTask)
																	.addGap(52)
																	.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_pnlCreateTask.createSequentialGroup()
																			.addGap(3)
																			.addComponent(lblTaskName))
																		.addComponent(textTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																	.addGap(130)
																	.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_pnlCreateTask.createSequentialGroup()
																			.addGap(6)
																			.addComponent(lblTaskDescription))
																		.addComponent(scrlPaneDescription, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE))
																	.addGap(73)
																	.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTaskReason)
																		.addComponent(scrlPaneReason, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
																	.addGap(77)
																	.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
																		.addComponent(btnCancelTask)
																		.addComponent(btnCreateNewTask)))
														);
														pnlCreateTask.setLayout(gl_pnlCreateTask);
										//create user end				
										//create project start		
												pnlCreateProject = new JPanel();
												layeredPaneManagerWorkerComponents.setLayer(pnlCreateProject, 0);
												pnlCreateProject.setBounds(0, 0, 746, 720);
												layeredPaneManagerWorkerComponents.add(pnlCreateProject);
												pnlCreateProject.setVisible(false);
												
												lblNewLabel = new JLabel("Project name:");
												
												lblNewLabel_1 = new JLabel("Description:");
												
												JScrollPane scrlPaneProjectDescription = new JScrollPane();
												
												JScrollPane scrlPaneQualifications = new JScrollPane();
												
												JScrollPane scrlPaneUsersAvailable = new JScrollPane();
												
												btnAssign = new JButton("->");
												btnRemove = new JButton("<-");
												
												btnCreateNewProject = new JButton("Create new Project");
												
												btnCancelProject = new JButton("Cancel");
												
												textAreaProjectDescription = new JTextArea();
												scrlPaneProjectDescription.setViewportView(textAreaProjectDescription);
												
												listUsersAvailable = new JList();
												scrlPaneUsersAvailable.setViewportView(listUsersAvailable);
												
												listQualifications = new JList(listedQualList);
												listQualifications.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
												scrlPaneQualifications.setViewportView(listQualifications);
												
												JLabel lblNewLabel_2 = new JLabel("Qualifications:");
												
												JLabel lblNewLabel_3 = new JLabel("Users to add:");
												
												JLabel lblNewLabel_4 = new JLabel("Users added:");
												
												JLabel lblCreateANew = new JLabel("Create a new Project");
												lblCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
												
												txtProjectName = new JTextField();
												txtProjectName.setColumns(10);
												
												JScrollPane scrlPaneUsersAdded = new JScrollPane();
												
												listUsersAdded = new JList();
												scrlPaneUsersAdded.setViewportView(listUsersAdded);
												GroupLayout gl_pnlCreateProject = new GroupLayout(pnlCreateProject);
												gl_pnlCreateProject.setHorizontalGroup(
													gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(299)
															.addComponent(lblCreateANew))
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(149)
															.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
															.addGap(162)
															.addComponent(txtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(157)
															.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
															.addGap(162)
															.addComponent(scrlPaneProjectDescription, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(111)
															.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
															.addGap(76)
															.addComponent(lblNewLabel_3, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
															.addGap(173)
															.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(111)
															.addComponent(scrlPaneQualifications, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
															.addGap(18)
															.addComponent(scrlPaneUsersAvailable, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE)
															.addGap(18)
															.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
																.addComponent(btnAssign)
																.addComponent(btnRemove))
															.addGap(32)
															.addComponent(scrlPaneUsersAdded, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(380)
															.addComponent(btnCancelProject, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
															.addGap(46)
															.addComponent(btnCreateNewProject, GroupLayout.PREFERRED_SIZE, 154, GroupLayout.PREFERRED_SIZE))
												);
												gl_pnlCreateProject.setVerticalGroup(
													gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_pnlCreateProject.createSequentialGroup()
															.addGap(29)
															.addComponent(lblCreateANew)
															.addGap(68)
															.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlCreateProject.createSequentialGroup()
																	.addGap(6)
																	.addComponent(lblNewLabel))
																.addComponent(txtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
															.addGap(47)
															.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlCreateProject.createSequentialGroup()
																	.addGap(6)
																	.addComponent(lblNewLabel_1))
																.addComponent(scrlPaneProjectDescription, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
															.addGap(55)
															.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel_2)
																.addComponent(lblNewLabel_3)
																.addComponent(lblNewLabel_4))
															.addGap(18)
															.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
																.addComponent(scrlPaneQualifications, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
																.addComponent(scrlPaneUsersAvailable, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
																.addGroup(gl_pnlCreateProject.createSequentialGroup()
																	.addGap(35)
																	.addComponent(btnAssign)
																	.addGap(32)
																	.addComponent(btnRemove))
																.addComponent(scrlPaneUsersAdded, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
															.addGap(53)
															.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
																.addComponent(btnCancelProject)
																.addComponent(btnCreateNewProject)))
												);
												pnlCreateProject.setLayout(gl_pnlCreateProject);
												//edit user info end
												//create job start				
														pnlCreateJob = new JPanel();
														layeredPaneManagerWorkerComponents.setLayer(pnlCreateJob, 10);
														pnlCreateJob.setBounds(0, 0, 746, 720);
														layeredPaneManagerWorkerComponents.add(pnlCreateJob);
														pnlCreateJob.setVisible(false);
														
														JLabel lblCreateNewJob = new JLabel("Create new Job");
														lblCreateNewJob.setFont(new Font("Tahoma", Font.PLAIN, 18));
														
														JScrollPane scrlPaneJobDescription = new JScrollPane();
														
														JScrollPane scrlPaneAssignableManagers = new JScrollPane();
														
														JScrollPane scrlPaneRequiredQuals = new JScrollPane();
														
														JScrollPane scrlPaneAvailableUsers = new JScrollPane();
														
														txtAreaJobDescription = new JTextArea();
														scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
														
														listAssignableManagers = new JList(managerList);
														scrlPaneAssignableManagers.setViewportView(listAssignableManagers);
														
														JLabel lblJobName = new JLabel("Name of the Job:");
														
														listRequiredQuals = new JList();
														scrlPaneRequiredQuals.setViewportView(listRequiredQuals);
														
														listAvailableUsers = new JList(userList);
														scrlPaneAvailableUsers.setViewportView(listAvailableUsers);
														
														buttonAssignUsers = new JButton("->");
														
														buttonRemoveUsers = new JButton("<-");
														
														txtJobName = new JTextField();
														txtJobName.setColumns(10);
														
														btnCreateJob = new JButton("Create Job");
														
														btnCancelJob  = new JButton("Cancel");
														
														JLabel lblAvailableUsers_1 = new JLabel("Available Users");
														
														JLabel lblAssignedUsers = new JLabel("Assigned Users");
														
														JLabel lblUsersList = new JLabel("User List:");
														
														JLabel lblNewLabel_9 = new JLabel("Description:");
														
														JLabel lblNewLabel_10 = new JLabel("Assigned Manager:");
														
														JLabel lblRequiredQualifications = new JLabel("Required Qualifications:");
														
														JScrollPane scrlPaneAssignedUsers = new JScrollPane();
														
														listAssignedUsers = new JList();
														scrlPaneAssignedUsers.setViewportView(listAssignedUsers);
														GroupLayout gl_pnlCreateJob = new GroupLayout(pnlCreateJob);
														gl_pnlCreateJob.setHorizontalGroup(
															gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(302)
																	.addComponent(lblCreateNewJob))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(150)
																	.addComponent(lblJobName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
																	.addGap(131)
																	.addComponent(txtJobName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(176)
																	.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
																	.addGap(131)
																	.addComponent(scrlPaneJobDescription, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(141)
																	.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
																	.addGap(131)
																	.addComponent(scrlPaneAssignableManagers, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(119)
																	.addComponent(lblRequiredQualifications, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
																	.addGap(131)
																	.addComponent(scrlPaneRequiredQuals, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(344)
																	.addComponent(lblAvailableUsers_1, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
																	.addGap(106)
																	.addComponent(lblAssignedUsers, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(188)
																	.addComponent(lblUsersList, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
																	.addGap(80)
																	.addComponent(scrlPaneAvailableUsers, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE)
																	.addGap(6)
																	.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
																		.addComponent(buttonAssignUsers)
																		.addComponent(buttonRemoveUsers))
																	.addGap(6)
																	.addComponent(scrlPaneAssignedUsers, GroupLayout.PREFERRED_SIZE, 148, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlCreateJob.createSequentialGroup()
																	.addGap(498)
																	.addComponent(btnCancelJob, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
																	.addGap(10)
																	.addComponent(btnCreateJob, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
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
																	.addGroup(gl_pnlCreateJob.createParallelGroup(Alignment.LEADING)
																		.addComponent(btnCancelJob)
																		.addComponent(btnCreateJob)))
														);
														pnlCreateJob.setLayout(gl_pnlCreateJob);
														GroupLayout gl_pnlManagerWorker = new GroupLayout(pnlManagerWorker);
														gl_pnlManagerWorker.setHorizontalGroup(
															gl_pnlManagerWorker.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlManagerWorker.createSequentialGroup()
																	.addGap(316)
																	.addGroup(gl_pnlManagerWorker.createParallelGroup(Alignment.LEADING)
																		.addGroup(gl_pnlManagerWorker.createSequentialGroup()
																			.addGap(134)
																			.addComponent(btn_create_task, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
																		.addComponent(btn_create_project, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
																	.addGap(3)
																	.addComponent(btn_create_job, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))
																.addGroup(gl_pnlManagerWorker.createSequentialGroup()
																	.addGap(181)
																	.addComponent(layeredPaneManagerWorkerComponents, GroupLayout.PREFERRED_SIZE, 746, GroupLayout.PREFERRED_SIZE))
														);
														gl_pnlManagerWorker.setVerticalGroup(
															gl_pnlManagerWorker.createParallelGroup(Alignment.LEADING)
																.addGroup(gl_pnlManagerWorker.createSequentialGroup()
																	.addGroup(gl_pnlManagerWorker.createParallelGroup(Alignment.LEADING)
																		.addComponent(btn_create_task, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
																		.addComponent(btn_create_project, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
																		.addComponent(btn_create_job, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
																	.addGap(13)
																	.addComponent(layeredPaneManagerWorkerComponents, GroupLayout.PREFERRED_SIZE, 719, GroupLayout.PREFERRED_SIZE))
														);
														pnlManagerWorker.setLayout(gl_pnlManagerWorker);
														
														btnLogout = new JButton("LOGOUT");

														btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
														btnLogout.setBounds(859, 11, 89, 23);
														contentPane.add(btnLogout);
														btnLogout.setVisible(false);
														
														btn_settings = new JButton("Settings");
														btn_settings.setFont(new Font("Tahoma", Font.BOLD, 11));
														btn_settings.setBounds(754, 8, 89, 28);
														contentPane.add(btn_settings);
														btn_settings.setVisible(false);
														
														layeredPane_1 = new JLayeredPane();
														layeredPane_1.setBounds(10, 8, 734, 25);
														contentPane.add(layeredPane_1);
														
														lblPortal = new JLabel("Admin Portal");
														lblPortal.setFont(new Font("Tahoma", Font.BOLD, 16));
														lblPortal.setBounds(0, 0, 734, 25);
														layeredPane_1.add(lblPortal);
														lblPortal.setVisible(false);
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
		
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pass = new String(passwordLogin.getPassword());
				User u = jdbc.login(txtLoginUser.getText(), pass);
				if (u == null) {
					JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");
				} else {
					if (u.get_usertype() == 1) {
						//They are an Admin
						layeredPane.setLayer(layeredPaneAdmin, 10);
						layeredPane.setLayer(layeredPaneLogin, 0);
						layeredPaneAdmin.setLayer(pnlAdmin, 10);
						System.out.println("logged in as Admin");
						lblPortal.setText("Admin Portal - "+u.get_firstname()+ " " + u.get_lastname());						
					} else if (u.get_usertype() == 2) {
						//They are a Manager
						layeredPane.setLayer(layeredPaneManagerWorker, 10);
						layeredPane.setLayer(layeredPaneLogin, 0);
						layeredPaneManagerWorker.setLayer(pnlManagerWorker, 10);
						System.out.println("logged in as Manager");
						lblPortal.setText("Manager Portal - "+u.get_firstname()+ " " + u.get_lastname());
					} else {
						//They are a Worker
						layeredPane.setLayer(layeredPaneManagerWorker, 10);
						System.out.println("logged in as Worker");
						lblPortal.setText("Worker Portal - "+u.get_firstname()+ " " + u.get_lastname());
						
					}
					btnLogout.setVisible(true);
					lblPortal.setVisible(true);
					btn_settings.setVisible(true);
				}
				
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.setLayer(layeredPaneLogin, 10);
				layeredPane.setLayer(layeredPaneAdmin, 0);
				layeredPane.setLayer(layeredPaneManagerWorker, 0);
				btnLogout.setVisible(false);
				lblPortal.setVisible(false);
				btn_settings.setVisible(false);
				txtLoginUser.setText("");
				passwordLogin.setText("");
			}
		});
		
		btnViewTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (pnlViewTickets.isVisible()) {
					pnlViewTickets.setVisible(false);
				} else {
					pnlViewTickets.setVisible(true);
				}
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
				pnlCreateQualification.setVisible(false);
				pnlCreateProject.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);	
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
				pnlCreateQualification.setVisible(false);
				pnlCreateUser.setVisible(false);
				pnlCreateTask.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);
				
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
				pnlCreateQualification.setVisible(false);
				pnlCreateJob.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);	
				createAllUsersList();
			}
		});
		//closes create job tab
		btnCancelJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
			}
		});
//		private JPanel pnlCreateJob;
//		private JTextField txtJobName;
//		private JButton btnCreateJob;
//		private JButton btnCancelJob;	
//		private JButton buttonAssignUsers; 								
//		private JButton buttonRemoveUsers;
//		private JButton btn_create_job;	
//		private JTextArea txtAreaJobDescription;
//		private JList listAssignableManagers;
//		private JList listRequiredQuals;
//		private JList listAssignedUsers;								
//		private JList listAvailableUsers;
		
		//creates a new job w/ info, assigns a manager to it,
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Id= jdbc.getMaxJobID()+1;
				Job job =new Job(Id, txtJobName.getText(), 2);
				job.setJobdesc(txtAreaJobDescription.getText());				
				jdbc.add_project(job);
				createManagersList();
				layeredPaneManagerWorker.setVisible(true);	
				JOptionPane.showMessageDialog(null, "job Created!");
				pnlCreateJob.setVisible(false);
			}
		});
		//moves the create user panel to the front to allow the user to enter the new user info
		btn_create_new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateQualification.setVisible(false);
				pnlCreateUser.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);
				layeredPaneAdminComponents.setLayer(pnlCreateUser, 3);
				
			}
		});
		
		btn_add_qualifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlUserEditInfo.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateUser.setVisible(false);
				pnlCreateQualification.setVisible(true);
				
				
			}
		});
		
		btnCancelAddQualifcation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateQualification.setVisible(false);
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
					String pass = new String(txtCreatePassword.getPassword());
					jdbc.add_user(usertype,txtCreateUsername.getText(),txtCreateFirstName.getText(), txtCreateLastName.getText(), txtCreateEmailAddress.getText(), txtCreatePhoneNumber.getText(), pass);
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
		//creates a new project 
		btnCreateNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Id= jdbc.getMaxJobID()+1;
				Job job =new Job(Id, txtProjectName.getText(), 1);
				job.setJobdesc(textAreaProjectDescription.getText());				
				jdbc.add_project(job);
				layeredPaneManagerWorker.setVisible(true);	
				JOptionPane.showMessageDialog(null, "Project Created!");
				pnlCreateProject.setVisible(false);
				
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateUser.setVisible(false);
			}
		});
		//TODO make it so they turn into an 'archived' user instead of complete deletion.
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.deleteUser(lastClickeduserID);
			}
		});
		
		btnCreateQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> usernames = new ArrayList<String>();
				for (int i = 0; i < userListAssignQual.size(); i++) {
					usernames.add(userListAssignQual.getElementAt(i).toString());
				}
				boolean worked = jdbc.createQual(txtNewQualificationName.getText(), txtNewQualificationDesc.getText(), usernames);
				if (worked) {
					JOptionPane.showMessageDialog(null, "Qualification Created!");
				} else {
					JOptionPane.showMessageDialog(null, "Qualification not created.");
					txtNewQualificationDesc.setText("");
					txtNewQualificationName.setText("");
					userListAssignQual.clear();
					createUserListQual();
				}
			}
		});
		
		btnAssignUserQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected = listCreateQualAvailUsers.getSelectedIndices();
				for (int i = 0; i < selected.length; i++) {
					userListAssignQual.addElement(userListAvailQual.getElementAt(selected[i]));
					userListAvailQual.remove(selected[i]);
				}
				
			}
		});
		
		btnUnassignUserQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected = listCreateQualAssignedUsers.getSelectedIndices();
				for (int i = 0; i < selected.length; i++) {
					userListAvailQual.addElement(userListAssignQual.getElementAt(selected[i]));
					userListAssignQual.remove(selected[i]);
				}
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
	
	private void createUserListQual() {
		userListAvailQual.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			userListAvailQual.addElement(String.format("%s, %s [%s]", users.get(i).get_lastname(), users.get(i).get_firstname(), users.get(i).get_username()));
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
	private void createAllUsersList(){
		userList.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			userList.addElement(String.format("%s, %s", users.get(i).get_lastname(), users.get(i).get_firstname()));
		}
	}
	
	//pulls all managers and fills the list
	private void createManagersList(){
		managerList.clear();
		ArrayList<User> users = jdbc.get_Managers();
		for (int i = 0; i < users.size(); i++) {
			managerList.addElement(String.format("%s, %s", users.get(i).get_lastname(), users.get(i).get_firstname()));
		}
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
