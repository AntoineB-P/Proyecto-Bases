import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Estadioimagen } from './estadioimagen';

describe('Estadioimagen', () => {
  let component: Estadioimagen;
  let fixture: ComponentFixture<Estadioimagen>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Estadioimagen],
    }).compileComponents();

    fixture = TestBed.createComponent(Estadioimagen);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
