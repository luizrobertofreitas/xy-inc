package com.xyinc.model.entity;

import java.util.Map;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

public class POIDBObject extends BasicDBObject {

	private static final long serialVersionUID = 4434006230616777645L;
	
	public static final String POI_COLLECTION_NAME = "pois";
	public static final String X_KEY_FIELD = "x";
	public static final String Y_KEY_FIELD = "y";
	public static final String NAME_KEY_FIELD = "name";
	public static final String ID_KEY_FIELD = "_id";
	
	@SuppressWarnings("rawtypes")
	private POIDBObject(Map m) {
		super(m);
	}
	
	public POIDBObject() {}
	
	public POIDBObject(Integer x, Integer y, String name) {
		put(X_KEY_FIELD, x);
		put(Y_KEY_FIELD, y);
		put(NAME_KEY_FIELD, name);
	}
	
	public Integer getX() {
		return getInt(X_KEY_FIELD);
	}

	public void setX(Integer x) {
		put(X_KEY_FIELD, x);
	}

	public Integer getY() {
		return getInt(Y_KEY_FIELD);
	}

	public void setY(Integer y) {
		put(Y_KEY_FIELD, y);
	}

	public String getName() {
		return getString(NAME_KEY_FIELD);
	}

	public void setName(String name) {
		put(NAME_KEY_FIELD, name);
	}

	/**
	 * Validates a poi
	 * 
	 * @return boolean valid poi
	 */
	public Boolean isValid() {
		Boolean isValid = false;
		
		if (getX() != null && getX() >= 0 &&
			getY() != null && getY() >= 0 &&
			getName() != null && !getName().isEmpty()) {
			
			isValid = true;
		}
		
		return isValid;
	}
	
	/* (non-Javadoc)
	 * @see com.mongodb.BasicDBObject#toString()
	 */
	public String toString() {
		this.remove(ID_KEY_FIELD);
		return JSON.serialize(this);
	}
	
	/**
	 * Build an instance of POIDBObject
	 * 
	 * @param dbo
	 * @return POIDBObject
	 */
	public static POIDBObject build(DBObject dbo) {
		return new POIDBObject(dbo.toMap());
	}
}
