import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MotorcycleList } from './motorcycle-list';

describe('MotorcycleList', () => {
  let component: MotorcycleList;
  let fixture: ComponentFixture<MotorcycleList>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MotorcycleList]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MotorcycleList);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
