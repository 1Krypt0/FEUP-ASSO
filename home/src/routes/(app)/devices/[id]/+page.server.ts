import type { Device } from '$lib/types/device';
import type { PageServerLoad } from './$types';
// import { getDevice } from '$lib/services/devices';

const BASE_URL = 'http://localhost:8080';

interface Properties {
	min: number | null
	max: number | null
	step: number | null
}

interface Action {
	type: string
	name: string
	updatable: boolean
}



export const load = (async ({params}) => {
	//const device = await getDevice(params.id);

	// TODO: Change to data fetching when available
	const res = await fetch(`${BASE_URL}/devices/${params.id}`);
	const device: any = await res.json();

	console.log(device);
		
	return { device };
}) satisfies PageServerLoad;
