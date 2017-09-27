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

public class AdminManageUsers extends JFrame {

	private JPanel contentPane;
	private JButton btn_create_new_user;
	private JButton btn_preferences;
	private JButton btn_settings;

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
		setBounds(100, 100, 1255, 815);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		btn_settings = new JButton("Settings");
		
		
		btn_preferences = new JButton("Preferences");
		
		
		Panel panel = new Panel();
		panel.setBackground(Color.LIGHT_GRAY);
		
		btn_create_new_user = new JButton("Create New User");
		
		ArrayList<User> users = jdbc.get_users();
		String[] users_names = get_users_names(users);
		JList<User[]> list = new JList(users_names);
		list.setBackground(UIManager.getColor("Button.background"));
		list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(1);
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(list, GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btn_create_new_user, GroupLayout.PREFERRED_SIZE, 125, GroupLayout.PREFERRED_SIZE)
					.addGap(713)
					.addComponent(btn_preferences, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btn_settings, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 276, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btn_preferences)
								.addComponent(btn_settings))
							.addComponent(btn_create_new_user, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(452, Short.MAX_VALUE))
		);
		
		JLabel lblUsers = DefaultComponentFactory.getInstance().createLabel("USERS");
		lblUsers.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(lblUsers);
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
		
	}
	
	private String[] get_users_names(ArrayList<User> data) {
		String[] users = new String[data.size()];
		for (int i = 0; i < data.size(); i++) {
			System.out.println(data.get(i).get_firstname()+" "+data.get(i).get_lastname()+"["+data.get(i).get_username()+"]");
			users[i] = data.get(i).get_firstname()+" "+data.get(i).get_lastname()+"["+data.get(i).get_username()+"]";
		}
		return users;
	}
}
