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
	private JList listAvailableQuals;
	private JList listAssignedQuals;
	DefaultListModel availableQualList = new DefaultListModel();
	DefaultListModel assignedQualList = new DefaultListModel();
	private JButton assignQual;
	private JButton unassignQual;
	ArrayList<Qualification> assignedQuals = new ArrayList<Qualification>();
	ArrayList<Qualification> availQuals = new ArrayList<Qualification>();
	private JPanel pnlCreateUser;
	private JLabel lblEnterUserInfo;
	private JLabel lblFirstName_1;
	private JButton btnCreateUser;
	private JLabel lblUserType;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnAdmin;
	private JRadioButton rdbtnManager;
	private JRadioButton rdbtnWorker;
	private JTextField txtCreateFirstName;
	private JTextField txtCreateLastName;
	private JTextField txtCreateUsername;
	private JTextField txtCreateEmailAddress;
	private JTextField txtCreatePhoneNumber;
	private JPasswordField txtCreatePassword;
	

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

		jdbc.openSQLConnection();
		initComponents();
		createEvents();
		
	}

	//This method contains all of the code for creating and intializing components.
	private void initComponents() {
		setBackground(Color.RED);
		setTitle("TABL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 815);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		contentPane.setVisible(true);
		
		pnlCreateUser = new JPanel();
		pnlCreateUser.setVisible(false);
		
		btn_settings = new JButton("Settings");
		btn_preferences = new JButton("Preferences");
		
		Panel pnlUsers = new Panel();
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		
		btn_create_new_user = new JButton("Create New User");
		
		JPanel pnlUserEditInfo = new JPanel();
		pnlUserEditInfo.setBorder(new TitledBorder(null, "User Edit/Info", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		pnlUsers.add(lblUsers);
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlUsers, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(btn_create_new_user, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(329)
							.addComponent(btn_preferences, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btn_settings, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addComponent(pnlUserEditInfo, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE))
					.addGap(4))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(pnlUserEditInfo, GroupLayout.PREFERRED_SIZE, 733, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(pnlUsers, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(btn_preferences)
										.addComponent(btn_settings))
									.addComponent(btn_create_new_user)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
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
		
		
		
		GroupLayout gl_pnlUserEditInfo = new GroupLayout(pnlUserEditInfo);
		gl_pnlUserEditInfo.setHorizontalGroup(
			gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addContainerGap()
					.addComponent(pnlCreateUser, GroupLayout.PREFERRED_SIZE, 722, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblLastName)
								.addComponent(lblUsername)
								.addComponent(lblEmailAddress)
								.addComponent(lblPhoneNumber))
							.addGap(80)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addComponent(scrlPaneAvailableQuals, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(unassignQual, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addComponent(assignQual, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addComponent(scrlPaneAssignedQuals, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSaveChanges))
					.addContainerGap(731, Short.MAX_VALUE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(179)
					.addComponent(lblAvailable)
					.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
					.addComponent(lblAssigned, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(202))
		);
		gl_pnlUserEditInfo.setVerticalGroup(
			gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
									.addComponent(lblFirstName)
									.addGap(29)
									.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblLastName)
										.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(30)
									.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblUsername)
										.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(31)
									.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblEmailAddress)
										.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(33)
									.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPhoneNumber)
										.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(26)
							.addComponent(btnSaveChanges)
							.addGap(27)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
									.addGap(13)
									.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAvailable)
										.addComponent(lblAssigned, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE))
									.addGap(18)
									.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrlPaneAvailableQuals, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
										.addComponent(scrlPaneAssignedQuals, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
									.addGap(127)
									.addComponent(assignQual, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(unassignQual, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))))
						.addComponent(pnlCreateUser, GroupLayout.PREFERRED_SIZE, 703, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		
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
		GroupLayout gl_pnlCreateUser = new GroupLayout(pnlCreateUser);
		gl_pnlCreateUser.setHorizontalGroup(
			gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateUser.createSequentialGroup()
					.addGap(91)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPassword)
						.addComponent(lblPhoneNumber_1)
						.addComponent(lblEmailAddress_1)
						.addComponent(lblUsername_1)
						.addComponent(lblLastName_1)
						.addComponent(lblUserType)
						.addComponent(lblFirstName_1))
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(33)
							.addComponent(rdbtnAdmin)
							.addGap(25)
							.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_pnlCreateUser.createSequentialGroup()
									.addComponent(rdbtnManager)
									.addGap(31)
									.addComponent(rdbtnWorker))
								.addComponent(btnCreateUser)
								.addComponent(lblEnterUserInfo)))
						.addGroup(gl_pnlCreateUser.createSequentialGroup()
							.addGap(62)
							.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
								.addComponent(txtCreateLastName, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCreateFirstName, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCreateUsername, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCreateEmailAddress, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCreatePhoneNumber, GroupLayout.PREFERRED_SIZE, 291, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtCreatePassword, GroupLayout.PREFERRED_SIZE, 292, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(173, Short.MAX_VALUE))
		);
		gl_pnlCreateUser.setVerticalGroup(
			gl_pnlCreateUser.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlCreateUser.createSequentialGroup()
					.addGap(21)
					.addComponent(lblEnterUserInfo)
					.addGap(18)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName_1)
						.addComponent(txtCreateFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblLastName_1)
						.addComponent(txtCreateLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblUsername_1)
						.addComponent(txtCreateUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmailAddress_1)
						.addComponent(txtCreateEmailAddress, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(37)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPhoneNumber_1)
						.addComponent(txtCreatePhoneNumber, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(36)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(txtCreatePassword, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
					.addGroup(gl_pnlCreateUser.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(rdbtnAdmin)
						.addComponent(rdbtnManager)
						.addComponent(rdbtnWorker))
					.addGap(38)
					.addComponent(btnCreateUser)
					.addGap(176))
		);
		pnlCreateUser.setLayout(gl_pnlCreateUser);
		
		
		
		listAssignedQuals = new JList(assignedQualList);
		scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
		
		listAvailableQuals = new JList(availableQualList);
		scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
		pnlUserEditInfo.setLayout(gl_pnlUserEditInfo);
		
		
		createUserList();
		listUsers = new JList(userList);
		
		scrollPane.setViewportView(listUsers);
		listUsers.setBackground(UIManager.getColor("Button.background"));
		listUsers.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listUsers.setLayoutOrientation(JList.VERTICAL);
		listUsers.setVisibleRowCount(1);
		contentPane.setLayout(gl_contentPane);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminManageUsers.class.getResource("/resources/Logo.PNG")));
		
	}
	
	//This method contains all of the code for creating events
	private void createEvents() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				jdbc.closeSQLConnection();
			}
		});
		btn_create_new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pnlCreateUser.setVisible(true);
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
		//Display user information that was clicked on the left.
		listUsers.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  displayUserInfo(listUsers.getSelectedValue().toString());
                  lastClickedIndex = listUsers.getSelectedIndex();
                  int id = jdbc.getIdOfUser(textUsername.getText());
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
		
		assignQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				jdbc.assignQuals(lastClickeduserID, availQuals, listAvailableQuals.getSelectedIndices());
				createQualLists(lastClickeduserID);
			}
		});
		
		unassignQual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jdbc.UnassignQuals(lastClickeduserID, assignedQuals, listAssignedQuals.getSelectedIndices());
				createQualLists(lastClickeduserID);
			}
		});
		
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
				pnlCreateUser.setVisible(false);
				createUserList();
			}
		});
	}
	
	//creates a list of users
	private void createUserList() {
		userList.clear();
		ArrayList<User> users = jdbc.get_users();
		for (int i = 0; i < users.size(); i++) {
			userList.addElement(String.format("%s, %s [%s]", users.get(i).get_lastname(), users.get(i).get_firstname(), users.get(i).get_username()));
		}
	}
	
	//
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
	
	//
	private void updateUserList() {
		User u = jdbc.get_user(lastClickedUser);
		String s = String.format("%s, %s [%s]", u.get_lastname(), u.get_firstname(), u.get_username());
		userList.setElementAt(s, lastClickedIndex);
	}
	
	//creates a list of qualities a userID's user has or can have
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
