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
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Panel;
import java.awt.TextArea;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.Button;
import javax.swing.JList;
import javax.swing.border.MatteBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;

import common.jdbc;
import common.User;
import common.Job;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.UIManager;
import javax.swing.JInternalFrame;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTextField;
import javax.swing.JSeparator;

public class AdminManageUsers extends JFrame {

	private JPanel contentPane;
	private JButton btn_create_new_user;
	private JButton btn_preferences;
	private JButton btn_settings;
	private JList<User[]> list;
	private JTextField textFirstName;
	private JTextField textLastName;
	private JTextField textUsername;
	private JTextField textEmail;
	private JTextField textPhone;

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
		
		initComponents();
		createEvents();
		
	}

	//This method contains all of the code for creating and intializing components.
	private void initComponents() {
		// TODO Auto-generated method stub
		setBackground(Color.RED);
		setTitle("TABL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 938, 815);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btn_settings = new JButton("Settings");
		
		
		btn_preferences = new JButton("Preferences");
		
		
		Panel pnlUsers = new Panel();
		pnlUsers.setBackground(Color.LIGHT_GRAY);
		
		btn_create_new_user = new JButton("Create New User");
		
		ArrayList<User> users = jdbc.get_users();
		String[] users_names = get_users_names(users);
		
		
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
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(pnlUsers, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btn_create_new_user, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
							.addGap(329)
							.addComponent(btn_preferences, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(btn_settings, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(pnlUserEditInfo, GroupLayout.PREFERRED_SIZE, 743, GroupLayout.PREFERRED_SIZE)))
					.addGap(313))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlUsers, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btn_preferences)
								.addComponent(btn_settings))
							.addComponent(btn_create_new_user)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 720, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(1)
							.addComponent(pnlUserEditInfo, GroupLayout.PREFERRED_SIZE, 733, GroupLayout.PREFERRED_SIZE)))
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
		
		JButton btnSaveChanges = new JButton("Save Changes");
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setForeground(Color.BLACK);
		
		JScrollPane scrlPaneAvailableQuals = new JScrollPane();
		
		JScrollPane scrlPaneAssignedQuals = new JScrollPane();
		
		JLabel lblAvailable = new JLabel("Available");
		lblAvailable.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel lblAssigned = new JLabel("Assigned");
		lblAssigned.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JButton button = new JButton("->");
		button.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JButton button_1 = new JButton("<-");
		button_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		GroupLayout gl_pnlUserEditInfo = new GroupLayout(pnlUserEditInfo);
		gl_pnlUserEditInfo.setHorizontalGroup(
			gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(63)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName)
								.addComponent(lblLastName)
								.addComponent(lblUsername)
								.addComponent(lblEmailAddress)
								.addComponent(lblPhoneNumber))
							.addGap(80)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, 330, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(123)
							.addComponent(scrlPaneAvailableQuals, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
								.addComponent(button)
								.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
							.addGap(21)
							.addComponent(scrlPaneAssignedQuals, GroupLayout.PREFERRED_SIZE, 174, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(24)
							.addComponent(separator, GroupLayout.PREFERRED_SIZE, 699, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addGap(278)
							.addComponent(btnSaveChanges)))
					.addContainerGap(8, Short.MAX_VALUE))
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addGap(179)
					.addComponent(lblAvailable)
					.addPreferredGap(ComponentPlacement.RELATED, 204, Short.MAX_VALUE)
					.addComponent(lblAssigned, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addGap(202))
		);
		gl_pnlUserEditInfo.setVerticalGroup(
			gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
					.addComponent(lblFullName, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.addGap(30)
					.addGroup(gl_pnlUserEditInfo.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addComponent(textFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(29)
							.addComponent(textLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(30)
							.addComponent(textUsername, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(31)
							.addComponent(textEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(textPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_pnlUserEditInfo.createSequentialGroup()
							.addComponent(lblFirstName)
							.addGap(29)
							.addComponent(lblLastName)
							.addGap(30)
							.addComponent(lblUsername)
							.addGap(31)
							.addComponent(lblEmailAddress)
							.addGap(33)
							.addComponent(lblPhoneNumber)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
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
							.addComponent(button)
							.addGap(18)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(30))
		);
		
		JList listAssignedQuals = new JList();
		scrlPaneAssignedQuals.setViewportView(listAssignedQuals);
		
		JList listAvailableQuals = new JList();
		scrlPaneAvailableQuals.setViewportView(listAvailableQuals);
		pnlUserEditInfo.setLayout(gl_pnlUserEditInfo);
		list = new JList(users_names);
		scrollPane.setViewportView(list);
		list.setBackground(UIManager.getColor("Button.background"));
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(1);
		contentPane.setLayout(gl_contentPane);
		setForeground(Color.BLACK);
		setIconImage(Toolkit.getDefaultToolkit().getImage(AdminManageUsers.class.getResource("/resources/Logo.PNG")));
	}
	
	//This method contains all of the code for creating events
	private void createEvents() {
		btn_create_new_user.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                if (!arg0.getValueIsAdjusting()) {
                  displayUserInfo((list.getSelectedValue().toString()));
                }
            }
        });
		
	}
	
	private String[] get_users_names(ArrayList<User> data) {
		String[] users = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			users[i] = data.get(i).get_firstname()+" "+data.get(i).get_lastname()+" ["+data.get(i).get_username()+"]";
		}
		return users;
	}
	
	private void displayUserInfo(String username) {
		User u = jdbc.get_user(username);
		
	}
}
