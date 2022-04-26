import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProductService} from "../../services/product.service";
import {Router} from "@angular/router";
import {CreateProduct} from "../../models/create-product";
import {Location} from "@angular/common";

@Component({
  selector: 'app-create-product',
  templateUrl: './create-product.component.html',
  styleUrls: ['./create-product.component.css']
})
export class CreateProductComponent implements OnInit {

  submitted: boolean = false;

  product?: Product;

  createProductForm: FormGroup = this.fb.group({
    title: '',
    description: '',
    quantity: '',
    price: '',
    seller: '',
    pictures: [],
    tags: []
  });

  constructor(private productService: ProductService,
              private fb: FormBuilder,
              private location: Location,
              private router: Router) { }

  onSubmit() {
    this.submitted = true;

    if (this.createProductForm.invalid) {
      return;
    }

    let product: CreateProduct = {
      title: this.createProductForm.value.title,
      description: this.createProductForm.value.description,
      quantity: this.createProductForm.value.quantity,
      price: this.createProductForm.value.price,
      seller: this.createProductForm.value.seller,
      pictures: [this.createProductForm.value.pictures],
      tags: [this.createProductForm.value.tags]
    };

    this.productService.create(product)
      .subscribe({
        next: ok => {
        }
      });
    this.router.navigate(['/products']);
  }

  get f() {
    return this.createProductForm.controls;
  }

  back(): void {
    this.location.back()
  }

  ngOnInit(): void {
  }

}
