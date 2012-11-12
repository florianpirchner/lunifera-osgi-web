/*******************************************************************************
 * Copyright (c) 2012 by committers of lunifera.org
 * 
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *    Florian Pirchner - initial API and implementation
 *******************************************************************************/
package org.lunifera.web.vaadin.designstudy01.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.lunifera.web.vaadin.designstudy01.api.IPerson;
import org.lunifera.web.vaadin.designstudy01.api.IPersonService;
import org.lunifera.web.vaadin.designstudy01.model.PersonEntity;
import org.lunifera.web.vaadin.designstudy01.model.PersonPersistenceActivator;

public class PersonService implements IPersonService {

	@SuppressWarnings("unchecked")
	@Override
	public List<IPerson> getAll() {
		EntityManagerFactory emf = PersonPersistenceActivator.getEMF();
		if (emf == null) {
			return new ArrayList<IPerson>();
		}
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();

		List<IPerson> persons;
		try {
			txn.begin();

			Query query = em.createQuery("select p from PersonEntity as p");
			persons = query.getResultList();
			txn.commit();
			txn = null;
		} finally {
			if (txn != null) {
				txn.rollback();
				txn = null;
			}
		}

		return persons != null ? persons : new ArrayList<IPerson>();
	}

	@Override
	public void save(IPerson person) {
		EntityManagerFactory emf = PersonPersistenceActivator.getEMF();
		EntityManager em = emf.createEntityManager();
		EntityTransaction txn = em.getTransaction();
		try {
			txn.begin();
			PersonEntity pers = em.find(PersonEntity.class, person.getId());
			if (pers != null) {
				pers.setFirstName(person.getFirstName());
				pers.setLastName(person.getLastName());
				pers.setAge(person.getAge());
			} else {
				em.persist(person);
			}
			txn.commit();
			txn = null;
		} catch (Exception ex) {
			System.out.println(ex);
		} finally {
			if (txn != null) {
				txn.rollback();
				txn = null;
			}
		}
	}

	@Override
	public IPerson createNew() {
		return new PersonEntity();
	}

}
