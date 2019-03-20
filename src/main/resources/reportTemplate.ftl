<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <style>
        * {
            font-family: 'Roboto';
            font-weight: normal;
            color: #4E4E4E;
            padding: 10px;
            margin: 0;
        }
        p {
            margin: 0;
        }
        p.center{
            margin-left: auto;
            margin-right: auto;
            text-align: center;
            padding-top: 0px;
        }
        h1 {
            font-family: 'Roboto';
            font-size: larger;
            padding: 0px;
        }
        h3 {
            font-size: medium;
            padding: 0px 0px 5px;
        }
        h2.center {
            margin-left: auto;
            margin-right: auto;
            text-align: center; 
        }
        table.center {
            margin-bottom: 15px;
            border: none;
            border-collapse: collapse;   
            width: 100%;    
            min-width: 1024px;
        }
        table, th, td {
            border:none;
            border-collapse: collapse;
        }
        th {
            text-align: left;
            border: none;
            font-weight: normal;
        }
        .sectionHeader {
            background-color: #ACBAC3;
            color: #FFFFFF;
        }
        .sectionHeader p, small {
            color: #FFFFFF;
            display: inline-block;
        }
        .tableData {
            background-color: lightgray;
            color: #4E4E4E;
        }
        .container {
            position: relative;
            padding-top: 30px;
        }
        #pass_fail{
            height: 70px;
            width: 70px;
            border-radius: 50%;
            display: inline-block;
            float: left;
            margin-left: 20px;
            margin-top: 5px;
        }
        #pass_fail p {
            position: relative;
            top: 22%;
            text-align: center;
            color: #FFFFFF;
        }
        .description {
            padding-top: 10px;
            margin-left: 125px;
        }
        .description p {
            line-height: 1.5;
        }
        .summary-data {
            background-color: whitesmoke;
        }
        .package-info {
            background-color: whitesmoke;
            /*border-bottom: white solid 10px;*/
        }
        .package-info p {
            font-size: 25px;
        }
        .pf-flag {
            position: relative;
            vertical-align: middle;
            height: 1px;
            width: 1px;
            border-radius: 50%;
            display: inline-block;
        }
        .pf {
            position: relative;
            vertical-align: middle;
            padding: 5px;
        }
        .pf-package {
            padding-top: 2px;
        }
        small  {
            float: right;
        }
        .top-left {
            float: left;
            padding-top: 0px;
            padding-bottom: 0px;
            padding-right: 0px;
            padding-left: 10px;
            text-align: left;
        }
        .top-right {
            float: right;
            padding-top: 0px;
            padding-bottom: 0px;
            padding-right: 10px;
            padding-left: 0px;
            text-align: right;
        }
        .timeStamp {
            position: relative;
            height: 5%;
            padding-bottom: 5px;
        }
        .triggered > * {
            float: right;
        }
        tr.addMargin {
            margin-bottom: 10px;
        }
        .malware-left-icon {
            height:25px;
        }
        .malware-legend {
            float: right;
            padding-top: 7px;
            padding-left: 0px;
            height:20px;
        }
        .fail {
            background-color: #E54C4C;
        }
        .pass {
            background-color: #00B07E;
        }
        .capitalized {
            text-transform: capitalize;
        }
        .min-width-column {
            white-space: nowrap;
        }
        .max-width-column {
            width: 99%
        }
        .vuln-description p {
            padding: 0;
        }
    </style>
</head>
<body>
<script>
   document.addEventListener('DOMContentLoaded', function() {
      // display date
      var dateStr = new Date(${dateInMillis?long?c});
      document.getElementById("date").innerHTML = dateStr;
      
      // round decimals greater than 100
      var elements = document.getElementsByClassName('rounded');
      for (var i = 0; i < elements.length; ++i) {
         var content = elements[i].innerHTML;
         if (!isNaN(content)) {
            // if not a whole number
            if (content % 1 !== 0) {
               if (content < 10)
                  content = Number.parseFloat(content).toPrecision(3); // 2 decimal places
               else if (content < 100)
                  content = Number.parseFloat(content).toPrecision(4); // 2 decimal places
               else 
                  content = Math.round(content);
            }
            else
               content = Math.round(content);
               
            elements[i].innerHTML = content;
         }
      }
   }, false);
</script>
           
<div class="timeStamp">
    <div class="top-left">
        <h3>${projectName}</h3>
        <h1>Rapid7 Assessment</h1>
    </div>
    <div class="top-right">
        <h3 id="date"></h3>
        <h3>${cause}</h3>
    </div>
</div>

