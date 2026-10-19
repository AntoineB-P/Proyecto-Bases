import { ComponentFixture, TestBed } from '@angular/core/testing';
import { BalonHero } from './balon-hero';

describe('BalonHero', () => {
  let component: BalonHero;
  let fixture: ComponentFixture<BalonHero>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [BalonHero],
    }).compileComponents();

    fixture = TestBed.createComponent(BalonHero);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
