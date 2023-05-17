import type { PageServerLoad } from './$types';

export const load = (() => {
	const foundDevices = [
		{ name: 'default name 1' },
		{ name: 'default name 2' },
		{ name: 'MI Wireless Headphones' },
		{ name: 'Samsung Galaxy Buds 2' },
		{ name: 'Samsung Galaxy Buds 2' },
		{ name: 'Samsung Galaxy Buds 2' },
		{ name: 'Samsung Galaxy Buds 2' }
	];
	return { foundDevices };
}) satisfies PageServerLoad;
