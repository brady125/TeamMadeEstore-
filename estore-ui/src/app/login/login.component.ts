import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user'
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  errorMessage = "";
  display = "none";
  user: User = {username: "", password: ""};

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  login(username: String, password: String) {
    this.userService.loginUser(username, password).subscribe(user => this.handleLogin(user), err => {this.errorMessage = "Username and/or password is incorrect.";
    this.display = "initial";});
  }

  handleLogin(user: User) {
    this.user = user;
    if (this.user.username == "admin") {
        this.router.navigate(['admin-homepage']);
    } else {
      this.errorMessage = "user homepage navigation";
      this.display = "initial";
      // this.router.navigate(['user-homepage']);
    }
  }
}
