package usermenu;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;
import person.NormalUser;
import utilities.ViewRentedBook;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ReturnBook extends JFrame {

	private JPanel contentPane;
	private JTable table = new JTable() {
		@Override
		public Class getColumnClass(int column) {
		    return (getValueAt(0, column).getClass());
		  } //Render true/false as checkboxes
		@Override
	    public boolean isCellEditable(int row, int column) {
	       if (column == table.getColumnCount() - 1) return true;
	       else return false;
	    }
	};
	private static Vector<Boolean> checkbox = new Vector<Boolean>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnBook frame = new ReturnBook();
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
	public ReturnBook() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 608, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 592, 187);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select books to return:");
		lblNewLabel.setBounds(10, 11, 153, 20);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 33, 592, 154);
		panel.add(scrollPane);
		
		scrollPane.setViewportView(table);
		DefaultTableModel tableModel = (DefaultTableModel) DbUtils.resultSetToTableModel(ViewRentedBook.viewUserRentedBook(UserMenu.user));
		checkbox.clear(); 
		for (int i=0; i< tableModel.getRowCount(); i++) {
			checkbox.add(false);
		}
			
		tableModel.addColumn("Select", checkbox);
		table.setModel(tableModel);
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Integer> returnList = new ArrayList<Integer>();
				for (int i=0; i< tableModel.getRowCount(); i++) {
					if ((boolean) table.getModel().getValueAt(i, 6)) returnList.add((Integer) table.getModel().getValueAt(0, i));
				}
				if (NormalUser.returnBook(returnList, UserMenu.user)) dispose();
			}
		});
		submitButton.setBounds(251, 215, 89, 23);
		contentPane.add(submitButton);
	}
}
