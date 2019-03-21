# InsightVM Container Image Scanner Jenkins Plugin

[![Build Status](https://ci.jenkins.io/buildStatus/icon?job=Plugins/rapid7-insightvm-container-assessment-plugin/master)](https://ci.jenkins.io/job/Plugins/job/rapid7-insightvm-container-assessment-plugin/job/master/)

Jenkins plugin for InsightVM Container Image Assessment

More information about InsightVM can be found here: https://www.rapid7.com/products/insightvm/

## Prerequisites
To use the plugin you will need
- A user account on the Insight Platform with `Read Write` or `Admin` access.
- Access to InsightVM.

## Installation
The plugin may be installed using the plugin manager.  
For more information see https://plugins.jenkins.io/rapid7-insightvm-container-assessment

Additionally the plugin can be installed by manually building the `hpi` file and uploading to your Jenkins installation.

## Usage

See https://insightvm.help.rapid7.com/docs/containers-cicd-plugin

## Development
To run the plugin locally, `cd` to the root directory and invoke:
```
mvn hpi:run
```
When the output shows `INFO: Jenkins is fully up and running` navigate to `http://localhost:8080/jenkins/` and you will see the sandbox jenkins homepage.

## See also
- Jenkins plugin tutorial: https://wiki.jenkins.io/display/JENKINS/Plugin+tutorial

## Changelog

### 1.0.3
- Initial Release
