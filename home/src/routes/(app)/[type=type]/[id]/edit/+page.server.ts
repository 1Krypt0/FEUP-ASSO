import { fail, redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

const BASE_URL = 'http://localhost:8080';

const headers = new Headers();
headers.append('Content-Type', 'application/json');

export const load = (async ({ params, parent }) => {
	if (params.type === 'categories') {
		throw redirect(303, '/rooms/create');
	}

	// Doig this to reduce number of fetch requests
	const parentData = await parent();
	const room = parentData.rooms.find((elem) => elem.id == Number.parseInt(params.id));

	return {
		room
	};
}) satisfies PageServerLoad;

export const actions = {
	default: async ({ request, params }) => {
		const data = await request.formData();
		const newName = data.get('name');
		const id = params.id;

		if (!newName) {
			return fail(400, { newName, missing: true });
		}

		const res = await fetch(`${BASE_URL}/rooms/${id}`, {
			method: 'PUT',
			body: JSON.stringify({
				name: newName
			}),
			headers: headers
		});

		if (res.ok) {
			throw redirect(303, `/rooms/${id}`);
		} else {
			return fail(405, { error: true, description: 'Invalid input' });
		}
	}
} satisfies Actions;
