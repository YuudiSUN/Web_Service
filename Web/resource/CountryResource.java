package travel.management.web.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import travel.management.web.data.Country;
import travel.management.web.service.CountryService;

// Defines the base path for all operations related to countries within the web service
@Path("/countries")
public class CountryResource {

  // Service layer handling business logic for country operations
  CountryService service = new CountryService();

  // Contextual information about the URI, injected by the JAX-RS runtime
  @Context
  UriInfo uriInfo;
  
  // Method to handle POST requests to add a new country. Accepts and returns data in XML format.
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response addCountry(Country country) {
    Country newCountry = service.addCountry(country);
    if(newCountry == null) {
      // Returns a 400 BAD REQUEST status if the country cannot be added
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    // Constructs the URI for the newly created country and returns a 201 CREATED response with the country entity
    URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newCountry.getId())).build();
    return Response.created(uri)
                   .entity(newCountry)
                   .build();
  }
  
  // Handles GET requests to retrieve all countries. Returns data in XML format.
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Country> getAllCountries() {
    // Fetches all countries from the service and returns them as a list
    return new ArrayList<>(service.getAllCountries());
  }

  // Handles GET requests for a single country by its ID. Returns data in XML format.
  @GET
  @Path("/{countryId}")
  @Produces(MediaType.APPLICATION_XML)
  public Response getCountryById(@PathParam("countryId") Long countryId) {
      Country country = service.getCountryById(countryId);
      if(country == null) {
          // Returns a 404 NOT FOUND status if the country does not exist
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      // Returns a 200 OK status with the country entity
      return Response.ok()
                     .entity(country)
                     .build();
  }

  // Handles PUT requests to update an existing country by its ID. Accepts and returns data in XML format.
  @PUT
  @Path("/{countryId}")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response updateCountry(@PathParam("countryId") Long countryId, Country updatedCountry) {
      Country country = service.updateCountry(countryId, updatedCountry);
      if(country == null) {
          // Returns a 404 NOT FOUND status if the country to be updated does not exist
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      // Returns a 200 OK status with the updated country entity
      return Response.ok()
                     .entity(country)
                     .build();
  }

  // Handles DELETE requests to remove a country by its ID.
  @DELETE
  @Path("/{countryId}")
  public Response deleteCountry(@PathParam("countryId") Long countryId) {
      boolean deleted = service.deleteCountry(countryId);
      if(!deleted) {
          // Returns a 404 NOT FOUND status if the country cannot be found or deleted
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      // Returns a 204 NO CONTENT status to indicate successful deletion
      return Response.noContent().build();
  }

}
