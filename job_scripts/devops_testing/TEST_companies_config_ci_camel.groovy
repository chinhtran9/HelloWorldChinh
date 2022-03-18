// Provided for completeness
def folderName = 'devops_testing'
def jobconfig = """
<com.cloudbees.hudson.plugins.folder.Folder plugin="cloudbees-folder@6.15">
  <actions/>
  <description></description>
  <properties>
    <org.jenkinsci.plugins.docker.workflow.declarative.FolderConfig plugin="docker-workflow@1.26">
      <dockerLabel></dockerLabel>
      <registry plugin="docker-commons@1.14"/>
    </org.jenkinsci.plugins.docker.workflow.declarative.FolderConfig>
  </properties>
  <folderViews class="com.cloudbees.hudson.plugins.folder.views.DefaultFolderViewHolder">
    <views>
      <hudson.model.AllView>
        <owner class="com.cloudbees.hudson.plugins.folder.Folder" reference="../../../.."/>
        <name>all</name>
        <filterExecutors>false</filterExecutors>
        <filterQueue>false</filterQueue>
        <properties class="hudson.model.View\$PropertyList"/>
      </hudson.model.AllView>
      <hudson.model.ListView>
        <owner class="com.cloudbees.hudson.plugins.folder.Folder" reference="../../../.."/>
        <name>Camel Pipelines</name>
        <filterExecutors>false</filterExecutors>
        <filterQueue>false</filterQueue>
        <properties class="hudson.model.View\$PropertyList"/>
        <jobNames>
          <comparator class="hudson.util.CaseInsensitiveComparator"/>
          <string>TEST-mbr-ap368-mappings</string>
          <string>TEST-mbr-asic-camel-service</string>
          <string>TEST-mbr-bambora-camel-service</string>
          <string>TEST-mbr-technical-architecture</string>
          <string>TEST-mbr-voil-integration-layer-client</string>
          <string>TEST-mbr-voil-openapi-camel-service</string>
        </jobNames>
        <jobFilters/>
        <columns>
          <hudson.views.StatusColumn/>
          <hudson.views.WeatherColumn/>
          <hudson.views.JobColumn/>
          <hudson.views.LastSuccessColumn/>
          <hudson.views.LastFailureColumn/>
          <hudson.views.LastDurationColumn/>
          <hudson.views.BuildButtonColumn/>
        </columns>
        <recurse>false</recurse>
      </hudson.model.ListView>
    </views>
    <primaryView>all</primaryView>
    <tabBar class="hudson.views.DefaultViewsTabBar"/>
  </folderViews>
  <healthMetrics/>
  <icon class="com.cloudbees.hudson.plugins.folder.icons.StockFolderIcon"/>
</com.cloudbees.hudson.plugins.folder.Folder>
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

job(folderName + '/TEST_companies_config_ci_camel') {
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