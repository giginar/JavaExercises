package lab3YigitKucukcinar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.awt.event.ActionEvent;

public class PhoneBook extends JFrame {

	private JPanel contentPane;
	private JMenuBar menuBar;
	private JMenu mnNewMenu;
	private JMenuItem mntmNewMenuItem;
	private JComboBox<String> cmbBox1;
	private JScrollPane scrollPane;
	private JTextArea txtData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhoneBook frame = new PhoneBook();
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
	public PhoneBook() {
		initGUI();
	}

	private void initGUI() {
		DefaultComboBoxModel<String> cmbCategoryModel = new DefaultComboBoxModel<>();
		Map<String, List<String>> categoryMap = new TreeMap<>();
		List<String[]> list1 = new ArrayList<>();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);

		mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(PhoneBook.this) == JFileChooser.APPROVE_OPTION) {

					try (BufferedReader reader = new BufferedReader(
							new InputStreamReader(new FileInputStream(fileChooser.getSelectedFile()), "utf-8"))) {

						String line = "";

						while ((line = reader.readLine()) != null) {
							line = line.toLowerCase();
							String[] temporaryList = line.split(":");
							list1.add(temporaryList);
							String category = temporaryList[5];

							if ((categoryMap.containsKey(category))) {
								List<String> inventoryList = categoryMap.get(temporaryList[5]);
								String inventory = temporaryList[0] + " " + temporaryList[1] + " " + temporaryList[2]
										+ "\n";
								inventoryList.add(inventory);
								Collections.sort(inventoryList);
								categoryMap.put(category, inventoryList);

							} else {
								List<String> inventoryList = new ArrayList<>();
								String inventory = temporaryList[0] + " " + temporaryList[1] + " " + temporaryList[2]
										+ "\n";
								inventoryList.add(inventory);
								Collections.sort(inventoryList);
								categoryMap.put(category, inventoryList);
							}
						}

					} catch (Exception e2) {
						e2.printStackTrace();
					}

				}

				Set<String> temporarySet = new HashSet<>();
				for (String string : categoryMap.keySet()) {
					temporarySet.add(string);
				}

				List<String> list = new ArrayList<String>(temporarySet);
				Collections.sort(list);
				for (String string2 : list) {
					// System.out.println(string2);
					cmbCategoryModel.addElement(string2);
				}

				cmbBox1.setModel(cmbCategoryModel);
			}
		});

		/*
		 * for (String key : catList.keySet()) { System.out.println(key); }
		 */
		mnNewMenu.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		cmbBox1 = new JComboBox<>();
		cmbBox1.setModel(cmbCategoryModel);
		//

		// DefaultComboBoxModel<String> lstModel = new DefaultComboBoxModel<>(new
		// String[] {"Page 1","Page 2","Page 3"});

		cmbBox1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (String key : categoryMap.keySet()) {
					if (key == cmbBox1.getSelectedItem()) {

						List<String> tempList = categoryMap.get(key);
						String x = "";
						for (String string : tempList) {
							x = x + string;
						}
						txtData.setText(x);
					}
				}

			}
		});

		contentPane.add(cmbBox1, BorderLayout.NORTH);

		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);

		txtData = new JTextArea();
		scrollPane.setViewportView(txtData);
	}

}
