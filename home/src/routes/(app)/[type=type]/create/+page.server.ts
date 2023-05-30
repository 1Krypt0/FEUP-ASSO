import type { Room } from '$lib/types/room';
import { fail, redirect, type Actions } from '@sveltejs/kit';

const BASE_URL = 'http://localhost:8080';

const headers = new Headers();
headers.append('Content-Type', 'application/json');

export const actions = {
	default: async ({ request }) => {
		const data = await request.formData();
		const name = data.get('name') as string;

		if (!name) {
			return fail(400, { name, missing: true });
		}

		const res = await fetch(`${BASE_URL}/rooms/`, {
			method: 'POST',
			body: JSON.stringify({
				name: name
			}),
			headers: headers
		});

		if (res.ok) {
			const resBody: Room = await res.json();

			const id = resBody.id;

			throw redirect(303, `/rooms/${id}`);
		}

		if (res.status === 405) {
			return fail(405, { error: true, description: 'Invalid input' });
		}
		return true;
	}
} satisfies Actions;
