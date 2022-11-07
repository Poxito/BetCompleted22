package businessLogic;

import domain.ExtendedIterator;

import java.util.Calendar;

import configuration.UtilDate;
import domain.Event;

public class MainIterator {

	public static void main(String[] args) {
		
		boolean isLocal=true;
		 //Facade objektua lortu lehendabiziko ariketa erabiliz
		 BLFacade facadeInterface = Factory.sortuFactory(0);
		 
		 Calendar today = Calendar.getInstance();
		   
		 int month=today.get(Calendar.MONTH);
		 month+=1;
		 int year=today.get(Calendar.YEAR);
		 if (month==12) { month=0; year+=1;}
		 
		 ExtendedIterator<Event> i = facadeInterface.getEvents(UtilDate.newDate(year,month,17));
		 Event ev;
		 
		 System.out.println("Azkenengo eventetik lehenengora");
		 i.goLast();
		 while (i.hasPrevious()){
			 ev = (Event) i.previous();
			 System.out.println(ev.toString());
		 }
		 
		 System.out.println("Lehenengo eventetik azkenengora");
		 //Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
		 i.goFirst();
		 while (i.hasNext()){
			 ev = i.next();
			 System.out.println(ev.toString());
		 }
	}
}
