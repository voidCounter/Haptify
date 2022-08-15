package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.AppController;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;

public class SingleMusic extends JPanel {
	AppController appController;
	private String duration;
	private String musicNo;
	private String musicName;
	private File musicLocation;
	private JButton playButton;
	public SingleMusic(AppController appController, File location, int musicNo, double duration) {
		this.appController = appController;
		this.musicLocation = location;
		this.musicName = location.toString().substring(location.toString().lastIndexOf('/') + 1, location.toString().length());
		this.musicNo = String.valueOf(musicNo);
		// getting the duration string
		String min = String.valueOf((int)duration/60);
		String sec = String.valueOf((int)duration%60);
		this.duration = (min.length()==1?"0"+min:min) +":" +  (sec.length()==1?"0"+sec :sec);
		this.setBackground(null);
		setPreferredSize(new Dimension(900, 80));
		setLayout(new BorderLayout(0, 0));
		JPanel musicNoLabel = new JPanel();
		musicNoLabel.setBackground(null);
		musicNoLabel.setPreferredSize(new Dimension(80, 10));
		add(musicNoLabel, BorderLayout.WEST);
		musicNoLabel.setLayout(new BorderLayout(0, 0));
		// adding a mouse listener to the parent panel
		this.addMouseListener(new MouseAdapter() {
		
	     });
		
		JLabel label = new JLabel(this.musicNo);
		label.setForeground(new Color(169, 169, 169));
		label.setFont(new Font("DM Sans", Font.BOLD, 18));
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		musicNoLabel.add(label, BorderLayout.CENTER);
		
		JPanel musicInfo = new JPanel();
		musicInfo.setForeground(new Color(169, 169, 169));
		musicInfo.setBackground(null);
		add(musicInfo, BorderLayout.CENTER);
		musicInfo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel(musicName);
		lblNewLabel.setFont(new Font("DM Sans", Font.BOLD, 18));
		lblNewLabel.setForeground(new Color(169, 169, 169));
		musicInfo.add(lblNewLabel, BorderLayout.WEST);
		
		JPanel playButtonContainer = new JPanel();
		playButtonContainer.setBackground(null);
		playButtonContainer.setPreferredSize(new Dimension(130, 10));
		musicInfo.add(playButtonContainer, BorderLayout.EAST);
		playButtonContainer.setLayout(new BorderLayout(0, 0));
		
		JButton playButton = new JButton("");
		playButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.playButton = playButton;
		playButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				playButton.getParent().getParent().getParent().setBackground(new Color(0x041C32));
				// After clicking the play buttton, things should go like this
				/**
				 * Req: there will be a list in the music controller to hold all music object so far. displayController will manage
				 * an instance of it throughout its lifetime. displaycontroller will have a data for tracking the 
				 * previously played music. It is nothing but the index of the music in the list.
				 * 1. Send the musicno of the music to the displaycontroller. 
				 * 2. then display controller will check if there is already a file selected in the past(selected). 
				 * 3. If the index matches, background color will reset and off the music by musicController and exit. 
				 * 4. If the indexes don't match, previous indexed music's background will be reset. and off the 
				 * previously played music. Now 
				 * 5. The current indexed music's background will be changed and it'll be on by musicController and exit.
				 * 6. While doing so, displayController will change the current song info 
				 */
				try {
					appController.getAppMusicController().functionThePlayButton(musicNo-1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		playButton.setBorder(null);
		playButton.setBackground(null);
		playButton.setIcon(new ImageIcon(SingleMusic.class.getResource("/resources/play-button-arrowhead24*24.png")));
		playButtonContainer.add(playButton, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel(this.duration);
		lblNewLabel_1.setForeground(new Color(169, 169, 169));
		lblNewLabel_1.setFont(new Font("DM Sans", Font.BOLD, 16));
		playButtonContainer.add(lblNewLabel_1, BorderLayout.WEST);
	}
	public File getMusicLocation() {
		return musicLocation;
	}
	public String getMusicName() {
		return this.musicName;
	}
	public String getMusicNo() {
		return this.musicNo;
	}
	public void changeIcon(boolean toPause) {
		if(toPause== true)
			playButton.setIcon(new ImageIcon(SingleMusic.class.getResource("/resources/pause-button24*24.png")));
		else {
			playButton.setIcon(new ImageIcon(SingleMusic.class.getResource("/resources/play-button-arrowhead24*24.png")));
		}
	}

}
