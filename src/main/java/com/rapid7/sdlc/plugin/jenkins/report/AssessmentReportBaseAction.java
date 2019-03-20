package com.rapid7.sdlc.plugin.jenkins.report;

import hudson.model.Action;
import static com.rapid7.sdlc.plugin.jenkins.ContainerAssessmentBuilder.PLUGIN_NAME;

public class AssessmentReportBaseAction implements Action {
  public static final String BASE_DIR = "rapid7-assessments";
  public static final String REPORT_PAGE = "report.html";
  public static final String ICON_DIR = "/plugin/" +  PLUGIN_NAME + "/images";

  @Override
  public String getUrlName() {
    return REPORT_PAGE;
  }

  @Override
  public String getIconFileName() {
    return ICON_DIR + "/rapid7-logo.png";
  }

  @Override
  public String getDisplayName() {
    return "Rapid7 Assessment";
  }
}
