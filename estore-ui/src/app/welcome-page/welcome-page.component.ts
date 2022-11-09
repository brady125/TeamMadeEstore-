import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-welcome-page',
  templateUrl: './welcome-page.component.html',
  styleUrls: ['./welcome-page.component.css']
})
export class WelcomePageComponent {

  constructor(private route: ActivatedRoute, private router: Router) { }
  

  goto_login() {
    this.router.navigate(['login']);
  }

  goto_signup() {
    this.router.navigate(['sign-up']);
  }

}
