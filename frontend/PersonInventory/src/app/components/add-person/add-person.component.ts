import { Component, OnInit } from '@angular/core';
import { PersonService } from "src/app/services/person.service";

@Component({
  selector: 'app-add-person',
  templateUrl: './add-person.component.html',
  styleUrls: ['./add-person.component.css']
})
export class AddPersonComponent implements OnInit {

  /*  Diese Component beinhaltet eine Methode um die eingegebenen Attribute einer neuen Person an den Service weiterzuleiten 
      und um die Werte wieder leer zu setzen wenn eine neue Person hinzugefügt werden soll. 
  */

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
    //Check ob die Felder nicht leer sind und nur dann die Person zu speichern.

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
