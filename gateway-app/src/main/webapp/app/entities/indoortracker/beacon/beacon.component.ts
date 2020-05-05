import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBeacon } from 'app/shared/model/indoortracker/beacon.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { BeaconService } from './beacon.service';
import { BeaconDeleteDialogComponent } from './beacon-delete-dialog.component';

@Component({
  selector: 'jhi-beacon',
  templateUrl: './beacon.component.html'
})
export class BeaconComponent implements OnInit, OnDestroy {
  beacons: IBeacon[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;

  constructor(
    protected beaconService: BeaconService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks
  ) {
    this.beacons = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0
    };
    this.predicate = 'id';
    this.ascending = true;
  }

  loadAll(): void {
    this.beaconService
      .query({
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe((res: HttpResponse<IBeacon[]>) => this.paginateBeacons(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.beacons = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBeacons();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBeacon): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBeacons(): void {
    this.eventSubscriber = this.eventManager.subscribe('beaconListModification', () => this.reset());
  }

  delete(beacon: IBeacon): void {
    const modalRef = this.modalService.open(BeaconDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.beacon = beacon;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateBeacons(data: IBeacon[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.beacons.push(data[i]);
      }
    }
  }
}
