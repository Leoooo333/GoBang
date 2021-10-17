package com.bpf.gobang.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Checkerboard;
import com.bpf.gobang.entity.Common;
import com.bpf.gobang.entity.Record;
import com.bpf.gobang.function.ReplayerFunction;
import com.bpf.gobang.function.runnable.TimerRunnable;
import com.bpf.gobang.panel.HistoryTablePanel;
import com.bpf.gobang.role.PlayerDataBase;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HistoryFrame extends JFrame {

	private static HistoryFrame historyFrame = null;
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}

	private JTextField textField;
	private JTable table;
	private ReplayerFrame replayerFrame;
	private JComboBox comboBox;
	private Object records[][] = null;
	//判断是否由管理员打开
	private boolean admin = false;
	
	private ArrayList<Record> currentRecords = LoginAlgorithm.getCurrentPlayer().getRecords();
	public Object[][] getRecords() {
		return records;
	}
	public void setRecords(Object[][] records) {
		this.records = records;
	}
	/**
	 * Create the frame.
	 */
	private HistoryFrame() {
		init();
	}
	public static HistoryFrame getHistoryFrame(){
        if(historyFrame == null){
            synchronized(HistoryFrame.class){
                if(historyFrame == null){
                	historyFrame = new HistoryFrame();
                }
            }
        }
        return historyFrame;
    }
	public void init() {
		int length = currentRecords.size();
		
		records = new Object[length][4];
		
		int index = 0;
		for(Record r : currentRecords) {
			//设置所有行未选中
			records[index][0] = false;
			//设置黑子方账号
			records[index][1] = LoginAlgorithm.getCurrentPlayer().getName();
			//设置白字方账号
			records[index][2] = r.getName();
			//设置对战编号
			records[index][3] = LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index;
			index++;
		}
		
		
		this.setBounds(0,0,776,556);
		//设置窗体标题
		this.setTitle("战绩界面");
		//设置关闭窗体后，程序结束
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//取消该窗体布局
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("点击回放");
		btnNewButton.addMouseListener(new MouseAdapter() {// 回放按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				int i; 
				for(i = 0; i < records.length;i++) {
					if ((Boolean) table.getValueAt(i, 0)) {
						int index = 0;
						for(Record r : currentRecords) {
							if(r.getName().equals((String)table.getValueAt(i,2))) {
								ReplayerFunction.showRecordByTime(index);
								break;
							}
							index++;
						}
						break;
					}
				}
			}
		});
		btnNewButton.setBounds(493, 28, 97, 23);
		getContentPane().add(btnNewButton);
		
		String[] columns = {"单击选择","黑子方","白子方","对战编号"};
		ComboBoxModel jComboBoxModel = new DefaultComboBoxModel(
				new String[] {"黑子方","白子方","对战编号"} );
		comboBox = new JComboBox(jComboBoxModel);
		comboBox.setBounds(148, 27, 66, 25);
		getContentPane().add(comboBox);
		
		JButton btnNewButton_1 = new JButton("搜索");
		btnNewButton_1.addMouseListener(new MouseAdapter() {//搜索按钮
			@Override
			public void mouseClicked(MouseEvent e) {
				searchObjects(String.valueOf(comboBox.getSelectedItem()));
				table.setModel(new HistoryTablePanel(records, columns));
				repaint();
			}
		});
		btnNewButton_1.setBounds(244, 28, 97, 23);
		getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("返回上级");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(admin == false) {
					HistoryFrame.getHistoryFrame().dispose();
					MenuFrame.getMenuFrame().setVisible(true);
				 } else if(admin == true) {
					 HistoryFrame.getHistoryFrame().dispose();
					 AdminFrame.getAdminFrame().setVisible(true);
				 }
				
			}
		});
		btnNewButton_2.setBounds(628, 28, 97, 23);
		getContentPane().add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(46, 29, 66, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		

	
		
		table = new JTable();
		//设置单元格居中显示
		table.setModel(new HistoryTablePanel(records, columns));
		DefaultTableCellRenderer r=new DefaultTableCellRenderer();
        r.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class,r);
        //设置单可选
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(46, 76, 681, 433);
		getContentPane().add(scrollPane);
		
		
	//	scrollPane.setColumnHeaderView(table);
		
		
		//使当前窗体显示
		this.setVisible(true);
		//禁止当前窗体大小改变
		this.setResizable(false);
		
	}
	public ReplayerFrame getReplayerFrame() {
		return replayerFrame;
	}
	public void setReplayerFrame(ReplayerFrame replayerFrame) {
		this.replayerFrame = replayerFrame;
	}
	
	public void searchObjects(String key) {
		int sum = 0;
			String content = this.textField.getText();
			System.out.println(content);
			int index = 0;
			for(Record r : currentRecords) {
				if(key.equals("黑子方")) {
					if(LoginAlgorithm.getCurrentPlayer().getName().equals(content)) {
						sum++;
					}
				}else if(key.equals("白子方")) {
					if(r.getName().equals(content)) {
						sum++;
					}
				}else if(key.equals("对战编号")) {
					if(content.equals(LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index)) {
						sum++;
					}
				}
			}
			//System.out.println("SUM=" + sum);
			records = new Object[sum][4];
			
			for(Record r : currentRecords) {
				if(key.equals("黑子方")) {
					if(LoginAlgorithm.getCurrentPlayer().getName().equals(content)) {
						//设置所有行未选中
						records[index][0] = false;
						//设置黑子方账号
						records[index][1] = LoginAlgorithm.getCurrentPlayer().getName();
						//设置白字方账号
						records[index][2] = r.getName();
						//设置对战编号
						records[index][3] = LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index;
						index++;
					}
				}else if(key.equals("白子方")) {
					if(r.getName().equals(content)) {
						//设置所有行未选中
						records[index][0] = false;
						//设置黑子方账号
						records[index][1] = LoginAlgorithm.getCurrentPlayer().getName();
						//设置白字方账号
						records[index][2] = r.getName();
						//设置对战编号
						records[index][3] = LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index;
						index++;
					}
				}else if(key.equals("对战编号")) {
					if(content.equals(LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index)) {
						//设置所有行未选中
						records[index][0] = false;
						//设置黑子方账号
						records[index][1] = LoginAlgorithm.getCurrentPlayer().getName();
						//设置白字方账号
						records[index][2] = r.getName();
						//设置对战编号
						records[index][3] = LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index;
						index++;
					}
				}

			}
		if(sum == 0) {
			JOptionPane.showMessageDialog(null, "未查询到对局记录！");
		}
	}
	
	public void updateCurrentPlayer() {
		currentRecords = LoginAlgorithm.getCurrentPlayer().getRecords();
		System.out.println(LoginAlgorithm.getCurrentPlayer().getName());
		int length = currentRecords.size();
		
		records = new Object[length][4];
		
		int index = 0;
		for(Record r : currentRecords) {
			//设置所有行未选中
			records[index][0] = false;
			//设置黑子方账号
			records[index][1] = LoginAlgorithm.getCurrentPlayer().getName();
			//设置白字方账号
			records[index][2] = r.getName();
			//设置对战编号
			records[index][3] = LoginAlgorithm.getCurrentPlayer().getName() + "VS" + r.getName() + "R" + index;
			index++;
		}
		
		String[] columns = {"单击选择","黑子方","白子方","对战编号"};
		table.setModel(new HistoryTablePanel(records, columns));
		repaint();
	}
}
