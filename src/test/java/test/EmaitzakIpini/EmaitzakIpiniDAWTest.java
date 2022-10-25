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
		Event ev112=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,17), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev112);
		Question q1=ev112.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote112 = q1.addQuote(1.3, "1", q1);
		
		try {
			testDA.open();
			testDA.createEvent(ev112);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote112);
			} catch (EventNotFinished e) {
				Date date = new Date();
				System.out.println(date.toString());
				System.out.println(ev112.getEventDate().toString());
				System.out.println(date.compareTo(ev112.getEventDate()));
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev112);
			testDA.close();
		}
	}

	//Quote-ak ez du apusturik beraz ez da for begiztatan sartuko
	@Test
	public void test3() {
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev113=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev113);
		Question q1=ev113.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote113 = q1.addQuote(1.3, "1", q1);
		
		try {
			testDA.open();
			testDA.createEvent(ev113);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote113);
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev113);
			testDA.close();
		}
	}
	
	//Apustua galduta bezala markatu da
	@Test
	public void test4() {
		Registered reg3 = new Registered("Gotzon", "123", 1111);
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev114=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,9,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev114);
		Question q1=ev114.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote114 = q1.addQuote(1.3, "1", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg3, 5.0);
		Apustua ap1 = new Apustua(apA1, quote114);
		apA1.addApustua(ap1);
		
		try {
			testDA.open();
			testDA.createEvent(ev114);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote114);
				assertEquals("galduta", apA1.getEgoera());
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev114);
			testDA.close();
		}
	}
	
	//Apustua irabazita bezala markatu da
	@Test
	public void test5() {
		Registered reg35 = new Registered("Gotzon", "123", 1111);
		Team team115= new Team("Chelsea");
		Team team215= new Team("Bayern");
		Event ev115=new Event(1, "Chelsea-Bayern", UtilDate.newDate(2022,9,8), team115, team215);
		Sport sp115=new Sport("Futbol");
		sp115.addEvent(ev115);
		ev115.setSport(sp115);
		Question q115=ev115.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote115 = q115.addQuote(1.3, "2", q115);
		ApustuAnitza apA115 = new ApustuAnitza(reg35, 5.0);
		//ApustuAnitza apA2 = new ApustuAnitza(reg3, 3.5);
		Apustua ap115 = new Apustua(apA115, quote115);
		//Apustua ap2 = new Apustua(apA2, quote115);
		apA115.addApustua(ap115);
		/*apA2.addApustua(ap2);
		quote115.addApustua(ap1);
		ap1.eguneratuApustuKant(sp1);
		quote115.addApustua(ap2);
		ap2.eguneratuApustuKant(sp1);*/
		
		try {
			testDA.open();
			testDA.createEvent(ev115);
			/*testDA.beginTransaction();
			testDA.persist(sp115);
			testDA.persist(reg35);
			testDA.persist(quote115);
			testDA.persist(q115);
			testDA.persist(apA115);
			testDA.persist(ap115);
			testDA.persist(team115);
			testDA.persist(team215);
			testDA.commitTransaction();*/
			//quote115.addApustua(ap1);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote115);
				assertEquals("irabazita", apA115.getEgoera());
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
			testDA.removeEvent(ev115);
			testDA.close();
		}
	}

}
