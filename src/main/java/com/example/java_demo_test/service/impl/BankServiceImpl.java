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
//		���F����ƥi�H�a�J���g���T�w��, �ҥH���ܼƦbinfo�̭�(�����w�g�����n�غc��k)
		
		
		//�ˬd�b��:1.�b������:3-8; 2.�b�����঳����ť�; 3. �b�����঳�S��Ÿ�
		//�Υ��W��F���g
//		if(bank == null || bank.getAccount() == null || !bank.getAccount().matches(accountPattern)) {
//			System.out.println("���~�榡");
//			return;
//		}
		checkAccount(bank);
		
		//�ˬd�K�X:1.�Dnull; 2.���঳�ť�; 3.����8-16; 4.�]�t�@�ǯS��r��

		//�Υ��W��F���g
//		if(bank.getPwd() == null || !bank.getPwd().matches(pwdPattern)) {
//			System.out.println("���~�榡");
//			return;
//		}	
		checkPwd(bank);
		
		
		//�ˬd�l�B:���i���t��
		if(bank.getAmount() <0) {
			System.out.println("���~�榡");
			return;
		}
		

		//�H�W�ˬd�S�����D�N�s�W���(save) �� bank �o�i��
		bankDao.save(bank);
		
	}
	
	
//	�����p����k(�]���ˬd���ʧ@�u���b�o�̷|���ϥ�)
	private void checkAccount(Bank bank) {
		if(bank == null || bank.getAccount() == null || !bank.getAccount().matches(accountPattern)) {
			System.out.println("���~�榡");
			return;
		}
		
	}
	
	private void checkPwd(Bank bank) {
		if(bank == null || bank.getPwd() == null || !bank.getPwd().matches(pwdPattern)) {
			System.out.println("���~�榡");
			return;
		}
	}


	@Override
	//�o��O�b�����,��W�����ƬO���P���ʧ@,�ҥH�٬O�n�ˬd
	public Bank getAmountById(String Id) {
		if(!StringUtils.hasText(Id)) {
			return new Bank();
		}
		//�N��@��
//		if(Id == null || Id.isBlank()) {
//			return new Bank();
//		}
		//Optional�O�@�Ӯe��,���A�P�_�쩳���S���F��(�p�Gbank�Onull�N�|����)
		Optional<Bank> op = bankDao.findById(Id);
		if(!op.isPresent()) {
			//�P�_���S���F�� ��L���X�� �[!�ܨS���F��
			return new Bank();
		}
		return op.get();
	}


	@Override
	public BankResponse deposit(Bank bank) { //�s��

		if(bank ==null 
				|| !StringUtils.hasText(bank.getAccount())
				|| !StringUtils.hasText(bank.getPwd()) 
				|| bank.getAmount()<=0 ){
			return new BankResponse(new Bank() , "�b���αK�X���~");
		}
//		Optional<Bank> op = bankDao.findById(bank.getAccount());
//		if(!op.isPresent()) {
//			return new Bank();
//		}
//		Bank resBank = op.get();
		//�]���o��O�u����ID�h�d��,�S��k�T�w�K�X���T,�ҧڧڭ̦b���F�@�Ӥ�k(�U����k)�h�P�_�b�K�O�_���T
		
		Bank resBank = bankDao.findByAccountAndPwd( bank.getAccount(), bank.getPwd());
		if(resBank == null) {
			return new BankResponse(new Bank() , "��Ƥ��s�b");
		}
		int oldAmount = resBank.getAmount();
		int depositAmount = bank.getAmount();
		int newAmount = oldAmount + depositAmount ;
		
		//�]�w�s�ګ᪺�l�B[
		resBank.setAmount(newAmount);
//		Bank newBank = bankDao.save(resBank);
//		return newBank;
		return new BankResponse(bankDao.save(resBank) , "�s�ڦ��\");
	}

	@Override
	public BankResponse withdraw(Bank bank) { //����
		if(bank ==null 
				|| !StringUtils.hasText(bank.getAccount())
				|| !StringUtils.hasText(bank.getPwd()) 
				|| bank.getAmount()<=0){
			return new BankResponse(new Bank() , "�b���αK�X���~");
		}
			Bank resBank = bankDao.findByAccountAndPwd( bank.getAccount(), bank.getPwd());
			if(resBank == null) {
				return new BankResponse(new Bank() , "��Ƥ��s�b");
			}
			int oldAmount = resBank.getAmount();
			int withdrawAmount = bank.getAmount();
			//�P�_���ڪ��B����j��즳���B
			if(withdrawAmount > oldAmount) {
				return new BankResponse(new Bank() , "�l�B����");
			}
			//�쥻���B - ���ڪ��B = �l�B		
			int newAmount = oldAmount - withdrawAmount ;
			
			//��s���ګ᪺�l�B
			resBank.setAmount(newAmount);

			return new BankResponse(bankDao.save(resBank) , "���ڦ��\");
	}
}
