import { Component, AfterViewInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user'
import { ActivatedRoute, Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import anime from 'animejs';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements AfterViewInit {
  private display = "none";
  private float_up = null;
  private login_err = null;

  ngAfterViewInit(): void {

    this.login_err = anime({
      targets: '.login-container',
      translateY: 5,
      direction: 'alternate',
      loop: true,
      autoplay: true,
      easing: 'easeInOutQuad',
      duration: 1000
    });

    this.float_up = anime({
      targets: '.login-container',
      translateY: [10, -1000],
      rotate: function() { return anime.random(-360, 360); },
      direction: 'normal',
      autoplay: false,
      easing: 'easeInOutQuad',
      duration: 3000
    });

    this.login_err = anime({
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

  public deactivate() {
    this.float_up.finished = true;
  }
  
  login(username: string, password: string) {
    this.userService.loginUser(username, password).subscribe(user => 
        {this.handleLogin(user);}, 
    err => {
      this.login_err.play();
      this.handleErr(username, password);
      this.display = "initial";
    }
    );
  }

  private handleLogin(user: User) {
    this.float_up.play();
    if (user.username == "admin") {
      this.router.navigate(['admin-homepage']);
    } else {
      this.display = "initial";
      this.router.navigate(['user-homepage/'+user.username]);
    }
  }

  private handleErr(usnm: string, pswd: string) {
    if (usnm === "" && pswd === "")
        this._snackBar.open("Please Enter a username and password", '', { duration: 2000 });
    else if (usnm !== "" && pswd === "")
        this._snackBar.open("Please enter your password", '', { duration: 2000 });
    else if (usnm === "" && pswd !== "")
        this._snackBar.open("Please enter your username", '', { duration: 2000 });
    else
      this._snackBar.open("Username and/or password is incorrect.", '', { duration: 2000 });
  } 
}
