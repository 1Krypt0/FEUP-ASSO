import type { PageServerLoad } from './$types';

export const load = (() => {
	// TODO: Fetch devices with room = NULL to get the new devices;
	const foundDevices = [
		{ name: 'default name 1', id: 0 },
		{ name: 'default name 2', id: 1 },
		{ name: 'MI Wireless Headphones', id: 2 },
		{ name: 'Samsung Galaxy Buds 2', id: 3 },
		{ name: 'Samsung Galaxy Buds 2', id: 4 },
		{ name: 'Samsung Galaxy Buds 2', id: 5 },
		{ name: 'Samsung Galaxy Buds 2', id: 6 }
	];
	return { foundDevices };
}) satisfies PageServerLoad;
