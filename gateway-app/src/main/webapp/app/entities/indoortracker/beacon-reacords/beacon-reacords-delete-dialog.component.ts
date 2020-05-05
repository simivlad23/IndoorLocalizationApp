import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';
import { BeaconReacordsService } from './beacon-reacords.service';

@Component({
  templateUrl: './beacon-reacords-delete-dialog.component.html'
})
export class BeaconReacordsDeleteDialogComponent {
  beaconReacords?: IBeaconReacords;

  constructor(
    protected beaconReacordsService: BeaconReacordsService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.beaconReacordsService.delete(id).subscribe(() => {
      this.eventManager.broadcast('beaconReacordsListModification');
      this.activeModal.close();
    });
  }
}
