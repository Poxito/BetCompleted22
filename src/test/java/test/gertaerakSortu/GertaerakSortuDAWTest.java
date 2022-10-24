package test.gertaerakSortu;

//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

//import configuration.ConfigXML;
//import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
//import domain.Question;
import domain.Team;
//import exceptions.EventFinished;
//import exceptions.QuestionAlreadyExist;
//import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class GertaerakSortuDAWTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	//private Event ev;
	private Event event;
	
	@Test//datu baseak ez du sport aurkituko eta false itzuliko du (sop==null).
	public void test1() {
		Team a = new Team("description");
		Team b = new Team("description22");
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			testDA.open();
			Boolean emaitza= testDA.gertaerakSortu("description-description22", date, "pianoa");
			testDA.close();
		   }
	
	
	@Test//Gertaera sortu egingo du description-description2 aurka.
	public void test2() {
		Team a = new Team("Atletico2");
		Team b = new Team("Athletic3");
		
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			testDA.open();
			Boolean emaitza= testDA.gertaerakSortu("Atletico2-Athletic3", date, "Futbol");
			testDA.close();
			event= new Event("description-description2",date,a,b);
		  
			//     testDA.open();
			  //  boolean b1=testDA.removeEvent(event);
			  //  testDA.close();
		        
		   }
	
	@Test//Gertaera sortu egingo du non deskripzioa jadanik DBan dagoena, false itzultzeko.
	public void test3() {
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
			Boolean emaitza= testDA.gertaerakSortu("Atletico-Athletic", date, "Futbol");//Badagoenez false.
			testDA.close();
		   } 
		   }













