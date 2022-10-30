package test.EmaitzakIpini;

import static org.junit.Assert.*;

import org.junit.runner.RunWith;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import configuration.UtilDate;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import exceptions.EventNotFinished;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class EmaitzakIpiniINTTest {

	@Mock
	TestDataAccess dataAccess;
 
	@InjectMocks
	TestFacadeImplementation sut;
	
	@Test
	public void test1() {
		try {
			sut.EmaitzakIpini(null);
			
			Mockito.verify(dataAccess, Mockito.times(1)).EmaitzakIpini(null);
			
		} catch (Exception e) {
			fail("Parametro bezala sartu den Quote-a ez dago datubasean");
		}
	}

	@Test
	public void test2() {
		try {
			Team team1= new Team("Almeria");
			Team team2= new Team("Athletic");
			Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,10,17), team1, team2);
			Sport sp1=new Sport("Futbol");
			sp1.addEvent(ev111);
			Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
			Quote quote111 = q1.addQuote(1.3, "1", q1);
			
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			
			sut.EmaitzakIpini(quote111);
			
			Mockito.verify(dataAccess, Mockito.times(1)).EmaitzakIpini(quoteCaptor.capture());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test3() {
		
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,10,17), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "1", q1);
		
		ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
		
		try {
			
			dataAccess.open();
			dataAccess.createEvent(ev111);
			dataAccess.close();
			
			sut.EmaitzakIpini(quote111);
			
			Mockito.verify(dataAccess, Mockito.times(1)).EmaitzakIpini(quoteCaptor.capture());
			
		} catch (Exception e) {
			
		}finally {
			dataAccess.open();
			dataAccess.removeEvent(ev111);
			dataAccess.close();
		}
	}
	
	@Test
	public void test4() {
		Registered reg3 = new Registered("Gotzon", "123", 1111);
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,10,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "1", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg3, 5.0);
		Apustua ap1 = new Apustua(apA1, quote111);
		apA1.addApustua(ap1);
		
		try {
			dataAccess.open();
			dataAccess.createEvent(ev111);
			dataAccess.close();
			
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			
			try {
				sut.EmaitzakIpini(quote111);
				
				Mockito.verify(dataAccess, Mockito.times(1)).EmaitzakIpini(quoteCaptor.capture());
				
				assertEquals("galduta", ap1.getEgoera());
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			} catch (NullPointerException e) {
				fail("Ezin izan da egoera eguneratu");
			}
			
		}finally {
			dataAccess.open();
			dataAccess.removeEvent(ev111);
			dataAccess.close();
		}
	}
	
	@Test
	public void test5() {
		Registered reg3 = new Registered("Gotzon", "123", 1111);
		Team team1= new Team("Almeria");
		Team team2= new Team("Athletic");
		Event ev111=new Event(1, "Almeria-Athletic", UtilDate.newDate(2022,10,8), team1, team2);
		Sport sp1=new Sport("Futbol");
		sp1.addEvent(ev111);
		Question q1=ev111.addQuestion("Zeinek irabaziko du partidua?",1);
		Quote quote111 = q1.addQuote(1.3, "2", q1);
		ApustuAnitza apA1 = new ApustuAnitza(reg3, 5.0);
		Apustua ap1 = new Apustua(apA1, quote111);
		apA1.addApustua(ap1);
		
		try {
			dataAccess.open();
			dataAccess.createEvent(ev111);
			dataAccess.close();
			
			ArgumentCaptor<Quote> quoteCaptor = ArgumentCaptor.forClass(Quote.class);
			
			try {
				sut.EmaitzakIpini(quote111);
				
				Mockito.verify(dataAccess, Mockito.times(1)).EmaitzakIpini(quoteCaptor.capture());
				
				assertEquals("irabazita", ap1.getEgoera());
			} catch (EventNotFinished e) {
				fail("Gertaera ez da amaitu oraindik");
			} catch (NullPointerException e) {
				fail("Ezin izan da egoera eguneratu");
			}
			
		}finally {
			dataAccess.open();
			dataAccess.removeEvent(ev111);
			dataAccess.close();
		}
	}
}
