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
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev112);
			testDA.close();
		}
	}

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
	
	@Test
	public void test4() {
		Registered reg3 = new Registered("markel", "123", 1111);
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev114=new Event(1, "Chelsea-Bayern", UtilDate.newDate(2022,9,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev114);
		Question q114=ev114.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote114 = q114.addQuote(1.3, "1", q114);
		ApustuAnitza apA114 = new ApustuAnitza(reg3, 5.0);
		Apustua ap114 = new Apustua(apA114, quote114);
		apA114.addApustua(ap114);
		quote114.addApustua(ap114);
		try {
			testDA.open();
			testDA.createEvent(ev114);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote114);
				assertEquals("galduta", apA114.getEgoera());
		
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev114);
			testDA.close();
		}
	}
	
	@Test
	public void test5() {
		Registered reg35 = new Registered("Gotzon", "123", 1111);
		Registered reg3 = new Registered("markel", "123", 1111);
		Team team115= new Team("Chelsea");
		Team team215= new Team("Bayern");
		Event ev115=new Event(1, "Chelsea-Bayern", UtilDate.newDate(2022,9,8), team115, team215);
		Sport sp115=new Sport("Futbol");
		sp115.addEvent(ev115);
		ev115.setSport(sp115);
		Question q115=ev115.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote115 = q115.addQuote(1.3, "1", q115);
		Quote quote116 = q115.addQuote(1.3, "2", q115);
		ApustuAnitza apA115 = new ApustuAnitza(reg3, 5.0);
		Apustua ap115 = new Apustua(apA115, quote115);
		Apustua ap116 = new Apustua(apA115, quote116);
		apA115.addApustua(ap115);
		apA115.addApustua(ap116);
		quote115.addApustua(ap115);
		quote115.addApustua(ap116);
		
		try {
			testDA.open();
			testDA.createEvent(ev115);
			testDA.close();
			
			try {
				System.out.println(quote115);
				sut.EmaitzakIpini(quote115);
				//assertEquals("irabazita", apA115.getEgoera());
				testDA.open();
				testDA.beginTransaction();
				for(Apustua apu: quote116.getApustuak()) {
					//assertEquals("irabazita", apu.getApustuAnitza().getEgoera());
					assertEquals("irabazita", apu.getApustuAnitza().getEgoera());
				}
				testDA.commitTransaction();
				testDA.close();
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev115);
			testDA.close();
		}
	}
	
	@Test
	public void test7() {
		Team team1= new Team("Chelsea");
		Team team2= new Team("Bayern");
		Event ev117=new Event(1, "Chelsea-Bayern", UtilDate.newDate(2021,10,30), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev117);
		Question q117=ev117.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote117 = q117.addQuote(1.3, "1", q117);
		
		try {
			testDA.open();
			testDA.createEvent(ev117);
			testDA.close();
			
			try {
				sut.EmaitzakIpini(quote117);
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			}
			
		}finally {
			testDA.open();
			testDA.removeEvent(ev117);
			testDA.close();
		}
	}

}
