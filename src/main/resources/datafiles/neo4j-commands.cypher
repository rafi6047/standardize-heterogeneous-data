LOAD CSV WITH HEADERS FROM "file:///datafiles/ports.csv" AS row CREATE (port:Port { classType: row.classType, defaultPort: row.defaultPort, keyword: row.serviceName, serviceName: row.serviceName, description: row.serviceDescription, entry: row.entry, source: row.source});

LOAD CSV WITH HEADERS FROM "file:///datafiles/operatingSystems.csv" AS row CREATE (operatingSystem:OperatingSystem { classType: row.classType, keyword: row.name, name: row.name,version: row.version,entry: row.entry,source: row.source,tags: row.tags,ESP_DEP: row.ESP_DEP,ASLR: row.ASLR,SEHOP: row.SEHOP,UAC: row.UAC,DNSSEC: row.DNSSEC,Encryption: row.Encryption,Cryptography: row.Cryptography,Firewall_Defender: row.Firewall_Defender,Authentication: row.Authentication});

LOAD CSV WITH HEADERS FROM "file:///datafiles/misc.csv" AS row CREATE (misc:Misc { classType: row.classType, keyword: row.name, description: row.description, name: row.name,entry: row.entry,source: row.source,tags: row.tags});

CREATE (commonPorts:CommonPorts{classType: "CommonPorts"})
CREATE (ontologyRoot:OntologyRoot{classType: "OntologyRoot"})
CREATE (software:Software{classType: "Software"})
CREATE (client:Client{classType: "Client"})
CREATE (operatingSystem:OperatingSystem{classType: "OperatingSystem"})
CREATE (windows:OperatingSystem{classType: "Windows"})
CREATE (bsd:OperatingSystem{classType: "BSD"})
CREATE (macOS:OperatingSystem{classType: "macOS"})
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
  (macOS)-[:IS_A]->(operatingSystem),
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
  (commonPorts)-[:IS_PART_OF]->(linux);

MATCH (commonPorts:CommonPorts),(a:Port) CREATE (a)-[r:IS_A]->(commonPorts);

MATCH (osChild:OperatingSystem {classType:'WindowsOperatingSystem'}), (osRoot:OperatingSystem {classType:'Windows'}) CREATE (osChild)-[:IS_A]->(osRoot);
MATCH (osChild:OperatingSystem {classType:'LinuxOperatingSytem'}), (osRoot:OperatingSystem {classType:'Linux'}) CREATE (osChild)-[:IS_A]->(osRoot);
MATCH (osChild:OperatingSystem {classType:'macOperatingSytem'}), (osRoot:OperatingSystem {classType:'macOS'}) CREATE (osChild)-[:IS_A]->(osRoot);
MATCH (osChild:OperatingSystem {classType:'bsdOperatingSytem'}), (osRoot:OperatingSystem {classType:'BSD'}) CREATE (osChild)-[:IS_A]->(osRoot);

MATCH (os:OperatingSystem), (client:Client) where os.tags CONTAINS 'client' CREATE (os)-[:IS_A]->(client);
MATCH (os:OperatingSystem), (server:Server) where os.tags CONTAINS 'server' CREATE (os)-[:IS_A]->(server);
MATCH (vs:VulnerabilityScanner), (misc:Misc) where misc.tags CONTAINS 'VulnerabilityScanner' CREATE (misc)-[:IS_A]->(vs);
MATCH (os:OperatingSystem {classType:'Windows'}), (misc:Misc) where misc.tags CONTAINS 'WindowsPackage' CREATE (misc)-[:IS_A_PACKAGE_OF]->(os);

MATCH (os:OperatingSystem {classType:'Windows'}), (misc:Misc) where misc.tags CONTAINS 'clientOfWindows' CREATE (misc)-[:IS_A_PACKAGE_OF]->(os);
MATCH (os:OperatingSystem {classType:'Linux'}), (misc:Misc) where misc.tags CONTAINS 'clientOfLinux' CREATE (misc)-[:IS_A_PACKAGE_OF]->(os);
MATCH (os:OperatingSystem {classType:'macOS'}), (misc:Misc) where misc.tags CONTAINS 'clientOfMac' CREATE (misc)-[:IS_A_PACKAGE_OF]->(os);

CREATE INDEX ON :Port(defaultPort);
CREATE INDEX ON :Port(serviceName);
CREATE INDEX ON :OperatingSystem(name);
MATCH (n) SET n:AllNodes;
CREATE INDEX ON :AllNodes(keyword);