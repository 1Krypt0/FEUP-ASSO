import type { ParamMatcher } from '@sveltejs/kit';

export const match = ((params) => {
	return params === 'room' || params === 'category';
}) satisfies ParamMatcher;
