package test.gertaerakSortu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
//import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Team;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
//import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class GertaerakSortuDABTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	//private Event ev;
	private Event event;
	
	@Test//sport DBan ez dago. baldintza egin eta false itzuliko du.
	public void test6() {
		Team a = new Team("description");
		Team b = new Team("description2");
		
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sut.open(true);
			Boolean emaitza= sut.gertaerakSortu("description-description2", date, "pianoa");
			sut.close();
			assertEquals(emaitza,false);
			 //event= new Event("description-description2",date,a,b);
		  
		   }
	
	@Test//Gertaera sortu eginfo du description-description2 aurka.
	public void test1() {
		Team a = new Team("description");
		Team b = new Team("description3");
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DataAccess.open(true);
			Boolean emaitza= sut.gertaerakSortu("description-description3", date, "Futbol");
			sut.close();

			//assertEquals(emaitza,true);
			 event= new Event("description-description3",date,a,b);
		   } finally {
			   testDA.open();
		         boolean b1=testDA.removeEvent2(event);
		         testDA.close();
		        }
		   }
	@Test//Gertaera sortu egingo dugu non deskripzioa jadanik DBan dagoena false itzultzeko.
	public void test7() {
		Team a = new Team("Atletico");
		Team b = new Team("Athletic");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			DataAccess.open(true);
			Boolean emaitza= sut.gertaerakSortu("Atletico-Athletic", date, "Futbol");
			sut.close();

			assertEquals(emaitza,false);
			
		   } 
		   
	/*
@Test
public void test2() {//data null bada Eventua sortu egingo du date null balioarekin.
	Team a = new Team("Barcelona");
	Team b = new Team("Madrid2");
	try {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		//date = format.parse("");
		DataAccess.open(true);
		Boolean emaitza= sut.gertaerakSortu("Barcelona-Madrid2", null, "Futbol");
		sut.close();
		
	    event= new Event("Barcelona-Madrid2",null,a,b);
	} finally {
	     testDA.open();
         boolean b1=testDA.removeEvent(event);
         testDA.close();
        }
		
	   }*/

@Test//Deskripzioa gabe nullPointerException jaurtiko du, for bilaketan ez duelako inongo deskripziorik aurkituko.
public void test5() {
	Team a = new Team("Atletico");
	Team b = new Team("Athletic");
	try {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		String s=null;
		try {
			date = format.parse("17/11/2022");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		DataAccess.open(true);
		Boolean emaitza= sut.gertaerakSortu(s, date, "Futbol");
		
		
	}catch(NullPointerException e) {
		assertTrue(true);
		e.getMessage();}
	   }
	   
	   
@Test//Sport null bada Illegal argument exceptiona jaurti beharko da.
public void test4() {
	Team a = new Team("Atletico");
	Team b = new Team("Athletic");
	try {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		String s =null;
		try {
			date = format.parse("17/11/2022");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		DataAccess.open(true);
		Boolean emaitza= sut.gertaerakSortu("Barcelona-Madrid", date,null);
		
		
	}catch(IllegalArgumentException e) {
		
		assertTrue(true);
		e.getMessage();
	}
	   }
	   
	   }



