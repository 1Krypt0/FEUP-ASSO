export type Device = {
	id: number;
	dataTopic: string;
	actionTopic: string;
	name: string;
	macAddress: string;
	roomID: number;
	currentValue: string;
	status: 'CONNECTED' | 'DISCONNECTED';
	type: 'LIGHT' | 'MEDIA' | 'CLIMATE';
};
