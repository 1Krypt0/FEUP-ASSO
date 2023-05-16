import type { PageServerLoad } from './$types';
// import { getDevice } from '$lib/services/devices';

interface Device {
	[index: string]: number | string;
	id: number;
	name: string;
	room: number;
	category: string;
}

export const load = (async ({ params, parent }) => {
	const parentData = await parent();
	//const device = await getDevice(params.id);

	let divisionName;
	let isRoom;
	if (params.type === 'category') {
		divisionName = params.id.charAt(0).toUpperCase() + params.id.slice(1);
		isRoom = false;
	} else if (params.type === 'room') {
		const room = parentData.rooms.find((elem) => elem.id == Number.parseInt(params.id));
		divisionName = room?.name;
		isRoom = true;
	}

	// TODO: Change to data fetching when available
	const devices: Device[] = [
		{
			id: 1,
			name: 'Device 1',
			room: 1,
			category: 'lights'
		},
		{
			id: 2,
			name: 'Device 2',
			room: 0,
			category: 'media'
		},
		{
			id: 3,
			name: 'Device 3',
			room: 1,
			category: 'media'
		},
		{
			id: 4,
			name: 'Device 4',
			room: 3,
			category: 'climate'
		},
		{
			id: 5,
			name: 'Device 5',
			room: 1,
			category: 'climate'
		},

		{
			id: 6,
			name: 'Device 6',
			room: 1,
			category: 'lights'
		},

		{
			id: 7,
			name: 'Device 7',
			room: 1,
			category: 'lights'
		}
	];

	const divisionDevices = devices.filter((elem) => elem[params.type] == params.id);
	return { divisionDevices, divisionName, isRoom };
}) satisfies PageServerLoad;
