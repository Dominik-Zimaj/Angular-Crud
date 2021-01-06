import { Component, OnInit } from '@angular/core';
import { PersonService } from "src/app/services/person.service";
import { ActivatedRoute, Router } from "@angular/router";

@Component({
  selector: 'app-person-details',
  templateUrl: './person-details.component.html',
  styleUrls: ['./person-details.component.css']
})
export class PersonDetailsComponent implements OnInit {

  /*  In dieser Component kann eine bestehende Person bearbeitet werden. 
  */  

  currentPerson = null;
  message = "";
  

  constructor(
    private personService: PersonService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.message = "";
    //Hier wird die ID der Person aus dem Pfad ausgelesen.
    this.getPerson(this.route.snapshot.paramMap.get('id'));
  }

  getPerson(id: any): void {
    this.personService.get(id)
    .subscribe(
      data => {
        this.currentPerson = data;
        console.log(data);
      },
      error => {
        console.log(error);
      });
  }

  updatePerson(): void {
    this.personService.update(this.currentPerson.id, this.currentPerson)
    .subscribe(
      response => {
        console.log(response);
        this.message = "Person successfully updated";
      },
      error => {
        console.log(error);
      });
  }

  

}
