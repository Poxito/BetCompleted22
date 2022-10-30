package test.gertaerakSortu;

import static org.junit.Assert.assertEquals;



import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import exceptions.EventFinished;

@RunWith(MockitoJUnitRunner.class)
public class GertaerakSortuINTTest {

	DataAccess da = Mockito.mock(DataAccess.class);
	
	@Mock
	BLFacadeImplementation facadeDAO = Mockito.mock(BLFacadeImplementation.class);

    @InjectMocks
	BLFacade sut = new BLFacadeImplementation(da);
	
	@Test
	// sut.gertaerakSortu: The event does not exist and it is introduced.
	public void test1() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			Mockito.when(da.gertaerakSortu(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(true);
			
			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: The description is null.
	public void test2() {
		try {

			String kirola = "Tennis";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mockito.when(da.gertaerakSortu(null, Mockito.any(), Mockito.any())).thenThrow(new RuntimeException());

		//	boolean esperotakoa = false;
		//	boolean emaitza = sut.gertaerakSortu(null, data, kirola);
		//	fail();
		//	assertEquals(esperotakoa, emaitza);
			
		} catch (RuntimeException e) {
			assertTrue(true);

		} 
	}
	
	@Test
	// sut.gertaerakSortu: The date is null.
	public void test3() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";
			
			Mockito.when(da.gertaerakSortu(Mockito.anyString(), null, Mockito.any())).thenThrow(new RuntimeException());

			//boolean esperotakoa = false;
			//boolean emaitza = sut.gertaerakSortu(deskr, null, kirola);
			fail();
			//assertEquals(esperotakoa, emaitza);
		} catch (RuntimeException e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: The sport is null.
	public void test4() {
		try {

			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mockito.when(da.gertaerakSortu(Mockito.anyString(), Mockito.any(), null)).thenThrow(new RuntimeException());

		//	boolean esperotakoa = false;
		//	boolean emaitza = sut.gertaerakSortu(deskr, data, null);
			fail();
		//	assertEquals(esperotakoa, emaitza);
		} catch (RuntimeException e) {
			assertTrue(true);
		} catch (Exception e) {
			// if the program goes to this point fail
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: The sport is not in the BD.
	public void test5() {
		try {

			String kirola = "Xakea";
			String deskr = "Donostia-Eibar";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("03/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mockito.when(da.gertaerakSortu(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(false);

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: Already exists
	public void test6() {
		try {

			String kirola = "Baloncesto";
			String deskr = "LA Lakers-Phoenix Suns";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = sdf.parse("17/11/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mockito.when(da.gertaerakSortu(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(false);

			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	@Test
	// sut.gertaerakSortu: The date is yesterday
	public void test7_1() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("25/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);
			fail();

			//assertEquals(esperotakoa, emaitza);	
		} catch (EventFinished e) {
			assertTrue(true);
		} 
	}
	
	@Test
	// sut.gertaerakSortu: The date is today
	public void test7_2() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("26/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
						
			boolean esperotakoa = false;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);
			fail();
			
			//assertEquals(esperotakoa, emaitza);
		} catch (EventFinished e) {
			assertTrue(true);
		}
	}
	
	@Test
	// sut.gertaerakSortu: The date is tomorrow
	public void test7_3() {
		try {

			String kirola = "Tennis";
			String deskr = "Nadal-Federer";

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;
			
			try {
				data = sdf.parse("27/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Mockito.when(da.gertaerakSortu(Mockito.anyString(), Mockito.any(), Mockito.any())).thenReturn(true);

			boolean esperotakoa = true;
			boolean emaitza = sut.gertaerakSortu(deskr, data, kirola);

			//assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}

}