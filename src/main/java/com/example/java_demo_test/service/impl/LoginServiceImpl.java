package com.example.java_demo_test.service.impl;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Login;
import com.example.java_demo_test.respository.LoginDAO;
import com.example.java_demo_test.service.ifs.LoginService;
import com.example.java_demo_test.vo.LoginResponse;


@Service
public class LoginServiceImpl implements LoginService{
	
	private String loginCheck="(\\w){3,8}";
	//�K�X���� 8 ~12 �X�A�ܤ֭n���@�ӯS��Ÿ������঳�ťաBTab�B�w��B����B�������Ÿ�
	//^    ��ܦr��Ÿ��_�l(�u���b[]���~�O��ܤ��]�A) ; $��ܵ���
	//?��ܷj�M
	//()��ܤ��� ; []��ܽd��
	//?=  ��ܧڤ����w�n���@��,���n���]�t�ڭn������
	// \p{}�N���諸�r�����{�Y��}�S��
	//{punct} ��ܼ��I�Ÿ�
	//.�O�U�βŸ�  ; +�N��X�{�@��h��
	private String pwdCheck="^(?=.*[\\p{Punct}])[\\S]{8,12}$"; 

	
	@Autowired
	private LoginDAO loginDao;

	@Override
	public LoginResponse addInfo(List<Login> login) {
		
			for(Login single_login:login) {
				if(single_login == null || single_login.getAccount() == null || !single_login.getAccount().matches(loginCheck)) {
					return new LoginResponse("���~�b���榡");
				}
				if(single_login == null || single_login.getPwd() == null || !single_login.getPwd().matches(pwdCheck)) {
					return new LoginResponse("���~�K�X�榡");
				}
				single_login.setRegisterTime(LocalDateTime.now());
				single_login.setActive(false);
			}
			
			loginDao.saveAll(login);
			return new LoginResponse("�b�K�s�ئ��\");
		}
		
		@Override
		public LoginResponse activeAccount(List<Login>login) {
			for(Login activeLogin:login) {
				if(!StringUtils.hasText(activeLogin.getAccount())){
					return new LoginResponse("�b����J���~");
				}
				Optional<Login> op = loginDao.findById(activeLogin.getAccount());
				if(!op.isPresent()) {
					return new LoginResponse("�b�����s�b");
				}
				if(!StringUtils.hasText(activeLogin.getPwd())) {
					return new LoginResponse("�K�X��J���~");
				}

				if(op.get().isActive()==true) {
					return new LoginResponse("���b���w���ҹL,�L����������");
				}
				
				Login opGet = op.get();
				opGet.setActive(true);

				
				
				loginDao.save(opGet);
				return new LoginResponse(opGet,"�b�K���T,���Ҧ��\");

				
			}
			
			return new LoginResponse();
		}

		@Override
		public LoginResponse loginAccount(List<Login> login) {
			for(Login loginAccount:login) {
				if(!StringUtils.hasText(loginAccount.getAccount())){
					return new LoginResponse("�b����J���~");
				}
				Optional<Login> op = loginDao.findById(loginAccount.getAccount());
				if(!op.isPresent()) {
					return new LoginResponse("�b�����s�b");
				}
				Login op1= op.get();
				if(!StringUtils.hasText(loginAccount.getPwd())|| !loginAccount.getPwd().equals(op1.getPwd())) {
					return new LoginResponse("�K�X��J���~");
				}
				if(!op1.isActive()==true) {
					return new LoginResponse("���b���w�Q���v/�|������");
				}
		}
			return new LoginResponse("�n�J���\");		
	}

		@Override
			public LoginResponse findByCityContainingOrderByAgeAsc(String str) {
				List<Login>findInfo=loginDao.findByCityContainingOrderByAgeAsc(str);
				
				for(int i =0; i<findInfo.size(); i++) {
					findInfo.get(i).setPwd("");
				}
		
				return new LoginResponse(findInfo,"�ŦX�d�߫������ϥΪ�");
		}

		
		
		
		
}
