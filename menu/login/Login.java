package menu.login;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.sql.*;

public class Login {
	private static final String url = "jdbc:postgresql://localhost/Library";
	private static final String user = "postgres";
	private static final String password = "123456";
	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton exitButton;
	private JLabel loginMenuLabel;
	/**
	 * Establish connection to the database
	 */
	public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
	/**
	 * Check username and password from database
	 */
	private void checkLogin(String username, String password) {
		String sql = "SELECT * from users WHERE username='" + username + "' and password='" + password + "'";
		 try (Connection conn = connect();
	                Statement stmt = conn.createStatement();
	                ResultSet rs = stmt.executeQuery(sql)) {
		        if (username != null && password != null) {
		            if (rs.next()) {
		               JOptionPane.showMessageDialog(null, "Login Successfully");
		            } else {
		            	JOptionPane.showMessageDialog(null, "Wrong username/password", "Login Error", JOptionPane.ERROR_MESSAGE);
		            }
		        }
		    } catch (SQLException err) {
		    	 System.out.println(err.getMessage());
		    }
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.connect();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel userLabel = new JLabel("Username");
		userLabel.setBounds(44, 92, 76, 14);
		frame.getContentPane().add(userLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(44, 155, 76, 14);
		frame.getContentPane().add(passwordLabel);
		
		textField = new JTextField();
		textField.setBounds(130, 89, 227, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(130, 152, 227, 20);
		frame.getContentPane().add(passwordField);
		
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = textField.getText();
				String password = passwordField.getText();
				checkLogin(username, password);
			}
		});
		loginButton.setBounds(81, 197, 89, 23);
		frame.getContentPane().add(loginButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitButton.setBounds(268, 197, 89, 23);
		frame.getContentPane().add(exitButton);
		
		loginMenuLabel = new JLabel("Library Management System");
		loginMenuLabel.setHorizontalAlignment(SwingConstants.CENTER);
		loginMenuLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		loginMenuLabel.setBounds(50, 23, 324, 50);
		frame.getContentPane().add(loginMenuLabel);
	}
}