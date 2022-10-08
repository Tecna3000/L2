

fichier = open("meteo.data","r")
table = ""
for line in fichier:
    annee = line[0:4]
    print(annee)
    if annee not in table :
        table+= "\n"
        table+=  "<th> " + annee + " </th>" 
        table+= "\n"
        table += "<tr>"
        table+= "\n"
    table+= "\n"
    table+=" <td> " + line[5] + " </td>"
    table += " \n"
table += "</tr>"
#print (table)
 
fichier.close()

with open("new.html","w") as l :
     l.write(table)
