package domain;

import java.util.Iterator;

public interface ExtendedIterator extends Iterator {
		//uneko elementua itzultzen du eta aurrekora pasatzen da
		public Object previous();
		//true aurreko elementua existitzen bada.
		public boolean hasPrevious();
		//Lehendabiziko elementuan kokatzen da.
		public void goFirst();
		//Azkeneko elementuan kokatzen da.
		public void goLast();
}
	
