package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import model.MBookmark;
import service.IServiceHelloWorld;
import service.impl.ServiceHelloworld;

import com.sun.jersey.api.core.InjectParam;


/**
 * Root resource (exposed at "services" path)
 */
@Path("services")
public class Helloworld {
    @Context
    private UriInfo context;
    
    /**
     * Injected service
     */
    @InjectParam
    private ServiceHelloworld serviceBookmark;    
 
    public Helloworld() {
    }
    
    /**
     * Resource exposed at "helloworld" path
     * @return dummy text
     */
    @GET
    @Path("helloworld")
    @Produces("text/html")
    public String getHtml() {
    	MBookmark m1 = new MBookmark("test","/test","bookmark de test",30);
    	this.serviceBookmark.save(m1);
    	return this.serviceBookmark.getAll().get(0).toString();
    }
    
    @GET
    @Path("bookmark/{id}")
    @Produces("text/html")
    public String getHtmlBookmark(@PathParam("id") int id) {
    	return this.serviceBookmark.getById(id).toString();

    }
    
}