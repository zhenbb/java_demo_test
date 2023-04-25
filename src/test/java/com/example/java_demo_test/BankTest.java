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
		//�s�W���(save) �� bank �o�i��
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
		System.out.println("�b��:" + bank.getAccount() + "�l�B:" + bank.getAmount());
	}
		

//		Assert.isTrue(bank.getAmount() > 0 , pattern); //�_��,����ƥ�
//		Assert.isTrue(bank.getAccount() .equals("A01") , "�d�L���");
		
		
		@Test
		public void depositTest() {
			
			//�Ыذ����
//			Bank bank = new Bank("AA999" , "AA123456@", 2000);
//			Bank oldBank= bankDao.save(bank);
			//�o��y�]�N���� 
			Bank oldBank= bankDao.save( new Bank("AA999" , "AA123456@", 2000));
		
			//�s��
			Bank newBank = new Bank("AA999" , "AA123456@" , 3000) ; 
			BankResponse response = bankService.deposit(newBank);
		
		
			//�T�{���B���s�i�h
			Bank resBank = response.getBank();
			Assert.isTrue(resBank.getAmount() == (oldBank.getAmount() + newBank.getAmount()), "�s�ڲ��`");
			Assert.isTrue(response.getMessege().equals("�s�ڦ��\"), "�s�ڲ��`");
			//�R�����ժ����
			bankDao.delete(resBank);
	}
		
		
		
		@Test
		public void withdrawTest() {
			//�Ыذ����
			Bank oldBank= bankDao.save( new Bank("AA999" , "AA123456@", 5000));
			
			//����
			Bank newBank = new Bank("AA999" , "AA123456@" , 3000) ; 
			BankResponse response = bankService.withdraw(newBank);
		
			//�T�{���ڪ��B
			Bank resBank = response.getBank();
			Assert.isTrue(resBank.getAmount() == (oldBank.getAmount() - newBank.getAmount()), "���ڲ��`");
			Assert.isTrue(response.getMessege().equals("���ڦ��\"), "���ڲ��`");
			//�R�����ժ����
			bankDao.delete(resBank);
		}
		
		

}
