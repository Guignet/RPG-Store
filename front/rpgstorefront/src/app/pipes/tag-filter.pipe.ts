import { Pipe, PipeTransform } from '@angular/core';
import {Product} from "../models/product";

@Pipe({
  name: 'tagFilter'
})
export class TagFilterPipe implements PipeTransform {

  transform(items: Product[], searchText: string): any[] {
    if(!items) return [];
    if(!searchText) return items;

    searchText = searchText.toLowerCase();
    return items.filter( it => {
      for(var tag of it.listTags){

      }
    });
  }

}
