package com.example.java_demo_test.respository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	
	
	public List<PersonInfo> doQueryByAge(int age);
	public List<PersonInfo> doQueryByAge(int age,int limitSize);
	public List<PersonInfo> doQueryByAge(int age,int limitSize, int startPosition);
	

	
	
	@Transactional
	public int doUpdateAgeByName(int age , String name);
	
	@Transactional
	@Modifying
	@Query("update PersonInfo  p set p.id = :newId,  p.name = :newName,"
						+ " p.age = :newAge, p.city = :newCity where p.id = :newId" )
	//�n�O�o��where���w��m,���M�Ҧ���Ƴ��|�Q����
	//:�۩w�q���Ѽ� �n�O�o:��W�r�������঳�ť�(:�ǰt�Ϊ�)
	//update...set�i�H�u�g"�n�諸���",���Υ��g
	public int updateNameById(
			@Param("newId")String inputId ,
			@Param("newName")String inputName ,
			@Param("newAge")int inPutAge ,
			@Param("newCity")String inputCity );
	
	@Transactional
	@Modifying	
	@Query(value = "insert into person_Info(id,name,age,city)"+
	"select (:inputId , :inputName , :inputAge , :inputCity)"+
    "where not exists(select 1 from person_Info where id =:inputId)", nativeQuery = true)
	public int addPersonInfo(
			@Param("inputId")String id,
			@Param("inputName")String name,
			@Param("inputAge")int age,
			@Param("inputCity")String city
			) ;
	

	
	
	
	
	//JPA
	public List<PersonInfo> findByNameContainingOrCityContaining(String name , String city);
	
	@Query(value ="select * from person_info  as p where p.name regexp :name or p.city regexp :city", nativeQuery = true)
	public List<PersonInfo> findNameOrCityRegexp(@Param("name")String name,@Param("city")String city);
	
	
}
