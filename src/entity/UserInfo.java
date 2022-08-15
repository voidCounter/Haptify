package entity;

import java.io.File;

import boundary.SingleMusic;

public class UserInfo {
	private String userName; 
	private File folderLocation;
	public UserInfo() {
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public File getFolderLocation() {
		return folderLocation;
	}
	public void setFolderLocation(File folderLocation) {
		this.folderLocation = folderLocation;
	}
}
