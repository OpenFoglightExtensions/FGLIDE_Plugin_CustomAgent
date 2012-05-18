package system._development_agents_customscriptagent.scripts;

def token = xmlString.split("<") as List
def allToken = token[1..-1]

def result = ""
def indent = 0

allToken.each{ t->
   if (t.startsWith("/")) indent = indent -1
   String t2 = "<"+t+"\n"
   result += t2
   for (def x = 0; x < indent;x++) result+="    "
   if (!t.startsWith("/"))  indent++  
}

return result