import type { Room } from '$lib/types/room';
import type { LayoutServerLoad } from './$types';

const BASE_URL = 'http://localhost:8080';

export const load = (async () => {
	const roomRes = await fetch(`${BASE_URL}/rooms/`);
	const rooms: Room[] = await roomRes.json();

	const categories = [
		{
			name: 'Lights',
			id: 'lights'
		},
		{
			name: 'Media',
			id: 'media'
		},
		{
			name: 'Climate',
			id: 'climate'
		}
	];

	return {
		rooms,
		categories
	};
}) satisfies LayoutServerLoad;
