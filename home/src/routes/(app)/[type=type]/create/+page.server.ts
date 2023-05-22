import { fail, type Actions } from '@sveltejs/kit';

export const actions = {
	default: async ({ request }) => {
		const data = await request.formData();
		const name = data.get('name');

		if (!name) {
			return fail(400, { name, missing: true });
		}

		// TODO: Complete with actual creation
		return true;
	}
} satisfies Actions;
