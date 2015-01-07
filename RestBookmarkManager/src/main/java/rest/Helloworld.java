package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.springframework.http.MediaType;

import model.MBookmark;
import model.MTag;
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
    	this.serviceBookmark.saveBookmark(m1);
    	return this.serviceBookmark.getAllBookmarks().get(0).toString();
    }
    
    @GET
    @Path("bookmark/{id}")
    @Produces("text/html")
    public String getHtmlBookmark(@PathParam("id") int id) {
    	return this.serviceBookmark.getBookmarkById(id).toString();

    }
    
    @GET
    @Path("bookmark/{id}/tags")
    @Produces("text/html")
    public String getHtmlBookmarkTags(@PathParam("id") int id) {
    	return this.serviceBookmark.getBookmarkById(id).getTags().toString();

    }
    
    @GET
    @Path("bookmark/all")
    @Produces("text/html")
    public String getHtmlBookmarks() {
    	return this.serviceBookmark.getAllTags().toString();
    }
    
    @GET
    @Path("tag/id")
    @Produces("text/html")
    public String getHtmlBookmarkTag(@PathParam("id") int id) {
    	return this.serviceBookmark.getTagById(id).toString();

    }
    
    @GET
    @Path("tag/all")
    @Produces("text/html")
    public String getHtmlAll() {
    	return this.serviceBookmark.getAllTags().toString();
    }
    
    @POST
    @Path("bookmark/")
    @Produces("text/html")
    public String postHtmlPostBookmark(@FormParam("name") String name,
    		@FormParam("url") String url,
    		@FormParam("description") String description,
    		@FormParam("vues") int vues){
    	
    	MBookmark bookmark = new MBookmark(name, url, description ,vues);
    	this.serviceBookmark.saveBookmark(bookmark);
    	return "";
    }
    
    @GET
    @Path("bookmark/tag/{bookmark_id}/{tag_name}")
    @Produces("text/html")
    public String getHtmlBookmarkTag(@FormParam("bookmark_id") int bookmark_id,
    		@FormParam("tag_name") String tag_name) {
    	List<MTag> list = serviceBookmark.getAllTags();
    	//a faire verifier que la liste ne contient pas déjà le tag
    	MBookmark bookmark = serviceBookmark.getBookmarkById(bookmark_id);
    	MTag tag = new MTag(tag_name);
    	this.serviceBookmark.saveTag(tag);
    	tag.addBookmark(bookmark);
    	bookmark.addTag(tag);
    	
    	return "";

    }
    
	/* * remove the specified bookmark in the db */
    @DELETE
	@Path("/bookmark/{id}")
	public String removeBookmarkById(@PathParam("id") int id) {
		this.serviceBookmark.removeBookmarkById(id);
		return "";
	} /* * remove the specified tag in db */

	@DELETE
	@Path("/tag/{id}")
	public String removeTagById(@PathParam("id") int id) {
		this.serviceBookmark.removeTagById(id);
		return "";
	}  
    
}