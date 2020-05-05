import { Moment } from 'moment';

export interface IBeaconReacords {
  id?: number;
  timeReacord?: Moment;
  readRssi?: number;
  meanRssi?: number;
  smootRssi?: number;
  smootDistance?: number;
  meanDistance?: number;
}

export class BeaconReacords implements IBeaconReacords {
  constructor(
    public id?: number,
    public timeReacord?: Moment,
    public readRssi?: number,
    public meanRssi?: number,
    public smootRssi?: number,
    public smootDistance?: number,
    public meanDistance?: number
  ) {}
}
