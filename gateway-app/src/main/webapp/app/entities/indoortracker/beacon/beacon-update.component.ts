import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBeacon, Beacon } from 'app/shared/model/indoortracker/beacon.model';
import { BeaconService } from './beacon.service';

@Component({
  selector: 'jhi-beacon-update',
  templateUrl: './beacon-update.component.html'
})
export class BeaconUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [],
    address: [],
    txPower: [],
    advertistingInterval: []
  });

  constructor(protected beaconService: BeaconService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beacon }) => {
      this.updateForm(beacon);
    });
  }

  updateForm(beacon: IBeacon): void {
    this.editForm.patchValue({
      id: beacon.id,
      name: beacon.name,
      address: beacon.address,
      txPower: beacon.txPower,
      advertistingInterval: beacon.advertistingInterval
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const beacon = this.createFromForm();
    if (beacon.id !== undefined) {
      this.subscribeToSaveResponse(this.beaconService.update(beacon));
    } else {
      this.subscribeToSaveResponse(this.beaconService.create(beacon));
    }
  }

  private createFromForm(): IBeacon {
    return {
      ...new Beacon(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      address: this.editForm.get(['address'])!.value,
      txPower: this.editForm.get(['txPower'])!.value,
      advertistingInterval: this.editForm.get(['advertistingInterval'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeacon>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
