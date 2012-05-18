def cols = target.get("enums",null)


if(cols==null)
{
	cols=new java.util.ArrayList();

}


def sort = cols.size()+1
def obj = functionHelper.createDataObject("development_agents_customscriptagent:EnumValue","none",null)

obj.store("id","n/a",null)
obj.store("pos",sort,null)

cols.add(obj)
	target.store('enums', cols, null);

return target