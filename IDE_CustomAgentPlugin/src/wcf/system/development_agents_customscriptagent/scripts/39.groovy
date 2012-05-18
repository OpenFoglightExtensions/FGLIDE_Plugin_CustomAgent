// The following are special reserved parameters which are always available to scripts (see documentation for details)
def mappings = ["ASPContainer":"aspGroups",
"ASPGroup":"properties","PropSecondaryASP":"cols"]

def objs = parent.get(mappings[parent.type.name],null)
if (objs == null) {
objs = [] as List
parent.store(mappings[parent.type.name],objs,null)
}
objs.add(child)
return child