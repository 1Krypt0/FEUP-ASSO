import type { PageServerLoad } from './$types';
import { getDevice } from '$lib/services/devices';

export const load = (async ({ params }) => {
	const device = await getDevice(params.id);
	return { device };
}) satisfies PageServerLoad;
