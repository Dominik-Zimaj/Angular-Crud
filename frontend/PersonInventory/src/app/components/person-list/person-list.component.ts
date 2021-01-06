import { Component, OnInit } from '@angular/core';
import { PersonService } from "src/app/services/person.service";

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.component.html',
  styleUrls: ['./person-list.component.css']
})
export class PersonListComponent implements OnInit {

  /*  In dieser Component werden findet die Auflistung aller Personen statt, sowie können einzelne Personen oder alle Gelöscht werden.
  */  

  person: any;
  currentPerson: any = null;
  currentIndex = -1;
  firstName = "";
  lastName = "";
  email = "";

  constructor(private personService: PersonService) { }

  ngOnInit(): void {
    //Auflistung aller Personen bei initialisierung der Component.
    this.getList();
  }


  getList(): void {
    this.personService.getAll()
      .subscribe(
        data => {
          this.person = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  refreshList(): void {
    this.getList();
    this.currentPerson = null;
    this.currentIndex = -1;
  }

  setActivePerson(person: any, index: any): void {
    this.currentPerson = person;
    this.currentIndex = index;
  }

  removeAllPersons(): void {
    this.personService.deleteAll()
    .subscribe(
      response => {
        console.log(response);
        this.getList();
      },
      error => {
        console.log(error);
      });
  }

  removeActivePerson(index: any): void {
    this.personService.delete(index)
    .subscribe(
      response => {
        console.log(response);
        this.getList();
      },
      error => {
        console.log(error);
      });
      this.refreshList();
  }

  searchByLastName(): void {
    this.personService.findByLastName(this.lastName)
    .subscribe(
      data => {
        this.person = data;
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }

  searchByFirstName(): void {
    this.personService.findByFirstName(this.firstName)
    .subscribe(
      data => {
        this.person = data;
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }
}
