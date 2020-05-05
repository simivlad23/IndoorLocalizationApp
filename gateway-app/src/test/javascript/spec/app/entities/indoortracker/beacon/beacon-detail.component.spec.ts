import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { BeaconDetailComponent } from 'app/entities/indoortracker/beacon/beacon-detail.component';
import { Beacon } from 'app/shared/model/indoortracker/beacon.model';

describe('Component Tests', () => {
  describe('Beacon Management Detail Component', () => {
    let comp: BeaconDetailComponent;
    let fixture: ComponentFixture<BeaconDetailComponent>;
    const route = ({ data: of({ beacon: new Beacon(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewayTestModule],
        declarations: [BeaconDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(BeaconDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(BeaconDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load beacon on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.beacon).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
