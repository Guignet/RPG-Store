import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-filters',
  templateUrl: './filters.component.html',
  styleUrls: ['./filters.component.css']
})
export class FiltersComponent implements OnInit {

  sort = {
    price: 'asc',
    rare: ''
  }

  constructor() { }

  ngOnInit(): void {
  }


  price(param: string) {
    if (param.toLocaleLowerCase() == "asc")
      this.sort.price = 'asc';
    else if  (param.toLocaleLowerCase() == "desc")
      this.sort.price = 'desc';
  }


  rare(param: string) {
    if (param.toLocaleLowerCase() == "all")
      this.sort.rare = '';
    else if  (param.toLocaleLowerCase() == "common")
      this.sort.rare = 'common';
    else if  (param.toLocaleLowerCase() == "uncommon")
      this.sort.rare = 'uncommon';
    else if  (param.toLocaleLowerCase() == "rare")
      this.sort.rare = 'rare';
    else if  (param.toLocaleLowerCase() == "legend")
      this.sort.rare = 'legend';
  }

}
