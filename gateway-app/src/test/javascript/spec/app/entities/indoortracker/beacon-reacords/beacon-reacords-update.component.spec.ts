import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { GatewayTestModule } from '../../../../test.module';
import { BeaconReacordsUpdateComponent } from 'app/entities/indoortracker/beacon-reacords/beacon-reacords-update.component';
import { BeaconReacordsService } from 'app/entities/indoortracker/beacon-reacords/beacon-reacords.service';
import { BeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';

describe('Component Tests', () => {
  describe('BeaconReacords Management Update Component', () => {
    let comp: BeaconReacordsUpdateComponent;
    let fixture: ComponentFixture<BeaconReacordsUpdateComponent>;
    let service: BeaconReacordsService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [GatewayTestModule],
        declarations: [BeaconReacordsUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(BeaconReacordsUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(BeaconReacordsUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(BeaconReacordsService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new BeaconReacords(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new BeaconReacords();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
