package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.AppController;
import controller.DisplayController;
import controller.FormController;

public class AppWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private File folderLocation;		
	AppController appController = new AppController();
	public AppWindow() {
		appController.setAppAppWindow(this);
		setTitle("Haptify");
		setBackground(new Color(255, 255, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1200, 804);
		contentPane = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(contentPane);
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
				
		JPanel parentPanel = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(parentPanel);
		parentPanel.setLayout(new BorderLayout(0, 0));
		contentPane.add(parentPanel, BorderLayout.CENTER);

		JPanel imagePanel = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(imagePanel);
		imagePanel.setPreferredSize(new Dimension(500, 10));
		imagePanel.setLayout(new BorderLayout(0, 0));
		parentPanel.add(imagePanel,BorderLayout.WEST);
//		contentPane.add(panel, BorderLayout.WEST);

		
		JPanel userPanel = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(userPanel);
		userPanel.setPreferredSize(new Dimension(400, 10));
		userPanel.setBackground(new Color(0x1B2430));
		userPanel.setLayout(new BorderLayout(0, 0));
		parentPanel.add(userPanel, BorderLayout.CENTER);
//		contentPane.add(panel_1, BorderLayout.CENTER);


		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		lblNewLabel.setIcon(new ImageIcon(AppWindow.class.getResource("/resources/music.jpg")));
		imagePanel.add(lblNewLabel);
		
	
		JPanel logoPanel = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(logoPanel);
		logoPanel.setBackground(null);
		logoPanel.setPreferredSize(new Dimension(10, 100));
		userPanel.add(logoPanel, BorderLayout.NORTH);
		logoPanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblHaptify = new JLabel("Haptify");
		lblHaptify.setPreferredSize(new Dimension(45, 64));
		lblHaptify.setHorizontalAlignment(SwingConstants.CENTER);
		lblHaptify.setIconTextGap(8);
		lblHaptify.setFont(new Font("DM Sans", Font.BOLD, 18));
		lblHaptify.setForeground(Color.RED);
		lblHaptify.setIcon(new ImageIcon(AppWindow.class.getResource("/resources/haptify.png")));
		logoPanel.add(lblHaptify);
		
		JPanel formPanelContainer = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(formPanelContainer);
		userPanel.add(formPanelContainer, BorderLayout.CENTER);
		formPanelContainer.setBackground(null);
		formPanelContainer.setLayout(new BorderLayout(0, 0));
		
		JPanel formPanel = new JPanel();
		appController.getAppDisplayController().addToJpanelTree(formPanel);
		formPanel.setPreferredSize(new Dimension(5, 10));
		formPanel.setBackground(null);
		formPanelContainer.add(formPanel, BorderLayout.CENTER);
		formPanel.setLayout(null);
		
		JLabel lblWriteYourNick = new JLabel("Write your nick name");
		lblWriteYourNick.setFont(new Font("DM Sans", Font.BOLD, 14));
		lblWriteYourNick.setBounds(211, 136, 186, 17);
		formPanel.add(lblWriteYourNick);
		lblWriteYourNick.setForeground(new Color(169, 169, 169));
		
		textField = new JTextField();
		textField.setFont(new Font("DM Sans", Font.PLAIN, 14));
		textField.setCaretColor(SystemColor.text);
		textField.setBounds(213, 165, 216, 36);
		formPanel.add(textField);
		textField.setForeground(SystemColor.text);
		textField.setBackground(null);
		textField.setColumns(10);
		
		JLabel lblChooseFolderContaining = new JLabel("Choose folder containing music");
		lblChooseFolderContaining.setFont(new Font("DM Sans", Font.BOLD, 14));
		lblChooseFolderContaining.setBounds(214, 229, 234, 17);
		formPanel.add(lblChooseFolderContaining);
		lblChooseFolderContaining.setForeground(new Color(169, 169, 169));
		
		JButton btnNewButton = new JButton("Select folder");
		btnNewButton.setPreferredSize(new Dimension(109, 28));
		btnNewButton.setFont(new Font("DM Sans", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					File file = appController.getAppFormController().showSelectFolder();
					folderLocation = file;
					btnNewButton.setText(String.valueOf(file));
			}
		});
		btnNewButton.setBounds(214, 260, 140, 27);
		formPanel.add(btnNewButton);
		btnNewButton.setForeground(new Color(169, 169, 169));
		btnNewButton.setBackground(null);
		
		JButton btnNewButton_1 = new JButton("Submit");
		btnNewButton_1.setFont(new Font("DM Sans", Font.BOLD, 14));
		btnNewButton_1.setBounds(214, 327, 216, 36);
		formPanel.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// messages if escaped
					if(arg0.getSource() == btnNewButton_1) {
						JPanel parentJPanel = appController.getAppDisplayController().getPanel(1);
						JPanel affectedPanel= appController.getAppDisplayController().getPanel(3);
						appController.getAppFormController().manageForm1(textField.getText(), folderLocation, parentJPanel, affectedPanel);
				}
			}
		});
		btnNewButton_1.setForeground(new Color(169, 169, 169));
		btnNewButton_1.setBackground(new Color(178, 34, 34));
	}
}
