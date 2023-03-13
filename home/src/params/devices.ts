import type { ParamMatcher } from '@sveltejs/kit';

export const match = ((param: string) => {
	switch (param) {
		case 'lights':
		case 'media':
		case 'climate':
			return true;
		default:
			return false;
	}
}) satisfies ParamMatcher;
