import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DefineEventsectorsComponent } from './define-eventsectors.component';

describe('DefineEventsectorsComponent', () => {
  let component: DefineEventsectorsComponent;
  let fixture: ComponentFixture<DefineEventsectorsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DefineEventsectorsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DefineEventsectorsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
