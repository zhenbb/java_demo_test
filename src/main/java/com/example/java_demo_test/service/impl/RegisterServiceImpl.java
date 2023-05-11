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
			return new RegisterResponse("��J���e���~");
		}
		Register reg = new Register(account ,pwd );
		registerDao.save(reg);
		return new RegisterResponse("�b�K�s�W���\");
	}

	@Override
	public RegisterResponse active(String account, String pwd) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("��J���e���~");
		}
		Register res = registerDao.findByAccountAndPwd(account, pwd);
		if(res==null) {
			return new RegisterResponse("�b�K���s�b");
		}else {
			res.setActive(true);
		}
		registerDao.save(res);
		return new RegisterResponse("�b�K���Ҧ��\");
	}

	@Override
	public RegisterResponse login(String account, String pwd) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("��J���e���~");
		}
		Register res = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if(res==null) {
			return new RegisterResponse("�n�J����");
		}
		return new RegisterResponse("�n�J���\");
	}

	@Override
	public RegisterResponse getRegTime(String account, String pwd) {	
		Register res = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if(res==null) {
			return new RegisterResponse("�Х��n�J");
		}
		return new RegisterResponse(res.getRegTime(),"�n�J���\");
	}

	@Override
	public RegisterResponse getRegTime2(RegisterRequest request, String account, String pwd, Integer verifyCode) {
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("�Х��n�J");
		}
		if(verifyCode == null || verifyCode != request.getVerifyCode()) {
			return new RegisterResponse("���ҽX��J���~");
		}
		Register res = registerDao.findByAccountAndPwdAndActive(account, pwd, true);
		if(res==null) {
			return new RegisterResponse("�Х��n�J");
		}
		return new RegisterResponse(res.getRegTime(),"�n�J���\");
	}
	
	
	
	
	
	
}
