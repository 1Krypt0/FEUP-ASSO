import type { LayoutServerLoad } from './[page=devices]/$types';

export const load = (async ({ params }) => {
	return {
		page: params.page,
		bg_img: `url('/bg-${params.page}.svg')`,
		colors: {
			lights: {
				50: 'bg-lights-50',
				400: 'bg-lights-400',
				500: 'bg-lights-500'
			},
			media: {
				50: 'bg-media-50',
				400: 'bg-media-400',
				500: 'bg-media-500'
			},
			climate: {
				50: 'bg-climate-50',
				400: 'bg-climate-400',
				500: 'bg-climate-500'
			}
		}
	};
}) satisfies LayoutServerLoad;
