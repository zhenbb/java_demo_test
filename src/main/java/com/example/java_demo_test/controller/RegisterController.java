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
		if(res.getMessage().equalsIgnoreCase("登入成功")) {    //如果登入成功才會在session裡面存資料
			double random = Math.random()*10000; //做一個四位數的隨機驗證碼
			int verifyCode = (int)Math.round(random); //四捨五入 及 強轉成整數位
			session.setAttribute("verifyCode", verifyCode); // 存我需要的資料到session裡面
			session.setAttribute("account", request.getAccount());
			session.setAttribute("pwd", request.getPwd());
			session.setMaxInactiveInterval(60); // 設定sessionId的有效時間(秒)
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
		// 因為已經成功登入了,所以不必重複輸入帳密,直接使用儲存在session的資料去帶入
		String account =(String) session.getAttribute("account");
		String pwd =(String) session.getAttribute("pwd");
		if(!StringUtils.hasText(account)|| !StringUtils.hasText(pwd)) {
			return new RegisterResponse("請先登入");
		}
		Integer verifyCode = (Integer)session.getAttribute("verifyCode"); //如果這邊verifyCode是null的話,object無法強轉成int,只能轉成integer
		if(verifyCode == null || verifyCode != request.getVerifyCode()) { //如果沒有先login或是已經超過有效時間(上面設定六十秒),就會出現null
			return new RegisterResponse("驗證碼輸入錯誤");
		}
		return registerService.getRegTime(account,pwd);
	}
	
	
	//建議將判斷的東西都放到serviceImpl去做,所以將上面的改寫到實作去判斷(controller越單純越好)
	@PostMapping(value = "getRegTime2")
	public RegisterResponse getRegTime2(@RequestBody RegisterRequest request ,HttpSession session) { 

		String account =(String) session.getAttribute("account");
		String pwd =(String) session.getAttribute("pwd");
		Integer verifyCode = (Integer)session.getAttribute("verifyCode"); 
		return registerService.getRegTime2(request,account,pwd,verifyCode);
	}

}
