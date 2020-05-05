import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBeaconReacords, BeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';
import { BeaconReacordsService } from './beacon-reacords.service';

@Component({
  selector: 'jhi-beacon-reacords-update',
  templateUrl: './beacon-reacords-update.component.html'
})
export class BeaconReacordsUpdateComponent implements OnInit {
  isSaving = false;
  timeReacordDp: any;

  editForm = this.fb.group({
    id: [],
    timeReacord: [],
    readRssi: [],
    meanRssi: [],
    smootRssi: [],
    smootDistance: [],
    meanDistance: []
  });

  constructor(protected beaconReacordsService: BeaconReacordsService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ beaconReacords }) => {
      this.updateForm(beaconReacords);
    });
  }

  updateForm(beaconReacords: IBeaconReacords): void {
    this.editForm.patchValue({
      id: beaconReacords.id,
      timeReacord: beaconReacords.timeReacord,
      readRssi: beaconReacords.readRssi,
      meanRssi: beaconReacords.meanRssi,
      smootRssi: beaconReacords.smootRssi,
      smootDistance: beaconReacords.smootDistance,
      meanDistance: beaconReacords.meanDistance
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const beaconReacords = this.createFromForm();
    if (beaconReacords.id !== undefined) {
      this.subscribeToSaveResponse(this.beaconReacordsService.update(beaconReacords));
    } else {
      this.subscribeToSaveResponse(this.beaconReacordsService.create(beaconReacords));
    }
  }

  private createFromForm(): IBeaconReacords {
    return {
      ...new BeaconReacords(),
      id: this.editForm.get(['id'])!.value,
      timeReacord: this.editForm.get(['timeReacord'])!.value,
      readRssi: this.editForm.get(['readRssi'])!.value,
      meanRssi: this.editForm.get(['meanRssi'])!.value,
      smootRssi: this.editForm.get(['smootRssi'])!.value,
      smootDistance: this.editForm.get(['smootDistance'])!.value,
      meanDistance: this.editForm.get(['meanDistance'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBeaconReacords>>): void {
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
