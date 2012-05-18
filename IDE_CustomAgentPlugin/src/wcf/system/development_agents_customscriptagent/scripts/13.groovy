mvs = container.get('aspGroups', null);

if(mvs==null)
{
	mvs=new java.util.ArrayList();
	container.store('aspGroups', mvs, null);
}

mvs.add(group);

"The metric value is added";