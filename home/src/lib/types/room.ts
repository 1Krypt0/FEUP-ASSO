import type { Device } from './device';

export type Room = {
	id: number;
	name: string;
	devices: Device[];
};
