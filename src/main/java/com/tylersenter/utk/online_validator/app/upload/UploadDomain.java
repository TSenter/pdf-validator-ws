package com.tylersenter.utk.online_validator.app.upload;

import java.io.File;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tylersenter.pdf.Validator;
import com.tylersenter.pdf.reporting.Report;
import com.tylersenter.pdf.reporting.Report.ReportType;

public class UploadDomain {

  private static final String CONFIG_PATH = "/config/validator/config.json";
  private static final ObjectMapper mapper = new ObjectMapper();

  private Validator validator;

  public UploadDomain(File file) throws Exception {
    File configFile = new File(CONFIG_PATH);

    if (!configFile.exists()) {
      throw new IllegalStateException("Could not file configuration file '" + CONFIG_PATH + "'");
    }

    JsonNode rootConfig = mapper.readTree(configFile);

    validator = new Validator(rootConfig, file);
  }

  public String performValidations() {
    Report report = validator.validateAll();
    
    return report.build(ReportType.JSON, true);
  }
}
