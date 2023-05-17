import type { RequestHandler } from './$types';

export const POST = (async ({ request }) => {
	const data = await request.json();

	const name = data['name'];
	const roomID = data['roomID'];
	const categoryID = data['categoryID'];
	const deviceName = data['selectedName'];

	console.log(
		`Name: ${name}, Room: ${roomID}, Category: ${categoryID}, Device Name: ${deviceName}`
	);

	// TODO: Save new device to database

	return new Response(null, { status: 200 });
}) satisfies RequestHandler;
