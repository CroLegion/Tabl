package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javafx.print.JobSettings;

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
import java.util.HashMap;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.TimeUnit;
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
import common.Task;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
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
import javax.swing.JTree;
import javax.swing.JTable;

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
	DefaultListModel listedUsersAvailList = new DefaultListModel();
	ArrayList<Qualification> listedUsersAvail = new ArrayList<Qualification>();
	DefaultListModel listedUsersAddList = new DefaultListModel();
	ArrayList<Qualification> listedUsersAdd = new ArrayList<Qualification>();
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
	private JScrollPane scrlPaneSuperJob;
	DefaultListModel superJobsList = new DefaultListModel();
	ArrayList<Job> superJobs = new ArrayList<Job>();
	private JList listSuperJobs;
	private String superJobString;
	
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
	private List selectedQual;
	private User manager;
	DefaultListModel assignedUsersList = new DefaultListModel();
	ArrayList<User> assignedUsers = new ArrayList<User>();
	DefaultListModel unassignedUsersList = new DefaultListModel();
	ArrayList<User> unassignedUser = new ArrayList<User>();
	
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
	DefaultListModel conversationMessagesList = new DefaultListModel();
	
	DefaultListModel assignedProjEmpList = new DefaultListModel();
	DefaultListModel availProjEmpList = new DefaultListModel();
	DefaultListModel projAssignQualList = new DefaultListModel();
	DefaultListModel projAvailQualList = new DefaultListModel();
	
	DefaultListModel assignedJobEmpList = new DefaultListModel();
	DefaultListModel availJobEmpList = new DefaultListModel();
	DefaultListModel assignJobQualList = new DefaultListModel();
	DefaultListModel availJobQualList = new DefaultListModel();
	
	int lastClickedIndex;
	int lastClickeduserID;
	int lastClickedUserType;
	int currentOpenTicketId;
	int currentOpenConversation;
	int currentUsertype;
	HashMap<String, Integer> conversationMap;
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
	private JList listConversations;
	private JPanel pnlConversationDetails;
	private JTextField txtNewMessage;
	private JButton btnSendMessage;
	private JList listConversationMessages;
	private JLabel lblConversationTitle;
	private JPanel pnlProjectViewerManager;
	private JLabel lblProjectName;
	private JTree tree;
	private JPanel pnlProjectTree;
	private JButton btnRefresh;
	private JPanel pnlJobViewerManager;
	private JLabel lblProjectDescrption;
	private JScrollPane scrollPane_6;
	private JTextField textFieldProjectName;
	private JScrollPane sclPaneProjAvailQual;
	private JScrollPane sclPaneProjAssignQual;
	private JList listProjAssignQual;
	private JList listProjAvailQual;
	private JButton btnAssignProjQual;
	private JButton btnUnassignProjQual;
	private JLabel lblAvailable_2;
	private JLabel lblAssigned_2;
	private JLabel lblEmployees;
	private JScrollPane scrollPane_7;
	private JScrollPane scrollPane_8;
	private JButton btnAssignEmployeeProj;
	private JButton btnUnassignEmployeeProj;
	private JList listAvailProjEmp;
	private JList listAssignedProjEmp;
	private JLabel label;
	private JLabel label_1;
	private JTextArea textProjectDesc;
	private JButton btnSaveChangesProject;
	private JLabel lblJobID;
	private boolean treeIsBeingUpdated = false;
	private JLabel lblJobName_1;
	private JTextField textFieldJobName;
	private JLabel lblJobDescription;
	private JScrollPane scrollPane_9;
	private JTextArea textAreaJobDesc;
	private JScrollPane scrollPane_10;
	private JScrollPane scrollPane_11;
	private JScrollPane scrollPane_12;
	private JScrollPane scrollPane_13;
	private JList listAvailJobQual;
	private JList listAssignJobQual;
	private JList listAvailJobEmp;
	private JList listAssignJobEmp;
	private JLabel lblQualifications_1;
	private JLabel lblEmployees_1;
	private JLabel lblAvailable_3;
	private JLabel lblAssigned_3;
	private JLabel lblAvailable_4;
	private JLabel lblAssigned_4;
	private JButton btnAssignJobQual;
	private JButton btnAssignJobEmp;
	private JButton btnUnassignJobQual;
	private JButton btnUnassignJobEmp;
	private JButton btnSaveChangesJob;
	private JLabel lblTasks;
	private JTable tasksTable;
	private DefaultTableModel dataModel = new DefaultTableModel();
	boolean tasksTableInit = false;
	private JLabel lblJobID2;
	private JScrollPane scrollPane_14;
	private JPanel pnlChangePassword;
	private JLabel lblNew;
	private JPasswordField passwordFieldNew;
	private JPasswordField passwordFieldNewConfirm;
	private JButton btnX;
	private JButton btnSubmit;
	
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

	/**
	 * Initialize all the main components for the gui including the logout button,
	 * and setting the icon
	 */
	private void initMainComponents() {
		setBackground(Color.RED);
		setTitle("TABL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 925, 685);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setVisible(true);
		contentPane.setLayout(null);
		
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 40, 899, 606);
		contentPane.add(layeredPane);
		
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminManageUsers.class.getResource("/resources/Logo.PNG")));

	}
	
	/*
	 * Initialize all of the gui components on the login page
	 */
	private void initLoginComponents() {
		layeredPaneLogin = new JLayeredPane();
		layeredPane.setLayer(layeredPaneLogin, 10);
		layeredPaneLogin.setBounds(0, 0, 896, 606);
		layeredPane.add(layeredPaneLogin);
		
		pnlLogin = new JPanel();
		layeredPaneLogin.setLayer(pnlLogin, 10);
		pnlLogin.setBounds(0, 0, 896, 611);
		layeredPaneLogin.add(pnlLogin);
		pnlLogin.setLayout(null);
		
		layeredPaneLoginComponents = new JLayeredPane();
		layeredPaneLoginComponents.setBounds(0, 0, 896, 600);
		
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
	
	/*
	 * Initialize all of the components for the Admin view of the gui
	 * Also populates the user list and gets the qualifications of each user to be ready to display
	 */
	private void initAdminComponents() {
		
		createUserList();
		createUserListQual();
		
		layeredPaneAdmin = new JLayeredPane();
		layeredPaneAdmin.setBackground(new Color(100, 149, 237));
		layeredPane.setLayer(layeredPaneAdmin, 20);
		layeredPaneAdmin.setBounds(0, 0, 896, 600);
		layeredPane.add(layeredPaneAdmin);
		
		pnlAdmin = new JPanel();
		layeredPaneAdmin.setLayer(pnlAdmin, 0);
		pnlAdmin.setBounds(0, 0, 895, 600);
		layeredPaneAdmin.add(pnlAdmin);
		
		layeredPaneAdminComponents = new JLayeredPane();
		layeredPaneAdminComponents.setBounds(0, 0, 897, 606);
		
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
		layeredPaneAdminComponents.setLayer(pnlCreateQualification, 0);
		pnlCreateQualification.setBounds(180, 38, 715, 565);
		layeredPaneAdminComponents.add(pnlCreateQualification);
		pnlCreateQualification.setVisible(false);
		
		JLabel lblCreateQualification = new JLabel("Create New Qualification");
		lblCreateQualification.setBounds(246, 11, 262, 35);
		lblCreateQualification.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblTitle = new JLabel("Title:");
		lblTitle.setBounds(125, 62, 56, 20);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setBounds(61, 105, 120, 20);
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAssignToUsers = new JLabel("Assign to Users");
		lblAssignToUsers.setBounds(250, 195, 147, 14);
		lblAssignToUsers.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JLabel lblAvailable_1 = new JLabel("Available");
		lblAvailable_1.setBounds(121, 231, 60, 14);
		lblAvailable_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		JLabel lblAssigned_1 = new JLabel("Assigned");
		lblAssigned_1.setBounds(459, 231, 67, 14);
		lblAssigned_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		txtNewQualificationName = new JTextField();
		txtNewQualificationName.setBounds(246, 64, 381, 20);
		txtNewQualificationName.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(240, 105, 387, 79);
		
		txtNewQualificationDesc = new JTextArea();
		scrollPane_1.setViewportView(txtNewQualificationDesc);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(77, 256, 154, 287);
		listCreateQualAvailUsers = new JList(userListAvailQual);
		scrollPane_2.setViewportView(listCreateQualAvailUsers);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(400, 256, 154, 287);
		
		listCreateQualAssignedUsers = new JList(userListAssignQual);
		scrollPane_3.setViewportView(listCreateQualAssignedUsers);
		
		btnAssignUserQual = new JButton("->");
		btnAssignUserQual.setBounds(271, 329, 89, 23);
		
		btnAssignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnUnassignUserQual = new JButton("<-");
		btnUnassignUserQual.setBounds(271, 385, 89, 23);
				
		btnUnassignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnCreateQual = new JButton("CREATE");
		btnCreateQual.setBounds(250, 435, 123, 41);
						
		btnCreateQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		btnCancelAddQualifcation = new JButton("Cancel");
		btnCancelAddQualifcation.setBounds(260, 500, 100, 30);

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
		pnlCreateUser.setBounds(180, 38, 715, 565);
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
		layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 30);
		pnlUserEditInfo.setBounds(180, 38, 715, 565);
		layeredPaneAdminComponents.add(pnlUserEditInfo);
		pnlUserEditInfo.setBorder(new TitledBorder(null, "User Edit/Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(264, 0, 127, 45);
		lblFullName.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblFirstName = new JLabel("First Name:");
		lblFirstName.setBounds(86, 53, 79, 17);
		lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		textFirstName = new JTextField();
		textFirstName.setBounds(214, 56, 330, 20);
		textFirstName.setColumns(10);
		textLastName = new JTextField();
		textLastName.setBounds(214, 87, 330, 20);
		textLastName.setColumns(10);
		textUsername = new JTextField();
		textUsername.setBounds(214, 130, 330, 20);
		textUsername.setColumns(10);
		textEmail = new JTextField();
		textEmail.setBounds(214, 177, 330, 20);
		textEmail.setColumns(10);
		textPhone = new JTextField();
		textPhone.setBounds(214, 217, 330, 20);
		textPhone.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(16, 279, 699, 2);
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		JScrollPane scrlPaneAssignedQuals = new JScrollPane();
		scrlPaneAssignedQuals.setBounds(264, 320, 174, 234);
		
		unassignQual = new JButton("<-");
		unassignQual.setBounds(190, 416, 64, 29);
		
		unassignQual.setToolTipText("Click to remove assigned Qualifications");
		unassignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		assignQual = new JButton("->");
		assignQual.setBounds(190, 361, 64, 29);
		
		assignQual.setToolTipText("Click to move selected Qualifications to Assigned");
		assignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		
		
		listAssignedQuals = new JList(assignedQualList);
		scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
														
		JLabel lblLastName = new JLabel("Last Name:");
		lblLastName.setBounds(86, 91, 78, 17);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setBounds(90, 130, 74, 17);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblEmailAddress = new JLabel("Email Address:");
		lblEmailAddress.setBounds(63, 177, 101, 17);
		lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblPhoneNumber = new JLabel("Phone Number:");
		lblPhoneNumber.setBounds(56, 217, 109, 17);
		lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setBounds(65, 292, 60, 17);
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 14));
		JLabel lblAssigned = new JLabel("Assigned");
		lblAssigned.setBounds(304, 292, 86, 17);
		lblAssigned.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnChangePassword = new JButton("Change Password");
		btnChangePassword.setBounds(318, 248, 166, 20);
		
		btnSaveChanges = new JButton("Save Changes");
		btnSaveChanges.setBounds(162, 248, 146, 20);
		
		btnSaveChanges.setToolTipText("Save Changes to Database");
		
		JScrollPane scrlPaneAvailableQuals = new JScrollPane();
		scrlPaneAvailableQuals.setBounds(16, 320, 174, 234);
		
		listAvailableQuals = new JList(availableQualList);
		scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
		
		pnlDeleteUser = new JPanel();
		pnlDeleteUser.setBounds(496, 361, 166, 89);
		pnlDeleteUser.setBorder(new TitledBorder(null, "WARNING AREA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlDeleteUser.setBackground(new Color(245, 222, 179));
		pnlDeleteUser.setLayout(null);
		
		btnDeleteUser = new JButton("ARCHIVE USER");
														
		btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnDeleteUser.setBounds(17, 27, 140, 39);
		pnlDeleteUser.add(btnDeleteUser);
		
		JLabel lblUserType_1 = new JLabel("User Type");
		lblUserType_1.setBounds(565, 14, 82, 20);
		lblUserType_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		rdbtnAdminDetails = new JRadioButton("Admin");
		rdbtnAdminDetails.setBounds(562, 41, 109, 23);
		buttonGroup_1.add(rdbtnAdminDetails);
		
		rdbtnManagerDetails = new JRadioButton("Manager");
		rdbtnManagerDetails.setBounds(562, 67, 120, 23);
		buttonGroup_1.add(rdbtnManagerDetails);
		
		rdbtnWorkerDetails = new JRadioButton("Worker");
		rdbtnWorkerDetails.setBounds(562, 90, 120, 23);
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
		
		pnlChangePassword = new JPanel();
		pnlChangePassword.setBorder(new TitledBorder(null, "Change Password", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlChangePassword.setBounds(554, 130, 151, 187);
		pnlUserEditInfo.add(pnlChangePassword);
		pnlChangePassword.setLayout(null);
		
		lblNew = new JLabel("NEW");
		lblNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblNew.setBounds(48, 41, 46, 14);
		pnlChangePassword.add(lblNew);
		
		passwordFieldNew = new JPasswordField();
		passwordFieldNew.setBounds(28, 66, 95, 20);
		pnlChangePassword.add(passwordFieldNew);
		
		passwordFieldNewConfirm = new JPasswordField();
		passwordFieldNewConfirm.setBounds(28, 114, 95, 20);
		pnlChangePassword.add(passwordFieldNewConfirm);
		
		JLabel lblConfirm = new JLabel("Confirm");
		lblConfirm.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirm.setBounds(48, 89, 46, 14);
		pnlChangePassword.add(lblConfirm);
		
		btnSubmit = new JButton("Submit");

		btnSubmit.setBounds(10, 153, 79, 23);
		pnlChangePassword.add(btnSubmit);
		
		btnX = new JButton("X");
		
		btnX.setBounds(83, 151, 58, 25);
		pnlChangePassword.add(btnX);
														
		pnlViewTickets = new JPanel();
		layeredPaneAdminComponents.setLayer(pnlViewTickets, 0);
		pnlViewTickets.setBounds(180, 38, 715, 565);
		layeredPaneAdminComponents.add(pnlViewTickets);
		pnlViewTickets.setVisible(false);
		
		pnlOpenTicketsLbl = new JPanel();
		pnlOpenTicketsLbl.setBounds(0, 0, 715, 26);
		pnlOpenTicketsLbl.setBackground(UIManager.getColor("Button.shadow"));
		pnlOpenTicketsLbl.setLayout(null);
		
		lblOpenTickets = new JLabel("Open Tickets");
		lblOpenTickets.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblOpenTickets.setBounds(312, 0, 189, 15);
		pnlOpenTicketsLbl.add(lblOpenTickets);
		
		pnlClosedTicketsLbl = new JPanel();
		pnlClosedTicketsLbl.setBounds(0, 329, 715, 26);
		pnlClosedTicketsLbl.setBackground(SystemColor.controlShadow);
		pnlClosedTicketsLbl.setLayout(null);
		
		lblClosedTickets = new JLabel("Closed Tickets");
		lblClosedTickets.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblClosedTickets.setBounds(302, 0, 189, 26);
		pnlClosedTicketsLbl.add(lblClosedTickets);
		
		scrlOpenTickets = new JScrollPane();
		scrlOpenTickets.setBounds(0, 22, 715, 307);
		
		scrlClosedTickets = new JScrollPane();
		scrlClosedTickets.setBounds(0, 354, 715, 211);
		
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
		scrollPane.setBounds(0, 23, 157, 249);
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
		pnlTicketDetails.setBounds(180, 38, 715, 565);
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
		lblDone.setBounds(291, 373, 87, 46);
		pnlTicketDetails.add(lblDone);
		
		rdbtnTicketDoneYes = new JRadioButton("Yes");
		buttonGroup_2.add(rdbtnTicketDoneYes);
		rdbtnTicketDoneYes.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTicketDoneYes.setBounds(221, 426, 109, 23);
		pnlTicketDetails.add(rdbtnTicketDoneYes);
		
		rdbtnTicketDoneNo = new JRadioButton("No");
		buttonGroup_2.add(rdbtnTicketDoneNo);
		rdbtnTicketDoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTicketDoneNo.setBounds(372, 426, 109, 23);
		pnlTicketDetails.add(rdbtnTicketDoneNo);
		
		lblTicketDetailsTitle = new JLabel("");
		lblTicketDetailsTitle.setBounds(200, 80, 487, 23);
		pnlTicketDetails.add(lblTicketDetailsTitle);
		
		btnTicketDetailsClose = new JButton("Close");
		
		btnTicketDetailsClose.setBounds(614, 11, 89, 23);
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
		
		btnTicketDoneSave.setBounds(278, 467, 89, 23);
		pnlTicketDetails.add(btnTicketDoneSave);
		
		JPanel pnlArchivedUsers = new JPanel();
		pnlArchivedUsers.setBackground(Color.LIGHT_GRAY);
		pnlArchivedUsers.setBounds(0, 273, 157, 28);
		layeredPaneAdminComponents.add(pnlArchivedUsers);
		pnlArchivedUsers.setLayout(null);
		
		JLabel lblNewLabel_6 = new JLabel("ARCHIVED USERS");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_6.setBounds(24, 5, 107, 20);
		pnlArchivedUsers.add(lblNewLabel_6);
		
		JScrollPane scrollPane_5 = new JScrollPane();
		scrollPane_5.setBounds(0, 300, 157, 303);
		layeredPaneAdminComponents.add(scrollPane_5);
		
		listArchivedUsers = new JList(archivedUserList);
		listArchivedUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_5.setViewportView(listArchivedUsers);
		pnlAdmin.add(layeredPaneAdminComponents);
		
		pnlChangePassword.setVisible(false);
	}
	
	
	/*
	 * Initializes all of the gui components for the Manager/Worker view
	 */
	private void initManagerWorkerComponents() {
		layeredPaneManagerWorker = new JLayeredPane();
		layeredPane.setLayer(layeredPaneManagerWorker, 0);
		layeredPaneManagerWorker.setBounds(0, 0, 896, 606);
		layeredPane.add(layeredPaneManagerWorker);
		
		pnlManagerWorker = new JPanel();
		layeredPaneManagerWorker.setLayer(pnlManagerWorker, 10);
		pnlManagerWorker.setBounds(0, 0, 899, 607);
		layeredPaneManagerWorker.add(pnlManagerWorker);
		
		btn_create_project = new JButton("Create Project");
		btn_create_project.setBounds(316, 0, 136, 28);
		
		btn_create_task = new JButton("Create Task");
		btn_create_task.setBounds(450, 0, 115, 28);
		
		btn_create_job = new JButton("Create Job");
		btn_create_job.setBounds(568, 0, 97, 28);
		
		layeredPaneManagerWorkerComponents = new JLayeredPane();
		layeredPaneManagerWorkerComponents.setBounds(181, 41, 717, 566);
		//create project end
		//Create task start			
		pnlCreateTask = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateTask, 0);

		pnlCreateTask.setBounds(0, 0, 717, 567);

		layeredPaneManagerWorkerComponents.add(pnlCreateTask);
		pnlCreateTask.setVisible(false);
		
		JLabel lblTaskName = new JLabel("Name of the Task:");
		lblTaskName.setBounds(163, 70, 114, 14);
		
		JLabel lblTaskDescription = new JLabel("Description of the Task:");

		lblTaskDescription.setBounds(137, 213, 140, 14);

		
		JLabel lblTaskReason = new JLabel("Reason why it should be added:");
		lblTaskReason.setBounds(94, 327, 180, 14);
		
		JScrollPane scrlPaneDescription = new JScrollPane();

		scrlPaneDescription.setBounds(365, 213, 216, 78);

		
		JScrollPane scrlPaneReason = new JScrollPane();
		scrlPaneReason.setBounds(366, 327, 215, 105);
		
		btnCreateNewTask = new JButton("Create new Task");
		btnCreateNewTask.setBounds(467, 510, 140, 23);
		
		textTaskName = new JTextField();
		textTaskName.setBounds(366, 67, 180, 20);
		textTaskName.setColumns(10);
		
		btnCancelTask = new JButton("Cancel");
		btnCancelTask.setBounds(291, 510, 86, 23);
		
		txtAreaReason = new JTextArea();
		scrlPaneReason.setViewportView(txtAreaReason);
		
		txtAreaDescription = new JTextArea();
		scrlPaneDescription.setViewportView(txtAreaDescription);
		pnlCreateTask.setLayout(null);
		
		JLabel lblCreateTask = new JLabel("Create a new Task");
		lblCreateTask.setBounds(291, 22, 146, 22);
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
		
		JLabel lblWhereItShould = new JLabel("Where it should fall under:");

		lblWhereItShould.setBounds(137, 114, 171, 14);
		pnlCreateTask.add(lblWhereItShould);
		
		scrlPaneSuperJob = new JScrollPane();
		scrlPaneSuperJob.setBounds(366, 114, 180, 78);

		pnlCreateTask.add(scrlPaneSuperJob);
		listSuperJobs = new JList(superJobsList);
		scrlPaneSuperJob.setViewportView(listSuperJobs);
		//create user end				
		//create project start		
		pnlCreateProject = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateProject, 0);
		pnlCreateProject.setBounds(0, 0, 717, 567);
		layeredPaneManagerWorkerComponents.add(pnlCreateProject);
		pnlCreateProject.setVisible(false);
		
		lblNewLabel = new JLabel("Project name:");
		lblNewLabel.setBounds(149, 66, 86, 14);
		
		lblNewLabel_1 = new JLabel("Description:");
		lblNewLabel_1.setBounds(157, 117, 78, 14);
		
		JScrollPane scrlPaneProjectDescription = new JScrollPane();
		scrlPaneProjectDescription.setBounds(366, 117, 205, 81);
		
		JScrollPane scrlPaneQualifications = new JScrollPane();
		scrlPaneQualifications.setBounds(107, 252, 154, 164);
		
		JScrollPane scrlPaneUsersAvailable = new JScrollPane();
		scrlPaneUsersAvailable.setBounds(283, 252, 154, 164);
		
		btnAssign = new JButton("->");
		btnAssign.setBounds(447, 282, 45, 23);
		btnRemove = new JButton("<-");
		btnRemove.setBounds(447, 341, 45, 23);
		
		btnCreateNewProject = new JButton("Create new Project");
		btnCreateNewProject.setBounds(483, 485, 154, 23);
		
		btnCancelProject = new JButton("Cancel");
		btnCancelProject.setBounds(277, 485, 82, 23);
		
		textAreaProjectDescription = new JTextArea();
		scrlPaneProjectDescription.setViewportView(textAreaProjectDescription);
		
		listUsersAvailable = new JList(userList);
		scrlPaneUsersAvailable.setViewportView(listUsersAvailable);
		
		listQualifications = new JList(listedQualList);
		listQualifications.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneQualifications.setViewportView(listQualifications);
		
		JLabel lblNewLabel_2 = new JLabel("Qualifications:");
		lblNewLabel_2.setBounds(110, 227, 96, 14);
		
		JLabel lblNewLabel_3 = new JLabel("Users to add:");
		lblNewLabel_3.setBounds(283, 227, 76, 14);
		
		JLabel lblNewLabel_4 = new JLabel("Users added:");
		lblNewLabel_4.setBounds(532, 227, 86, 14);
		
		JLabel lblCreateANew = new JLabel("Create a new Project");
		lblCreateANew.setBounds(299, 29, 163, 22);
		lblCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		txtProjectName = new JTextField();
		txtProjectName.setBounds(367, 63, 187, 20);
		txtProjectName.setColumns(10);
		
		JScrollPane scrlPaneUsersAdded = new JScrollPane();
		scrlPaneUsersAdded.setBounds(520, 264, 154, 164);
		
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

		layeredPaneManagerWorkerComponents.setLayer(pnlCreateJob, 0);
		pnlCreateJob.setBounds(0, 0, 717, 567);

		layeredPaneManagerWorkerComponents.add(pnlCreateJob);
		pnlCreateJob.setVisible(false);
		
		JLabel lblCreateNewJob = new JLabel("Create new Job");
		lblCreateNewJob.setBounds(302, 22, 122, 22);
		lblCreateNewJob.setFont(new Font("Tahoma", Font.PLAIN, 18));
		

		JScrollPane scrlPaneJobDescription = new JScrollPane();
		scrlPaneJobDescription.setBounds(395, 80, 224, 69);
		

		JScrollPane scrlPaneAssignableManagers = new JScrollPane();
		scrlPaneAssignableManagers.setBounds(395, 171, 234, 69);
		
		JScrollPane scrlPaneRequiredQuals = new JScrollPane();
		scrlPaneRequiredQuals.setBounds(395, 251, 145, 104);
		
		JScrollPane scrlPaneAvailableUsers = new JScrollPane();

		scrlPaneAvailableUsers.setBounds(319, 391, 148, 138);

		
		listAssignableManagers = new JList(managerList);
		listAssignableManagers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneAssignableManagers.setViewportView(listAssignableManagers);
		
		JLabel lblJobName = new JLabel("Name of the Job:");
		lblJobName.setBounds(150, 52, 114, 14);
		
		listRequiredQuals = new JList(listedQualList);
		scrlPaneRequiredQuals.setViewportView(listRequiredQuals);
		
		listAvailableUsers = new JList(unassignedUsersList);
		listAvailableUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneAvailableUsers.setViewportView(listAvailableUsers);
		
		buttonAssignUsers = new JButton("->");
		buttonAssignUsers.setBounds(477, 433, 45, 23);
		
		buttonRemoveUsers = new JButton("<-");
		buttonRemoveUsers.setBounds(477, 467, 45, 23);
		
		txtJobName = new JTextField();
		txtJobName.setBounds(395, 49, 86, 20);
		txtJobName.setColumns(10);

		btnCreateJob = new JButton("Create Job");
		btnCreateJob.setBounds(561, 532, 103, 23);
		
		btnCancelJob  = new JButton("Cancel");
		btnCancelJob.setBounds(91, 532, 86, 23);
		
		JLabel lblNewLabel_9 = new JLabel("Description:");
		lblNewLabel_9.setBounds(176, 166, 88, 14);
		
		JLabel lblNewLabel_10 = new JLabel("Assignable Manager:");
		lblNewLabel_10.setBounds(141, 241, 123, 14);
		pnlCreateJob.setLayout(null);
		pnlCreateJob.add(lblCreateNewJob);
		pnlCreateJob.add(lblJobName);
		pnlCreateJob.add(lblNewLabel_9);
		pnlCreateJob.add(lblNewLabel_10);
		
		JLabel lblRequiredQualifications = new JLabel("Required Qualifications:");
		lblRequiredQualifications.setBounds(119, 356, 145, 14);
		pnlCreateJob.add(lblRequiredQualifications);
		
		JLabel lblAvailableUsers_1 = new JLabel("Available Users");

		lblAvailableUsers_1.setBounds(324, 366, 100, 14);
		
		JLabel lblAssignedUsers = new JLabel("Assigned Users");
		lblAssignedUsers.setBounds(533, 366, 94, 14);
		
		JLabel lblUsersList = new JLabel("User List:");
		lblUsersList.setBounds(188, 393, 76, 14);
		

		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);



		

		pnlCreateJob.add(lblAssignedUsers);
		
		pnlCreateJob.add(lblUsersList);
		
		pnlCreateJob.add(txtJobName);
		
		txtAreaJobDescription = new JTextArea();
		scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
		pnlCreateJob.add(scrlPaneJobDescription);
		pnlCreateJob.add(scrlPaneAssignableManagers);
		pnlCreateJob.add(scrlPaneRequiredQuals);
		pnlCreateJob.add(scrlPaneAvailableUsers);
		
		JScrollPane scrlPaneAssignedUsers = new JScrollPane();
		scrlPaneAssignedUsers.setBounds(533, 391, 148, 130);
		
		listAssignedUsers = new JList(assignedUsersList);
		listAssignedUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrlPaneAssignedUsers.setViewportView(listAssignedUsers);

		pnlCreateJob.setLayout(null);
		pnlCreateJob.add(lblCreateNewJob);
		pnlCreateJob.add(lblJobName);
		pnlCreateJob.add(txtJobName);

		pnlCreateJob.add(scrlPaneJobDescription);
		
		txtAreaJobDescription = new JTextArea();
		scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
		pnlCreateJob.add(scrlPaneAssignableManagers);
		pnlCreateJob.add(lblRequiredQualifications);
		pnlCreateJob.add(scrlPaneRequiredQuals);
		pnlCreateJob.add(lblAvailableUsers_1);
		pnlCreateJob.add(lblAssignedUsers);
		pnlCreateJob.add(lblUsersList);
		pnlCreateJob.add(scrlPaneAvailableUsers);

		pnlCreateJob.add(buttonAssignUsers);
		pnlCreateJob.add(buttonRemoveUsers);
		pnlCreateJob.add(btnCancelJob);
		pnlCreateJob.add(btnCreateJob);
		
		btnCreateTicket = new JButton("Create Ticket");
		btnCreateTicket.setBounds(762, 3, 115, 23);
		
		pnlCreateTicket = new JPanel();
		pnlCreateTicket.setBorder(new TitledBorder(null, "Ticket Creator", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		layeredPaneManagerWorkerComponents.setLayer(pnlCreateTicket, 0);
		pnlCreateTicket.setBounds(0, 0, 717, 567);
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
		
		pnlConversationDetails = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlConversationDetails, 0);
		pnlConversationDetails.setBounds(0, 0, 717, 567);
		layeredPaneManagerWorkerComponents.add(pnlConversationDetails);
		pnlConversationDetails.setLayout(null);
		
		lblConversationTitle = new JLabel("Conversation");
		lblConversationTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblConversationTitle.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblConversationTitle.setBounds(24, 11, 695, 40);
		pnlConversationDetails.add(lblConversationTitle);
		
		txtNewMessage = new JTextField();
		txtNewMessage.setBounds(110, 526, 488, 20);
		pnlConversationDetails.add(txtNewMessage);
		txtNewMessage.setColumns(10);
		
		btnSendMessage = new JButton("Send");

		btnSendMessage.setBounds(608, 525, 89, 23);
		pnlConversationDetails.add(btnSendMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(110, 62, 584, 452);
		pnlConversationDetails.add(scrollPane);
		
		listConversationMessages = new JList(conversationMessagesList);
		scrollPane.setViewportView(listConversationMessages);
		
		btnRefresh = new JButton("Refresh");

		btnRefresh.setBounds(597, 36, 89, 23);
		pnlConversationDetails.add(btnRefresh);
		
		pnlProjectViewerManager = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlProjectViewerManager, 0);
		pnlProjectViewerManager.setBounds(0, 0, 717, 567);
		layeredPaneManagerWorkerComponents.add(pnlProjectViewerManager);
		pnlProjectViewerManager.setLayout(null);
		
		lblProjectName = new JLabel("Project Name: ");
		lblProjectName.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblProjectName.setBounds(157, 0, 126, 32);
		pnlProjectViewerManager.add(lblProjectName);
		
		lblProjectDescrption = new JLabel("Project Description:");
		lblProjectDescrption.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblProjectDescrption.setBounds(31, 35, 139, 32);
		pnlProjectViewerManager.add(lblProjectDescrption);
		
		scrollPane_6 = new JScrollPane();
		scrollPane_6.setBounds(180, 43, 503, 40);
		pnlProjectViewerManager.add(scrollPane_6);
		
		textProjectDesc = new JTextArea();
		scrollPane_6.setViewportView(textProjectDesc);
		
		JLabel lblQualifications = new JLabel("Qualifications:");
		lblQualifications.setFont(new Font("Dialog", Font.BOLD, 12));
		lblQualifications.setBounds(45, 103, 102, 14);
		pnlProjectViewerManager.add(lblQualifications);
		
		btnSaveChangesProject = new JButton("Save Changes");
		
		btnSaveChangesProject.setBounds(294, 358, 115, 32);
		pnlProjectViewerManager.add(btnSaveChangesProject);
		
		textFieldProjectName = new JTextField();
		textFieldProjectName.setToolTipText("Click on a Project or Job to View Details");
		textFieldProjectName.setBounds(283, 8, 400, 24);
		pnlProjectViewerManager.add(textFieldProjectName);
		textFieldProjectName.setColumns(10);
		
		sclPaneProjAvailQual = new JScrollPane();
		sclPaneProjAvailQual.setBounds(31, 146, 115, 176);
		pnlProjectViewerManager.add(sclPaneProjAvailQual);
		
		listProjAvailQual = new JList(projAvailQualList);
		listProjAvailQual.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		sclPaneProjAvailQual.setViewportView(listProjAvailQual);
		
		sclPaneProjAssignQual = new JScrollPane();
		sclPaneProjAssignQual.setBounds(197, 146, 126, 176);
		pnlProjectViewerManager.add(sclPaneProjAssignQual);
		
		listProjAssignQual = new JList(projAssignQualList);
		listProjAssignQual.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		sclPaneProjAssignQual.setViewportView(listProjAssignQual);
		
		btnAssignProjQual = new JButton("->");
		
		btnAssignProjQual.setBounds(150, 186, 45, 24);
		pnlProjectViewerManager.add(btnAssignProjQual);
		
		btnUnassignProjQual = new JButton("<-");

		btnUnassignProjQual.setBounds(150, 223, 45, 24);
		pnlProjectViewerManager.add(btnUnassignProjQual);
		
		lblAvailable_2 = new JLabel("Available");
		lblAvailable_2.setBounds(30, 128, 117, 14);
		pnlProjectViewerManager.add(lblAvailable_2);
		
		lblAssigned_2 = new JLabel("Assigned");
		lblAssigned_2.setBounds(197, 128, 126, 14);
		pnlProjectViewerManager.add(lblAssigned_2);
		
		lblEmployees = new JLabel("Employees:");
		lblEmployees.setFont(new Font("Dialog", Font.BOLD, 12));
		lblEmployees.setBounds(376, 103, 84, 14);
		pnlProjectViewerManager.add(lblEmployees);
		
		scrollPane_7 = new JScrollPane();
		scrollPane_7.setBounds(369, 146, 115, 176);
		pnlProjectViewerManager.add(scrollPane_7);
		
		listAvailProjEmp = new JList(availProjEmpList);
		listAvailProjEmp.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_7.setViewportView(listAvailProjEmp);
		
		scrollPane_8 = new JScrollPane();
		scrollPane_8.setBounds(549, 146, 115, 176);
		pnlProjectViewerManager.add(scrollPane_8);
		
		listAssignedProjEmp = new JList(assignedProjEmpList);
		listAssignedProjEmp.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrollPane_8.setViewportView(listAssignedProjEmp);
		
		btnAssignEmployeeProj = new JButton("->");
		
		btnAssignEmployeeProj.setBounds(494, 187, 45, 24);
		pnlProjectViewerManager.add(btnAssignEmployeeProj);
		
		btnUnassignEmployeeProj = new JButton("<-");
		
		btnUnassignEmployeeProj.setBounds(494, 224, 45, 24);
		pnlProjectViewerManager.add(btnUnassignEmployeeProj);
		
		label = new JLabel("Available");
		label.setBounds(369, 128, 115, 14);
		pnlProjectViewerManager.add(label);
		
		label_1 = new JLabel("Assigned");
		label_1.setBounds(549, 128, 115, 14);
		pnlProjectViewerManager.add(label_1);
		
		lblJobID = new JLabel("JobID");
		lblJobID.setBounds(197, 458, 46, 14);
		pnlProjectViewerManager.add(lblJobID);
		lblJobID.setVisible(false);
		
		pnlJobViewerManager = new JPanel();
		layeredPaneManagerWorkerComponents.setLayer(pnlJobViewerManager, 0);
		pnlJobViewerManager.setBounds(0, 0, 717, 567);
		layeredPaneManagerWorkerComponents.add(pnlJobViewerManager);
		pnlJobViewerManager.setLayout(null);
		
		lblJobName_1 = new JLabel("Job Name:");
		lblJobName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJobName_1.setBounds(170, 11, 77, 23);
		pnlJobViewerManager.add(lblJobName_1);
		
		textFieldJobName = new JTextField();
		textFieldJobName.setBounds(291, 12, 357, 23);
		pnlJobViewerManager.add(textFieldJobName);
		textFieldJobName.setColumns(10);
		
		lblJobDescription = new JLabel("Job Description:");
		lblJobDescription.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblJobDescription.setBounds(59, 52, 122, 23);
		pnlJobViewerManager.add(lblJobDescription);
		
		scrollPane_9 = new JScrollPane();
		scrollPane_9.setBounds(201, 58, 471, 41);
		pnlJobViewerManager.add(scrollPane_9);
		
		textAreaJobDesc = new JTextArea();
		scrollPane_9.setViewportView(textAreaJobDesc);
		
		scrollPane_10 = new JScrollPane();
		scrollPane_10.setBounds(35, 146, 102, 154);
		pnlJobViewerManager.add(scrollPane_10);
		
		listAvailJobQual = new JList(availJobQualList);
		scrollPane_10.setViewportView(listAvailJobQual);
		
		scrollPane_11 = new JScrollPane();
		scrollPane_11.setBounds(192, 146, 102, 154);
		pnlJobViewerManager.add(scrollPane_11);
		
		listAssignJobQual = new JList(assignJobQualList);
		scrollPane_11.setViewportView(listAssignJobQual);
		
		scrollPane_12 = new JScrollPane();
		scrollPane_12.setBounds(381, 146, 102, 154);
		pnlJobViewerManager.add(scrollPane_12);
		
		listAvailJobEmp = new JList(availJobEmpList);
		scrollPane_12.setViewportView(listAvailJobEmp);
		
		scrollPane_13 = new JScrollPane();
		scrollPane_13.setBounds(546, 146, 102, 154);
		pnlJobViewerManager.add(scrollPane_13);
		
		listAssignJobEmp = new JList(assignedJobEmpList);
		scrollPane_13.setViewportView(listAssignJobEmp);
		
		lblQualifications_1 = new JLabel("Qualifications");
		lblQualifications_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblQualifications_1.setBounds(135, 106, 85, 14);
		pnlJobViewerManager.add(lblQualifications_1);
		
		lblEmployees_1 = new JLabel("Employees");
		lblEmployees_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmployees_1.setBounds(475, 106, 77, 14);
		pnlJobViewerManager.add(lblEmployees_1);
		
		lblAvailable_3 = new JLabel("Available");
		lblAvailable_3.setBounds(35, 131, 102, 14);
		pnlJobViewerManager.add(lblAvailable_3);
		
		lblAssigned_3 = new JLabel("Assigned");
		lblAssigned_3.setBounds(192, 131, 102, 14);
		pnlJobViewerManager.add(lblAssigned_3);
		
		lblAvailable_4 = new JLabel("Available");
		lblAvailable_4.setBounds(381, 131, 102, 14);
		pnlJobViewerManager.add(lblAvailable_4);
		
		lblAssigned_4 = new JLabel("Assigned");
		lblAssigned_4.setBounds(534, 131, 102, 14);
		pnlJobViewerManager.add(lblAssigned_4);
		
		btnAssignJobQual = new JButton("->");

		btnAssignJobQual.setBounds(135, 176, 57, 23);
		pnlJobViewerManager.add(btnAssignJobQual);
		
		btnAssignJobEmp = new JButton("->");
		btnAssignJobEmp.setBounds(485, 176, 57, 23);
		pnlJobViewerManager.add(btnAssignJobEmp);
		
		btnUnassignJobQual = new JButton("<-");
		btnUnassignJobQual.setBounds(135, 223, 57, 23);
		pnlJobViewerManager.add(btnUnassignJobQual);
		
		btnUnassignJobEmp = new JButton("<-");
		btnUnassignJobEmp.setBounds(485, 223, 57, 23);
		pnlJobViewerManager.add(btnUnassignJobEmp);
		
		btnSaveChangesJob = new JButton("Save Changes");
		
		btnSaveChangesJob.setBounds(291, 311, 131, 23);
		pnlJobViewerManager.add(btnSaveChangesJob);
		
		lblTasks = new JLabel("Tasks:");
		lblTasks.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTasks.setBounds(44, 360, 93, 23);
		pnlJobViewerManager.add(lblTasks);
		
		lblJobID2 = new JLabel("New label");
		lblJobID2.setBounds(37, 17, 46, 14);
		pnlJobViewerManager.add(lblJobID2);
		
		
		

		lblJobID2.setVisible(false);
		
		pnlMessages = new JPanel();
		pnlMessages.setBackground(Color.LIGHT_GRAY);
		pnlMessages.setBounds(0, 363, 181, 28);
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
		scrlConversations.setBounds(0, 390, 179, 217);
		pnlManagerWorker.add(scrlConversations);
		
		listConversations = new JList(conversationsList);
		listConversations.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		scrlConversations.setViewportView(listConversations);
		
		pnlProjectTree = new JPanel();
		pnlProjectTree.setBounds(0, 27, 181, 336);
		pnlManagerWorker.add(pnlProjectTree);
		pnlProjectTree.setLayout(null);
		
		
		
		btnLogout = new JButton("LOGOUT");

		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnLogout.setBounds(800, 11, 89, 23);
		contentPane.add(btnLogout);
		btnLogout.setVisible(false);
		
		layeredPane_1 = new JLayeredPane();
		layeredPane_1.setBounds(10, 8, 678, 25);
		contentPane.add(layeredPane_1);
		
		lblPortal = new JLabel("Admin Portal");
		lblPortal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPortal.setBounds(0, 0, 629, 25);
		layeredPane_1.add(lblPortal);
		lblPortal.setVisible(false);
		//create job end		
		
	    tasksTable = new JTable();
	    tasksTable.setModel(dataModel);
	    scrollPane_14 = new JScrollPane(tasksTable);
		scrollPane_14.setBounds(35, 394, 672, 154);
		pnlJobViewerManager.add(scrollPane_14);
		
		loadProjects();
		loadConversations();
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

	}

	
	/*
	 * creates the listener events for the Login Components
	 */
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
			    	currentUsertype = u.get_usertype();
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
						clearAllManagerPanels();
					} else {
						//They are a Worker
						layeredPane.setLayer(layeredPaneManagerWorker, 10);
						layeredPane.setLayer(layeredPaneLogin, 0);
						layeredPaneManagerWorker.setLayer(pnlManagerWorker, 10);
						System.out.println("logged in as Worker");
						lblPortal.setText("Worker Portal - "+u.get_firstname()+ " " + u.get_lastname());
						clearAllManagerPanels();
						
					}
					currentSessionUserID = u.get_userID();
					btnLogout.setVisible(true);
					lblPortal.setVisible(true);
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
				txtLoginUser.setText("");
				passwordLogin.setText("");
				
			}
		});
	}
	
	/*
	 * creates the listener events for the Admin Components
	 */
	private void createAdminEvents() {
		//moves the create user panel to the front to allow the user to enter the new user info
		btn_create_new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
				pnlCreateTask.setVisible(false);
				pnlCreateProject.setVisible(false);
				pnlCreateQualification.setVisible(false);
				clearAllManagerPanels();
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
				clearAllManagerPanels();
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
				clearAllManagerPanels();
				pnlUserEditInfo.setVisible(true);
			}
		});
		//
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
				pnlChangePassword.setVisible(true);
			}
			
		});
		btnX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlChangePassword.setVisible(false);
				passwordFieldNew.setText("");
				passwordFieldNewConfirm.setText("");
			}
		});
		//Display user information that was clicked on the left.
		listUsers.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting() && !listUsers.isSelectionEmpty()) {
                  pnlViewTickets.setVisible(false);
                  pnlCreateQualification.setVisible(false);
                  clearAllManagerPanels();
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
		//
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
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pass = new String(passwordFieldNew.getPassword());
				String pass1 = new String(passwordFieldNewConfirm.getPassword());
				
				if (pass.equals(pass1)) {
					jdbc.changePassword(lastClickeduserID, pass);
					passwordFieldNew.setText("");
					passwordFieldNewConfirm.setText("");
					pnlChangePassword.setVisible(false);
					JOptionPane.showMessageDialog(null, "Password Changed!");
				} else {
					JOptionPane.showMessageDialog(null, "Entered Passwords do not match!");
				}
				
			}
		});
	}
	
	/*
	 * creates the listener events for the Manager/Worker Components
	 */
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
					clearAllManagerPanels();
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
				Task task = new Task(textTaskName.getText(), txtAreaDescription.getText(), txtAreaReason.getText(), jdbc.getTaskID(superJobString), jdbc.getMaxTaskID()+1);
				jdbc.add_task(task);
				JOptionPane.showMessageDialog(null, "Task Created!");
				textTaskName.setText("");
				txtAreaReason.setText("");
				txtAreaDescription.setText("");
				pnlCreateTask.setVisible(false);

			}
		});	
	
		
