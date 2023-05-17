import { DropletIcon, MonitorIcon, SunIcon } from 'svelte-feather-icons';
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
