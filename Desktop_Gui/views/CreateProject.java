package views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CreateProject extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateProject frame = new CreateProject();
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
	public CreateProject() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 783, 544);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JLabel lblNewLabel = new JLabel("Project Name:");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 139, SpringLayout.WEST, contentPane);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Description:");
		sl_contentPane.putConstraint(SpringLayout.EAST, lblNewLabel_1, 0, SpringLayout.EAST, lblNewLabel);
		contentPane.add(lblNewLabel_1);
		
		textField = new JTextField();
		sl_contentPane.putConstraint(SpringLayout.NORTH, textField, 72, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 3, SpringLayout.NORTH, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, textField, -201, SpringLayout.EAST, contentPane);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JTextArea textArea = new JTextArea();
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel_1, 0, SpringLayout.NORTH, textArea);
		sl_contentPane.putConstraint(SpringLayout.NORTH, textArea, 32, SpringLayout.SOUTH, textField);
		sl_contentPane.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, textArea, -72, SpringLayout.EAST, contentPane);
		contentPane.add(textArea);
		
		JScrollPane scrollPane = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane, 260, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane, 94, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane, -82, SpringLayout.SOUTH, contentPane);
		contentPane.add(scrollPane);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_1, 295, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane, -46, SpringLayout.WEST, scrollPane_1);
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_1, 0, SpringLayout.NORTH, scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_1, -82, SpringLayout.SOUTH, contentPane);
		contentPane.add(scrollPane_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		sl_contentPane.putConstraint(SpringLayout.NORTH, scrollPane_2, 260, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, textArea, -35, SpringLayout.NORTH, scrollPane_2);
		sl_contentPane.putConstraint(SpringLayout.WEST, scrollPane_2, 552, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, scrollPane_2, -82, SpringLayout.SOUTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_2, -50, SpringLayout.EAST, contentPane);
		contentPane.add(scrollPane_2);
		
		JButton btnNewButton = new JButton("Add");
		sl_contentPane.putConstraint(SpringLayout.EAST, scrollPane_1, -20, SpringLayout.WEST, btnNewButton);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton, -7, SpringLayout.WEST, scrollPane_2);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		contentPane.add(btnNewButton);
		
		JButton button = new JButton("Remove");
		sl_contentPane.putConstraint(SpringLayout.NORTH, button, 343, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, btnNewButton, -20, SpringLayout.NORTH, button);
		sl_contentPane.putConstraint(SpringLayout.WEST, button, 0, SpringLayout.WEST, textField);
		sl_contentPane.putConstraint(SpringLayout.EAST, button, -6, SpringLayout.WEST, scrollPane_2);
		
		JList list_2 = new JList();
		scrollPane_2.setViewportView(list_2);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPane.add(button);
		
		JLabel lblQualification = new JLabel("Qualification");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblQualification, 0, SpringLayout.WEST, scrollPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblQualification, -6, SpringLayout.NORTH, scrollPane);
		contentPane.add(lblQualification);
		
		JLabel lblUserToAdd = new JLabel("Users to add");
		sl_contentPane.putConstraint(SpringLayout.WEST, lblUserToAdd, 294, SpringLayout.WEST, contentPane);
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUserToAdd, -6, SpringLayout.NORTH, scrollPane_1);
		contentPane.add(lblUserToAdd);
		
		JLabel lblUsersAdded = new JLabel("Users added");
		sl_contentPane.putConstraint(SpringLayout.SOUTH, lblUsersAdded, -6, SpringLayout.NORTH, scrollPane_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, lblUsersAdded, -144, SpringLayout.EAST, contentPane);
		contentPane.add(lblUsersAdded);
	}
}
