package com.rapid7.sdlc.plugin;

import com.rapid7.container.analyzer.docker.model.image.ImageId;
import com.rapid7.sdlc.plugin.ruleset.Rule;
import com.rapid7.sdlc.plugin.ruleset.RuleResult;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import freemarker.template.TemplateException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;

// TODO: unit test?
public class TestReportGenerator {
  private static final String TEST_PROJECT = "Test Project";
  private static final int BUILD_NUMBER = 1;
  private static final String BUILD_CAUSE = "Unknown Cause";
  private static final String IMAGE_NAME = "test-image:1";
  private static final String STATIC_DIR = "/";


  public static void main(String[] args) throws IOException, TemplateException {
    File workspace;
    if (args.length > 0)
      workspace = new File(args[0]);
    else
      workspace = Files.createTempDir();

    generateReport(workspace);
  }

  public static void generateReport(File workspace) throws IOException, TemplateException {
    File reportFile = new File(workspace, "report.html");
    System.out.println("Report: " + reportFile.getAbsolutePath());
    ReportService reportService = new ReportService(workspace);
    ObjectMapper mapper = new ObjectMapper();
    mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    com.rapid7.sdlc.plugin.api.model.Image assessedImage;
    try (InputStream assessmentJson = TestReportGenerator.class.getResourceAsStream("assessment.json")) {
      assessedImage = mapper.readValue(assessmentJson, com.rapid7.sdlc.plugin.api.model.Image.class);
    }
    // TODO: evaluate rules
    Map<Rule, RuleResult> ruleResults = Collections.emptyMap();
    String result = reportService.generateAssessmentReport(TEST_PROJECT, BUILD_NUMBER, BUILD_CAUSE, new ImageId(assessedImage.getId()), IMAGE_NAME, assessedImage, ruleResults, STATIC_DIR);
    try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(reportFile), StandardCharsets.UTF_8)) {
      writer.write(result);
    }
  }
}
