# be-task-junior-dev

# Backend

Ho modificato il pom.xml, in modo da far esportare il war del progetto direttamente sotto la cartella webapps del mio Tomcat.

# Frontend

Siccome ho usato Nginx per creare dei virtual host per il frontend e il backend, nel file conf/conf.json del frontend si può cambiare il base url dell'api, mettendo "http://localhost:8080/amarisApp/" per chiamare direttamente la webapp deployata sul Tomcat.

# Nginx

Ho usato Nginx per creare dei virtual host, in modo da poter utilizzare dei DNS fake, per simulare un ambiente di produzione. Per testare il progetto senza Nginx, bisogna effettuare la modifica appena illustrata.
