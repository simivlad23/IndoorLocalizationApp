import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { BeaconReacordsService } from 'app/entities/indoortracker/beacon-reacords/beacon-reacords.service';
import { IBeaconReacords, BeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';

describe('Service Tests', () => {
  describe('BeaconReacords Service', () => {
    let injector: TestBed;
    let service: BeaconReacordsService;
    let httpMock: HttpTestingController;
    let elemDefault: IBeaconReacords;
    let expectedResult: IBeaconReacords | IBeaconReacords[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(BeaconReacordsService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new BeaconReacords(0, currentDate, 0, 0, 0, 0, 0);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            timeReacord: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a BeaconReacords', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            timeReacord: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeReacord: currentDate
          },
          returnedFromService
        );

        service.create(new BeaconReacords()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a BeaconReacords', () => {
        const returnedFromService = Object.assign(
          {
            timeReacord: currentDate.format(DATE_FORMAT),
            readRssi: 1,
            meanRssi: 1,
            smootRssi: 1,
            smootDistance: 1,
            meanDistance: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeReacord: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of BeaconReacords', () => {
        const returnedFromService = Object.assign(
          {
            timeReacord: currentDate.format(DATE_FORMAT),
            readRssi: 1,
            meanRssi: 1,
            smootRssi: 1,
            smootDistance: 1,
            meanDistance: 1
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            timeReacord: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a BeaconReacords', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
