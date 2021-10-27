package com.tylersenter.utk.online_validator.app.upload;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.commons.io.IOUtils;

@RequestScoped
@Path("validate")
public class UploadResource {

  @POST
  @Consumes(MediaType.MULTIPART_FORM_DATA)
  public Response validate(InputStream inputStream) throws Exception {
    byte[] fileContents;
    File tempFile = null;
    try {
      fileContents = IOUtils.toByteArray(inputStream);
      tempFile = createTemporaryFile(fileContents);
    } catch (IOException e) {
      return Response.status(Status.INTERNAL_SERVER_ERROR).build();
    }
    UploadDomain domain = new UploadDomain(tempFile);

    return Response.ok(domain.performValidations()).build();
  }

  private File createTemporaryFile(byte[] content) throws IOException {
    java.nio.file.Path tempFilePath = Files.createTempFile(null, ".pdf");

    try {
      Files.write(tempFilePath, content);
    } catch (Exception e) {
      Files.deleteIfExists(tempFilePath);
    }
    return tempFilePath.toFile();
  }
}
