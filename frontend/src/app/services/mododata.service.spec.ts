import { TestBed } from '@angular/core/testing';

import { MododataService } from './mododata.service';

describe('MododataService', () => {
  let service: MododataService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MododataService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
