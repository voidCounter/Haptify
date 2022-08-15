package controller;

import java.io.IOException;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

import boundary.AppWindow;
import boundary.MusicPage;
import boundary.SingleMusic;
import boundary.Welcome;
import entity.UserInfo;

public class AppController {
	AppWindow appAppWindow;
	MusicPage appMusicPage;
	Welcome appWelcomePage;
	DisplayController appDisplayController;
	FormController appFormController;
	MusicController appMusicController;
	UserInfo userInfo;
	public AppController(){
		appDisplayController = new DisplayController(this);
		appFormController= new FormController(this);
		appMusicController= new MusicController(this);
		userInfo = new UserInfo();
	}
	public DisplayController getAppDisplayController() {
		return appDisplayController;
	}
	public FormController getAppFormController() {
		return appFormController;
	}
	public AppWindow getAppAppWindow() {
		return appAppWindow;
	}
	public void setAppAppWindow(AppWindow appAppWindow) {
		this.appAppWindow = appAppWindow;
	}
	public MusicPage getAppMusicPage() {
		return appMusicPage;
	}
	public void setAppMusicPage(MusicPage appMusicPage) {
		this.appMusicPage = appMusicPage;
	}
	public Welcome getAppWelcomePage() {
		return appWelcomePage;
	}
	public void setAppWelcomePage(Welcome appWelcomePage) {
		this.appWelcomePage = appWelcomePage;
	}
	public MusicController getAppMusicController() {
		return appMusicController;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	
}
