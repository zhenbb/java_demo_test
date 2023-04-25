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
	//密碼長度 8 ~12 碼，至少要有一個特殊符號但不能有空白、Tab、定位、換行、換頁等符號
	//^    表示字串符號起始(只有在[]中才是表示不包括) ; $表示結尾
	//?表示搜尋
	//()表示分類 ; []表示範圍
	//?=  表示我不指定要哪一個,但要有包含我要的條件的
	// \p{}代表比對的字元具備{某種}特性
	//{punct} 表示標點符號
	//.是萬用符號  ; +代表出現一到多次
	private String pwdCheck="^(?=.*[\\p{Punct}])[\\S]{8,12}$"; 

	
	@Autowired
	private LoginDAO loginDao;

	@Override
	public LoginResponse addInfo(List<Login> login) {
		
			for(Login single_login:login) {
				if(single_login == null || single_login.getAccount() == null || !single_login.getAccount().matches(loginCheck)) {
					return new LoginResponse("錯誤帳號格式");
				}
				if(single_login == null || single_login.getPwd() == null || !single_login.getPwd().matches(pwdCheck)) {
					return new LoginResponse("錯誤密碼格式");
				}
				single_login.setRegisterTime(LocalDateTime.now());
				single_login.setActive(false);
			}
			
			loginDao.saveAll(login);
			return new LoginResponse("帳密新建成功");
		}
		
		@Override
		public LoginResponse activeAccount(List<Login>login) {
			for(Login activeLogin:login) {
				if(!StringUtils.hasText(activeLogin.getAccount())){
					return new LoginResponse("帳號輸入錯誤");
				}
				Optional<Login> op = loginDao.findById(activeLogin.getAccount());
				if(!op.isPresent()) {
					return new LoginResponse("帳號不存在");
				}
				if(!StringUtils.hasText(activeLogin.getPwd())) {
					return new LoginResponse("密碼輸入錯誤");
				}

				if(op.get().isActive()==true) {
					return new LoginResponse("此帳號已驗證過,無須重複驗證");
				}
				
				Login opGet = op.get();
				opGet.setActive(true);

				
				
				loginDao.save(opGet);
				return new LoginResponse(opGet,"帳密正確,驗證成功");

				
			}
			
			return new LoginResponse();
		}

		@Override
		public LoginResponse loginAccount(List<Login> login) {
			for(Login loginAccount:login) {
				if(!StringUtils.hasText(loginAccount.getAccount())){
					return new LoginResponse("帳號輸入錯誤");
				}
				Optional<Login> op = loginDao.findById(loginAccount.getAccount());
				if(!op.isPresent()) {
					return new LoginResponse("帳號不存在");
				}
				Login op1= op.get();
				if(!StringUtils.hasText(loginAccount.getPwd())|| !loginAccount.getPwd().equals(op1.getPwd())) {
					return new LoginResponse("密碼輸入錯誤");
				}
				if(!op1.isActive()==true) {
					return new LoginResponse("此帳號已被停權/尚未驗證");
				}
		}
			return new LoginResponse("登入成功");		
	}

		@Override
			public LoginResponse findByCityContainingOrderByAgeAsc(String str) {
				List<Login>findInfo=loginDao.findByCityContainingOrderByAgeAsc(str);
				
				for(int i =0; i<findInfo.size(); i++) {
					findInfo.get(i).setPwd("");
				}
		
				return new LoginResponse(findInfo,"符合查詢城市之使用者");
		}

		
		
		
		
}
