package domain;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class UserAdapter extends AbstractTableModel{

	private String[] columnNames = {"Event","Question","EventDate","Bet(€)"};
	private Vector<Apustua> apustuak;
	
	public UserAdapter(Vector<Apustua> apustuak) {
		this.apustuak = apustuak;
	}
	
	@Override
	public int getRowCount() {
		int size;
	    if (apustuak == null) {
	       size = 0;
	    }
	    else {
	       size = apustuak.size();
	    }
	    return size;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		if (columnIndex == 0) {
			return apustuak.get(rowIndex).getKuota().getQuestion().getEvent();
		}else if (columnIndex == 1) {
			return apustuak.get(rowIndex).getKuota().getQuestion();
		}else if (columnIndex == 2) {
			return apustuak.get(rowIndex).getKuota().getQuestion().getEvent().getEventDate();
		}
		return apustuak.get(rowIndex).getKuota().getQuote();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int col) {
		if (col == 2) {
			return Double.class;
		} else {
			return String.class;
		}
	}
	
}
