import { ComponentFixture, TestBed } from '@angular/core/testing';
import { IntroBalon } from './introbalon';

describe('IntroBalon', () => {
  let component: IntroBalon;
  let fixture: ComponentFixture<IntroBalon>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [IntroBalon],
    }).compileComponents();

    fixture = TestBed.createComponent(IntroBalon);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
