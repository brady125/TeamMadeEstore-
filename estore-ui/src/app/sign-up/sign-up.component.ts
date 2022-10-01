import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  errorMessage = "";
  display = "none";

  constructor() { }

  ngOnInit(): void {
  }

  createAccount(): boolean {
    /**
     * Checks whether an account can be creeated with the information inputed
     * Returns true and logs the user in if it suceeds
     * Returns false and shows an error message if it fails
    */
    // if both fields are filled in, the user name is unique, and password meets requirements(?), 
      // then create the new account and log the user
      // return true
    // use else if statements to reveal an errormessage that describes the problem
    this.errorMessage = "This is a placeholder error message";
    this.display = "initial";
      // return false
    return false;
  }

  login(): void {
    // logs the user into their account and brings them to their home screen (buyer or admin)- might need parameter
  }

}
