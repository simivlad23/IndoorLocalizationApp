import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';

@Component({
  selector: 'jhi-beacon-reacords-detail',
  templateUrl: './beacon-reacords-detail.component.html'
})
export class BeaconReacordsDetailComponent implements OnInit {
  beaconReacords: IBeaconReacords | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beaconReacords }) => (this.beaconReacords = beaconReacords));
  }

  previousState(): void {
    window.history.back();
  }
}
