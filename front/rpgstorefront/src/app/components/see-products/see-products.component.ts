import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {TagFilterPipe} from "../../pipes/tag-filter.pipe";
import {PriceOrderPipe} from "../../pipes/price-order.pipe";

@Component({
  selector: 'app-see-products',
  templateUrl: './see-products.component.html',
  styleUrls: ['./see-products.component.css']
})

export class SeeProductsComponent implements OnInit {

  sortByPriceValue: string = 'asc';
  searchText: string = "";
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

  priceFunction(param: string) {
    // if (param.toLocaleLowerCase() == "asc") {
    //   this.sort.price = 'asc';
    //   console.log('asc');
    // }
    // else if  (param.toLocaleLowerCase() == "desc") {
    //   this.sort.price = 'desc';
    //   console.log('desc');
    // }
    this.sort.price = param.toLocaleLowerCase();
    console.log(this.sort.price);
  }


  rareFunction(param: string) {
    // if (param.toLocaleLowerCase() == "")
    //   this.sort.rare = '';
    // else if  (param.toLocaleLowerCase() == "common")
    //   this.sort.rare = 'common';
    // else if  (param.toLocaleLowerCase() == "uncommon")
    //   this.sort.rare = 'uncommon';
    // else if  (param.toLocaleLowerCase() == "rare")
    //   this.sort.rare = 'rare';
    // else if  (param.toLocaleLowerCase() == "legend")
    //   this.sort.rare = 'legend';

    this.sort.rare = param.toLocaleLowerCase();
    this.searchText = param.toLowerCase();
  }



}

