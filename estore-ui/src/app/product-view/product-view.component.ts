import { Component, Input, OnInit } from '@angular/core';
import { Product } from "../product";
import { ProductService } from '../product.service';

@Component({
  selector: 'app-product-view',
  templateUrl: './product-view.component.html',
  styleUrls: ['./product-view.component.css']
})
export class ProductViewComponent implements OnInit {
  @Input() product!: Product;
  deleted = false

  constructor(private productService: ProductService) { }

  ngOnInit(): void {
  }

  delete() {
    this.productService.deleteProduct(this.product.id).subscribe(_ => this.deleted = true)
  }

}
