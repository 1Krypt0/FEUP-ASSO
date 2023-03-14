export type Device = {
	actionTopic: string;
	currentValue: string;
	dataTopic: string;
	id: string;
	macAddress: string;
	name: string;
	status: 'CONNECTED' | 'DISCONNECTED';
	type: 'LIGHT' | 'MEDIA' | 'CLIMATE';
};
