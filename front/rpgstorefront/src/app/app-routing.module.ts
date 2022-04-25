import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SeeProductsComponent} from "./components/see-products/see-products.component";
import {CreateProductComponent} from "./components/create-product/create-product.component";
import {ProductDetailsComponent} from "./components/product-details/product-details.component";
import {UpdateProductComponent} from "./components/update-product/update-product.component";


const routes: Routes = [
  {path:'products',component:SeeProductsComponent},
  {path:'create-product',component:CreateProductComponent},
  {path:'product-details/:id',component:ProductDetailsComponent},
  {path:'update-product/:id',component:UpdateProductComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
