package com.example.java_demo_test.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.PersonInfo;
import com.example.java_demo_test.vo.GetPersonInfoResponse;

@Repository
public interface PersonInfoDAO extends JpaRepository<PersonInfo , String>{
	
	
	//��X�~���j���J���󪺩Ҧ��ӤH��T 
	public List<PersonInfo> findByAgeGreaterThan(int age);
	//��X�~���p�󵥩��J���󪺩Ҧ��ӤH��T,�̦~�֥Ѥp��j�Ƨ�
	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	//���W��ASC �����DESC
	//���~������2�ӼƦr(���]�t)��������T,�H�~�֥Ѥj��p�Ƨ�,�u���e3�����
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int age ,int age1);
	public List<PersonInfo> findByAgeBetweenOrderByAgeDesc(int age ,int age1);
	//���~���p��Y�ƩάO�~���j��Y�ƪ���T(��������A�Ϊ̺�������B,��Or)
	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age ,int age1);
	//���o city �]�t�Y�ӯS�w�r���Ҧ��ӤH��T(�ҽk�j�M)
	public List<PersonInfo> findByCityContaining(String str);//'%�x%'
	public List<PersonInfo> findByCityLike(String str);//'�x'
	//	public GetPersonInfoResponse findByCityLike(String str) {
	//personInfoDao.findByCityLike("%"+str+"%");

	//��X�~���j���J����H��city �]�t�Y�ӯS�w�r���Ҧ��H��T,�̷Ӧ~�֥Ѥj��p�Ƨ�
	public List<PersonInfo> findByAgeGreaterThanAndCityContainingOrderByAgeDesc(int age ,String str);
	
	
	
	

}
