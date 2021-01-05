import { Component, OnInit } from '@angular/core';
import { PersonService } from "src/app/services/person.service";

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent implements OnInit {

  person = {
    firstName: "",
    lastName: "",
    email: ""
  };

  submitted = false;


  constructor(private personService: PersonService) { }

  ngOnInit(): void {
  }

  savePerson(): void {
    if (this.person.firstName == "")
      alert("The first name is empty");

      if (this.person.lastName == "")
      alert("The last name is empty");

      if (this.person.email == "")
      alert("The email is empty");


    if (this.person.firstName !== "" && this.person.lastName !== "" && this.person.email !== "") {
      const data = {
      firstName : this.person.firstName,
      lastName : this.person.lastName,
      email  : this.person.email
      }

      this.personService.create(data)
      .subscribe(response => {
        console.log(response);
        this.submitted = true;
      },
      error => {
        console.log(error);
      });
    };  
  }


  newPerson(): void {
    this.submitted = false;
    this.person = {
      firstName: "",
      lastName: "",
      email: ""
    };
  }


}
