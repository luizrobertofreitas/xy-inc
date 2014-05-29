package com.xyinc.business;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.xyinc.model.entity.POIDBObject;

public class POIBusinessTest {

	private POIBusiness business;
	
	@Before
	public void setUp() throws Exception {
		business = new POIBusiness();
		business.initialize();
	}

	@After
	public void tearDown() throws Exception {
		business.dropCollection();
	}

	@Test
	public void testFindAll() {
		BasicDBList dbList = business.findAll();
		Assert.assertTrue(dbList.size() == 7);
	}

	@Test
	public void testFindNearest() {
		BasicDBList dbList = business.findNearest(20, 10, 10);
		Assert.assertTrue(dbList.size() == 4);
	}

	@Test
	public void testCreate() {
		business.create(new POIDBObject(10, 10, "Shopping"));
		BasicDBList dbList = business.findAll();
		Assert.assertTrue(dbList.size() == 8);
	}

}
