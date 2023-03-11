import type { PageServerLoad } from './$types';

export const load = (({ params }) => {
	return {
		bg_img: `url('/bg-${params.page}.svg')`,
		page: params.page
	};
}) satisfies PageServerLoad;
