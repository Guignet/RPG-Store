import {LOCALE_ID, NgModule} from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { ProductComponent } from './components/product/product.component';
import { SeeProductsComponent } from './components/see-products/see-products.component';
import { PriceOrderPipe } from './pipes/price-order.pipe';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { TagComponent } from './components/tag/tag.component';
import { SeeTagsComponent } from './components/see-tags/see-tags.component';
import { NameOrderPipe } from './pipes/name-order.pipe';
import { ProductDetailsComponent } from './components/product-details/product-details.component';
import { UpdateProductComponent } from './components/update-product/update-product.component';
import { UpdateTagComponent } from './components/update-tag/update-tag.component';
import { CreateTagComponent } from './components/create-tag/create-tag.component';
import { CreateProductComponent } from './components/create-product/create-product.component';
import { TagDetailsComponent } from './components/tag-details/tag-details.component';
import {registerLocaleData} from "@angular/common";
import localFR from '@angular/common/locales/fr';
import { HomeComponent } from './components/home/home.component';

registerLocaleData(localFR, 'fr');

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    ProductComponent,
    SeeProductsComponent,
    PriceOrderPipe,
    TagComponent,
    SeeTagsComponent,
    NameOrderPipe,
    ProductDetailsComponent,
    UpdateProductComponent,
    UpdateTagComponent,
    CreateTagComponent,
    CreateProductComponent,
    TagDetailsComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [{provide: LOCALE_ID,useValue:"fr"}],
  bootstrap: [AppComponent]
})
export class AppModule { }
