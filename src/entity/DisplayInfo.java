package entity;

import javax.swing.JPanel;

public class DisplayInfo {
	private JPanel parentPanel;
	private JPanel panelToBeReplaced;
	private JPanel panelToBeAdded;
	public JPanel getParentPanel() {
		return parentPanel;
	}
	public void setParentPanel(JPanel parentPanel) {
		this.parentPanel = parentPanel;
	}
	public JPanel getPanelToBeReplaced() {
		return panelToBeReplaced;
	}
	public void setPanelToBeReplaced(JPanel panelToBeReplaced) {
		this.panelToBeReplaced = panelToBeReplaced;
	}
	public JPanel getPanelToBeAdded() {
		return panelToBeAdded;
	}
	public void setPanelToBeAdded(JPanel panelToBeAdded) {
		this.panelToBeAdded = panelToBeAdded;
	}
	
}
