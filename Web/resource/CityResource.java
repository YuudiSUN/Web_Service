package travel.management.web.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import org.apache.cxf.ws.eventing.URI;

import travel.management.web.data.City;
import travel.management.web.service.CityService;

@Path("/cities")
public class CityResource {

  CityService service = new CityService();

  @Context
  UriInfo uriInfo;
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response addCity(City city) {
    City newCity = service.addCity(city);
    if(newCity == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    java.net.URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newCity.getId())).build();
    return Response.created(uri)
                   .entity(newCity)
                   .build();
  }
  
  @GET
  @Path("/{countryId}")
  @Produces(MediaType.APPLICATION_XML)
  public List<City> getCitiesByCountry(@PathParam("countryId") Long countryId) {
    return new ArrayList<>(service.getCitiesByCountry(countryId));
  }

  @PUT
  @Path("/{cityId}")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response updateCity(@PathParam("cityId") Long cityId, City updatedCity) {
      City city = service.updateCity(cityId, updatedCity);
      if(city == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok()
                     .entity(city)
                     .build();
  }

  @DELETE
  @Path("/{cityId}")
  public Response deleteCity(@PathParam("cityId") Long cityId) {
      boolean deleted = service.deleteCity(cityId);
      if(!deleted) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.noContent().build();
  }

}
