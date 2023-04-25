package com.example.java_demo_test;

import java.util.Arrays;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.respository.NewMenuDAO;

@SpringBootTest(classes =JavaDemoTestApplication.class )
public class NewMenuTest {
	
	@Autowired
	private NewMenuDAO newMenuDao;
	
	@Test
	public void addDataTest() {
//		NewMenu nm1 = new NewMenu("fish","��",100 , UUID.randomUUID());
		NewMenu nm2 = new NewMenu(6,"fish","�N",110);
		newMenuDao.saveAll(Arrays.asList(nm2));
//		newMenuDao.delete(nm1);//�u�|�R���@��,�R���������@�����ެO�ĴX��,���|�����s�W�U�@��(�y�������^�Y)
	}

}
