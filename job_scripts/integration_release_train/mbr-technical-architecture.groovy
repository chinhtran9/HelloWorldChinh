// Provided for completeness
def folderName = 'integration_release_train'
def jobconfig = """
<flow-definition plugin="workflow-job@2.40">
  <actions>
    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobAction plugin="pipeline-model-definition@1.8.4"/>
    <org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction plugin="pipeline-model-definition@1.8.4">
      <jobProperties>
        <string>jenkins.model.BuildDiscarderProperty</string>
        <string>org.jenkinsci.plugins.workflow.job.properties.DisableResumeJobProperty</string>
      </jobProperties>
      <triggers/>
      <parameters>
        <string>dockerRegistry</string>
        <string>buildRef</string>
      </parameters>
      <options>
        <string>skipDefaultCheckout</string>
      </options>
    </org.jenkinsci.plugins.pipeline.modeldefinition.actions.DeclarativeJobPropertyTrackerAction>
  </actions>
  <description></description>
  <keepDependencies>false</keepDependencies>
  <properties>
    <jenkins.model.BuildDiscarderProperty>
      <strategy class="hudson.tasks.LogRotator">
        <daysToKeep>7</daysToKeep>
        <numToKeep>-1</numToKeep>
        <artifactDaysToKeep>7</artifactDaysToKeep>
        <artifactNumToKeep>-1</artifactNumToKeep>
      </strategy>
    </jenkins.model.BuildDiscarderProperty>
    <org.jenkinsci.plugins.workflow.job.properties.DisableResumeJobProperty/>
    <com.sonyericsson.rebuild.RebuildSettings plugin="rebuild@1.31">
      <autoRebuild>false</autoRebuild>
      <rebuildDisabled>false</rebuildDisabled>
    </com.sonyericsson.rebuild.RebuildSettings>
    <hudson.model.ParametersDefinitionProperty>
      <parameterDefinitions>
        <hudson.model.StringParameterDefinition>
          <name>buildRef</name>
          <description>Branch to be built</description>
          <defaultValue>refs/heads/develop</defaultValue>
          <trim>false</trim>
        </hudson.model.StringParameterDefinition>
        <hudson.model.StringParameterDefinition>
          <name>dockerRegistry</name>
          <description>Docker registry to push to, note that this requires a secret to be stored for the builder</description>
          <defaultValue>artifactory.devtest.atohdtnet.gov.au</defaultValue>
          <trim>false</trim>
        </hudson.model.StringParameterDefinition>
      </parameterDefinitions>
    </hudson.model.ParametersDefinitionProperty>
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
          <name>feature/integration-team-pipelines</name>
        </hudson.plugins.git.BranchSpec>
      </branches>
      <doGenerateSubmoduleConfigurations>false</doGenerateSubmoduleConfigurations>
      <submoduleCfg class="list"/>
      <extensions/>
    </scm>
    <scriptPath>pipelines/camelapps/Jenkinsfile.groovy</scriptPath>
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

job(folderName + '/mbr-technical-architecture') {
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