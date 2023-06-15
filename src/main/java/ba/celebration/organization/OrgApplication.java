package ba.celebration.organization;

import ba.celebration.organization.api.filter.CorsFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class OrgApplication extends ResourceConfig {
    public OrgApplication(){
        packages("ba.celebration.organization");
        //Registrovati CORS filter
        register(CorsFilter.class);
    }
}
