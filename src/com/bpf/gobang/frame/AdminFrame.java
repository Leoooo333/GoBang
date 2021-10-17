package com.bpf.gobang.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.bpf.gobang.algorithm.LoginAlgorithm;
import com.bpf.gobang.entity.Record;
import com.bpf.gobang.function.ReplayerFunction;
import com.bpf.gobang.panel.HistoryTablePanel;
import com.bpf.gobang.role.Player;
import com.bpf.gobang.role.PlayerDataBase;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class AdminFrame extends JFrame {
	private static AdminFrame adminFrame = null;
	private JTextField textField;
	private JTable table;
	private static ArrayList<Player> playersList = null;
	private Object players[][] = null;
	
	public Object[][] getPlayers() {
		return players;
	}
	public void setPlayers(Object[][] players) {
		this.players = players;
	}
	
	 public static AdminFrame getAdminFrame(){
	        if(adminFrame == null){
	            synchronized(AdminFrame.class){
	                if(adminFrame == null){
	                	adminFrame = new AdminFrame();
	                }
	            }
	        }
	        return adminFrame;
	    }
		private AdminFrame() {
			init();
		}
		public void init() {		
			this.setBounds(0,0,776,556);
			//设置窗体标题
			this.setTitle("战绩界面");
			//设置关闭窗体后，程序结束
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			//取消该窗体布局
			getContentPane().setLayout(null);
			//获取用户数据集
			playersList = PlayerDataBase.getPlayerDataBase().getPlayersList();
			int length = playersList.size();
			
			players = new Object[length][4];
			int index = 0;
			for(Player p : playersList) {
				//设置所有行未选中
				players[index][0] = false;
				//设置用户名
				players[index][1] = p.getName();
				//设置用户密码
				players[index][2] = p.getPassword();
				//设置用户权限
				players[index][3] = p.isAdmin() ? "管理员" : "普通用户"; 
				index++;
			}
			String[] columns = {"选中","用户名","用户密码","用户权限"};
			getContentPane().setLayout(null);
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 94, 771, 429);
			getContentPane().add(scrollPane);
			
			table = new JTable();
			//设置单元格居中显示
			table.setModel(new HistoryTablePanel(players, columns));
			DefaultTableCellRenderer r=new DefaultTableCellRenderer();
	        r.setHorizontalAlignment(JLabel.CENTER);
	        table.setDefaultRenderer(Object.class,r);
	        //设置单可选
	        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			scrollPane.setViewportView(table);
			
			textField = new JTextField();
			textField.setBounds(10, 33, 102, 21);
			getContentPane().add(textField);
			textField.setColumns(10);
			
			JButton btnNewButton = new JButton("查询");
			btnNewButton.addMouseListener(new MouseAdapter() { //查询按钮
				@Override
				public void mouseClicked(MouseEvent e) {
					String content = textField.getText();
					int sum = 0;
					for(Player p : playersList) {
						if(content.equals(p.getName())) {
							sum++;
						}
					}
					
					players = new Object[sum][4];
					int index = 0;
					for(Player p : playersList) {
						if(content.equals(p.getName())) {
							players[index][0] = false;
							players[index][1] = p.getName();
							players[index][2] = p.getPassword();
							players[index][3] = p.isAdmin() ? "管理员" : "普通用户"; 
							index++;	
						}
					}
					if(sum == 0) {
						JOptionPane.showMessageDialog(null, "未查询到此用户！！！");
					}
					table.setModel(new HistoryTablePanel(players, columns));
					repaint();
				}
			});
			
			btnNewButton.setBounds(122, 32, 97, 23);
			getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("查看棋局");
			btnNewButton_1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i;
					for(i = 0; i < players.length; i++) {
						if((Boolean)table.getValueAt(i, 0)) {
							int index = 0;
							for(Player p : playersList) {
								if(p.getName().equals((String)table.getValueAt(i,1))) {
									LoginAlgorithm.setCurrentPlayer(playersList.get(index));
									break;
								}
								index++;
							}
							break;		
						}
					}
					HistoryFrame.getHistoryFrame().setAdmin(true);
					HistoryFrame.getHistoryFrame().updateCurrentPlayer();					
					HistoryFrame.getHistoryFrame().setVisible(true);
					AdminFrame.getAdminFrame().dispose();
				}
			});
			btnNewButton_1.setBounds(251, 32, 97, 23);
			getContentPane().add(btnNewButton_1);
			
			JButton btnNewButton_2 = new JButton("新建用户");
			btnNewButton_2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					Player player = new Player();
					String name = JOptionPane.showInputDialog("请输入新建用户名");
					if(name != "" ) {
						player.setName(name);
						String password = JOptionPane.showInputDialog("请输入用户密码");
						if (password != "") {
							player.setPassword(password);
							Object[] Values = { "普通用户", "管理员"};
							Object selectedValue = JOptionPane.showInputDialog(null, "请选择用户权限", "新建用户权限",
							JOptionPane.INFORMATION_MESSAGE, null,
							Values, Values[0]);
							if(selectedValue.equals("普通用户")) {
								player.setAdmin(false);
								JOptionPane.showMessageDialog(null,"普通用户新建成功！");
							}else {
								JOptionPane.showMessageDialog(null,"管理员新建成功！");
								player.setAdmin(true);
							}
							PlayerDataBase.getPlayerDataBase().getPlayersList().add(player);
							PlayerDataBase.getPlayerDataBase().update();
							
							playersList = PlayerDataBase.getPlayerDataBase().getPlayersList();
							int length = playersList.size();
							
							players = new Object[length][4];
							int index = 0;
							for(Player p : playersList) {
								//设置所有行未选中
								players[index][0] = false;
								//设置用户名
								players[index][1] = p.getName();
								//设置用户密码
								players[index][2] = p.getPassword();
								//设置用户权限
								players[index][3] = p.isAdmin() ? "管理员" : "普通用户"; 
								index++;
							}
							table.setModel(new HistoryTablePanel(players, columns));
							repaint();
						} else {
							JOptionPane.showMessageDialog(null,"密码不能为空！！！");
						}
					}else {
						JOptionPane.showMessageDialog(null,"用户名不能为空");
					}
				}
			});
			btnNewButton_2.setBounds(501, 33, 97, 23);
			getContentPane().add(btnNewButton_2);
			
			JButton btnNewButton_3 = new JButton("删除用户");
			btnNewButton_3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int i;
					for(i = 0; i < playersList.size(); i++) {
						if((Boolean)table.getValueAt(i, 0)) {
							PlayerDataBase.getPlayerDataBase().getPlayersList().remove(i);
							
							Object[] options = { "确认", "取消" }; 
							int choice = JOptionPane.showOptionDialog(null, "确认删除吗？", "警告", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
							null, options, options[0]); 
							if(choice == 1) {
								break;
							}			
							PlayerDataBase.getPlayerDataBase().update();
							playersList = PlayerDataBase.getPlayerDataBase().getPlayersList();
							int length = playersList.size();
							
							players = new Object[length][4];
							int index = 0;
							for(Player p : playersList) {
								//设置所有行未选中
								players[index][0] = false;
								//设置用户名
								players[index][1] = p.getName();
								//设置用户密码
								players[index][2] = p.getPassword();
								//设置用户权限
								players[index][3] = p.isAdmin() ? "管理员" : "普通用户"; 
								index++;
							}
							table.setModel(new HistoryTablePanel(players, columns));
							
							JOptionPane.showMessageDialog(null,"删除成功！！！");
							repaint();
							break;
						}
					}
				}
			});
			btnNewButton_3.setBounds(632, 32, 97, 23);
			getContentPane().add(btnNewButton_3);
			
			//使当前窗体显示
			this.setVisible(true);
			//禁止当前窗体大小改变
			this.setResizable(false);
		}
		
}
