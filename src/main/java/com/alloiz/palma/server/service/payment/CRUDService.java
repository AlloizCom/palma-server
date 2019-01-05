package com.alloiz.palma.server.service.payment;

import com.alloiz.palma.server.model.BaseEntity;

import java.util.List;


public interface CRUDService<T extends BaseEntity>
{

	T save(T obj);

	T update(T obj);

	Boolean delete(T obj);

	Boolean delete(Long id);

	T findOne(Long id);

	List<T> findAll();

}
