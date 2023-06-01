package ba.celebration.organization.country.rest;

import ba.celebration.organization.country.Country;
import ba.celebration.organization.country.CountryServiceLocal;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/countries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CountryRestService {


    @Inject
    private CountryServiceLocal countryServiceLocal;


    // HTTP GET http://localhost:8080/organi.../api/countries
    @GET
    public Response getAll(){
        List<Country> countries = countryServiceLocal.findAll();
        return Response.ok(countries).build();
    }
}
