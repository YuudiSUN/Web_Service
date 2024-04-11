package travel.management.web.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import travel.management.web.data.City;
import travel.management.web.service.CityService;

// Defines the path to the cities resources, making this class a resource handler for city-related API calls
@Path("/cities")
public class CityResource {

  // Instance of CityService to interact with city data
  CityService service = new CityService();

  // Injects information about the URI context into this resource class
  @Context
  UriInfo uriInfo;
  
  // Handles POST requests to add a new city. Consumes and produces XML.
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response addCity(City city) {
    City newCity = service.addCity(city);
    if(newCity == null) {
      // Returns a BAD_REQUEST status if the city could not be added
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    // Builds a URI for the newly created city and returns a CREATED response including the city data
    java.net.URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newCity.getId())).build();
    return Response.created(uri)
                   .entity(newCity)
                   .build();
  }
  
  // Handles GET requests to retrieve cities by country ID. Produces XML.
  @GET
  @Path("/{countryId}")
  @Produces(MediaType.APPLICATION_XML)
  public List<City> getCitiesByCountry(@PathParam("countryId") Long countryId) {
    // Returns a list of cities for the specified country ID
    return new ArrayList<>(service.getCitiesByCountry(countryId));
  }

  // Handles PUT requests to update an existing city by ID. Consumes and produces XML.
  @PUT
  @Path("/{cityId}")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response updateCity(@PathParam("cityId") Long cityId, City updatedCity) {
      City city = service.updateCity(cityId, updatedCity);
      if(city == null) {
          // Returns a NOT_FOUND status if the city does not exist
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      // Returns an OK status and includes the updated city data
      return Response.ok()
                     .entity(city)
                     .build();
  }

  // Handles DELETE requests to remove a city by ID.
  @DELETE
  @Path("/{cityId}")
  public Response deleteCity(@PathParam("cityId") Long cityId) {
      boolean deleted = service.deleteCity(cityId);
      if(!deleted) {
          // Returns a NOT_FOUND status if the city could not be found or deleted
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      // Returns a no-content response to indicate successful deletion
      return Response.noContent().build();
  }

}
