import type { LayoutServerLoad } from './$types';

export const load = (async () => {
	return {
		colors: {
			lights: 'bg-lights-100',
			media: 'bg-media-100',
			climate: 'bg-climate-100'
		}
	};
}) satisfies LayoutServerLoad;
