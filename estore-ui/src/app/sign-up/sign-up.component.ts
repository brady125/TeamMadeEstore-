import { Component, AfterViewInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user'
import anime from 'animejs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements AfterViewInit {
  errorMessage = "";
  display = "none";
  float_up = null;
  signup_err = null;

  ngAfterViewInit(): void {
    anime({
      targets: '.signup-container',
      translateY: 5,
      direction: 'alternate',
      loop: true,
      autoplay: true,
      easing: 'easeInOutQuad',
      duration: 1000
    });

    this.float_up = anime({
      targets: '.signup-container',
      translateY: [10, -1000],
      rotate: function() { return anime.random(-360, 360); },
      direction: 'normal',
      autoplay: false,
      easing: 'easeInOutQuad',
      duration: 3500
    });

    this.signup_err = anime({
      targets: '.login-container',
      translateX: 30,
      direction: 'alternate',
      loop: false,
      autoplay: false,
      easing: 'easeInOutQuad',
      duration: 100
    });
  }

  constructor(private userService: UserService, 
    private route: ActivatedRoute, private router: Router,
    private _snackBar: MatSnackBar) { }

  public canDeactivate(): Promise<boolean> {
    return this.float_up.finished; 
  }

  /**
   * Creates a new account and logs user in
   * If account creation fails, an error message is displayed
   * @param username the username of the new account entered by the user
   * @param password the password for the new account entered by the user, must be at least 8 characters long
   * @returns true if the account was created and false if it was not
   */
  createAccount(username: string, password: string): void {
    // trim whitespace
    username = username.trim();
    password = password.trim();
    // use if statements to reveal an errormessage that describes the problem
    if (username === "" && password === ""){
      this.signup_err.play();
      this._snackBar.open("Please enter a username and password", '', { duration: 2000 });
    } 
    else if (username !== "" && password.length < 8) {
      this.signup_err.play();
      this._snackBar.open("Your password must be at least 8 characters long.", '', { duration: 2000 });
    }
    else if (username === "" && password !== ""){
      this.signup_err.play();
      this._snackBar.open("Please enter your username", '', { duration: 2000 });
    }
    // if both fields are filled in and password meets requirements, 
    else {
      this.float_up.play();
      // then try to create the new account
      document.getElementById('signup-button').onclick = this.float_up.play;
      this.userService.addUser({username, password} as User).subscribe(
        newUser => {
          this.login(newUser.username)
        }, 
        // if creating the account fails (username not unique), set appropiate error message
        err => {
          this._snackBar.open("This username was already taken", '', { duration: 2000 });
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
