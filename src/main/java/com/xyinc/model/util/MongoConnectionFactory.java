package com.xyinc.model.util;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.xyinc.util.AppUtil;

/**
 * Mongo connection factory class
 * 
 * @author luizrobertofreitas
 */
public final class MongoConnectionFactory {

	private static MongoClient mongoClient;
	
	private static final String HOSTNAME;
	private static final Integer PORT;
	private static final String DB;
	private static final String USERNAME;
	private static final String PASSWORD;
	private static final Boolean IS_AUTHENTICABLE;
	
	/**
	 * Static block 
	 * */
	static {
		HOSTNAME = AppUtil.getProperty("mongo.hostname");
		PORT = Integer.parseInt(AppUtil.getProperty("mongo.port"));
		DB = AppUtil.getProperty("mongo.dbname");
		USERNAME = AppUtil.getProperty("mongo.username");
		PASSWORD = AppUtil.getProperty("mongo.password");
		IS_AUTHENTICABLE = Boolean.valueOf(AppUtil.getProperty("mongo.isauthenticable"));
		
		List<MongoCredential> mongoCredentials = new ArrayList<MongoCredential>();
		
		if (IS_AUTHENTICABLE) {
			mongoCredentials.add(MongoCredential.createPlainCredential(USERNAME, DB, PASSWORD.toCharArray()));
		}
		
		ServerAddress serverAddress = new ServerAddress(new InetSocketAddress(HOSTNAME, PORT));
		
		mongoClient = new MongoClient(Arrays.asList(serverAddress), mongoCredentials);
	}
	
	/**
	 * Private constructor
	 */
	private MongoConnectionFactory() {}
	
	/**
	 * Returns a connection
	 * 
	 * @return Mongo Database Connection
	 */
	public static DB getMongoDB() {
		return mongoClient.getDB(DB);
	}
	
}
