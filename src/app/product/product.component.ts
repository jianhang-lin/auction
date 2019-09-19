import { Component, OnInit } from '@angular/core';
import { Product, ProductService } from '../shared/product.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  private products: Observable<Product[]>;

  private imgUrl = '../../assets/320x150.png';

  constructor(
    private productService: ProductService
  ) {}


  ngOnInit() {
    this.products = this.productService.getProducts();
  }

}
