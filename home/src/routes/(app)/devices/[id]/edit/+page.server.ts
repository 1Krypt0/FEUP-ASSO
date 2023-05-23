import type { Device } from '$lib/types/device';
import { fail, redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

const BASE_URL = 'http://localhost:8080';

const headers = new Headers();
headers.append('Content-Type', 'application/json');

export const load = (async ({ params }) => {
	const deviceID = params.id;
	const res = await fetch(`${BASE_URL}/devices/${deviceID}`);
	const data: Device = await res.json();

	const name = data.displayName;
	const roomID = data.room;

	return {
		deviceID,
		name,
		roomID
	};
}) satisfies PageServerLoad;

export const actions = {
	default: async ({ request, params }) => {
		const data = await request.formData();

		const newName = data.get('name');
		const newRoom = data.get('room');

		const res = await fetch(`${BASE_URL}/devices/${params.id}`, {
			method: 'PUT',
			body: JSON.stringify({
				displayName: newName,
				room: newRoom
			}),
			headers: headers
		});

		if (res.ok) {
			throw redirect(302, `/devices/${params.id}`);
		} else {
			return fail(405, { error: true, description: 'Invalid input' });
		}

		// TODO: Update the data on the server with the new names
	}
} satisfies Actions;
