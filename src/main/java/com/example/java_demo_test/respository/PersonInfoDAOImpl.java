package com.example.java_demo_test.respository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.PersonInfoResponse;

public class PersonInfoDAOImpl extends BaseDAO{


	public List<PersonInfo> doQueryByAge(int age){
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :inputage");
		Map<String , Object>params = new HashMap<>();
		params.put("inputage",age);
		return doQuery(sb.toString(), params, PersonInfo.class);	
	}
	
//	private Logger logger = LoggerFactory.getLogger(getClass()); //slf4j
//	@Override
//	public PersonInfoResponse search() {
//		logger.info("logger by......");		
//		return ;
//	}

	
	public List<PersonInfo> doQueryByAge(int age , int limitSize){
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :inputage");
		Map<String , Object>params = new HashMap<>();
		params.put("inputage",age);
		return doQuery(sb.toString(), params, PersonInfo.class , limitSize);
	}
	
	public List<PersonInfo> doQueryByAge(int age , int limitSize ,  int startPosition){
		StringBuffer sb = new StringBuffer();
		sb.append("select P from PersonInfo P where P.age >= :inputage");
		Map<String , Object>params = new HashMap<>();
		params.put("inputage",age);
		return doQuery(sb.toString(), params, PersonInfo.class , limitSize , startPosition);
	}

	public int doUpdateAgeByName(int age , String name) {
		StringBuffer sb = new StringBuffer();
		sb.append("update PersonInfo set age = :age"
				+ "where  name= :name");
		Map<String , Object>params = new HashMap<>();
		params.put("age",age);
		params.put("name",name);
		return doUpdate(sb.toString(), params);
	}
}
