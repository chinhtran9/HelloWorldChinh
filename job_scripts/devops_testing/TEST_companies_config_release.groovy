// Provided for completeness
def folderName = 'devops_testing'
def jobconfig = """
<flow-definition plugin="workflow-job@2.40">
  <actions>
    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobAction plugin="pipeline-model-definition@1.8.4"/>
    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction plugin="pipeline-model-definition@1.8.4">
      <jobProperties>
        <string>org.jenkinsci.plugins.workflow.job.properties.DisableConcurrentBuildsJobProperty</string>
        <string>jenkins.model.BuildDiscarderProperty</string>
        <string>org.jenkinsci.plugins.workflow.job.properties.DisableResumeJobProperty</string>
      </jobProperties>
      <triggers/>
      <parameters>
        <string>skipPersonsTests</string>
        <string>dryRun</string>
        <string>skipCompaniesTests</string>
        <string>skipTests</string>
        <string>skipStartRelease</string>
        <string>buildRef</string>
        <string>versionDigitToIncrement</string>
        <string>skipCommonTests</string>
      </parameters>
      <options>
        <string>skipDefaultCheckout</string>
      </options>
    </org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction>
  </actions>
  <description></description>
  <keepDependencies>false</keepDependencies>
  <properties>
    <org.jenkinsci.plugins.workflow.job.properties.DisableConcurrentBuildsJobProperty/>
    <com.sonyericsson.rebuild.RebuildSettings plugin="rebuild@1.31">
      <autoRebuild>false</autoRebuild>
      <rebuildDisabled>false</rebuildDisabled>
    </com.sonyericsson.rebuild.RebuildSettings>
    <hudson.model.ParametersDefinitionProperty>
      <parameterDefinitions>
        <hudson.model.ChoiceParameterDefinition>
          <name>versionDigitToIncrement</name>
          <description>Version digit to increment for development update, 0 is major, 1 is minor, 2 is hotfix, defaults to 1 (minor)</description>
          <choices>
            <string>1</string>
            <string>0</string>
            <string>2</string>
          </choices>
        </hudson.model.ChoiceParameterDefinition>
        <hudson.model.BooleanParameterDefinition>
          <name>skipStartRelease</name>
          <description>Skip Start Release Branch - assumes the buildRef is the release branch</description>
          <defaultValue>false</defaultValue>
        </hudson.model.BooleanParameterDefinition>
        <hudson.model.BooleanParameterDefinition>
          <name>skipTests</name>
          <description>Skips all testing if required</description>
          <defaultValue>false</defaultValue>
        </hudson.model.BooleanParameterDefinition>
        <hudson.model.BooleanParameterDefinition>
          <name>skipCompaniesTests</name>
          <description>Skips companies ui testing if required</description>
          <defaultValue>true</defaultValue>
        </hudson.model.BooleanParameterDefinition>
        <hudson.model.BooleanParameterDefinition>
          <name>skipCommonTests</name>
          <description>Skips common ui testing if required</description>
          <defaultValue>false</defaultValue>
        </hudson.model.BooleanParameterDefinition>
        <hudson.model.BooleanParameterDefinition>
          <name>skipPersonsTests</name>
          <description>Skips persons ui testing if required</description>
          <defaultValue>false</defaultValue>
        </hudson.model.BooleanParameterDefinition>
        <hudson.model.BooleanParameterDefinition>
          <name>dryRun</name>
          <description>Dry Run, don&apos;t push tags and docker builds</description>
          <defaultValue>false</defaultValue>
        </hudson.model.BooleanParameterDefinition>
        <hudson.model.StringParameterDefinition>
          <name>buildRef</name>
          <description>Branch to be built</description>
          <defaultValue>refs/heads/develop</defaultValue>
          <trim>false</trim>
        </hudson.model.StringParameterDefinition>
      </parameterDefinitions>
    </hudson.model.ParametersDefinitionProperty>
    <jenkins.model.BuildDiscarderProperty>
      <strategy class="hudson.tasks.LogRotator">
        <daysToKeep>7</daysToKeep>
        <numToKeep>-1</numToKeep>
        <artifactDaysToKeep>7</artifactDaysToKeep>
        <artifactNumToKeep>-1</artifactNumToKeep>
      </strategy>
    </jenkins.model.BuildDiscarderProperty>
    <org.jenkinsci.plugins.workflow.job.properties.DisableResumeJobProperty/>
  </properties>
  <definition class="org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition" plugin="workflow-cps@2.90">
    <scm class="hudson.plugins.git.GitSCM" plugin="git@4.7.0">
      <configVersion>2</configVersion>
      <userRemoteConfigs>
        <hudson.plugins.git.UserRemoteConfig>
          <url>ssh://atotfs01.developer.atodnet.gov.au:22/tfs/TPC01/EST/_git/mbr-release-library</url>
          <credentialsId>svc_mbrtfs_sshkey</credentialsId>
        </hudson.plugins.git.UserRemoteConfig>
      </userRemoteConfigs>
      <branches>
        <hudson.plugins.git.BranchSpec>
          <name>feature/prepareCompaniesRelease</name>
        </hudson.plugins.git.BranchSpec>
      </branches>
      <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
      <submoduleCfg class="empty-list"/>
      <extensions/>
    </scm>
    <scriptPath>pipelines/mbr/Jenkinsfile-RELEASE.groovy</scriptPath>
    <lightweight>true</lightweight>
  </definition>
  <triggers/>
  <disabled>false</disabled>
</flow-definition>
"""

/*
1. Make a copy (X) of the `xml_to_jobdsl_template.groovy` template file.
    > NB: A "JobDSL .groovy file" must be named with letters and underscores.
2. Replace the `XML JOB HERE`-part with the contents of your `config.xml` file.
    > Go to the `/config.xml` endpoint in your browser, and use the "view-source"
3. Copy the contents of the `config.xml` and paste it into X.
4. Remove `<?xml version='1.1' encoding='UTF-8'?>`.
5. Escape all `\` and `$` with backslashes.
6. Give the job a name other than `replace-me-jobdsl`. NB: don't use whitespaces.
    > Congratulations! You've now converted your job to JobDSL.
*/

def jobconfignode = new XmlParser().parseText(jobconfig)

job(folderName + '/TEST_companies_config_release') {
    configure { node ->
        // node represents <project>
        jobconfignode.each { child ->

          def name = child.name()

          def existingChild = node.get(name)
          if(existingChild){
            node.remove(existingChild)
          }

          node << child
        }
    }
}