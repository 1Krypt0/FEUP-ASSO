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
	//const device = await getDevice(params.id);

	// TODO: Change to data fetching when available
	const device = 
		{
			id: 1,
			name: 'Device 1',
			room: 1,
			category: 'lights'
		};
		
	return { device};
}) satisfies PageServerLoad;
