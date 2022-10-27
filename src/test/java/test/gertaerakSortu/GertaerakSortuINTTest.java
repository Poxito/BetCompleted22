package test.gertaerakSortu;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
public class GertaerakSortuINTTest {
	  Event mockedEvent=Mockito.mock(Event.class);
	@Mock
	TestDataAccess dataAccess;

	@InjectMocks
	TestFacadeImplementation sut;
	
	@Test//Hiru parametroak null balioekin konprobatu
	public void test1() {
		try {
			sut.gertaerakSortu(null, null, null);
			
			Mockito.verify(sut, Mockito.times(1)).gertaerakSortu(null, null, null);
			
			fail();
		} catch (Exception e) {
			
		}
	}
	@Test
	public void test2() {//ondo emango du.
		Team a = new Team("description");
		Team b = new Team("description23");
		Sport sport= new Sport("Futbol");
	
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try { 
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Mockito.doReturn(sport).when(mockedEvent).getSport();
			Boolean bo=sut.gertaerakSortu("description-description23", date, "Futbol");
			Mockito.verify(dataAccess, Mockito.times(1)).gertaerakSortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(String.class));
		Event ev = new Event("description-description23",date,a,b);
			dataAccess.open();
			dataAccess.removeEvent2(ev);
			dataAccess.close();
			//assertTrue(bo==true);
		   }
	/*
	@Test //badago
	public void test7() {
		Team a = new Team("Atletico");
		Team b = new Team("Athletic");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			Sport sport= new Sport("piano");
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			Mockito.doReturn(sport).when(mockedEvent).getSport();
			Boolean bo=dataAccess.gertaerakSortu("Atletico-Athletic", date, "piano");
			Mockito.verify(dataAccess, Mockito.times(1)).gertaerakSortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(String.class));

			assertEquals(bo,false);
		   }
		   
	@Test 
	public void test() {
		Team a = new Team("Atletico");
		Team b = new Team("Athletic");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			Sport sport= new Sport("");
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				e1.printStackTrace();
			} 
			Mockito.doReturn(sport).when(mockedEvent).getSport();
			Boolean bo=dataAccess.gertaerakSortu("Atletico-Athletic", date, "");
			Mockito.verify(dataAccess, Mockito.times(1)).gertaerakSortu(Mockito.any(String.class),Mockito.any(Date.class), Mockito.any(String.class));

			assertEquals(bo,false);
		   }
		   */
	
}
