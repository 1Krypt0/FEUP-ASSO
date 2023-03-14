import type { PageServerLoad } from './$types';
import { getDevices } from '$lib/services/devices';

export const load = (async ({ parent }) => {
	let data = await parent();

	const devices = await getDevices(data.type[data.page]);
	return { devices };
}) satisfies PageServerLoad;
