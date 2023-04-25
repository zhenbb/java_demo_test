package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.respository.BankDAO;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@SpringBootTest
public class BankTest {

	@Autowired
	private BankDAO bankDao;
	
	@Autowired
	private BankService bankService;
	
	private String pattern="(\\w){3,8}";
	

	@Test
	public void addBankInfo() {
		Bank bank = new Bank("A01" , "AA123" , 1000);	
		//新增資料(save) 到 bank 這張表
		bankDao.save(bank);
	}
	
	@Test
	public void bankServiceTest() {

		Bank bank = new Bank(null , "AA123456@" , -2000);		
		bankService.addInfo(bank);
	}
	
	@Test
	public void getAmountByIdTest() {
		Bank bank = bankService.getAmountById("A01");
		System.out.println("帳號:" + bank.getAccount() + "餘額:" + bank.getAmount());
	}
		

//		Assert.isTrue(bank.getAmount() > 0 , pattern); //斷言,比對資料用
//		Assert.isTrue(bank.getAccount() .equals("A01") , "查無資料");
		
		
		@Test
		public void depositTest() {
			
			//創建假資料
//			Bank bank = new Bank("AA999" , "AA123456@", 2000);
//			Bank oldBank= bankDao.save(bank);
			//這兩句也就等於 
			Bank oldBank= bankDao.save( new Bank("AA999" , "AA123456@", 2000));
		
			//存款
			Bank newBank = new Bank("AA999" , "AA123456@" , 3000) ; 
			BankResponse response = bankService.deposit(newBank);
		
		
			//確認金額有存進去
			Bank resBank = response.getBank();
			Assert.isTrue(resBank.getAmount() == (oldBank.getAmount() + newBank.getAmount()), "存款異常");
			Assert.isTrue(response.getMessege().equals("存款成功"), "存款異常");
			//刪除測試的資料
			bankDao.delete(resBank);
	}
		
		
		
		@Test
		public void withdrawTest() {
			//創建假資料
			Bank oldBank= bankDao.save( new Bank("AA999" , "AA123456@", 5000));
			
			//提款
			Bank newBank = new Bank("AA999" , "AA123456@" , 3000) ; 
			BankResponse response = bankService.withdraw(newBank);
		
			//確認提款金額
			Bank resBank = response.getBank();
			Assert.isTrue(resBank.getAmount() == (oldBank.getAmount() - newBank.getAmount()), "提款異常");
			Assert.isTrue(response.getMessege().equals("提款成功"), "提款異常");
			//刪除測試的資料
			bankDao.delete(resBank);
		}
		
		

}
