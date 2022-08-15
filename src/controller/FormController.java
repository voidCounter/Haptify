package controller;

import java.awt.Component;
import java.io.File;
import java.util.FormatFlagsConversionMismatchException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;

import boundary.AppWindow;
import boundary.Welcome;
import entity.UserInfo;

public class FormController {
	private String fieldMessage = "Provide a valid nick name";
	private String fileMessage = "Provide a folder to continue";
//	DisplayController newDisplayController = new DisplayController();
	private AppController appController;
	public FormController(AppController appController) {
		this.appController = appController;
	}

	public void manageForm1(String name, File location, JPanel parentJPanel, JPanel affectedPanel) {
		if(name.length()== 0 && location == null) {
			appController.getAppDisplayController().showErrorMessage(fieldMessage);
			appController.getAppDisplayController().showErrorMessage(fileMessage);
		}
		else if(location== null) {
			appController.getAppDisplayController().showErrorMessage(fileMessage);
		}
		else if(name.length() == 0) {
			appController.getAppDisplayController().showErrorMessage(fieldMessage);
		}
		else {
			appController.getUserInfo().setUserName(name);
			appController.getUserInfo().setFolderLocation(location);
			appController.getAppDisplayController().replacePanel(parentJPanel, affectedPanel, new Welcome(appController,
					appController.getUserInfo().getUserName(), parentJPanel));
		}
	}
	public File showSelectFolder() {
		JFileChooser folderChooser = new JFileChooser();
		folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int response = folderChooser.showOpenDialog(null);
		if(response == folderChooser.APPROVE_OPTION) {
			File file = new File(folderChooser.getSelectedFile().getAbsolutePath());
//					System.out.println(file);
			return file;
		}
		return null;
	}
}