//		assign users to job		
		buttonAssignUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub	
				Object x = listAvailableUsers.getSelectedValue();
				assignedUsersList.addElement(x);
			    unassignedUsersList.removeElement(x);
				
			}
		});
		//removes users from job
		buttonRemoveUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub		
				Object x = listAssignedUsers.getSelectedValue();
				unassignedUsersList.addElement(x);
			    assignedUsersList.removeElement(x);

			}
		});
		//Open create new project tab
		btn_create_project.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearAllManagerPanels();
				pnlCreateProject.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);	
				createQualificationsList();
				createAllUsersList();
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
				clearAllManagerPanels();
				pnlCreateTask.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);
				loadJobs();
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
				clearAllManagerPanels();
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
				txtJobName.setText("");
				txtAreaJobDescription.setText("");
			}
		});	
		//creates a new job w/ info, assigns a manager to it,
		btnCreateJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int Id= jdbc.getMaxJobID()+1;
				Job job =new Job(Id, txtJobName.getText(), 2, txtAreaJobDescription.getText(), 0);				
				jdbc.add_project(job);
				jdbc.add_Manager(manager, Id);
				jdbc.add_requiredQuals(Id,selectedQual);//TODO only works w/ one
				layeredPaneManagerWorker.setVisible(true);	
				JOptionPane.showMessageDialog(null, "Job Created!");
				pnlCreateJob.setVisible(false);
				txtJobName.setText("");
				txtAreaJobDescription.setText("");
				
			}
		});
		//gets selected qualification from job and sets up users w/ it
		listRequiredQuals.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                	 selectedQual = listRequiredQuals.getSelectedValuesList();
                	 createAllUsersList(selectedQual);
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
		//listens for selection of a singular qualification in project and returns users
		listQualifications.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					String s = (String) listAssignableManagers.getSelectedValue();
					userList.clear();
					ArrayList<User> users = jdbc.getUsersWithQual(s);
					for (int i = 0; i < users.size(); i++) {
						userList.addElement(String.format("%s, %s", users.get(i).get_lastname(), users.get(i).get_firstname()));
					}
				}
			}
		});
		//listens for selection of a singular job in tasks and saves it
		listSuperJobs.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()) {
					superJobString = (String) listSuperJobs.getSelectedValue();
					System.out.println(superJobString);
				}
			}
		});
		
		listConversations.addListSelectionListener(new ListSelectionListener() {
	        @Override
	        public void valueChanged(ListSelectionEvent arg0) {
	            if (!arg0.getValueIsAdjusting()) {
	            	clearAllManagerPanels();
	            	pnlConversationDetails.setVisible(true);
	            	System.out.println(listConversations.getSelectedValue().toString());
	            	loadConversationMessages(conversationMap.get(listConversations.getSelectedValue().toString()));
	            	lblConversationTitle.setText(listConversations.getSelectedValue().toString());
	            }
	        }
	    });
		
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdbc.sendMessage(txtNewMessage.getText().toString(), currentSessionUserID, currentOpenConversation);
				loadConversationMessages(currentOpenConversation);
			}
		});
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadConversationMessages(currentOpenConversation);
			}
		});
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
		    @Override
		    public void valueChanged(TreeSelectionEvent e) {
		    	if (!treeIsBeingUpdated) {
					DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			        clearAllManagerPanels();
			        displaySelectedNode(selectedNode.getUserObject().toString());
				}
		    }
		});
		//TODO for the project viewer
		btnAssignProjQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = listProjAvailQual.getSelectedValue().toString();
				jdbc.addQualToJob(name, Integer.parseInt(lblJobID.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnUnassignProjQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listProjAssignQual.getSelectedValue().toString();
				jdbc.removeQualFromJob(name, Integer.parseInt(lblJobID.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnAssignEmployeeProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listAvailProjEmp.getSelectedValue().toString();
				jdbc.addUserToJob(name, Integer.parseInt(lblJobID.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnUnassignEmployeeProj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listAssignedProjEmp.getSelectedValue().toString();
				jdbc.removeUserFromJob(name, Integer.parseInt(lblJobID.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnSaveChangesProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				selectedNode.setUserObject(textFieldProjectName.getText().toString());
				jdbc.updateJob(textFieldProjectName.getText().toString(), textProjectDesc.getText().toString(), Integer.parseInt(lblJobID.getText()));
			}
		});
		
		btnAssignJobQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listAvailJobQual.getSelectedValue().toString();
				jdbc.addQualToJob(name, Integer.parseInt(lblJobID2.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnUnassignJobQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listAssignJobQual.getSelectedValue().toString();
				jdbc.removeQualFromJob(name, Integer.parseInt(lblJobID2.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnAssignJobEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listAvailJobEmp.getSelectedValue().toString();
				jdbc.addUserToJob(name, Integer.parseInt(lblJobID2.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnUnassignJobEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = listAssignJobEmp.getSelectedValue().toString();
				jdbc.removeUserFromJob(name, Integer.parseInt(lblJobID2.getText()));
				displaySelectedNode(name);
			}
		});
		
		btnSaveChangesJob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				selectedNode.setUserObject(textFieldJobName.getText().toString());
				jdbc.updateJob(textFieldJobName.getText().toString(), textAreaJobDesc.getText().toString(), Integer.parseInt(lblJobID2.getText()));
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
	
	/*
	 * gets a list of user's qualifications from the database and then loads then into the correct list.
	 */
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
	private void createAllUsersList(List l){
		unassignedUsersList.clear();
		ArrayList<User> users = jdbc.getUsersWithQual(l);
		for (int i = 0; i < users.size(); i++) {
			unassignedUsersList.addElement(String.format("%s, %s", users.get(i).get_lastname(), users.get(i).get_firstname()));
		}
	}
	//pulls all users 
	private void createAllUsersList(){
		unassignedUsersList.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			unassignedUsersList.addElement(String.format("%s, %s", users.get(i).get_lastname(), users.get(i).get_firstname()));
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
	/*Loads all tickets into the openTickets list and the closedTickets list for the user to be able to click on a ticket
	to view its details and either mark it done or not done.*/
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
	
	private void loadJobs(){
		superJobsList.clear();
		superJobs=jdbc.getProjectsAndJobs();
		for(Job j: superJobs){
			superJobsList.addElement(j.jobname);
		}
		
	}	
	/*
	 * Displays all of the details for a ticket once it has been clicked.
	 */
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

	/*
	 * Pulls a list of all projects into the Manager/Worker view
	 */
	private void loadProjects() {
		
		DefaultMutableTreeNode projects = new DefaultMutableTreeNode("Company Projects");

		ArrayList<Job> projectsList = jdbc.getProjectsAndJobs();
		for (Job j : projectsList) {
			if (!j.jobname.equals("") && j.jobtype == 1) {
				DefaultMutableTreeNode job = new DefaultMutableTreeNode(j.jobname);
				projects.add(job);
			}

			
		}
		tree = new JTree(projects);
		
		for (Job j : projectsList) {
			if (!j.jobname.equals("") && j.jobtype == 2 && j.parentID != 0 && j.jobID < 12) {
				DefaultMutableTreeNode parentNode = null;
				DefaultMutableTreeNode job = new DefaultMutableTreeNode(j.jobname);
				System.out.println(j.jobname);
				String parentName;
				for (Job i : projectsList) {
					if (j.parentID == i.jobID && i.jobID < 12) {
						String prefix = i.jobname.substring(0, 1);
						TreePath path = tree.getNextMatch(prefix, 0, Position.Bias.Forward);
						parentNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
						parentNode.add(job);
					}
				}
			}
		}
		tree.setBounds(0, 0, 181, 325);
		pnlProjectTree.add(tree);

	}
	

	
	
	
	private void loadConversations() {
		conversationMap = jdbc.getConversations(currentSessionUserID);
		Object[] names = conversationMap.keySet().toArray();
		
		conversationsList.clear();
		for (int i = 0; i < conversationMap.size(); i++) {
			conversationsList.addElement(names[i].toString());
			
		}
	}
	
	private void loadConversationMessages(int conversationID) {
		ArrayList<String> messages = jdbc.getConversationMessages(conversationID, currentSessionUserID);
		currentOpenConversation = conversationID;
		
		conversationMessagesList.clear();
		
		for (String s : messages) {
			conversationMessagesList.addElement(s);
		}
	}
	private void displaySelectedNode(String nodeName) {
		ArrayList<Job> projectsList = jdbc.getProjectsAndJobs();
		ArrayList<Task> tasksList = jdbc.getTasks();
		
		for (Job j : projectsList) {
			if (j.jobname.equals(nodeName)) {
				//its a project
				if (j.jobtype == 1) {
					displayProjectDetails(j);
				} else {
					//it is a job
					displayJobDetails(j);
				}
				
			}
		}
		
		
	}
	
	private void displayJobDetails(Job j) {
		clearAllManagerPanels();
		pnlJobViewerManager.setVisible(true);
		if (tasksTableInit) {
			dataModel.setRowCount(0);
			
		}
		
		
		
		lblJobID2.setText(String.format("%d", j.jobID));
		
		if (currentUsertype == 3) {
			btnUnassignJobEmp.setVisible(false);
			btnUnassignJobQual.setVisible(false);
			btnAssignJobEmp.setVisible(false);
			btnAssignJobQual.setVisible(false);
			btnSaveChangesJob.setVisible(false);
		}
		
		textFieldJobName.setText(j.jobname);
		textAreaJobDesc.setText(j.jobdesc);
		
		assignedJobEmpList.clear();
		availJobEmpList.clear();
		assignJobQualList.clear();
		availJobQualList.clear();
		
		ArrayList<Qualification> assignedJobQuals = jdbc.getAssignedJobQuals(j.jobID);
		ArrayList<Qualification> availJobQuals = jdbc.getAvailJobQuals(j.jobID);
		
		ArrayList<String> assignedJobUsers = jdbc.getAssignedJobUsers(j.jobID);
		ArrayList<String> availJobUsers = jdbc.getAvailJobUsers(j.jobID);
		
		if (assignedJobQuals.isEmpty()) {
			availJobEmpList.addElement("No Quals Selected");
		}
		
		for (Qualification q : assignedJobQuals) {
			assignJobQualList.addElement(q.getQualName());
		}
		
		for (Qualification q : availJobQuals) {
			availJobQualList.addElement(q.getQualName());
		}
		
		for (String s : assignedJobUsers) {
			assignedJobEmpList.addElement(s);
		}
		
		for (String s : availJobUsers) {
			availJobEmpList.addElement(s);
		}
		
		loadTasks(j);
		

	}
	
	private void loadTasks(Job j) {
		Vector<String> columnNames = new Vector<String>();
	    columnNames.addElement("Task Name");
	    columnNames.addElement("Task Description");
	    columnNames.addElement("Task Reason");
	    
	    Vector<Vector> rowData = new Vector<Vector>();
		    
		
	    ArrayList<Task> tasks = jdbc.getTasks(j.jobID);
		
	    for (Task t : tasks) {
	    	Vector<String> row = new Vector<String>();
		    row.addElement(t.name);
		    row.addElement(t.desc);
		    row.addElement(t.reason);
		    rowData.addElement(row);
		    dataModel.addRow(row);
	    }
	    
	
	    dataModel.setColumnIdentifiers(columnNames);
	    tasksTableInit = true;
	}
	
	private void displayProjectDetails(Job j) {
		lblJobID.setVisible(false);
		listAssignedProjEmp.clearSelection();
		listAvailProjEmp.clearSelection();
		listProjAssignQual.clearSelection();
		listProjAvailQual.clearSelection();
		clearAllManagerPanels();
		pnlProjectViewerManager.setVisible(true);
		
		textFieldProjectName.setText(j.jobname);
		textProjectDesc.setText(j.jobdesc);
		String id = String.format("%d", j.jobID);
		lblJobID.setText(id);
		
		if (currentUsertype == 3) {
			textFieldProjectName.setEnabled(false);
			textProjectDesc.setEnabled(false);
			btnUnassignProjQual.setVisible(false);
			btnAssignProjQual.setVisible(false);
			btnAssignEmployeeProj.setVisible(false);
			btnUnassignEmployeeProj.setVisible(false);
			btnSaveChangesProject.setVisible(false);
		}
		
		assignedProjEmpList.clear();
		availProjEmpList.clear();
		projAssignQualList.clear();
		projAvailQualList.clear();
		
		ArrayList<Qualification> assignedJobQuals = jdbc.getAssignedJobQuals(j.jobID);
		ArrayList<Qualification> availJobQuals = jdbc.getAvailJobQuals(j.jobID);
		
		ArrayList<String> assignedJobUsers = jdbc.getAssignedJobUsers(j.jobID);
		ArrayList<String> availJobUsers = jdbc.getAvailJobUsers(j.jobID);
		
		if (assignedJobQuals.isEmpty()) {
			availProjEmpList.addElement("No Quals Selected");
		}
		
		for (Qualification q : assignedJobQuals) {
			projAssignQualList.addElement(q.getQualName());
		}
		
		for (Qualification q : availJobQuals) {
			projAvailQualList.addElement(q.getQualName());
		}
		
		for (String s : assignedJobUsers) {
			assignedProjEmpList.addElement(s);
		}
		
		for (String s : availJobUsers) {
			availProjEmpList.addElement(s);
		}
		
	}
	
	
	private void clearAllManagerPanels() {
		pnlConversationDetails.setVisible(false);
    	pnlCreateProject.setVisible(false);
    	pnlCreateJob.setVisible(false);
		pnlCreateTask.setVisible(false);
    	pnlProjectViewerManager.setVisible(false);
    	pnlJobViewerManager.setVisible(false);

	}
}

