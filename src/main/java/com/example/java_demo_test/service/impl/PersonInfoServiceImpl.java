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
		//�x�sid
	    List<String> idList = new ArrayList<>();
	    //�ˬdpersonInfos���Onull�B�Ŷ��X
	    if (CollectionUtils.isEmpty(personInfos)) {
	      return new PersonInfoResponse("�s�W��T���~");
	    }
	    //�ˬdList
	    //���O�Ů�B�Ŧr��BNULL�B����Ÿ�
	    for (PersonInfo personInfo : personInfos) {
	      if (personInfo == null
	              ||!StringUtils.hasText(personInfo.getName())
	              || !StringUtils.hasText(personInfo.getId())
	              || !StringUtils.hasText(personInfo.getCity())) {
	        return new PersonInfoResponse("�s�W��T���~");
	      }

	      if (personInfo.getAge() < 0) {
	        return new PersonInfoResponse("�~�ֿ��~");
	      }
	      idList.add(personInfo.getId());
	    }
	    int count = 0;
	    //�ˬdid�A�O�_���Ʈw�۲�
	    //��粒��s�JfilterIdList
	    List<PersonInfo> filterIdList = personInfoDao.findAllById(idList);
	    //�T�{��粒�s�J�����O�Ŷ��X
	    if (!filterIdList.isEmpty()) {
	      //.removeAll() �簣�w�s�b��Ʈw�����
	      //�ݭn���gequals��hashCode��k
	    count =filterIdList.size();
	      personInfos.removeAll(filterIdList);
	      
	    }
	String message = "";
	    if (count >= 0){
	      message = "�A����"+count+"����ơA�w�簣���Ƹ��";
	    }
	    //��lambda��{�W��
	    //filterIdList.stream().filter(item->idList.contains(item.getId())).collect(Collectors.toList());

	    personInfoDao.saveAll(personInfos);
	    return new PersonInfoResponse(personInfos,"�s�W��T���\"+"�A����"+count+"����ơA�w�簣���Ƹ��");
	  }

	@Override
	public GetPersonInfoResponse getPersonInfoById(String id) {
		//�������b�ˬd
		if(!StringUtils.hasText(id)) {
			return new GetPersonInfoResponse("ID���o����");
		}
		Optional<PersonInfo> op = personInfoDao.findById(id);
		if(!op.isPresent()) {
			return new GetPersonInfoResponse("��Ƥ��s�b");
		}
		return  new GetPersonInfoResponse(op.get(),"���\�P");
	}

	@Override
	public GetPersonInfoResponse getAllPersonInfo() {
		List<PersonInfo>response = new ArrayList<>();
		response = personInfoDao.findAll();
		return new GetPersonInfoResponse(response ,"���\");

	}

	
	public GetPersonInfoResponse findByAgeGreaterThan(int age) {
		List<PersonInfo>findAge=personInfoDao.findByAgeGreaterThan(age);
		return new GetPersonInfoResponse(findAge ,"���\");
	}

	
	@Override
	public GetPersonInfoResponse findByAgeLessThanEqualOrderByAgeAsc(int age) {
		List<PersonInfo>findAge1=personInfoDao.findByAgeLessThanEqualOrderByAgeAsc(age);
		return new GetPersonInfoResponse(findAge1 ,"���\");
		
	}

	
	@Override
	public GetPersonInfoResponse findByAgeBetweenOrderByAgeDesc(int age ,int age1){
		List<PersonInfo>findAge2=personInfoDao.findByAgeBetweenOrderByAgeDesc(age ,age1);
		List<PersonInfo>findAge3 = findAge2.subList(0,3);
		return new GetPersonInfoResponse(findAge3 ,"���\");
	}
	
	
	
	
	
	
	
	
}
	
	
