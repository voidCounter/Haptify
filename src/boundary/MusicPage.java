package boundary;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import controller.AppController;

import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.format.TextStyle;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.BoxLayout;
import java.awt.CardLayout;
import javax.swing.ScrollPaneConstants;

public class MusicPage extends JPanel {
	AppController appController;
	private JLabel currentMusicLabel;
	private JButton currentPlayButton;
	private boolean previouslyClicked = false;
	/**
	 * Create the panel.
	 * @throws IOException 
	 * @throws InvalidDataException 
	 * @throws UnsupportedTagException 
	 */
	public MusicPage(AppController appController) throws UnsupportedTagException, InvalidDataException, IOException {
		this.appController = appController;
		appController.setAppMusicPage(this);
		setLayout(new BorderLayout(0, 0));
		
		JPanel currentSong = new JPanel();
		currentSong.setPreferredSize(new Dimension(10, 300));
		currentSong.setBackground(new Color(0x2C3639));
		add(currentSong, BorderLayout.NORTH);
		currentSong.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setPreferredSize(new Dimension(300, 0));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MusicPage.class.getResource("/resources/musicIcon.png")));
		currentSong.add(lblNewLabel, BorderLayout.WEST);
		
		JPanel musicControl = new JPanel();
		musicControl.setBackground(null);
		currentSong.add(musicControl, BorderLayout.CENTER);
		musicControl.setLayout(new BorderLayout(0, 0));
		
		JLabel currentMusicName = new JLabel("Music");
		this.currentMusicLabel = currentMusicName;
	
		currentMusicName.setForeground(new Color(169, 169, 169));
		currentMusicName.setFont(new Font("DM Sans", Font.BOLD, 36));
		musicControl.add(currentMusicName, BorderLayout.WEST);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(null);
		panel_1.setPreferredSize(new Dimension(10, 80));
		musicControl.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(null);
		
		JButton currentPlayButton = new JButton("");
		this.currentPlayButton = currentPlayButton;
		currentPlayButton.setBorder(null);
		currentPlayButton.setBackground(null);
		currentPlayButton.setIcon(new ImageIcon(MusicPage.class.getResource("/resources/play-button-arrowhead.png")));
		currentPlayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(previouslyClicked == true) {
					String currentMusicIndex = appController.getAppMusicController().getPreviouslyPlayed().getMusicNo();
					try {
						appController.getAppMusicController().functionThePlayButton(Integer.parseInt(currentMusicIndex) - 1);
					} catch (NumberFormatException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else {
					try {
						appController.getAppMusicController().functionThePlayButton(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					previouslyClicked = true;
				}
			}
		});
		currentPlayButton.setBounds(69, 12, 57, 46);
		panel_1.add(currentPlayButton);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					appController.getAppMusicController().playPreNext(true);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_1.setIcon(new ImageIcon(MusicPage.class.getResource("/resources/right-forward.png")));
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground((Color) null);
		btnNewButton_1.setBounds(138, 12, 57, 46);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					appController.getAppMusicController().playPreNext(false);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton_2.setIcon(new ImageIcon(MusicPage.class.getResource("/resources/left-forward.png")));
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground((Color) null);
		btnNewButton_2.setBounds(0, 12, 57, 46);
		panel_1.add(btnNewButton_2);
		
		JPanel musicListPanel = new JPanel();
		musicListPanel.setBorder(null);
		musicListPanel.setForeground(SystemColor.inactiveCaptionBorder);
		musicListPanel.setBackground(new Color(0x1B2430));
		add(musicListPanel, BorderLayout.CENTER);
		musicListPanel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(null);
		scrollPane.setSize(1024, 800);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		musicListPanel.add(scrollPane);
		JPanel test = new JPanel();
		test.setBorder(null);
		test.setBackground(new Color(0x1B2430));
		test.setPreferredSize(new Dimension(10, 700));
		scrollPane.setViewportView(test);

		File[] musicFileList = appController.getAppMusicController().parseFolder();
		appController.getAppDisplayController().batchFileAdd(musicFileList, test);
		test.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
	}
	public void setName(String name) {
		this.currentMusicLabel.setText(name);
	}
	public void changeIcon(boolean toPause) {
		if(toPause== true)
			this.currentPlayButton.setIcon(new ImageIcon(SingleMusic.class.getResource("/resources/pause-button.png")));
		else {
			this.currentPlayButton.setIcon(new ImageIcon(SingleMusic.class.getResource("/resources/play-button-arrowhead.png")));
		}
	}
	
}
