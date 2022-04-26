import { Component, OnInit } from '@angular/core';
import {Tag} from "../../models/tag";
import {TagService} from "../../services/tag-service";

@Component({
  selector: 'app-see-tags',
  templateUrl: './see-tags.component.html',
  styleUrls: ['./see-tags.component.css']
})

export class SeeTagsComponent implements OnInit {

  sortByName: string = 'ASC';

  listTags: Tag[] = [];

  constructor(private tagService: TagService) { }

  ngOnInit(): void {

    this.tagService.fetchAll()
      .subscribe({
        next: listTags => {
          this.listTags = listTags;
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
    if (this.sortByName === 'ASC') {
      this.sortByName = 'DESC'
    }
    else {
      this.sortByName = 'ASC'
    }
  }


}
