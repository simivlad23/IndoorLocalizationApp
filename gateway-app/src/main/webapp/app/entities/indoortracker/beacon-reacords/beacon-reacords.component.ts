import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBeaconReacords } from 'app/shared/model/indoortracker/beacon-reacords.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BeaconReacordsService } from './beacon-reacords.service';
import { BeaconReacordsDeleteDialogComponent } from './beacon-reacords-delete-dialog.component';

@Component({
  selector: 'jhi-beacon-reacords',
  templateUrl: './beacon-reacords.component.html'
})
export class BeaconReacordsComponent implements OnInit, OnDestroy {
  beaconReacords: IBeaconReacords[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected beaconReacordsService: BeaconReacordsService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.beaconReacords = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.beaconReacordsService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IBeaconReacords[]>) => this.paginateBeaconReacords(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.beaconReacords = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBeaconReacords();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBeaconReacords): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBeaconReacords(): void {
    this.eventSubscriber = this.eventManager.subscribe('beaconReacordsListModification', () => this.reset());
  }

  delete(beaconReacords: IBeaconReacords): void {
    const modalRef = this.modalService.open(BeaconReacordsDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.beaconReacords = beaconReacords;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateBeaconReacords(data: IBeaconReacords[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.beaconReacords.push(data[i]);
      }
    }
  }
}
