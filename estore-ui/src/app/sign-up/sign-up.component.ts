import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user'
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  errorMessage = "";
  display = "none";
  user: User = {username: "", password: ""}

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
  createAccount(username: String, password: String): boolean {
    // trim whitespace
    username = username.trim();
    password = password.trim();
    // if both fields are filled in and password meets requirements, 
    if (username != "" && password.length >= 8) {
      // then try to create the new account
      this.userService.addUser(username, password).subscribe(newUser => this.user = newUser);
      this.errorMessage = this.user.username;
      this.display = "initial"
      // if (this.user.username != "") {
      //   this.login(this.user)
      //   return true;
      // // if creating the account fails (username not unique), set appropiate error message
      // } else {
      //   this.errorMessage = "This username was already taken.";
      // }
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
   * Logs the user into their account and brings them to their home screen (buyer or admin)
   */
  login(user: User): void {
    this.errorMessage = "logincalled"
    this.display= "initial"
    this.router.navigate(['admin-homepage'])
    // if (user.username == "admin") {
    //   this.router.navigate(['admin-homepage'])
    // } else {
    //   this.router.navigate(['user-homepage'], { username: user.username})
    // }
  }

}
