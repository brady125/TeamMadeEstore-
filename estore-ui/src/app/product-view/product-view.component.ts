import { Component, Input, OnInit } from '@angular/core';
import { Product } from "../product";

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {
  @Input() product!: Product;

  constructor() { }

  ngOnInit(): void {
  }


}
