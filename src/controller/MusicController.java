package controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.mpatric.mp3agic.Mp3File;

import boundary.SingleMusic;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

import javax.naming.InterruptedNamingException;



public class MusicController {
	private List<SingleMusic> musicObjectList;
	private AppController appController;
	private MP3Player previousMp3Player;
	private int previouslyPlayed = -1;
	private SingleMusic currentSingleMusic;
	private boolean previousState = false;
	public MusicController(AppController appController) {
		musicObjectList = new ArrayList<SingleMusic>();
		this.appController = appController;
	}
	public File[] parseFolder(){
		File[] fileList;
		File location = appController.getUserInfo().getFolderLocation();
		fileList = location.listFiles();
//		for(int i = 0; i < fileList.length; i++) {
//			System.out.println(fileList[i]);
//		}
		return fileList;
	}
	public double getDuration(Mp3File mp3File) {
		return mp3File.getLengthInSeconds();
	}
	public void addToMusicList(SingleMusic newMusic) {
		musicObjectList.add(newMusic);
	}
	public SingleMusic getPreviouslyPlayed() {
		if(previouslyPlayed == -1) {
			return null;
		}
		return musicObjectList.get(previouslyPlayed);
	}
	public SingleMusic getMusic(int index) {
		return musicObjectList.get(index);
	}
	public int getMusicListSize() {
		return musicObjectList.size();
	}
	public void functionThePlayButton(int i) throws InterruptedException {
		this.currentSingleMusic= musicObjectList.get(i);
		// for the first time a music has been played
		if(previouslyPlayed == -1 && previousState == false) {
			appController.getAppDisplayController().controlHighlight(true, musicObjectList.get(i));
			previousMp3Player= appController.getAppMusicController().playMusic(musicObjectList.get(i));
			musicObjectList.get(i).changeIcon(true);
			appController.getAppDisplayController().changeCurrentSongInfo(true,i);
			previouslyPlayed = i;
			previousState = true;
		}
		// previously there was a music that has been played
		else {
			// it is the same music
			if(previouslyPlayed == i) {
				// we want to off the music
				if(previousState == true) {
					previousMp3Player.close();
					musicObjectList.get(i).changeIcon(false);
					// Changing the current song info
					appController.getAppDisplayController().changeCurrentSongInfo(false, i);
					appController.getAppDisplayController().controlHighlight(true, musicObjectList.get(i));
					previousState = false;
				}
				else {
				// we want to on the music
					musicObjectList.get(i).changeIcon(true);
					previousMp3Player= appController.getAppMusicController().playMusic(musicObjectList.get(i));
					appController.getAppDisplayController().changeCurrentSongInfo(true, i);
					appController.getAppDisplayController().controlHighlight(true, musicObjectList.get(i));
					previousState = true;
				}
			}
			// we're going to play another song
			else {
					previousMp3Player.close();
					this.getPreviouslyPlayed().changeIcon(false);
					musicObjectList.get(i).changeIcon(true);
					previousMp3Player= appController.getAppMusicController().playMusic(musicObjectList.get(i));
					appController.getAppDisplayController().controlHighlight(false, getPreviouslyPlayed());
					appController.getAppDisplayController().controlHighlight(true, musicObjectList.get(i));
					appController.getAppDisplayController().changeCurrentSongInfo(true, i);
					previousState = true;
					previouslyPlayed = i;
			}
				
		}
	}
	public SingleMusic getCurrentSingleMusic() {
		return this.currentSingleMusic;
	}
	public MP3Player playMusic(SingleMusic singleMusic) throws InterruptedException {
		MP3Player mp3Player = new MP3Player(appController, singleMusic.getMusicLocation().toString());
        mp3Player.play();
        return mp3Player;
	}
	public void offMusic(MP3Player mp3Player) {
		mp3Player.close();
	}
	public void playPreNext(boolean isToPlayNext) throws InterruptedException {
		SingleMusic currMusic= appController.getAppMusicController().getPreviouslyPlayed();
		if(currMusic == null) {
			if(isToPlayNext == true) {
				appController.getAppMusicController().functionThePlayButton(0);
			}
			else {
				appController.getAppMusicController().functionThePlayButton(getMusicListSize()-1);
			}
		}
		else {
			int index = Integer.parseInt(currMusic.getMusicNo()) -1;
			if(isToPlayNext) {
				if(index == appController.getAppMusicController().getMusicListSize() -1) {
					index = 0;
				}
				else {
					index++;
				}
			}
			else {
				if(index == 0) {
					index = appController.getAppMusicController().getMusicListSize()-1;
				}
				else {
					index--;
				}
			}
			appController.getAppMusicController().functionThePlayButton(index);
		}
	}
}
