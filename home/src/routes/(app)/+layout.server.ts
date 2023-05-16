import type { LayoutServerLoad } from './$types';

export const load = (async () => {
	// TODO: Change to data fetching when it is implemented
	const rooms: { name: string; id: number }[] = [
		{
			name: 'Roomless Devices',
			id: 0
		},
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

	return {
		rooms
	};
}) satisfies LayoutServerLoad;
