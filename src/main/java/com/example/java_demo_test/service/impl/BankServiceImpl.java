package com.example.java_demo_test.service.impl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Bank;
import com.example.java_demo_test.respository.BankDAO;
import com.example.java_demo_test.service.ifs.BankService;
import com.example.java_demo_test.vo.BankResponse;

@Service
public class BankServiceImpl implements BankService{
	
	private String accountPattern="(\\w){3,8}";
	private String pwdPattern = "(\\S){8,16}";
	
	@Autowired
	private BankDAO bankDao;

	@Override
	public void addInfo(Bank bank ) {
//		Bank bank = new Bank("A01" , "AA123" , 1000);
//		為了讓資料可以帶入不寫死固定值, 所以放變數在info裡面(本身已經有做好建構方法)
		
		
		//檢查帳號:1.帳號長度:3-8; 2.帳號不能有任何空白; 3. 帳號不能有特殊符號
		//用正規表達式寫
//		if(bank == null || bank.getAccount() == null || !bank.getAccount().matches(accountPattern)) {
//			System.out.println("錯誤格式");
//			return;
//		}
		checkAccount(bank);
		
		//檢查密碼:1.非null; 2.不能有空白; 3.長度8-16; 4.包含一些特殊字元

		//用正規表達式寫
//		if(bank.getPwd() == null || !bank.getPwd().matches(pwdPattern)) {
//			System.out.println("錯誤格式");
//			return;
//		}	
		checkPwd(bank);
		
		
		//檢查餘額:不可為負數
		if(bank.getAmount() <0) {
			System.out.println("錯誤格式");
			return;
		}
		

		//以上檢查沒有問題就新增資料(save) 到 bank 這張表
		bankDao.save(bank);
		
	}
	
	
//	做成私有方法(因為檢查的動作只有在這裡會做使用)
	private void checkAccount(Bank bank) {
		if(bank == null || bank.getAccount() == null || !bank.getAccount().matches(accountPattern)) {
			System.out.println("錯誤格式");
			return;
		}
		
	}
	
	private void checkPwd(Bank bank) {
		if(bank == null || bank.getPwd() == null || !bank.getPwd().matches(pwdPattern)) {
			System.out.println("錯誤格式");
			return;
		}
	}


	@Override
	//這邊是在取資料,跟上面放資料是不同的動作,所以還是要檢查
	public Bank getAmountById(String Id) {
		if(!StringUtils.hasText(Id)) {
			return new Bank();
		}
		//意思一樣
//		if(Id == null || Id.isBlank()) {
//			return new Bank();
//		}
		//Optional是一個容器,幫你判斷到底有沒有東西(如果bank是null就會報錯)
		Optional<Bank> op = bankDao.findById(Id);
		if(!op.isPresent()) {
			//判斷有沒有東西 把他取出來 加!變沒有東西
			return new Bank();
		}
		return op.get();
	}


	@Override
	public BankResponse deposit(Bank bank) { //存款

		if(bank ==null 
				|| !StringUtils.hasText(bank.getAccount())
				|| !StringUtils.hasText(bank.getPwd()) 
				|| bank.getAmount()<=0 ){
			return new BankResponse(new Bank() , "帳號或密碼錯誤");
		}
//		Optional<Bank> op = bankDao.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		Bank resBank = op.get();
		//因為這邊是只有用ID去查詢,沒辦法確定密碼正確,所我我們在做了一個方法(下面方法)去判斷帳密是否正確
		
		Bank resBank = bankDao.findByAccountAndPwd( bank.getAccount(), bank.getPwd());
		if(resBank == null) {
			return new BankResponse(new Bank() , "資料不存在");
		}
		int oldAmount = resBank.getAmount();
		int depositAmount = bank.getAmount();
		int newAmount = oldAmount + depositAmount ;
		
		//設定存款後的餘額[
		resBank.setAmount(newAmount);
//		Bank newBank = bankDao.save(resBank);
//		return newBank;
		return new BankResponse(bankDao.save(resBank) , "存款成功");
	}

	@Override
	public BankResponse withdraw(Bank bank) { //提款
		if(bank ==null 
				|| !StringUtils.hasText(bank.getAccount())
				|| !StringUtils.hasText(bank.getPwd()) 
				|| bank.getAmount()<=0){
			return new BankResponse(new Bank() , "帳號或密碼錯誤");
		}
			Bank resBank = bankDao.findByAccountAndPwd( bank.getAccount(), bank.getPwd());
			if(resBank == null) {
				return new BankResponse(new Bank() , "資料不存在");
			}
			int oldAmount = resBank.getAmount();
			int withdrawAmount = bank.getAmount();
			//判斷提款金額不能大於原有金額
			if(withdrawAmount > oldAmount) {
				return new BankResponse(new Bank() , "餘額不足");
			}
			//原本金額 - 提款金額 = 餘額		
			int newAmount = oldAmount - withdrawAmount ;
			
			//更新提款後的餘額
			resBank.setAmount(newAmount);

			return new BankResponse(bankDao.save(resBank) , "提款成功");
	}
}
