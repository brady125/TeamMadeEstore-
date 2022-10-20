import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user'
import { HttpErrorResponse } from '@angular/common/http';
import { ThisReceiver } from '@angular/compiler';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  errorMessage = "";
  display = "none";
  // user: User = {username: "", password: ""}
  private user = "";

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  /**
   * Creates a new account and logs user in
   * If account creation fails, an error message is displayed
   * @param username the username of the new account entered by the user
   * @param password the password for the new account entered by the user, must be at least 8 characters long
   * @returns true if the account was created and false if it was not
   */
  createAccount(username: String, password: String): void {
    // trim whitespace
    username = username.trim();
    password = password.trim();
    // use if statements to reveal an errormessage that describes the problem
    if (username == "") {
      this.errorMessage = "You must enter a username.";
    } else if (password.length < 8) {
      this.errorMessage = "Your password must be at least 8 characters long."
    // if both fields are filled in and password meets requirements, 
    } else {
      // then try to create the new account
      this.userService.addUser({username, password} as User).subscribe(
        newUser => {
          this.user = newUser.username;
          if (this.user == username) {
            this.login(this.user)
          }
        }, 
        // if creating the account fails (username not unique), set appropiate error message
        err => {
          this.errorMessage = "This username was already taken.";
        }
      );
    }
    this.display = "initial";
  }

  /**
   * Logs the user into their account and brings them to their home screen (buyer or admin)
   */
  login(username: string): void {
    this.router.navigate(['user-homepage/'+username]);
  }
}
