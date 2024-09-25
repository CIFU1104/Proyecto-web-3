import { TestBed } from '@angular/core/testing';
import { logInComponent } from './logIn.component';

describe('logInComponent', () => {
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [logInComponent],
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(logInComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have the 'frontEndWeb' title`, () => {
    const fixture = TestBed.createComponent(logInComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('frontEndWeb');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(logInComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement as HTMLElement;
    expect(compiled.querySelector('h1')?.textContent).toContain('Hello, frontEndWeb');
  });
});