package boundary;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.SwingConstants;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import controller.AppController;
import controller.DisplayController;
import controller.FormController;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Welcome extends JPanel{
	private JPanel parentPanel;

	/**
	 * Create the panel.
	 */
	public Welcome(AppController appController, String message, JPanel parentPanel) {
		this.parentPanel = parentPanel;
		appController.setAppWelcomePage(this);
		setForeground(SystemColor.text);
		setLayout(new BorderLayout(0, 0));
		this.setBackground(new Color(0x1B2430));
		JLabel lblWelcome = new JLabel("Welcome " + message);
		lblWelcome.setVerticalAlignment(SwingConstants.TOP);
		lblWelcome.setForeground(SystemColor.text);
		lblWelcome.setFont(new Font("DM Sans", Font.BOLD, 48));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		add(lblWelcome, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(0, 450));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Welcome.class.getResource("/resources/path352.png")));
		add(lblNewLabel, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0x1B2430));
		panel.setPreferredSize(new Dimension(5, 200));
		add(panel, BorderLayout.SOUTH);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnWelcom = new JButton("Continue");

		btnWelcom.setBackground(SystemColor.text);
		btnWelcom.setFont(new Font("DM Sans", Font.BOLD, 18));
		panel.add(btnWelcom);
		btnWelcom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel panelToBeReplaced = appController.getAppDisplayController().getPanel(1);
				try {
					MusicPage newMusicPage = new MusicPage(appController);
					appController.getAppDisplayController().addToJpanelTree(newMusicPage);
					appController.getAppDisplayController().replacePanel(panelToBeReplaced, newMusicPage);
				} catch (UnsupportedTagException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidDataException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

	}

}
