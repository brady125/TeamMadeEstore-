import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
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

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  login(username: String, password: String) {
    this.userService.loginUser(username, password).subscribe(user => {
      this.user.push(user);
    });
    if (this.user.length != 0) {
      if (this.user[0].username == "admin") {
        ///
      }
    }
  }

}
