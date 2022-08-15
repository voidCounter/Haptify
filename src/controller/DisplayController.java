package controller;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Panel;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RootPaneContainer;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import boundary.MusicPage;
import boundary.SingleMusic;

//import org.w3c.dom.html.HTMLIsIndexElement;

import boundary.Welcome;

public class DisplayController {
	private AppController appController;
	public DisplayController(AppController appController) {
		this.appController = appController;
	}
	private List<JPanel> jpanelTree = new ArrayList<JPanel>();
	public void showErrorMessage(String message) {
		JOptionPane.showMessageDialog(null, message);
	}
	public void addToJpanelTree(JPanel panel) {
		jpanelTree.add(panel);
	}
	public JPanel getPanel(int index) {
		return jpanelTree.get(index);
	}

	public void replacePanel(JPanel parentPanel, JPanel panelToBeDelete, JPanel panelToBeAdded) {
		parentPanel.remove(panelToBeDelete);
		parentPanel.add(panelToBeAdded);
		parentPanel.validate();
	}
	public void replacePanel(JPanel panelToBeDelete, JPanel panelToBeAdded) {
		appController.getAppDisplayController().getPanel(0).remove(panelToBeDelete);
		appController.getAppDisplayController().getPanel(0).add(panelToBeAdded);
		appController.getAppDisplayController().getPanel(0).validate();
	}
	public void batchFileAdd(File[] files, JPanel parentPanel) throws UnsupportedTagException, InvalidDataException, IOException {
		for(int i= 0; i < files.length; i++) {
//		parentPanel.add(new SingleMusic(files[i], i+1));
			Mp3File mp3File = new Mp3File(files[i]);
			SingleMusic newSingleMusic = new SingleMusic(appController, files[i], i+1, appController.getAppMusicController().getDuration(mp3File));
			appController.getAppMusicController().addToMusicList(newSingleMusic);
			parentPanel.add(newSingleMusic);
		}
	}
	public void controlHighlight(boolean toHighlight, JPanel panel) {
		if(toHighlight) {
			panel.setBackground(new Color(0x041C32));
		}
		else {
			panel.setBackground(null);
		}
	}
	public int getJPanelTreeSize() {
		return this.jpanelTree.size();
	}
	public void changeCurrentSongInfo(boolean toPause, int index) {
		String updatedString;
		SingleMusic previouSingleMusic = appController.getAppMusicController().getPreviouslyPlayed();
		updatedString = appController.getAppMusicController().getMusic(index).getMusicName();
		if(previouSingleMusic == null) {
			appController.getAppMusicPage().changeIcon(toPause);
			appController.getAppMusicPage().setName(updatedString);
		}
		else {
			if((Integer.parseInt(previouSingleMusic.getMusicNo()) - 1) == index) {
				appController.getAppMusicPage().changeIcon(toPause);
			}
			else {
				appController.getAppMusicPage().setName(updatedString);
				appController.getAppMusicPage().changeIcon(toPause);
			}
		}
	}
}
