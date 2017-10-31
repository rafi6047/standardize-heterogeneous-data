LOAD CSV WITH HEADERS FROM "file:///datafiles/ports.csv" AS row CREATE (Port:Port { classType: row.classType, defaultPort: row.defaultPort, serviceName: row.serviceName, serviceDescription: row.serviceDescription, entry: row.entry, source: row.source})

CREATE (commonPorts:CommonPorts{classType: "CommonPorts"})
CREATE (ontologyRoot:OntologyRoot{classType: "OntologyRoot"})
CREATE (software:Software{classType: "Software"})
CREATE (client:Client{classType: "Client"})
CREATE (operatingSystem:OperatingSystem{classType: "OperatingSystem"})
CREATE (windows:OperatingSystem{classType: "Windows"})
CREATE (bsd:OperatingSystem{classType: "BSD"})
CREATE (osx:OperatingSystem{classType: "OSX"})
CREATE (linux:OperatingSystem{classType: "Linux"})
CREATE (vulnerabilityScanner:VulnerabilityScanner{classType: "VulnerabilityScanner"})
CREATE (antivirus:Antivirus{classType: "Antivirus"})
CREATE (firewall:Firewall{classType: "Firewall"})
CREATE (hdis:HDIS{classType: "HDIS"})
CREATE (server:Server{classType: "Server"})
CREATE (databaseServer:Server{classType: "DatabaseServer"})
CREATE (applicationServer:Server{classType: "ApplicationServer"})
CREATE
  (commonPorts)-[:IS_PART_OF]->(ontologyRoot),
  (software)-[:IS_A]->(ontologyRoot),
  (client)-[:IS_A]->(software),
  (operatingSystem)-[:IS_A]->(software),
  (windows)-[:IS_A]->(operatingSystem),
  (bsd)-[:IS_A]->(operatingSystem),
  (osx)-[:IS_A]->(operatingSystem),
  (linux)-[:IS_A]->(operatingSystem),
  (vulnerabilityScanner)-[:IS_A]->(software),
  (antivirus)-[:IS_A]->(software),
  (firewall)-[:IS_A]->(software),
  (hdis)-[:IS_A]->(software),
  (server)-[:IS_A]->(software),
  (databaseServer)-[:IS_A]->(server),
  (applicationServer)-[:IS_A]->(server),
  (commonPorts)-[:IS_PART_OF]->(windows),
  (commonPorts)-[:IS_PART_OF]->(bsd),
  (commonPorts)-[:IS_PART_OF]->(osx),
  (commonPorts)-[:IS_PART_OF]->(linux)
WITH commonPorts MATCH (a:Port),(commonPorts) CREATE (a)-[r:IS_A]->(commonPorts)
