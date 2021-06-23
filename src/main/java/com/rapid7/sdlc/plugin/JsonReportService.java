package com.rapid7.sdlc.plugin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rapid7.sdlc.plugin.jenkins.ReportCreationException;

import java.util.Map;

/**
 * Provides functionality for generating an JSON report of assessment results.
 */
public class JsonReportService extends ReportService {

    @Override
    String report(final Map<String, Object> data) throws ReportCreationException {
        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writer()
                         .writeValueAsString(data);
        } catch (final JsonProcessingException e) {
            throw new ReportCreationException(e.getMessage());
        }
    }

}
