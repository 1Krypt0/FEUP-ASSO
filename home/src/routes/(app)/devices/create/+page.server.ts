import type { Device } from '$lib/types/device';
import type { PageServerLoad } from './$types';

const BASE_URL = 'http://localhost:8080';

export const load = (async () => {
	const res = await fetch(`${BASE_URL}/devices/?room=0`);
	const foundDevices: Device[] = await res.json();

	return { foundDevices };
}) satisfies PageServerLoad;
