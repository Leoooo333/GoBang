package com.cjm.gobang.panel;

import javax.swing.table.AbstractTableModel;

public class HistoryTablePanel extends AbstractTableModel {
    private String[] colNames;
    private Object[][] datas;
    private static final long serialVersionUID = -6654375615177673608L;

    public HistoryTablePanel() {
    }

    public HistoryTablePanel(Object[][] datas, String[] colNames) {
        this.colNames = colNames;
        this.datas = datas;
    }

    public HistoryTablePanel(String[] colNames, Object[][] datas) {
        this.colNames = colNames;
        this.datas = datas;
    }

    public Class<?> getColumnClass(int arg0) {
        return this.datas[0][arg0].getClass();
    }

    public int getColumnCount() {
        return this.colNames.length;
    }

    public String getColumnName(int arg0) {
        return this.colNames[arg0];
    }

    public int getRowCount() {
        return this.datas.length;
    }

    public Object getValueAt(int arg0, int arg1) {
        return this.datas[arg0][arg1];
    }

    public boolean isCellEditable(int arg0, int arg1) {
        return arg1 != 1;
    }

    public void setValueAt(Object arg0, int arg1, int arg2) {
        this.datas[arg1][arg2] = arg0;
    }
}
