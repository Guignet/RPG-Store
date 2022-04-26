import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SeeTagsComponent } from './see-tags.component';

describe('SeeTagsComponent', () => {
  let component: SeeTagsComponent;
  let fixture: ComponentFixture<SeeTagsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SeeTagsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SeeTagsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
