import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  errorMessage = "";
  display = "none";
  user: User[] = [];

  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
  }

  login(username: String, password: String): void {
    username = username.trim();
    password = password.trim();

    if (this.user != null){
      if (username == "admin") {
        this.router.navigate(['admin-homepage'])
      } else {
        this.router.navigate(['user-homepage'])
      }
    }
  }

}
