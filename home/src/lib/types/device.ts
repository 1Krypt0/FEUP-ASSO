export type Device = {
	actionTopic: string;
	currentValue: number;
	dataTopic: string;
	id: string;
	macAddress: string;
	name: string;
	status: 'CONNECTED' | 'DISCONNECTED';
	type: 'LIGHT' | 'MEDIA' | 'CLIMATE';
};
