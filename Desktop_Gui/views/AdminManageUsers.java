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
	private JLayeredPane layeredPane;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField txtJobName;
	private JTextField textTaskName;
	private JTextField txtProjectName;
	

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
		btn_settings.setBounds(756, 5, 89, 23);
		btn_preferences = new JButton("Preferences");
		btn_preferences.setBounds(631, 5, 115, 23);
		
		Panel pnlUsers = new Panel();
		pnlUsers.setBounds(5, 5, 157, 28);
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		
		btn_create_new_user = new JButton("Create New User");
		btn_create_new_user.setBounds(172, 5, 130, 23);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		pnlUsers.add(lblUsers);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 43, 155, 720);
		
		//creates the list of all the users on the left side of the window
		createUserList();
		listUsers = new JList(userList);
		
		scrollPane.setViewportView(listUsers);
		listUsers.setBackground(UIManager.getColor("Button.background"));
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listUsers.setLayoutOrientation(JList.VERTICAL);
		listUsers.setVisibleRowCount(1);
		contentPane.setLayout(null);
		contentPane.add(pnlUsers);
		contentPane.add(scrollPane);
		contentPane.add(btn_create_new_user);
		contentPane.add(btn_preferences);
		contentPane.add(btn_settings);
		
		layeredPane = new JLayeredPane();
		layeredPane.setBounds(168, 23, 756, 740);
		contentPane.add(layeredPane);
		layeredPane.setLayout(null);
		
		pnlCreateUser = new JPanel();
		layeredPane.setLayer(pnlCreateUser, 2);
		pnlCreateUser.setBounds(44, 41, 664, 571);
		layeredPane.add(pnlCreateUser);
		pnlCreateUser.setBackground(UIManager.getColor("Button.background"));
		pnlCreateUser.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(220, 20, 60), null, null, null));
		//hides the create user window until the user click "Create new user" button
		pnlCreateUser.setVisible(false);
		
		lblEnterUserInfo = new JLabel("Enter User Info");
		lblEnterUserInfo.setBounds(314, 21, 155, 25);
		lblEnterUserInfo.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		lblFirstName_1 = new JLabel("First Name:");
		lblFirstName_1.setBounds(116, 64, 79, 17);
		lblFirstName_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		btnCreateUser = new JButton("Create User");
		btnCreateUser.setBounds(144, 496, 200, 29);
		
				btnCreateUser.setFont(new Font("Tahoma", Font.BOLD, 16));
				
				lblUserType = new JLabel("User Type:");
				lblUserType.setBounds(121, 438, 74, 17);
				lblUserType.setFont(new Font("Tahoma", Font.BOLD, 14));
				
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
				pnlCreateUser.setLayout(null);
				pnlCreateUser.add(lblPassword);
				pnlCreateUser.add(lblPhoneNumber_1);
				pnlCreateUser.add(lblEmailAddress_1);
				pnlCreateUser.add(lblUsername_1);
				pnlCreateUser.add(lblLastName_1);
				pnlCreateUser.add(lblUserType);
				pnlCreateUser.add(lblFirstName_1);
				pnlCreateUser.add(rdbtnAdmin);
				pnlCreateUser.add(rdbtnManager);
				pnlCreateUser.add(rdbtnWorker);
				pnlCreateUser.add(btnCreateUser);
				pnlCreateUser.add(lblEnterUserInfo);
				pnlCreateUser.add(txtCreateLastName);
				pnlCreateUser.add(txtCreateFirstName);
				pnlCreateUser.add(txtCreateUsername);
				pnlCreateUser.add(txtCreateEmailAddress);
				pnlCreateUser.add(txtCreatePhoneNumber);
				pnlCreateUser.add(txtCreatePassword);
				
				btnCancel = new JButton("Cancel");

				btnCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
				btnCancel.setBounds(401, 493, 133, 35);
				pnlCreateUser.add(btnCancel);
				
				JPanel pnlCreateProject = new JPanel();
				layeredPane.setLayer(pnlCreateProject, 5);
				pnlCreateProject.setBounds(0, 11, 743, 720);
				layeredPane.add(pnlCreateProject);
				
				lblNewLabel = new JLabel("Project name:");
				
				lblNewLabel_1 = new JLabel("Description:");
				
				JScrollPane scrlPaneProjectDescription = new JScrollPane();
				
				JScrollPane scrlPaneQualifications = new JScrollPane();
				
				JScrollPane scrlPaneUsersAvailable = new JScrollPane();
				
				JScrollPane scrlPaneUsersAdded = new JScrollPane();
				
				JLabel lblNewLabel_2 = new JLabel("Qualifications:");
				
				JLabel lblNewLabel_3 = new JLabel("Users to add:");
				
				JLabel lblNewLabel_4 = new JLabel("Users added:");
				
				JButton btnAssign = new JButton("->");
				
				JButton btnRemove = new JButton("<-");
				btnRemove.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				
				JLabel lblCreateANew = new JLabel("Create a new Project");
				lblCreateANew.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				JButton btnCreateNewProject = new JButton("Create new Project");
				
				txtProjectName = new JTextField();
				txtProjectName.setColumns(10);
				GroupLayout gl_pnlCreateProject = new GroupLayout(pnlCreateProject);
				gl_pnlCreateProject.setHorizontalGroup(
					gl_pnlCreateProject.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlCreateProject.createSequentialGroup()
							.addGap(147)
							.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1))
							.addPreferredGap(ComponentPlacement.RELATED, 182, Short.MAX_VALUE)
							.addGroup(gl_pnlCreateProject.createParallelGroup(Alignment.LEADING)
								.addComponent(scrlPaneProjectDescription, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtProjectName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(142, Short.MAX_VALUE))
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
							.addContainerGap(296, Short.MAX_VALUE)
							.addComponent(lblCreateANew)
							.addGap(284))
						.addGroup(gl_pnlCreateProject.createSequentialGroup()
							.addContainerGap(505, Short.MAX_VALUE)
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
							.addComponent(btnCreateNewProject)
							.addContainerGap(126, Short.MAX_VALUE))
				);
				
				JTextArea textAreaProjectDescription = new JTextArea();
				scrlPaneProjectDescription.setViewportView(textAreaProjectDescription);
				
				JList listUsersAdded = new JList();
				scrlPaneUsersAdded.setViewportView(listUsersAdded);
				
				JList listUsersAvailable = new JList();
				scrlPaneUsersAvailable.setViewportView(listUsersAvailable);
				
				JList listQualifications = new JList();
				scrlPaneQualifications.setViewportView(listQualifications);
				pnlCreateProject.setLayout(gl_pnlCreateProject);
				
				JPanel pnlCreateTask = new JPanel();
				layeredPane.setLayer(pnlCreateTask, 2);
				pnlCreateTask.setBounds(0, 11, 743, 720);
				layeredPane.add(pnlCreateTask);
				
				JLabel lblCreateTask = new JLabel("Create a new Task");
				lblCreateTask.setFont(new Font("Tahoma", Font.PLAIN, 18));
				
				JLabel lblTaskName = new JLabel("Name of the Task:");
				
				JLabel lblTaskDescription = new JLabel("Description of the Task:");
				
				JLabel lblTaskReason = new JLabel("Reason why it should be added:");
				
				JScrollPane scrlPaneDescription = new JScrollPane();
				
				JScrollPane scrlPaneReason = new JScrollPane();
				
				JButton btnCreateNewTask = new JButton("Create new Task");
				
				textTaskName = new JTextField();
				textTaskName.setColumns(10);
				GroupLayout gl_pnlCreateTask = new GroupLayout(pnlCreateTask);
				gl_pnlCreateTask.setHorizontalGroup(
					gl_pnlCreateTask.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_pnlCreateTask.createSequentialGroup()
							.addGap(97)
							.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblTaskName)
								.addComponent(lblTaskDescription)
								.addComponent(lblTaskReason))
							.addPreferredGap(ComponentPlacement.RELATED, 113, Short.MAX_VALUE)
							.addGroup(gl_pnlCreateTask.createParallelGroup(Alignment.LEADING)
								.addComponent(scrlPaneReason, GroupLayout.PREFERRED_SIZE, 215, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrlPaneDescription, GroupLayout.PREFERRED_SIZE, 216, GroupLayout.PREFERRED_SIZE)
								.addComponent(textTaskName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addContainerGap(163, Short.MAX_VALUE))
						.addGroup(gl_pnlCreateTask.createSequentialGroup()
							.addContainerGap(505, Short.MAX_VALUE)
							.addComponent(btnCreateNewTask)
							.addGap(125))
						.addGroup(gl_pnlCreateTask.createSequentialGroup()
							.addContainerGap(298, Short.MAX_VALUE)
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
							.addComponent(btnCreateNewTask)
							.addContainerGap(99, Short.MAX_VALUE))
				);
				
				JTextArea txtAreaReason = new JTextArea();
				scrlPaneReason.setViewportView(txtAreaReason);
				
				JTextArea txtAreaDescription = new JTextArea();
				scrlPaneDescription.setViewportView(txtAreaDescription);
				pnlCreateTask.setLayout(gl_pnlCreateTask);
				
				pnlUserEditInfo = new JPanel();
				layeredPane.setLayer(pnlUserEditInfo, 3);
				pnlUserEditInfo.setBounds(0, 11, 743, 720);
				layeredPane.add(pnlUserEditInfo);
				pnlUserEditInfo.setBorder(new TitledBorder(null, "User Edit/Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				JLabel lblFullName = new JLabel("Full Name");
				lblFullName.setBounds(264, 16, 127, 45);
				lblFullName.setFont(new Font("Tahoma", Font.BOLD, 20));
				
				JLabel lblFirstName = new JLabel("First Name:");
				lblFirstName.setBounds(85, 89, 79, 17);
				lblFirstName.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblLastName = new JLabel("Last Name:");
				lblLastName.setBounds(86, 132, 78, 17);
				lblLastName.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setBounds(90, 180, 74, 17);
				lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblEmailAddress = new JLabel("Email Address:");
				lblEmailAddress.setBounds(63, 230, 101, 17);
				lblEmailAddress.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				JLabel lblPhoneNumber = new JLabel("Phone Number:");
				lblPhoneNumber.setBounds(55, 284, 109, 17);
				lblPhoneNumber.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				textFirstName = new JTextField();
				textFirstName.setBounds(214, 89, 330, 20);
				textFirstName.setColumns(10);
				textLastName = new JTextField();
				textLastName.setBounds(214, 132, 330, 20);
				textLastName.setColumns(10);
				textUsername = new JTextField();
				textUsername.setBounds(214, 177, 330, 20);
				textUsername.setColumns(10);
				textEmail = new JTextField();
				textEmail.setBounds(214, 227, 330, 20);
				textEmail.setColumns(10);
				textPhone = new JTextField();
				textPhone.setBounds(214, 281, 330, 20);
				textPhone.setColumns(10);
				
				btnSaveChanges = new JButton("Save Changes");
				btnSaveChanges.setBounds(164, 329, 127, 34);
				
				btnSaveChanges.setToolTipText("Save Changes to Database");
				
				JSeparator separator = new JSeparator();
				separator.setBounds(22, 374, 699, 2);
				separator.setBackground(Color.BLACK);
				separator.setForeground(Color.BLACK);
				
				JScrollPane scrlPaneAvailableQuals = new JScrollPane();
				scrlPaneAvailableQuals.setBounds(22, 436, 174, 271);
				JScrollPane scrlPaneAssignedQuals = new JScrollPane();
				scrlPaneAssignedQuals.setBounds(275, 436, 174, 271);
				
				JLabel lblAvailable = new JLabel("Available");
				lblAvailable.setBounds(67, 408, 60, 17);
				lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 14));
				JLabel lblAssigned = new JLabel("Assigned");
				lblAssigned.setBounds(318, 408, 86, 17);
				lblAssigned.setFont(new Font("Tahoma", Font.BOLD, 14));
				
				unassignQual = new JButton("<-");
				unassignQual.setBounds(206, 579, 64, 29);
				
						unassignQual.setToolTipText("Click to remove assigned Qualifications");
						unassignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
						
						assignQual = new JButton("->");
						assignQual.setBounds(206, 521, 64, 29);
						
								assignQual.setToolTipText("Click to move selected Qualifications to Assigned");
								assignQual.setFont(new Font("Tahoma", Font.BOLD, 16));
								
								
								
								listAssignedQuals = new JList(assignedQualList);
								scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
								
								listAvailableQuals = new JList(availableQualList);
								scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
								pnlUserEditInfo.setLayout(null);
								pnlUserEditInfo.add(btnSaveChanges);
								pnlUserEditInfo.add(scrlPaneAvailableQuals);
								pnlUserEditInfo.add(unassignQual);
								pnlUserEditInfo.add(assignQual);
								pnlUserEditInfo.add(scrlPaneAssignedQuals);
								pnlUserEditInfo.add(separator);
								pnlUserEditInfo.add(lblPhoneNumber);
								pnlUserEditInfo.add(lblEmailAddress);
								pnlUserEditInfo.add(lblUsername);
								pnlUserEditInfo.add(lblLastName);
								pnlUserEditInfo.add(lblFirstName);
								pnlUserEditInfo.add(textFirstName);
								pnlUserEditInfo.add(textPhone);
								pnlUserEditInfo.add(textLastName);
								pnlUserEditInfo.add(textUsername);
								pnlUserEditInfo.add(textEmail);
								pnlUserEditInfo.add(lblAvailable);
								pnlUserEditInfo.add(lblAssigned);
								pnlUserEditInfo.add(lblFullName);
								
								btnChangePassword = new JButton("Change Password");
								
										btnChangePassword.setBounds(328, 329, 142, 34);
										pnlUserEditInfo.add(btnChangePassword);
										
										pnlDeleteUser = new JPanel();
										pnlDeleteUser.setBorder(new TitledBorder(null, "WARNING AREA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
										pnlDeleteUser.setBackground(new Color(245, 222, 179));
										pnlDeleteUser.setBounds(531, 618, 166, 89);
										pnlUserEditInfo.add(pnlDeleteUser);
										pnlDeleteUser.setLayout(null);
										
										btnDeleteUser = new JButton("DELETE \r\nUSER");

										btnDeleteUser.setFont(new Font("Tahoma", Font.BOLD, 10));
										btnDeleteUser.setBounds(24, 27, 120, 39);
										pnlDeleteUser.add(btnDeleteUser);
										
										JPanel pnlCreateJob = new JPanel();
										layeredPane.setLayer(pnlCreateJob, 2);
										pnlCreateJob.setBounds(0, 11, 743, 720);
										layeredPane.add(pnlCreateJob);
										
										JLabel lblCreateNewJob = new JLabel("Create new Job");
										lblCreateNewJob.setFont(new Font("Tahoma", Font.PLAIN, 18));
										
										JLabel lblNewLabel_9 = new JLabel("Description:");
										
										JLabel lblNewLabel_10 = new JLabel("Assigned Manager:");
										
										JScrollPane scrlPaneJobDescription = new JScrollPane();
										
										JScrollPane scrlPaneAssignableManagers = new JScrollPane();
										
										JScrollPane scrlPaneRequiredQuals = new JScrollPane();
										
										JScrollPane scrlPaneAvailableUsers = new JScrollPane();
										
										JScrollPane scrlPaneAssignedUsers = new JScrollPane();
										SpringLayout sl_pnlCreateJob = new SpringLayout();
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, scrlPaneAssignedUsers, 0, SpringLayout.NORTH, scrlPaneAvailableUsers);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, scrlPaneAssignedUsers, -46, SpringLayout.EAST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.SOUTH, scrlPaneAvailableUsers, -55, SpringLayout.SOUTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, scrlPaneAvailableUsers, -251, SpringLayout.EAST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, scrlPaneRequiredQuals, -200, SpringLayout.EAST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.SOUTH, scrlPaneAssignableManagers, -370, SpringLayout.SOUTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.SOUTH, scrlPaneRequiredQuals, -226, SpringLayout.SOUTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, scrlPaneRequiredQuals, 6, SpringLayout.SOUTH, scrlPaneAssignableManagers);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, scrlPaneAssignableManagers, 241, SpringLayout.NORTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, scrlPaneJobDescription, 166, SpringLayout.NORTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.SOUTH, scrlPaneJobDescription, -6, SpringLayout.NORTH, scrlPaneAssignableManagers);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblNewLabel_10, 0, SpringLayout.NORTH, scrlPaneAssignableManagers);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, lblNewLabel_10, 0, SpringLayout.EAST, lblNewLabel_9);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, scrlPaneAssignableManagers, 0, SpringLayout.WEST, scrlPaneRequiredQuals);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, scrlPaneAssignableManagers, -200, SpringLayout.EAST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, scrlPaneJobDescription, 395, SpringLayout.WEST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, scrlPaneJobDescription, -124, SpringLayout.EAST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblNewLabel_9, 166, SpringLayout.NORTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, lblNewLabel_9, 176, SpringLayout.WEST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblCreateNewJob, 22, SpringLayout.NORTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, lblCreateNewJob, 302, SpringLayout.WEST, pnlCreateJob);
										pnlCreateJob.setLayout(sl_pnlCreateJob);
										pnlCreateJob.add(lblCreateNewJob);
										pnlCreateJob.add(lblNewLabel_10);
										pnlCreateJob.add(lblNewLabel_9);
										pnlCreateJob.add(scrlPaneAvailableUsers);
										pnlCreateJob.add(scrlPaneRequiredQuals);
										pnlCreateJob.add(scrlPaneAssignedUsers);
										
										JList listAssignedUsers = new JList();
										scrlPaneAssignedUsers.setViewportView(listAssignedUsers);
										pnlCreateJob.add(scrlPaneJobDescription);
										
										JTextArea txtAreaJobDescription = new JTextArea();
										scrlPaneJobDescription.setViewportView(txtAreaJobDescription);
										pnlCreateJob.add(scrlPaneAssignableManagers);
										
										JList listAssignableManagers = new JList();
										scrlPaneAssignableManagers.setViewportView(listAssignableManagers);
										
										JLabel lblJobName = new JLabel("Name of the Job:");
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblJobName, 107, SpringLayout.NORTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, lblJobName, 0, SpringLayout.EAST, lblNewLabel_10);
										pnlCreateJob.add(lblJobName);
										
										JLabel lblRequiredQualifications = new JLabel("Required Qualifications:");
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, scrlPaneRequiredQuals, 162, SpringLayout.EAST, lblRequiredQualifications);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblRequiredQualifications, 0, SpringLayout.NORTH, scrlPaneRequiredQuals);
										
										JList listRequiredQuals = new JList();
										scrlPaneRequiredQuals.setViewportView(listRequiredQuals);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, lblRequiredQualifications, 0, SpringLayout.EAST, lblNewLabel_10);
										pnlCreateJob.add(lblRequiredQualifications);
										
										JLabel lblUsersList = new JLabel("User List:");
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblUsersList, 2, SpringLayout.NORTH, scrlPaneAvailableUsers);
										sl_pnlCreateJob.putConstraint(SpringLayout.EAST, lblUsersList, 0, SpringLayout.EAST, lblNewLabel_10);
										
										JList listAvailableUsers = new JList();
										scrlPaneAvailableUsers.setViewportView(listAvailableUsers);
										pnlCreateJob.add(lblUsersList);
										
										JButton buttonAssignUsers = new JButton("->");
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, scrlPaneAssignedUsers, 6, SpringLayout.EAST, buttonAssignUsers);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, buttonAssignUsers, 6, SpringLayout.EAST, scrlPaneAvailableUsers);
										buttonAssignUsers.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent arg0) {
											}
										});
										pnlCreateJob.add(buttonAssignUsers);
										
										JButton buttonRemoveUsers = new JButton("<-");
										sl_pnlCreateJob.putConstraint(SpringLayout.SOUTH, buttonAssignUsers, -26, SpringLayout.NORTH, buttonRemoveUsers);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, buttonRemoveUsers, 603, SpringLayout.NORTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, buttonRemoveUsers, 6, SpringLayout.EAST, scrlPaneAvailableUsers);
										pnlCreateJob.add(buttonRemoveUsers);
										
										txtJobName = new JTextField();
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, txtJobName, 0, SpringLayout.NORTH, lblJobName);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, txtJobName, 0, SpringLayout.WEST, scrlPaneRequiredQuals);
										pnlCreateJob.add(txtJobName);
										txtJobName.setColumns(10);
										
										JLabel lblAvailableUsers_1 = new JLabel("Available Users");
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, lblAvailableUsers_1, 344, SpringLayout.WEST, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, scrlPaneAvailableUsers, 6, SpringLayout.SOUTH, lblAvailableUsers_1);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, scrlPaneAvailableUsers, 0, SpringLayout.WEST, lblAvailableUsers_1);
										pnlCreateJob.add(lblAvailableUsers_1);
										
										JLabel lblAssignedUsers = new JLabel("Assigned Users");
										sl_pnlCreateJob.putConstraint(SpringLayout.SOUTH, lblAssignedUsers, -199, SpringLayout.SOUTH, pnlCreateJob);
										sl_pnlCreateJob.putConstraint(SpringLayout.WEST, lblAssignedUsers, 133, SpringLayout.EAST, lblAvailableUsers_1);
										sl_pnlCreateJob.putConstraint(SpringLayout.NORTH, lblAvailableUsers_1, 0, SpringLayout.NORTH, lblAssignedUsers);
										pnlCreateJob.add(lblAssignedUsers);
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

		//moves the create user panel to the front to allow the user to enter the new user info
		btn_create_new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateUser.setVisible(true);
				layeredPane.setLayer(pnlUserEditInfo, 2);
				layeredPane.setLayer(pnlCreateUser, 3);
				
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
