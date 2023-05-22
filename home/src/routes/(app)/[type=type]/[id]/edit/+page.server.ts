import { fail, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (async ({ params, parent }) => {
	const parentData = await parent();
	const room = parentData.rooms.find((elem) => elem.id == Number.parseInt(params.id));

	return {
		room
	};
}) satisfies PageServerLoad;

export const actions = {
	default: async ({ request }) => {
		const data = await request.formData();
		const newName = data.get('name');

		if (!newName) {
			return fail(400, { name, missing: true });
		}
		//TODO: Change to update actual room name
		return true;
	}
} satisfies Actions;
