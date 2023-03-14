import type { PageServerLoad } from './$types';
import { getDevice, getDevices } from '$lib/services/devices';

export const load = (async ({ params }) => {
	// let data = await parent();
	console.log(params.id)
	const device = await getDevice(params.id);
	console.log(device)
	 return { device };
}) satisfies PageServerLoad;
