package test.EmaitzakIpini;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import exceptions.EventNotFinished;
import test.dataAccess.TestDataAccess;

public class EmaitzakIpiniDAWTest {

	//sut:system under test
	static DataAccess sut = new DataAccess();
		
	//additional operations needed to execute the test 
	static TestDataAccess testDA = new TestDataAccess();
		
	//Sortutako Quote-a ez dago datubasean
	@Test 
	public void test1() {
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,10,17), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "1", q1);
			
		try {
			sut.EmaitzakIpini(quote111);
		} catch (IllegalArgumentException e) {
			fail("Parametro bezala sartu den Quote-a ez dago datubasean");
		} catch (NullPointerException e) {
			fail("Parametro bezala sartu den Quote-a ez dago datubasean");
		} catch (EventNotFinished e) {
			fail("Gertaera amiatu gabe dago");
		}
	}
	
	//Gertaeraren data oraindik ez da igaro, beraz, amaitu gabe dago
	@Test
	public void test2() {
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,17), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "1", q1);
		
		try {
			testDA.open();
			testDA.createEvent(ev111);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote111);
			} catch (EventNotFinished e) {
				Date date = new Date();
				System.out.println(date.toString());
				System.out.println(ev111.getEventDate().toString());
				System.out.println(date.compareTo(ev111.getEventDate()));
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev111);
			testDA.close();
		}
	}

	//Quote-ak ez du apusturik beraz ez da for begiztatan sartuko
	@Test
	public void test3() {
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "1", q1);
		
		try {
			testDA.open();
			testDA.createEvent(ev111);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote111);
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev111);
			testDA.close();
		}
	}
	
	//Apustua galduta bezala markatu da
	@Test
	public void test4() {
		Registered reg3 = new Registered("Gotzon", "123", 1111);
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "1", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg3, 5.0);
		Apustua ap1 = new Apustua(apA1, quote111);
		apA1.addApustua(ap1);
		
		try {
			testDA.open();
			testDA.createEvent(ev111);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote111);
				assertEquals("galduta", apA1.getEgoera());
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev111);
			testDA.close();
		}
	}
	
	//Apustua irabazita bezala markatu da
	@Test
	public void test5() {
		Registered reg3 = new Registered("Gotzon", "123", 1111);
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "2", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg3, 5.0);
		Apustua ap1 = new Apustua(apA1, quote111);
		apA1.addApustua(ap1);
		//quote111.addApustua(ap1);
		
		try {
			testDA.open();
			testDA.createEvent(ev111);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote111);
				assertEquals("irabazita", apA1.getEgoera());
				/*testDA.beginTransaction();
				for(Apustua apu: quote111.getApustuak()) {
					assertEquals("irabazita", apu.getApustuAnitza().getEgoera());
				}
				testDA.commitTransaction();*/
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev111);
			testDA.close();
		}
	}

}
