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

public class GertaerakSortuDAWTest {//Eguneratzen da

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	//private Event ev;
	private Event event;
	

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
			//Event event= new Event("Proba-Proba2",date,a,b);
		//testDA.open();
		//testDA.createEvent(event);
		//testDA.close();
		DataAccess.open(true);
			Boolean emaitza= sut.gertaerakSortu("Atletico-Athletic", date, "Futbol");//Badagoenez false.
		sut.close();
		//testDA.open();
        //testDA.removeEvent(event);
        //testDA.close();
		   
	}
	
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
			Event event= new Event("description-description22",date,a,b);
			testDA.open();
			testDA.createEvent(event);
			testDA.close();
			DataAccess.open(true);
			sut.gertaerakSortu("description-description22", date, "pianoa");
			sut.close();
			testDA.open();
	        testDA.removeEvent2(event);
	        testDA.close();
		   }
	
	
	
	@Test//Gertaera sortu egingo du description-description2 aurka.
	public void test2() {
		Team a = new Team("Atletico3");
		Team b = new Team("Athletic3");
		
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date date=null;
			try {
				date = format.parse("17/11/2022");
			} catch (ParseException e1) {
				e1.printStackTrace();
			}
			
			DataAccess.open(true);
			Boolean emaitza= sut.gertaerakSortu("Atletico3-Athletic3", date, "Futbol");
			sut.close();
			
			event= new Event("Atletico3-Athletic3",date,a,b);//id bat gehitu exekutatu egitean.
			
			testDA.open();
	        testDA.removeEvent2(event);
	        testDA.close();
	
		   }
	

		   }