<div class="container" style="margin-top: 20px;">
    <#if failed>
    <div id="pass_fail" class="fail">
        <p>Fail</p>
    </div>
    <#else>
    <div id="pass_fail" class="pass">
        <p>Pass</p>
    </div>
    </#if>
    <div class="description">
      <table>
         <tr>
            <td style="min-width: 425px;">Build: ${buildNumber}</td>
            <td style="min-width: 75px;">Image:</td>
            <td style="width: 100%;">${imageName}</td>
         </tr>
         <tr>
            <td>Packages: ${image.getPackages()?size}</td>
            <td>Image ID:</td>
            <td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap; max-width: 100px; "><span style="padding: 0px;" title="${imageId}">${imageId}</span><td>
         </tr>
         <tr>
            <#if failed>
               <td>Status: Build failed as a result of ${failedRuleCount} failed policy <#if failedRuleCount gt 1 || failedRuleCount == 0>rules<#else>rule</#if>.</td>
            <#else>
               <td>Status: Build succeeded as a result of ${passedRuleCount} passed policy <#if passedRuleCount gt 1 || passedRuleCount == 0>rules<#else>rule</#if>.</td>
            </#if>
            <td>Image OS:</td>
            <td><#if image.getOperatingSystem()??>${image.getOperatingSystem().getDescription()}</#if></td>
         </tr>
      </table>
    </div>
</div>

<div class="container">
    <table class="center">
        <thead>
            <tr>
                <th colspan="5" class="sectionHeader">1. Policy Results</th>
            </tr>
        </thead>
        <tbody>
            <tr class="tableData">
                <th style="width: 50%; font-weight: bold">Property</th>
                <th style="font-weight: bold; width: 25%">Configured Value</th>
                <th style="font-weight: bold; width: 25%">Actual Value</th>
                <th style="font-weight: bold; min-width: 150px">Status</th>
                <th style="font-weight: bold; min-width: 175px">Failure Action</th>
            </tr>
        <#if ruleResults?size == 0>
            <tr>
                <td colspan="5">No policy rules were defined.</td>
            </tr>
        <#else>
        <#list ruleResults as ruleResult>
        <#assign rule = ruleResult.getKey()>
        <#assign result = ruleResult.getValue()>
        <#assign configuredValue = rule.getPropertyEvaluator().getConfiguredValue()>
            <tr>
                <td style="width:30%;">${rule.getPropertyEvaluator().getDisplayName()}</td>
                <td class="rounded">${configuredValue}</td>
                <td class="rounded" <#if result.failed()>style="color: #E54C4C;"<#else>style="color: #00B07E;"</#if>>${result.getActualValue()!"-"}</td>
                <td>
                <#if result.failed()>
                    <div class="pf-flag fail"></div>
                <#else>
                    <div class="pf-flag pass"></div>
                </#if>
                    <span class="pf"><#if result.failed()>Fail<#else>Pass</#if></span>
                </td>
                <td>${rule.getAction().getDisplayName()}</td>
            </tr>
        </#list>
        </#if>
        </tbody>
    </table>
</div>

<div class="container">
    <table class="center">
        <thead>
            <tr>
                <th colspan="6" class="sectionHeader">2. Summary</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td style="padding: 0px">
                    <h2 class="center summary-data" <#if failedRuleCount gt 0>style="color: #e45c5c;"<#else>style="color: #00B07E;"</#if>>${failedRuleCount}</h2>
                    <p class="center" style="background-color: whitesmoke">Failed Rules</p>
                </td>
                <td style="padding: 0px">
                    <h2 class="center summary-data">${image.getAssessment().getVulnerabilities().getTotal()}</h2>
                    <p class="center" style="background-color: whitesmoke">Total Vulnerabilities</p>
                </td>
                <td style="padding: 0px">
                    <h2 class="center summary-data" <#if image.getAssessment().getVulnerabilities().getSeverity().getCritical() gt 0>style="color: #e45c5c;"</#if>>${image.getAssessment().getVulnerabilities().getSeverity().getCritical()}</h2>
                    <p class="center" style="background-color: whitesmoke">Critical Vulnerabilities</p>  
                </td>
                <td style="padding: 0px">
                    <h2 class="center summary-data" <#if image.getAssessment().getVulnerabilities().getSeverity().getSevere() gt 0>style="color: #ec7a50;"</#if>>${image.getAssessment().getVulnerabilities().getSeverity().getSevere()}</h2>
                    <p class="center" style="background-color: whitesmoke">Severe Vulnerabilities</p>
                </td>
                <td style="padding: 0px">
                    <h2 class="center summary-data" <#if image.getAssessment().getVulnerabilities().getSeverity().getModerate() gt 0>style="color: #F6A73D;"</#if>>${image.getAssessment().getVulnerabilities().getSeverity().getModerate()}</h2>
                    <p class="center" style="background-color: whitesmoke">Moderate Vulnerabilities</p>
                </td>
                <td style="padding: 0px">
                    <h2 class="center summary-data">${vulnerablePackageCount}</h2>
                    <p class="center" style="background-color: whitesmoke">Vulnerable Packages</p>
                </td>
            </tr>
        </tbody>
    </table>
</div>

