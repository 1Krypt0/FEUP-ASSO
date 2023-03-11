import type { LayoutServerLoad } from './$types';

export const load = (async () => {
	return {
		colors: {
			pageColors: {
				lights: 'bg-lights-50',
				media: 'bg-media-50',
				climate: 'bg-climate-50'
			},
			sidebarColors: {
				lights: 'bg-lights-100',
				media: 'bg-media-100',
				climate: 'bg-climate-100'
			}
		}
	};
}) satisfies LayoutServerLoad;
