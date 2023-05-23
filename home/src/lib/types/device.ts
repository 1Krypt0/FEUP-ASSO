export type Device = {
	id: number;
	name: string;
	displayName: string;
	macAddress: string;
	added: boolean;
	room: number;
	category: number;
	status: 'CONNECTED' | 'DISCONNECTED';
	actions: DeviceAction[]
};

export type DeviceAction = {
	id: number;
	name: string;
	actionName: string;
	properties: Record<string, string | number>
	status: string;
};
