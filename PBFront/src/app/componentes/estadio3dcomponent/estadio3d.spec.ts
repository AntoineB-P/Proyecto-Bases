import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Estadio3d } from './estadio3d';

describe('Estadio3d', () => {
  let component: Estadio3d;
  let fixture: ComponentFixture<Estadio3d>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [Estadio3d],
    }).compileComponents();

    fixture = TestBed.createComponent(Estadio3d);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
