package domain;

import java.util.Vector;

public class EventIterator<Event> implements ExtendedIterator<Event> {
	private Vector<Event> lista;//Eventu lista
	private int posizioa;
	
	
	public EventIterator(Vector<Event> lista) {
		this.lista = lista;
		this.posizioa=0;
	}

	public Vector<Event> getEventList() {
		return this.lista;
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
	public Event next() {
		Event e = lista.get(posizioa);
		this.posizioa++;
		return e;
	}

	@Override//?
	public Event previous() {
		Event e = lista.get(posizioa);
		posizioa--;
		return e;
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