<#if packageAssessmentsAffectingPolicy?size gt 0>
<div class="container">
    <table class="center">
        <thead>
            <tr>
                <th colspan="7" class="sectionHeader">
                    <p>3. Packages Affecting Policy</p>
                    <img class="malware-legend" src="${staticContentDir}/images/exploit-published-shield.svg"/>
                    <small>Exploitable by Malware:</small>
                </th>
            </tr>
        </thead>
        <tbody>
        <#list packageAssessmentsAffectingPolicy as package>
        <#assign vulns = package.getAssessment().getFindings()>
            <tr>
                <td colspan="4" class="package-info">
                    <p>${package.getName()}</p>
                    <p style="font-size: 12px; padding-top: 0px">Vulnerabilities: ${vulns?size}</p>
                </td>
                <td colspan="3" class="package-info triggered">
                    <span class="pf pf-package">Fail</span>
                    <div class="pf-flag fail"></div>
                </td>
            </tr>
        <#if vulns?size gt 0 >
        <#list vulns as vulnerability>
        <#assign vuln = vulnerability.getVulnerability()>
        <#if vulnerability?index == 0 >
            <tr class="tableData">
                <td></td>
                <td class="min-width-column">Title</td>
                <td class="min-width-column">CVE</td>
                <td class="min-width-column">Severity</td>
                <td class="min-width-column">CVSS</td>
                <td class="min-width-column">Risk Score</td>
                <td class="max-width-column">Description</td>
            </tr>
        </#if>
        <#assign cvssV2 = vuln.getCvssV2()>
            <tr style="border-bottom: solid 1px #D2D6D8; vertical-align: top" <#if vulnerability?index+1 == vulns?size>class="addMargin"</#if>>
                <td style="min-width: 30px;"><#if vuln.getMalwareKits()?has_content><img class="malware-left-icon" src="${staticContentDir}/images/exploit-published-shield.svg" /></#if></td>
                <td class="min-width-column">${vuln.getTitle()}</td>
                <td class="min-width-column">${vuln.getCves()?join("\n")}</td>
                <td class="capitalized min-width-column">${vuln.getSeverity()}</td>
                <td class="min-width-column">${cvssV2.getScore()}</td>
                <td class="min-width-column rounded">${vuln.getRiskScore()}</td>
                <td class="max-width-column vuln-description">${vuln.getDescription().getHtml()}</td>
            </tr>
        </#list>
        </#if>
        </#list>
        </tbody>
    </table>
</div>
</#if>

<#if otherPackageAssessments?size gt 0>
<div class="container">
    <table class="center">
        <thead>
            <tr>
                <th colspan="7" class="sectionHeader">
                    <p><#if packageAssessmentsAffectingPolicy?size gt 0>4<#else>3</#if>. Other Vulnerable Packages</p>
                    <img class="malware-legend" src="${staticContentDir}/images/exploit-published-shield.svg"/>
                    <small>Exploitable by Malware:</small>
                </th>
            </tr>
        </thead>
        <tbody>
        <#list otherPackageAssessments as package>
        <#assign vulns = package.getAssessment().getFindings()>
        <#if vulns?size gt 0 >
        <#list vulns as vulnerability>
        <#assign vuln = vulnerability.getVulnerability()>
        <#if vulnerability?index == 0 >
            <tr>
                <td colspan="7" class="package-info">
                    <p>${package.getName()}</p>
                    <p style="font-size: 12px; padding-top: 0px">Vulnerabilities: ${vulns?size}</p>
                </td>
            </tr>
            <tr class="tableData">
                <td></td>
                <td class="min-width-column">Title</td>
                <td class="min-width-column">CVE</td>
                <td class="min-width-column">Severity</td>
                <td class="min-width-column">CVSS</td>
                <td class="min-width-column">Risk Score</td>
                <td class="max-width-column">Description</td>
            </tr>
        </#if>
        <#assign cvssV2 = vuln.getCvssV2()>
            <tr style="border-bottom: solid 1px #D2D6D8; vertical-align: top" <#if vulnerability?index+1 == vulns?size>class="addMargin"</#if>>
                <td style="min-width: 30px;"><#if vuln.getMalwareKits()?has_content><img class="malware-left-icon" src="${staticContentDir}/images/exploit-published-shield.svg" /></#if></td>
                <td class="min-width-column">${vuln.getTitle()}</td>
                <td class="min-width-column">${vuln.getCves()?join("\n")}</td>
                <td class="capitalized min-width-column">${vuln.getSeverity()}</td>
                <td class="min-width-column">${cvssV2.getScore()}</td>
                <td class="min-width-column rounded">${vuln.getRiskScore()}</td>
                <td class="max-width-column vuln-description">${vuln.getDescription().getHtml()}</td>
            </tr>
        </#list>
        </#if>
        </#list>
        </tbody>
    </table>
</div>
</#if>
        
</body>
