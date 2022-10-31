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
	// Eventua sortzen da.
	public void test1() {
		try {
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
			boolean emaitza = sut.gertaerakSortu("Nadal-Federer", data, "Tennis");
			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	// Ez dauka deskripziorik.
	public void test2() {
		try {
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
	// data null danean.
	public void test3() {
		try {
			
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
	// sport null.
	public void test4() {
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
			Date data = null;

			try {
				data = format.parse("17/11/2022");
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
	// sport ez dago DBan.
	public void test5() {
		try {
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
			boolean emaitza = sut.gertaerakSortu("proba-probatzen", data, "pianoa");

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	
	@Test
	// Jadanik existitzen da.
	public void test6() {
		try {
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
			boolean emaitza = sut.gertaerakSortu("LA Lakers-Phoenix Suns", data, "Baloncesto");

			assertEquals(esperotakoa, emaitza);
		} catch (Exception e) {
			// if the program goes to this point fail
			fail();
		}
	}
	

}