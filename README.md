# okbu-progarming-nootbook

#startup
1 - Open a commad terminal
2 - cd to okbu-progaming-nootbook
3 - Pull from Github
```
git pull
```
4 - Checkout your branch (example first time)
```
git checkout -b class-basics-1 #for first tome
```
or
```
git checkout class-basics-1
```
5 - go to the directory for the class
```
cd computer-science-1
```
6 - if no project if created, run the following:
```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

OBU Progarming Notebook : C:\Users\cpayn

git status
git add
git commit -m ""
git push


Server template
```
const express = require("express"); // ALWAYS
const bodyParser = require("body-parser");
const app = express(); //ALWAYS

app.use(express.json());
app.use(bodyParser.json());


//Start a server
app.listen(3000, function(){
    console.log("listening on port 3000");
})
```
[GitHub / git](computer-science-1/README.md)
