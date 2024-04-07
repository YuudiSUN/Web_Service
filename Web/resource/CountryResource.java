package travel.management.web.resource;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import travel.management.web.data.Country;
import travel.management.web.service.CountryService;

@Path("/countries")
public class CountryResource {

  CountryService service = new CountryService();

  @Context
  UriInfo uriInfo;
  
  @POST
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response addCountry(Country country) {
    Country newCountry = service.addCountry(country);
    if(newCountry == null) {
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(newCountry.getId())).build();
    return Response.created(uri)
                   .entity(newCountry)
                   .build();
  }
  
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public List<Country> getAllCountries() {
    return new ArrayList<>(service.getAllCountries());
  }

  @GET
  @Path("/{countryId}")
  @Produces(MediaType.APPLICATION_XML)
  public Response getCountryById(@PathParam("countryId") Long countryId) {
      Country country = service.getCountryById(countryId);
      if(country == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok()
                     .entity(country)
                     .build();
  }

  @PUT
  @Path("/{countryId}")
  @Consumes(MediaType.APPLICATION_XML)
  @Produces(MediaType.APPLICATION_XML)
  public Response updateCountry(@PathParam("countryId") Long countryId, Country updatedCountry) {
      Country country = service.updateCountry(countryId, updatedCountry);
      if(country == null) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.ok()
                     .entity(country)
                     .build();
  }

  @DELETE
  @Path("/{countryId}")
  public Response deleteCountry(@PathParam("countryId") Long countryId) {
      boolean deleted = service.deleteCountry(countryId);
      if(!deleted) {
          return Response.status(Response.Status.NOT_FOUND).build();
      }
      return Response.noContent().build();
  }

}
