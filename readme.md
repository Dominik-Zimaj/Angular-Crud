# Person Inventory

## Übersicht:
Dies ist eine kleine Angular CRUD App, die Personen verwaltet. Auf ein Mock Backend wurde verzichtet und eine Java API geschrieben, die alle Daten in einer "in-memory database" (H2) speichert.

### Das Backend

Die Datenbank lässt sich über "localhost:8080/h2-console" mit folgenden Einstellungen öffnen:  
Driver Class: 	org.h2.Driver  
JDBC URL: 	jdbc:h2:mem:testdb  
User Name: 	sa  
Password:  

Die API hat folgende HTTP Aufrufe definiert:  
GET:	"localhost:8080/api/person"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Listet alle Personen in der Datenbank auf  
GET:	"localhost:8080/api/person/firstname?firstName={String}"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sucht in der Datenbank nach Personen mit dem eingegebenen Vornamen  
GET:	"localhost:8080/api/person/lastname?lastName={String}"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Sucht in der Datenbank nach Personen mit dem eingegebenen Nachnamen  
GET:	"localhost:8080/api/person/{id}"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Zeigt die Details einer Person an  
POST:	"localhost:8080/api/person"	 (JSON Body)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Erstellt eine Person  
PUT:	"localhost:8080/api/person/{id}" (JSON Body)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Updatet eine Person  
DELETE:	"localhost:8080/api/person/&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Löscht alle Personen in der Datenbank  
DELETE: "localhost:8080/api/person/{id}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Löscht nur die Person mit der id  



### Das Frontend 

Die Angular App lässt sich über "localhost:4200" erreichen. Der Link "Person" oben rechts listet alle Personen auf und über den Link "Add" können Personen hinzugefügt werden.
In der Liste lassen sich Personen suchen und anklicken um Details anzusehen und die Person zu bearbeiten oder zu entfernen.  


### Vorgehensweise:

Zu erst wurde das Backend eingerichtet. Die Klasse Person enthält die Attribute ID, Vorname, Nachname und E-Mail. Die Klasse PersonRepository implementiert JPARepository und somit lassen sich standard Methoden direkt einsetzen. Zwei eigene Methoden wurden definiert um nach Vor- und Nachnamen suchen zu können.
Die Klasse PersonController beinhaltet alle HTTP Aufrufe wie oben gelistet.  
  
Für das Frontend wurde ein Angular Projekt initialisiert und über die CLI drei Komponenten und einen Service hinzugefügt.
In der app.module.ts wurden HttpClientModule und FormsModule importiert um Http Aufrufe durchführen zu können und Attribute des Modells in das HTML einbetten zu können. In der app-routing.module.ts wurden vier Routen gesetzt, eine für den standard Pfad und die anderen drei für die Komponenten.
In der app.component.html wurde eine Navbar oben eingefügt für die Navigation. Unter der Navbar befindet sich ein Container, der die Komponenten anzeigen kann. In dem Service (person.service.ts) der angelegt wurde, befinden sich die Http Aufrufe mit den Parametern, die den Aufrufen in der Api entsprechen.  
  
In der add-person Komponente befinden sich die Textfelder um eine Person anlegen zu können und Methoden, um diese Daten an den Service zu übermitteln, der die Http Aufrufe tätigt.  
In der person-list Komponente werden alle Personen aufgelistet und es kann nach Personen (entweder Vor- order Nachname) gefiltert werden. Personen in der Liste werden nur mit Vor- und Nachnamen angezeigt wegen der Übersichtlichkeit. Wird auf eine Person geklickt, werden unterhalt der Liste alle Details der Person angezeigt und es kann diese Person bearbeitet oder entfernt werden. Außerdem kann die gesamte Liste geleert werden.
In der person-details Komponente kann die Person bearbeitet und nochmals gespeichert werden.
