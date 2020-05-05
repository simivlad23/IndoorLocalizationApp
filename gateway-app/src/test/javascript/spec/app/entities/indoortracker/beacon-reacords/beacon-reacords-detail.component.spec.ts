import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { BeaconReacordsDetailComponent } from 'app/entities/indoortracker/beacon-reacords/beacon-reacords-detail.component';
import { BeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';

describe('Component Tests', () => {
  describe('BeaconReacords Management Detail Component', () => {
    let comp: BeaconReacordsDetailComponent;
    let fixture: ComponentFixture<BeaconReacordsDetailComponent>;
    const route = ({ data: of({ beaconReacords: new BeaconReacords(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewayTestModule],
        declarations: [BeaconReacordsDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BeaconReacordsDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BeaconReacordsDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load beaconReacords on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.beaconReacords).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
