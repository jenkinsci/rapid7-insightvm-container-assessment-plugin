package com.rapid7.sdlc.plugin;

import com.rapid7.sdlc.plugin.jenkins.ReportCreationException;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Provides functionality for generating an HTML report of assessment results.
 */
public class HtmlReportService extends ReportService {

    private static final String TEMPLATE_FILE = "reportTemplate.ftl";
    private final Template template;

    @SuppressFBWarnings("RV_RETURN_VALUE_IGNORED_BAD_PRACTICE")
    public HtmlReportService(final File buildDir) throws IOException {

        final Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

        final File buildTemplate = new File(buildDir, TEMPLATE_FILE);
        try {
            // First copy the template into the build directory where it could be found
            final InputStream templateData = getClass().getClassLoader()
                                                       .getResourceAsStream(TEMPLATE_FILE);

            FileUtils.copyInputStreamToFile(templateData, buildTemplate);

            // Load build template from build folder
            cfg.setDirectoryForTemplateLoading(buildDir);
            template = cfg.getTemplate(TEMPLATE_FILE);
        } finally {
            //noinspection ResultOfMethodCallIgnored
            buildTemplate.delete();
        }
    }

    @Override
    String report(final Map<String, Object> data) throws ReportCreationException {
        try {
            final Writer dataWriter = new StringWriter();
            template.process(data, dataWriter);
            dataWriter.flush();
            return dataWriter.toString();
        } catch (final TemplateException | IOException e) {
            throw new ReportCreationException(e.getMessage());
        }
    }
}
