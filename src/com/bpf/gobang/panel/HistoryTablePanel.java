package com.bpf.gobang.panel;

import javax.swing.table.AbstractTableModel;

public class HistoryTablePanel extends AbstractTableModel{
	private String[] colNames;
	private Object[][] datas;
	private static final long serialVersionUID = -6654375615177673608L;
	
	// 空构造不接收任何参数
		public HistoryTablePanel() {

		}

		// 重载
		public HistoryTablePanel(Object[][] datas, String[] colNames) {
			this.colNames = colNames;
			this.datas = datas;
		}

		public HistoryTablePanel(String[] colNames, Object[][] datas) {
			this.colNames = colNames;
			this.datas = datas;
		}
		/**
		 * 获取表的单元格的列的类型
		 */
		public Class<?> getColumnClass(int arg0) {
			// 标题 列索引 类型
			return datas[0][arg0].getClass();
		}

		/**
		 * @see获取表的列的数量
		 */
		public int getColumnCount() {
			return colNames.length;
		}

		/**
		 * @see获得列名()
		 */
		public String getColumnName(int arg0) {
			return colNames[arg0];
		}

		/**
		 * @see获取表的行的数量
		 */
		public int getRowCount() {
			return datas.length;
		}

		/**
		 * @see此处的方法是为将来的集合或者文件流读取过程
		 * @see如果是从数据库中读数据，写在此处
		 * @return返回初始化的模板
		 */
		// private Template[] getTemps() {
		// Template[] temps = new Template[3];
		// temps[0] = new Template(1, "模板一", "A");
		// temps[0] = new Template(2, "模板二", "B");
		// temps[0] = new Template(3, "模板三", "C");
		// return temps;
		// }

		/**
		 * @param行
		 * @param列
		 * @see获取表的单元格的值
		 */
		public Object getValueAt(int arg0, int arg1) {
			return datas[arg0][arg1];
		}

		/**
		 * @see确定表中的单元格（cell）是否能编辑（鼠标双击和改变）
		 * @return某一列不可编辑返回假，某一列可编辑返回真
		 */
		public boolean isCellEditable(int arg0, int arg1) {
			// ID不可编辑
			if (arg1 == 1) {// 编号不可编辑，返回假
				return false;
			} else {
				return true;// 可编辑
			}
		}

		public void setValueAt(Object arg0, int arg1, int arg2) {
			datas[arg1][arg2] = arg0;
		}
}
