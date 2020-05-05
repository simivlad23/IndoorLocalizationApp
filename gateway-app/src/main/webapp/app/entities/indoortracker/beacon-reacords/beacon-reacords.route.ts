import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBeaconReacords, BeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';
import { BeaconReacordsService } from './beacon-reacords.service';
import { BeaconReacordsComponent } from './beacon-reacords.component';
import { BeaconReacordsDetailComponent } from './beacon-reacords-detail.component';
import { BeaconReacordsUpdateComponent } from './beacon-reacords-update.component';

@Injectable({ providedIn: 'root' })
export class BeaconReacordsResolve implements Resolve<IBeaconReacords> {
  constructor(private service: BeaconReacordsService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBeaconReacords> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((beaconReacords: HttpResponse<BeaconReacords>) => {
          if (beaconReacords.body) {
            return of(beaconReacords.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BeaconReacords());
  }
}

export const beaconReacordsRoute: Routes = [
  {
    path: '',
    component: BeaconReacordsComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BeaconReacords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: BeaconReacordsDetailComponent,
    resolve: {
      beaconReacords: BeaconReacordsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BeaconReacords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: BeaconReacordsUpdateComponent,
    resolve: {
      beaconReacords: BeaconReacordsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BeaconReacords'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: BeaconReacordsUpdateComponent,
    resolve: {
      beaconReacords: BeaconReacordsResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'BeaconReacords'
    },
    canActivate: [UserRouteAccessService]
  }
];
