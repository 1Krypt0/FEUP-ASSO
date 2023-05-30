import type { Actions } from '@sveltejs/kit';

export const actions = {
	default: async ({ request }) => {
		const data = await request.formData();

		// TODO: Complete with actual creation
		return true;
	}
} satisfies Actions;
