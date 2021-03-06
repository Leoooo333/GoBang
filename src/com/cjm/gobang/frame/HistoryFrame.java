package com.cjm.gobang.frame;

import com.cjm.gobang.algorithm.LoginAlgorithm;
import com.cjm.gobang.entity.Record;
import com.cjm.gobang.function.ReplayerFunction;
import com.cjm.gobang.panel.HistoryTablePanel;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;

public class HistoryFrame extends JFrame {
    private static HistoryFrame historyFrame = null;
    private JTextField textField;
    private JTable table;
    private ReplayerFrame replayerFrame;
    private JComboBox comboBox;
    private Object[][] records = null;
    private ArrayList<Record> currentRecords = LoginAlgorithm.getCurrentPlayer().getRecords();

    public Object[][] getRecords() {
        return this.records;
    }

    public void setRecords(Object[][] records) {
        this.records = records;
    }

    private HistoryFrame() {
        this.init();
    }

    public static HistoryFrame getHistoryFrame() {
        if (historyFrame == null) {
            Class var0 = HistoryFrame.class;
            synchronized(HistoryFrame.class) {
                if (historyFrame == null) {
                    historyFrame = new HistoryFrame();
                }
            }
        }

        return historyFrame;
    }

    public void init() {
        int length = this.currentRecords.size();
        this.records = new Object[length][6];
        int index = 0;

        for(Iterator var4 = this.currentRecords.iterator(); var4.hasNext(); ++index) {
            Record r = (Record)var4.next();
            this.records[index][0] = false;
            this.records[index][1] = r.getBlackname();
            this.records[index][2] = r.getWhitename();
            this.records[index][3] = r.getId();
            this.records[index][4] = r.getGamename();
            String winInformation = "????????????";
            if (r.getWin() == 1) {
                winInformation = "???????????????";
            } else if (r.getWin() == 0) {
                winInformation = "???????????????";
            }

            this.records[index][5] = winInformation;
        }

        this.setBounds(0, 0, 776, 556);
        this.setTitle("????????????");
        this.setDefaultCloseOperation(3);
        this.getContentPane().setLayout((LayoutManager)null);
        JButton btnNewButton = new JButton("????????????");
        btnNewButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < HistoryFrame.this.records.length; ++i) {
                    if ((Boolean)HistoryFrame.this.table.getValueAt(i, 0)) {
                        int index = 0;

                        for(Iterator var5 = HistoryFrame.this.currentRecords.iterator(); var5.hasNext(); ++index) {
                            Record r = (Record)var5.next();
                            if (r.getId().equals((String)HistoryFrame.this.table.getValueAt(i, 3))) {
                                ReplayerFunction.showRecordByTime(index);
                                return;
                            }
                        }

                        return;
                    }
                }

            }
        });
        btnNewButton.setBounds(493, 28, 97, 23);
        this.getContentPane().add(btnNewButton);
        final String[] columns = new String[]{"????????????", "?????????", "?????????", "????????????", "????????????", "?????????"};
        ComboBoxModel jComboBoxModel = new DefaultComboBoxModel(new String[]{"?????????", "?????????", "????????????"});
        this.comboBox = new JComboBox(jComboBoxModel);
        this.comboBox.setBounds(148, 27, 66, 25);
        this.getContentPane().add(this.comboBox);
        JButton btnNewButton_1 = new JButton("??????");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                HistoryFrame.this.searchObjects(String.valueOf(HistoryFrame.this.comboBox.getSelectedItem()));
                HistoryFrame.this.table.setModel(new HistoryTablePanel(HistoryFrame.this.records, columns));
                HistoryFrame.this.repaint();
            }
        });
        btnNewButton_1.setBounds(244, 28, 97, 23);
        this.getContentPane().add(btnNewButton_1);
        JButton btnNewButton_2 = new JButton("????????????");
        btnNewButton_2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                HistoryFrame.getHistoryFrame().dispose();
                MenuFrame.getMenuFrame().setVisible(true);
            }
        });
        btnNewButton_2.setBounds(628, 28, 97, 23);
        this.getContentPane().add(btnNewButton_2);
        this.textField = new JTextField();
        this.textField.setBounds(46, 29, 66, 21);
        this.getContentPane().add(this.textField);
        this.textField.setColumns(10);
        this.table = new JTable();
        this.table.setModel(new HistoryTablePanel(this.records, columns));
        DefaultTableCellRenderer r = new DefaultTableCellRenderer();
        r.setHorizontalAlignment(0);
        this.table.setDefaultRenderer(Object.class, r);
        this.table.setSelectionMode(0);
        JScrollPane scrollPane = new JScrollPane(this.table);
        scrollPane.setBounds(46, 76, 681, 433);
        this.getContentPane().add(scrollPane);
        JButton btnNewButton_3 = new JButton("????????????");
        btnNewButton_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for(int i = 0; i < HistoryFrame.this.records.length; ++i) {
                    if ((Boolean)HistoryFrame.this.table.getValueAt(i, 0)) {
                        int index = 0;

                        for(Iterator var5 = HistoryFrame.this.currentRecords.iterator(); var5.hasNext(); ++index) {
                            Record r = (Record)var5.next();
                            if (r.getId().equals((String)HistoryFrame.this.table.getValueAt(i, 3))) {
                                GameResultFrame.getGameResultFrame().getGameResultPanel().setIndex(index);
                                System.out.println("Index = " + GameResultFrame.getGameResultFrame().getGameResultPanel().getIndex());
                                HistoryFrame.getHistoryFrame().dispose();
                                GameResultFrame.getGameResultFrame().setVisible(true);
                                return;
                            }
                        }

                        return;
                    }
                }

            }
        });
        btnNewButton_3.setBounds(386, 28, 97, 23);
        this.getContentPane().add(btnNewButton_3);
        this.setVisible(true);
        this.setResizable(false);
    }

    public ReplayerFrame getReplayerFrame() {
        return this.replayerFrame;
    }

    public void setReplayerFrame(ReplayerFrame replayerFrame) {
        this.replayerFrame = replayerFrame;
    }

    public void searchObjects(String key) {
        int sum = 0;
        String content = this.textField.getText();
        System.out.println(content);
        int index = 0;
        Iterator var6 = this.currentRecords.iterator();

        while(true) {
            Record r;
            label103:
            do {
                while(var6.hasNext()) {
                    r = (Record)var6.next();
                    if (key.equals("?????????")) {
                        continue label103;
                    }

                    if (key.equals("?????????")) {
                        if (r.getWhitename().equals(content) || content.equals("")) {
                            ++sum;
                        }
                    } else if ((key.equals("????????????") || content.equals("")) && content.equals(r.getId())) {
                        ++sum;
                    }
                }

                this.records = new Object[sum][6];
                var6 = this.currentRecords.iterator();

                while(true) {
                    String winInformation;
                    label80:
                    do {
                        while(var6.hasNext()) {
                            r = (Record)var6.next();
                            if (key.equals("?????????")) {
                                continue label80;
                            }

                            if (key.equals("?????????")) {
                                if (r.getWhitename().equals(content) || content.equals("")) {
                                    this.records[index][0] = false;
                                    this.records[index][1] = r.getBlackname();
                                    this.records[index][2] = r.getWhitename();
                                    this.records[index][3] = r.getId();
                                    this.records[index][4] = r.getGamename();
                                    winInformation = "????????????";
                                    if (r.getWin() == 1) {
                                        winInformation = "???????????????";
                                    } else if (r.getWin() == 0) {
                                        winInformation = "???????????????";
                                    }

                                    this.records[index][5] = winInformation;
                                    ++index;
                                }
                            } else if (key.equals("????????????") && (content.equals(r.getId()) || content.equals(""))) {
                                this.records[index][0] = false;
                                this.records[index][1] = r.getBlackname();
                                this.records[index][2] = r.getWhitename();
                                this.records[index][3] = r.getId();
                                this.records[index][4] = r.getGamename();
                                winInformation = "????????????";
                                if (r.getWin() == 1) {
                                    winInformation = "???????????????";
                                } else if (r.getWin() == 0) {
                                    winInformation = "???????????????";
                                }

                                this.records[index][5] = winInformation;
                                ++index;
                            }
                        }

                        if (sum == 0) {
                            JOptionPane.showMessageDialog((Component)null, "???????????????????????????");
                        }

                        return;
                    } while(!r.getBlackname().equals(content) && !content.equals(""));

                    this.records[index][0] = false;
                    this.records[index][1] = r.getBlackname();
                    this.records[index][2] = r.getWhitename();
                    this.records[index][3] = r.getId();
                    this.records[index][4] = r.getGamename();
                    winInformation = "????????????";
                    if (r.getWin() == 1) {
                        winInformation = "???????????????";
                    } else if (r.getWin() == 0) {
                        winInformation = "???????????????";
                    }

                    this.records[index][5] = winInformation;
                    ++index;
                }
            } while(!r.getBlackname().equals(content) && !content.equals(""));

            ++sum;
        }
    }

    public void updateCurrentPlayer() {
        this.currentRecords = LoginAlgorithm.getCurrentPlayer().getRecords();
        System.out.println(LoginAlgorithm.getCurrentPlayer().getName());
        int length = this.currentRecords.size();
        this.records = new Object[length][6];
        int index = 0;

        for(Iterator var4 = this.currentRecords.iterator(); var4.hasNext(); ++index) {
            Record r = (Record)var4.next();
            this.records[index][0] = false;
            this.records[index][1] = r.getBlackname();
            this.records[index][2] = r.getWhitename();
            this.records[index][3] = r.getId();
            this.records[index][4] = r.getGamename();
            String winInformation = "????????????";
            if (r.getWin() == 1) {
                winInformation = "???????????????";
            } else if (r.getWin() == 0) {
                winInformation = "???????????????";
            }

            this.records[index][5] = winInformation;
        }

        String[] columns = new String[]{"????????????", "?????????", "?????????", "????????????"};
        this.table.setModel(new HistoryTablePanel(this.records, columns));
        this.repaint();
    }
}
