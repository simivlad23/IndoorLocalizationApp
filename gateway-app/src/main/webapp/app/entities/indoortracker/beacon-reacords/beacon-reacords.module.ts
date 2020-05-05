import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { GatewaySharedModule } from 'app/shared/shared.module';
import { BeaconReacordsComponent } from './beacon-reacords.component';
import { BeaconReacordsDetailComponent } from './beacon-reacords-detail.component';
import { BeaconReacordsUpdateComponent } from './beacon-reacords-update.component';
import { BeaconReacordsDeleteDialogComponent } from './beacon-reacords-delete-dialog.component';
import { beaconReacordsRoute } from './beacon-reacords.route';

@NgModule({
  imports: [GatewaySharedModule, RouterModule.forChild(beaconReacordsRoute)],
  declarations: [
    BeaconReacordsComponent,
    BeaconReacordsDetailComponent,
    BeaconReacordsUpdateComponent,
    BeaconReacordsDeleteDialogComponent
  ],
  entryComponents: [BeaconReacordsDeleteDialogComponent]
})
export class IndoortrackerBeaconReacordsModule {}
