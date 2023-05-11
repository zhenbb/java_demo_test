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
	
	@PersistenceContext   //JPA�M��������
	private EntityManager entityManager;

//	@SuppressWarnings({ "rawtypes", "unchecked" }) //�o��O���F30��~����
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz){
		//�y�k,�Ѽ�,�^�ǫ��A
		Query query = entityManager.createQuery(sql, clazz);
		//Query�Ojavax.persistence��,���h�ӿﶵ�n�`�N
		if(!CollectionUtils.isEmpty(params)) {
			//��MAP�M��(foreach)
			for(Entry<String, Object> item :params.entrySet()) {
				query.setParameter(item.getKey(), item.getValue());
//			//�o���foreach�X�Ӫ��ȬO�ۦP��,�g�k���P
//			for(Parameter p : query.getParameters()) {
//				query.setParameter(p , params.get(p.getName()));
//				}
			}
		}
//		return query.getResultList();
//		return doQuery(sql ,params , clazz,-1);
//		//�ΤU���s�g�X�Ӫ��Ѽ�(2),�^�Y�a�W��,���F�������ܦ��L�Ī��ҥH�g-1
		return doQuery(sql ,params , clazz,-1 ,0);
		//�ΤU���s�g�X�Ӫ��Ѽ�(3),�^�Y�a�W��,���F�������ܦ��L�Ī��ҥH�g0
	}
	
	
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz,int limitSize){
		//�y�k,�Ѽ�,�^�ǫ��A,����^�ǵ���
		Query query = entityManager.createQuery(sql, clazz);

		if(!CollectionUtils.isEmpty(params)) {
			//��MAP�M��(foreach)
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
		//�y�k,�Ѽ�,�^�ǫ��A,����^�ǵ���,������(�@����ܴX�����,�C�����_�l��m)
		Query query = entityManager.createQuery(sql, clazz);
		
		if(!CollectionUtils.isEmpty(params)) {
			//��MAP�M��(foreach)
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
		//��MAP�M��(foreach)
		for(Entry<String, Object> item :params.entrySet()) {
			query.setParameter(item.getKey(), item.getValue());
		}
	}
	return query.executeUpdate();
}
	
	@SuppressWarnings("unchecked")
	protected <EntityType>List<EntityType>doNativeQuery(String sql , Map<String , Object>params , 
			Class<EntityType>clazz,int limitSize){
		//�y�k,�Ѽ�,�^�ǫ��A,����^�ǵ���
		Query query = entityManager.createNativeQuery(sql, clazz);

		if(!CollectionUtils.isEmpty(params)) {
			//��MAP�M��(foreach)
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
