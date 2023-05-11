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
	
	
	//找出年紀大於輸入條件的所有個人資訊 
	public List<PersonInfo> findByAgeGreaterThan(int age);
	//找出年紀小於等於輸入條件的所有個人資訊,依年齡由小到大排序
	public List<PersonInfo> findByAgeLessThanEqualOrderByAgeAsc(int age);
	//遞增用ASC 遞減用DESC
	//找到年紀介於2個數字(有包含)之間的資訊,以年齡由大到小排序,只取前3筆資料
	public List<PersonInfo> findFirst3ByAgeBetweenOrderByAgeDesc(int age ,int age1);
	public List<PersonInfo> findByAgeBetweenOrderByAgeDesc(int age ,int age1);
	//找到年紀小於某數或是年紀大於某數的資訊(滿足條件A或者滿足條件B,用Or)
	public List<PersonInfo> findByAgeLessThanOrAgeGreaterThan(int age ,int age1);
	//取得 city 包含某個特定字的所有個人資訊(模糊搜尋)
	public List<PersonInfo> findByCityContaining(String str);//'%台%'
	public List<PersonInfo> findByCityLike(String str);//'台'
	//	public GetPersonInfoResponse findByCityLike(String str) {
	//personInfoDao.findByCityLike("%"+str+"%");

	//找出年紀大於輸入條件以及city 包含某個特定字的所有人資訊,依照年齡由大到小排序
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
	//要記得用where指定位置,不然所有資料都會被異動
	//:自定義的參數 要記得:跟名字中間不能有空白(:匹配用的)
	//update...set可以只寫"要改的欄位",不用全寫
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
