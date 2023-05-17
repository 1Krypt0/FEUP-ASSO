import { redirect, type Actions } from '@sveltejs/kit';
import type { PageServerLoad } from './$types';

export const load = (({ params }) => {
	const deviceID = params.id;
	const name = 'Dummy Name';
	const roomID = 1;
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

		console.log(`New Name: ${newName}, New Room: ${newRoom}`);

		// TODO: Update the data on the server with the new names

		throw redirect(302, `/devices/${params.id}`);
	}
} satisfies Actions;
