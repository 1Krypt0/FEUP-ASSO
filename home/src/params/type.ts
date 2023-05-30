import type { ParamMatcher } from '@sveltejs/kit';

export const match = ((params) => {
	return params === 'rooms' || params === 'categories';
}) satisfies ParamMatcher;
