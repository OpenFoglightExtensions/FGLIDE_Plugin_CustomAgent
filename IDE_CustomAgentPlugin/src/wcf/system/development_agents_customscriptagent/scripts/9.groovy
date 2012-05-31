package system._development_agents_customscriptagent.scripts;
import com.quest.nitro.service.sl.interfaces.topology.DuplicateIdentityException
 
topSrv = server.get('TopologyService')
uiQueryResult = topSrv.createAnonymousDataObject(server.TopologyService.getType("UIQueryResult"))
uiQueryResult.set("returnCode", "success")
 
try {
    def objToUpdate = topSrv.getObject(agent.get("uniqueId"))
    def objShell  = topSrv.beginUpdate(objToUpdate)
 
    objShell.set("name",newName)
 
 
    objShell.set("typeNameAgent",newName+ "_Agent")
    objShell.set("typeNameTable",newName+ "_Agent_Table")
    objShell.set("typeNameTableDataPrefix",newName+ "_Agent_Table_")
 
    queryResult = topSrv.endUpdate(objShell)
 
    uiQueryResult.set("queryResult", new ArrayList())
    uiQueryResult.get("queryResult").add(queryResult)
 
    // To make sure we get the new longName
    uiQueryService = server.get("UIQueryService")
    uiQueryService.refreshCachedValues(queryResult)
 
} catch (DuplicateIdentityException die) {
    uiQueryResult.set("returnCode", "duplicateIdentity")
    uiQueryResult.set("errorMessage", die.getMessage())
}
 
return uiQueryResult