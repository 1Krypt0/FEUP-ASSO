export type Device = {
	id: number;
	name: string;
	displayName: string;
	macAddress: string;
	added: boolean;
	roomID: number;
	categoryID: number;
	status: 'CONNECTED' | 'DISCONNECTED';
	type: 'LIGHT' | 'MEDIA' | 'CLIMATE';
};
