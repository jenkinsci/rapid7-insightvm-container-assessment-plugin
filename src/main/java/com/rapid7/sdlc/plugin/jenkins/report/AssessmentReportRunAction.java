package com.rapid7.sdlc.plugin.jenkins.report;

import hudson.model.Run;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jenkins.model.RunAction2;

public class AssessmentReportRunAction extends SafeArchiveServingAction implements RunAction2 {
  private static final Logger LOGGER = Logger.getLogger(AssessmentReportRunAction.class.getName());

  public AssessmentReportRunAction(File rootDir, String... safeExtensions) {
    super(rootDir, BASE_DIR, REPORT_PAGE, safeExtensions);
  }

  @Override
  public void onAttached(Run<?, ?> run) {
    try {
      processDirectory();
    } catch (IOException | NoSuchAlgorithmException ex) {
      LOGGER.log(Level.WARNING, "Exception encountered while scanning " + run.getRootDir(), ex);
    }
  }

  @Override
  public void onLoad(Run<?, ?> run) {
  }
}
