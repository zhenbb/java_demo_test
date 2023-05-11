package com.example.java_demo_test.respository;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.util.CollectionUtils;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

public class BaseDAO {
	
	@PersistenceContext   //JPA專有的註釋
	private EntityManager entityManager;

//	@SuppressWarnings({ "rawtypes", "unchecked" }) //這行是為了30行才有的
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz){
		//語法,參數,回傳型態
		Query query = entityManager.createQuery(sql, clazz);
		//Query是javax.persistence的,有多個選項要注意
		if(!CollectionUtils.isEmpty(params)) {
			//把MAP遍歷(foreach)
			for(Entry<String, Object> item :params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
//			//這兩個foreach出來的值是相同的,寫法不同
//			for(Parameter p : query.getParameters()) {
//				query.setParameter(p , params.get(p.getName()));
//				}
			}
		}
//		return query.getResultList();
//		return doQuery(sql ,params , clazz,-1);
//		//用下面新寫出來的參數(2),回頭帶上面,為了讓筆數變成無效的所以寫-1
		return doQuery(sql ,params , clazz,-1 ,0);
		//用下面新寫出來的參數(3),回頭帶上面,為了讓頁數變成無效的所以寫0
	}
	
	
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz,int limitSize){
		//語法,參數,回傳型態,限制回傳筆數
		Query query = entityManager.createQuery(sql, clazz);

		if(!CollectionUtils.isEmpty(params)) {
			//把MAP遍歷(foreach)
			for(Entry<String, Object> item :params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if(limitSize>0) {
			query.setMaxResults(limitSize);
		}

		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz,int limitSize,int startPosition){
		//語法,參數,回傳型態,限制回傳筆數,做分頁(一頁顯示幾筆資料,每頁的起始位置)
		Query query = entityManager.createQuery(sql, clazz);
		
		if(!CollectionUtils.isEmpty(params)) {
			//把MAP遍歷(foreach)
			for(Entry<String, Object> item :params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if(limitSize>0) {
			query.setMaxResults(limitSize);
		}
		if(startPosition>=0) {
			query.setFirstResult(startPosition);
		}

		return query.getResultList();
		}
	
	protected int doUpdate(String sql , Map<String , Object>params) {
	Query query = entityManager.createQuery(sql);
	if(!CollectionUtils.isEmpty(params)) {
		//把MAP遍歷(foreach)
		for(Entry<String, Object> item :params.entrySet()) {
			query.setParameter(item.getKey(), item.getValue());
		}
	}
	return query.executeUpdate();
}
	
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doNativeQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz,int limitSize){
		//語法,參數,回傳型態,限制回傳筆數
		Query query = entityManager.createNativeQuery(sql, clazz);

		if(!CollectionUtils.isEmpty(params)) {
			//把MAP遍歷(foreach)
			for(Entry<String, Object> item :params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
			}
		}
		if(limitSize>0) {
			query.setMaxResults(limitSize);
		}

		return query.getResultList();
	}
	
	
	
	
	
	
	}
