package com.cjm.gobang.panel;

import javax.swing.JPanel;
import java.awt.Color;

public class HistoryPanel extends JPanel {
	private static HistoryPanel historyPanel = null;

	/**
	 * Create the panel.
	 */
	private HistoryPanel() {
		init();
	}

	public static HistoryPanel getHistoryPanel() {
		if (historyPanel == null) {
			synchronized (HistoryPanel.class) {
				if (historyPanel == null) {
					historyPanel = new HistoryPanel();
				}
			}
		}
		return historyPanel;
	}

	public void init() {
		setLayout(null);
		this.setBackground(Color.WHITE);
		this.setBounds(0, 0, 776, 556);
	}
}
