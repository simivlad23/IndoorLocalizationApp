import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';

type EntityResponseType = HttpResponse<IBeaconReacords>;
type EntityArrayResponseType = HttpResponse<IBeaconReacords[]>;

@Injectable({ providedIn: 'root' })
export class BeaconReacordsService {
  public resourceUrl = SERVER_API_URL + 'services/indoortracker/api/beacon-reacords';

  constructor(protected http: HttpClient) {}

  create(beaconReacords: IBeaconReacords): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beaconReacords);
    return this.http
      .post<IBeaconReacords>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(beaconReacords: IBeaconReacords): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(beaconReacords);
    return this.http
      .put<IBeaconReacords>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IBeaconReacords>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IBeaconReacords[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(beaconReacords: IBeaconReacords): IBeaconReacords {
    const copy: IBeaconReacords = Object.assign({}, beaconReacords, {
      timeReacord:
        beaconReacords.timeReacord && beaconReacords.timeReacord.isValid() ? beaconReacords.timeReacord.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.timeReacord = res.body.timeReacord ? moment(res.body.timeReacord) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((beaconReacords: IBeaconReacords) => {
        beaconReacords.timeReacord = beaconReacords.timeReacord ? moment(beaconReacords.timeReacord) : undefined;
      });
    }
    return res;
  }
}
