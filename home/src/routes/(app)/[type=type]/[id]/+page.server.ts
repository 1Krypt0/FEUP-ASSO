import type { Device } from '$lib/types/device';
import type { PageServerLoad } from './$types';

const BASE_URL = 'http://localhost:8080';

export const load = (async ({ params, parent }) => {
	// TODO: If the category is not found, redirect to 404
	const parentData = await parent();

	let name = '';
	let isRoom = true;

	const field = params.type === 'categories' ? 'category' : params.type === 'rooms' ? 'room' : '';
	const res = await fetch(`${BASE_URL}/devices/?${field}=${params.id}`);
	const devices: Device[] = await res.json();

	console.log('DEVICES');
	console.log(devices);

	if (params.type === 'categories') {
		name = parentData.categories.find((elem) => elem.id === Number.parseInt(params.id))?.name || '';
		isRoom = false;
	} else if (params.type === 'rooms') {
		name = parentData.rooms.find((elem) => elem.id === Number.parseInt(params.id))?.name || '';
		isRoom = true;
	}

	return {
		name,
		devices,
		isRoom
	};
}) satisfies PageServerLoad;
