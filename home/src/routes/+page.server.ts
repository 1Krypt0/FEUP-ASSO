import { redirect } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (() => {
	// TODO: Fetch the rooms and if none is present redirect to this one. Otherwise redirect to the first one that shows up
	const rooms: { name: string; id: number }[] = [
		{
			name: 'Room 1',
			id: 1
		},
		{
			name: 'Room 2',
			id: 2
		},
		{
			name: 'Room 3',
			id: 3
		}
	];

	if (rooms.length === 0) {
		throw redirect(303, 'rooms/create');
	} else {
		const first = rooms.at(0)?.id;
		throw redirect(303, `rooms/${first}`);
	}
}) satisfies PageServerLoad;
