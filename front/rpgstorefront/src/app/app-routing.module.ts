import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {SeeProductsComponent} from "./components/see-products/see-products.component";

const routes: Routes = [
  {path:'products',component:SeeProductsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
