package domain;

import java.util.Vector;

public class EventIterator implements ExtendedIterator {
	private Vector<Event> lista;//Eventu lista
	private int posizioa;
	
	
	public EventIterator(Vector<Event> lista) {
		this.lista = lista;
		this.posizioa=0;
	}

	@Override
	public boolean hasNext() {
		if(this.posizioa <= this.lista.size()-1) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public Object next() {
		this.posizioa++;
		return this.lista.get(this.posizioa-1);
	}

	@Override//?
	public Object previous() {
		this.posizioa--;
		return this.lista.get(this.posizioa-1);
	}

	@Override
	public boolean hasPrevious() {
		if(this.posizioa >= 0) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public void goFirst() {
		this.posizioa=0;
		
	}

	@Override
	public void goLast() {
		this.posizioa=this.lista.size()-1;
		
	}

}
