package com.training.dataproviders;

import java.util.List;

import org.testng.annotations.DataProvider;

import com.training.bean.GrantLoanBean;
import com.training.bean.LoginBean;
import com.training.dao.BankApplicationDAO;
import com.training.dao.ELearningDAO;
import com.training.readexcel.ApachePOIExcelRead;

public class GrantLoanDataProvider {

	@DataProvider(name = "GrantLoan_db-inputs")
	public Object [][] getDBData() {

		List<GrantLoanBean> list = new BankApplicationDAO().getGrantLoan(); 
		
		Object[][] result = new Object[list.size()][]; 
		int count = 0; 
		for(GrantLoanBean temp : list){
			Object[]  obj = new Object[3]; 
			obj[0] = temp.getMemberLogin();
			obj[1] = temp.getAmount();
			obj[2] = temp.getDescription();
			
			result[count ++] = obj; 
		}
		
		return result;
	}
	
	
	@DataProvider(name = "GrantLoan_excel-inputs_TC_076")
	public static Object[][] getExcelData(){
		//String fileName = "C:\\Users\\SATISHKALE\\Downloads\\IBM\\Upskill Program - Selenium\\Cyclos Project\\CYTC_076.xlsx";
		String fileName = "./resources/CYTC_076.xlsx";

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
	
	@DataProvider(name = "GrantLoan_excel-inputs_TC_078")
	public static Object[][] getExcelData1(){
		//String fileName = "C:\\Users\\SATISHKALE\\Downloads\\IBM\\Upskill Program - Selenium\\Cyclos Project\\CYTC_076.xlsx";
		String fileName = "./resources/CYTC_078.xlsx";

		List<List<Object>> retVal = ApachePOIExcelRead.getExcelContent(fileName);
		System.out.println("size " + retVal.size());
		
		Object[][] result = new Object[retVal.size()][retVal.size()]; 
		int count = 0; 

		for(List<Object> temp : retVal){
			if(temp!=null){
			Object[]  obj = new Object[4]; 
			System.out.println(temp.get(0));
			System.out.println(temp.get(1));
			System.out.println(temp.get(2));
			System.out.println(temp.get(3));
									
			obj[0] = temp.get(0); 
			obj[1] = temp.get(1); 
			obj[2] = temp.get(2);
			obj[3] = temp.get(3);
							
			result[count ++] = obj; 
			}
		}
		
		return result; 
	}
	
	
}
