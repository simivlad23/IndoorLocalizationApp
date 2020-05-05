export interface IBeacon {
  id?: number;
  name?: string;
  address?: string;
  txPower?: number;
  advertistingInterval?: number;
}

export class Beacon implements IBeacon {
  constructor(
    public id?: number,
    public name?: string,
    public address?: string,
    public txPower?: number,
    public advertistingInterval?: number
  ) {}
}
