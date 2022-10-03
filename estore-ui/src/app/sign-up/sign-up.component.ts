import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  errorMessage = "";
  display = "none";

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  /**
   * Creates a new account and logs user in
   * If account creation fails, an error message is displayed
   * @param username the username of the new account entered by the user
   * @param password the password for the new account entered by the user, must be at least 8 characters long
   * @returns true if the account was created and false if it was not
   */
  createAccount(username: String, password: String): boolean {
    // if both fields are filled in and password meets requirements, 
    if (username != "" && password.length >= 8) {
      // then try to create the new account
      if (this.userService.createAccount(username, password)) {
        // log in user
        return true;
      // if creating the account fails (username not unique), set appropiate error message
      } else {
        this.errorMessage = "This username was already taken.";
      }
    // use else if statements to reveal an errormessage that describes the problem
    } else if (username == "") {
      this.errorMessage = "You must enter a username.";
    } else if (password.length < 8) {
      this.errorMessage = "Your password must be at least 8 characters long."
    }
    this.display = "initial";
    return false;
  }

  /**
   * Logs the user into their account and brings them to their home screen (buyer or admin)- might need parameter
   */
  login(): void {
    
  }

}
