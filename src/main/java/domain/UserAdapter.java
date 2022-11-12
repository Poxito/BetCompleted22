package domain;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

public class UserAdapter extends AbstractTableModel{

	private String[] columnNames = {"Event","Question","EventDate","Bet(€)"};
	private Vector<ApustuAnitza> apustuak;
	
	public UserAdapter(Vector<ApustuAnitza> apustuak) {
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
		ArrayList<Apustua> apu = new ArrayList<Apustua>();
		for(ApustuAnitza aa : apustuak) {
			for(Apustua a : aa.getApustuak()) {
				apu.add(a);
			}
			
		}
		
		if (columnIndex == 0) {
			return apu.get(rowIndex).getKuota().getQuestion().getEvent().getDescription();
		}else if (columnIndex == 1) {
			return apu.get(rowIndex).getKuota().getQuestion().getQuestion();
		}else if (columnIndex == 2) {
			return apu.get(rowIndex).getKuota().getQuestion().getEvent().getEventDate();
		}
		return apu.get(rowIndex).getKuota().getQuote();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}
	
	public Class getColumnClass(int col) {
		if (col == 3) {
			return Double.class;
		} else {
			return String.class;
		}
	}
	
}
