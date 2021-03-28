package hw1YigitKucukcinar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class MySQLBrowser extends JFrame {

	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblServer;
	private JTextField txtServer;
	private JLabel lblUsername;
	private JTextField txtUsername;
	private JLabel lblPassword;
	private JTextField txtPassword;
	private JButton btnConnect;
	private JComboBox cmbBoxSchemas;
	private JButton btnTables;
	private JButton btnRun;
	private JPanel panel_1;
	private JTextField txtFilter;
	private JButton btnFilter;
	private JSplitPane splitPane;
	private List<String> databases = new ArrayList<>();
	private JSplitPane splitPane_1;
	private JScrollPane scrollPane;
	private JTextArea txtQuerries;
	private JScrollPane scrollPane_1;
	private JTable tbl1;
	private String server;
	private String database;
	private String username;
	private String password;
	private String databaseURL;
	private Boolean connectedFlag = false;
	private String selectedQuerry;
	private ResultSetTableModel rsTbl1;
	private TableRowSorter<TableModel> sorter;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MySQLBrowser frame = new MySQLBrowser();
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
	public MySQLBrowser() {
		initGUI();
	}

	
	
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1116, 484);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		txtFilter = new JTextField();
		panel_1.add(txtFilter);
		txtFilter.setColumns(10);

		btnFilter = new JButton("Filter");

		btnFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filter();
			}

		});

		panel_1.add(btnFilter);

		splitPane = new JSplitPane();
		splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		contentPane.add(splitPane, BorderLayout.CENTER);

		panel = new JPanel();
		splitPane.setLeftComponent(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		lblServer = new JLabel("Server");
		panel.add(lblServer);

		txtServer = new JTextField();
		panel.add(txtServer);
		txtServer.setColumns(10);

		lblUsername = new JLabel("Username");
		panel.add(lblUsername);

		txtUsername = new JTextField();
		panel.add(txtUsername);
		txtUsername.setColumns(10);

		lblPassword = new JLabel("Password");
		panel.add(lblPassword);

		txtPassword = new JPasswordField();
		panel.add(txtPassword);
		txtPassword.setColumns(10);

		btnConnect = new JButton("Connect");
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnConnectClicked();
			}
		});
		panel.add(btnConnect);

		cmbBoxSchemas = new JComboBox();
		panel.add(cmbBoxSchemas);

		btnTables = new JButton("Tables");
		btnTables.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				showSelectedDBQuerries();
			}
		});
		panel.add(btnTables);

		btnRun = new JButton("Run");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				runBtnClicked();
			}
		});
		panel.add(btnRun);

		splitPane_1 = new JSplitPane();
		splitPane_1.setOrientation(JSplitPane.VERTICAL_SPLIT);
		splitPane.setRightComponent(splitPane_1);

		scrollPane = new JScrollPane();
		splitPane_1.setLeftComponent(scrollPane);

		txtQuerries = new JTextArea();
		scrollPane.setViewportView(txtQuerries);

		scrollPane_1 = new JScrollPane();
		splitPane_1.setRightComponent(scrollPane_1);

		tbl1 = new JTable();
		scrollPane_1.setViewportView(tbl1);
		splitPane_1.setDividerLocation(150);
	}

	protected void runBtnClicked() {

		selectedQuerry = txtQuerries.getSelectedText();

		try {
			String selectedDB = (String) cmbBoxSchemas.getSelectedItem();
			String newDBURL = databaseURL + selectedDB;
			ResultSetTableModel rsTbl1 = new ResultSetTableModel(newDBURL, txtUsername.getText(), txtPassword.getText(),
					selectedQuerry);

			if (selectedQuerry.toLowerCase().startsWith("select")) {
				tbl1.setModel(rsTbl1);
				txtFilter.setText("");
				sorter = new TableRowSorter<TableModel>(rsTbl1);
			} else {
				runBtnClicked();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(MySQLBrowser.this, "Wrong query");
		}

	}

	protected void filter() {

		String text = txtFilter.getText();

		try {
			sorter.setRowFilter(RowFilter.regexFilter(text));
		} catch (PatternSyntaxException pse) {
			JOptionPane.showMessageDialog(null, "Bad regex pattern", "Bad regex pattern", JOptionPane.ERROR_MESSAGE);
		}

		tbl1.setRowSorter(sorter);

	}

	protected void showSelectedDBQuerries() {
		String selectedDB = (String) cmbBoxSchemas.getSelectedItem();
		List<String> tables = new ArrayList<>();

		ResultSet rs;
		PreparedStatement pst;
		String newDBURL = databaseURL + selectedDB;
		try (Connection conn = DriverManager.getConnection(newDBURL, username, password);

		) {
			String query = "show tables";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				String tableName = rs.getString(1);
				tables.add(tableName);
			}
			StringBuilder stringBuilder = new StringBuilder();

			for (String table : tables) {
				stringBuilder.append("Select * from " + table + ";" + "\n");
			}
			txtQuerries.setText(stringBuilder.toString());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void btnConnectClicked() {
		if (connectedFlag) {
			disconnect();
			return;
		}
		server = txtServer.getText();
		database = "jdbc:mysql://";
		username = txtUsername.getText();
		password = txtPassword.getText();
		databaseURL = database + server + "/";

		ResultSet rs;
		PreparedStatement pst;

		try (Connection conn = DriverManager.getConnection(databaseURL, username, password);

		) {
			connectedFlag = true;
			btnConnect.setText("Disconnect");
			String query = "show databases";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			JOptionPane.showMessageDialog(MySQLBrowser.this, "Connected");
			while (rs.next()) {
				String DBname = rs.getString(1);
				databases.add(DBname);
			}

			cmbBoxSchemasUsable();

		} catch (Exception e) {
			JOptionPane.showMessageDialog(MySQLBrowser.this, "Wrong entry");
		}

	}

	private void disconnect() {
		connectedFlag = false;
		btnConnect.setText("Connect");
		txtServer.setText("");
		txtPassword.setText("");
		txtUsername.setText("");
		databases = new ArrayList<String>();
		cmbBoxSchemasUsable();
		txtQuerries.setText("");
		JOptionPane.showMessageDialog(MySQLBrowser.this, "Disconnected");
		tbl1 = new JTable();
		scrollPane_1.setViewportView(tbl1);
	}

	private void cmbBoxSchemasUsable() {
		DefaultComboBoxModel<String> cmbCategoryModel = new DefaultComboBoxModel<>(
				databases.toArray(new String[databases.size()]));

		cmbBoxSchemas.setModel(cmbCategoryModel);
	}

}
