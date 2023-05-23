export type Device = {
	id: number;
	name: string;
	displayName: string;
	macAddress: string;
	added: boolean;
	room: number;
	category: number;
	status: 'CONNECTED' | 'DISCONNECTED';
	type: 'LIGHT' | 'MEDIA' | 'CLIMATE';
};
