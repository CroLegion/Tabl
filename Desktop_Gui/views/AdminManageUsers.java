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
		layeredPane.setBounds(168, 18, 756, 758);
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
				
				pnlUserEditInfo = new JPanel();
				layeredPane.setLayer(pnlUserEditInfo, 3);
				pnlUserEditInfo.setBounds(0, 11, 743, 733);
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
