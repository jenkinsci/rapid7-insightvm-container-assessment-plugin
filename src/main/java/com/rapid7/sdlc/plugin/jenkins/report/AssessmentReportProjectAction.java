package com.rapid7.sdlc.plugin.jenkins.report;

import hudson.model.AbstractProject;
import hudson.model.Run;

public class AssessmentReportProjectAction extends AssessmentReportBaseAction {

  private final AbstractProject<?, ?> project;

  public AssessmentReportProjectAction(AbstractProject<?, ?> project) {
    this.project = project;
  }

  @Override
  public String getUrlName() {
    Run<?, ?> run = this.project.getLastCompletedBuild();
    if (run != null) {
      return String.valueOf(run.getNumber()) + "/" + AssessmentReportBaseAction.BASE_DIR + "/" + AssessmentReportBaseAction.REPORT_PAGE;
    }

    // none build was completed, report is yet not available
    return "";
  }
}
