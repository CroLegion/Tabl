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
import java.awt.Component;

public class AdminManageUsers extends JFrame {

	private JPanel contentPane;
	private JButton btn_create_new_user;
	private JButton btn_add_qualifications;
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
	DefaultListModel userListAssignQual = new DefaultListModel();
	DefaultListModel userListAvailQual = new DefaultListModel();
	

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
	private JLabel lblTitle;
	private JTextField txtNewQualificationName;
	private JButton btnCreateQual;
	private JTextArea txtNewQualificationDesc;
	private JList listCreateQualAvailUsers;
	private JList listCreateQualAssignedUsers;
	private JButton btnAssignUserQual;
	private JButton btnUnassignUserQual;
	private JPanel pnlCreateQualification;
	private JLayeredPane layeredPaneAdmin;
	private JLayeredPane layeredPaneLogin;
	private JLayeredPane layeredPaneManagerWorker;
	private JPanel pnlAdmin;
	private JLayeredPane layeredPaneAdminComponents;
	private JPanel pnlLogin;
	private JLayeredPane layeredPaneLoginComponents;
	private JLabel lblTablLogin;
	private JPanel pnlManagerWorker;
	private JLayeredPane layeredPaneManagerWorkerComponents;
	private JLayeredPane layeredPane;
	private JLabel lblUsername_2;
	private JLabel lblPassword_1;
	private JButton btnLogin;
	private JTextField txtLoginUser;
	private JPasswordField passwordLogin;
	private JPanel panel;
	private JButton btnCancelAddQualifcation;
	private JButton btnLogout;
	private JLayeredPane layeredPane_1;
	private JLabel lblPortal;
	private JTextArea textAreaProjectDescription;
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
		layeredPane.setLayer(layeredPaneAdmin, 0);
		layeredPaneAdmin.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneAdmin);
		
		pnlAdmin = new JPanel();
		layeredPaneAdmin.setLayer(pnlAdmin, 0);
		pnlAdmin.setBounds(0, 0, 947, 760);
		layeredPaneAdmin.add(pnlAdmin);
		pnlAdmin.setLayout(null);
		
		layeredPaneAdminComponents = new JLayeredPane();
		layeredPaneAdminComponents.setBounds(0, 0, 937, 760);
		pnlAdmin.add(layeredPaneAdminComponents);
		
		Panel pnlUsers = new Panel();
		pnlUsers.setEnabled(false);
		pnlUsers.setBounds(0, 0, 157, 28);
		layeredPaneAdminComponents.add(pnlUsers);
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		pnlUsers.setLayout(null);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		lblUsers.setBounds(58, 5, 40, 16);
		pnlUsers.add(lblUsers);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		
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
		
		pnlCreateQualification = new JPanel();
		pnlCreateQualification.setBounds(180, 38, 746, 720);
		layeredPaneAdminComponents.add(pnlCreateQualification);
		pnlCreateQualification.setVisible(false);
		pnlCreateQualification.setLayout(null);
		
		JLabel lblCreateQualification = new JLabel("Create New Qualification");
		lblCreateQualification.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCreateQualification.setBounds(246, 11, 262, 51);
		pnlCreateQualification.add(lblCreateQualification);
		
		lblTitle = new JLabel("Title:");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitle.setBounds(111, 131, 56, 20);
		pnlCreateQualification.add(lblTitle);
		
		txtNewQualificationName = new JTextField();
		txtNewQualificationName.setBounds(240, 133, 154, 20);
		pnlCreateQualification.add(txtNewQualificationName);
		txtNewQualificationName.setColumns(10);
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDescription.setBounds(61, 193, 120, 14);
		pnlCreateQualification.add(lblDescription);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(240, 194, 387, 79);
		pnlCreateQualification.add(scrollPane_1);
		
		txtNewQualificationDesc = new JTextArea();
		scrollPane_1.setViewportView(txtNewQualificationDesc);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(80, 339, 154, 287);
		pnlCreateQualification.add(scrollPane_2);
		listCreateQualAvailUsers = new JList(userListAvailQual);
		scrollPane_2.setViewportView(listCreateQualAvailUsers);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(406, 339, 154, 287);
		pnlCreateQualification.add(scrollPane_3);
		
		listCreateQualAssignedUsers = new JList(userListAssignQual);
		scrollPane_3.setViewportView(listCreateQualAssignedUsers);
		
		JLabel lblAssignToUsers = new JLabel("Assign to Users");
		lblAssignToUsers.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAssignToUsers.setBounds(247, 298, 147, 14);
		pnlCreateQualification.add(lblAssignToUsers);
		
		btnAssignUserQual = new JButton("->");
		
				btnAssignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnAssignUserQual.setBounds(271, 445, 89, 23);
				pnlCreateQualification.add(btnAssignUserQual);
				
				btnUnassignUserQual = new JButton("<-");
				
						btnUnassignUserQual.setFont(new Font("Tahoma", Font.BOLD, 16));
						btnUnassignUserQual.setBounds(271, 519, 89, 23);
						pnlCreateQualification.add(btnUnassignUserQual);
						
						JLabel lblAvailable_1 = new JLabel("Available");
						lblAvailable_1.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblAvailable_1.setBounds(121, 314, 60, 14);
						pnlCreateQualification.add(lblAvailable_1);
						
						JLabel lblAssigned_1 = new JLabel("Assigned");
						lblAssigned_1.setFont(new Font("Tahoma", Font.BOLD, 11));
						lblAssigned_1.setBounds(462, 314, 67, 14);
						pnlCreateQualification.add(lblAssigned_1);
						
						btnCreateQual = new JButton("CREATE");
						
								btnCreateQual.setFont(new Font("Tahoma", Font.BOLD, 16));
								btnCreateQual.setBounds(271, 668, 123, 41);
								pnlCreateQualification.add(btnCreateQual);
								
								btnCancelAddQualifcation = new JButton("Cancel");

								btnCancelAddQualifcation.setFont(new Font("Tahoma", Font.BOLD, 16));
								btnCancelAddQualifcation.setBounds(400, 668, 100, 30);
								pnlCreateQualification.add(btnCancelAddQualifcation);
								
								pnlCreateUser = new JPanel();
								layeredPaneAdminComponents.setLayer(pnlCreateUser, 5);
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
										//create task end	
										//edit user info start				
												pnlUserEditInfo = new JPanel();
												pnlUserEditInfo.setBounds(180, 38, 746, 720);
												layeredPaneAdminComponents.add(pnlUserEditInfo);
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
		pnlLogin.add(layeredPaneLoginComponents);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Login", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(134, 109, 639, 354);
		layeredPaneLoginComponents.add(panel);
		panel.setLayout(null);
		
		btnLogin = new JButton("Login");
		btnLogin.setBounds(308, 320, 89, 23);
		panel.add(btnLogin);
		
				btnLogin.setFont(new Font("Tahoma", Font.BOLD, 11));
				
				passwordLogin = new JPasswordField();
				passwordLogin.setBounds(306, 237, 192, 19);
				panel.add(passwordLogin);
				
				lblPassword_1 = new JLabel("Password:");
				lblPassword_1.setBounds(136, 237, 105, 14);
				panel.add(lblPassword_1);
				lblPassword_1.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblUsername_2 = new JLabel("Username:");
				lblUsername_2.setBounds(136, 153, 105, 14);
				panel.add(lblUsername_2);
				lblUsername_2.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				txtLoginUser = new JTextField();
				txtLoginUser.setBounds(306, 152, 192, 20);
				panel.add(txtLoginUser);
				txtLoginUser.setColumns(10);
				
				lblTablLogin = new JLabel("TABL LOGIN");
				lblTablLogin.setBounds(196, 32, 274, 60);
				panel.add(lblTablLogin);
				lblTablLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		layeredPaneManagerWorker = new JLayeredPane();
		layeredPane.setLayer(layeredPaneManagerWorker, 1);
		layeredPaneManagerWorker.setBounds(0, 0, 941, 760);
		layeredPane.add(layeredPaneManagerWorker);
		
		pnlManagerWorker = new JPanel();
		layeredPaneManagerWorker.setLayer(pnlManagerWorker, 10);
		pnlManagerWorker.setBounds(0, 0, 941, 760);
		layeredPaneManagerWorker.add(pnlManagerWorker);
		pnlManagerWorker.setLayout(null);
		
		btn_create_project = new JButton("Create Project");
		btn_create_project.setBounds(316, 0, 136, 28);
		pnlManagerWorker.add(btn_create_project);
		
		btn_create_task = new JButton("Create Task");
		btn_create_task.setBounds(450, 0, 115, 28);
		pnlManagerWorker.add(btn_create_task);
		
		btn_create_job = new JButton("Create Job");
		btn_create_job.setBounds(568, 0, 97, 28);
		pnlManagerWorker.add(btn_create_job);
		
		layeredPaneManagerWorkerComponents = new JLayeredPane();
		layeredPaneManagerWorkerComponents.setBounds(181, 41, 746, 719);
		pnlManagerWorker.add(layeredPaneManagerWorkerComponents);
												//create project end
												//Create task start			
														pnlCreateTask = new JPanel();
														layeredPaneManagerWorkerComponents.setLayer(pnlCreateTask, 0);
														pnlCreateTask.setBounds(0, 0, 746, 720);
														layeredPaneManagerWorkerComponents.add(pnlCreateTask);
														pnlCreateTask.setVisible(false);
														
														JLabel lblCreateTask = new JLabel("Create a new Task");
														lblCreateTask.setBounds(301, 47, 146, 22);
														lblCreateTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
														
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
														
														JTextArea txtAreaReason = new JTextArea();
														scrlPaneReason.setViewportView(txtAreaReason);
														
														JTextArea txtAreaDescription = new JTextArea();
														scrlPaneDescription.setViewportView(txtAreaDescription);
														pnlCreateTask.setLayout(null);
														pnlCreateTask.add(lblTaskName);
														pnlCreateTask.add(lblTaskDescription);
														pnlCreateTask.add(lblTaskReason);
														pnlCreateTask.add(scrlPaneReason);
														pnlCreateTask.add(scrlPaneDescription);
														pnlCreateTask.add(textTaskName);
														pnlCreateTask.add(btnCancelTask);
														pnlCreateTask.add(btnCreateNewTask);
														pnlCreateTask.add(lblCreateTask);
										//create user end				
										//create project start		
												pnlCreateProject = new JPanel();
												layeredPaneManagerWorkerComponents.setLayer(pnlCreateProject, 20);
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
												
												JScrollPane scrlPaneUsersAdded = new JScrollPane();
												scrlPaneUsersAdded.setBounds(532, 354, 154, 164);
												
												JLabel lblNewLabel_2 = new JLabel("Qualifications:");
												lblNewLabel_2.setBounds(111, 322, 96, 14);
												
												JLabel lblNewLabel_3 = new JLabel("Users to add:");
												lblNewLabel_3.setBounds(283, 322, 76, 14);
												
												JLabel lblNewLabel_4 = new JLabel("Users added:");
												lblNewLabel_4.setBounds(532, 322, 86, 14);
												
												btnAssign = new JButton("->");
												btnAssign.setBounds(455, 389, 45, 23);
												btnRemove = new JButton("<-");
												btnRemove.setBounds(455, 444, 45, 23);
												
												JLabel lblCreateANew = new JLabel("Create a new Project");
												lblCreateANew.setBounds(299, 29, 163, 22);
												lblCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
												
												btnCreateNewProject = new JButton("Create new Project");
												btnCreateNewProject.setBounds(508, 571, 154, 23);
												
												txtProjectName = new JTextField();
												txtProjectName.setBounds(397, 119, 86, 20);
												txtProjectName.setColumns(10);
												
												btnCancelProject = new JButton("Cancel");
												btnCancelProject.setBounds(380, 571, 82, 23);
												
												textAreaProjectDescription = new JTextArea();
												scrlPaneProjectDescription.setViewportView(textAreaProjectDescription);
												
												listUsersAdded = new JList();
												scrlPaneUsersAdded.setViewportView(listUsersAdded);
												
												listUsersAvailable = new JList();
												scrlPaneUsersAvailable.setViewportView(listUsersAvailable);
												
												listQualifications = new JList(listedQualList);
												listQualifications.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
												scrlPaneQualifications.setViewportView(listQualifications);
												pnlCreateProject.setLayout(null);
												pnlCreateProject.add(lblNewLabel);
												pnlCreateProject.add(lblNewLabel_1);
												pnlCreateProject.add(scrlPaneProjectDescription);
												pnlCreateProject.add(txtProjectName);
												pnlCreateProject.add(scrlPaneQualifications);
												pnlCreateProject.add(lblNewLabel_2);
												pnlCreateProject.add(scrlPaneUsersAvailable);
												pnlCreateProject.add(btnRemove);
												pnlCreateProject.add(btnAssign);
												pnlCreateProject.add(lblNewLabel_3);
												pnlCreateProject.add(lblNewLabel_4);
												pnlCreateProject.add(scrlPaneUsersAdded);
												pnlCreateProject.add(lblCreateANew);
												pnlCreateProject.add(btnCancelProject);
												pnlCreateProject.add(btnCreateNewProject);
												//edit user info end
												//create job start				
														pnlCreateJob = new JPanel();
														layeredPaneManagerWorkerComponents.setLayer(pnlCreateJob, 1);
														pnlCreateJob.setBounds(0, 0, 746, 720);
														layeredPaneManagerWorkerComponents.add(pnlCreateJob);
														pnlCreateJob.setVisible(false);
														
														JLabel lblCreateNewJob = new JLabel("Create new Job");
														lblCreateNewJob.setBounds(302, 22, 122, 22);
														lblCreateNewJob.setFont(new Font("Tahoma", Font.PLAIN, 18));
														
														JLabel lblNewLabel_9 = new JLabel("Description:");
														lblNewLabel_9.setBounds(176, 166, 88, 14);
														
														JLabel lblNewLabel_10 = new JLabel("Assigned Manager:");
														lblNewLabel_10.setBounds(141, 241, 123, 14);
														
														JScrollPane scrlPaneJobDescription = new JScrollPane();
														scrlPaneJobDescription.setBounds(395, 166, 224, 69);
														
														JScrollPane scrlPaneAssignableManagers = new JScrollPane();
														scrlPaneAssignableManagers.setBounds(395, 241, 148, 109);
														
														JScrollPane scrlPaneRequiredQuals = new JScrollPane();
														scrlPaneRequiredQuals.setBounds(395, 356, 148, 138);
														
														JScrollPane scrlPaneAvailableUsers = new JScrollPane();
														scrlPaneAvailableUsers.setBounds(344, 527, 148, 138);
														
														JScrollPane scrlPaneAssignedUsers = new JScrollPane();
														scrlPaneAssignedUsers.setBounds(549, 527, 148, 130);
														
														listAssignedUsers = new JList();
														scrlPaneAssignedUsers.setViewportView(listAssignedUsers);
														
														JTextArea txtAreaJobDescription = new JTextArea();
														scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
														
														JList listAssignableManagers = new JList();
														scrlPaneAssignableManagers.setViewportView(listAssignableManagers);
														
														JLabel lblJobName = new JLabel("Name of the Job:");
														lblJobName.setBounds(150, 107, 114, 14);
														
														JLabel lblRequiredQualifications = new JLabel("Required Qualifications:");
														lblRequiredQualifications.setBounds(119, 356, 145, 14);
														
														JList listRequiredQuals = new JList();
														scrlPaneRequiredQuals.setViewportView(listRequiredQuals);
														
														JLabel lblUsersList = new JLabel("User List:");
														lblUsersList.setBounds(188, 529, 76, 14);
														
														listAvailableUsers = new JList(userList);
														scrlPaneAvailableUsers.setViewportView(listAvailableUsers);
														
														buttonAssignUsers = new JButton("->");
														buttonAssignUsers.setBounds(498, 554, 45, 23);
														
														buttonRemoveUsers = new JButton("<-");
														buttonRemoveUsers.setBounds(498, 603, 45, 23);
														
														txtJobName = new JTextField();
														txtJobName.setBounds(395, 107, 86, 20);
														txtJobName.setColumns(10);
														
														JLabel lblAvailableUsers_1 = new JLabel("Available Users");
														lblAvailableUsers_1.setBounds(344, 507, 100, 14);
														
														JLabel lblAssignedUsers = new JLabel("Assigned Users");
														lblAssignedUsers.setBounds(550, 507, 94, 14);
														
														btnCreateJob = new JButton("Create Job");
														btnCreateJob.setBounds(594, 683, 103, 23);
														
														btnCancelJob  = new JButton("Cancel");
														btnCancelJob.setBounds(498, 683, 86, 23);
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
				pnlCreateQualification.setVisible(false);
				pnlCreateUser.setVisible(true);
				layeredPaneAdminComponents.setLayer(pnlUserEditInfo, 2);
				layeredPaneAdminComponents.setLayer(pnlCreateUser, 3);
				
			}
		});
		
		btn_add_qualifications.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateJob.setVisible(false);
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
		//creates a new project TODO
		btnCreateNewProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int Id= jdbc.getMaxJobID()+1;
				Job job =new Job(Id, txtProjectName.getText(), 0);
				job.setJobdesc(textAreaProjectDescription.getText());
				ArrayList<Job> j = new ArrayList<Job>();
				j.add(job);
					
				
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
