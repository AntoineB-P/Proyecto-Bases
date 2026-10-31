import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BarraLateral } from './barralateral';

describe('BarraLateral', () => {
  let component: BarraLateral;
  let fixture: ComponentFixture<BarraLateral>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BarraLateral],
    }).compileComponents();

    fixture = TestBed.createComponent(BarraLateral);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
