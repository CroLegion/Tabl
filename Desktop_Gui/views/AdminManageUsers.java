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
import common.Ticket;

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
	private int currentSessionUserID;
	
	private JLabel lblUsername_2;
	private JLabel lblPassword_1;
	private JLabel lblTitle;
	private JLabel lblTablLogin;	
	private JLabel lblPortal;
	private JLabel lblOpenTickets;
	private JLabel lblClosedTickets;
	
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
	private User manager;
	java.util.List qualsArray;

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
	private JPanel pnlClosedTicketsLbl;
	private JScrollPane scrlOpenTickets;
	private JScrollPane scrlClosedTickets;									
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
	DefaultListModel openTickets = new DefaultListModel();
	DefaultListModel closedTickets = new DefaultListModel();
	DefaultListModel archivedUserList = new DefaultListModel();
	DefaultListModel projectsList = new DefaultListModel();
	DefaultListModel conversationsList = new DefaultListModel();
	int lastClickedIndex;
	int lastClickeduserID;
	int lastClickedUserType;
	int currentOpenTicketId;
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
	private JButton btnCreateTicket;
	private JPanel pnlCreateTicket;
	private JTextField txtNewTicketTitle;
	private JButton btnSendTicket;
	private JTextArea txtNewTicketDesc;
	private JList listOpenTickets;
	private JList listClosedTickets;
	private JPanel pnlTicketDetails;
	private JLabel lblDone;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JRadioButton rdbtnAdminDetails;
	private JRadioButton rdbtnManagerDetails;
	private JRadioButton rdbtnWorkerDetails;
	private JRadioButton rdbtnTicketDoneYes;
	private JRadioButton rdbtnTicketDoneNo;
	private JLabel lblTicketDetailsTitle;
	private JLabel lblTicketDetailsMessage;
	private JLabel lblTicketDetailsSubmittedBy;
	private JLabel lblTicketDetailsDate;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JButton btnTicketDetailsClose;
	private JButton btnTicketDoneSave;
	private JList listArchivedUsers;
	private JPanel pnlMessages;
	private JPanel pnlProjects;
	private JLabel lblNewLabel_7;
	private JLabel lblNewLabel_8;
	private JScrollPane scrlConversations;
	private JScrollPane scrlProjects;
	private JList listProjects;
	private JList listConversations;
	
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
		
		initMainComponents();
		initLoginComponents();
		initAdminComponents();
		initManagerWorkerComponents();
		
		
		createMainEvents();
		createLoginEvents();
		createAdminEvents();
		createManagerWorkerEvents();
	}


	private void initMainComponents() {
		setBackground(Color.RED);
		setTitle("TABL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 974, 842);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setVisible(true);
		contentPane.setLayout(null);
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 40, 941, 757);
		contentPane.add(layeredPane);
		
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminManageUsers.class.getResource("/resources/Logo.PNG")));

	}
	
	
	private void initLoginComponents() {
		layeredPaneLogin = new JLayeredPane();
		layeredPane.setLayer(layeredPaneLogin, 10);
		layeredPaneLogin.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneLogin);
		
		pnlLogin = new JPanel();
		layeredPaneLogin.setLayer(pnlLogin, 10);
		pnlLogin.setBounds(0, 0, 941, 760);
		layeredPaneLogin.add(pnlLogin);
		pnlLogin.setLayout(null);
		
		layeredPaneLoginComponents = new JLayeredPane();
		layeredPaneLoginComponents.setBounds(0, 0, 941, 760);
		
		pnl_login_components = new JPanel();
		pnl_login_components.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnl_login_components.setBounds(134, 109, 639, 354);
		layeredPaneLoginComponents.add(pnl_login_components);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(308, 320, 89, 23);
		
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		passwordLogin = new JPasswordField();
		passwordLogin.setBounds(306, 237, 192, 19);
		
		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setBounds(136, 237, 105, 14);
		lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblUsername_2 = new JLabel("Username:");
		lblUsername_2.setBounds(136, 153, 105, 14);
		lblUsername_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		lblTablLogin = new JLabel("TABL LOGIN");
		lblTablLogin.setBounds(196, 32, 274, 60);
		lblTablLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		txtLoginUser = new JTextField();
		txtLoginUser.setBounds(306, 152, 192, 20);
		txtLoginUser.setColumns(10);
		pnl_login_components.setLayout(null);
		pnl_login_components.add(lblTablLogin);
		pnl_login_components.add(lblUsername_2);
		pnl_login_components.add(txtLoginUser);
		pnl_login_components.add(lblPassword_1);
		pnl_login_components.add(passwordLogin);
		pnl_login_components.add(btnLogin);
		pnlLogin.add(layeredPaneLoginComponents);
	}
	
	
	private void initAdminComponents() {
		
		createUserList();
		createUserListQual();
		
		layeredPaneAdmin = new JLayeredPane();
		layeredPaneAdmin.setBackground(new Color(100, 149, 237));
		layeredPane.setLayer(layeredPaneAdmin, 0);
		layeredPaneAdmin.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneAdmin);
		
		pnlAdmin = new JPanel();
		layeredPaneAdmin.setLayer(pnlAdmin, 0);
		pnlAdmin.setBounds(0, 0, 947, 760);
		layeredPaneAdmin.add(pnlAdmin);
		
		layeredPaneAdminComponents = new JLayeredPane();
		layeredPaneAdminComponents.setBounds(0, 0, 937, 760);
		
		Panel pnlUsers = new Panel();
		layeredPaneAdminComponents.setLayer(pnlUsers, 0);
		pnlUsers.setEnabled(false);
		pnlUsers.setBounds(0, 0, 157, 28);
		layeredPaneAdminComponents.add(pnlUsers);
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		pnlUsers.setLayout(null);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		lblUsers.setBounds(58, 5, 40, 16);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		pnlUsers.add(lblUsers);
		
		pnlCreateQualification = new JPanel();
		pnlCreateQualification.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlCreateQualification);
		pnlCreateQualification.setVisible(false);
		
		JLabel lblCreateQualification = new JLabel("Create New Qualification");
		lblCreateQualification.setBounds(246, 11, 262, 51);
		lblCreateQualification.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(111, 131, 56, 20);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(61, 193, 120, 14);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAssignToUsers = new JLabel("Assign to Users");
		lblAssignToUsers.setBounds(247, 298, 147, 14);
		lblAssignToUsers.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAvailable_1 = new JLabel("Available");
		lblAvailable_1.setBounds(121, 314, 60, 14);
		lblAvailable_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblAssigned_1 = new JLabel("Assigned");
		lblAssigned_1.setBounds(462, 314, 67, 14);
		lblAssigned_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtNewQualificationName = new JTextField();
		txtNewQualificationName.setBounds(240, 133, 154, 20);
		txtNewQualificationName.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(240, 194, 387, 79);
		
		txtNewQualificationDesc = new JTextArea();
		scrollPane_1.setViewportView(txtNewQualificationDesc);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(80, 339, 154, 287);
		listCreateQualAvailUsers = new JList(userListAvailQual);
		scrollPane_2.setViewportView(listCreateQualAvailUsers);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(406, 339, 154, 287);
		
		listCreateQualAssignedUsers = new JList(userListAssignQual);
		scrollPane_3.setViewportView(listCreateQualAssignedUsers);
		
		btnAssignUserQual = new JButton("->");
		btnAssignUserQual.setBounds(271, 445, 89, 23);
		
		btnAssignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnUnassignUserQual = new JButton("<-");
		btnUnassignUserQual.setBounds(271, 519, 89, 23);
				
		btnUnassignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnCreateQual = new JButton("CREATE");
		btnCreateQual.setBounds(271, 668, 123, 41);
						
		btnCreateQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnCancelAddQualifcation = new JButton("Cancel");
		btnCancelAddQualifcation.setBounds(400, 668, 100, 30);

		btnCancelAddQualifcation.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlCreateQualification.setLayout(null);
		pnlCreateQualification.add(lblCreateQualification);
		pnlCreateQualification.add(lblTitle);
		pnlCreateQualification.add(txtNewQualificationName);
		pnlCreateQualification.add(lblDescription);
		pnlCreateQualification.add(scrollPane_1);
		pnlCreateQualification.add(lblAssignToUsers);
		pnlCreateQualification.add(lblAvailable_1);
		pnlCreateQualification.add(lblAssigned_1);
		pnlCreateQualification.add(scrollPane_2);
		pnlCreateQualification.add(btnAssignUserQual);
		pnlCreateQualification.add(btnUnassignUserQual);
		pnlCreateQualification.add(scrollPane_3);
		pnlCreateQualification.add(btnCreateQual);
		pnlCreateQualification.add(btnCancelAddQualifcation);
		
		pnlCreateUser = new JPanel();
		layeredPaneAdminComponents.setLayer(pnlCreateUser, 0);
		pnlCreateUser.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlCreateUser);
		pnlCreateUser.setVisible(false);
		pnlCreateUser.setBackground(UIManager.getColor("Button.background"));
		pnlCreateUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(220, 20, 60), null, null, null));
		
		lblEnterUserInfo = new JLabel("Enter User Info");
		lblEnterUserInfo.setBounds(314, 21, 155, 25);
		lblEnterUserInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblFirstName_1 = new JLabel("First Name:");
		lblFirstName_1.setBounds(116, 64, 79, 17);
		lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(144, 496, 200, 29);
		
		btnCreateUser.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		rdbtnAdmin = new JRadioButton("Admin");
		rdbtnAdmin.setBounds(228, 437, 61, 23);
		buttonGroup.add(rdbtnAdmin);
		rdbtnAdmin.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rdbtnManager = new JRadioButton("Manager");
		rdbtnManager.setBounds(314, 437, 75, 23);
		buttonGroup.add(rdbtnManager);
		rdbtnManager.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		rdbtnWorker = new JRadioButton("Worker");
		rdbtnWorker.setBounds(420, 437, 67, 23);
		buttonGroup.add(rdbtnWorker);
		rdbtnWorker.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtCreateFirstName = new JTextField();
		txtCreateFirstName.setBounds(257, 64, 291, 20);
		txtCreateFirstName.setColumns(10);
		
		txtCreateLastName = new JTextField();
		txtCreateLastName.setBounds(257, 126, 291, 20);
		txtCreateLastName.setColumns(10);
		
		txtCreateUsername = new JTextField();
		txtCreateUsername.setBounds(257, 179, 291, 20);
		txtCreateUsername.setColumns(10);
		
		txtCreateEmailAddress = new JTextField();
		txtCreateEmailAddress.setBounds(257, 235, 291, 20);
		txtCreateEmailAddress.setColumns(10);
		
		txtCreatePhoneNumber = new JTextField();
		txtCreatePhoneNumber.setBounds(257, 292, 291, 20);
		txtCreatePhoneNumber.setColumns(10);
		
		txtCreatePassword = new JPasswordField();
		txtCreatePassword.setBounds(257, 348, 292, 22);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(401, 493, 133, 35);
								
		btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblLastName_1 = new JLabel("Last Name:");
		lblLastName_1.setBounds(117, 129, 78, 17);
		lblLastName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblUsername_1 = new JLabel("Username:");
		lblUsername_1.setBounds(121, 182, 74, 17);
		lblUsername_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblEmailAddress_1 = new JLabel("Email Address:");
		lblEmailAddress_1.setBounds(94, 238, 101, 17);
		lblEmailAddress_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number");
		lblPhoneNumber_1.setBounds(91, 295, 104, 17);
		lblPhoneNumber_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(123, 349, 72, 17);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(121, 438, 74, 17);
		lblUserType.setFont(new Font("Tahoma", Font.BOLD, 14));
		pnlCreateUser.setLayout(null);
		pnlCreateUser.add(lblEnterUserInfo);
		pnlCreateUser.add(lblFirstName_1);
		pnlCreateUser.add(txtCreateFirstName);
		pnlCreateUser.add(lblLastName_1);
		pnlCreateUser.add(txtCreateLastName);
		pnlCreateUser.add(lblUsername_1);
		pnlCreateUser.add(txtCreateUsername);
		pnlCreateUser.add(lblEmailAddress_1);
		pnlCreateUser.add(txtCreateEmailAddress);
		pnlCreateUser.add(lblPhoneNumber_1);
		pnlCreateUser.add(txtCreatePhoneNumber);
		pnlCreateUser.add(lblPassword);
		pnlCreateUser.add(txtCreatePassword);
		pnlCreateUser.add(lblUserType);
		pnlCreateUser.add(rdbtnAdmin);
		pnlCreateUser.add(rdbtnManager);
		pnlCreateUser.add(rdbtnWorker);
		pnlCreateUser.add(btnCreateUser);
		pnlCreateUser.add(btnCancel);
		//create task end	
		//edit user info start				
		pnlUserEditInfo = new JPanel();
		layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 0);
		pnlUserEditInfo.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlUserEditInfo);
		pnlUserEditInfo.setBorder(new TitledBorder(null, "User Edit/Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(264, 16, 127, 45);
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(85, 89, 79, 17);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFirstName = new JTextField();
		textFirstName.setBounds(214, 91, 330, 20);
		textFirstName.setColumns(10);
		textLastName = new JTextField();
		textLastName.setBounds(214, 134, 330, 20);
		textLastName.setColumns(10);
		textUsername = new JTextField();
		textUsername.setBounds(214, 179, 330, 20);
		textUsername.setColumns(10);
		textEmail = new JTextField();
		textEmail.setBounds(214, 229, 330, 20);
		textEmail.setColumns(10);
		textPhone = new JTextField();
		textPhone.setBounds(214, 283, 330, 20);
		textPhone.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(22, 376, 699, 2);
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		JScrollPane scrlPaneAssignedQuals = new JScrollPane();
		scrlPaneAssignedQuals.setBounds(275, 438, 174, 271);
		
		unassignQual = new JButton("<-");
		unassignQual.setBounds(206, 581, 64, 29);
		
		unassignQual.setToolTipText("Click to remove assigned Qualifications");
		unassignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		assignQual = new JButton("->");
		assignQual.setBounds(206, 523, 64, 29);
		
		assignQual.setToolTipText("Click to move selected Qualifications to Assigned");
		assignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		listAssignedQuals = new JList(assignedQualList);
		scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
														
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(86, 134, 78, 17);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(90, 182, 74, 17);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setBounds(63, 232, 101, 17);
		lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(55, 286, 109, 17);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(67, 410, 60, 17);
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel lblAssigned = new JLabel("Assigned");
		lblAssigned.setBounds(318, 410, 86, 17);
		lblAssigned.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(328, 331, 142, 34);
		
		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(164, 331, 127, 34);
		
		btnSaveChanges.setToolTipText("Save Changes to Database");
		
		JScrollPane scrlPaneAvailableQuals = new JScrollPane();
		scrlPaneAvailableQuals.setBounds(22, 438, 174, 271);
		
		listAvailableQuals = new JList(availableQualList);
		scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
		
		pnlDeleteUser = new JPanel();
		pnlDeleteUser.setBounds(531, 620, 166, 89);
		pnlDeleteUser.setBorder(new TitledBorder(null, "WARNING AREA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDeleteUser.setBackground(new Color(245, 222, 179));
		pnlDeleteUser.setLayout(null);
		
		btnDeleteUser = new JButton("ARCHIVE USER");
														
		btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDeleteUser.setBounds(17, 27, 140, 39);
		pnlDeleteUser.add(btnDeleteUser);
		
		JLabel lblUserType_1 = new JLabel("User Type");
		lblUserType_1.setBounds(562, 89, 82, 20);
		lblUserType_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		rdbtnAdminDetails = new JRadioButton("Admin");
		rdbtnAdminDetails.setBounds(562, 129, 109, 23);
		buttonGroup_1.add(rdbtnAdminDetails);
		
		rdbtnManagerDetails = new JRadioButton("Manager");
		rdbtnManagerDetails.setBounds(562, 170, 120, 23);
		buttonGroup_1.add(rdbtnManagerDetails);
		
		rdbtnWorkerDetails = new JRadioButton("Worker");
		rdbtnWorkerDetails.setBounds(562, 211, 120, 23);
		buttonGroup_1.add(rdbtnWorkerDetails);
		pnlUserEditInfo.setLayout(null);
		pnlUserEditInfo.add(lblFullName);
		pnlUserEditInfo.add(lblPhoneNumber);
		pnlUserEditInfo.add(textPhone);
		pnlUserEditInfo.add(btnSaveChanges);
		pnlUserEditInfo.add(btnChangePassword);
		pnlUserEditInfo.add(separator);
		pnlUserEditInfo.add(lblAvailable);
		pnlUserEditInfo.add(lblAssigned);
		pnlUserEditInfo.add(scrlPaneAvailableQuals);
		pnlUserEditInfo.add(assignQual);
		pnlUserEditInfo.add(unassignQual);
		pnlUserEditInfo.add(scrlPaneAssignedQuals);
		pnlUserEditInfo.add(pnlDeleteUser);
		pnlUserEditInfo.add(lblFirstName);
		pnlUserEditInfo.add(textFirstName);
		pnlUserEditInfo.add(lblLastName);
		pnlUserEditInfo.add(textLastName);
		pnlUserEditInfo.add(lblUsername);
		pnlUserEditInfo.add(textUsername);
		pnlUserEditInfo.add(lblEmailAddress);
		pnlUserEditInfo.add(textEmail);
		pnlUserEditInfo.add(rdbtnWorkerDetails);
		pnlUserEditInfo.add(rdbtnManagerDetails);
		pnlUserEditInfo.add(rdbtnAdminDetails);
		pnlUserEditInfo.add(lblUserType_1);
														
		pnlViewTickets = new JPanel();
		layeredPaneAdminComponents.setLayer(pnlViewTickets, 0);
		pnlViewTickets.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlViewTickets);
		pnlViewTickets.setVisible(false);
		
		pnlOpenTicketsLbl = new JPanel();
		pnlOpenTicketsLbl.setBounds(0, 0, 746, 26);
		pnlOpenTicketsLbl.setBackground(UIManager.getColor("Button.shadow"));
		pnlOpenTicketsLbl.setLayout(null);
		
		lblOpenTickets = new JLabel("Open Tickets");
		lblOpenTickets.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOpenTickets.setBounds(312, 0, 189, 15);
		pnlOpenTicketsLbl.add(lblOpenTickets);
		
		pnlClosedTicketsLbl = new JPanel();
		pnlClosedTicketsLbl.setBounds(0, 329, 746, 26);
		pnlClosedTicketsLbl.setBackground(SystemColor.controlShadow);
		pnlClosedTicketsLbl.setLayout(null);
		
		lblClosedTickets = new JLabel("Closed Tickets");
		lblClosedTickets.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClosedTickets.setBounds(302, 0, 189, 26);
		pnlClosedTicketsLbl.add(lblClosedTickets);
		
		scrlOpenTickets = new JScrollPane();
		scrlOpenTickets.setBounds(0, 22, 746, 307);
		
		scrlClosedTickets = new JScrollPane();
		scrlClosedTickets.setBounds(0, 354, 746, 366);
		
		listClosedTickets = new JList(closedTickets);
		listClosedTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlClosedTickets.setViewportView(listClosedTickets);
		pnlViewTickets.setLayout(null);
		
		listOpenTickets = new JList(openTickets);
		listOpenTickets.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlOpenTickets.setViewportView(listOpenTickets);
		pnlViewTickets.add(scrlOpenTickets);
		pnlViewTickets.add(pnlOpenTicketsLbl);
		pnlViewTickets.add(pnlClosedTicketsLbl);
		pnlViewTickets.add(scrlClosedTickets);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 23, 157, 314);
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
		
		pnlTicketDetails = new JPanel();
		pnlTicketDetails.setBorder(new TitledBorder(null, "Ticket Details", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPaneAdminComponents.setLayer(pnlTicketDetails, 10);
		pnlTicketDetails.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlTicketDetails);
		pnlTicketDetails.setLayout(null);
		pnlTicketDetails.setVisible(false);
		pnlAdmin.setLayout(null);
		
		JLabel lblTicketDetails = new JLabel("Ticket Details");
		lblTicketDetails.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTicketDetails.setBounds(265, 11, 155, 65);
		pnlTicketDetails.add(lblTicketDetails);
		
		JLabel lblTitle_2 = new JLabel("Title:");
		lblTitle_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle_2.setBounds(69, 78, 121, 14);
		pnlTicketDetails.add(lblTitle_2);
		
		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblMessage.setBounds(69, 103, 121, 32);
		pnlTicketDetails.add(lblMessage);
		
		JLabel lblSubmittedBy = new JLabel("Submitted By:");
		lblSubmittedBy.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSubmittedBy.setBounds(69, 213, 155, 26);
		pnlTicketDetails.add(lblSubmittedBy);
		
		JLabel lblDateSubmitted = new JLabel("Date Submitted:");
		lblDateSubmitted.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDateSubmitted.setBounds(69, 274, 142, 14);
		pnlTicketDetails.add(lblDateSubmitted);
		
		lblDone = new JLabel("Done?");
		lblDone.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDone.setBounds(297, 421, 87, 46);
		pnlTicketDetails.add(lblDone);
		
		rdbtnTicketDoneYes = new JRadioButton("Yes");
		buttonGroup_2.add(rdbtnTicketDoneYes);
		rdbtnTicketDoneYes.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTicketDoneYes.setBounds(224, 474, 109, 23);
		pnlTicketDetails.add(rdbtnTicketDoneYes);
		
		rdbtnTicketDoneNo = new JRadioButton("No");
		buttonGroup_2.add(rdbtnTicketDoneNo);
		rdbtnTicketDoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTicketDoneNo.setBounds(369, 474, 109, 23);
		pnlTicketDetails.add(rdbtnTicketDoneNo);
		
		lblTicketDetailsTitle = new JLabel("");
		lblTicketDetailsTitle.setBounds(200, 80, 487, 23);
		pnlTicketDetails.add(lblTicketDetailsTitle);
		
		btnTicketDetailsClose = new JButton("Close");
		
		btnTicketDetailsClose.setBounds(647, 11, 89, 23);
		pnlTicketDetails.add(btnTicketDetailsClose);
		
		lblTicketDetailsMessage = new JLabel(" ");
		lblTicketDetailsMessage.setBounds(200, 114, 503, 88);
		pnlTicketDetails.add(lblTicketDetailsMessage);
		
		lblTicketDetailsSubmittedBy = new JLabel("");
		lblTicketDetailsSubmittedBy.setBounds(200, 221, 503, 18);
		pnlTicketDetails.add(lblTicketDetailsSubmittedBy);
		
		lblTicketDetailsDate = new JLabel("");
		lblTicketDetailsDate.setBounds(221, 276, 354, 26);
		pnlTicketDetails.add(lblTicketDetailsDate);
		
		btnTicketDoneSave = new JButton("Save");
		
		btnTicketDoneSave.setBounds(280, 520, 89, 23);
		pnlTicketDetails.add(btnTicketDoneSave);
		
		JPanel pnlArchivedUsers = new JPanel();
		pnlArchivedUsers.setBackground(Color.LIGHT_GRAY);
		pnlArchivedUsers.setBounds(0, 337, 157, 28);
		layeredPaneAdminComponents.add(pnlArchivedUsers);
		pnlArchivedUsers.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("ARCHIVED USERS");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(24, 5, 107, 20);
		pnlArchivedUsers.add(lblNewLabel_6);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 365, 157, 393);
		layeredPaneAdminComponents.add(scrollPane_5);
		
		listArchivedUsers = new JList(archivedUserList);
		listArchivedUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_5.setViewportView(listArchivedUsers);
		pnlAdmin.add(layeredPaneAdminComponents);
	}
	
	
	private void initManagerWorkerComponents() {
		layeredPaneManagerWorker = new JLayeredPane();
		layeredPane.setLayer(layeredPaneManagerWorker, 0);
		layeredPaneManagerWorker.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneManagerWorker);
		
		pnlManagerWorker = new JPanel();
		layeredPaneManagerWorker.setLayer(pnlManagerWorker, 10);
		pnlManagerWorker.setBounds(0, 0, 941, 760);
		layeredPaneManagerWorker.add(pnlManagerWorker);
		
		btn_create_project = new JButton("Create Project");
		btn_create_project.setBounds(316, 0, 136, 28);
		
		btn_create_task = new JButton("Create Task");
		btn_create_task.setBounds(450, 0, 115, 28);
		
		btn_create_job = new JButton("Create Job");
		btn_create_job.setBounds(568, 0, 97, 28);
		
		layeredPaneManagerWorkerComponents = new JLayeredPane();
		layeredPaneManagerWorkerComponents.setBounds(181, 41, 746, 719);
		//create project end
		//Create task start			
		pnlCreateTask = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateTask, 0);
		pnlCreateTask.setBounds(0, 0, 746, 720);
		layeredPaneManagerWorkerComponents.add(pnlCreateTask);
		pnlCreateTask.setVisible(false);
		
		JLabel lblTaskName = new JLabel("Name of the Task:");
		lblTaskName.setBounds(163, 124, 114, 14);
		
		JLabel lblTaskDescription = new JLabel("Description of the Task:");
		lblTaskDescription.setBounds(137, 277, 140, 14);
		
		JLabel lblTaskReason = new JLabel("Reason why it should be added:");
		lblTaskReason.setBounds(97, 422, 180, 14);
		
		JScrollPane scrlPaneDescription = new JScrollPane();
		scrlPaneDescription.setBounds(366, 271, 216, 78);
		
		JScrollPane scrlPaneReason = new JScrollPane();
		scrlPaneReason.setBounds(366, 422, 215, 105);
		
		btnCreateNewTask = new JButton("Create new Task");
		btnCreateNewTask.setBounds(508, 604, 140, 23);
		
		textTaskName = new JTextField();
		textTaskName.setBounds(366, 121, 86, 20);
		textTaskName.setColumns(10);
		
		btnCancelTask = new JButton("Cancel");
		btnCancelTask.setBounds(398, 604, 86, 23);
		
		txtAreaReason = new JTextArea();
		scrlPaneReason.setViewportView(txtAreaReason);
		
		txtAreaDescription = new JTextArea();
		scrlPaneDescription.setViewportView(txtAreaDescription);
		pnlCreateTask.setLayout(null);
		
		JLabel lblCreateTask = new JLabel("Create a new Task");
		lblCreateTask.setBounds(301, 47, 146, 22);
		lblCreateTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
		pnlCreateTask.add(lblCreateTask);
		pnlCreateTask.add(lblTaskName);
		pnlCreateTask.add(textTaskName);
		pnlCreateTask.add(lblTaskDescription);
		pnlCreateTask.add(scrlPaneDescription);
		pnlCreateTask.add(lblTaskReason);
		pnlCreateTask.add(scrlPaneReason);
		pnlCreateTask.add(btnCancelTask);
		pnlCreateTask.add(btnCreateNewTask);
		//create user end				
		//create project start		
		pnlCreateProject = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateProject, 0);
		pnlCreateProject.setBounds(0, 0, 746, 720);
		layeredPaneManagerWorkerComponents.add(pnlCreateProject);
		pnlCreateProject.setVisible(false);
		
		lblNewLabel = new JLabel("Project name:");
		lblNewLabel.setBounds(149, 125, 86, 14);
		
		lblNewLabel_1 = new JLabel("Description:");
		lblNewLabel_1.setBounds(157, 192, 78, 14);
		
		JScrollPane scrlPaneProjectDescription = new JScrollPane();
		scrlPaneProjectDescription.setBounds(397, 186, 205, 81);
		
		JScrollPane scrlPaneQualifications = new JScrollPane();
		scrlPaneQualifications.setBounds(111, 354, 154, 164);
		
		JScrollPane scrlPaneUsersAvailable = new JScrollPane();
		scrlPaneUsersAvailable.setBounds(283, 354, 154, 164);
		
		btnAssign = new JButton("->");
		btnAssign.setBounds(455, 389, 45, 23);
		btnRemove = new JButton("<-");
		btnRemove.setBounds(455, 444, 45, 23);
		
		btnCreateNewProject = new JButton("Create new Project");
		btnCreateNewProject.setBounds(508, 571, 154, 23);
		
		btnCancelProject = new JButton("Cancel");
		btnCancelProject.setBounds(380, 571, 82, 23);
		
		textAreaProjectDescription = new JTextArea();
		scrlPaneProjectDescription.setViewportView(textAreaProjectDescription);
		
		listUsersAvailable = new JList();
		scrlPaneUsersAvailable.setViewportView(listUsersAvailable);
		
		listQualifications = new JList(listedQualList);
		listQualifications.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneQualifications.setViewportView(listQualifications);
		
		JLabel lblNewLabel_2 = new JLabel("Qualifications:");
		lblNewLabel_2.setBounds(111, 322, 96, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Users to add:");
		lblNewLabel_3.setBounds(283, 322, 76, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Users added:");
		lblNewLabel_4.setBounds(532, 322, 86, 14);
		
		JLabel lblCreateANew = new JLabel("Create a new Project");
		lblCreateANew.setBounds(299, 29, 163, 22);
		lblCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtProjectName = new JTextField();
		txtProjectName.setBounds(397, 119, 86, 20);
		txtProjectName.setColumns(10);
		
		JScrollPane scrlPaneUsersAdded = new JScrollPane();
		scrlPaneUsersAdded.setBounds(532, 354, 154, 164);
		
		listUsersAdded = new JList();
		scrlPaneUsersAdded.setViewportView(listUsersAdded);
		pnlCreateProject.setLayout(null);
		pnlCreateProject.add(lblCreateANew);
		pnlCreateProject.add(lblNewLabel);
		pnlCreateProject.add(txtProjectName);
		pnlCreateProject.add(lblNewLabel_1);
		pnlCreateProject.add(scrlPaneProjectDescription);
		pnlCreateProject.add(lblNewLabel_2);
		pnlCreateProject.add(lblNewLabel_3);
		pnlCreateProject.add(lblNewLabel_4);
		pnlCreateProject.add(scrlPaneQualifications);
		pnlCreateProject.add(scrlPaneUsersAvailable);
		pnlCreateProject.add(btnAssign);
		pnlCreateProject.add(btnRemove);
		pnlCreateProject.add(scrlPaneUsersAdded);
		pnlCreateProject.add(btnCancelProject);
		pnlCreateProject.add(btnCreateNewProject);
		//edit user info end
		//create job start				
		pnlCreateJob = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateJob, 10);
		pnlCreateJob.setBounds(0, 0, 746, 720);
		layeredPaneManagerWorkerComponents.add(pnlCreateJob);
		pnlCreateJob.setVisible(false);
		
		JLabel lblCreateNewJob = new JLabel("Create new Job");
		lblCreateNewJob.setBounds(302, 22, 122, 22);
		lblCreateNewJob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		JScrollPane scrlPaneJobDescription = new JScrollPane();
		scrlPaneJobDescription.setBounds(395, 166, 224, 69);
		
		JScrollPane scrlPaneAssignableManagers = new JScrollPane();
		scrlPaneAssignableManagers.setBounds(395, 241, 148, 109);
		
		JScrollPane scrlPaneRequiredQuals = new JScrollPane();
		scrlPaneRequiredQuals.setBounds(395, 356, 148, 138);
		
		JScrollPane scrlPaneAvailableUsers = new JScrollPane();
		scrlPaneAvailableUsers.setBounds(344, 527, 148, 138);
		
		txtAreaJobDescription = new JTextArea();
		scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
		
		listAssignableManagers = new JList(managerList);
		listAssignableManagers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneAssignableManagers.setViewportView(listAssignableManagers);
		
		JLabel lblJobName = new JLabel("Name of the Job:");
		lblJobName.setBounds(150, 107, 114, 14);
		
		listRequiredQuals = new JList(listedQualList);
		scrlPaneRequiredQuals.setViewportView(listRequiredQuals);
		
		listAvailableUsers = new JList(userList);
		scrlPaneAvailableUsers.setViewportView(listAvailableUsers);
		
		buttonAssignUsers = new JButton("->");
		buttonAssignUsers.setBounds(498, 554, 45, 23);
		
		buttonRemoveUsers = new JButton("<-");
		buttonRemoveUsers.setBounds(498, 603, 45, 23);
		
		txtJobName = new JTextField();
		txtJobName.setBounds(395, 107, 86, 20);
		txtJobName.setColumns(10);
		
		btnCreateJob = new JButton("Create Job");
		btnCreateJob.setBounds(594, 683, 103, 23);
		
		btnCancelJob  = new JButton("Cancel");
		btnCancelJob.setBounds(498, 683, 86, 23);
		
		JLabel lblAvailableUsers_1 = new JLabel("Available Users");
		lblAvailableUsers_1.setBounds(344, 507, 100, 14);
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		lblAssignedUsers.setBounds(550, 507, 94, 14);
		
		JLabel lblUsersList = new JLabel("User List:");
		lblUsersList.setBounds(188, 529, 76, 14);
		
		JLabel lblNewLabel_9 = new JLabel("Description:");
		lblNewLabel_9.setBounds(176, 166, 88, 14);
		
		JLabel lblNewLabel_10 = new JLabel("Assignable Manager:");
		lblNewLabel_10.setBounds(141, 241, 123, 14);
		
		JLabel lblRequiredQualifications = new JLabel("Required Qualifications:");
		lblRequiredQualifications.setBounds(119, 356, 145, 14);
		
		JScrollPane scrlPaneAssignedUsers = new JScrollPane();
		scrlPaneAssignedUsers.setBounds(549, 527, 148, 130);
		
		listAssignedUsers = new JList();
		scrlPaneAssignedUsers.setViewportView(listAssignedUsers);
		pnlCreateJob.setLayout(null);
		pnlCreateJob.add(lblCreateNewJob);
		pnlCreateJob.add(lblJobName);
		pnlCreateJob.add(txtJobName);
		pnlCreateJob.add(lblNewLabel_9);
		pnlCreateJob.add(scrlPaneJobDescription);
		pnlCreateJob.add(lblNewLabel_10);
		pnlCreateJob.add(scrlPaneAssignableManagers);
		pnlCreateJob.add(lblRequiredQualifications);
		pnlCreateJob.add(scrlPaneRequiredQuals);
		pnlCreateJob.add(lblAvailableUsers_1);
		pnlCreateJob.add(lblAssignedUsers);
		pnlCreateJob.add(lblUsersList);
		pnlCreateJob.add(scrlPaneAvailableUsers);
		pnlCreateJob.add(buttonAssignUsers);
		pnlCreateJob.add(buttonRemoveUsers);
		pnlCreateJob.add(scrlPaneAssignedUsers);
		pnlCreateJob.add(btnCancelJob);
		pnlCreateJob.add(btnCreateJob);
		
		btnCreateTicket = new JButton("Create Ticket");
		btnCreateTicket.setBounds(762, 3, 136, 23);
		
		pnlCreateTicket = new JPanel();
		pnlCreateTicket.setBorder(new TitledBorder(null, "Ticket Creator", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateTicket, 0);
		pnlCreateTicket.setBounds(0, 0, 746, 720);
		layeredPaneManagerWorkerComponents.add(pnlCreateTicket);
		pnlCreateTicket.setLayout(null);
		pnlCreateTicket.setVisible(false);
		
		JLabel lblNewLabel_5 = new JLabel("Create New Ticket");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_5.setBounds(310, 11, 170, 41);
		pnlCreateTicket.add(lblNewLabel_5);
		
		JLabel lblTitle_1 = new JLabel("Title:");
		lblTitle_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle_1.setBounds(120, 90, 46, 14);
		pnlCreateTicket.add(lblTitle_1);
		
		JLabel lblDescription_1 = new JLabel("Description:");
		lblDescription_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription_1.setBounds(10, 161, 134, 14);
		pnlCreateTicket.add(lblDescription_1);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(186, 157, 459, 123);
		pnlCreateTicket.add(scrollPane_4);
		
		txtNewTicketDesc = new JTextArea();
		scrollPane_4.setViewportView(txtNewTicketDesc);
		
		txtNewTicketTitle = new JTextField();
		txtNewTicketTitle.setBounds(186, 87, 431, 20);
		pnlCreateTicket.add(txtNewTicketTitle);
		txtNewTicketTitle.setColumns(10);
		
		btnSendTicket = new JButton("SEND");

		btnSendTicket.setBounds(324, 347, 89, 23);
		pnlCreateTicket.add(btnSendTicket);
		pnlManagerWorker.setLayout(null);
		pnlManagerWorker.add(btn_create_task);
		pnlManagerWorker.add(btn_create_project);
		pnlManagerWorker.add(btn_create_job);
		pnlManagerWorker.add(btnCreateTicket);
		pnlManagerWorker.add(layeredPaneManagerWorkerComponents);
		
		pnlMessages = new JPanel();
		pnlMessages.setBackground(Color.LIGHT_GRAY);
		pnlMessages.setBounds(0, 347, 181, 28);
		pnlManagerWorker.add(pnlMessages);
		pnlMessages.setLayout(null);
		
		lblNewLabel_7 = new JLabel("Conversations");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_7.setBounds(28, 5, 143, 14);
		pnlMessages.add(lblNewLabel_7);
		
		pnlProjects = new JPanel();
		pnlProjects.setBackground(Color.LIGHT_GRAY);
		pnlProjects.setBounds(0, 0, 181, 28);
		pnlManagerWorker.add(pnlProjects);
		pnlProjects.setLayout(null);
		
		lblNewLabel_8 = new JLabel("Projects");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_8.setBounds(43, 5, 128, 14);
		pnlProjects.add(lblNewLabel_8);
		
		scrlConversations = new JScrollPane();
		scrlConversations.setBounds(2, 375, 179, 385);
		pnlManagerWorker.add(scrlConversations);
		
		listConversations = new JList(conversationsList);
		listConversations.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrlConversations.setViewportView(listConversations);
		
		scrlProjects = new JScrollPane();
		scrlProjects.setBounds(0, 27, 181, 319);
		pnlManagerWorker.add(scrlProjects);
		
		listProjects = new JList(projectsList);
		listProjects.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrlProjects.setViewportView(listProjects);
		
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
		
		loadProjects();
	}
	
	
	private void createMainEvents() {
		/*called when the 'X' button of the GUI in the top right is clicked
		Closes the SQL connection conn1*/
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jdbc.closeSQLConnection();
			}
		});
		
		btn_settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

	}

	
	
	private void createLoginEvents() {
		
		//Fires after the user clicks the login button.  If they typed in the wrong creds or they have been inactivated
		//they will be given a proper pop up message notifying them
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pass = new String(passwordLogin.getPassword());
				User u = jdbc.login(txtLoginUser.getText(), pass);
				if (u == null) {
					JOptionPane.showMessageDialog(null, "Incorrect Login Credentials");
				} else if (!u.active()) {
					JOptionPane.showMessageDialog(null, "Your account has been inactivated.  Contact your manager for help");
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
					currentSessionUserID = u.get_userID();
					btnLogout.setVisible(true);
					lblPortal.setVisible(true);
					btn_settings.setVisible(true);
				}
				
			}
		});
		//
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
	}
	
	private void createAdminEvents() {
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
		//
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
		//
		btnCancelAddQualifcation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateQualification.setVisible(false);
				pnlUserEditInfo.setVisible(true);
			}
		});
		//
		//
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
			}
		});
		//Display user information that was clicked on the left.
		listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && !listUsers.isSelectionEmpty()) {
                  pnlViewTickets.setVisible(false);
                  pnlCreateQualification.setVisible(false);
                  pnlUserEditInfo.setVisible(true);
                  displayUserInfo(listUsers.getSelectedValue().toString());
                  //saves the index of the array that was clicked on
                  btnDeleteUser.setText("ARCHIVE USER");
                  lastClickedIndex = listUsers.getSelectedIndex();
                  int id = jdbc.getIdOfUser(textUsername.getText());
                  //saved the userID of the user that is displayed
                  lastClickeduserID = id;
                  if (rdbtnAdminDetails.isSelected()) {
                	  lastClickedUserType = 1;
                  } else if (rdbtnManagerDetails.isSelected()) {
                	  lastClickedUserType = 2;
                  } else {
                	  lastClickedUserType = 3;
                  }
                }
            }
        });
		
		//Display user information that was clicked on the left.
		listArchivedUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && !listArchivedUsers.isSelectionEmpty()) {
                  pnlUserEditInfo.setVisible(true);
                  displayUserInfo(listArchivedUsers.getSelectedValue().toString());
                  //saves the index of the array that was clicked on
                  lastClickedIndex = listArchivedUsers.getSelectedIndex();
                  btnDeleteUser.setText("UNARCHIVE USER");
                  int id = jdbc.getIdOfUser(textUsername.getText());
                  //saved the userID of the user that is displayed
                  lastClickeduserID = id;
                  if (rdbtnAdminDetails.isSelected()) {
                	  lastClickedUserType = 1;
                  } else if (rdbtnManagerDetails.isSelected()) {
                	  lastClickedUserType = 2;
                  } else {
                	  lastClickedUserType = 3;
                  }
                }
            }
        });
		//Save changes made the user
		btnSaveChanges.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id = jdbc.getIdOfUser(textUsername.getText());
				lastClickeduserID = id;
				try {
					 if (rdbtnAdminDetails.isSelected()) {
	                	  lastClickedUserType = 1;
	                  } else if (rdbtnManagerDetails.isSelected()) {
	                	  lastClickedUserType = 2;
	                  } else {
	                	  lastClickedUserType = 3;
	                  }
					  jdbc.updateUser(id, lastClickedUserType, textFirstName.getText(), textLastName.getText(), textUsername.getText(), textEmail.getText(), textPhone.getText());
				} catch (SQLException e) {
					  e.printStackTrace();
				}
				updateUserList();
			}
		});
		/*Called when the -> button is clicked to add some qualifications to a user
		all edits are done with the jdbc function assignQuals()
		parameters are the userId, the ArrayList of available qualifications and the selected indices of the qualification list*/
		assignQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//parameters are the userId, the ArrayList of available qualifications and the selected indices of the qualification list
				jdbc.assignQuals(lastClickeduserID, availQuals, listAvailableQuals.getSelectedIndices());
				createQualLists(lastClickeduserID);
			}
		});
		/*Called when the <- button is clicked to remove some qualifications from a user
		all edits are done with the jdbc function UnassignQuals()*/
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
		//
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateUser.setVisible(false);
			}
		});
		//TODO make it so they turn into an 'archived' user instead of complete deletion.
		btnDeleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean b = true;
				if (btnDeleteUser.getText() == "ARCHIVE USER") {
					b = false;
				} 
				jdbc.archiveUser(lastClickeduserID, b);
				createUserList();
			}
		});
		//
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
		//
		btnAssignUserQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected = listCreateQualAvailUsers.getSelectedIndices();
				for (int i = 0; i < selected.length; i++) {
					userListAssignQual.addElement(userListAvailQual.getElementAt(selected[i]));
					userListAvailQual.remove(selected[i]);
				}
				
			}
		});
		//
		btnUnassignUserQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] selected = listCreateQualAssignedUsers.getSelectedIndices();
				for (int i = 0; i < selected.length; i++) {
					userListAvailQual.addElement(userListAssignQual.getElementAt(selected[i]));
					userListAssignQual.remove(selected[i]);
				}
			}
		});
		btnViewTickets.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loadTickets();
				if (pnlViewTickets.isVisible()) {
					pnlViewTickets.setVisible(false);
					pnlUserEditInfo.setVisible(true);
				} else {
					pnlUserEditInfo.setVisible(false);
					pnlCreateQualification.setVisible(false);
					pnlViewTickets.setVisible(true);
				}
			}
		});
		
		
		//Displays the closed ticket information
		listClosedTickets.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && !listClosedTickets.isSelectionEmpty()) {
                	pnlTicketDetails.setVisible(true);
                	displayTicketDetails(listClosedTickets.getSelectedValue().toString());
                }
            }
        });
		//Displays the open ticket information
		listOpenTickets.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && !listOpenTickets.isSelectionEmpty()) {
                	pnlTicketDetails.setVisible(true);
                	displayTicketDetails(listOpenTickets.getSelectedValue().toString());
                }
            }
        });
		//
		btnTicketDetailsClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				pnlTicketDetails.setVisible(false);
				loadTickets();
			}
		});
		//
		btnTicketDoneSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.updateTicket(currentOpenTicketId, rdbtnTicketDoneYes.isSelected());
				pnlTicketDetails.setVisible(false);
				loadTickets();
			}
		});
	}
	
	private void createManagerWorkerEvents() {
		//creates a new project 
		btnCreateNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Id= jdbc.getMaxJobID()+1;
				Job job =new Job(Id, txtProjectName.getText(), 1, textAreaProjectDescription.getText(), (Integer) null);			
				jdbc.add_project(job);
				layeredPaneManagerWorker.setVisible(true);	
				JOptionPane.showMessageDialog(null, "Project Created!");
				pnlCreateProject.setVisible(false);
				
			}
		});
		//
		btnCreateTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (pnlCreateTicket.isVisible()) {
					pnlCreateTicket.setVisible(false);
				} else {
					pnlCreateTicket.setVisible(true);
					pnlCreateJob.setVisible(false);
					pnlCreateTask.setVisible(false);
					pnlCreateProject.setVisible(false);
				}
			}
		});
		//
		btnSendTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (jdbc.createTicket(txtNewTicketTitle.getText(), txtNewTicketDesc.getText(), currentSessionUserID)) {
					JOptionPane.showMessageDialog(null, "Ticket sent to Admin");
					pnlCreateTicket.setVisible(false);
				} else {
					txtNewTicketTitle.setText("");
					txtNewTicketDesc.setText("");
					JOptionPane.showMessageDialog(null, "Ticket not sent to Admin");
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
		//assign users to job
		buttonAssignUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub			
			}
		});
		//removes users from job
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
		//open create new job tab TODO
		btn_create_job.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Qualification q=null;
				pnlCreateProject.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateJob.setVisible(true);	
				createManagersList();
				createAllUsersList();
				createQualificationsList();
			}
		});
		//closes create job tab
		btnCancelJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
			}
		});	
		//creates a new job w/ info, assigns a manager to it,
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Id= jdbc.getMaxJobID()+1;
				Job job =new Job(Id, txtJobName.getText(), 2, txtAreaJobDescription.getText(), (Integer) null);				
				jdbc.add_project(job);
				jdbc.add_Manager(manager, Id);
				jdbc.add_requiredQuals(Id,qualsArray);
				layeredPaneManagerWorker.setVisible(true);	
				JOptionPane.showMessageDialog(null, "Job Created!");
				pnlCreateJob.setVisible(false);
			}
		});
		//Display user information that was clicked on the left.
		listRequiredQuals.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	qualsArray = listRequiredQuals.getSelectedValuesList(); 
                	System.out.println(qualsArray.get(0).toString());
                }
            }
        });
		//listens for selection of a singular manager and saves it
		listAssignableManagers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                 String s = (String) listAssignableManagers.getSelectedValue();
                 int x= s.indexOf(44);
                 String last = s.substring(0, x);
                 String first = s.substring(x+2, s.length());
                 manager =jdbc.get_user(first, last);
                }
            }
		});
	}
	
	
	/*Query's the SQL database to get all users, then constructs a string "Lastname, Firstname [username]"
	This string is then added to the userList that is displayed on the left panel*/
	private void createUserList() {
		if (!userList.isEmpty() && !listUsers.isSelectionEmpty()) {
			listUsers.clearSelection();
		}
		
		if (!archivedUserList.isEmpty() && !listArchivedUsers.isSelectionEmpty()) {
			listArchivedUsers.clearSelection();
		}
		
		archivedUserList.clear();
		userList.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).active()) {
				userList.addElement(String.format("%s, %s [%s]", users.get(i).get_lastname(), users.get(i).get_firstname(), users.get(i).get_username()));
			} else {
				archivedUserList.addElement(String.format("%s, %s [%s]", users.get(i).get_lastname(), users.get(i).get_firstname(), users.get(i).get_username()));
			}
			
		}
	}
	
	private void createUserListQual() {
		userListAvailQual.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			userListAvailQual.addElement(String.format("%s, %s [%s]", users.get(i).get_lastname(), users.get(i).get_firstname(), users.get(i).get_username()));
		}
	}
	
	/*This function takes the string from the userList and uses a regular expression to get the username between the []
	then it gets the user from the database, and displays that information in the view user panel
	it also calls the createQualList method that gets both assigned and available qualifications*/ 
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
		if (u.get_usertype() == 1) {
			rdbtnAdminDetails.setSelected(true);
		} else if (u.get_usertype() == 2) {
			rdbtnManagerDetails.setSelected(true);
		} else {
			rdbtnWorkerDetails.setSelected(true);
		}
		if (u.get_email() != null) {textEmail.setText(u.get_email());} else {textEmail.setText("No Email Address in Database.");}
		if (u.get_phone() != null) {textPhone.setText(u.get_phone());} else {textPhone.setText("No Phone Number in Database.");}				
		createQualLists(u.get_userID());
	}
	
	/*This function is used when a user is updated because their firstname, lastname, or username could have changed
	meaning they need to be displayed correctly on the left side panel
	it gets the new information from the server and then users the lastClickedIndex to update the string at that spot*/
	private void updateUserList() {
		User u = jdbc.get_user(lastClickedUser);
		String s = String.format("%s, %s [%s]", u.get_lastname(), u.get_firstname(), u.get_username());
		userList.setElementAt(s, lastClickedIndex);
	}
	
	//pulls all users with selected qualification
	private void createAllUsersList(Qualification q){
		userList.clear();
		ArrayList<User> users = jdbc.getUsersWithQual(q);
		for (int i = 0; i < users.size(); i++) {
			userList.addElement(String.format("%s, %s", users.get(i).get_lastname(), users.get(i).get_firstname()));
		}
	}
	//pulls all users 
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

	/*Populates both the assigned and available qualification lists for a user after clicked on one
	Is also called each time a qualification is assigned or unassigned to update the lists.*/
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
	
	//Loads all tickets into the openTickets list and the closedTickets list for the user to be able to click on a ticket
	//to view its details and either mark it done or not done.
	private void loadTickets() {
		openTickets.clear();
		closedTickets.clear();
		ArrayList<Ticket> tickets = jdbc.getTickets();
		
		for (Ticket t : tickets) {
			User u = jdbc.get_user(t.submittedBy);
			String submittedBy = "Submitted By: "+u.get_lastname()+", "+u.get_firstname();
			String id = "Ticket ID: "+t.ticketID;
			String title = "Title: "+t.title;
			if (t.isDone) {
				String status = "Status: Closed";
				String s = String.format("%-40s%-40s%-40s%-40s", id, title, submittedBy, status);
				closedTickets.addElement(s);
			} else {
				String status = "Status: Open";
				String s = String.format("%-30s%-30s%-30s%-30s", id, title, submittedBy, status);
				openTickets.addElement(s);
			}
		}
	}
	
	private void displayTicketDetails(String s) {
		int i = 0;
		while (i < s.length() && !Character.isDigit(s.charAt(i))) i++;
		int j = i;
		while (j < s.length() && Character.isDigit(s.charAt(j))) j++;
		int id = Integer.parseInt(s.substring(i, j));
		currentOpenTicketId = id;
		Ticket t = jdbc.getTicket(id);
		lblTicketDetailsTitle.setText(t.title);
		lblTicketDetailsMessage.setText(t.message);
		User u = jdbc.get_user(t.submittedBy);
		lblTicketDetailsSubmittedBy.setText(u.get_lastname()+", "+u.get_firstname());
		lblTicketDetailsDate.setText(t.submittedDate);
		if (t.isDone) {
			rdbtnTicketDoneYes.setSelected(true);
		} else {
			rdbtnTicketDoneNo.setSelected(true);
		}
	}
	
	private void loadProjects() {
		projectsList.clear();
		
		ArrayList<Job> projects = jdbc.getProjects();
		for (Job j : projects) {
			projectsList.addElement(j.jobname);
		}
	}
}

