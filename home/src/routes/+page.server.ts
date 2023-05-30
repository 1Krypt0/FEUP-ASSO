import type { Room } from '$lib/types/room';
import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

const ROOMS_URL = 'http://localhost:8080/rooms/';

export const load = (async () => {
	const res = await fetch(ROOMS_URL);
	const rooms: Room[] = await res.json();

	if (rooms.length === 0) {
		throw redirect(303, 'rooms/create');
	} else {
		const first = rooms.at(0)?.id;
		throw redirect(303, `rooms/${first}`);
	}
}) satisfies PageServerLoad;
