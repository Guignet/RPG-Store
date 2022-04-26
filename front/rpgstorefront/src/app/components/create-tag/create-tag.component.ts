import { Component, OnInit } from '@angular/core';
import {Product} from "../../models/product";
import {FormBuilder, FormGroup} from "@angular/forms";
import {ProductService} from "../../services/product.service";
import {Location} from "@angular/common";
import {Router} from "@angular/router";
import {CreateProduct} from "../../models/create-product";
import {Tag} from "../../models/tag";
import {TagService} from "../../services/tag-service";
import {CreateTag} from "../../models/create-tag";

@Component({
  selector: 'app-create-tag',
  templateUrl: './create-tag.component.html',
  styleUrls: ['./create-tag.component.css']
})
export class CreateTagComponent implements OnInit {
  submitted: boolean = false;

  tag?: Tag;

  createTagForm: FormGroup = this.fb.group({
    title: '',
    description: ''
  });

  constructor(private tagService: TagService,
              private fb: FormBuilder,
              private location: Location,
              private router: Router) { }

  onSubmit() {
    this.submitted = true;

    if (this.createTagForm.invalid) {
      return;
    }

    let tag: CreateTag = {
      title: this.createTagForm.value.title,
      description: this.createTagForm.value.description
    };

    this.tagService.create(tag)
      .subscribe({
        next: ok => {
        }
      });
    this.router.navigate(['/tags']);
  }

  get f() {
    return this.createTagForm.controls;
  }

  back(): void {
    this.location.back()
  }

  ngOnInit(): void {
  }

}
