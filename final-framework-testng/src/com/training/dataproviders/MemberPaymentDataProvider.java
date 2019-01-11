package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.LoginBean;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;

public class MemberPaymentDataProvider {

	/*@DataProvider(name = "db-inputs")
	public Object [][] getDBData() {

		List<LoginBean> list = new ELearningDAO().getLogins(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(LoginBean temp : list){
			Object[]  obj = new Object[2]; 
			obj[0] = temp.getUserName(); 
			obj[1] = temp.getPassword(); 
			
			result[count ++] = obj; 
		}
		
		
		return result;
	}*/
	
	
	@DataProvider(name = "MemberPayment_excel-inputs_TC_079")
	public static Object[][] getExcelData(){
		//String fileName = "C:\\Users\\SATISHKALE\\Downloads\\IBM\\Upskill Program - Selenium\\Cyclos Project\\CYTC_076.xlsx";
		String fileName = "./resources/CYTC_079.xlsx";

		List<List<Object>> retVal = ApachePOIExcelRead.getExcelContent(fileName);
		System.out.println("size " + retVal.size());
		
		Object[][] result = new Object[retVal.size()][retVal.size()]; 
		int count = 0; 

		for(List<Object> temp : retVal){
			if(temp!=null){
			Object[]  obj = new Object[3]; 
			System.out.println(temp.get(0));
			System.out.println(temp.get(1));
			System.out.println(temp.get(2));

			obj[0] = temp.get(0); 
			obj[1] = temp.get(1); 
			obj[2] = temp.get(2);
			
			result[count ++] = obj; 
			}
		}
		
		return result; 
	}
	
	@DataProvider(name = "MemberPayment_excel-inputs_TC_080")
	public static Object[][] getExcelData1(){
		//String fileName = "C:\\Users\\SATISHKALE\\Downloads\\IBM\\Upskill Program - Selenium\\Cyclos Project\\CYTC_076.xlsx";
		String fileName = "./resources/CYTC_080.xlsx";

		List<List<Object>> retVal = ApachePOIExcelRead.getExcelContent(fileName);
		System.out.println("size " + retVal.size());
		
		Object[][] result = new Object[retVal.size()][retVal.size()]; 
		int count = 0; 

		for(List<Object> temp : retVal){
			if(temp!=null){
			Object[]  obj = new Object[1]; 
			System.out.println(temp.get(0));
			//System.out.println(temp.get(1));
			//System.out.println(temp.get(2));

			obj[0] = temp.get(0); 
			//obj[1] = temp.get(1); 
			//obj[2] = temp.get(2);
			
			result[count ++] = obj; 
			}
		}
		
		return result; 
	}
	
	
}
