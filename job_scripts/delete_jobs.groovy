import jenkins.model.*
import java.util.regex.Pattern
import java.util.Date

jenkins = Jenkins.instance
def count=0;
dryRun = false
println "Dry mode: $dryRun"

def views = Jenkins.instance.getViews()
println "View count ${views.size()}"

views.each { view ->
  
  if(view.name != 'all'){
    count++
  
    if(!dryRun){
       println "List view ${view.name} deleted"
       jenkins.deleteView(view)
    }
    else{
       println "Find ${view.name} view"
    }
  }	
}

println "\nDeleted ${count} views.\n"

jobs = jenkins.items

jobs.each { job ->
  
  if(job.name != 'super_seed_job'){
   	count++
   
    
    if(!dryRun){
       job.delete()
       println "Job ${job.name} deleted"
    }
    else{
       println "Find ${job.name} job"
    }
  } 
}

println "\nDeleted ${count} jobs.\n"