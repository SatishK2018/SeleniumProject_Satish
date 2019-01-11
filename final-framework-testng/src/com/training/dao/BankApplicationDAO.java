package com.training.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.training.bean.GrantLoanBean;
import com.training.bean.LoginBean;
import com.training.connection.GetConnection;
import com.training.utility.LoadDBDetails;

public class BankApplicationDAO {
	
	Properties properties; 
	
	public BankApplicationDAO() {
		 try {
			properties = new Properties();
			FileInputStream inStream = new FileInputStream("./resources/sql.properties");
			properties.load(inStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<GrantLoanBean> getGrantLoan(){
		String sql = properties.getProperty("get.grantLoan"); 
		
		GetConnection gc  = new GetConnection(); 
		List<GrantLoanBean> list = null;
		try {
			gc.ps1 = GetConnection.getMySqlConnection(LoadDBDetails.getDBDetails()).prepareStatement(sql); 
			list = new ArrayList<GrantLoanBean>(); 
			
			gc.rs1 = gc.ps1.executeQuery(); 
			
			while(gc.rs1.next()) {
			
				GrantLoanBean temp = new GrantLoanBean(); 
				temp.setMemberLogin(gc.rs1.getString(1));
				temp.setAmount(gc.rs1.getString(2));
				temp.setDescription(gc.rs1.getString(3));

				list.add(temp); 
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list; 
	}
	
	public static void main(String[] args) {
		new BankApplicationDAO().getGrantLoan().forEach(System.out :: println);
	}
	
	
}
