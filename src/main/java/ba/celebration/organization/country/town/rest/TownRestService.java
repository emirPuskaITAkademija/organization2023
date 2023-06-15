package ba.celebration.organization.country.town.rest;

import ba.celebration.organization.country.town.Town;
import ba.celebration.organization.country.town.TownPage;
import ba.celebration.organization.country.town.TownServiceLocal;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

// http://localhost:8080/organization-1.0-SNAPSHOT/api/towns
@Path("/towns")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TownRestService {

    @Inject
    private TownServiceLocal townServiceLocal;


    /**
     *  HTTP GET request
     *  <p>http://localhost:8080/organization-1.0-SNAPSHOT/api/towns?page=1&pageSize=5</p>
     * @return response
     */
    @GET
    public Response getAll(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize){
        List<Town> townList = townServiceLocal.findPage(page, pageSize);
        TownPage townPage = new TownPage();
        townPage.setTowns(townList);
        //totalPages
        int totalItems = townServiceLocal.count();
        int totalPages =(int) Math.ceil((double)totalItems/pageSize);//3.3 -> 4
        townPage.setTotalPages(totalPages);
        return Response.ok(townPage).build();
    }

    /**
     * HTTP GET request
    <p>http://localhost:8080/organization-1.0-SNAPSHOT/api/towns/2</p>
     */
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id){
        Town town = townServiceLocal.find(id);
        if(town !=null){
            return Response.ok(town).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    /**
     *  HTTP POST request
     *  <p>http://localhost:8080/organization-1.0-SNAPSHOT/api/towns</p>
     */
    @POST
    public Response create(Town town){
        townServiceLocal.create(town);
        return Response.status(Response.Status.CREATED).build();
    }

    /**
     *  HTTP PUT request
     *  <p>http://localhost:8080/organization-1.0-SNAPSHOT/api/towns/id</p>
     */
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, Town town){
        Town existingTown = townServiceLocal.find(id);
        if(existingTown != null){
            town.setId(id);
            if(town.getName()==null || town.getName().isBlank()){
                town.setName(existingTown.getName());
            }
            if(town.getCountry() == null){
                town.setCountry(existingTown.getCountry());
            }
            townServiceLocal.edit(town);
            return Response.ok().build();
        }else{
            return Response.notModified().build();
        }
    }
    /**
     *  HTTP DELETE request
     *  <p>http://localhost:8080/organization-1.0-SNAPSHOT/api/towns/id</p>
     */
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id){
        Town existingTown = townServiceLocal.find(id);
        if(existingTown != null){
            townServiceLocal.remove(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        }else{
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
