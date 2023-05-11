package com.example.java_demo_test.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.service.ifs.RegisterService;
import com.example.java_demo_test.vo.RegisterRequest;
import com.example.java_demo_test.vo.RegisterResponse;

@RestController
public class RegisterController {
	
	@Autowired
	private RegisterService registerService;
	
	@PostMapping(value = "register")
	public RegisterResponse register(@RequestBody RegisterRequest request) {
		return registerService.register(request.getAccount(),request.getPwd());
	}
	
	@PostMapping(value = "active")
	public RegisterResponse active(@RequestBody RegisterRequest request) {
		return registerService.active(request.getAccount(),request.getPwd());
	}
	
	@PostMapping(value = "login")
	public RegisterResponse login(@RequestBody RegisterRequest request , HttpSession session) {
		RegisterResponse res =  registerService.login(request.getAccount(),request.getPwd());
		if(res.getMessage().equalsIgnoreCase("�n�J���\")) {    //�p�G�n�J���\�~�|�bsession�̭��s���
			double random = Math.random()*10000; //���@�ӥ|��ƪ��H�����ҽX
			int verifyCode = (int)Math.round(random); //�|�ˤ��J �� �j�ন��Ʀ�
			session.setAttribute("verifyCode", verifyCode); // �s�ڻݭn����ƨ�session�̭�
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
			session.setMaxInactiveInterval(60); // �]�wsessionId�����Įɶ�(��)
			res.setSessionId(session.getId());
			res.setVerifyCode(verifyCode);			
		}
		return res;	
	}
	
	@PostMapping(value = "getRegTime")
	public RegisterResponse getRegTime(@RequestBody RegisterRequest request) {
		return registerService.getRegTime(request.getAccount(),request.getPwd());
	}

	
	@PostMapping(value = "getRegTime1")
	public RegisterResponse getRegTime1(@RequestBody RegisterRequest request ,HttpSession session) { 
		// �]���w�g���\�n�J�F,�ҥH�������ƿ�J�b�K,�����ϥ��x�s�bsession����ƥh�a�J
		String account =(String) session.getAttribute("account");
		String pwd =(String) session.getAttribute("pwd");
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("�Х��n�J");
		}
		Integer verifyCode = (Integer)session.getAttribute("verifyCode"); //�p�G�o��verifyCode�Onull����,object�L�k�j�নint,�u���নinteger
		if(verifyCode == null || verifyCode != request.getVerifyCode()) { //�p�G�S����login�άO�w�g�W�L���Įɶ�(�W���]�w���Q��),�N�|�X�{null
			return new RegisterResponse("���ҽX��J���~");
		}
		return registerService.getRegTime(account,pwd);
	}
	
	
	//��ĳ�N�P�_���F�賣���serviceImpl�h��,�ҥH�N�W������g���@�h�P�_(controller�V��¶V�n)
	@PostMapping(value = "getRegTime2")
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest request ,HttpSession session) { 

		String account =(String) session.getAttribute("account");
		String pwd =(String) session.getAttribute("pwd");
		Integer verifyCode = (Integer)session.getAttribute("verifyCode"); 
		return registerService.getRegTime2(request,account,pwd,verifyCode);
	}

}
