package com.honey.core.types;

import com.honey.core.types.Vendor;
import com.honey.core.types.Vendor.VendorFactory;

import junit.framework.TestCase;

public class TestVendor extends TestCase {

	public void test1(){
		Vendor v= new Vendor("MYsql",5,4);
			
		assertEquals(VendorFactory.MYSQL, v.getVendor());
		assertEquals(5, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test2(){
		Vendor v= new Vendor("MYsql-45.4.25",5,4);
		assertEquals(VendorFactory.MYSQL, v.getVendor());
		assertEquals(5, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test3(){
		Vendor v= new Vendor("Oracle9i",9);
		assertEquals(VendorFactory.ORACLE, v.getVendor());
		assertEquals(9, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
	public void test4(){
		Vendor v= new Vendor("Oracle13",5,4);
		assertEquals(VendorFactory.ORACLE, v.getVendor());
		assertEquals(5, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	
	public void test5(){
		Vendor v= new Vendor("SQL server 2000",1);
		assertEquals(VendorFactory.MS_SQL_SERVER, v.getVendor());
		assertEquals(1, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
	public void test6(){
		Vendor v= new Vendor("SQL server 2005",5,4);
		assertEquals(VendorFactory.MS_SQL_SERVER, v.getVendor());
		assertEquals(5, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test7(){
		Vendor v= new Vendor("Postgresql",7);
		assertEquals(VendorFactory.POSTGRE, v.getVendor());
		assertEquals(7, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
	public void test8(){
		Vendor v= new Vendor("Postgre sql",9,7);
		assertEquals(VendorFactory.POSTGRE, v.getVendor());
		assertEquals(9, v.getMajorVersion());
		assertEquals(7, v.getMinorVersion());
	}
	
	
	public void test9(){
		Vendor v= new Vendor("db2",7);
		assertEquals(VendorFactory.DB2, v.getVendor());
		assertEquals(7, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
	public void test10(){
		Vendor v= new Vendor("HQL",9,7);
		assertEquals(VendorFactory.HSQL, v.getVendor());
		assertEquals(9, v.getMajorVersion());
		assertEquals(7, v.getMinorVersion());
	}
	public void test11(){
		Vendor v= new Vendor("sybase",9,7);
		assertEquals(VendorFactory.SYBASE, v.getVendor());
		assertEquals(9, v.getMajorVersion());
		assertEquals(7, v.getMinorVersion());
	}
	
	
	public void test12(){
		Vendor v= new Vendor("sqlite",0,4);
		assertEquals(VendorFactory.SQLITE, v.getVendor());
		assertEquals(0, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test13(){
		Vendor v= new Vendor("DERBY",10,4);
		assertEquals(VendorFactory.DERBY, v.getVendor());
		assertEquals(10, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	public void test14(){
		Vendor v= new Vendor("informix",10,4);
		assertEquals(VendorFactory.INFORMIX, v.getVendor());
		assertEquals(10, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test15(){
		Vendor v= new Vendor("firebird",10,4);
		assertEquals(VendorFactory.FIREBIRD, v.getVendor());
		assertEquals(10, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test16(){
		Vendor v= new Vendor("h2",10,4);
		assertEquals(VendorFactory.H2, v.getVendor());
		assertEquals(10, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test17(){
		Vendor v= new Vendor(" ms access",10,4);
		assertEquals(VendorFactory.ACCESS, v.getVendor());
		assertEquals(10, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	public void test18(){
		Vendor v= new Vendor(" SQL92",10,4);
		assertEquals(VendorFactory.SQL92, v.getVendor());
		assertEquals(10, v.getMajorVersion());
		assertEquals(4, v.getMinorVersion());
	}
	
	public void test19(){
		Vendor v= new Vendor(" SQL-2",0,0);
		assertEquals(VendorFactory.SQL92, v.getVendor());
		assertEquals(0, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
	public void test20(){
		Vendor v= new Vendor(" SQL2",0,0);
		assertEquals(VendorFactory.SQL92, v.getVendor());
		assertEquals(0, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
	
	public void test21(){
		Vendor v= new Vendor("myDatabase",0,0);
		assertEquals(VendorFactory.OTHER, v.getVendor());
		assertEquals(0, v.getMajorVersion());
		assertEquals(0, v.getMinorVersion());
	}
	
}
