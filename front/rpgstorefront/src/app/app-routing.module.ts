import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SeeProductsComponent} from "./components/see-products/see-products.component";
import {CreateProductComponent} from "./components/create-product/create-product.component";
import {ProductDetailsComponent} from "./components/product-details/product-details.component";
import {UpdateProductComponent} from "./components/update-product/update-product.component";
import {SeeTagsComponent} from "./components/see-tags/see-tags.component";
import {CreateTagComponent} from "./components/create-tag/create-tag.component";
import {TagDetailsComponent} from "./components/tag-details/tag-details.component";
import {UpdateTagComponent} from "./components/update-tag/update-tag.component";
import {HomeComponent} from "./components/home/home.component";


const routes: Routes = [
  {path:'',component:HomeComponent},

  {path:'products',component:SeeProductsComponent},
  {path:'create-product',component:CreateProductComponent},
  {path:'product-details/:id',component:ProductDetailsComponent},
  {path:'update-product/:id',component:UpdateProductComponent},

  {path:'tags',component:SeeTagsComponent},
  {path:'create-tag',component:CreateTagComponent},
  {path:'tag-details/:id',component:TagDetailsComponent},
  {path:'update-tag/:id',component:UpdateTagComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
