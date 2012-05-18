def cols = target.get("enums",null)


if(cols==null)
{
	cols=new java.util.ArrayList();

}

def pos = line.get("pos",null)
def li = cols.find{ it.get("pos",null) == pos)
if ( li != null) clos -= li

target.store('enums', cols, null);

return target