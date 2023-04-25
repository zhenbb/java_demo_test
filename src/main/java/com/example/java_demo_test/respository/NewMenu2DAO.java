package com.example.java_demo_test.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.java_demo_test.entity.NewMenu;
import com.example.java_demo_test.entity.NewMenu2;
import com.example.java_demo_test.entity.NewMenu2Id;

@Repository
public interface NewMenu2DAO extends JpaRepository<NewMenu2, NewMenu2Id>{

}
