package com.xyinc.business;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.xyinc.model.dao.MongoDao;
import com.xyinc.model.entity.POIDBObject;
import com.xyinc.util.AppUtil;

/**
 * Business class for POIs
 * 
 * @author luizrobertofreitas
 *
 */
public class POIBusiness {

	private MongoDao mongoDao;
	
	private DBObject fieldsObject;
	
	/**
	 * Constructor
	 */
	public POIBusiness() {
		mongoDao = new MongoDao(POIDBObject.POI_COLLECTION_NAME);
		
		fieldsObject = new BasicDBObject();
		fieldsObject.put(POIDBObject.NAME_KEY_FIELD, true);
		fieldsObject.put(POIDBObject.X_KEY_FIELD, true);
		fieldsObject.put(POIDBObject.Y_KEY_FIELD, true);
		fieldsObject.put(POIDBObject.ID_KEY_FIELD, false);
	}
	
	/**
	 * Returns all POIs
	 * 
	 * @return JSON String
	 */
	public BasicDBList findAll() {
		DBCursor cursor = mongoDao.query(new BasicDBObject(), fieldsObject);
		
		BasicDBList dbos = new BasicDBList();
		
		while(cursor.hasNext()) {
			POIDBObject dbo = POIDBObject.build(cursor.next());
			dbos.add(dbo);
		}
		
		return dbos;
	}
	
	/**
	 * Returns all nearest POIS
	 * 
	 * @param currentX
	 * @param currentY
	 * @param maxDistance
	 * @return JSON String
	 */
	public BasicDBList findNearest(Integer currentX, Integer currentY, Integer maxDistance) {

		DBCursor cursor = mongoDao.query(new BasicDBObject(), fieldsObject);
		
		BasicDBList dbos = new BasicDBList();
		
		while(cursor.hasNext()) {
			POIDBObject dbo = POIDBObject.build(cursor.next());
			
			if (isPOINear(dbo, currentX, currentY, maxDistance)) {
				dbos.add(dbo);
			}
		}
		
		return dbos;
	}
	
	/**
	 * Creates a POI
	 * 
	 * @param poi
	 * @return String message
	 */
	public String create(POIDBObject dbo) {
		
		String returnMessage = null;
		
		if (dbo.isValid()) {
			mongoDao.insert(dbo);
			returnMessage = AppUtil.getProperty("poi.insert.success");
		}
		else {
			returnMessage = AppUtil.getProperty("poi.non.valid.fields");
		}
		
		return returnMessage;
	}
	
	/**
	 * Initialize the collection
	 * 
	 * @return String message
	 */
	public String initialize() {
		
		dropCollection();
		
		POIDBObject lanchonete = new POIDBObject(27, 12, "Lanchonete");
		POIDBObject posto = new POIDBObject(31, 18, "Posto");
		POIDBObject joalheria = new POIDBObject(15, 12, "Joalheria");
		POIDBObject floricultura = new POIDBObject(19, 21, "Floricultura");
		POIDBObject pub = new POIDBObject(12, 8, "Pub");
		POIDBObject supermercado = new POIDBObject(23, 6, "Supermercado");
		POIDBObject churrascaria = new POIDBObject(28, 2, "Churrascaria");
		
		mongoDao.insert(new DBObject[] {
			lanchonete, 
			posto,
			joalheria,
			floricultura,
			pub,
			supermercado,
			churrascaria
		});
		
		return AppUtil.getProperty("poi.initialize.success");
	}
	
	/**
	 * Drops collection
	 * 
	 * @return String message
	 */
	public String dropCollection() {
		mongoDao.dropCollection();
		
		return AppUtil.getProperty("poi.drop.success");
	}
	
	/**
	 * Checks whether the POI is near of current location
	 * 
	 * @param poi
	 * @param currentX
	 * @param currentY
	 * @param maxDistance
	 * @return Boolean whether the POI is near
	 */
	private Boolean isPOINear(POIDBObject poi, Integer currentX, Integer currentY, Integer maxDistance) {
		Boolean isPoiNear = false;
		
		if (currentX != null && currentY != null && maxDistance != null) {
			
			double distanceBetweenPoints = Math.sqrt(Math.pow(Math.abs(currentX - poi.getX()), 2) + Math.pow(Math.abs(currentY - poi.getY()), 2));
			
			if (distanceBetweenPoints <= maxDistance.doubleValue()) {
				isPoiNear = true;
			}
		}
		
		return isPoiNear;
	}
	
}
