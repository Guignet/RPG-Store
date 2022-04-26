import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";

@Component({
  selector: 'app-see-products',
  templateUrl: './see-products.component.html',
  styleUrls: ['./see-products.component.css']
})

export class SeeProductsComponent implements OnInit {

  sortByPriceValue: string = 'ASC';

  sort = {
    price: 'asc',
    rare: ''
  }


  listProducts: Product[] = [];

  constructor(private productService: ProductService) { }

  ngOnInit(): void {

    this.productService.fetchAll()
      .subscribe({
        next: listProducts => {
          this.listProducts = listProducts;
        },
        error: err => {
          console.log(err);
        },
        complete: () => {
          console.log('completed');
        }
      });
  }

  sortOrder() {
    if (this.sortByPriceValue === 'ASC') {
      this.sortByPriceValue = 'DESC'
    }
    else {
      this.sortByPriceValue = 'ASC'
    }
  }



}

