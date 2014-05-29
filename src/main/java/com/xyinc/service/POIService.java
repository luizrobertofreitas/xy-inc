package com.xyinc.service;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.xyinc.business.POIBusiness;
import com.xyinc.model.entity.POIDBObject;

/**
 * POIs REST service
 * 
 * @author luizrobertofreitas
 *
 */
@Path("/pois")
public class POIService {

	private POIBusiness poiBusiness = new POIBusiness();
	
	/**
	 * Returns a JSON Array of all POIs
	 * 
	 * @return Response
	 */
	@GET
	@Path("/list")
	@Produces(value = "application/json")
	public Response getAll() {
		return Response.status(200).entity(String.valueOf(poiBusiness.findAll())).build();
	}
	
	/**
	 * Returns a JSON array of nearest POIs
	 * 
	 * @param x
	 * @param y
	 * @param maxDistance
	 * @return Response
	 */
	@GET
	@Path("/nearest/{x}/{y}/{maxDistance}")
	@Produces(value = "application/json")
	public Response getNearest(@PathParam("x") @DefaultValue("0") Integer x, 
							   @PathParam("y") @DefaultValue("0") Integer y, 
							   @PathParam("maxDistance") @DefaultValue("0") Integer maxDistance) {
		return Response.status(200).entity(String.valueOf(poiBusiness.findNearest(x, y, maxDistance))).build();
	}
	
	/**
	 * Creates a POI
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @return Response
	 */
	@POST
	@Path("/create")
	public Response create(@FormParam("name") String name, 
						   @FormParam("x") @DefaultValue("0") Integer x, 
						   @FormParam("y") @DefaultValue("0") Integer y) {
		return Response.status(200).entity(poiBusiness.create(new POIDBObject(x, y, name))).build();
	}
	
	/**
	 * Initialize the collection of POIs
	 * 
	 * @return Result message
	 */
	@GET
	@Path("/initialize")
	public Response initialize() {
		return Response.status(200).entity(poiBusiness.initialize()).build();
	}
	
	/**
	 * Drops the collection of POIs
	 * 
	 * @return Result message
	 */
	@GET
	@Path("/drop")
	public Response dropCollection() {
		return Response.status(200).entity(poiBusiness.dropCollection()).build();
	}
	
}
