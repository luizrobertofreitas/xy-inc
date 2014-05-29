package com.xyinc.model.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.xyinc.model.util.MongoConnectionFactory;

/**
 * Mongo DAO class
 * 
 * @author luizrobertofreitas
 *
 */
public class MongoDao {

	private DBCollection collection;
	
	/**
	 * Constructor
	 * 
	 * @param collectionName
	 */
	public MongoDao(String collectionName) {
		DB db = MongoConnectionFactory.getMongoDB();
		collection = db.getCollection(collectionName);
	}
	
	/**
	 * Returns all queried documents from collection
	 * 
	 * @param queryObject
	 * @return DBCursor listing all documents queried from collection
	 */
	public DBCursor query(DBObject queryObject) {
		return collection.find(queryObject);
	}
	
	/**
	 * Inserts documents into a collection
	 * 
	 * @param dbObject
	 */
	public void insert(DBObject ... dbObject) {
		collection.insert(dbObject).getN();
	}
	
	/**
	 * Drops the collection
	 */
	public void dropCollection() {
		collection.drop();
	}
}
