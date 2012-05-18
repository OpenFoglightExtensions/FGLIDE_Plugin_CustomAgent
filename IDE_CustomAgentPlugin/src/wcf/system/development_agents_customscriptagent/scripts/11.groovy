// The following are special reserved parameters which are always available to scripts (see documentation for details)
//
// locale - the locale selected on the browser by the user
// resourceBundle - the java ResourceBundle for the current locale with strings loaded from the module's "strings.properties" file in the public directory
// specificTimeRange - the current specific time range that must be used to get property values from data objects
// functionHelper - the FunctionHelper instance for getting other information 
//
// Uncomment the following line to get a Log instance
// org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog("function id: " + functionHelper.getFunctionId());
if (xmlText == null || xmlText == "") return functionHelper.createDataObject("development_agents_customscriptagent:ASPContainer","none",null)


def impProps = {obj,node -> 
	node.attributes().each{k,v->
		obj.store(k,v,null)
	}
}

def propTypemapping = [
		"string" :"development_agents_customscriptagent:PropString",
		"integer":"development_agents_customscriptagent:PropInteger",
		"float":"development_agents_customscriptagent:PropFloat",
		"boolean":"development_agents_customscriptagent:PropBoolean",
		"secondary":"development_agents_customscriptagent:PropSecondaryASP",
		"enum":"development_agents_customscriptagent:PropEnum"
		]

def createPop = {node,type,pos -> 
	def o = functionHelper.createDataObject(type,"none",null)
	impProps.call(o,node)
	o.store("pos",pos,null)
	return o
}


try { 
def groups = new XmlSlurper().parseText(xmlText).group
def aspGroups = groups.collect      { g->
	def group = functionHelper.createDataObject("development_agents_customscriptagent:ASPGroup","none",null)
	impProps.call(group,g)
	def i = 1
	def childs = g.children().collect { c->
		def tname = propTypemapping[c.name()]
		if (tname == null) return null
		else {
			def child = createPop.call(c,tname,i++)
			
			if (c.name() == "enum") {
				def k = 1
				def evals = c["const"].collect { ev->
					return createPop(ev,"development_agents_customscriptagent:EnumValue",k++)
				}
				child.store("enums",evals,null)
			}
			if (c.name() == "secondary") {
				def k = 1
				def secs = c["cols"].children().collect { ev->
					def t2name = propTypemapping[ev.name()]
					if (t2name == null) return null
					
					def child2 = createPop.call(ev,t2name,k++)
					if (ev.name() == "enum") {
						def k2 = 1
						def evals = ev["const"].collect { ev2->
							return createPop(ev2,"development_agents_customscriptagent:EnumValue",k2++)
						}
						child2.store("enums",evals,null)
					}
					
					return child2
				}
				child.store("cols",secs,null)
			}
			
			return child
			
		}
	}
	
	group.store("properties",childs.flatten() as List,null)
	return group
}

	def container = functionHelper.createDataObject("development_agents_customscriptagent:ASPContainer","none",null)
container.store("aspGroups",aspGroups as List, null)
return container

} catch (Throwable t) {return null;}