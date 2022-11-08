import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-checkout-page',
  templateUrl: './checkout-page.component.html',
  styleUrls: ['./checkout-page.component.css']
  
})
export class CheckoutPageComponent implements OnInit {

  username: string = ""

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
    var url = this.router.url.split("/")
    this.username = url.pop()!
  }

}
