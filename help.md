# Description

The Rapid7 InsightVM Container Image Scanner is a Jenkins plugin that provides a mechanism for scanning Docker containers with InsightVM. This enables teams to perform vulnerability assessment for containers as an integral part of their CI/CD workflow. Container assessment plugin results are available both through Jenkins and the Builds tab of InsightVM's "Containers" screen.

# Key Features

* Configure custom rules for compliant container images
* Trigger build actions based on compliance to your rules
* Generate an assessment report

# Requirements

* Rapid7 Platform API Key
* Installation of plugin in the user's Jenkins instance with administrative privileges

# Documentation

## Setup

### Connection

Follow these steps to configure the Rapid7 Insight platform API key:

1. Login to Jenkins and navigate to the Manage Jenkins page
2. Click Configure System
3. Scroll to the "Rapid7 InsightVM Container Assessment" section
4. In the "InsightVM Region" field, select the region that InsightVM uses to access the platform
5. In the "Insight Platform API Key" field, click Add. In the dropdown menu, select "Jenkins" to configure the Insight platform API key that you generated earlier

## Technical Details

### Installing the Plugin

The plugin can be installed via the Jenkins Update Center as follows:

1. Navigate to Manage Jenkins > Manage Plugins
2. In the "Filter" box, search for "InsightVM"
3. Under the Under the Available tab, select the checkbox for the InsightVM Container Image Scanner
4. Click the desired install button

### Configuration

After configuring the Rapid7 Insight platform API key, follow these steps to configure the plugin:

1. In the Rapid7 InsightVM Container Assessment form, complete the following fields:
   - In the "Domain" field, select "Global credentials (unrestricted)"
   - In the "Kind" field, select "Secret text."
   - In the "Scope" field, select "Global (Jenkins, nodes, items, all child items, etc)"
   - In the "Secret" field, enter your API key
   - Leave the "ID" field blank
   - Enter a description for your reference
2. Click Add
3. Select your newly configured credential from the dropdown menu
4. Click Save to complete your plugin configuration

### Jenkins Build Job Setup

The plugin supports a couple differnt Jenkins build methods.

#### Freestyle Build

"Freestyle" is the classic job builder. Build steps can be added or removed via the user interface:

1. In a new or existing job, click Add build step
2. Select Assess Container Image with Rapid7 InsightVM. This will add a build step with a blank configuration
3. Configure the items under "Options" as desired
4. Click Add under the respective “Rules” section to configure the conditions that will trigger a build action. Two rule types are available:
   - "Threshold Rules" - Sets a numeric limit. Values that exceed this limit will trigger the build action
   - "Name Rules" - Matches text using the “contains” operator. A match will trigger the build action
5. Click Save when finished

#### Pipeline Build

The "Pipeline" method involves generating build step scripts from the plugin and adding them to the existing Pipeline script:

1. In a new or existing job, browse to the “Pipeline” section
2. Click Pipeline Syntax below the "Script" field
3. Open the dropdown next to "Sample Step" and select "assessContainerImage: Assess Container Image with Rapid7 InsightVM"
4. Configure your build options and rules in the same manner as before
5. Click Generate Pipeline Script when finished
6. Add your new step script to the existing Pipeline script
7. Click Save when finished

Note: Threshold rules must be unique per type. For example, you cannot have two rules for Critical Vulnerabilities. Only one instance of the rule will be applied.

## Troubleshooting

_This plugin does not contain any troubleshooting information._

# Version History

* 1.0.6 - Updated docker-image-analyzer to 0.1.8, which fixed an Out Of Memory issue when parsing large files
* 1.0.5 - Updated docker-image-analyzer to 0.1.6, which fixed a regression in parsing lsb-release files
* 1.0.4 - Updated docker-image-analyzer to 0.1.5. Improved fingerprint results for some Linux distributions 
* 1.0.3 - Initial release to Jenkins plugin repository

# Links

## References

* [Jenkins plugin page](https://plugins.jenkins.io/rapid7-insightvm-container-assessment)
* [Create a Rapid7 Platform API Key](https://insight.help.rapid7.com/docs/managing-platform-api-keys)
