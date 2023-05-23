type Action = {
	id: number;
	type: string;
	required: string[] | undefined;
};

type DeviceAction = {
	id: number;
	action: Action;
	name: string;
	properties: object | undefined;
	state: string;
};

export type Device = {
	id: number;
	category: string;
	displayName: string;
	customName: string;
	room: number;
	actions: DeviceAction[];
};
