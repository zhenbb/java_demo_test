package com.example.java_demo_test.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.entity.Menu;
import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.respository.PersonInfoDAO;
import com.example.java_demo_test.service.ifs.PersonInfoService;
import com.example.java_demo_test.vo.GetPersonInfoResponse;
import com.example.java_demo_test.vo.OrderResponse;
import com.example.java_demo_test.vo.PersonInfoResponse;

@Service
public class PersonInfoServiceImpl implements PersonInfoService{
	
	@Autowired
	PersonInfoDAO personInfoDao;

	@Override
	public PersonInfoResponse addPersonInfo(List<PersonInfo> personInfos) {
		//儲存id
	    List<String> idList = new ArrayList<>();
	    //檢查personInfos不是null、空集合
	    if (CollectionUtils.isEmpty(personInfos)) {
	      return new PersonInfoResponse("新增資訊錯誤");
	    }
	    //檢查List
	    //不是空格、空字串、NULL、換行符號
	    for (PersonInfo personInfo : personInfos) {
	      if (personInfo == null
	              ||!StringUtils.hasText(personInfo.getName())
	              || !StringUtils.hasText(personInfo.getId())
	              || !StringUtils.hasText(personInfo.getCity())) {
	        return new PersonInfoResponse("新增資訊錯誤");
	      }

	      if (personInfo.getAge() < 0) {
	        return new PersonInfoResponse("年齡錯誤");
	      }
	      idList.add(personInfo.getId());
	    }
	    int count = 0;
	    //檢查id，是否跟資料庫相符
	    //比對完後存入filterIdList
	    List<PersonInfo> filterIdList = personInfoDao.findAllById(idList);
	    //確認比對完存入的不是空集合
	    if (!filterIdList.isEmpty()) {
	      //.removeAll() 剔除已存在資料庫的資料
	      //需要重寫equals跟hashCode方法
	    count =filterIdList.size();
	      personInfos.removeAll(filterIdList);
	      
	    }
	String message = "";
	    if (count >= 0){
	      message = "，重複"+count+"筆資料，已剔除重複資料";
	    }
	    //用lambda實現上面
	    //filterIdList.stream().filter(item->idList.contains(item.getId())).collect(Collectors.toList());

	    personInfoDao.saveAll(personInfos);
	    return new PersonInfoResponse(personInfos,"新增資訊成功"+"，重複"+count+"筆資料，已剔除重複資料");
	  }

	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		//先做防呆檢查
		if(!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("ID不得為空");
		}
		Optional<PersonInfo> op = personInfoDao.findById(id);
		if(!op.isPresent()) {
			return new GetPersonInfoResponse("資料不存在");
		}
		return  new GetPersonInfoResponse(op.get(),"成功嚕");
	}

	@Override
	public GetPersonInfoResponse getAllPersonInfo() {
		List<PersonInfo>response = new ArrayList<>();
		response = personInfoDao.findAll();
		return new GetPersonInfoResponse(response ,"成功");

	}

	
	public GetPersonInfoResponse findByAgeGreaterThan(int age) {
		List<PersonInfo>findAge=personInfoDao.findByAgeGreaterThan(age);
		return new GetPersonInfoResponse(findAge ,"成功");
	}

	
	@Override
	public GetPersonInfoResponse findByAgeLessThanEqualOrderByAgeAsc(int age) {
		List<PersonInfo>findAge1=personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		return new GetPersonInfoResponse(findAge1 ,"成功");
		
	}

	
	@Override
	public GetPersonInfoResponse findByAgeBetweenOrderByAgeDesc(int age ,int age1){
		List<PersonInfo>findAge2=personInfoDao.findByAgeBetweenOrderByAgeDesc(age ,age1);
		List<PersonInfo>findAge3 = findAge2.subList(0,3);
		return new GetPersonInfoResponse(findAge3 ,"成功");
	}
	
	
	
	
	
	
	
	
}
	
	
