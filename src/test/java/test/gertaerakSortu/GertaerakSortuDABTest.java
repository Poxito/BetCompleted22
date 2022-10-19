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
	
	@Test//sport null baldintza egin eta false itzuliko du.
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
			testDA.open();
			Boolean emaitza= testDA.gertaerakSortu("description-description2", date, "pianoa");
			testDA.close();
			assertEquals(emaitza,false);
			 event= new Event("description-description2",date,a,b);
		  
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
			testDA.open();
			Boolean emaitza= testDA.gertaerakSortu("description-description3", date, "Futbol");
			testDA.close();

		//	assertTrue(emaitza!=null);
			assertEquals(emaitza,true);
			 event= new Event("description-description3",date,a,b);
		   } finally {
			  // testDA.open();
		       //  boolean b1=testDA.removeEvent(event);
		        // testDA.close();
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
			testDA.open();
			Boolean emaitza= testDA.gertaerakSortu("Atletico-Athletic", date, "Futbol");
			testDA.close();

			assertEquals(emaitza,false);
		   }
		   
	
@Test
public void test2() {//data gabe false
	Team a = new Team("Barcelona");
	Team b = new Team("Madrid");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		try {
			date = format.parse("");
			testDA.open();
			Boolean emaitza= testDA.gertaerakSortu("Barcelona-Madrid", date, "Futbol");
			testDA.close();
		} catch (ParseException e1) {
			assertTrue(true);
			e1.printStackTrace();
		}
	   }
@Test//Deskripzioa gabe false
public void test5() {
	Team a = new Team("Atletico");
	Team b = new Team("Athletic");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		String s=null;
		try {
			date = format.parse("17/11/2022");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		testDA.open();
		Boolean emaitza= testDA.gertaerakSortu(s, date, "Futbol");
		testDA.close();
		assertEquals(emaitza,false);
	   }
	   
	   
@Test//String hutsa false
public void test4() {
	Team a = new Team("Atletico");
	Team b = new Team("Athletic");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date date=null;
		String s =null;
		try {
			date = format.parse("17/11/2022");
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		testDA.open();
		Boolean emaitza= testDA.gertaerakSortu("Barcelona-Madrid", date,s);
		testDA.close();
		assertEquals(emaitza,null);
	   }
	   
	   }



