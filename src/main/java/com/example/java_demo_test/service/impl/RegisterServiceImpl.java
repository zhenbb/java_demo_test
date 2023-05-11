package com.example.java_demo_test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Register;
import com.example.java_demo_test.respository.RegisterDAO;
import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@Service
public class RegisterServiceImpl implements RegisterService {
	
	@Autowired
	private RegisterDAO registerDao;

	@Override
	public RegisterResponse register(String account, String pwd) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("輸入內容有誤");
		}
		Register reg = new Register(account ,pwd );
		registerDao.save(reg);
		return new RegisterResponse("帳密新增成功");
	}

	@Override
	public RegisterResponse active(String account, String pwd) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("輸入內容有誤");
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if(res==null) {
			return new RegisterResponse("帳密不存在");
		}else {
			res.setActive(true);
		}
		registerDao.save(res);
		return new RegisterResponse("帳密驗證成功");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("輸入內容有誤");
		}
		Register res = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if(res==null) {
			return new RegisterResponse("登入失敗");
		}
		return new RegisterResponse("登入成功");
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {	
		Register res = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if(res==null) {
			return new RegisterResponse("請先登入");
		}
		return new RegisterResponse(res.getRegTime(),"登入成功");
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("請先登入");
		}
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("驗證碼輸入錯誤");
		}
		Register res = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if(res==null) {
			return new RegisterResponse("請先登入");
		}
		return new RegisterResponse(res.getRegTime(),"登入成功");
	}
	
	
	
	
	
	
}
