package com.xyinc.service;

import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mongodb.BasicDBList;
import com.mongodb.util.JSON;

public class POIServiceTest {

	private POIService service;
	
	@Before
	public void setUp() throws Exception {
		service = new POIService();
		service.initialize();
	}

	@After
	public void tearDown() throws Exception {
		service.dropCollection();
	}

	@Test
	public void testGetAll() {
		Response response = service.getAll();
		String json = String.valueOf(response.getEntity());
		BasicDBList dbList = (BasicDBList) JSON.parse(json);
		Assert.assertTrue(dbList.size() == 7);
	}

	@Test
	public void testGetNearest() {
		Response response = service.getNearest(20, 10, 10);
		String json = String.valueOf(response.getEntity());
		BasicDBList dbList = (BasicDBList) JSON.parse(json);
		Assert.assertTrue(dbList.size() == 4);
	}
	
	@Test
	public void testGetNearestWithNullValues() {
		Response response = service.getNearest(null, null, null);
		String json = String.valueOf(response.getEntity());
		BasicDBList dbList = (BasicDBList) JSON.parse(json);
		Assert.assertTrue(dbList.size() == 0);
	}
	
	@Test
	public void testGetNearestWithZeroValues() {
		Response response = service.getNearest(0, 0, 0);
		String json = String.valueOf(response.getEntity());
		BasicDBList dbList = (BasicDBList) JSON.parse(json);
		Assert.assertTrue(dbList.size() == 0);
	}

	@Test
	public void testCreate() {
		service.create("Shopping", 10, 10);
		Response response = service.getAll();
		String json = String.valueOf(response.getEntity());
		BasicDBList dbList = (BasicDBList) JSON.parse(json);
		Assert.assertTrue(dbList.size() == 8);
	}

}
