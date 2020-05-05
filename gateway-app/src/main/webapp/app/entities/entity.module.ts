import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'beacon',
        loadChildren: () => import('./indoortracker/beacon/beacon.module').then(m => m.IndoortrackerBeaconModule)
      },
      {
        path: 'beacon-reacords',
        loadChildren: () => import('./indoortracker/beacon-reacords/beacon-reacords.module').then(m => m.IndoortrackerBeaconReacordsModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class GatewayEntityModule {}
