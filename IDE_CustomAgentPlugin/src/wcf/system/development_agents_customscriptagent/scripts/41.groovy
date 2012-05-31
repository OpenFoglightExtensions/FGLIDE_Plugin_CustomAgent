package system._development_agents_customscriptagent.scripts;
def CDATASTART="![CDATA["
def CDATAEND = "]]"

name = name.replace(" ","")
name = name.replace(".","_")

def xml = """
<topologyExport version='1.0.0'>
  <objectInstance type='IDE_CAR_ExportContainer' id='0'>
    <stringProperty name='name'><${CDATASTART}${containerName}${CDATAEND}></stringProperty>
    <objectProperty name='definitionGroups'>
      <objectInstance type='IDE_Delegated_Group' id='1'>
        <stringProperty name='name'><${CDATASTART}agents${CDATAEND}></stringProperty>
        <objectProperty name='container'>
          <objectReference refId='0' />
        </objectProperty>
        <objectProperty name='includes'>
          <objectInstance type='IDE_CustomScriptagent' id='2'>
            <stringProperty name='name'><${CDATASTART}${name}${CDATAEND}></stringProperty>
            <stringProperty name='ci_id'><${CDATASTART}${name}${CDATAEND}></stringProperty>
            <objectProperty name='parent'>
              <objectReference refId='1' />
            </objectProperty>
            <stringProperty name='typeNameAgent'><${CDATASTART}${name}_Agent${CDATAEND}></stringProperty>
            <stringProperty name='typeNameTable'><${CDATASTART}${name}_Agent_Table${CDATAEND}></stringProperty>
            <stringProperty name='typeNameTableDataPrefix'><${CDATASTART}${name}_Agent_Table_${CDATAEND}></stringProperty>
<stringProperty name='aspDefinition'><${CDATASTART}<properties></properties>${CDATAEND}></stringProperty>
            <!--<stringProperty name='command'><${CDATASTART}${name}.cmd${CDATAEND}></stringProperty>-->
              <objectProperty name='agentPackages'>
              <objectInstance type='IDE_CA_AgentFiles' id='3'>
                <stringProperty name='name'><${CDATASTART}default${CDATAEND}></stringProperty>
                <objectProperty name='agentDefinition'>
                  <objectReference refId='2' />
                </objectProperty>
              </objectInstance>
            </objectProperty>
          </objectInstance>
        </objectProperty>
      </objectInstance>
    </objectProperty>
  </objectInstance>
</topologyExport>
"""
println xml
fglide_importTopologyData(xml)
return true