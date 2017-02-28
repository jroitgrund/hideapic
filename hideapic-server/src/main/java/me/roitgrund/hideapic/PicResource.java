package me.roitgrund.hideapic;

import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

@Path("/")
public class PicResource {
  private PicService picService;

  public PicResource(PicService picService) {
    this.picService = picService;
  }

  @POST
  @Path("/")
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void savePic(@FormParam("pic") String pic, @Suspended AsyncResponse asyncResponse) {
    picService
        .savePic(ImmutablePic.of(pic))
        .thenAccept(
            uuid ->
                asyncResponse.resume(
                    Response.seeOther(
                            UriBuilder.fromMethod(PicResource.class, "getPic").build(uuid))
                        .build()));
  }

  @GET
  @Path("/")
  public NewPicView newPic() {
    return new NewPicView(UriBuilder.fromMethod(PicResource.class, "savePic").build().toString());
  }

  @GET
  @Path("/{key}")
  public void getPic(@PathParam("key") String key, @Suspended AsyncResponse asyncResponse) {
    picService.getPic(key).thenAccept(pic -> asyncResponse.resume(new PicView(pic)));
  }
}
